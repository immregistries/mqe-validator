<!-- GENERATED FILE. Do not hand edit.
     Regenerate with DetectionMarkdownGenerator (see docs/detections/README.md). -->

# Vaccination detections

[Back to index](index.md)

## Action Code (RXA-21)

### `VaccinationActionCodeIsDeprecated` — MQE0232

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination action code is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationActionCodeIsValid` — Deprecated code value.

### `VaccinationActionCodeIsIgnored` — MQE0233

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination action code is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationActionCodeIsInvalid` — MQE0234

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination action code is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationActionCodeIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `VaccinationActionCodeIsMissing` — MQE0235

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination action code is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationActionCodeIsValid` — No value found, no data sent, nothing to analyze.

### `VaccinationActionCodeIsUnrecognized` — MQE0236

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination action code is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationActionCodeIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `VaccinationActionCodeIsValuedAsAdd` — MQE0237

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination action code is valued as add
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationActionCodeIsValid` — Vaccination Action Code value is A

### `VaccinationActionCodeIsValuedAsAddOrUpdate` — MQE0238

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination action code is valued as add or update
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationActionCodeIsValid` — Vaccination Action Code value is A or U

### `VaccinationActionCodeIsValuedAsDelete` — MQE0239

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination action code is valued as delete
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationActionCodeIsValid` — Vaccination Action Code value is D

### `VaccinationActionCodeIsValuedAsUpdate` — MQE0240

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination action code is valued as update
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationActionCodeIsValid` — Vaccination Action Code value is U

### `VaccinationActionCodeIsPresent` — MQE0709

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination action code is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationActionCodeIsValid` — Value found, something sent, may be valid or invalid

## Admin Code (RXA-5)

### `VaccinationAdminCodeIsForeign` — MQE0268

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin code is valued as foreign
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationIsForeign` — Administered Vaccination has a foreign CVX vaccine code.

### `VaccinationHistoricalCodeIsForeign` — MQE0553

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin code is valued as foreign
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationIsForeign` — Historical Vaccination has a foreign CVX vaccine code.

### `VaccinationAdminCodeIsDeprecated` — MQE0241

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination admin code is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationAdminCodeIsIgnored` — MQE0242

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination admin code is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationAdminCodeIsInvalid` — MQE0243

- **Severity:** Error
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination admin code is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationAdminCodeIsInvalidForDateAdministered` — MQE0491

- **Severity:** Error
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination admin code is invalid for date administered
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationAdminCodeIsMissing` — MQE0244

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin code is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminCodeIsPresent` — No value found, no data sent, nothing to analyze.

### `VaccinationAdminCodeIsNotSpecific` — MQE0245

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin code is not specific
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminCodeIsValid` — Vaccination Administered Code (CVX derived from given NDC, CVX, CPT. Derivation stops on first success.) has an unspecified value type.

### `VaccinationAdminCodeIsNotVaccine` — MQE0246

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin code is not vaccine
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminCodeIsValid` — Vaccination Administered Code (CVX derived from given NDC, CVX, CPT. Derivation stops on first success.) has a non-vaccine value type.

### `VaccinationAdminCodeIsUnexpectedForDateAdministered` — MQE0490

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination admin code is unexpected for date administered
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationAdminCodeIsUnrecognized` — MQE0247

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin code is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminCodeIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `VaccinationAdminCodeIsNotUsable` — MQE0561

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin code is not usable
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminCodeIsUsable` — NDC, CVX, or CPT must be given in order to have a Vaccination Administered Code.

### `VaccinationAdminCodeIsValuedAsNotAdministered` — MQE0248

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin code is valued as not administered
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminCodeIsValid` — Vaccination Administered Code (CVX derived from given NDC, CVX, CPT. Derivation stops on first success.) has a value of 998.

### `VaccinationAdminCodeIsValuedAsUnknown` — MQE0249

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin code is valued as unknown
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminCodeIsValid` — Vaccination Administered Code (CVX derived from given NDC, CVX, CPT. Derivation stops on first success.) has a value of 999.

### `VaccinationAdminCodeMayBeVariationOfPreviouslyReportedCodes` — MQE0250

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination admin code may be variation of previously reported codes
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationAdminCodeIsPresent` — MQE0710

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin code is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminCodeIsPresent` — Value found, something sent, may be valid or invalid

## Admin Code Table (RXA-5)

### `VaccinationAdminCodeTableIsMissing` — MQE0483

- **Severity:** Error
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination admin code table is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationAdminCodeTableIsInvalid` — MQE0484

- **Severity:** Error
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination admin code table is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationAdminCodeTableIsPresent` — MQE0711

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination admin code table is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Admin Date (RXA-3)

### `VaccinationAdminDateIsAfterLotExpirationDate` — MQE0251

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin date is after lot expiration date
- **What this means:** The vaccine's administration date falls on or after the expiration date of the lot that was administered, meaning the dose may have been given from expired vaccine stock.
- **Implemented by:**
  - `VaccinationAdminDateIsBeforeLotExpirationDate` — Compares the vaccination's admin date to the lot's expiration date on the same vaccination record; fails when admin date is on or after the expiration date.
    - *Why it matters:* A dose given from expired vaccine stock may not have provided the intended immunity, which can affect clinical decisions (e.g. whether the dose counts toward the series) and may indicate a data entry error (wrong lot or wrong date) rather than an actual expired-dose administration.
    - *How to fix:* Confirm the lot number and admin date on the source record. If either was mistyped, correct and resubmit. If the dose truly was administered from expired stock, no correction is needed here, but the provider organization should be made aware of the expired-stock usage.

### `VaccinationAdminDateIsAfterMessageSubmitted` — MQE0252

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin date is after message submitted
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminDateIsValid` — Vaccination Administered Date is after the message header date.

