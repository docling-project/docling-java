package ai.docling.client.tester.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

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
        assertThat(factory.getRegistryClient("ghcr.io"))
                .isNotNull()
                .isSameAs(ghcrClient);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { "docker.io", "quay.io", "GHCR.IO", " ghcr.io " })
    void shouldThrowExceptionForUnsupportedRegistry(String registry) {
        assertThatThrownBy(() -> factory.getRegistryClient(registry))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unsupported registry: " + registry);
    }
}
