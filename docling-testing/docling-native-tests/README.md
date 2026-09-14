# Native Image smoke tests

The serialization smoke application checks the reflection metadata packaged in `docling-core`
and `docling-serve-api`. It exercises convert request serialization, polymorphic
source and target deserialization, task polling, conversion results containing
nested `DoclingDocument` items, chunk requests/results, durations, artifact timestamps, and
validation errors. It uses no framework, application reflection hints, or Docling
Serve instance.

The separate client smoke application calls `DoclingServeApi.builder()` to discover
`docling-serve-client` through `ServiceLoader` and select the available Jackson version.
It performs health and conversion requests against WireMock, including request
serialization and nested document deserialization. WireMock runs in the JVM test
harness, which starts the client smoke application as a separate process and verifies
the HTTP methods, paths, and JSON request body. Its entry point references only the
API and model types, so direct mapper use in the serialization test cannot hide a
failure in service discovery or Jackson detection.

WireMock is a test-only dependency and is excluded from both native executables.
JVM smoke applications also run in separate processes with the main runtime
classpath, keeping WireMock's Jackson 2 dependency out of the applications under test.
The client is a runtime-only dependency of this test module; published libraries do
not depend on the test module.

The external reachability metadata repository is disabled so these tests verify
the metadata shipped in the library JARs.

Each smoke application includes only Jackson 2 or Jackson 3 on its runtime classpath. Set
`JAVA_HOME` (or `GRAALVM_HOME`) to a GraalVM 25 installation with `native-image`, then run both:

```bash
./gradlew --no-daemon -PnativeTestJacksonVersion=2 \
  :docling-native-tests:test :docling-native-tests:nativeRun :docling-native-tests:nativeClientTest
./gradlew --no-daemon -PnativeTestJacksonVersion=3 \
  :docling-native-tests:test :docling-native-tests:nativeRun :docling-native-tests:nativeClientTest
```

`nativeClientTest` builds the client native executable, starts WireMock on a dynamic
loopback port, runs the executable against it, and stops the server afterward. The
serialization executable continues to run directly through `nativeRun`.

The JVM smoke tests also run as part of `build` (Jackson 3 by default). Native
compilation is opt-in locally. In CI, the `native-tests` job in
`.github/workflows/build.yml` runs both versions only for pull requests, after all
`jvm-build-test` matrix jobs succeed.

Native builds enable exact reachability metadata checking for `ai.docling` and
use `-XX:MissingRegistrationReportingMode=Exit` so missing registrations fail the
process even if library code catches the error. The
[official Gradle guide](https://graalvm.github.io/native-build-tools/latest/end-to-end-gradle-guide.html)
describes these diagnostics and the optional tracing-agent workflow for collecting
metadata during development.

The library builds generate `META-INF/native-image/ai.docling/<artifact>/reflect-config.json`
using `generateNativeImageMetadata`. The plugin reads exported package names from
the compiled `module-info.class` with the public JDK `ModuleDescriptor` API, so
there is no separate package list to maintain. Nested classes and Lombok-generated
builders in those packages are included automatically. Applying `docling-native-image`
is sufficient for a model-only module such as `docling-core`.

Modules that also export non-model APIs can exclude those packages:

```kotlin
nativeImageMetadata {
  excludePackages(
    "ai.docling.serve.api",
    "ai.docling.serve.api.spi",
    "ai.docling.serve.api.util"
  )
}
```

Exports and exclusions match exact package names, following JPMS semantics;
excluding a parent package does not exclude its exported subpackages. Newly exported
model packages are picked up automatically. The plugin requires a compiled module
descriptor and does not infer model packages from `open module` or `opens` declarations.
Jackson-specific serializers and deserializers are conditional on their version's
`ObjectMapper` being reachable.
