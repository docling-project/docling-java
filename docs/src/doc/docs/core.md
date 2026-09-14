# Docling Core

[![docling-core version](https://img.shields.io/badge/docling--core_v{{ gradle.project_version }}-orange)](https://docling-project.github.io/docling-java/{{ gradle.project_version }}/core)

The `docling-core` module provides the core data types used by Docling for document representation. It defines the `DoclingDocument` model, which captures the structure and content of documents across various formats, along with utilities for working with these types.

The base Java version is 17. This module has no external dependencies, making it lightweight and easy to integrate into your projects. It represents the foundational building block for the other Docling Java modules.

## When to use this module

You typically won't need to use this module directly, as it is consumed by higher-level modules like [`docling-serve-api`](docling-serve/serve-api.md) and [`docling-serve-client`](docling-serve/serve-client.md).

## Installation

Add the dependency to your project.

=== "Gradle"

    ``` kotlin
    dependencies {
      implementation("{{ gradle.project_groupId }}:{{ gradle.core_artifactId }}:{{ gradle.project_version }}")
    }
    ```

=== "Maven"

    ``` xml
    <dependency>
      <groupId>{{ gradle.project_groupId }}</groupId>
      <artifactId>{{ gradle.core_artifactId }}</artifactId>
      <version>{{ gradle.project_version }}</version>
    </dependency>
    ```

## GraalVM Native Image

The JAR includes reflection metadata at
`META-INF/native-image/ai.docling/docling-core/reflect-config.json` for Jackson 2
and Jackson 3 serialization and deserialization. It covers `DoclingDocument`, its
nested types, enums, and Lombok builders. GraalVM discovers this metadata
automatically, including in Spring Boot native applications, so application-level
reflection hints for these models are unnecessary.

## Core concepts

The `DoclingDocument` class is the primary representation of a document in Docling. It can be retrieved as the result of a document conversion process against a Docling Serve backend via the [`docling-serve-api`](docling-serve/serve-api.md) or [`docling-serve-client`](docling-serve/serve-client.md) modules.

To learn more about the `DoclingDocument` model and related types, refer to the Docling documentation: [Docling Document](https://docling-project.github.io/docling/concepts/docling_document/).
