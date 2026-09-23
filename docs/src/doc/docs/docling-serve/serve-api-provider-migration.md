# Migrating to `DoclingServeApiProvider`

Docling Java {{ gradle.project_version }} replaces the `DoclingServeApiBuilderFactory` service provider interface (SPI) with `DoclingServeApiProvider`. This page explains how to move an implementation to the new SPI, and how to update code that builds a `DoclingServeApi`.

This page is for you if you:

- implement `DoclingServeApiBuilderFactory`, `DoclingServeApi` or `DoclingServeApi.DoclingApiBuilder`, or
- call `DoclingServeApi.builder()` and declare the builder with its type, use client-specific methods such as `httpClientBuilder(...)`, or copy an API with `toBuilder()`.

If you only chain calls such as `DoclingServeApi.builder().baseUrl(...).build()`, nothing changes for you.

## Why the SPI changed

`DoclingServeApiBuilderFactory` returned a `DoclingApiBuilder`, an interface that every implementation had to implement. Each new configuration option meant a new method on that interface, which either broke every implementation or needed a `default` method throwing `UnsupportedOperationException`.

A `DoclingServeApiProvider` instead receives an immutable `DoclingServeApiConfig`, a final class owned by `docling-serve-api`. New options are added to that class, so they never break an existing provider.

## Compatibility

`DoclingServeApiBuilderFactory`, `DoclingServeApi.DoclingApiBuilder` and `DoclingServeApi.toBuilder()` are deprecated for removal in a future release, as is `DoclingServeClientBuilderFactory` in `docling-serve-client`. Until then:

- A `DoclingServeApiBuilderFactory` is still used when no `DoclingServeApiProvider` is available. Options added after its deprecation, such as `asyncExecutor`, make `build()` throw an `UnsupportedConfigurationException` through it, because the old builder has no way to receive them.
- When both are available, the `DoclingServeApiProvider` is used, and a warning naming the ignored factory is logged every time an API is built.

