package ai.docling.client.tester.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ai.docling.client.tester.client.ghcr.GHCRClient;

class RegistryClientFactoryTests {

    private GHCRClient ghcrClient;
    private RegistryClientFactory factory;

    @BeforeEach
    void setUp() {
        ghcrClient = mock(GHCRClient.class);
        factory = new RegistryClientFactory(ghcrClient);
    }

    @Test
    void shouldReturnGHCRClientForGhcrRegistry() {
        var client = factory.getRegistryClient("ghcr.io");

        assertThat(client)
                .isNotNull()
                .isSameAs(ghcrClient);
    }

    @Test
    void shouldThrowExceptionForUnsupportedRegistry() {
        assertThatThrownBy(() -> factory.getRegistryClient("docker.io"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unsupported registry: docker.io");
    }

    @Test
    void shouldThrowExceptionForNullRegistry() {
        assertThatThrownBy(() -> factory.getRegistryClient(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unsupported registry: null");
    }

    @Test
    void shouldThrowExceptionForEmptyRegistry() {
        assertThatThrownBy(() -> factory.getRegistryClient(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unsupported registry: ");
    }

    @Test
    void shouldThrowExceptionForUnknownRegistry() {
        assertThatThrownBy(() -> factory.getRegistryClient("quay.io"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unsupported registry: quay.io");
    }

    @Test
    void shouldThrowExceptionForRegistryWithDifferentCase() {
        // Registry names are case-sensitive
        assertThatThrownBy(() -> factory.getRegistryClient("GHCR.IO"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unsupported registry: GHCR.IO");
    }

    @Test
    void shouldThrowExceptionForRegistryWithWhitespace() {
        assertThatThrownBy(() -> factory.getRegistryClient(" ghcr.io "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unsupported registry:  ghcr.io ");
    }

    @Test
    void shouldReturnSameClientInstanceOnMultipleCalls() {
        var client1 = factory.getRegistryClient("ghcr.io");
        var client2 = factory.getRegistryClient("ghcr.io");

        assertThat(client1).isSameAs(client2);
    }
}