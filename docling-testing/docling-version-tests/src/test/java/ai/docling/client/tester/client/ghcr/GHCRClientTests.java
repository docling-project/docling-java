package ai.docling.client.tester.client.ghcr;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.tomakehurst.wiremock.client.WireMock;

import io.quarkiverse.wiremock.devservice.ConnectWireMock;
import io.quarkus.test.junit.QuarkusTest;

import ai.docling.client.tester.client.ghcr.domain.Tags;
import ai.docling.client.tester.client.ghcr.domain.Token;
import ai.docling.testcontainers.serve.config.DoclingServeContainerConfig;

@QuarkusTest
@ConnectWireMock
class GHCRClientTests {
  private static final String IMAGE = "%s/%s".formatted(DoclingServeContainerConfig.DOCLING_ORG, DoclingServeContainerConfig.DOCLING_IMAGE_NAME);

  @Inject
  @RestClient
  GHCRClient ghcrClient;

  WireMock wireMock;

  @BeforeEach
  void resetWiremock() {
    this.wireMock.resetRequests();
  }

  @Test
  void getToken() {
    assertThat(this.ghcrClient.getTokenForImage(IMAGE))
        .isNotNull()
        .usingRecursiveComparison()
        .isEqualTo(new Token("some-long-token-value"));

      this.wireMock.verify(
          1,
          getRequestedFor(urlPathEqualTo("/token"))
              .withQueryParam("scope", equalTo("repository:" + IMAGE + ":pull"))
              .withHeader(HttpHeaders.ACCEPT, equalTo(MediaType.APPLICATION_JSON))
      );
  }

  @Test
  void getTags() throws IOException {
    var tags = JsonMapper.builder()
        .build()
        .readValue(Files.readString(Path.of("src", "test", "resources", "wiremock", "__files", "ghcr-tags.json")), Tags.class);

    assertThat(getAllTags())
        .isNotNull()
        .isInstanceOf(Tags.class)
        .usingRecursiveComparison()
        .isEqualTo(tags);

    verifyGetTags();
  }

  @Test
  void getVersionTags() throws UnsupportedEncodingException {
    var versionTags = getAllTags().getVersionTags();

    assertThat(versionTags)
        .isNotNull()
        .containsExactlyElementsOf(
            List.of(
                "v0.6.0",
                "v0.7.0",
                "v0.8.0",
                "v0.9.0",
                "v0.10.0",
                "v0.10.1",
                "v0.11.0",
                "v0.12.0",
                "v0.13.0",
                "v0.14.0",
                "v0.15.0",
                "v0.16.0",
                "v0.16.1",
                "v1.0.0",
                "v1.0.1",
                "v1.1.0",
                "v1.2.0",
                "v1.2.1",
                "v1.2.2",
                "v1.3.0",
                "v1.3.1",
                "v1.4.0",
                "v1.4.1",
                "v1.5.0",
                "v1.5.1",
                "v1.6.0",
                "v1.7.0",
                "v1.7.1",
                "v1.7.2"
            )
        );
    
    verifyGetTags();
  }

  private ai.docling.client.tester.domain.Tags getAllTags() {
    return this.ghcrClient.getTags(IMAGE, new Token("some-long-token-value"));
  }
  
  private void verifyGetTags() throws UnsupportedEncodingException {
    this.wireMock.verify(
        1,
        getRequestedFor(urlPathTemplate("/v2/{image}/tags/list"))
            .withHeader(HttpHeaders.AUTHORIZATION, equalTo("Bearer some-long-token-value"))
            .withHeader(HttpHeaders.ACCEPT, equalTo(MediaType.APPLICATION_JSON))
            .withQueryParam("n", equalTo("1000"))
            .withPathParam("image", equalTo(URLEncoder.encode(IMAGE, "UTF-8")))
    );
  }
}