`DoclingServeApi` also has a new abstract `config()` method, which every implementation must add: see [Update your `DoclingServeApi` implementation](#update-your-doclingserveapi-implementation).

## Replace the factory with a provider

A factory returned a builder, and `docling-serve-api` applied the caller's settings to it. A provider receives the settings and builds the API itself.

Before:

```java
public final class MyFactory implements DoclingServeApiBuilderFactory {
  @Override
  @SuppressWarnings("unchecked")
  public <T extends DoclingServeApi, B extends DoclingApiBuilder<T, B>> B getBuilder() {
    return (B) MyDoclingServeApi.builder();
  }
}
```

After, mapping every option of `DoclingServeApiConfig` onto your own builder:

```java
public final class MyProvider implements DoclingServeApiProvider {
  @Override
  public DoclingServeApi create(DoclingServeApiConfig config) {
    var builder = MyDoclingServeApi.builder()
        .baseUrl(config.baseUrl())
        .apiKey(config.apiKey())
        .logRequests(config.logRequests())
        .logResponses(config.logResponses())
        .prettyPrint(config.prettyPrint())
        .connectTimeout(config.connectTimeout())
        .readTimeout(config.readTimeout())
        .asyncPollInterval(config.asyncPollInterval())
        .asyncTimeout(config.asyncTimeout());

    // asyncExecutor() is null when not set: only pass it on when it is set
    Optional.ofNullable(config.asyncExecutor())
        .ifPresent(builder::asyncExecutor);

    return builder.build();
  }
}
```

Every accessor returns the effective value: the caller's setting, or the default when the caller didn't set it. The only exceptions are `apiKey()` and `asyncExecutor()`, which return `null` when not set.

!!! warning
    When `asyncExecutor()` returns `null`, keep your implementation's default behavior. Don't replace it with `ForkJoinPool.commonPool()`: `CompletableFuture` doesn't always use the common pool by default, and `CompletableFuture.delayedExecutor(...)` doesn't guard against a common pool without worker threads, for example on a single-CPU container.

A provider must be `public` and have a `public` no-argument constructor.

If your implementation can't support an option, declare it instead of dropping it silently: see [Declare the options you can't honor](#declare-the-options-you-cant-honor).

## Register the provider

Rename the service file, and point it to the provider:

```text
# Before: META-INF/services/ai.docling.serve.api.spi.DoclingServeApiBuilderFactory
com.example.MyFactory

# After: META-INF/services/ai.docling.serve.api.spi.DoclingServeApiProvider
com.example.MyProvider
```

If you use Java modules, change the `provides` clause in `module-info.java`:

```java
// Before
provides ai.docling.serve.api.spi.DoclingServeApiBuilderFactory with com.example.MyFactory;

// After
provides ai.docling.serve.api.spi.DoclingServeApiProvider with com.example.MyProvider;
```

Remove the old registration: if both are present, the factory is ignored and a warning is logged every time an API is built.

## Declare the options you can't honor

Some options may not make sense for your implementation. Declare them in `unsupportedOptions()`, together with what should happen when a caller explicitly sets them:

| Value | When a caller explicitly sets the option | Use it when |
|-------|------------------------------------------|-------------|
| `IGNORE` | Nothing happens. | The option has no meaning for your implementation, and ignoring it can't surprise anyone. |
| `WARN` | A warning naming the option and your provider is logged. | The caller may expect an effect that your implementation achieves differently. |
| `FAIL` | `build()` throws an `UnsupportedConfigurationException`. | Ignoring the option would change the behavior the caller asked for. |

For example, a reactive implementation that manages its own threads and doesn't poll for task status:

```java
@Override
public Map<ConfigOption<?>, Unsupported> unsupportedOptions() {
  return Map.of(
      DoclingServeApiConfig.ASYNC_EXECUTOR, Unsupported.WARN,
      DoclingServeApiConfig.ASYNC_POLL_INTERVAL, Unsupported.IGNORE);
}
```

A few rules apply:

- Options left at their default value never trigger anything, so declaring an option doesn't bother callers who don't use it.
- Options absent from the map are assumed to be honored. Nothing checks it, so only leave out the options you actually apply.
- Never declare the base URL, the API key or the timeouts as `IGNORE` or `WARN`: silently dropping them causes failures far from their cause. If your implementation can't honor one of them, declare it `FAIL`.
- The declaration is only enforced by `DoclingServeApi.builder()`. Code that uses your implementation's own builder bypasses it.

## Update your `DoclingServeApi` implementation

### Add `config()`

`DoclingServeApi.config()` returns the configuration an API runs with. Callers use it to create a modified copy with `api.config().toBuilder()`, so it must report the effective value of every option: an API built from it should behave like the original.

If your implementation keeps the configuration it was created from, return it:

```java
public final class MyDoclingServeApi implements DoclingServeApi {
  private final DoclingServeApiConfig config;

  private MyDoclingServeApi(DoclingServeApiConfig config) {
    this.config = config;
  }

  @Override
  public DoclingServeApiConfig config() {
    return this.config;
  }

  // The other API methods are unchanged
}
```

If it's built from its own builder, the simplest option is to keep a `DoclingServeApiBuilder` in that builder for the shared options, as the reference client does, and store the configuration it produces. Otherwise, rebuild the configuration from its settings. `config()` on `DoclingServeApiBuilder` only creates the configuration: it doesn't look up a provider.

```java
@Override
public DoclingServeApiConfig config() {
  var builder = DoclingServeApi.builder()
      .baseUrl(this.baseUrl)
      .apiKey(this.apiKey)
      .logRequests(this.logRequests)
      .logResponses(this.logResponses)
      .prettyPrint(this.prettyPrint)
      .connectTimeout(this.connectTimeout)
      .readTimeout(this.readTimeout)
      .asyncPollInterval(this.asyncPollInterval)
      .asyncTimeout(this.asyncTimeout);

  // Only set when configured: an unset executor must stay unset
  Optional.ofNullable(this.asyncExecutor)
      .ifPresent(builder::asyncExecutor);

  return builder.config();
}
```

Implementations compiled against an earlier version don't have this method, and calling `config()` on them throws an `AbstractMethodError`. Recompile them against this version.

### Keep `toBuilder()` for now

`DoclingServeApi.toBuilder()` is deprecated for removal, and replaced by `config().toBuilder()`. It is still abstract, so keep your existing implementation until it is removed, and add `@SuppressWarnings("removal")` to it to silence the deprecation warning.

### Detach your builder from `DoclingApiBuilder`

If your builder implements `DoclingServeApi.DoclingApiBuilder`, it keeps compiling, but the interface is deprecated for removal and won't gain new options. `asyncExecutor`, for example, isn't part of it.

Before the interface is removed, drop the `implements DoclingApiBuilder<...>` clause and the `@Override` annotations of its methods. The methods themselves can stay. Add new options such as `asyncExecutor(Executor)` to your builder directly if your implementation supports them.

## Require this version of Docling Java

Shipping both a `DoclingServeApiBuilderFactory` and a `DoclingServeApiProvider` to support several versions of Docling Java isn't recommended: with this version, the factory is ignored and a warning is logged every time an API is built. Require Docling Java {{ gradle.project_version }} or later, and ship only the provider.

## Update code that builds a `DoclingServeApi`

### The builder type

`DoclingServeApi.builder()` used to return the builder of the implementation, through a generic `<T, B> B` return type. It now returns a `DoclingServeApiBuilder`, whatever the implementation.

Before:

```java
DoclingServeJackson3Client.Builder builder = DoclingServeApi.builder();
```

After:

```java
DoclingServeApiBuilder builder = DoclingServeApi.builder();
```

### Client-specific settings

`DoclingServeApiBuilder` only has the options shared by every implementation. For the settings of the reference client, such as the `HttpClient`, start from `DoclingServeClient.builder()` instead. It picks Jackson 3 or Jackson 2 the same way. To customize the JSON mapper, whose type depends on the version of Jackson, use `DoclingServeJackson3Client.builder()` or `DoclingServeJackson2Client.builder()` and their `jsonParser(...)` method.

Before:

```java
DoclingServeApi api = DoclingServeApi.builder()
    .baseUrl("https://serve.example.com")
    .httpClientBuilder(HttpClient.newBuilder().proxy(proxySelector))
    .build();
```

After:

```java
DoclingServeApi api = DoclingServeClient.builder()
    .baseUrl("https://serve.example.com")
    .httpClientBuilder(HttpClient.newBuilder().proxy(proxySelector))
    .build();
```

`DoclingServeClientBuilderFactory.newBuilder()` is deprecated for removal. Replace it with `DoclingServeClient.builder()`, which returns the same builder. If you assign the result to the builder type of a concrete client, use the `builder()` of that client instead, e.g. `DoclingServeJackson3Client.builder()`.

### Copying an API

Before:

```java
DoclingServeApi copy = api.toBuilder()
    .logRequests()
    .build();
```

After:

```java
DoclingServeApi copy = api.config()
    .toBuilder()
    .logRequests()
    .build();
```

The copy is built through the provider, from the options of `DoclingServeApiConfig` only. To also keep the client-specific settings of the reference client, call `toBuilder()` on the concrete client type, which isn't deprecated:

```java
DoclingServeJackson3Client copy = client.toBuilder()
    .logRequests()
    .build();
```

## Check the migration

These tests check that your provider is discovered, and that `config()` reports the settings the API was built with:

```java
@Test
void providerIsDiscovered() {
  var api = DoclingServeApi.builder()
      .baseUrl("http://localhost:5001")
      .build();

  assertThat(api).isInstanceOf(MyDoclingServeApi.class);
}

@Test
void configReportsTheEffectiveSettings() {
  var api = DoclingServeApi.builder()
      .baseUrl("http://localhost:5001")
      .apiKey("key")
      .readTimeout(Duration.ofSeconds(10))
      .build();

  assertThat(api.config().toBuilder().build().config()).isEqualTo(api.config());
}
```

Also check your logs for a warning starting with `Ignoring deprecated ai.docling.serve.api.spi.DoclingServeApiBuilderFactory implementation(s)`: it means an old registration is still on the classpath.
