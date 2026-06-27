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
    void shouldCreateRequestWithBuilder() {
        var request = TagsTestRequest.builder()
                .registry("ghcr.io")
                .image("docling-project/docling-serve")
                .executor(TEST_EXECUTOR)
                .cleanupContainerImages(true)
                .tags(List.of("v1.0.0", "v1.1.0"))
                .build();

        assertThat(request).isNotNull();
        assertThat(request.registry()).isEqualTo("ghcr.io");
        assertThat(request.image()).isEqualTo("docling-project/docling-serve");
        assertThat(request.executor()).isEqualTo(TEST_EXECUTOR);
        assertThat(request.cleanupContainerImages()).isTrue();
        assertThat(request.tags()).containsExactly("v1.0.0", "v1.1.0");
    }

    @Test
    void shouldCreateRequestWithMinimalBuilder() {
        var request = TagsTestRequest.builder()
                .executor(TEST_EXECUTOR)
                .build();

        assertThat(request).isNotNull();
        assertThat(request.registry()).isNull();
        assertThat(request.image()).isNull();
        assertThat(request.executor()).isEqualTo(TEST_EXECUTOR);
        assertThat(request.cleanupContainerImages()).isFalse();
        assertThat(request.tags()).isNull();
    }

    @Test
    void shouldConvertToBuilderAndModify() {
        var originalRequest = TagsTestRequest.builder()
                .registry("ghcr.io")
                .image("docling-project/docling-serve")
                .executor(TEST_EXECUTOR)
                .cleanupContainerImages(false)
                .tags(List.of("v1.0.0"))
                .build();

        var modifiedRequest = originalRequest.toBuilder()
                .registry("quay.io")
                .cleanupContainerImages(true)
                .tags(List.of("v2.0.0"))
                .build();

        assertThat(originalRequest.registry()).isEqualTo("ghcr.io");
        assertThat(originalRequest.cleanupContainerImages()).isFalse();
        assertThat(originalRequest.tags()).containsExactly("v1.0.0");

        assertThat(modifiedRequest.registry()).isEqualTo("quay.io");
        assertThat(modifiedRequest.image()).isEqualTo("docling-project/docling-serve");
        assertThat(modifiedRequest.executor()).isEqualTo(TEST_EXECUTOR);
        assertThat(modifiedRequest.cleanupContainerImages()).isTrue();
        assertThat(modifiedRequest.tags()).containsExactly("v2.0.0");
    }

    @Test
    void shouldCopyAllFieldsWithToBuilder() {

        var original = TagsTestRequest.builder()
                .registry("docker.io")
                .image("library/nginx")
                .executor(TEST_EXECUTOR)
                .cleanupContainerImages(true)
                .tags(List.of("latest", "stable"))
                .build();

        var copy = original.toBuilder().build();

        assertThat(copy.registry()).isEqualTo(original.registry());
        assertThat(copy.image()).isEqualTo(original.image());
        assertThat(copy.executor()).isEqualTo(original.executor());
        assertThat(copy.cleanupContainerImages()).isEqualTo(original.cleanupContainerImages());
        assertThat(copy.tags()).isEqualTo(original.tags());
    }

    @Test
    void shouldSetRegistryInBuilder() {
        var request = TagsTestRequest.builder()
                .registry("custom-registry.io")
                .executor(TEST_EXECUTOR)
                .build();

        assertThat(request.registry()).isEqualTo("custom-registry.io");
    }

    @Test
    void shouldSetImageInBuilder() {
        // Test image setter (line 54-57)
        var request = TagsTestRequest.builder()
                .image("my-app/my-service")
                .executor(TEST_EXECUTOR)
                .build();

        assertThat(request.image()).isEqualTo("my-app/my-service");
    }

    @Test
    void shouldSetCleanupContainerImagesInBuilder() {
        var requestWithCleanup = TagsTestRequest.builder()
                .cleanupContainerImages(true)
                .executor(TEST_EXECUTOR)
                .build();

        var requestWithoutCleanup = TagsTestRequest.builder()
                .cleanupContainerImages(false)
                .executor(TEST_EXECUTOR)
                .build();

        assertThat(requestWithCleanup.cleanupContainerImages()).isTrue();
        assertThat(requestWithoutCleanup.cleanupContainerImages()).isFalse();
    }

    @Test
    void shouldSetTagsInBuilder() {
        var tags = List.of("v1.0.0", "v1.1.0", "latest");
        var request = TagsTestRequest.builder()
                .tags(tags)
                .executor(TEST_EXECUTOR)
                .build();

        assertThat(request.tags()).isEqualTo(tags);
    }

    @Test
    void shouldSetExecutorInBuilder() {
        var customExecutor = Executors.newFixedThreadPool(2);
        var request = TagsTestRequest.builder()
                .executor(customExecutor)
                .build();

        assertThat(request.executor()).isEqualTo(customExecutor);
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
    void shouldHandleEmptyTagsList() {
        // Edge case: empty tags list
        var request = TagsTestRequest.builder()
                .tags(List.of())
                .executor(TEST_EXECUTOR)
                .build();

        assertThat(request.tags()).isEmpty();
    }

    @Test
    void shouldHandleNullTagsList() {
        // Edge case: null tags list
        var request = TagsTestRequest.builder()
                .tags(null)
                .executor(TEST_EXECUTOR)
                .build();

        assertThat(request.tags()).isNull();
    }

    @Test
    void shouldHandleNullRegistry() {
        // Edge case: null registry
        var request = TagsTestRequest.builder()
                .registry(null)
                .executor(TEST_EXECUTOR)
                .build();

        assertThat(request.registry()).isNull();
    }

    @Test
    void shouldHandleNullImage() {
        // Edge case: null image
        var request = TagsTestRequest.builder()
                .image(null)
                .executor(TEST_EXECUTOR)
                .build();

        assertThat(request.image()).isNull();
    }

    @Test
    void shouldChainBuilderMethods() {
        // Test method chaining
        var request = TagsTestRequest.builder()
                .registry("ghcr.io")
                .image("test-image")
                .executor(TEST_EXECUTOR)
                .tags(List.of("v1.0.0"))
                .cleanupContainerImages(true)
                .build();

        assertThat(request.registry()).isEqualTo("ghcr.io");
        assertThat(request.image()).isEqualTo("test-image");
        assertThat(request.executor()).isEqualTo(TEST_EXECUTOR);
        assertThat(request.tags()).containsExactly("v1.0.0");
        assertThat(request.cleanupContainerImages()).isTrue();
    }

    @Test
    void shouldOverrideBuilderValues() {
        // Test that builder values can be overridden
        var request = TagsTestRequest.builder()
                .registry("first-registry")
                .registry("second-registry")
                .image("first-image")
                .image("second-image")
                .cleanupContainerImages(false)
                .cleanupContainerImages(true)
                .executor(TEST_EXECUTOR)
                .build();

        assertThat(request.registry()).isEqualTo("second-registry");
        assertThat(request.image()).isEqualTo("second-image");
        assertThat(request.cleanupContainerImages()).isTrue();
    }
}