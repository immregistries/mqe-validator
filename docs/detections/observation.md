<!-- GENERATED FILE. Do not hand edit.
     Regenerate with DetectionMarkdownGenerator (see docs/detections/README.md). -->

# Observation detections

[Back to index](index.md)

## Date Time Of Observation (OBX-14)

### `ObservationDateTimeOfObservationIsMissing` — MQE0481

- **Severity:** Info
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Observation date time of observation is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `ObservationDateIsValid` — No value found, no data sent, nothing to analyze.

### `ObservationDateTimeOfObservationIsInvalid` — MQE0482

- **Severity:** Info
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Observation date time of observation is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `ObservationDateIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `ObservationDateTimeOfObservationIsPresent` — MQE0639

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Observation date time of observation is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `ObservationDateIsValid` — Observation Date/Time of Observation is indicated

## Identifier Code (OBX-3)

### `ObservationObservationIdentifierCodeIsDeprecated` — MQE0475

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Observation identifier code is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `ObservationObservationIdentifierCodeIsIgnored` — MQE0476

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Observation identifier code is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `ObservationObservationIdentifierCodeIsInvalid` — MQE0477

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Observation identifier code is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `ObservationObservationIdentifierCodeIsMissing` — MQE0478

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Observation identifier code is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `ObservationObservationIdentifierCodeIsUnrecognized` — MQE0479

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Observation identifier code is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `ObservationObservationIdentifierCodeIsPresent` — MQE0640

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Observation identifier code is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Value (OBX-5)

### `ObservationValueIsMissing` — MQE0532

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Observation value is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `ObservationObservationValueIsMissing` — MQE0480

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Observation value is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `ObservationObservationValueIsPresent` — MQE0641

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Observation value is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `ObservationValueIsPresent` — MQE0642

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Observation value is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Value Type (OBX-2)

### `ObservationValueTypeIsDeprecated` — MQE0470

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Observation value type is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `ObservationValueTypeIsValid` — Deprecated code value.

### `ObservationValueTypeIsIgnored` — MQE0471

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Observation value type is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `ObservationValueTypeIsInvalid` — MQE0472

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Observation value type is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `ObservationValueTypeIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `ObservationValueTypeIsMissing` — MQE0473

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Observation value type is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `ObservationValueTypeIsValid` — No value found, no data sent, nothing to analyze.

### `ObservationValueTypeIsUnrecognized` — MQE0474

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Observation value type is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `ObservationValueTypeIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `ObservationValueTypeIsPresent` — MQE0643

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Observation value type is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `ObservationValueTypeIsValid` — Value found, something sent, may be valid or invalid