### `VaccinationAdminDateIsAfterPatientDeathDate` — MQE0253

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin date is after patient death date
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminDateIsValid` — Vaccination Administered Date is after patient's death date

### `VaccinationAdminDateIsAfterSystemEntryDate` — MQE0254

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin date is after system entry date
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminDateIsValid` — Vaccination Administered Date is after System Entry date.

### `VaccinationAdminDateIsBeforeBirth` — MQE0255

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin date is before birth
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminDateIsValid` — Vaccination Administered Date is before patient's birth date.

### `VaccinationAdminDateIsBeforeOrAfterExpectedVaccineUsageRange` — MQE0256

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin date is before or after expected vaccine usage range
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCvxUseIsValid` — Vaccination Administered Date is outside of expected vaccine date range.

### `VaccinationAdminDateIsBeforeOrAfterLicensedVaccineRange` — MQE0257

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin date is before or after licensed vaccine range
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCvxUseIsValid` — Vaccination Administered Date is outside of licensed vaccine date range.

### `VaccinationAdminDateIsBeforeOrAfterWhenExpectedForPatientAge` — MQE0258

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin date is before or after when expected for patient age
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminDateIsValidForPatientAge` — For this specific vaccine, it was not expected that this vaccination should be given at the age the patient was when this was administered.

### `VaccinationAdminDateIsBeforeOrAfterWhenValidForPatientAge` — MQE0259

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin date is before or after when valid for patient age
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminDateIsValidForPatientAge` — CodeBase specifies the valid age range for this vaccination.  We compare the patients age at administration to this age range.

### `VaccinationAdminDateIsInvalid` — MQE0260

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin date is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminDateIsValid` — Vaccination Administered Date annot be translated to a date.

### `VaccinationAdminDateIsMissing` — MQE0261

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin date is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminDateIsValid` — No value found, no data sent, nothing to analyze.

### `VaccinationAdminDateIsOn15ThDayOfMonth` — MQE0262

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin date is on 15th day of month
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminDateIsValid` — Vaccination Administered Date is on the 15th of the month.

### `VaccinationAdminDateIsOnFirstDayOfMonth` — MQE0263

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin date is on first day of month
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminDateIsValid` — Vaccination Administered Date is the first day of the month.

### `VaccinationAdminDateIsOnLastDayOfMonth` — MQE0264

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin date is on last day of month
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminDateIsValid` — Vaccination Administered Date is on the last day of the month.

### `VaccinationAdminDateIsReportedLate` — MQE0265

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination admin date is reported late
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationAdminDateIsPresent` — MQE0713

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination admin date is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminDateIsValid` — Value found, something sent, may be valid or invalid

## Admin Date End (RXA-4)

### `VaccinationAdminDateEndIsDifferentFromStartDate` — MQE0266

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination admin date end is different from start date
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationAdminDateEndIsMissing` — MQE0267

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination admin date end is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationAdminDateEndIsPresent` — MQE0712

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination admin date end is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Administered Amount (RXA-6)

### `VaccinationAdministeredAmountIsInvalid` — MQE0555

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination administered amount is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredAmountIsReasonable` — Vaccination Administered amount is expected to be a number between 0 and 999.
  - `VaccinationAdministeredAmtIsValid` — Vaccination Administered Amount could not be converted into a number.

### `VaccinationAdministeredAmountIsMissing` — MQE0554

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination administered amount is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredAmtIsValid` — Vaccination Administered Amount is missing or equal to 999.

### `VaccinationAdministeredAmountIsValuedAsZero` — MQE0270

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination administered amount is valued as zero
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredAmtIsValid` — Vaccination Administered Amount is 0.

### `VaccinationAdministeredAmountIsValuedAsUnknown` — MQE0271

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination administered amount is valued as unknown
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredAmtIsValid` — Vaccination Administered Amount is missing or equal to 999.

### `VaccinationAdministeredAmountIsPresent` — MQE0714

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination administered amount is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredAmtIsValid` — Vaccination Administered Amount is indicated and not equal to 999.

## Administered Unit (RXA-7)

### `VaccinationAdministeredUnitIsDeprecated` — MQE0447

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination administered unit is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredUnitIsValid` — Deprecated code value.

### `VaccinationAdministeredUnitIsIgnored` — MQE0448

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination administered unit is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationAdministeredUnitIsInvalid` — MQE0449

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination administered unit is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredUnitIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `VaccinationAdministeredUnitIsMissing` — MQE0272

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination administered unit is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredUnitIsValid` — No value found, no data sent, nothing to analyze.

### `VaccinationAdministeredUnitIsUnrecognized` — MQE0450

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination administered unit is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredUnitIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `VaccinationAdministeredUnitIsPresent` — MQE0715

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination administered unit is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredUnitIsValid` — Value found, something sent, may be valid or invalid

## Body Route (RXR-1)

### `VaccinationBodyRouteIsDeprecated` — MQE0273

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination body route is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationBodyRouteAndSiteAreValid` — Deprecated code value.

