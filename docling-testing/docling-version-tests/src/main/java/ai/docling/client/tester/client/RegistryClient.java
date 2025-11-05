package ai.docling.client.tester.client;

import ai.docling.client.tester.domain.Tags;
import ai.docling.client.tester.domain.Token;

public interface RegistryClient {
  Token getTokenForImage(String image);
  Tags getTags(String image, Token token);
}
