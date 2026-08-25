## MQE0734 - 1.25. Vaccination manufacturer code is present #3768

Modify detection or create new one so it only reviews administered doses for MVX.

Currently it looks at all doses - not just administered ones

**What we know (2026-08-11):** `VaccinationManufacturerCodeIsPresent` is implemented in
[`VaccinationMfrIsValid`](../src/main/java/org/immregistries/mqe/validator/engine/rules/vaccination/VaccinationMfrIsValid.java).
In the code as it exists in this repo right now, the entire code-validity check — including the call that
raises `Present` — is already wrapped in `if (target.isAdministered())`:

```java
if (target.isAdministered()) {
  issues.addAll(codr.handleCode(target.getManufacturer(), VxuField.VACCINATION_MANUFACTURER_CODE, target));
  ...
}
```

`git log` shows this guard has been in place since at least July 2021 (commit `0f403dc`). Historical doses
should not be able to raise `VaccinationManufacturerCodeIsPresent` today. **This ticket appears to already
be resolved in this codebase** — either the deployed/production version predates this fix, or the ticket is
stale. No code change is evident here; recommend confirming against the actual deployed behavior before
doing anything else.

**Status: Possibly already resolved — verify against production, don't re-implement blind.**

This is also a literal duplicate of "Modify MQE0734 - MVX is present #3671" below — same detection, same ask.

## Create MQE Detections for Administered dose entry into IIS y days from admin date #3789

I would like detections for the following items:

Administered dose entry into IIS exactly 2 days from admin date
Administered dose entry into IIS exactly 3 days from admin date
Administered dose entry into IIS exactly 4 days from admin date
Administered dose entry into IIS exactly 5 days from admin date
Administered dose entry into IIS exactly 6 days from admin date
Administered dose entry into IIS exactly 7 days from admin date

**What we know (2026-08-11):** A related rule already exists —
[`VaccinationCreationTimeliness`](../src/main/java/org/immregistries/mqe/validator/engine/rules/vaccination/VaccinationCreationTimeliness.java)
— which buckets the gap between admin date and system entry date into `VaccinationCreationIsOnTime` /
`IsLate` / `IsVeryLate` / `IsTooLate`. It does **not** currently give exact-day granularity, so this is a
genuinely new set of detections, not a tweak of an existing one.

**Found while reading this rule, unrelated to this ticket but worth fixing separately:** the
`ImplementationDetail` descriptions on that rule claim thresholds of **3 / 14 / 30 days**, but the actual
code constants are `w1=1, w2=7, w3=14` days:

```java
int w1 = 1;
int w2 = 7;
int w3 = 14;
...
if (difference.getDays() <= w1) { ... IsOnTime }
else if (difference.getDays() <= w2) { ... IsLate }
else if (difference.getDays() <= w3) { ... IsVeryLate }
else { ... IsTooLate }
```
The written documentation (both the in-code description and, until now, the generated `docs/detections/`
page) does not match what the rule actually does. This is an existing bug independent of this ticket —
someone reading the docs today would believe "on time" means ≤3 days when the code enforces ≤1 day.

**Status: Straightforward to build, but needs a few decisions first:**
- Literal "exactly N days" means no detection fires on day 0/1 or day 8+ under the new scheme — confirm
  that's really what's wanted (vs. "at least N days," which is how the existing Late/VeryLate/TooLate
  buckets work).
- Should these six new detections replace the existing bucketed ones, sit alongside them, or should the
  bucket thresholds be reconciled with these new exact-day ones so they tell a consistent story?
- The 1/7/14-vs-3/14/30 documentation mismatch above should probably be fixed (pick which is actually
  correct and make the docs match) independent of whatever happens with this ticket.

## New MQE - Bad Zip Code #3834

Detect Zip Codes that are bad. Bad Zip Codes are one of the following:

  + Length not equal to 5
  + Is 00000,11111,22222,33333,44444,55555,66666,77777,88888,99999
  + Is lower than 00501
  + Is higher than 99950


**What we know (2026-08-11):** `PatientAddressZipIsInvalid` (MQE0112) already exists as a `Detection`
constant in `Detection.java`, but **no rule currently implements it** — there is no
`PatientAddressZipIsValid` class the way there's a `PatientAddressIsValid`. It's one of the ~252 detections
in the enum that are defined but not wired to anything (see `docs/detections/index.md`). Similarly-shaped
zip fields exist for other person types too: `NextOfKinAddressZipIsInvalid`, and patient guardian address
has no zip-specific detection at all currently. None of these are implemented either.