### `VaccinationBodyRouteIsIgnored` — MQE0274

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination body route is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationBodyRouteIsInvalid` — MQE0275

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination body route is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationBodyRouteAndSiteAreValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `VaccinationBodyRouteIsInvalidForVaccineIndicated` — MQE0276

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination body route is invalid for vaccine indicated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationBodyRouteIsMissing` — MQE0277

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination body route is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationBodyRouteAndSiteAreValid` — No value found, no data sent, nothing to analyze.

### `VaccinationBodyRouteIsUnrecognized` — MQE0278

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination body route is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationBodyRouteAndSiteAreValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `VaccinationBodyRouteIsPresent` — MQE0716

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination body route is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationBodyRouteAndSiteAreValid` — Value found, something sent, may be valid or invalid

## Body Site (RXR-2)

### `VaccinationBodySiteIsDeprecated` — MQE0279

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination body site is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationBodyRouteAndSiteAreValid` — Deprecated code value.

### `VaccinationBodySiteIsIgnored` — MQE0280

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination body site is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationBodySiteIsInvalid` — MQE0281

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination body site is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationBodyRouteAndSiteAreValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `VaccinationBodySiteIsInvalidForVaccineIndicated` — MQE0282

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination body site is invalid for vaccine indicated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationBodySiteIsMissing` — MQE0283

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination body site is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationBodyRouteAndSiteAreValid` — No value found, no data sent, nothing to analyze.

### `VaccinationBodySiteIsUnrecognized` — MQE0284

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination body site is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationBodyRouteAndSiteAreValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `VaccinationBodySiteIsPresent` — MQE0717

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination body site is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationBodyRouteAndSiteAreValid` — Value found, something sent, may be valid or invalid

## Completion Status (RXA-20)

### `VaccinationCompletionStatusIsDeprecated` — MQE0285

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination completion status is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCompletionStatusIsValid` — Deprecated code value.

### `VaccinationCompletionStatusIsIgnored` — MQE0286

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination completion status is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationCompletionStatusIsInvalid` — MQE0287

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination completion status is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCompletionStatusIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `VaccinationCompletionStatusIsMissing` — MQE0288

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination completion status is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCompletionStatusIsValid` — No value found, no data sent, nothing to analyze.

### `VaccinationCompletionStatusIsUnrecognized` — MQE0289

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination completion status is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCompletionStatusIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `VaccinationCompletionStatusIsValuedAsCompleted` — MQE0290

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination completion status is valued as completed
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCompletionStatusIsValid` — Vaccination Completion Code has value of CP

### `VaccinationCompletionStatusIsValuedAsNotAdministered` — MQE0291

- **Severity:** Info
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination completion status is valued as not administered
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCompletionStatusIsValid` — Vaccination Completion Code has value of NA

### `VaccinationCompletionStatusIsValuedAsPartiallyAdministered` — MQE0292

- **Severity:** Info
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination completion status is valued as partially administered
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCompletionStatusIsValid` — Vaccination Completion Code has value of PA

### `VaccinationCompletionStatusIsValuedAsRefused` — MQE0293

- **Severity:** Info
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination completion status is valued as refused
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCompletionStatusIsValid` — Vaccination Completion Code has value of RE

### `VaccinationCompletionStatusIsPresent` — MQE0718

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination completion status is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCompletionStatusIsValid` — Value found, something sent, may be valid or invalid

## Confidentiality Code (ORC-28)

### `VaccinationConfidentialityCodeIsDeprecated` — MQE0294

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination confidentiality code is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationConfidentialityCodeIsValid` — Deprecated code value.

### `VaccinationConfidentialityCodeIsIgnored` — MQE0295

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination confidentiality code is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationConfidentialityCodeIsInvalid` — MQE0296

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination confidentiality code is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationConfidentialityCodeIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `VaccinationConfidentialityCodeIsMissing` — MQE0297

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination confidentiality code is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationConfidentialityCodeIsValid` — No value found, no data sent, nothing to analyze.

### `VaccinationConfidentialityCodeIsUnrecognized` — MQE0298

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination confidentiality code is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationConfidentialityCodeIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `VaccinationConfidentialityCodeIsValuedAsRestricted` — MQE0299

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination confidentiality code is valued as restricted
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationConfidentialityCodeIsValid` — Vaccination Confidentiality Code has value of 'R' or 'V'.

### `VaccinationConfidentialityCodeIsPresent` — MQE0719

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination confidentiality code is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationConfidentialityCodeIsValid` — Value found, something sent, may be valid or invalid

## Cpt Code (RXA-5)

### `VaccinationCptCodeIsDeprecated` — MQE0300

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination CPT code is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminCodeCptIsValid` — Deprecated code value.
  - `VaccinationCptIsValid` — Deprecated code value.

### `VaccinationCptCodeIsIgnored` — MQE0301

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination CPT code is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationCptCodeIsInvalid` — MQE0302

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination CPT code is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminCodeCptIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.
  - `VaccinationCptIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `VaccinationCptCodeIsInvalidForDateAdministered` — MQE0489

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination CPT code is invalid for date administered
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCptIsValid` — Vaccination CPT Code is outside of expected vaccine date range for the cvx it maps to.

