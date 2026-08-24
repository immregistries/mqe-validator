package org.immregistries.mqe.validator.detection;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntityLists;

/**
 * Regenerates the human-readable Detection documentation under {@code docs/detections/} from the
 * {@link Detection} enum and the {@link ValidationRule} implementations that raise each one.
 *
 * <p>This is the only supported way to produce those files - they are a build artifact of the
 * code, not something to hand edit. Run this class's {@code main} method (or the Maven-friendly
 * wrapper {@code mvn -q exec:java -Dexec.mainClass=org.immregistries.mqe.validator.detection.DetectionMarkdownGenerator}
 * from the {@code mqe-validator} module) after changing {@code Detection.java} or a rule's
 * {@code @Documentation}/{@code ImplementationDetail} content, then commit the regenerated files.
 * {@link DetectionDocsUpToDateTest} fails the build if the checked-in files fall out of sync.
 */
public class DetectionMarkdownGenerator {

  public static final Path OUTPUT_DIR = Paths.get("docs", "detections");

  public static void main(String[] args) throws IOException {
    Map<String, String> files = renderAll();
    Files.createDirectories(OUTPUT_DIR);
    for (Map.Entry<String, String> entry : files.entrySet()) {
      Files.write(OUTPUT_DIR.resolve(entry.getKey()), entry.getValue().getBytes(StandardCharsets.UTF_8));
    }
    System.out.println("Wrote " + files.size() + " files to " + OUTPUT_DIR.toAbsolutePath());
  }

  /**
   * @return map of filename (e.g. "patient.md", "index.md") to full file content, entirely
   *     in-memory so tests can diff it against what's checked in without touching disk.
   */
  public static Map<String, String> renderAll() {
    Map<String, String> files = new LinkedHashMap<>();

    Map<String, List<RuleImplementation>> byDetection = implementationsByDetection();
    Set<Detection> active = ValidationRuleEntityLists.activeDetections();

    // Object description (e.g. "Patient") -> Field label (e.g. "Address (PID-11)") -> detections,
    // in enum declaration order within each field.
    Map<String, Map<String, List<Detection>>> byObjectThenField = new TreeMap<>();
    for (Detection d : Detection.values()) {
      String object = d.getTargetObject().getDescription();
      String field = fieldLabel(d);
      byObjectThenField
          .computeIfAbsent(object, o -> new TreeMap<>())
          .computeIfAbsent(field, f -> new ArrayList<>())
          .add(d);
    }

    int totalCount = Detection.values().length;
    int activeCount = active.size();
    int documentedCount = 0;
    Map<DetectionLifecycle, Integer> lifecycleCounts = new EnumMap<>(DetectionLifecycle.class);
    int lifecycleUnsetCount = 0;
    for (Detection d : Detection.values()) {
      DetectionStatus lifecycle = lifecycleFor(d);
      if (lifecycle == null) {
        lifecycleUnsetCount++;
      } else {
        lifecycleCounts.merge(lifecycle.status(), 1, Integer::sum);
      }
    }
    List<String> indexLines = new ArrayList<>();

    for (Map.Entry<String, Map<String, List<Detection>>> objEntry : byObjectThenField.entrySet()) {
      String object = objEntry.getKey();
      int objectCount = objEntry.getValue().values().stream().mapToInt(List::size).sum();
      String fileName = slug(object) + ".md";

      StringBuilder sb = new StringBuilder();
      sb.append(GENERATED_BANNER).append("\n\n");
      sb.append("# ").append(object).append(" detections\n\n");
      sb.append("[Back to index](index.md)\n\n");

      for (Map.Entry<String, List<Detection>> fieldEntry : objEntry.getValue().entrySet()) {
        sb.append("## ").append(fieldEntry.getKey()).append("\n\n");
        for (Detection d : fieldEntry.getValue()) {
          sb.append(renderDetection(d, active.contains(d), byDetection.get(d.name())));
        }
      }

      for (Detection d : Detection.values()) {
        if (d.getTargetObject().getDescription().equals(object) && !documentationFor(d).isEmpty()) {
          documentedCount++;
        }
      }

      files.put(fileName, sb.toString());
      indexLines.add("- [" + object + "](" + fileName + ") - " + objectCount + " detections");
    }

    StringBuilder index = new StringBuilder();
    index.append(GENERATED_BANNER).append("\n\n");
    index.append("# Detection documentation index\n\n");
    index.append("See [README.md](README.md) for what this is and how to regenerate it.\n\n");
    index.append("- Total detections defined: ").append(totalCount).append("\n");
    index.append("- Wired to an active rule: ").append(activeCount).append("\n");
    index.append("- Not yet wired to any rule: ").append(totalCount - activeCount).append("\n");
    index.append("- With a `@Documentation` concept description: ").append(documentedCount).append("\n\n");
    index.append("## Lifecycle status coverage\n\n");
    index.append("Backfilled incrementally - see [detection-status-lifecycle.md](../detection-status-lifecycle.md). ");
    index.append("Not build-enforced yet, just a gap report.\n\n");
    index.append("- No `@DetectionStatus` set: ").append(lifecycleUnsetCount).append("\n");
    for (DetectionLifecycle value : DetectionLifecycle.values()) {
      index.append("- ").append(value).append(": ").append(lifecycleCounts.getOrDefault(value, 0)).append("\n");
    }
    index.append("\n## By object\n\n");
    for (String line : indexLines) {
      index.append(line).append("\n");
    }
    files.put("index.md", index.toString());

    return files;
  }

