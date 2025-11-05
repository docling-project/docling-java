package ai.docling.client.tester.client.ghcr;

import java.util.ArrayList;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.rest.client.reactive.NotBody;

import ai.docling.client.tester.client.RegistryClient;
import ai.docling.client.tester.domain.Tags;
import ai.docling.client.tester.domain.Token;

@Path("/")
@RegisterRestClient(configKey = "github-container-registry")
public interface GHCRClient extends RegistryClient {
  @GET
  @Path("/token")
  @Produces(MediaType.APPLICATION_JSON)
  ai.docling.client.tester.client.ghcr.domain.Token getToken(@QueryParam("scope") String scope);

  @Override
  default Token getTokenForImage(String image) {
    return getToken("repository:%s:pull".formatted(image));
  }

  @GET
  @Path("/v2/{image}/tags/list")
  @Produces(MediaType.APPLICATION_JSON)
  @ClientHeaderParam(name = HttpHeaders.AUTHORIZATION, value = "Bearer {token}")
  ai.docling.client.tester.client.ghcr.domain.Tags getTags(@PathParam("image") String image, @QueryParam("n") Integer limit, @QueryParam("last") String lastTag, @NotBody String token);

  @Override
  default Tags getTags(String image, Token token) {
    var allTags = new ArrayList<Tags>();
    String lastTag = null;

    while (true) {
      var tags = getTags(image, 1000, lastTag, token.token());

      if ((tags.tags() == null) || tags.tags().isEmpty()) {
        // All done
        break;
      }

      allTags.add(tags);

      if (tags.tags().size() < 1000) {
        // Last page
        break;
      }

      lastTag = tags.tags().get(tags.tags().size() - 1);
    }

    var firstTagName = !allTags.isEmpty() ? allTags.get(0).name() : null;
    var allTagNames = allTags.stream()
        .flatMap(t -> t.tags().stream())
        .toList();

    return new ai.docling.client.tester.client.ghcr.domain.Tags(firstTagName, allTagNames);
  }
}