### `VaccinationCptCodeIsMissing` — MQE0303

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination CPT code is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminCodeCptIsValid` — No value found, no data sent, nothing to analyze.
  - `VaccinationCptIsValid` — No value found, no data sent, nothing to analyze.

### `VaccinationCptCodeIsUnexpectedForDateAdministered` — MQE0488

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination CPT code is unexpected for date administered
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCptIsValid` — Vaccination CPT Code is outside of licensed vaccine date range for the cvx it maps to.

### `VaccinationCptCodeIsUnrecognized` — MQE0304

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination CPT code is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminCodeCptIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.
  - `VaccinationCptIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `VaccinationCptCodeIsPresent` — MQE0720

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination CPT code is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminCodeCptIsValid` — Value found, something sent, may be valid or invalid

## Cvx Code (RXA-5)

### `VaccinationCvxCodeIsDeprecated` — MQE0305

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination CVX code is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCvxIsValid` — Deprecated code value.

### `VaccinationCvxCodeIsIgnored` — MQE0306

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination CVX code is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationCvxCodeIsInvalid` — MQE0307

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination CVX code is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCvxIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `VaccinationCvxCodeIsInvalidForDateAdministered` — MQE0487

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination CVX code is invalid for date administered
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationCvxCodeIsMissing` — MQE0308

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination CVX code is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCvxIsValid` — No value found, no data sent, nothing to analyze.

### `VaccinationCvxCodeIsUnexpectedForDateAdministered` — MQE0486

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination CVX code is unexpected for date administered
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationCvxCodeIsUnrecognized` — MQE0309

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination CVX code is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCvxIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `VaccinationCvxCodeIsPresent` — MQE0721

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination CVX code is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCvxIsValid` — Value found, something sent, may be valid or invalid

## Cvx Code And Cpt Code (RXA-5)

### `VaccinationCvxCodeAndCptCodeAreInconsistent` — MQE0310

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination CVX code and CPT code are inconsistent
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCodeGroupsMatch` — The Vaccination CPT code given is expecting a different vaccine group than the vaccine group from the CVX given.

## Facility Id (RXA-11.4)

### `VaccinationFacilityIdIsDeprecated` — MQE0311

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination facility id is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationFacilityIdIsIgnored` — MQE0312

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination facility id is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationFacilityIdIsInvalid` — MQE0313

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination facility id is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationFacilityIdIsMissing` — MQE0314

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination facility id is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationFacilityIdIsUnrecognized` — MQE0315

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination facility id is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationFacilityIdIsPresent` — MQE0722

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination facility id is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Facility Name (RXA-11.4)

### `VaccinationFacilityNameIsMissing` — MQE0316

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination facility name is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredRequiredFieldsArePresent` — No value found, no data sent, nothing to analyze.

### `VaccinationFacilityNameIsPresent` — MQE0723

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination facility name is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredRequiredFieldsArePresent` — Value found, something sent, may be valid or invalid

## Filler Order Number (ORC-3)

### `VaccinationFillerOrderNumberIsDeprecated` — MQE0379

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination filler order number is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationFillerOrderNumberIsIgnored` — MQE0380

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination filler order number is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationFillerOrderNumberIsInvalid` — MQE0381

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination filler order number is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationFillerOrderNumberIsMissing` — MQE0382

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination filler order number is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationFillerOrderNumberIsUnrecognized` — MQE0383

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination filler order number is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationFillerOrderNumberIsPresent` — MQE0724

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination filler order number is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Financial Eligibility Code (OBX-5)

### `VaccinationFinancialEligibilityCodeIsDeprecated` — MQE0465

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination financial eligibility code is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationFinancialEligibilityCodeIsValid` — Deprecated code value.

### `VaccinationFinancialEligibilityCodeIsIgnored` — MQE0466

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination financial eligibility code is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationFinancialEligibilityCodeIsInvalid` — MQE0467

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination financial eligibility code is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationFinancialEligibilityCodeIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `VaccinationFinancialEligibilityCodeIsMissing` — MQE0468

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination financial eligibility code is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationFinancialEligibilityCodeIsValid` — No value found, no data sent, nothing to analyze.

### `VaccinationFinancialEligibilityCodeIsUnrecognized` — MQE0469

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination financial eligibility code is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationFinancialEligibilityCodeIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `VaccinationFinancialEligibilityCodeIsPresent` — MQE0725

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination financial eligibility code is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationFinancialEligibilityCodeIsValid` — Value found, something sent, may be valid or invalid

## Funding Source Code (OBX-5)

### `VaccinationFundingSourceCodeIsDeprecated` — MQE0583

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination funding source code is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationFundingSourceCodeIsValid` — Deprecated code value.

### `VaccinationFundingSourceCodeIsIgnored` — MQE0584

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination funding source code is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationFundingSourceCodeIsInvalid` — MQE0585

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination funding source code is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationFundingSourceCodeIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `VaccinationFundingSourceCodeIsMissing` — MQE0586

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination funding source code is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationFundingSourceCodeIsValid` — No value found, no data sent, nothing to analyze.

