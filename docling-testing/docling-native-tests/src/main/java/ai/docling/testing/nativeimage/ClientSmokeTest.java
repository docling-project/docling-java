package ai.docling.testing.nativeimage;

import java.net.URI;
import java.time.Duration;
import java.util.Objects;

import ai.docling.core.DoclingDocument.SectionHeaderItem;
import ai.docling.serve.api.DoclingServeApi;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.request.source.HttpSource;
import ai.docling.serve.api.convert.request.target.InBodyTarget;
import ai.docling.serve.api.convert.response.InBodyConvertDocumentResponse;

/** Exercises service discovery and HTTP transport without directly referencing a client or mapper. */
public final class ClientSmokeTest {
  private ClientSmokeTest() {
  }

  public static void main(String[] args) throws Exception {
    if (args.length != 2 || !(args[0].equals("2") || args[0].equals("3"))) {
      throw new IllegalArgumentException("Expected Jackson version (2 or 3) and server URL");
    }

    // Only the SPI can make the provider reachable from this executable's entry point.
    // The JVM test harness supplies the URL of its WireMock server.
    DoclingServeApi api = DoclingServeApi.builder()
        .baseUrl(args[1])
        .connectTimeout(Duration.ofSeconds(5))
        .readTimeout(Duration.ofSeconds(5))
        .build();
    equal("ai.docling.serve.client.DoclingServeJackson" + args[0] + "Client", api.getClass().getName());
    equal("ok", api.health().getStatus());

    var request = ConvertDocumentRequest.builder()
        .source(HttpSource.builder().url(URI.create("https://example.com/document.pdf")).build())
        .target(InBodyTarget.builder().build())
        .build();
    var response = (InBodyConvertDocumentResponse) api.convertSource(request);
    var document = response.getDocument().getJsonContent();
    equal("document.pdf", document.getName());
    var heading = (SectionHeaderItem) document.getTexts().get(0);
    equal("Introduction", heading.getText());
    equal(1, heading.getLevel());
    System.out.println("Jackson " + args[0] + " client smoke test passed");
  }

  private static void equal(Object expected, Object actual) {
    if (!Objects.equals(expected, actual)) {
      throw new AssertionError("Expected " + expected + " but got " + actual);
    }
  }
}
