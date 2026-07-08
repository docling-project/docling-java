package ai.docling.client.tester.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NamedThreadFactoryTests {

    @ParameterizedTest(name = "prefix=\"{0}\"")
    @ValueSource(strings = { "test-thread", "test-worker_pool", "" })
    void shouldCreateThreadWithExpectedName(String prefix) {
        var factory = new NamedThreadFactory(prefix);

        Thread thread = factory.newThread(() -> {});

        assertThat(thread)
                .isNotNull()
                .extracting(Thread::getName)
                .asString()
                .startsWith(prefix);
    }
}
