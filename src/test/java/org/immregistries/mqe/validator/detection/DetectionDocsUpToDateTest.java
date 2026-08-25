package org.immregistries.mqe.validator.detection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

/**
 * Fails if {@code docs/detections/*.md} is out of sync with what {@link DetectionMarkdownGenerator}
 * would produce right now - i.e. someone changed a {@code Detection}, a {@code @Documentation}
 * annotation, or an {@code ImplementationDetail} without regenerating the docs. See
 * {@code docs/detections/README.md} for how to regenerate.
 */
public class DetectionDocsUpToDateTest {

  @Test
  public void generatedDocsMatchCheckedInFiles() throws IOException {
    Map<String, String> expected = DetectionMarkdownGenerator.renderAll();
    Path outputDir = DetectionMarkdownGenerator.OUTPUT_DIR;

    Set<String> actualFileNames = listGeneratedFileNames(outputDir);
    Set<String> expectedFileNames = new TreeSet<>(expected.keySet());
    assertEquals(
        "docs/detections/ contains a different set of generated files than DetectionMarkdownGenerator "
            + "would produce. Regenerate per docs/detections/README.md.",
        expectedFileNames, actualFileNames);

    StringBuilder mismatches = new StringBuilder();
    for (Map.Entry<String, String> entry : expected.entrySet()) {
      Path file = outputDir.resolve(entry.getKey());
      String onDisk = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
      if (!normalize(onDisk).equals(normalize(entry.getValue()))) {
        mismatches.append(entry.getKey()).append("\n");
      }
    }
    if (mismatches.length() > 0) {
      fail("The following docs/detections/ files are stale (don't match what DetectionMarkdownGenerator "
          + "produces from the current code). Regenerate per docs/detections/README.md and commit the "
          + "result:\n" + mismatches);
    }
  }

  private static String normalize(String s) {
    return s.replace("\r\n", "\n").trim();
  }

  private static Set<String> listGeneratedFileNames(Path outputDir) throws IOException {
    if (!Files.isDirectory(outputDir)) {
      return new HashSet<>();
    }
    try (Stream<Path> files = Files.list(outputDir)) {
      return files
          .map(p -> p.getFileName().toString())
          .filter(name -> name.endsWith(".md") && !name.equalsIgnoreCase("README.md"))
          .collect(Collectors.toCollection(TreeSet::new));
    }
  }
}
