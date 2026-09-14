package ai.docling.testing.nativeimage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/** Keeps the smoke application's classpath separate from WireMock and its Jackson dependencies. */
final class SmokeTestProcess {
  private SmokeTestProcess() {
  }

  static void run(Path outputFile, String mainClass, String... args) throws Exception {
    var command = new ArrayList<String>();
    String nativeExecutable = System.getProperty("nativeTestExecutable");
    if (nativeExecutable == null) {
      command.add(Path.of(System.getProperty("java.home"), "bin", "java").toString());
      command.add("-cp");
      command.add(System.getProperty("smokeTestClasspath"));
      command.add(mainClass);
    }
    else {
      command.add(nativeExecutable);
      // Preserve strict diagnostics when launching the binary outside nativeClientRun.
      command.add("-XX:MissingRegistrationReportingMode=Exit");
    }
    command.addAll(List.of(args));

    // Redirect output to a file so a full output pipe cannot block the subprocess.
    var process = new ProcessBuilder(command)
        .redirectErrorStream(true)
        .redirectOutput(outputFile.toFile())
        .start();
    boolean finished;
    try {
      finished = process.waitFor(30, TimeUnit.SECONDS);
    }
    finally {
      if (process.isAlive()) {
        process.destroyForcibly();
        process.waitFor(5, TimeUnit.SECONDS);
      }
    }
    String output = Files.readString(outputFile);
    System.out.print(output);
    assertTrue(finished, "Smoke process timed out:\n" + output);
    assertEquals(0, process.exitValue(), "Smoke process failed:\n" + output);
  }
}
