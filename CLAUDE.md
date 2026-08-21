# mqe-validator

Core business logic for the Message Quality Evaluator — determines what immunization data is valid and
what has quality problems. See `../CLAUDE.md` for how this fits with the other MQE repos.

- GitHub: immregistries/mqe-validator
- Maven: `groupId org.immregistries`, `artifactId mqe-validator`, packaging `jar`
- Java: 1.8
- Depends on: `mqe-hl7-util` (data model), `codebase-client`, `lonestar` (client), smartystreets SDK,
  libphonenumber, joda-time, commons-lang3, reflections
- Consumed by: `mqe-message-hub`, and externally by the **Data-at-Rest (DAR)** project — which reads data
  extracted from an Immunization Information System (IIS) and runs it through this validator to evaluate
  data quality
- Status: the most actively touched of the four MQE repos; last commit 2023-12-20

## Local build note

`mvn compile`/`test-compile` currently fails in this dev environment: `org.immregistries:lonestar:jar:client:4.4.2`
can't be resolved from Maven Central (it's a private/internal artifact) and isn't cached in `~/.m2`. A stale
but complete `target/classes` from a prior successful build (Nov 2023) is still present and usable as a
classpath for ad hoc `javac`/`java` checks of individual files. Also note if invoking classes reflectively
(e.g. anything that touches `ValidationRuleEntityLists`, which scans for `@ValidationRuleEntry` rules and
eagerly loads `CodeRepository`'s JAXB-based code lists) on a modern JDK: it needs
`--add-opens java.base/java.lang=ALL-UNNAMED` because the legacy JAXB impl this project bundles (2.2.11)
does reflective bytecode injection that current JDKs block by default.

## Detection documentation

Every possible data-quality observation MQE can raise is one entry in the `Detection` enum
(`src/main/java/org/immregistries/mqe/validator/detection/Detection.java`, ~700 entries) — think of it as
the full vocabulary of "notes" MQE can play. A `Detection` is just `(VxuField, DetectionType, SeverityLevel,
MqeCode)`; the human-readable message is generated at runtime from those parts. It says *what field, what
kind of problem*, but not *what it means* or *how it's actually detected* — that's documented separately in
two places, both of which existed as unused scaffolding in the codebase before 2026-08:

1. **`@Documentation("...")` on the `Detection` constant** — one line describing the *concept*, independent
   of any rule. Add it directly above the enum constant.
2. **`ImplementationDetail` on the rule that raises it** — in the `ValidationRule` subclass, the
   `ImplementationDetail` returned by `addRuleDetection(Detection.X)` carries `implementationDescription`
   (what this specific rule checks), `whyToFix` (why it matters), and `howToFix` (how a submitter resolves
   it). See `PatientAddressIsValid` and `VaccinationAdminDateIsBeforeLotExpirationDate` for fully worked
   examples of both layers.

**`docs/detections/*.md`** is generated from these two sources (via `DetectionMarkdownGenerator`, a test-scope
class) and checked into the repo, browsable directly on GitHub — this is how technical staff (not just
engineers) can look up what a detection means and how it's implemented without reading Java. It's a build
artifact, not hand-maintained: see `docs/detections/README.md` for the regenerate command.
`DetectionDocsUpToDateTest` fails the build if the checked-in files fall out of sync with the code, so
**whenever you add a `Detection`, change one's `@Documentation`, or touch a rule's `ImplementationDetail`,
regenerate `docs/detections/` and commit the result.**

Coverage is being backfilled incrementally, not all at once — as of 2026-08-11, 20 of ~692 detections have
`@Documentation`, and 440 are wired to an active rule (the rest are defined but not yet raised by anything).
`docs/detections/index.md` has current counts.
