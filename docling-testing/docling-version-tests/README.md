# Docling Version Tests

[![codecov](https://codecov.io/gh/docling-project/docling-java/branch/main/graph/badge.svg?flag=docling-version-tests)](https://codecov.io/gh/docling-project/docling-java/tree/main/docling-testing/docling-version-tests)

A [Quarkus](https://quarkus.io)-based command-line application for automated compatibility testing of the [`docling-serve-api`](../../docling-serve/docling-serve-api) client library against multiple versions of the [Docling Serve](https://github.com/docling-project/docling-serve) container image.

## Overview

This application automatically tests `docling-java` client compatibility by:

1. Fetching available Docker image tags from the [Docling Serve container registry](https://ghcr.io/docling-project/docling-serve)
2. Starting Docling Serve containers for each version tag
3. Executing conversion requests against each container
4. Validating responses and health checks
5. Generating detailed markdown reports
6. Optionally creating GitHub issues for failures

## Technologies

This application leverages the following frameworks and libraries:

### Core Framework

- **[Quarkus](https://quarkus.io)** - Supersonic Subatomic Java framework for building cloud-native applications

### Quarkus Extensions

| Extension                                                                   | Purpose                                                                    | Documentation                                                                                 |
|-----------------------------------------------------------------------------|----------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|
| [Picocli](https://quarkus.io/guides/picocli)                                | Command-line interface framework with argument parsing and help generation | [Quarkus Picocli Guide](https://quarkus.io/guides/picocli)                                    |
| [Config YAML](https://quarkus.io/guides/config-yaml)                        | YAML-based configuration support (`application.yml`)                       | [Quarkus YAML Configuration](https://quarkus.io/guides/config-yaml)                           |
| [ArC](https://quarkus.io/guides/cdi-reference)                              | Dependency injection (CDI implementation)                                  | [Quarkus CDI Reference](https://quarkus.io/guides/cdi-reference)                              |
| [SmallRye Config](https://quarkus.io/guides/config-reference)               | Type-safe configuration mapping (`@ConfigMapping`)                         | [Quarkus Configuration Guide](https://quarkus.io/guides/config-reference)                     |
| [REST Client Jackson](https://quarkus.io/guides/rest-client)                | Reactive REST client with Jackson serialization (for registry API calls)   | [Quarkus REST Client Guide](https://quarkus.io/guides/rest-client)                            |
| [Qute](https://quarkus.io/guides/qute)                                      | Server-side template engine (generates markdown reports)                   | [Quarkus Qute Guide](https://quarkus.io/guides/qute)                                          |
| [JUnit 5](https://quarkus.io/guides/getting-started-testing)                | Testing framework with Quarkus extensions                                  | [Quarkus Testing Guide](https://quarkus.io/guides/getting-started-testing)                    |
| [WireMock](https://docs.quarkiverse.io/quarkus-wiremock/dev/index.html)     | HTTP mocking for integration tests                                         | [Quarkus WireMock Extension](https://docs.quarkiverse.io/quarkus-wiremock/dev/index.html)     |
| [GitHub API](https://docs.quarkiverse.io/quarkus-github-api/dev/index.html) | GitHub REST API client (for automated issue creation)                      | [Quarkus GitHub API Extension](https://docs.quarkiverse.io/quarkus-github-api/dev/index.html) |

### Additional Libraries

- **[AssertJ](https://assertj.github.io/doc/)** - Fluent assertions for validation and testing
- **[Docling Testcontainers](https://testcontainers.com/modules/docling/)** - Spins up ephemeral Docling Serve Docker containers for each version test
- **[Semver4j](https://github.com/vdurmont/semver4j)** - Semantic versioning parsing and filtering
- **[SmallRye Mutiny](https://smallrye.io/smallrye-mutiny/)** - Reactive programming for parallel test execution

### Internal Dependencies

- [`docling-serve-client`](../../docling-serve/docling-serve-client) - Java HTTP client for Docling Serve API
- [`docling-testcontainers`](../../docling-testcontainers) - Testcontainers wrapper for Docling Serve
- [`docling-core`](../../docling-core) - Core Docling document model

## How It Works

### Architecture

![Architecture](https://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/docling-project/docling-java/main/docling-testing/docling-version-tests/images/architecture.puml)

### Execution Flow

#### 1. Command Initialization (`VersionTestsCommand`)

The application starts via [Quarkus Picocli](https://quarkus.io/guides/picocli) integration. The [`VersionTestsCommand`](src/main/java/ai/docling/client/tester/VersionTestsCommand.java) class is annotated with `@Command` and implements `Runnable`. It defines CLI options:

- `-p, --parallelism`: Number of concurrent container tests (default: 1)
- `-i, --image`: Docker image to test (default: `docling-project/docling-serve`)
- `-r, --registry`: Container registry URL (default: `ghcr.io`)
- `-o, --output`: Results output directory (default: `results/`)
- `-t, --tags`: Explicit list of tags to test (fetches all if omitted)
- `-e, --exclude-tags-regex`: Regex pattern to exclude tags
- `-g, --create-github-issue`: Create GitHub issue on test failure
- `-c, --cleanup-container-images`: Remove Docker images after testing

#### 2. Tag Discovery (`GHCRClient`)

If `--tags` is not provided, the application queries the container registry:

1. **Authentication**: Calls the registry's token endpoint (`/token?scope=repository:{image}:pull`)
2. **Fetch Tags**: Paginates through `/v2/{image}/tags/list` (1000 tags per page)
3. **Filter Versions**: Extracts semantic version tags (e.g., `v1.13.0`) and excludes non-version tags
4. **Apply Exclusions**: Filters out tags matching `--exclude-tags-regex`

The `GHCRClient` is a Quarkus REST Client interface (`@RegisterRestClient`) with declarative HTTP methods.

#### 3. Parallel Testing (`TagsTester` + `WorkParallelizer`)

For each tag, the `TagsTester` service:

1. **Spin Up Container**: Creates a `DoclingServeContainer` (Testcontainers wrapper) for the specific tag
   ```java
   var containerConfig = DoclingServeContainerConfig.builder()
       .image("{registry}/{image}:{tag}")
       .startupTimeout(Duration.ofMinutes(5))
       .build();
   var doclingContainer = new DoclingServeContainer(containerConfig);
   doclingContainer.start();
   ```

2. **Health Check**: Validates the `/health` endpoint returns `{"status": "ok"}`

3. **Execute Conversion**: Sends a test conversion request
   - **Source**: `https://docling.ai` (via `HttpSource`)
   - **Output Formats**: Markdown, JSON, Text
   - **Options**: `abortOnError=true`, `includeImages=true`

4. **Assertions**: Uses AssertJ to validate:
   - Response is not null
   - No errors in response
   - All output formats (markdown, text, JSON) are non-empty
   - JSON content deserializes to `DoclingDocument`

5. **Capture Logs**: Retrieves container logs via `doclingContainer.getLogs()`

6. **Cleanup**: Optionally removes the Docker image to save disk space

7. **Record Result**: Stores outcome as `TagTestResult` (success or failure with stack trace)

Parallelization is achieved via **SmallRye Mutiny** (`Multi.transformToUniAndMerge`), distributing work across a fixed thread pool.

#### 4. Results Handling

Results are processed by two handlers (run in parallel via `WorkParallelizer`):

##### A. [**MarkdownFileResultsHandler**](src/main/java/ai/docling/client/tester/service/results/MarkdownFileResultsHandler.java)

Generates a comprehensive markdown report using **Qute templates**:

- **Template**: [`results.md`](src/main/resources/templates/results/results.md)
- **Output**: `{output-dir}/results-{timestamp}/results.md`
- **Content**:
  - Summary table with ✅/❌ icons per tag
  - Expandable detail sections for each tag:
    - Success/failure message
    - Full stack trace (if failed)
    - Container logs

##### B. [**GithubIssueResultsHandler**](src/main/java/ai/docling/client/tester/service/results/GithubIssueResultsHandler.java)

If `--create-github-issue` is enabled and at least one test fails:

1. Reads `GITHUB_TOKEN` from environment
2. Creates an issue in the configured repository (default: `docling-project/docling-java`)
3. Issue body contains the markdown report
4. Labels: `automation`, `area:docling-serve`

Configured via `@ConfigMapping`:
```yaml
docling-version-tester:
  github:
    issue-creation:
      issue-org: docling-project  # default
      issue-repo: docling-java    # default
```

### Configuration

The application uses **Quarkus Config** with YAML support ([`application.yml`](src/main/resources/application.yml)):

- **REST Client**: Configures the `github-container-registry` client base URL (`https://ghcr.io`)
- **WireMock**: Dev/test mode uses WireMock to mock registry responses
- **Logging**: Configures log levels for Testcontainers, Docker client, etc.
- **Profiles**:
  - `%dev,test`: Uses WireMock on `localhost`
  - `%no-wiremock`: Disables WireMock for real registry calls

### Testing

The application includes unit tests for:

- **Command Options**: Validates Picocli argument parsing ([`VersionTestsCommandOptionTests`](src/test/java/ai/docling/client/tester/VersionTestsCommandOptionTests.java))
- **Domain Models**: Tests `TagTestResult` serialization ([`TagTestResultTests`](src/test/java/ai/docling/client/tester/domain/TagTestResultTests.java))
- **GHCR Client**: Mocks registry API with WireMock ([`GHCRClientTests`](src/test/java/ai/docling/client/tester/client/ghcr/GHCRClientTests.java))

Tests use:
- [**Quarkus JUnit**](https://quarkus.io/guides/getting-started-testing) (`@QuarkusTest`)
- [**Quarkus WireMock**](https://docs.quarkiverse.io/quarkus-wiremock/dev/index.html) for HTTP mocking
- [**AssertJ**](https://assertj.github.io/doc/) for fluent assertions

## Usage

### Development Mode (with live reload)

Run the CLI with specific arguments:
```bash
# Test specific tags
./gradlew :docling-version-tests:quarkusDev -Dquarkus.args="-t v1.13.0 v1.12.0 -p 2"

# Test all tags from GHCR
./gradlew :docling-version-tests:quarkusDev -Dquarkus.args="-p 4 --exclude-tags-regex '.*rc.*'"
```

### CI/CD Integration

GitHub Actions workflow ([`version-tests.yml`](../../.github/workflows/version-tests.yml)) runs this application on a schedule:

```yaml
- name: Run version tests
  run: |
    ./gradlew :docling-version-tests:build
    java -jar docling-testing/docling-version-tests/build/quarkus-app/quarkus-run.jar \
      --parallelism 2 \
      --output results \
      --create-github-issue
  env:
    GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
```

## Requirements

- **Java 17+** (tested on 17, 21, 25)
- **Docker** or **Podman** (for Testcontainers)
- **Gradle 8.5+**
- **Optional**: GraalVM for native compilation
- **Optional**: `GITHUB_TOKEN` environment variable (for issue creation)

## Output

Results are written to `{output-dir}/results-{timestamp}/`:

```
results/
└── results-2026-03-02T14-30-00.123456Z/
    └── results.md
```

Example `results.md` excerpt:

```markdown
# Results for ghcr.io/docling-project/docling-serve as of 2026-03-02T14:30:00.123456Z

| Tag | Result | Details |
| --- | ------ | ------- |
| v1.13.0 | ✅ SUCCESS | [Click for run details](#v1.13.0-details) |
| v1.12.0 | ❌ FAILURE | [Click for run details](#v1.12.0-details) |
```

## Coverage

View detailed coverage reports for this module on [Codecov](https://codecov.io/gh/docling-project/docling-java/tree/main/docling-testing/docling-version-tests).

## Development

### Project Structure

```
docling-version-tests/
├── src/
│   ├── main/
│   │   ├── java/ai/docling/client/tester/
│   │   │   ├── VersionTestsCommand.java         # CLI entrypoint
│   │   │   ├── client/
│   │   │   │   ├── RegistryClient.java          # Registry API interface
│   │   │   │   ├── RegistryClientFactory.java   # Client factory
│   │   │   │   └── ghcr/
│   │   │   │       └── GHCRClient.java          # GHCR REST client
│   │   │   ├── config/
│   │   │   │   └── Config.java                  # Type-safe config
│   │   │   ├── domain/
│   │   │   │   ├── Tags.java                    # Tag domain model
│   │   │   │   ├── TagsTestRequest.java         # Test request
│   │   │   │   ├── TagsTestResults.java         # Test results
│   │   │   │   └── TagTestResult.java           # Single tag result
│   │   │   └── service/
│   │   │       ├── TagsTester.java              # Core testing logic
│   │   │       ├── WorkParallelizer.java        # Parallel execution
│   │   │       └── results/
│   │   │           ├── ResultsHandler.java      # Handler interface
│   │   │           ├── ResultsHandlers.java     # Handler aggregator
│   │   │           ├── MarkdownFileResultsHandler.java
│   │   │           └── GithubIssueResultsHandler.java
│   │   └── resources/
│   │       ├── application.yml                  # Quarkus config
│   │       └── templates/
│   │           └── results/
│   │               └── results.md               # Qute template
│   └── test/
│       ├── java/                                # Unit tests
│       └── resources/
│           └── wiremock/                        # WireMock stubs
└── build.gradle.kts
```

### Adding a New Registry Client

1. Create interface extending `RegistryClient` in `client/`
2. Annotate with `@RegisterRestClient(configKey = "my-registry")`
3. Implement `getTokenForImage` and `getTags` methods
4. Add configuration to `application.yml`:
   ```yaml
   quarkus:
     rest-client:
       my-registry:
         url: https://my-registry.io
   ```
5. Update `RegistryClientFactory` to return your client

### Customizing the Report

Edit the Qute template at `src/main/resources/templates/results/results.md`. Qute syntax:

```markdown
{#each results.results}
  Tag: {it.tag}, Status: {it.result.status.name()}
{/each}
```

## License

This application is part of the `docling-java` project. See [LICENSE](../../LICENSE) for details.

## Contributing

See [CONTRIBUTING.md](../../CONTRIBUTING.md) for contribution guidelines. All commits must follow [Conventional Commits](https://www.conventionalcommits.org/).

## Related Documentation

- [Docling Serve Documentation](https://github.com/docling-project/docling-serve)
- [Quarkus Documentation](https://quarkus.io/guides/)
- [Docling Java API Documentation](../../docs/)
