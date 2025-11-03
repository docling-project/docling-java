package ai.docling.api.serve.convert.response;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ErrorItem}.
 */
class ErrorItemTests {

  @Test
  void createErrorItemWithAllFields() {
    String componentType = "parser";
    String errorMessage = "Failed to parse document structure";
    String moduleName = "docling.core.parser";

    ErrorItem errorItem = ErrorItem.builder()
        .componentType(componentType)
        .errorMessage(errorMessage)
        .moduleName(moduleName)
        .build();

    assertThat(errorItem.getComponentType()).isEqualTo(componentType);
    assertThat(errorItem.getErrorMessage()).isEqualTo(errorMessage);
    assertThat(errorItem.getModuleName()).isEqualTo(moduleName);
  }

  @Test
  void createErrorItemWithNullFields() {
    ErrorItem errorItem = ErrorItem.builder().build();

    assertThat(errorItem.getComponentType()).isNull();
    assertThat(errorItem.getErrorMessage()).isNull();
    assertThat(errorItem.getModuleName()).isNull();
  }

  @Test
  void createErrorItemWithEmptyFields() {
    String componentType = "";
    String errorMessage = "";
    String moduleName = "";

    ErrorItem errorItem = ErrorItem.builder()
        .componentType(componentType)
        .errorMessage(errorMessage)
        .moduleName(moduleName)
        .build();

    assertThat(errorItem.getComponentType()).isEmpty();
    assertThat(errorItem.getErrorMessage()).isEmpty();
    assertThat(errorItem.getModuleName()).isEmpty();
  }

}