### `VaccinationFundingSourceCodeIsUnrecognized` — MQE0587

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination funding source code is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationFundingSourceCodeIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `VaccinationFundingSourceCodeIsUnexpectedForFinancialEligibility` — MQE0596

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination funding source code is unexpected for financial eligibility
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationFundingAndEligibilityConflict` — The financial funding source given is unexpected for the financial eligibility given.

### `VaccinationFundingSourceCodeIsPresent` — MQE0726

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination funding source code is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationFundingSourceCodeIsValid` — Value found, something sent, may be valid or invalid

## Given By (RXA-10)

### `VaccinationGivenByIsDeprecated` — MQE0317

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination given by is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationGivenByIsIgnored` — MQE0318

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination given by is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationGivenByIsInvalid` — MQE0319

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination given by is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationGivenByIsMissing` — MQE0320

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination given by is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationGivenByIsUnrecognized` — MQE0321

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination given by is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationGivenByIsPresent` — MQE0727

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination given by is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Id (ORC-3)

### `VaccinationIdIsMissing` — MQE0322

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination id is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationIdIsPresent` — MQE0728

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination id is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Id Of Receiver (ORC-2)

### `VaccinationIdOfReceiverIsMissing` — MQE0323

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination id of receiver is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationIdOfReceiverIsUnrecognized` — MQE0324

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination id of receiver is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationIdOfReceiverIsPresent` — MQE0729

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination id of receiver is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Id Of Sender (ORC-3)

### `VaccinationIdOfSenderIsMissing` — MQE0325

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination id of sender is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationIdOfSenderIsUnrecognized` — MQE0326

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination id of sender is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationIdOfSenderIsPresent` — MQE0730

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination id of sender is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Information Source (RXA-9)

### `VaccinationInformationSourceIsAdministeredButAppearsToHistorical` — MQE0327

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination information source is administered but appears to historical
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationSourceIsHistoricalButAppearsAdministered` — Vaccination information source is reported as administered, but based on our scoring calculation (how recently shot was given and how much data is known about the shot) the shot seems to be historical.

### `VaccinationInformationSourceIsDeprecated` — MQE0328

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination information source is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationInformationSourceIsValid` — Deprecated code value.

### `VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered` — MQE0329

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination information source is historical but appears to be administered
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationSourceIsAdministeredButAppearsHistorical` — Vaccination information source is reported as historical but based on our scoring calculation (how recently shot was given and how much data is known about the shot) the shot seems to be administered.

### `VaccinationInformationSourceIsIgnored` — MQE0330

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination information source is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationInformationSourceIsInvalid` — MQE0331

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination information source is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationInformationSourceIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `VaccinationInformationSourceIsMissing` — MQE0332

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination information source is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationInformationSourceIsValid` — No value found, no data sent, nothing to analyze.

### `VaccinationInformationSourceIsUnrecognized` — MQE0333

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination information source is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationInformationSourceIsValid` — Coded value is not recognized as either valid or invalid because it is unknown to this system.

### `VaccinationInformationSourceIsValuedAsAdministered` — MQE0334

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination information source is valued as administered
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationInformationSourceIsValid`

### `VaccinationInformationSourceIsValuedAsHistorical` — MQE0335

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination information source is valued as historical
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationInformationSourceIsValid`

### `VaccinationInformationSourceIsPresent` — MQE0731

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination information source is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationInformationSourceIsValid` — Value found, something sent, may be valid or invalid

## Lot Expiration Date (RXA-16)

### `VaccinationLotExpirationDateIsInvalid` — MQE0336

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination lot expiration date is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredRequiredFieldsArePresent` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `VaccinationLotExpirationDateIsMissing` — MQE0337

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination lot expiration date is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredRequiredFieldsArePresent` — No value found, no data sent, nothing to analyze.

### `VaccinationLotExpirationDateIsPresent` — MQE0732

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination lot expiration date is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredRequiredFieldsArePresent` — Value found, something sent, may be valid or invalid

## Lot Number (RXA-15)

### `VaccinationLotNumberFormatIsUnrecognized` — MQE0590

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination lot number is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredLotNumberIsValid` — Vaccination lot number doesn't match the expected format specified by the manufacturer code.

### `VaccinationLotNumberHasMultiple` — MQE0591

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination lot number has multiples
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredLotNumberIsValid` — Vaccination lot number has multiple lot numbers.

### `VaccinationLotNumberHasInvalidPrefixes` — MQE0592

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination lot number has invalid prefixes
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredLotNumberIsValid` — Vaccination lot number cannot start with LOT, (P), MED, SKB, LOT, PMC, WSD, WAL

### `VaccinationLotNumberHasInvalidSuffixes` — MQE0593

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination lot number has invalid suffixes
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredLotNumberIsValid` — Vaccination lot number cannot end with (P), -P, -S, -C, -H, -V, *, #, (S), (P),
        MSD, HSP, SELECT, CP, VFC, STATE, CHIP, ADULT, ST-, PRIVATE, PED,
        UNINSURED, SPECIAL, OVER19, VMC, -COUNT, REAR, PENT, PENTACEL, DTAP,
        IPV, ACTH, HIB, PFF, FLU, BOOST, HAV, GARDASIL, ROTATEQ, PEDVAX,
        VARIVAX, PNEU, PNEUMOVAX, MMR, MENVEO, MENACTRA, FLU ZONE