**Status: Straightforward — the rules given are fully specified with no ambiguity in the arithmetic.** A
few scope questions before building it:
- Same detection for Patient only, or also Next-of-kin / Guardian addresses (which have their own address
  fields and, in NOK's case, an existing but unimplemented `NextOfKinAddressZipIsInvalid`)?
- Should this run regardless of `PATIENT_ADDRESS_COUNTRY`, or only when the country is the US (a Canadian
  postal code would trivially fail "length != 5")? There's an existing `PatientAddressCountryIs*` set of
  detections this could depend on / be gated by.
- ZIP+4 format (`12345-6789`) — treat as invalid (length != 5) or extract/validate the first 5 digits?

## Case insensitive for MQE0344 - Vaccination manufacturer code is unrecognized #3663

This detection appears to be case sensitive. Could we make it case insensitive? For example the following are valid codes: idb, seq, val as invalid but IDB, SEQ, VAL are valid.

**What we know (2026-08-11):** Confirmed and root-caused. Code lookups (for MVX and every other codeset)
go through `CodeRepository.getCodeFromValue()` → `CodeMap.getCodeForCodeset()`, which lives in the
**external `codebase-client` library** (a separate published artifact, `org.immregistries:codebase-client`,
not part of any of the four repos in this workspace). That method does an exact-match `HashMap<String, Code>`
lookup with no case normalization anywhere:

```java
// CodeMap.remap():
codeMap.put(c.getValue(), c);
// CodeMap.getCodeForCodeset():
code = codeSetMap.get(value);
```

So the reported behavior is real and structural, not a one-off bug in a single rule: **any** codeset is
case-sensitive today, MVX included. The reference codes are presumably stored uppercase (hence `IDB` works
and `idb` doesn't).

**Status: Needs a decision, not a quick fix — the root cause is outside this repo.** Three ways to fix it,
increasing in scope:
1. **Normalize just in `VaccinationMfrIsValid`** before calling `handleCode` — narrowest blast radius, but
   inconsistent with every other codeset (which would remain case-sensitive), and duplicates logic if the
   same complaint comes in for another code (it likely will).
2. **Normalize in `CodeHandler.handleCode`** (shared by every codeset in mqe-validator) — fixes it
   everywhere at once, but is a behavior change for every codeset simultaneously, including ones where case
   sensitivity might be intentional (worth checking whether any codeset actually relies on case to
   disambiguate two different codes — unlikely but not verified).
3. **Fix upstream in `codebase-client`** — most correct long-term (the map itself becomes case-insensitive
   for every consumer of that library, not just mqe-validator), but that's a separate repo/release cycle
   outside this workspace, with its own consumers to consider.

## Modify MQE0734 - MVX is present #3671

The MQE detection for MVX present (MQE0734) is currently for all types of doses (administered AND historical) but I'd like it to be just administered doses.

**What we know (2026-08-11):** Duplicate of "MQE0734 - 1.25. Vaccination manufacturer code is present
#3768" above — same finding applies: the code already restricts this to administered doses only. See that
entry.

**Status: Possibly already resolved — verify, and close one of the two duplicate tickets either way.**

## Proposed: Detection status/lifecycle annotation (cross-cutting)

*Not tied to a single ticket — surfaced while discussing #3312/#3313 below, but addresses a problem that
already exists independent of those two.*

**The existing problem:** ~252 of the ~692 `Detection` constants are defined but not wired to any rule
(see `docs/detections/index.md`). Today there is exactly one bucket for all of them —
"defined but not currently wired to any rule" — with no way to tell, without digging through git history,
whether a given one was: spec'd from the original requirements and never built, implemented once and
quietly abandoned, or an intentional placeholder. That undifferentiated bucket is a real source of noise
for anyone (person or agent) trying to work in this catalog.

**The idea:** a second annotation on `Detection` constants, separate from `@Documentation` (which describes
the stable *concept* and rarely changes) since status is exactly the kind of thing that changes over time —
planned → experimental → active → possibly unsupported later. Deliberately lightweight: **just the status
value, no "why" / ticket-reference field** — the annotation should stay cheap to add so it doesn't become
its own chore.

Draft taxonomy:
- **Planned** — reserved (has a stable code, likely from the original spec), nothing implements it yet.
  Most of today's 252 would land here, though confirming that for each one is its own investigation.
- **Active / Supported** — implemented, wired, actively maintained. Safe to build a dashboard on.
- **Experimental** — implemented and firing, but the logic/threshold isn't calibrated yet. Treat the signal
  as provisional and expect it to change or disappear.
- **Unsupported** — still implemented, still firing, but no longer actively maintained. The concept doesn't
  go away — the promise to keep tuning/fixing it does.
- **Retired** — no longer fires. Kept as a stub so the code/enum constant doesn't vanish out from under
  anyone who finds it referenced in older data.

**Important distinction:** this status is orthogonal to "is it wired to a rule" (a mechanical fact, already
computable via `ValidationRuleEntityLists.activeDetections()`). A detection can be wired *and* Experimental
(exactly the calibration case below) or wired *and* Unsupported (legacy code nobody's tuning anymore).
Collapsing both facts into one active/inactive flag is part of why the current 252 reads as an
undifferentiated mess rather than a categorized backlog.

**Rollout:** same incremental approach used for `@Documentation` — apply it going forward to new detections
(starting with whatever comes out of #3312/#3313), backfill the existing catalog gradually rather than as a
one-time mass pass. Once it exists, the `docs/detections/` generator can filter/badge by status, which is
what gives consumers (including DAR) a way to "skinny down" to just the supported set without the rest of
the catalog being in their way.

**Explicitly out of scope for this idea:** no change to `ValidationReport` or the DAR-facing output. Status
lives in the source/documentation layer only. (A separate, later idea — a free-text "notes" field on
`ValidationReport` to say more about *why* a specific detection fired, similar to how `PatientAddressIsInvalid`
already appends SmartyStreets DPV codes via `additionalMessage` — was discussed as a plausible future
enhancement, but it doesn't solve this problem and isn't needed for it.)

## New MQE Detection - Too Many Flu Doses per season #3312

Create a detection to flag patients with too many flu doses for a single flu season. This can be used to identify potential patient mismerges.

Patient has 2 or more flu immunizations from September - March and patient is 10 years old or older at time of vaccination.

CVX codes to be included: 205,168,197,135,161,166,149,111,171,186,153,320,185,155,144,231,200,201,202,150,158,140,141

**What we know (2026-08-11):** No existing detection does per-antigen, calendar-window dose counting. The
closest precedent is
[`VaccinationAdminCountIsAsExpectedForAge`](../src/main/java/org/immregistries/mqe/validator/engine/rules/patient/VaccinationAdminCountIsAsExpectedForAge.java) —
a Patient-level rule that loops `m.getVaccinations()` and counts doses against age-relative windows
(depends on `PatientBirthDateIsValid`). It's a reasonable structural template, but it counts *all* doses
against birth-relative windows, not a specific CVX set against a recurring calendar season — new logic is
needed either way. There's also a dormant `DetectionType` family (`HAS_INVALID_DOSES_1_OR_MORE` through
`_10_OR_MORE`, used by the `VACCINE_EVALUATION*` detections) that shows a precedent for tiered "N or more"
detections, but those detections are themselves unimplemented (no rule raises them), so it's a naming/shape
precedent only, not working code to copy.

**Status: Needs several decisions before this is a "just build it" ticket.** Original open questions below,
followed by a design discussion (2026-08-12) that reframed the problem and settled some of them.

Original open questions:
- Severity — the existing dose-count detections (`AdministeredVaccinationsCountIsLargerThanExpected`, etc.)
  are all `ACCEPT` (logged, not surfaced as WARN/ERROR). Confirm that's right for a "possible mismerge" flag,
  or whether this should be more visible.
- Flu season spans a calendar-year boundary (Sept year N – March year N+1). Needs an explicit definition of
  how the season is identified/labeled and how doses are grouped into one.
- "10 years or older at time of vaccination" — per individual dose, or based on the patient's age at the
  time of the most recent of the 2+ doses?
- Given the next ticket asks for near-identical logic for COVID, worth deciding whether to build one
  reusable "too many doses of code-set X within window Y" mechanism instead of two bespoke rule classes —
  an architecture choice, not just a parameter choice.

### Design discussion (2026-08-12)

**This isn't really about flu or COVID.** These two tickets are the motivating examples for a general
signal: an unusually high dose count in a code-group/time-window is one of the few available clues that a
patient record was improperly merged. MQE can only see the combined record, never what it looked like before
a bad merge — a count anomaly is indirect evidence, not a diagnosis. Framed that way, a detection that only
exists for flu (or only for COVID) is the wrong shape for what's actually being detected.

**Agreed direction for the eventual production mechanism:** one generic, config-driven detection, following
the same pattern already used by `PatientAddressIsInvalid` — a single, simple "black box" signal whose
underlying logic can be arbitrarily complex (there, a SmartyStreets call; here, a dose-count-per-group
check). No new `MqeCode` per antigen family. A small fixed set of generic tier detections (2-3, e.g.
"exceeds expected count" / "far exceeds expected count") would be reused across every configured group,
with the actual group definitions — CVX code list, date window (recurring season vs. fixed range — the
schema needs to support both from day one, since flu is evergreen and COVID-2021 is a one-off), age filter,
and thresholds — living as data, not code. This is "a concept that's part of the validation, not a core
vaccination concept," so the config belongs inside mqe-validator's own resources, not in `codebase-client`.
Loading should follow the pattern `ValidatorProperties` already uses in this codebase: check the working
directory for an override file first, fall back to a bundled classpath default — no new mechanism needs to
be invented for that part.

**Explicitly decided against:** adding a structured field to `ValidationReport` to carry *why* a detection
fired (e.g. which group/rule). DAR only consumes counts of `MqeCode`s today, and a structured reason field
would require retooling on the consuming side for a benefit that doesn't solve the actual current problem.
A free-text elaboration (same idea as the `additionalMessage` already used by `PatientAddressIsInvalid`) is
a reasonable *future* addition once detections get more complex generally, but it's separate from this
ticket and not required to move forward.

**The real blocker is calibration, not engineering.** The 2 (flu) / 4,5,6 (COVID) thresholds in these
tickets were guesses — there's no empirical basis yet for what count is actually anomalous, and DAR's
count-only interface doesn't expose the distribution needed to find out. Address validity gets to skip this
because SmartyStreets already did that calibration externally; nothing has calibrated dose-count anomalies
against this population yet.

**Proposed calibration approach — reuses infrastructure you already have, instead of building a new export
path:** ship a finer ladder of granular, per-group threshold detections (not just the specific numbers in
the original tickets — enough of a ladder, e.g. every integer count in a reasonable range, to get real
resolution) explicitly marked **Experimental** using the status annotation proposed above. Because DAR
already reports counts per `MqeCode`, and each of these is a cumulative "≥N" signal, the difference between
adjacent thresholds reconstructs the actual per-count distribution — patients-with-exactly-N =
count(≥N) − count(≥N+1) — which is effectively a histogram, built entirely from the counting mechanism
that already works today. No new file format, no new plumbing to negotiate with DAR. Once a threshold is
chosen from that data (checked against any known/confirmed mismerge cases as ground truth, if any exist —
worth asking whether such labeled cases exist anywhere), the granular Experimental detections get marked
**Retired** and replaced by the single calibrated production mechanism described above.

**Still to take to the group:**
- Whether DAR needs to be involved in the calibration exercise at all, or whether it's purely
  mqe-validator-side (DAR would just be reporting counts of Experimental codes same as any other, without
  knowing they're temporary).
- The JSON config schema for groups (window types needed on day one, at minimum recurring-season and
  fixed-range).
- Whether any labeled/confirmed mismerge cases exist to validate a chosen threshold against, or whether this
  will be judgment-call calibration from the shape of the distribution alone.
- Final tier count and severity mapping, decided after real data is seen rather than guessed up front.

## New MQE Detection - NDC is present #3666

We currently have MQE0560 which detects if NDC is missing but I'd like one for NDC is present.

**What we know (2026-08-11):** This already exists and is already wired up.
`Detection.VaccinationNDCCodeIsPresent` is registered in
[`VaccinationNdcIsValid`](../src/main/java/org/immregistries/mqe/validator/engine/rules/vaccination/VaccinationNdcIsValid.java),
and fires automatically: `CodeHandler.handleCode()` unconditionally raises the `PRESENT` detection for any
codeset whenever the submitted value is non-blank, before it even checks validity. **This ticket appears to
already be fully implemented.**

**Status: Already resolved — verify against production, then close.**

## New MQE Detection - Too Many COVID Doses in 2021 #3313

Create a detection to flag patients with too many COVID doses in 2021. This can be used to identify potential patient mismerges.

Patient received 4 or more COVID-19 doses with administration date in 2021.
Patient received 5 or more COVID-19 doses with administration date in 2021.
Patient received 6 or more COVID-19 doses with administration date in 2021.

CVX codes to be included: 208,207,218,212,217

**What we know (2026-08-11):** Same situation as the flu-season ticket above — no existing per-antigen,
date-windowed dose-counting mechanism; `VaccinationAdminCountIsAsExpectedForAge` is the closest structural
precedent, not a drop-in template.

**Status: Needs decisions, and one of them is different from the flu ticket.** See the full design
discussion under "Too Many Flu Doses per season #3312" above — this ticket and that one are being treated
as one design problem (a general dose-count mismerge signal), not two separate features. Points specific to
this one:
- Unlike flu season (which recurs every year), this is hardcoded to calendar year 2021. Reframed in the
  2026-08-12 discussion: COVID isn't being flagged as a data-quality problem in itself, it's the known
  historical source of a lot of bad merges that still cause issues today. Read that way, the three named
  thresholds (4/5/6 doses in 2021) are a good candidate set of **Experimental calibration detections** —
  see the "Design discussion" note under #3312 — rather than the final, permanent shape of this detection.
- Once thresholds are calibrated (from the general mechanism proposed under #3312), it's an open question
  whether COVID stays as its own configured group forever, or whether it was really only useful as the
  motivating example and can retire once the general mechanism is proven.

## Investigate MQE Detections MQE0327 and MQE0329 #3793

I would like to know the criteria for inclusion in the following detections -

MQE0327 - administered but appears historical
MQE0329 - historical but appears to be administered

**What we know (2026-08-11):** Both detections are driven by one shared scoring function,
[`AdministeredLikelihood.administeredLikelihoodScore()`](../src/main/java/org/immregistries/mqe/validator/engine/codes/AdministeredLikelihood.java),
which adds up points for how much a dose "looks administered" based on how much supporting data was sent
and how recently it was given:

| Signal | Points |
|---|---|
| Admin date is within 1 month of the message's received date | +5 |
| Lot number present | +2 |
| Expiration date present | +2 |
| Manufacturer (MVX) code present | +2 |
| Financial eligibility code present | +2 |
| Body route code present | +1 |
| Body site code present | +1 |
| Amount present and not "999" or "0" | +3 |
| Facility ID or facility name present | +4 |
| "Given by" person (ID or first/last name) present | +4 |

Max possible score: 26. **Threshold: score ≥ 10 → looks administered; score < 10 → looks historical.**

- `MQE0329` (`VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered`) fires when the sender
  marked the dose historical (rule only runs for doses where `VaccinationSourceIsHistorical` passed) but the
  score is ≥ 10.
- `MQE0327` (`VaccinationInformationSourceIsAdministeredButAppearsToHistorical`) fires when the sender
  marked the dose administered (rule only runs for doses where `VaccinationSourceIsAdministered` passed) but
  the score is < 10.

**Documentation defect found while answering this, worth fixing on its own:** the two rule class files are
named **swapped** relative to what they actually do —
[`VaccinationSourceIsAdministeredButAppearsHistorical.java`](../src/main/java/org/immregistries/mqe/validator/engine/rules/vaccination/VaccinationSourceIsAdministeredButAppearsHistorical.java)
actually implements MQE0329 ("historical but appears administered"), and
[`VaccinationSourceIsHistoricalButAppearsAdministered.java`](../src/main/java/org/immregistries/mqe/validator/engine/rules/vaccination/VaccinationSourceIsHistoricalButAppearsAdministered.java)
actually implements MQE0327 ("administered but appears historical"). The logic and the detections raised
are correct — only the file/class names are inverted. This is almost certainly *why* this ticket was filed;
the code is genuinely confusing to read as-is. No other file in the workspace references these two class
names by string, so swapping the names is a safe, isolated rename whenever it's wanted.

**Status: This ticket is now answered — it's pure documentation, no behavior change needed.** The class-name
swap is a separate, optional, low-risk cleanup (rename only, confirmed nothing else references these names).
