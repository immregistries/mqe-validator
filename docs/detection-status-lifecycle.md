# Detection lifecycle status

`Detection.java` defines ~692 possible data-quality observations MQE can raise, but only ~440 of
them are currently wired to a `ValidationRule` — the rest are defined and unused. "Wired or not"
alone doesn't say *why*: is an unwired detection something nobody's gotten to yet, or something
deliberately shelved? Is a wired one production-solid, or still being calibrated? Before this,
there was no structured way to answer that, which made the ~252 unwired detections read as
undifferentiated noise when browsing the catalog.

The `@DetectionStatus` annotation (`org.immregistries.mqe.validator.detection.DetectionStatus`)
captures that lifecycle state directly on the `Detection` enum constant it applies to.

## Status is orthogonal to wiring

Whether a detection is wired to a rule and what lifecycle status it holds are two independent
questions. A detection can be wired *and* `EXPERIMENTAL` (firing today, thresholds not yet
trusted). A detection can be unwired and `PLANNED` (nobody's built the rule yet) — or unwired and
`RETIRED` (a rule used to raise it, and stopped).

This is also why the generated docs under `docs/detections/*.md` show two separate lines per
detection: **Wiring** (is a `ValidationRule` currently raising this?) and **Lifecycle** (what does
`@DetectionStatus` say about its intended state?).

## Status is independent of `@Documentation`

`@Documentation` is the stable, conceptual definition of what a detection *means* — independent of
implementation. `@DetectionStatus` is its lifecycle/maintenance state. A detection can, and
ideally should, carry both; they answer different questions and don't need to agree or change
together.

## The five values

### `PLANNED`

Reserved code from the original spec, not yet implemented by any rule. This is the default state
for most of the ~252 currently-unwired detections — a placeholder someone intends to build a rule
for eventually, not something abandoned.

**Worked example:** `NextOfKinAddressStreet2IsMissing` (`MQE0076`). Grepped for zero references
anywhere in `src/main` outside `Detection.java` itself, and confirmed unwired against a real run of
`ValidationRuleEntityLists.activeDetections()` via `DetectionMarkdownGenerator` — genuinely defined
and nothing else. A reasonable, unremarkable `PLANNED` case: the field exists in the data model,
the detection code was reserved for it, no rule has been written yet.

### `ACTIVE`

Implemented, wired to a rule, actively maintained. The steady-state, "this is production-solid" case
— what most wired detections should eventually be marked as.

**Worked example:** `VaccinationManufacturerCodeIsPresent` (`MQE0734`), wired in `VaccinationMfrIsValid`
(and its `VaccinationMfrIsValidForAdministered`/`VaccinationMfrIsValidForHistorical` variants). A
straightforward, uncontroversial presence check with no known open questions.

### `EXPERIMENTAL`

Implemented and firing, but logic/thresholds are not yet calibrated or finalized. Distinguishes
"this is running in production but we don't fully trust the numbers yet" from `ACTIVE`.

**Worked example:** `VaccinationCreationIsLate` (`MQE0570`), wired in `VaccinationCreationTimeliness`.
That rule's `ImplementationDetail` text claims 3/14/30-day thresholds, but the actual code constants
are `w1=1, w2=7, w3=14` days (see `docs/changes-needed-2028-08.md`) — a real, known doc/code mismatch
on live thresholds, which is exactly the kind of "not yet calibrated" situation `EXPERIMENTAL` exists
to flag. Fixing that mismatch and confirming the intended thresholds would be a reasonable next step
before promoting this to `ACTIVE`.

### `UNSUPPORTED`

Implemented and still firing, but no longer actively maintained or tuned — nobody's watching it,
but nobody's turned it off either.

**Demonstration only for now.** No detection in the current codebase was identified as a clear
`UNSUPPORTED` case; per issue #97 this category is left undemonstrated until a real one surfaces.
Applying it to a detection later is as simple as adding
`@DetectionStatus(status = DetectionLifecycle.UNSUPPORTED, since = "...")` above the constant.

### `RETIRED`

No longer fires; kept as an enum stub for backward compatibility with historical data (e.g. old
validation results referencing the code by name/number still need it to resolve).

**Demonstration only for now.** No detection in the current codebase was identified as a clear
`RETIRED` case — nothing has been deliberately decommissioned yet. Same application pattern as
`UNSUPPORTED` above.

## How it appears in generated documentation

Each entry in `docs/detections/*.md` shows a `**Lifecycle:**` line alongside the existing
`**Wiring:**` line. If a detection has no `@DetectionStatus`, it reads
`_not set - add @DetectionStatus(...) on this constant in Detection.java_` — the same
"not yet documented" convention `@Documentation` already uses, so both gaps read consistently.

`docs/detections/index.md` also reports a **Lifecycle status coverage** breakdown: a count of
detections with no `@DetectionStatus` at all, plus a count per lifecycle value. This is a gap
report only, not a build gate — see the next section.

## How to apply it going forward

Add the annotation directly above the `Detection` constant it describes, the same way
`@Documentation` is applied:

```java
@DetectionStatus(status = DetectionLifecycle.ACTIVE, since = "2026-08")
VaccinationManufacturerCodeIsPresent(VACCINATION_MANUFACTURER_CODE, PRESENT, ACCEPT, MQE0734),
```

`since` is a free-text version or date marking when the status was assigned — used consistently as
`"YYYY-MM"` so far, since `mqe-validator` doesn't do per-detection semantic versioning.

**No build-time enforcement yet.** The coverage counts in `docs/detections/index.md` are informational
only — the build does not fail for missing `@DetectionStatus`. Backfilling is expected to happen
incrementally, as detections are touched for other reasons (fixes, rule updates, documentation
work), not as a one-time pass across all ~692. This may evolve into an enforcement gate later (e.g.
failing the build only for detections that are wired but unannotated), once the backfill is further
along.

After adding or changing a `@DetectionStatus` annotation, regenerate `docs/detections/` per
`docs/detections/README.md` and commit the result, same as for any other `Detection.java` change.
