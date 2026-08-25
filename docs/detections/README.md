# Detection documentation

This folder documents every [`Detection`](../../src/main/java/org/immregistries/mqe/validator/detection/Detection.java)
MQE can raise — one entry per possible data-quality observation ("a note on the piano keyboard"), grouped by
the record type it applies to (Patient, Vaccination, Message Header, Next-of-kin, Observation, General).

**These files are generated from the code. Do not hand-edit them** — edits will be overwritten the next
time someone regenerates. If something here is wrong or missing, the fix belongs in the Java source (see
below), not in these `.md` files.

## What each entry tells you

- **Message shown to submitters** — the text MQE actually returns, built automatically from the field name
  and the kind of problem (see `Detection.getDisplayText()`).
- **What this means** — a plain-English description of the situation itself, independent of how it's
  detected. Comes from the `@Documentation("...")` annotation on the `Detection` enum constant.
- **Wiring** — whether the detection is currently wired to a live `ValidationRule`, or only defined (some
  detections in `Detection.java` are placeholders that no rule raises yet).
- **Lifecycle** — the detection's maintenance status (`PLANNED`/`ACTIVE`/`EXPERIMENTAL`/`UNSUPPORTED`/
  `RETIRED`), independent of wiring — a detection can be wired *and* experimental. Comes from the
  `@DetectionStatus(...)` annotation. See [detection-status-lifecycle.md](../detection-status-lifecycle.md).
- **Implemented by** — which `ValidationRule` class(es) raise this detection, what triggers it
  (`ImplementationDetail.description`), why it matters (`whyToFix`), and how a data submitter would resolve
  it (`howToFix`).

Not every detection has all of this filled in yet — coverage is being backfilled incrementally. An entry
with `_not yet documented_` under "What this means" is an easy one to pick up if you're adding documentation.

## How to add or update documentation for a detection

1. **What the detection means** (the concept): add or edit the `@Documentation("...")` annotation directly
   above the constant in `Detection.java`.
2. **How a specific rule detects it, why it matters, and how to fix it** (the implementation): in the
   `ValidationRule` subclass that calls `addRuleDetection(Detection.X)`, use the returned `ImplementationDetail`:
   ```java
   ImplementationDetail id = this.addRuleDetection(Detection.PatientAddressIsInvalid);
   id.setImplementationDescription("What the rule actually checks.");
   id.setWhyToFix("Why this matters to the registry / the data's usefulness.");
   id.setHowToFix("What a submitter should do to resolve it.");
   ```
   See `PatientAddressIsValid` and `VaccinationAdminDateIsBeforeLotExpirationDate` for worked examples.
3. **Lifecycle/maintenance status** (independent of the above): add or edit the `@DetectionStatus(...)`
   annotation directly above the constant in `Detection.java`. See
   [detection-status-lifecycle.md](../detection-status-lifecycle.md) for what each status value means and
   worked examples.
4. **Regenerate this folder** — from `mqe-validator/`:
   ```
   mvn -q test-compile
   mvn -q org.codehaus.mojo:exec-maven-plugin:3.1.0:java -Dexec.mainClass=org.immregistries.mqe.validator.detection.DetectionMarkdownGenerator -Dexec.classpathScope=test
   ```
   (or just run `DetectionMarkdownGenerator.main()` directly from your IDE with the module's test classpath).
   If `exec:java` fails with a `loader constraint violation` involving `QName`/`jaxb` on a newer JDK, that's
   exec-maven-plugin's in-process classloader conflicting with the JDK's built-in `java.xml` module — run it
   as a plain forked process instead:
   ```
   mvn -q dependency:build-classpath -Dmdep.outputFile=cp.txt -DincludeScope=test
   java -cp "target/classes;target/test-classes;$(cat cp.txt)" org.immregistries.mqe.validator.detection.DetectionMarkdownGenerator
   ```
5. Commit the regenerated `.md` files along with your code change. `DetectionDocsUpToDateTest` fails the
   build if they're out of sync, so CI will catch it if you forget.

## Files

- [`index.md`](index.md) — counts and links to each object's file.
- `patient.md`, `vaccination.md`, `message-header.md`, `next-of-kin.md`, `observation.md`, `general.md` —
  one file per record type.

## Why this exists

MQE's detection vocabulary (~700 entries and growing) previously lived only as a bare enum with no
attached explanation — understanding what a detection meant or how it was implemented required reading the
rule source directly. This generator, and the `@Documentation`/`ImplementationDetail` annotations it reads,
already existed in the codebase in partial form; this folder is the first time that data is actually
published somewhere browsable, for both engineers and non-engineering staff who want to know what a
detection is doing without reading Java.