### `VaccinationLotNumberHasInvalidInfixes` — MQE0594

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination lot number has invalid infixes
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredLotNumberIsValid` — Vaccination lot number cannot contain the text ICE3

### `VaccinationLotNumberIsTooShort` — MQE0595

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination lot number is too short
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredLotNumberIsValid` — Vaccination lot number is 4 characters or less.

### `VaccinationLotNumberIsInvalid` — MQE0338

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination lot number is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredLotNumberIsValid` — Vaccination lot number must be comprised of alphanumeric characters and/or the '-'. All other characters are invalid.

### `VaccinationLotNumberIsMissing` — MQE0339

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination lot number is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredLotNumberIsPresent` — No value found, no data sent, nothing to analyze.

### `VaccinationLotNumberIsPresent` — MQE0733

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination lot number is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdministeredLotNumberIsPresent` — Value found, something sent, may be valid or invalid

## Manufacturer Code (RXA-17)

### `VaccinationManufacturerCodeIsDeprecated` — MQE0340

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination manufacturer code is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationMfrIsValid` — Deprecated code value.
  - `VaccinationMfrIsValidForAdministered` — Deprecated code value.
  - `VaccinationMfrIsValidForHistorical` — Deprecated code value.

### `VaccinationManufacturerCodeIsIgnored` — MQE0341

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination manufacturer code is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationManufacturerCodeIsInvalid` — MQE0342

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination manufacturer code is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationMfrIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.
  - `VaccinationMfrIsValidForAdministered` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.
  - `VaccinationMfrIsValidForHistorical` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `VaccinationManufacturerCodeIsInvalidForDateAdministered` — MQE0495

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination manufacturer code is invalid for date administered
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationMfrIsValid` — Vaccination Manufacturer code was used outside of the valid date range defined for this code.
  - `VaccinationMfrIsValidForAdministered` — Vaccination Manufacturer code was used outside of the valid date range defined for this code.
  - `VaccinationMfrIsValidForHistorical` — Vaccination Manufacturer code was used outside of the valid date range defined for this code.

### `VaccinationManufacturerCodeIsMissing` — MQE0343

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination manufacturer code is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationMfrIsValid` — No value found, no data sent, nothing to analyze.
  - `VaccinationMfrIsValidForAdministered` — No value found, no data sent, nothing to analyze.
  - `VaccinationMfrIsValidForHistorical` — No value found, no data sent, nothing to analyze.

### `VaccinationManufacturerCodeIsUnexpectedForDateAdministered` — MQE0494

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination manufacturer code is unexpected for date administered
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationMfrIsValid` — Vaccination Manufacturer code was used outside of the expected date range defined for this code.
  - `VaccinationMfrIsValidForAdministered` — Vaccination Manufacturer code was used outside of the expected date range defined for this code.
  - `VaccinationMfrIsValidForHistorical` — Vaccination Manufacturer code was used outside of the expected date range defined for this code.

### `VaccinationManufacturerCodeIsUnrecognized` — MQE0344

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination manufacturer code is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationMfrIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.
  - `VaccinationMfrIsValidForAdministered` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.
  - `VaccinationMfrIsValidForHistorical` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `VaccinationManufacturerCodeIsPresent` — MQE0734

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination manufacturer code is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationMfrIsValid` — Value found, something sent, may be valid or invalid
  - `VaccinationMfrIsValidForAdministered` — Value found, something sent, may be valid or invalid
  - `VaccinationMfrIsValidForHistorical` — Value found, something sent, may be valid or invalid

## Ndc Code (RXA-5)

### `VaccinationNDCCodeIsUnrecognized` — MQE0559

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination NDC code is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationNdcIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `VaccinationNDCCodeIsMissing` — MQE0560

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination NDC code is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationNdcIsValid` — No value found, no data sent, nothing to analyze.

### `VaccinationNDCCodeIsPresent` — MQE0735

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination NDC code is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationNdcIsValid` — Value found, something sent, may be valid or invalid

## Order Control Code (ORC-1)

### `VaccinationOrderControlCodeIsDeprecated` — MQE0373

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination order control code is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationOrderControlCodeIsIgnored` — MQE0369

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination order control code is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationOrderControlCodeIsInvalid` — MQE0370

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination order control code is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationOrderControlCodeIsMissing` — MQE0371

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination order control code is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationOrderControlCodeIsUnrecognized` — MQE0372

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination order control code is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationOrderControlCodeIsPresent` — MQE0736

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination order control code is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Order Facility Id (ORC-21)

### `VaccinationOrderFacilityIdIsDeprecated` — MQE0442

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination order facility id is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationOrderFacilityIdIsIgnored` — MQE0443

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination order facility id is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationOrderFacilityIdIsInvalid` — MQE0444

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination order facility id is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationOrderFacilityIdIsMissing` — MQE0445

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination order facility id is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationOrderFacilityIdIsUnrecognized` — MQE0446

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination order facility id is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationOrderFacilityIdIsPresent` — MQE0738

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination order facility id is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Order Facility Name (ORC-21)

### `VaccinationOrderFacilityNameIsMissing` — MQE0441

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination order facility name is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationOrderFacilityNameIsPresent` — MQE0739

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination order facility name is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Ordered By (XCN-12)