  private static final String GENERATED_BANNER =
      "<!-- GENERATED FILE. Do not hand edit.\n"
          + "     Regenerate with DetectionMarkdownGenerator (see docs/detections/README.md). -->";

  private static String fieldLabel(Detection d) {
    String field = WordUtils.capitalizeFully(d.getTargetField().getFieldDescription());
    String locator = d.getTargetField().getHl7Locator();
    return locator != null ? field + " (" + locator.toUpperCase() + ")" : field;
  }

  private static String renderDetection(Detection d, boolean active, List<RuleImplementation> impls) {
    StringBuilder sb = new StringBuilder();
    sb.append("### `").append(d.name()).append("` — ").append(d.getMqeMqeCode()).append("\n\n");
    sb.append("- **Severity:** ").append(d.getSeverity().getLabel()).append("\n");
    sb.append("- **Wiring:** ")
        .append(active ? "Active - wired to at least one rule below" : "Defined but not currently wired to any rule")
        .append("\n");
    DetectionStatus lifecycle = lifecycleFor(d);
    sb.append("- **Lifecycle:** ")
        .append(lifecycle == null
            ? "_not set - add `@DetectionStatus(...)` on this constant in Detection.java_"
            : lifecycle.status() + " (since " + lifecycle.since() + ")")
        .append("\n");
    sb.append("- **Message shown to submitters:** ").append(d.getDisplayText()).append("\n");

    String doc = documentationFor(d);
    sb.append("- **What this means:** ")
        .append(doc.isEmpty() ? "_not yet documented - add `@Documentation(\"...\")` on this constant in Detection.java_" : doc)
        .append("\n");

    if (impls != null && !impls.isEmpty()) {
      sb.append("- **Implemented by:**\n");
      for (RuleImplementation impl : impls) {
        sb.append("  - `").append(impl.ruleClassName).append("`");
        String description = impl.detail.getImplementationDescription();
        if (StringUtils.isNotBlank(description)) {
          sb.append(" — ").append(description.trim());
        }
        sb.append("\n");
        if (StringUtils.isNotBlank(impl.detail.getWhyToFix())) {
          sb.append("    - *Why it matters:* ").append(impl.detail.getWhyToFix().trim()).append("\n");
        }
        if (StringUtils.isNotBlank(impl.detail.getHowToFix())) {
          sb.append("    - *How to fix:* ").append(impl.detail.getHowToFix().trim()).append("\n");
        }
      }
    }
    sb.append("\n");
    return sb.toString();
  }

  private static String documentationFor(Detection d) {
    try {
      Field f = Detection.class.getField(d.name());
      if (f.isAnnotationPresent(Documentation.class)) {
        return f.getAnnotation(Documentation.class).value();
      }
    } catch (NoSuchFieldException e) {
      // Can't happen - d.name() always names a field of its own enum class.
    }
    return "";
  }

  private static DetectionStatus lifecycleFor(Detection d) {
    try {
      Field f = Detection.class.getField(d.name());
      return f.getAnnotation(DetectionStatus.class);
    } catch (NoSuchFieldException e) {
      // Can't happen - d.name() always names a field of its own enum class.
      return null;
    }
  }

  private static String slug(String s) {
    return s.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("(^-|-$)", "");
  }

  private static class RuleImplementation {
    final String ruleClassName;
    final ImplementationDetail detail;

    RuleImplementation(String ruleClassName, ImplementationDetail detail) {
      this.ruleClassName = ruleClassName;
      this.detail = detail;
    }
  }

  @SuppressWarnings("unchecked")
  private static Map<String, List<RuleImplementation>> implementationsByDetection() {
    Map<String, List<RuleImplementation>> byDetection = new LinkedHashMap<>();
    for (ValidationRuleEntityLists list : ValidationRuleEntityLists.values()) {
      for (ValidationRule<?> rule : list.getRules()) {
        String ruleClassName = rule.getClass().getSimpleName();
        for (ImplementationDetail detail : rule.getImplementationDocumentation()) {
          if (detail.getDetection() == null) {
            continue;
          }
          byDetection
              .computeIfAbsent(detail.getDetection().name(), n -> new ArrayList<>())
              .add(new RuleImplementation(ruleClassName, detail));
        }
      }
    }
    // ValidationRuleEntityLists.getRules() and ValidationRule.getImplementationDocumentation() are both
    // backed by HashSets, whose iteration order depends on enum identity hash codes and is therefore not
    // stable across JVM runs. Sort explicitly so regenerated output is byte-for-byte reproducible.
    Comparator<RuleImplementation> byRuleThenDescription =
        Comparator.<RuleImplementation, String>comparing(r -> r.ruleClassName)
            .thenComparing(r -> String.valueOf(r.detail.getImplementationDescription()));
    for (List<RuleImplementation> impls : byDetection.values()) {
      impls.sort(byRuleThenDescription);
    }
    return byDetection;
  }
}
