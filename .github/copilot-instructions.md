# Docling Java – Copilot Instructions

## Project Overview

`docling-java` is a multi-module Java library providing a Java API for [Docling](https://github.com/docling-project), an IBM Research project for AI-based document processing (PDF, DOCX, PPTX, images, audio, etc.).

The project is published to Maven Central under the `ai.docling` group ID.

## Repository Structure

```
docling-java/
├── buildSrc/                         # Convention plugins (Gradle Kotlin DSL)
│   └── src/main/kotlin/
│       ├── docling-shared.gradle.kts          # group/version resolution
│       ├── docling-java-shared.gradle.kts     # java-library + jacoco + JUnit 5 test config
│       ├── docling-lombok.gradle.kts          # Lombok setup
│       └── docling-release.gradle.kts         # maven-publish setup
├── gradle/libs.versions.toml         # Version catalog (single source of truth for deps)
├── gradle.properties                 # Gradle settings (java.version=17, parallel, caching)
├── settings.gradle.kts               # Module declarations
├── docling-core/                     # Core DoclingDocument model (no runtime deps)
├── docling-serve/
│   ├── docling-serve-api/            # Framework-agnostic API interfaces + request/response types
│   └── docling-serve-client/         # Reference HTTP client (Java HttpClient + Jackson)
├── docling-testcontainers/           # Testcontainers module for Docling Serve
├── docling-testing/
│   └── docling-version-tests/        # Quarkus/Picocli CLI for version-compatibility testing
├── docs/                             # MkDocs documentation site
├── test-report-aggregation/          # Aggregated JaCoCo + JUnit reports
└── .github/
    ├── project.yml                   # release.current-version (source of truth for version)
    └── workflows/                    # CI: build.yml, release.yml, docs.yml, version-tests.yml
```

### Module ↔ Gradle project name mapping

| Directory path | Gradle project name |
|---|---|
| `docling-core` | `:docling-core` |
| `docling-serve/docling-serve-api` | `:docling-serve-api` |
| `docling-serve/docling-serve-client` | `:docling-serve-client` |
| `docling-testcontainers` | `:docling-testcontainers` |
| `docling-testing/docling-version-tests` | `:docling-version-tests` |

## Build System

- **Gradle** with **Kotlin DSL** (`build.gradle.kts`, `settings.gradle.kts`).
- **Java 17** is the baseline; CI also tests Java 21 and 25.
- Convention plugins in `buildSrc/` keep module `build.gradle.kts` files minimal.
- All dependency versions live in `gradle/libs.versions.toml` (version catalog).
- Project version is read from `.github/project.yml` (`release.current-version`) by `docling-shared.gradle.kts`.

## Common Build Commands

```bash
# Build and test a single module (recommended during development)
./gradlew :docling-serve-api:clean :docling-serve-api:build
./gradlew :docling-serve-client:clean :docling-serve-client:build
./gradlew :docling-testcontainers:clean :docling-testcontainers:build
./gradlew :docling-core:clean :docling-core:build

# Run tests for a specific module
./gradlew :docling-serve-api:test
./gradlew :docling-serve-client:test

# Specify a different Java version
./gradlew -Pjava.version=21 :docling-serve-client:build

# Generate aggregated test report
./gradlew :test-report-aggregation:check

# Build the documentation site
./gradlew :docs:build

# Run the version-compatibility CLI (dev mode, requires Docker)
./gradlew :docling-version-tests:quarkusDev
```

> **Note:** Tests in `docling-serve-client` that use WireMock also start a `DoclingServeContainer` via Testcontainers and therefore require a running Docker daemon. Tests in `docling-testcontainers` that use Testcontainers likewise require Docker, while tests in `docling-serve-api` do not use WireMock and can run without Docker.

## Java Code Conventions

### Lombok

All model/value types use **Lombok** annotations. The standard pattern for immutable value objects is:

```java
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
@lombok.extern.jackson.Jacksonized   // for Jackson deserialization via builder
public class MyType {
  @Nullable
  private String optionalField;
  private String requiredField;
}
```

- Use `@lombok.Singular` on collection fields for builder singular-adder methods.
- A `lombok.config` file exists in module source roots; do not remove it.

### Nullability

Use **JSpecify** annotations for nullability:

```java
import org.jspecify.annotations.Nullable;

@Nullable
private String mayBeNull;   // field/param/return that may be null
// no annotation = non-null by default
```

### Jackson (dual Jackson 2 & 3 support)

The project supports **both Jackson 2** (`com.fasterxml.jackson`) and **Jackson 3** (`tools.jackson`). When annotating models:

- Use `com.fasterxml.jackson.annotation.*` for Jackson 2/3-compatible annotations (`@JsonProperty`, `@JsonInclude`, `@JsonSetter`, etc.).
- Use `@tools.jackson.databind.annotation.JsonDeserialize` for Jackson 3-specific deserializer wiring.
- Jackson is a `compileOnly` / `testImplementation` dependency in most modules — it must not leak as a transitive `api` dependency.

### Module System (JPMS)

Most library modules have a `module-info.java` (some non-library/test modules, such as `docling-version-tests`, do not). When adding new packages to a library module, export them in its `module-info.java`. Jackson and Lombok are `requires static` (optional at runtime).

### Javadoc

All public types and methods require Javadoc. The build runs Javadoc with `-Xdoclint:syntax,html`, but the `Javadoc` task is configured with `isFailOnError = false`, so Javadoc issues do not currently fail the build. Treat Javadoc warnings and errors as if they were fatal when contributing and keep Javadoc accurate and complete.

### Code Style

- `.editorconfig` at the repository root defines formatting rules. Follow it.
- Follow [Conventional Commits](https://www.conventionalcommits.org/) for **all** commit messages and PR titles.
- Commits should be atomic and squashed before merging.

#### Conventional Commits format

```
<type>[optional scope]: <short description>

[optional body]

[optional footer(s)]
```

Common types used in this project:

| Type | When to use |
|---|---|
| `feat` | A new feature or capability |
| `fix` | A bug fix |
| `docs` | Documentation-only changes (including `copilot-instructions.md`) |
| `chore` | Build scripts, CI config, dependency bumps, tooling |
| `refactor` | Code restructuring with no behaviour change |
| `test` | Adding or updating tests |
| `style` | Formatting / code style (no logic change) |

Examples:

```
docs: add copilot-instructions.md for coding agent onboarding
feat(serve-client): add retry support to DoclingServeClient
fix(core): handle null RefItem in DoclingDocument resolution
chore: bump jackson to 2.18.3
```

> **Important:** Every commit pushed to this repository — including automated commits made by coding agents — **must** follow this format. PRs with non-conforming commit messages will be asked to reword or squash before merging.

## Testing Conventions

- **JUnit 5** (`org.junit.jupiter`) is the test framework for all modules.
- **AssertJ** is used for assertions (`assertThat(...)`, `assertThatThrownBy(...)`).
- **WireMock** is used to mock HTTP servers in `docling-serve-client` tests.
- **Testcontainers** is used for integration tests that need a real Docling Serve container (requires Docker).
- Avoid adding new testing frameworks; use the ones already present.

Test class naming convention: `*Tests.java` (e.g., `DoclingServeClientTests.java`).

Run a single test class:

```bash
./gradlew :docling-serve-client:test --tests "ai.docling.serve.client.DoclingServeJackson3ClientTests"
```

## Dependency Management

- **Never hardcode dependency versions in `build.gradle.kts`**; add versions to `gradle/libs.versions.toml` (or use the BOM) and reference dependencies via the `libs.*` version catalog.
- Check for security advisories before adding new libraries.
- Keep Jackson as `compileOnly` where it is already `compileOnly` — it must not become a transitive API dependency.

## Key APIs

### `DoclingServeApi` (docling-serve-api)

The main entry point for consumers. Uses SPI (`ServiceLoader`) to discover the client implementation at runtime:

```java
DoclingServeApi api = DoclingServeApi.builder()   // discovers docling-serve-client on classpath
    .baseUrl("http://localhost:5001")
    .build();

ConvertDocumentResponse response = api.convertSource(request);
```

### `DoclingServeContainer` (docling-testcontainers)

Wraps a Testcontainers `GenericContainer` for `ghcr.io/docling-project/docling-serve`:

```java
DoclingServeContainer container = new DoclingServeContainer(
    DoclingServeContainerConfig.builder()
        .image(DoclingServeContainerConfig.DOCLING_IMAGE)  // default image
        .enableUi(false)
        .startupTimeout(Duration.ofMinutes(2))
        .build()
);
// Default port: 5001 (automatically mapped)
// container.getApiUrl() → "http://localhost:<mapped_port>"
```

Default image constant pattern: `DoclingServeContainerConfig.DOCLING_IMAGE` → `ghcr.io/docling-project/docling-serve:<DOCLING_IMAGE_VERSION>` (where `<DOCLING_IMAGE_VERSION>` is a concrete tag such as `v1.13.0`).

## CI/CD

GitHub Actions workflows in `.github/workflows/`:

| Workflow | Trigger | What it does |
|---|---|---|
| `build.yml` | push/PR to `main` | Builds and tests all modules on Java 17, 21, 25 |
| `release.yml` | manual/tag | Publishes to Maven Central via JReleaser |
| `docs.yml` | push to `main` | Publishes MkDocs site to GitHub Pages |
| `version-tests.yml` | scheduled / manual | Runs `docling-version-tests` CLI against GHCR image tags |
| `dependabot-automerge.yml` | Dependabot PRs | Auto-merges minor/patch dependency updates |

The `build.yml` matrix runs each module independently (`docling-serve-api`, `docling-serve-client`, `docling-testcontainers`, `docling-version-tests`) across Java versions.

## Versioning and Release

- Current version is in `.github/project.yml` under `release.current-version`.
- Releases follow semantic versioning and are managed by [JReleaser](https://jreleaser.org/) (`jreleaser.yml`).
- **Do not bump version manually** in build files; the version is read dynamically from `.github/project.yml`.

## Documentation

- Docs live in `docs/src/doc/docs/` as Markdown files, built with [MkDocs Material](https://squidfunk.github.io/mkdocs-material/).
- When adding a new public API or module, add or update the corresponding `.md` file.
- Template variables like `{{ gradle.project_version }}` are substituted during the Gradle docs build.

## Known Errors and Workarounds

- **`delombok` and `module-info.java`**: The Lombok Gradle plugin cannot process `module-info.java`. The `docling-lombok.gradle.kts` convention plugin temporarily renames `module-info.java` to `module-info.java.bak` during delombok and restores it afterward. Do not remove this workaround.
- **Docker not available in CI for Testcontainers tests**: The `docling-serve-client` and `docling-testcontainers` modules include Testcontainers-based integration tests. These require Docker and may be skipped in environments without a Docker daemon. The `build.yml` CI workflow runs on `ubuntu-latest` which has Docker available.
- **Jackson 2 vs Jackson 3**: The project supports both Jackson 2 and Jackson 3. Some annotations (`@JsonProperty`, `@JsonInclude`) work with both; others are version-specific. Use `com.fasterxml.jackson.annotation` for shared annotations and `tools.jackson.*` for Jackson 3-specific ones.
