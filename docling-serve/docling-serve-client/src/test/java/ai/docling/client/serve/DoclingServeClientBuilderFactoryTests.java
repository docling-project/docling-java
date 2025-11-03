package ai.docling.client.serve;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ai.docling.client.serve.DoclingServeClient.DoclingServeClientBuilder;

class DoclingServeClientBuilderFactoryTests {
  @Test
  void correctBuilderWhenBothArePresent() {
    assertThat(DoclingServeClientBuilderFactory.<DoclingServeClient, DoclingServeClientBuilder>newBuilder())
        .isNotNull()
        .isExactlyInstanceOf(DoclingServeJackson3Client.Builder.class);
  }

  @Test
  void noBuilderWhenNeitherArePresent() {
    var classLoader = new ClassHidingClassLoader("com.fasterxml.jackson.databind.json.JsonMapper", "tools.jackson.databind.json.JsonMapper");

    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> DoclingServeClientBuilderFactory.<DoclingServeClient, DoclingServeClientBuilder>newBuilder(classLoader))
        .withMessage("Neither Jackson 2 or 3 is on the classpath");
  }

  @ParameterizedTest
  @MethodSource("correctBuilderArguments")
  void correctBuilder(String classToHide, Class<?> expectedBuilderClass) {
    var classLoader = new ClassHidingClassLoader(classToHide);

    assertThat(DoclingServeClientBuilderFactory.<DoclingServeClient, DoclingServeClientBuilder>newBuilder(classLoader))
        .isNotNull()
        .isExactlyInstanceOf(expectedBuilderClass);
  }

  static Stream<Arguments> correctBuilderArguments() {
    return Stream.of(
        Arguments.of("com.fasterxml.jackson.databind.json.JsonMapper", DoclingServeJackson3Client.Builder.class),
        Arguments.of("tools.jackson.databind.json.JsonMapper", DoclingServeJackson2Client.Builder.class)
    );
  }

  private static class ClassHidingClassLoader extends ClassLoader {
    private final Set<String> classedToHide = new HashSet<>();

    public ClassHidingClassLoader(String... classesToHide) {
      this(Thread.currentThread().getContextClassLoader(), classesToHide);
    }

    public ClassHidingClassLoader(ClassLoader parent, String... classesToHide) {
      super(parent);

      if (classesToHide != null) {
        this.classedToHide.addAll(Set.of(classesToHide));
      }
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
      if (this.classedToHide.contains(name)) {
        throw new ClassNotFoundException("Class %s not found".formatted(name));
      }

      return super.loadClass(name);
    }
  }
}
