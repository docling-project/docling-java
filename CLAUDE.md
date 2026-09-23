# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Full build
./gradlew --no-daemon build

# Run tests for a specific subproject
./gradlew --no-daemon :docling-serve-client:test

# Run a specific test class
./gradlew --no-daemon :docling-serve-client:test --tests "ai.docling.serve.client.SomeTestClass"

# Lint check (enforces import order + whitespace rules)
./gradlew --no-daemon spotlessCheck

# Auto-apply formatting fixes
./gradlew --no-daemon spotlessApply
```

## Project Architecture

This is a multi-module Gradle project (Kotlin DSL) with group `ai.docling`. Version is managed in `.github/project.yml` under `release.current-version`.

### Modules

- **`docling-core`**: Java types mirroring the [docling-core](https://github.com/docling-project/docling-core) Python library's document representation model. Uses Lombok and JSpecify. Jackson is a `compileOnly` dependency — consumers must bring their own.
- **`docling-serve-api`** (`docling-serve/docling-serve-api`): Framework-agnostic API interfaces for interacting with a [Docling Serve](https://github.com/docling-project/docling-serve) backend. Defines `DoclingServeApi` (extends `DoclingServeHealthApi`, `DoclingServeConvertApi`, `DoclingServeChunkApi`, `DoclingServeClearApi`, `DoclingServeTaskApi`) and the SPI interface `DoclingServeApiProvider` used for discovery via `java.util.ServiceLoader` (the older `DoclingServeApiBuilderFactory` is deprecated and only used as a fallback).
- **`docling-serve-client`** (`docling-serve/docling-serve-client`): Reference implementation using Java's `HttpClient`. Provides `DoclingServeJackson2Client` and `DoclingServeJackson3Client` — concrete implementations for Jackson 2.x and 3.x respectively. `DoclingServeClient` (abstract) contains all HTTP logic.
- **`docling-testcontainers`**: Testcontainers module exposing `DoclingServeContainer` for spinning up a Docling Serve Docker container in tests.
- **`docling-testing/docling-version-tests`**: Internal tooling for running compatibility tests across Docling Serve container versions.

### Key Design Patterns

**SPI for client discovery**: `DoclingServeApi.builder()` returns a `DoclingServeApiBuilder` that collects an immutable `DoclingServeApiConfig`; `build()` uses `ServiceLoader` to discover a `DoclingServeApiProvider` and calls `create(config)`. Exactly one provider must be on the classpath — zero or more than one throws `IllegalStateException`. If no provider is found, deprecated `DoclingServeApiBuilderFactory` implementations are adapted as a fallback. Providers declare options they cannot honor via `unsupportedOptions()`; new options are added to `DoclingServeApiConfig` (a constant in `ALL_OPTIONS`, an accessor and a builder setter — guarded by `DoclingServeApiConfigTests`), never to the deprecated `DoclingApiBuilder`. Every `DoclingServeApi` implements the abstract `config()`, which must report the effective value of every option; `DoclingServeApi.toBuilder()` is deprecated in favor of `config().toBuilder()`. The `docling-serve-client` module registers `DoclingServeClientProvider`.

**Dual Jackson support**: All Jackson dependencies are `compileOnly` in production code. Jackson 2.x and 3.x are both supported through parallel implementations. Consumers must include one Jackson version on their classpath.

**Java modules**: All publishable modules include `module-info.java`. The `docling-lombok.gradle.kts` convention plugin temporarily renames `module-info.java` during the Lombok `delombok` task to avoid module processing issues.

### Convention Plugins (in `buildSrc/`)

- `docling-shared`: Sets group/version from `.github/project.yml`
- `docling-java-shared`: Applies `docling-shared`, `java-library`, `jacoco`, `spotless`; configures JUnit Jupiter, AssertJ, parallel test execution, and test logging
- `docling-lombok`: Applies `io.freefair.lombok` with `module-info.java` workaround
- `docling-release`: Configures `maven-publish` for publishing to Maven Central

### Technology Stack

- Java 17 (minimum), tested against 17, 21, 25 (all LTS)
- Lombok for boilerplate reduction
- JSpecify for nullability annotations (`@Nullable`)
- JUnit Jupiter + AssertJ for testing
- WireMock for HTTP mocking in tests
- SLF4J for logging
- Spotless enforces import order (see `.spotless/import-order.txt`)

## Conventions

- Commits must follow [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/)
- All commits must include DCO sign-off (`git commit -s`)
- `spotless` uses `ratchetFrom("origin/main")` — only files changed relative to `origin/main` are checked
- Line endings: LF; no trailing whitespace; files must end with a newline (`.editorconfig`)
