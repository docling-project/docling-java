package ai.docling.serve.client;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import com.github.tomakehurst.wiremock.WireMockServer;

import ai.docling.serve.api.DoclingServeApi;
import ai.docling.serve.api.validation.ValidationError;

/**
 * Integration tests for {@link DoclingServeClient}.
 */
class DoclingServeJackson3ClientTests extends AbstractDoclingServeClientTests {
  private static DoclingServeApi doclingClient;
  private static DoclingServeApi authDoclingClient;
  private static DoclingServeApi wiremockDoclingClient;
  private static WireMockServer wireMockServer = new WireMockServer(options().dynamicPort());

  @BeforeAll
  static void setUp() {
    wireMockServer.start();
    doclingClient = DoclingServeJackson3Client.builder()
        .logRequests()
        .logResponses()
        .prettyPrint()
        .baseUrl(doclingContainer.getApiUrl())
        .build();

    authDoclingClient = doclingClient.config().toBuilder().apiKey("key").build();
    wiremockDoclingClient = doclingClient.config().toBuilder().baseUrl(wireMockServer.baseUrl()).build();
  }

  @AfterAll
  static void afterAll() {
    wireMockServer.stop();
  }

  @Override
  protected WireMockServer getWiremockServer() {
    return wireMockServer;
  }

  @Override
  protected DoclingServeApi getDoclingClient(boolean requiresAuth, boolean useWiremock) {
    if (requiresAuth) {
      return authDoclingClient;
    }

    return useWiremock ? wiremockDoclingClient : doclingClient;
  }

  @Override
  protected DoclingServeApi getDoclingClientWithFailingDeserializer() {
    var failingDeserializers = new SimpleModule().addDeserializer(Object.class, new FailingDeserializer<>());

    return DoclingServeJackson3Client.builder()
        .baseUrl(wireMockServer.baseUrl())
        .logResponses()
        .prettyPrint()
        .jsonParser(JsonMapper.builder().addModule(failingDeserializers))
        .build();
  }

  @Override
  protected DoclingServeApi getDoclingClientWithFailingValidationErrorDeserializer() {
    return DoclingServeJackson3Client.builder()
        .baseUrl(wireMockServer.baseUrl())
        .jsonParser(JsonMapper.builder().addMixIn(ValidationError.class, FailingValidationErrorMixIn.class))
        .build();
  }

  static class FailingDeserializer<T> extends ValueDeserializer<T> {
    @Override
    public T deserialize(JsonParser parser, DeserializationContext context) {
      throw new IllegalStateException("boom");
    }
  }

  // A mixin, not a deserializer registered for ValidationError directly, since Jackson does not consult one
  // registered for a type that has a builder over the builder (see FailingDeserializer above)
  @JsonDeserialize(using = FailingDeserializer.class)
  abstract static class FailingValidationErrorMixIn {
  }
}
