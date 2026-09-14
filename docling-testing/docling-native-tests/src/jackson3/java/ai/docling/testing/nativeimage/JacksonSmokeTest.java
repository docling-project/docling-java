package ai.docling.testing.nativeimage;

import tools.jackson.databind.json.JsonMapper;

public class JacksonSmokeTest extends SerializationSmokeTest {
  private final JsonMapper mapper = new JsonMapper();

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
    System.out.println("Jackson 3 serialization smoke test passed");
  }
}