### `VaccinationOrderedByIsDeprecated` — MQE0345

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination ordered by is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationOrdererIsValid` — Deprecated code value.

### `VaccinationOrderedByIsIgnored` — MQE0346

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination ordered by is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationOrderedByIsInvalid` — MQE0347

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination ordered by is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationOrdererIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `VaccinationOrderedByIsMissing` — MQE0348

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination ordered by is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationOrdererIsValid` — No value found, no data sent, nothing to analyze.

### `VaccinationOrderedByIsUnrecognized` — MQE0349

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination ordered by is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationOrdererIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `VaccinationOrderedByIsPresent` — MQE0737

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination ordered by is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationOrdererIsValid` — Value found, something sent, may be valid or invalid

## Placer Order Number (ORC-2)

### `VaccinationPlacerOrderNumberIsDeprecated` — MQE0384

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination placer order number is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationPlacerOrderNumberIsIgnored` — MQE0385

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination placer order number is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationPlacerOrderNumberIsInvalid` — MQE0386

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination placer order number is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationPlacerOrderNumberIsMissing` — MQE0387

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination placer order number is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationPlacerOrderNumberIsUnrecognized` — MQE0388

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination placer order number is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationPlacerOrderNumberIsPresent` — MQE0740

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination placer order number is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Product (RXA-5)

### `VaccinationProductIsDeprecated` — MQE0350

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination product is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationProductIsInvalid` — MQE0351

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination product is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationProductIsInvalidForDateAdministered` — MQE0493

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination product is invalid for date administered
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationProductIsValid` — Vaccination product was used outside of the valid date range defined for this product.

### `VaccinationProductIsMissing` — MQE0352

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination product is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationProductIsValid` — Vaccination product is missing.

### `VaccinationProductIsUnexpectedForDateAdministered` — MQE0492

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination product is unexpected for date administered
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationProductIsValid` — Vaccination product was used outside of the expected date range defined for this product.

### `VaccinationProductIsUnrecognized` — MQE0353

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination product is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationProductIsPresent` — MQE0741

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination product is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationProductIsValid` — Vaccination product is not missing.

## Recorded By (ORC-10)

### `VaccinationRecordedByIsDeprecated` — MQE0354

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination recorded by is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationOrdererIsValid` — Deprecated code value.

### `VaccinationRecordedByIsIgnored` — MQE0355

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination recorded by is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationRecordedByIsInvalid` — MQE0356

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination recorded by is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationOrdererIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `VaccinationRecordedByIsMissing` — MQE0357

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination recorded by is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationOrdererIsValid` — No value found, no data sent, nothing to analyze.

### `VaccinationRecordedByIsUnrecognized` — MQE0358

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination recorded by is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationOrdererIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `VaccinationRecordedByIsPresent` — MQE0742

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination recorded by is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationOrdererIsValid` — Value found, something sent, may be valid or invalid

## Refusal Reason (RXA-18)

### `VaccinationRefusalReasonConflictsCompletionStatus` — MQE0359

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination refusal reason conflicts completion status
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationRefusalReasonIsValid` — Vaccination is marked as completed but refusal code was given.

### `VaccinationRefusalReasonIsDeprecated` — MQE0360

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination refusal reason is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationRefusalReasonIsIgnored` — MQE0361

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination refusal reason is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationRefusalReasonIsInvalid` — MQE0362

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination refusal reason is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationRefusalReasonIsMissing` — MQE0363

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination refusal reason is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationRefusalReasonIsValid` — Vaccination completion was refused but refusal code is missing.

### `VaccinationRefusalReasonIsUnrecognized` — MQE0364

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination refusal reason is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationRefusalReasonIsPresent` — MQE0743

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination refusal reason is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationRefusalReasonIsValid` — Vaccination completion was refused and refusal code is indicated.

## System Entry Time (RXA-22)

### `VaccinationSystemEntryDateIsMissing` — MQE0573

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination system entry time is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCreationDateIsValid` — No value found, no data sent, nothing to analyze.

### `VaccinationSystemEntryDateIsInTheFuture` — MQE0581

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination system entry time is in future
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCreationDateIsValid` — Vaccination System Entry date is in the future or is after message recieved date.

### `VaccinationSystemEntryDateIsInvalid` — MQE0574

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination system entry time is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCreationDateIsValid` — Vaccination System Entry date cannot be translated to a date.

### `VaccinationCreationIsOnTime` — MQE0569

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination system entry time is on time
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCreationTimeliness` — Vaccination Administered Date and System Entry Date less than or equal to 3 days of each other.

### `VaccinationCreationIsLate` — MQE0570

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination system entry time is late
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCreationTimeliness` — Vaccination Administered Date and System Entry Date are are more than 3 days but less than or equal to 14 days apart.

### `VaccinationCreationIsVeryLate` — MQE0571

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination system entry time is very late
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCreationTimeliness` — Vaccination Administered Date and System Entry Date are more than 14 days but less than or equal to 30 days apart.

### `VaccinationCreationIsTooLate` — MQE0572

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination system entry time is too late
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCreationTimeliness` — Vaccination Administered Date and System Entry Date are over 30 days apart.

### `VaccinationSystemEntryDateIsPresent` — MQE0744

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination system entry time is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationCreationDateIsValid` — Value found, something sent, may be valid or invalid

## Vis (RXA-9)

### `VaccinationVisIsMissing` — MQE0542

- **Severity:** Info
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination VIS is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationVisIsPresent` — Administered Vaccine is missing VIS or VIS is missing a document code, CVX and Published Date.
  - `VaccinationVisIsRecognized` — Vaccination Vis is missing a document code, CVX and Published Date.

