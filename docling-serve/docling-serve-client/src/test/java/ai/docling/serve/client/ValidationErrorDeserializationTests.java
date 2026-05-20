package ai.docling.serve.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

import ai.docling.serve.api.validation.ValidationError;
import ai.docling.serve.api.validation.ValidationErrorDetail;

class ValidationErrorDeserializationTests {
  private final JsonMapper jsonMapper = JsonMapper.builder()
      .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
      .build();

  @Test
  void deserializeWithStringInput() throws Exception {
    var json = """
        {
          "detail": [{
            "type": "url_scheme",
            "loc": ["body", "sources", 0, "http", "url"],
            "msg": "URL scheme should be 'http' or 'https'",
            "input": "file:///path/to/file.pdf"
          }]
        }
        """;

    var error = jsonMapper.readValue(json, ValidationError.class);

    assertThat(error.getErrorDetails())
        .singleElement()
        .satisfies(detail -> {
          assertThat(detail.getType()).isEqualTo("url_scheme");
          assertThat(detail.getMessage()).isEqualTo("URL scheme should be 'http' or 'https'");
          assertThat(detail.getLocations()).isEqualTo(List.of("body", "sources", 0, "http", "url"));
          assertThat(detail.getInput()).isEqualTo("file:///path/to/file.pdf");
        });
  }

  @Test
  void deserializeWithObjectInput() throws Exception {
    var json = """
        {
          "detail": [{
            "type": "missing",
            "loc": ["body", "sources", 0, "file", "base64_string"],
            "msg": "Field required",
            "input": {"kind": "file"}
          }]
        }
        """;

    var error = jsonMapper.readValue(json, ValidationError.class);

    assertThat(error.getErrorDetails())
        .singleElement()
        .satisfies(detail -> {
          assertThat(detail.getType()).isEqualTo("missing");
          assertThat(detail.getMessage()).isEqualTo("Field required");
          assertThat(detail.getLocations()).isEqualTo(List.of("body", "sources", 0, "file", "base64_string"));
          assertThat(detail.getInput())
              .isInstanceOf(Map.class)
              .isEqualTo(Map.of("kind", "file"));
        });
  }

  @Test
  void deserializeWithNullInput() throws Exception {
    var json = """
        {
          "detail": [{
            "type": "value_error",
            "loc": ["body"],
            "msg": "Value error"
          }]
        }
        """;

    var error = jsonMapper.readValue(json, ValidationError.class);

    assertThat(error.getErrorDetails())
        .singleElement()
        .satisfies(detail -> {
          assertThat(detail.getType()).isEqualTo("value_error");
          assertThat(detail.getInput()).isNull();
        });
  }

  @Test
  void deserializeMultipleErrorDetails() throws Exception {
    var json = """
        {
          "detail": [
            {
              "type": "missing",
              "loc": ["body", "sources", 0, "file", "base64_string"],
              "msg": "Field required",
              "input": {"kind": "file"}
            },
            {
              "type": "missing",
              "loc": ["body", "sources", 0, "file", "filename"],
              "msg": "Field required",
              "input": {"kind": "file"}
            }
          ]
        }
        """;

    var error = jsonMapper.readValue(json, ValidationError.class);

    assertThat(error.getErrorDetails()).hasSize(2);
    assertThat(error.getErrorDetails())
        .extracting(ValidationErrorDetail::getMessage)
        .containsOnly("Field required");
    assertThat(error.getErrorDetails())
        .extracting(ValidationErrorDetail::getInput)
        .allSatisfy(input -> assertThat(input).isEqualTo(Map.of("kind", "file")));
  }

  @Test
  void builderAcceptsStringInput() {
    var detail = ValidationErrorDetail.builder()
        .type("url_scheme")
        .input("file:///path/to/file.pdf")
        .build();

    assertThat(detail.getInput()).isEqualTo("file:///path/to/file.pdf");
  }

  @Test
  void builderAcceptsObjectInput() {
    var detail = ValidationErrorDetail.builder()
        .type("missing")
        .input(Map.of("kind", "file"))
        .build();

    assertThat(detail.getInput()).isEqualTo(Map.of("kind", "file"));
  }
}
