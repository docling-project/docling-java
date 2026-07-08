package ai.docling.client.tester.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

class TagsTestRequestTests {

    private static final Executor TEST_EXECUTOR = Executors.newSingleThreadExecutor();

    @Test
    void shouldThrowExceptionWhenExecutorIsNull() {
        assertThatThrownBy(() -> new TagsTestRequest("registry", "image", null, false, List.of("tag1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("executor cannot be null");
    }

    @Test
    void shouldThrowExceptionWhenBuildingWithNullExecutor() {
        assertThatThrownBy(() -> TagsTestRequest.builder()
                .registry("ghcr.io")
                .image("test-image")
                .build())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("executor cannot be null");
    }

    @Test
    void shouldCreateRequestWithBuilder() {
        var request = TagsTestRequest.builder()
                .registry("ghcr.io")
                .image("docling-project/docling-serve")
                .executor(TEST_EXECUTOR)
                .cleanupContainerImages(true)
                .tags(List.of("v1.0.0", "v1.1.0"))
                .build();

        assertThat(request)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(new TagsTestRequest("ghcr.io", "docling-project/docling-serve", TEST_EXECUTOR, true, List.of("v1.0.0", "v1.1.0")));
    }

    @Test
    void shouldCreateRequestWithMinimalBuilder() {
        var request = TagsTestRequest.builder()
                .executor(TEST_EXECUTOR)
                .build();

        assertThat(request)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(new TagsTestRequest(null, null, TEST_EXECUTOR, false, null));
    }

    @Test
    void shouldConvertToBuilderAndModify() {
        var original = TagsTestRequest.builder()
                .registry("ghcr.io")
                .image("docling-project/docling-serve")
                .executor(TEST_EXECUTOR)
                .cleanupContainerImages(false)
                .tags(List.of("v1.0.0"))
                .build();

        var modified = original.toBuilder()
                .registry("quay.io")
                .cleanupContainerImages(true)
                .tags(List.of("v2.0.0"))
                .build();

        assertThat(original.registry()).isEqualTo("ghcr.io");
        assertThat(original.cleanupContainerImages()).isFalse();
        assertThat(original.tags()).containsExactly("v1.0.0");

        assertThat(modified)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(new TagsTestRequest("quay.io", "docling-project/docling-serve", TEST_EXECUTOR, true, List.of("v2.0.0")));
    }
}
