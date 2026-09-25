package ai.docling.serve.client;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.tomakehurst.wiremock.WireMockServer;

import ai.docling.serve.api.DoclingServeApi;
import ai.docling.serve.api.validation.ValidationError;

/**
 * Integration tests for {@link DoclingServeJackson2Client}.
 */
class DoclingServeJackson2ClientTests extends AbstractDoclingServeClientTests {
  private static DoclingServeApi doclingClient;
  private static DoclingServeApi authDoclingClient;
  private static DoclingServeApi wiremockDoclingClient;
  private static WireMockServer wireMockServer = new WireMockServer(options().dynamicPort());

  @BeforeAll
  static void setUp() {
    wireMockServer.start();
    doclingClient = DoclingServeJackson2Client.builder()
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

    return DoclingServeJackson2Client.builder()
        .baseUrl(wireMockServer.baseUrl())
        .logResponses()
        .prettyPrint()
        .jsonParser(JsonMapper.builder().addModule(failingDeserializers))
        .build();
  }

  @Override
  protected DoclingServeApi getDoclingClientWithFailingValidationErrorDeserializer() {
    return DoclingServeJackson2Client.builder()
        .baseUrl(wireMockServer.baseUrl())
        .jsonParser(JsonMapper.builder().addMixIn(ValidationError.class, FailingValidationErrorMixIn.class))
        .build();
  }

  static class FailingDeserializer<T> extends JsonDeserializer<T> {
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