### `VaccinationVisIsUnrecognized` — MQE0543

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationVisIsPresent` — MQE0748

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination VIS is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationVisIsPresent` — Administered Vaccine has VIS document code, CVX and Published Date.
  - `VaccinationVisIsRecognized` — Vaccination Vis is indicated with a document code, CVX and Published Date.

## Vis Cvx Code

### `VaccinationVisCvxIsDeprecated` — MQE0601

- **Severity:** Info
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination VIS CVX Code is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationVisCvxIsValid` — Deprecated code value.

### `VaccinationVisCvxIsIgnored` — MQE0602

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS CVX Code is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationVisCvxIsInvalid` — MQE0603

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination VIS CVX Code is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationVisCvxIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `VaccinationVisCvxIsUnrecognized` — MQE0604

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination VIS CVX Code is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationVisCvxIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `VaccinationVisCvxIsMissing` — MQE0605

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination VIS CVX Code is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationVisCvxIsValid` — No value found, no data sent, nothing to analyze.

### `VaccinationVisCvxIsPresent` — MQE0745

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination VIS CVX Code is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationVisCvxIsValid` — Value found, something sent, may be valid or invalid

## Vis Delivery Date

### `VaccinationVisDeliveryDateIsInvalid` — MQE0507

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS Delivery Date is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationVisDeliveryDateIsMissing` — MQE0508

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS Delivery Date is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationVisDeliveryDateIsNotAdminDate` — MQE0509

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS Delivery Date is not admin date
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationVisDeliveryDateIsBeforeVersionDate` — MQE0510

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS Delivery Date is before version date
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationVisDeliveryDateIsAfterAdminDate` — MQE0511

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS Delivery Date is after admin date
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationVisDeliveryDateIsPresent` — MQE0746

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS Delivery Date is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Vis Document Type

### `VaccinationVisDocumentTypeIsDeprecated` — MQE0496

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS document type is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationVisDocumentTypeIsIgnored` — MQE0497

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS document type is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationVisDocumentTypeIsIncorrect` — MQE0498

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS document type is incorrect
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationVisDocumentTypeIsInvalid` — MQE0499

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS document type is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationVisDocumentTypeIsMissing` — MQE0500

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS document type is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationVisDocumentTypeIsUnrecognized` — MQE0501

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS document type is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationVisDocumentTypeIsOutOfDate` — MQE0502

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS document type is out-of-date
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationVisDocumentTypeIsPresent` — MQE0747

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS document type is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Vis Presented Date

### `VaccinationVisPresentedDateIsInvalid` — MQE0537

- **Severity:** Info
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination VIS presented date is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationVisDatesAreValid` — Vaccination Vis Presented date cannot be translated to a date.

### `VaccinationVisPresentedDateIsMissing` — MQE0538

- **Severity:** Info
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination VIS presented date is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationVisDatesAreValid` — No value found, no data sent, nothing to analyze.

### `VaccinationVisPresentedDateIsNotAdminDate` — MQE0539

- **Severity:** Info
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination VIS presented date is not admin date
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationVisDatesAreValid` — Vaccination Vis Presented date should be the same as the Vaccination Administered Date.

### `VaccinationVisPresentedDateIsBeforePublishedDate` — MQE0540

- **Severity:** Info
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination VIS presented date is before published date
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationVisDatesAreValid` — Vaccination Vis Presented date cannot be earlier than Vis Published date.

### `VaccinationVisPresentedDateIsAfterAdminDate` — MQE0541

- **Severity:** Info
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination VIS presented date is after admin date
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationVisDatesAreValid` — Vaccination Vis Presented date cannot be after Vaccination Administered Date.

### `VaccinationVisPresentedDateIsPresent` — MQE0749

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination VIS presented date is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationVisDatesAreValid` — Value found, something sent, may be valid or invalid

## Vis Published Date

### `VaccinationVisPublishedDateIsMissing` — MQE0534

- **Severity:** Info
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination VIS published date is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationVisDatesAreValid` — No value found, no data sent, nothing to analyze.

### `VaccinationVisPublishedDateIsUnrecognized` — MQE0535

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS published date is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationVisPublishedDateIsInFuture` — MQE0536

- **Severity:** Info
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination VIS published date is in future
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationVisDatesAreValid` — Vaccination Vis Publication date cannot be a future date.

### `VaccinationVisPublishedDateIsInvalid` — MQE0544

- **Severity:** Info
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination VIS published date is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationVisDatesAreValid` — Vaccination Vis Publication date cannot be translated to a date.

### `VaccinationVisPublishedDateIsPresent` — MQE0750

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Vaccination VIS published date is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationVisDatesAreValid` — Value found, something sent, may be valid or invalid

## Vis Version Date

### `VaccinationVisVersionDateIsInvalid` — MQE0503

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS Version Date is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationVisVersionDateIsMissing` — MQE0504

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS Version Date is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationVisVersionDateIsUnrecognized` — MQE0505

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS Version Date is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationVisVersionDateIsInFuture` — MQE0506

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS Version Date is in future
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `VaccinationVisVersionDateIsPresent` — MQE0751

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Vaccination VIS Version Date is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

