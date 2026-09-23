package ai.docling.serve.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class DoclingServeApiConfigTests {
  private static final Executor EXECUTOR = Runnable::run;

  // Public no-arg methods of DoclingServeApiConfig that are not option accessors
  private static final Set<String> NON_ACCESSOR_METHODS = Set.of("toBuilder", "explicitlySetOptions", "hashCode", "toString");

  // Public methods of DoclingServeApiBuilder that are not option setters
  private static final Set<String> NON_SETTER_METHODS = Set.of("config", "build");

  @Test
  void defaultsWhenNothingIsSet() {
    var config = DoclingServeApi.builder().config();

    assertThat(config.explicitlySetOptions()).isEmpty();
    assertThat(config)
        .returns(URI.create("http://localhost:5001"), DoclingServeApiConfig::baseUrl)
        .returns(null, DoclingServeApiConfig::apiKey)
        .returns(false, DoclingServeApiConfig::logRequests)
        .returns(false, DoclingServeApiConfig::logResponses)
        .returns(false, DoclingServeApiConfig::prettyPrint)
        .returns(Duration.ofSeconds(5), DoclingServeApiConfig::connectTimeout)
        .returns(Duration.ofSeconds(30), DoclingServeApiConfig::readTimeout)
        .returns(Duration.ofSeconds(2), DoclingServeApiConfig::asyncPollInterval)
        .returns(Duration.ofMinutes(5), DoclingServeApiConfig::asyncTimeout)
        .returns(null, DoclingServeApiConfig::asyncExecutor);
  }

  @Test
  void setOptionsAreReturnedAndRecordedAsExplicitlySet() {
    var config = fullyConfigured().config();

    assertThat(config)
        .returns(URI.create("http://example.com:8080"), DoclingServeApiConfig::baseUrl)
        .returns("key", DoclingServeApiConfig::apiKey)
        .returns(true, DoclingServeApiConfig::logRequests)
        .returns(true, DoclingServeApiConfig::logResponses)
        .returns(true, DoclingServeApiConfig::prettyPrint)
        .returns(Duration.ofSeconds(1), DoclingServeApiConfig::connectTimeout)
        .returns(Duration.ofSeconds(2), DoclingServeApiConfig::readTimeout)
        .returns(Duration.ofSeconds(3), DoclingServeApiConfig::asyncPollInterval)
        .returns(Duration.ofSeconds(4), DoclingServeApiConfig::asyncTimeout)
        .returns(EXECUTOR, DoclingServeApiConfig::asyncExecutor);

    assertThat(config.explicitlySetOptions()).containsExactlyInAnyOrderElementsOf(DoclingServeApiConfig.ALL_OPTIONS);
  }

  @Test
  void settingAnOptionToItsDefaultValueStillRecordsItAsExplicitlySet() {
    var config = DoclingServeApi.builder()
        .logRequests(false)
        .config();

    assertThat(config.isExplicitlySet(DoclingServeApiConfig.LOG_REQUESTS)).isTrue();
    assertThat(config.isExplicitlySet(DoclingServeApiConfig.LOG_RESPONSES)).isFalse();
  }

  @Test
  void nullApiKeyUnsetsTheOption() {
    var config = DoclingServeApi.builder()
        .apiKey("key")
        .apiKey(null)
        .config();

    assertThat(config.apiKey()).isNull();
    assertThat(config.isExplicitlySet(DoclingServeApiConfig.API_KEY)).isFalse();
  }

  @Test
  void configIsASnapshotOfTheBuilder() {
    var builder = DoclingServeApi.builder().apiKey("key");
    var config = builder.config();

    builder.apiKey("other");

    assertThat(config.apiKey()).isEqualTo("key");
  }

  @Test
  void toBuilderRoundTrips() {
    var config = fullyConfigured().config();

    assertThat(config.toBuilder().config())
        .isEqualTo(config)
        .hasSameHashCodeAs(config);
  }

  @Test
  void toBuilderKeepsDefaultedOptionsUnset() {
    var config = DoclingServeApi.builder()
        .apiKey("key")
        .config()
        .toBuilder()
        .config();

    assertThat(config.explicitlySetOptions()).containsExactly(DoclingServeApiConfig.API_KEY);
  }

  @Test
  void invalidValuesAreRejected() {
    var builder = DoclingServeApi.builder();

    assertThatIllegalArgumentException().isThrownBy(() -> builder.baseUrl((URI) null)).withMessageContaining("baseUrl");
    assertThatIllegalArgumentException().isThrownBy(() -> builder.baseUrl(" ")).withMessageContaining("baseUrl");
    assertThatIllegalArgumentException().isThrownBy(() -> builder.connectTimeout(Duration.ZERO)).withMessageContaining("connectTimeout");
    assertThatIllegalArgumentException().isThrownBy(() -> builder.readTimeout(Duration.ofSeconds(-1))).withMessageContaining("readTimeout");
    assertThatIllegalArgumentException().isThrownBy(() -> builder.asyncPollInterval(null)).withMessageContaining("asyncPollInterval");
    assertThatIllegalArgumentException().isThrownBy(() -> builder.asyncTimeout(Duration.ZERO)).withMessageContaining("asyncTimeout");
    assertThatIllegalArgumentException().isThrownBy(() -> builder.asyncExecutor(null)).withMessageContaining("asyncExecutor");

    assertThat(builder.config().explicitlySetOptions()).isEmpty();
  }

  // The guards below keep the ConfigOption constants, ALL_OPTIONS, the accessors and the setters in sync

  @Test
  void allOptionsListsEveryConfigOptionConstant() {
    var constants = Arrays.stream(DoclingServeApiConfig.class.getDeclaredFields())
        .filter(field -> Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers()))
        .filter(field -> field.getType() == ConfigOption.class)
        .map(DoclingServeApiConfigTests::read)
        .toList();

    assertThat(DoclingServeApiConfig.ALL_OPTIONS)
        .doesNotHaveDuplicates()
        .containsExactlyElementsOf(constants);
  }

  @Test
  void everyOptionHasAMatchingAccessorAndViceVersa() {
    var accessors = Arrays.stream(DoclingServeApiConfig.class.getDeclaredMethods())
        .filter(method -> Modifier.isPublic(method.getModifiers()) && !Modifier.isStatic(method.getModifiers()))
        .filter(method -> method.getParameterCount() == 0)
        .filter(method -> !NON_ACCESSOR_METHODS.contains(method.getName()))
        .collect(Collectors.toMap(Method::getName, method -> wrap(method.getReturnType())));

    assertThat(accessors).isEqualTo(optionTypesByName());
  }

  @Test
  void everyOptionHasAMatchingSetterAndViceVersa() {
    var setterNames = Arrays.stream(DoclingServeApiBuilder.class.getDeclaredMethods())
        .filter(method -> Modifier.isPublic(method.getModifiers()) && !Modifier.isStatic(method.getModifiers()))
        .map(Method::getName)
        .filter(name -> !NON_SETTER_METHODS.contains(name))
        .collect(Collectors.toSet());

    var optionTypesByName = optionTypesByName();

    assertThat(setterNames).isEqualTo(optionTypesByName.keySet());
    assertThat(optionTypesByName)
        .allSatisfy((name, type) -> assertThat(DoclingServeApiBuilder.class.getMethods())
            .as("setter %s(%s)", name, type.getSimpleName())
            .anySatisfy(method -> assertThat(method)
                .returns(name, Method::getName)
                .returns(1, Method::getParameterCount)
                .satisfies(m -> assertThat(wrap(m.getParameterTypes()[0])).isEqualTo(type))));
  }

  private static DoclingServeApiBuilder fullyConfigured() {
    return DoclingServeApi.builder()
        .baseUrl("http://example.com:8080")
        .apiKey("key")
        .logRequests()
        .logResponses()
        .prettyPrint()
        .connectTimeout(Duration.ofSeconds(1))
        .readTimeout(Duration.ofSeconds(2))
        .asyncPollInterval(Duration.ofSeconds(3))
        .asyncTimeout(Duration.ofSeconds(4))
        .asyncExecutor(EXECUTOR);
  }

  private static Map<String, Class<?>> optionTypesByName() {
    return DoclingServeApiConfig.ALL_OPTIONS
        .stream()
        .collect(Collectors.toMap(ConfigOption::name, option -> option.type()));
  }

  private static Class<?> wrap(Class<?> type) {
    return MethodType.methodType(type).wrap().returnType();
  }

  private static ConfigOption<?> read(Field field) {
    try {
      return (ConfigOption<?>) field.get(null);
    }
    catch (IllegalAccessException e) {
      throw new IllegalStateException(e);
    }
  }
}
