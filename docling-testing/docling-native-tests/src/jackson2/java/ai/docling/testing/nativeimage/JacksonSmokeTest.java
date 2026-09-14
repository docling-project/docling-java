package ai.docling.testing.nativeimage;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class JacksonSmokeTest extends SerializationSmokeTest {
  // Match the reference client's Jackson 2 configuration (and Jackson 3's default).
  private final JsonMapper mapper = JsonMapper.builder()
      .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
      .build();

  @Override
  protected String write(Object value) throws Exception {
    return mapper.writeValueAsString(value);
  }

  @Override
  protected <T> T read(String json, Class<T> type) throws Exception {
    return mapper.readValue(json, type);
  }

  public static void main(String[] args) throws Exception {
    new JacksonSmokeTest().verify();
    System.out.println("Jackson 2 serialization smoke test passed");
  }
}
