package ai.docling.serve.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.lang.reflect.Method;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ai.docling.serve.api.DoclingServeApi;
import ai.docling.serve.api.DoclingServeApiBuilder;
import ai.docling.serve.client.DoclingServeClient.DoclingServeClientBuilder;

/**
 * Tests for the Jackson detection of {@link DoclingServeClient#builder()}.
 */
class DoclingServeClientBuilderTests {
  private static final String JACKSON_2 = "com.fasterxml.jackson.databind.json.JsonMapper";
  private static final String JACKSON_3 = "tools.jackson.databind.json.JsonMapper";

  @Test
  void jackson3IsPreferredWhenBothArePresent() {
    assertThat(DoclingServeClient.builder()).isExactlyInstanceOf(DoclingServeJackson3Client.Builder.class);
  }

  @ParameterizedTest
  @MethodSource("detectedBuilders")
  void builderMatchesTheJacksonOnTheClasspath(String hiddenClass, Class<?> expectedBuilder) {
    assertThat(DoclingServeClient.builderFor(new ClassHidingClassLoader(hiddenClass))).isExactlyInstanceOf(expectedBuilder);
  }

  @Test
  void failsWhenNeitherJacksonIsPresent() {
    var classLoader = new ClassHidingClassLoader(JACKSON_2, JACKSON_3);

    assertThatIllegalStateException()
        .isThrownBy(() -> DoclingServeClient.builderFor(classLoader))
        .withMessageContaining("Neither Jackson 2 nor Jackson 3 is on the classpath")
        .withMessageContaining("com.fasterxml.jackson.core:jackson-databind")
        .withMessageContaining("tools.jackson.core:jackson-databind");
  }

  @Test
  void clientSpecificSettingsChainWithoutKnowingTheJacksonVersion() {
    // Compiling this chain on the wildcard type returned by builder() is part of what is tested
    DoclingServeApi api = DoclingServeClient.builder()
        .baseUrl("http://localhost:5001")
        .httpClientBuilder(HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(20)))
        .logRequests()
        .build();

    assertThat(api).isExactlyInstanceOf(DoclingServeJackson3Client.class);
  }

  @Test
  void everySharedOptionCanBeSetOnTheClientBuilder() {
    var clientBuilderMethods = Arrays.stream(DoclingServeClientBuilder.class.getMethods())
        .map(DoclingServeClientBuilderTests::signature)
        .collect(Collectors.toSet());

    var sharedSetters = Arrays.stream(DoclingServeApiBuilder.class.getMethods())
        .filter(method -> method.getDeclaringClass() == DoclingServeApiBuilder.class)
        .filter(method -> !Set.of("config", "build").contains(method.getName()))
        .map(DoclingServeClientBuilderTests::signature)
        .toList();

    assertThat(sharedSetters).isNotEmpty();
    assertThat(clientBuilderMethods).containsAll(sharedSetters);
  }

  static Stream<Arguments> detectedBuilders() {
    return Stream.of(
        Arguments.of(JACKSON_3, DoclingServeJackson2Client.Builder.class), Arguments.of(JACKSON_2, DoclingServeJackson3Client.Builder.class));
  }

  private static String signature(Method method) {
    return "%s(%s)".formatted(
        method.getName(), Arrays.stream(method.getParameterTypes())
            .map(Class::getName)
            .collect(Collectors.joining(", ")));
  }
}
