package ai.docling.testing.nativeimage;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.matchingJsonPath;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.github.tomakehurst.wiremock.WireMockServer;

class ClientSmokeTests {
  @Test
  void serviceDiscoveryAndHttp(@TempDir Path temporaryDirectory) throws Exception {
    var server = new WireMockServer(wireMockConfig().bindAddress("127.0.0.1").dynamicPort());
    try {
      server.start();
      server.stubFor(get(urlEqualTo("/health")).willReturn(okJson("{\"status\":\"ok\"}")));
      server.stubFor(post(urlEqualTo("/v1/convert/source")).willReturn(okJson("""
          {"document":{"filename":"document.pdf","json_content":{
            "name":"document.pdf","texts":[{
              "self_ref":"#/texts/0","label":"section_header","text":"Introduction","level":1
            }]}},"status":"success","processing_time":0.25}
          """)));

      SmokeTestProcess.run(temporaryDirectory.resolve("client.log"), ClientSmokeTest.class.getName(), System.getProperty("nativeTestJacksonVersion", "3"), server.baseUrl());

      server.verify(1, getRequestedFor(urlEqualTo("/health")));
      server.verify(1, postRequestedFor(urlEqualTo("/v1/convert/source"))
          .withRequestBody(matchingJsonPath("$.sources[0].kind", equalTo("http")))
          .withRequestBody(matchingJsonPath("$.sources[0].url", equalTo("https://example.com/document.pdf")))
          .withRequestBody(matchingJsonPath("$.target.kind", equalTo("inbody"))));
    }
    finally {
      server.stop();
    }
  }
}
