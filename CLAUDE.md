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

As of 2026-08-24, `mvn compile test-compile` (and `mvn test`) work again. The build had been broken because
`org.immregistries:codebase-client` and `org.immregistries:lonestar:jar:client:4.4.2` are private artifacts,
not on Maven Central and not cached in `~/.m2` by default — `lonestar` in particular is produced as a jar
side-artifact (classifier `client`) of building the separate `LoneStarVaccineForecaster` repo (`war`
packaging), not published anywhere.

To build from scratch in a fresh environment:
1. Clone `immregistries/codebase-client`, check out tag `v3.0.0`, `mvn install -Dgpg.skip=true` (the gpg-sign
   plugin execution is for the real release pipeline and has no key available locally).
2. Build `LoneStarVaccineForecaster` (checked out separately, e.g. `C:\dev\immregistries\LoneStarVaccineForecaster`)
   with `mvn package` — **not** `mvn install`, since the war-plugin build binds Tomcat-deploy steps
   (`maven-antrun-plugin`/`maven-resources-plugin`) to the `pre-integration-test` phase that need
   `${env.CATALINA_BASE}` and aren't relevant here. Then install just the produced client jar directly:
   `mvn install:install-file -Dfile=target/lonestar-4.4.2-client.jar -DpomFile=pom.xml -Dclassifier=client
   -DgroupId=org.immregistries -DartifactId=lonestar -Dversion=4.4.2`.
3. `mvn compile test-compile` in `mqe-validator` now resolves both.

This project, `codebase-client`, and `LoneStarVaccineForecaster` are now aligned on `codebase-client` 3.0.0
(previously `mqe-validator` pinned `2.3.1` while `LoneStarVaccineForecaster` pinned a nonexistent `2.3` —
the two were already out of sync before this repo's build ever failed). `3.0.0` renamed
`javax.xml.bind` → `jakarta.xml.bind`; `LoneStarVaccineForecaster`'s pom was updated to depend on
`org.glassfish.jaxb:jaxb-runtime` (jakarta) instead of the old `javax.xml.bind`/`com.sun.xml.bind` 2.2.11
triplet. Neither `mqe-validator` nor `LoneStarVaccineForecaster` reference JAXB packages directly in their own
source — it's only pulled in transitively to back `codebase-client`'s generated classes — so no source changes
were needed in either repo, only pom dependency swaps. **The previously-needed
`--add-opens java.base/java.lang=ALL-UNNAMED` workaround is gone**: the modern jakarta JAXB runtime doesn't do
the reflective bytecode injection the old bundled 2.2.11 impl did, confirmed by a clean `mvn test` run with no
extra JVM args.

Separately, `mvn test` currently has 4 pre-existing failures (`ImplementationDocumentationTest`,
`ValidationRuleTest.AllPatientRules`, `ValidationRulesOverlapTest.patientHasDuplicate`/`vaccinationHasDuplicate`)
— these look unrelated to the library/version work (content/count mismatches in detection coverage and rule
overlap, not JAXB/classpath errors), most likely just never caught because the build has been broken since
2023. Not yet root-caused.

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
