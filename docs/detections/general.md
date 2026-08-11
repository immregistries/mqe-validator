<!-- GENERATED FILE. Do not hand edit.
     Regenerate with DetectionMarkdownGenerator (see docs/detections/README.md). -->

# General detections

[Back to index](index.md)

## Authorization

### `GeneralAuthorizationException` — MQE0002

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** General authorization exception
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Configuration

### `GeneralConfigurationException` — MQE0003

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** General configuration exception
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `UnknownValidationIssue` — MQE0000

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** General configuration is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Object

### `PatientObjectIsMissing` — MQE0545

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General object is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientExists` — No value found, no data sent, nothing to analyze.

### `PatientObjectIsPresent` — MQE0687

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General object is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientExists` — Verifies that the patient object was created and patient detections can be executed

## Parse

### `GeneralParseException` — MQE0004

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** General parse exception
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Processing

### `GeneralProcessingException` — MQE0005

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** General processing exception
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `UnknownDetection` — MQE0558

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** General processing exception
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Vaccine Coverage At 24 Months

### `VaccineCoverageAt24MonthsSeries4_3_1_3_3_1_4` — MQE0767

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine coverage at 24 months for series 4:3:1:3:3:1:4
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

## Vaccine Coverage At 36 Months

### `VaccineCoverageAt36MonthsSeries4_3_1_3_3_1_4` — MQE0768

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine coverage at 36 months for series 4:3:1:3:3:1:4
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

## Vaccine Evaluation

### `VaccineEvaluationHepb1Only` — MQE0773

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation with only 1 valid HepB dose
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineEvaluationHasInvalidDoses1orMore` — MQE0778

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation with 1 or more invalid doses
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineEvaluationHasInvalidDoses2orMore` — MQE0779

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation with 2 or more invalid doses
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineEvaluationHasInvalidDoses3orMore` — MQE0780

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation with 3 or more invalid doses
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineEvaluationHasInvalidDoses4orMore` — MQE0781

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation with 4 or more invalid doses
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineEvaluationHasInvalidDoses5orMore` — MQE0782

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation with 5 or more invalid doses
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineEvaluationHasInvalidDoses10orMore` — MQE0783

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation with 10 or more invalid doses
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

## Vaccine Evaluation At 15 Months

### `VaccineEvaluationAt15MonthsPcv4` — MQE0754

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation at 15 months with 4 valid PCV doses
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineEvaluationAt15MonthsPolio3` — MQE0755

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation at 15 months with 3 valid IPV doses
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineEvaluationAt15MonthsMmr1` — MQE0756

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation at 15 months with 1 valid MMR dose
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineEvaluationAt15MonthsVar1` — MQE0757

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation at 15 months with 1 valid Varicella doses
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineEvaluationAt15MonthsHib2` — MQE0759

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation at 15 months with 2 valid Hib Doses
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

## Vaccine Evaluation At 18 Months

### `VaccineEvaluationAt18MonthsHepb3` — MQE0752

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation at 18 months with 3 valid HepB doses
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineEvaluationAt18MonthsDtap4` — MQE0753

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation at 18 months with 4 valid DTaP doses
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineEvaluationAt18MonthsHepa2` — MQE0758

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation at 18 months with 2 valid HepA doses
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

## Vaccine Evaluation At 24 Months

### `VaccineEvaluationAt24MonthsDtap4` — MQE0760

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation at 24 months with 4 valid DTaP doses
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineEvaluationAt24MonthsPolio3` — MQE0761

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation at 24 months with 3 valid IPV doses
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineEvaluationAt24MonthsMmr1` — MQE0762

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation at 24 months with 1 valid MMR dose
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineEvaluationAt24MonthsHib3` — MQE0763

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation at 24 months with 3 valid Hib Doses
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineEvaluationAt24MonthsHepa2` — MQE0769

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation at 24 months with 2 valid HepA doses
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineEvaluationAt24MonthsHepb3` — MQE0764

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation at 24 months with 3 valid HepB doses
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineEvaluationAt24MonthsVar1` — MQE0765

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation at 24 months with 1 valid Varicella doses
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineEvaluationAt24MonthsPcv4` — MQE0766

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine evaluation at 24 months with 4 valid PCV doses
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

## Vaccine Forecast At 24 Months

### `VaccineForecastAt24MonthsHibComplete` — MQE0774

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine forecast at 24 months Hib
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineForecastAt24MonthsPcvComplete` — MQE0775

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine forecast at 24 months PCV
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineForecastAt24MonthsRotaComplete` — MQE0776

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine forecast at 24 months Rotavirus
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

### `VaccineForecastAt24MonthsHepbComplete` — MQE0777

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** General vaccine forecast at 24 months Hep B
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccineEvaluation`

