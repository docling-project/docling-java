package ai.docling.testing.nativeimage;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class SerializationSmokeTests {
  @Test
  void serialization(@TempDir Path temporaryDirectory) throws Exception {
    SmokeTestProcess.run(temporaryDirectory.resolve("serialization.log"), JacksonSmokeTest.class.getName());
  }
}
