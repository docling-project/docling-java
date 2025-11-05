package ai.docling.client.tester.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import ai.docling.client.tester.client.ghcr.GHCRClient;

@ApplicationScoped
public class RegistryClientFactory {
  private final Map<String, RegistryClient> clients = new HashMap<>();

  public RegistryClientFactory(@RestClient GHCRClient ghcrClient) {
    this.clients.put("ghcr.io", ghcrClient);
  }

  public RegistryClient getRegistryClient(String registry) {
    return Optional.ofNullable(this.clients.get(registry))
        .orElseThrow(() -> new IllegalArgumentException("Unsupported registry: " + registry));
  }
}
