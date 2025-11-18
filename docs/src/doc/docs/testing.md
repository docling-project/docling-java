# Testing

The `docling-testing` area contains tooling used to validate Docling Serve images and to help you test your integration. The main utility today is a small [Quarkus](https://quarkus.io)/[Picocli](https://quarkus.io/guides/picocli) command that automatically verifies multiple Docling Serve versions by:

- Discovering container tags (versions) from a registry (default: GitHub Container Registry)
- Starting each version with Testcontainers
- Performing a health check and a real conversion using the reference HTTP client
- Aggregating results, saving logs and a Markdown report, and optionally opening a GitHub issue when failures occur

If you’re looking to test your application code against a running server during unit/integration tests, consider the Testcontainers module instead:
- [`docling-testcontainers`](testcontainers.md)

## When to use `docling-testing`

- You want to validate that a range of Docling Serve image tags start correctly and can perform a basic conversion.
- You need a repeatable way to check compatibility across versions on CI.
- You want automatic result aggregation and, optionally, automated GitHub issue creation when a regression is detected.

## What’s included

Submodule: `docling-testing/docling-version-tests`

- Runtime: [Quarkus (Picocli) CLI](https://quarkus.io/guides/picocli)
- Orchestrates Docling Serve containers with Testcontainers
- Uses the reference client (`docling-serve-client`) to call the Health and Convert endpoints
- Produces artifacts: logs and a Markdown summary in an output directory

## Prerequisites

- Java 17+
- Docker or an alternative container runtime compatible with Testcontainers
- Network access to pull images from your chosen registry (default: `ghcr.io`)

## Run the version tests locally

You can run the CLI directly from the repository using the Quarkus Gradle plugin.

Option A — Dev mode (quick iteration):

```bash
./gradlew :docling-version-tests:quarkusDev
```

Option B — Build a runnable JAR and execute it:

```bash
# Build the application
./gradlew :docling-version-tests:build

# Run it
java -jar docling-testing/docling-version-tests/build/quarkus-app/quarkus-run.jar
```

Notes:
- The CLI will spin up Docker containers; ensure your user can access the Docker daemon.
- By default it fetches all version tags for the given image and tests them serially (configurable with `--parallelism`).

## CLI options

These map to the Picocli command `docling-serve-version-tests`.

- `-p, --parallelism` (int, default `1`): How many versions to verify in parallel.
- `-i, --image` (string, default `docling-project/docling-serve`): The image repository name (without registry and tag).
- `-r, --registry` (string, default `ghcr.io`): Which registry to query for tags and pull from.
- `-o, --output` (path, default `results`): Output directory. A timestamped subfolder is created per run.
- `-t, --tags` (multiple): Explicit list of tags to test. If omitted, the tool fetches all available version tags from the registry.
- `-g, --create-github-issue` (boolean, default `true`): When enabled, and if configured with credentials, creates/updates a GitHub issue on failures.

Examples:

```bash
# Test the latest published versions from GHCR, one at a time
./gradlew :docling-version-tests:quarkusDev

# Test a specific set of tags in parallel
./gradlew :docling-version-tests:quarkusDev \
  -p 3 \
  -t v1.8.0 \
  -t v1.7.3 \
  -t v1.6.5

# Save artifacts under a custom directory and disable GitHub issue creation
./gradlew :docling-version-tests:quarkusDev \
  -o ./my-results \
  --no-create-github-issue
```

## What the tool verifies

For each tag under test, the tool:

1. Starts `ghcr.io/<registry>/<image>:<tag>` with Testcontainers (default internal port 5001).
2. Checks `/health` using `DoclingServeApi.health()`.
3. Performs a conversion with `DoclingServeApi.convertSource()` on a simple HTTP source.
4. Asserts that the response contains no errors and includes expected fields (filename, Markdown, text, JSON content).
5. Captures server logs and aggregates pass/fail status per tag.

Under the hood, this is implemented in:
- `ai.docling.client.tester.service.TagsTester` (spins up the container, runs checks)
- `ai.docling.client.tester.VersionTestsCommand` (parses options, parallelizes work, aggregates results)

## Results and artifacts

At the end of a run, you’ll find a timestamped directory under the output path, for example:

```
results/
  results-20250101T121314Z/
    summary.md           # Markdown summary of tags tested and outcomes
    <tag>-server.log     # Logs captured from the Docling Serve container for each tag
    ...
```

Depending on your configuration, a GitHub issue may be created or updated to include the summary table and failure details.

## CI tips

- Ensure Docker is available to the build agent (for GitHub Actions, see the Testcontainers setup guides).
- Consider caching Docker layers between runs to speed up tag pulls.
- Use `--parallelism` prudently on shared or small CI runners to avoid resource contention.
- If your environment is slow to pull images or start containers, increase the startup timeout via the internal config (the tool already uses a conservative default of 2 minutes per tag).

## Related modules

- Reference HTTP client used by the tool: [`docling-serve-client`](docling-serve/serve-client.md)
- Testcontainers integration (use this if you want Docling in your own tests): [`docling-testcontainers`](testcontainers.md)
