<!-- GENERATED FILE. Do not hand edit.
     Regenerate with DetectionMarkdownGenerator (see docs/detections/README.md). -->

# Patient detections

[Back to index](index.md)

## Address (PID-11)

### `PatientAddressIsMissing` — MQE0092

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address is missing
- **What this means:** The patient record does not include any address information at all.
- **Implemented by:**
  - `PatientAddressIsValid` — Patient Address was not indicated

### `PatientAddressIsInvalid` — MQE0562

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address is invalid
- **What this means:** The patient's address was submitted but does not resolve to a real, deliverable address when checked against an address-verification service (e.g. Smarty Streets).
- **Implemented by:**
  - `PatientAddressIsValid` — Only runs when the address cleanser is enabled (props.isAddressCleanserEnabled()). Sends the patient address to the SmartyStreets address-verification service; if the returned cleansing result is not marked clean, this detection fires and any DPV (Delivery Point Validation) codes returned are appended to the detection message.
    - *Why it matters:* An address that doesn't verify against USPS data may be undeliverable, which affects the registry's ability to reach the patient/guardian by mail (e.g. reminder/recall notices).
    - *How to fix:* Review the submitted street, city, state, and zip for typos or an incomplete/PO-box-only address, and correct the source record if the address is wrong. If the address is correct but still fails verification (e.g. a new development not yet in USPS data), no correction is possible on the MQE side.
  - `PatientAddressIsValid` — Patient Address is not recognized by the address checker as a valid address

### `PatientAddressIsPresent` — MQE0647

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address is present
- **What this means:** Companion signal to PatientAddressIsMissing: the patient record includes address information.
- **Implemented by:**
  - `PatientAddressIsValid` — Patient Address was indicated

## Address City (PID-11.3)

### `PatientAddressCityIsInvalid` — MQE0093

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient address city is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientAddressCityIsMissing` — MQE0094

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address city is missing
- **What this means:** The patient's address is present, but no city value was provided.
- **Implemented by:**
  - `PatientAddressIsValid` — No value found, no data sent, nothing to analyze.

### `PatientAddressCityIsPresent` — MQE0644

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address city is present
- **What this means:** Companion signal to PatientAddressCityIsMissing: the patient's address city value was populated.
- **Implemented by:**
  - `PatientAddressIsValid` — Value found, something sent, may be valid or invalid

## Address Country (PID-11.6)

### `PatientAddressCountryIsDeprecated` — MQE0095

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient address country is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientAddressCountryIsIgnored` — MQE0096

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient address country is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientAddressCountryIsInvalid` — MQE0097

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient address country is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientAddressCountryIsMissing` — MQE0098

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address country is missing
- **What this means:** The patient's address is present, but no country value was provided.
- **Implemented by:**
  - `PatientAddressIsValid` — No value found, no data sent, nothing to analyze.

### `PatientAddressCountryIsUnrecognized` — MQE0099

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient address country is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientAddressCountryIsPresent` — MQE0645

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address country is present
- **What this means:** Companion signal to PatientAddressCountryIsMissing: the patient's address country value was populated.
- **Implemented by:**
  - `PatientAddressIsValid` — Value found, something sent, may be valid or invalid

## Address County (PID-11.9)

### `PatientAddressCountyIsDeprecated` — MQE0100

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient address county is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientAddressCountyIsIgnored` — MQE0101

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient address county is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientAddressCountyIsInvalid` — MQE0102

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient address county is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientAddressCountyIsMissing` — MQE0103

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address county is missing
- **What this means:** The patient's address is present, but no county value was provided.
- **Implemented by:**
  - `PatientAddressIsValid` — No value found, no data sent, nothing to analyze.

### `PatientAddressCountyIsUnrecognized` — MQE0104

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient address county is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientAddressCountyIsPresent` — MQE0646

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address county is present
- **What this means:** Companion signal to PatientAddressCountyIsMissing: the patient's address county value was populated.
- **Implemented by:**
  - `PatientAddressIsValid` — Value found, something sent, may be valid or invalid

## Address State (PID-11.4)

### `PatientAddressStateIsDeprecated` — MQE0105

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient address state is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientAddressStateIsIgnored` — MQE0106

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient address state is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientAddressStateIsInvalid` — MQE0107

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient address state is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientAddressStateIsMissing` — MQE0108

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address state is missing
- **What this means:** The patient's address is present, but no state value was provided.
- **Implemented by:**
  - `PatientAddressIsValid` — No value found, no data sent, nothing to analyze.

### `PatientAddressStateIsUnrecognized` — MQE0109

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient address state is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientAddressStateIsPresent` — MQE0648

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address state is present
- **What this means:** Companion signal to PatientAddressStateIsMissing: the patient's address state value was populated.
- **Implemented by:**
  - `PatientAddressIsValid` — Value found, something sent, may be valid or invalid

## Address Street (PID-11.1)

### `PatientAddressStreetIsMissing` — MQE0110

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address street is missing
- **What this means:** The patient's address is present, but no street value was provided.
- **Implemented by:**
  - `PatientAddressIsValid` — Patient Address street is not indicated

### `PatientAddressStreetIsPresent` — MQE0650

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address street is present
- **What this means:** Companion signal to PatientAddressStreetIsMissing: the patient's address street value was populated.
- **Implemented by:**
  - `PatientAddressIsValid` — Patient Address street is indicated

## Address Street2 (PID-11.2)

### `PatientAddressStreet2IsMissing` — MQE0111

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient address street2 is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientAddressStreet2IsPresent` — MQE0649

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient address street2 is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Address Type (NK1-4.7)

### `PatientGuardianAddressTypeIsValuedBadAddress` — MQE0597

- **Severity:** Info
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address type is valued bad address
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinGuardianAddressIsValid` — Address Type indicates the address is a bad address.

### `PatientGuardianAddressTypeIsMissing` — MQE0598

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address type is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinGuardianAddressIsValid` — No value found, no data sent, nothing to analyze.

### `PatientGuardianAddressTypeIsUnrecognized` — MQE0600

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address type is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinGuardianAddressIsValid` — Coded value is not recognized as either valid or invalid because it is unknown to this system.

### `PatientGuardianAddressTypeIsPresent` — MQE0672

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient address type is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Address Type (PID-11.7)

### `PatientAddressTypeIsMissing` — MQE0451

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address type is missing
- **What this means:** The patient's address is present, but no address type code (e.g. home, mailing) was provided.
- **Implemented by:**
  - `PatientAddressIsValid` — No value found, no data sent, nothing to analyze.

### `PatientAddressTypeIsDeprecated` — MQE0517

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient address type is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientAddressTypeIsIgnored` — MQE0518

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient address type is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientAddressTypeIsInvalid` — MQE0519

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient address type is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientAddressTypeIsUnrecognized` — MQE0520

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address type is unrecognized
- **What this means:** The patient's address type code was submitted, but the value is not one this system recognizes as either a valid or invalid code (unknown/unsupported code value).
- **Implemented by:**
  - `PatientAddressIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `PatientAddressTypeIsValuedBadAddress` — MQE0521

- **Severity:** Info
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address type is valued bad address
- **What this means:** The patient's address type code was submitted as 'BA' (Bad Address), meaning the sender has already flagged this address as undeliverable or invalid.
- **Implemented by:**
  - `PatientAddressIsValid` — Address Type indicates the address is a bad address.

### `PatientAddressTypeIsPresent` — MQE0651

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address type is present
- **What this means:** Companion signal to PatientAddressTypeIsMissing: the patient's address type code was populated.
- **Implemented by:**
  - `PatientAddressIsValid` — Value found, something sent, may be valid or invalid

## Address Zip (PID-11.5)

### `PatientAddressZipIsInvalid` — MQE0112

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient address zip is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientAddressZipIsMissing` — MQE0113

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address zip is missing
- **What this means:** The patient's address is present, but no zip/postal code value was provided.
- **Implemented by:**
  - `PatientAddressIsValid` — No value found, no data sent, nothing to analyze.

### `PatientAddressZipIsPresent` — MQE0652

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient address zip is present
- **What this means:** Companion signal to PatientAddressZipIsMissing: the patient's address zip/postal code value was populated.
- **Implemented by:**
  - `PatientAddressIsValid` — Value found, something sent, may be valid or invalid

## Alias (PID-5)

### `PatientAliasIsMissing` — MQE0114

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient alias is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientAliasIsPresent` — No value found, no data sent, nothing to analyze.

### `PatientAliasIsPresent` — MQE0653

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient alias is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientAliasIsPresent` — Patient has values for first and/or last alias names.

## Birth Date (PID-7)

### `PatientBirthDateIsAfterSubmission` — MQE0115

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient birth date is after submission
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientBirthDateIsReasonable` — Patient birth date is over 2 hours after the message header date.

### `PatientBirthDateIsOn15ThDayOfMonth` — MQE0565

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient birth date is on 15th day of month
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientBirthDateCharacteristic` — Patient birth date is on the 15th day of the month.
  - `PatientBirthDateIsReasonable` — Patient birth date is on the 15th day of the month.

### `PatientBirthDateIsOnFirstDayOfMonth` — MQE0566

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient birth date is on first day of month
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientBirthDateCharacteristic` — Patient birth date is on the first day of the month.
  - `PatientBirthDateIsReasonable` — Patient birth date is on the first day of the month.

### `PatientBirthDateIsOnLastDayOfMonth` — MQE0567

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient birth date is on last day of month
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientBirthDateCharacteristic` — Patient birth date is on the last day of the month.
  - `PatientBirthDateIsReasonable` — Patient birth date is on the last day of the month.

### `PatientBirthDateIsInvalid` — MQE0117

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient birth date is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientBirthDateIsValid` — Patient Birth date cannot be translated to a date.

### `PatientBirthDateIsMissing` — MQE0118

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient birth date is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientBirthDateIsValid` — No value found, no data sent, nothing to analyze.

### `PatientBirthDateIsUnderage` — MQE0119

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient birth date is underage
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientIsUnderage`

### `PatientBirthDateIsVeryLongAgo` — MQE0120

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient birth date is very long ago
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientBirthDateIsReasonable` — Patient is over 120 years old.

### `PatientBirthDateIsPresent` — MQE0654

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient birth date is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientBirthDateIsValid` — Value found, something sent, may be valid or invalid

## Birth Indicator (PID-24)

### `PatientBirthIndicatorIsInvalid` — MQE0121

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient birth indicator is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientMultipleBirthsValid` — Birth indicator is something other than 'Y' or 'N'.

### `PatientBirthIndicatorIsMissing` — MQE0122

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient birth indicator is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientMultipleBirthsValid` — No value found, no data sent, nothing to analyze.

### `PatientBirthIndicatorIsPresent` — MQE0655

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient birth indicator is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientMultipleBirthsValid` — Value found, something sent, may be valid or invalid

## Birth Order (PID-25)

### `PatientBirthOrderIsUnknown` — MQE0557

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient birth order is valued as unknown
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientBirthOrderIsInvalid` — MQE0123

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient birth order is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientMultipleBirthsValid` — Multiple birth indicator was sent as No but birth order was sent with value > 1.

### `PatientBirthOrderIsMissing` — MQE0124

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient birth order is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientBirthOrderIsMultipleAndMultipleBirthIndicatedIsMissing` — MQE0125

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient birth order is missing and multiple birth indicated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientMultipleBirthsValid` — Multiple birth indicator was sent as Yes but birth order was not.

### `PatientBirthOrderIsPresent` — MQE0656

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient birth order is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientBirthOrderIsMultipleAndMultipleBirthIndicatedIsPresent` — MQE0657

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient birth order is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Birth Place (PID-23)

### `PatientBirthPlaceIsMissing` — MQE0126

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient birth place is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientBirthPlaceIsValid` — No value found, no data sent, nothing to analyze.

### `PatientBirthPlaceIsPresent` — MQE0658

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient birth place is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientBirthPlaceIsValid` — Value found, something sent, may be valid or invalid

## Birth Registry Id (PID-3)

### `PatientBirthRegistryIdIsInvalid` — MQE0127

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient birth registry id is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientBirthRegistryIdIsMissing` — MQE0128

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient birth registry id is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientBirthRegistryIdIsPresent` — MQE0659

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient birth registry id is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Class (PV1-2)

### `PatientClassIsDeprecated` — MQE0374

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient class is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientClassIsIgnored` — MQE0375

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient class is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientClassIsInvalid` — MQE0376

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient class is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientClassIsMissing` — MQE0377

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient class is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientClassIsValid` — No value found, no data sent, nothing to analyze.

### `PatientClassIsUnrecognized` — MQE0378

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient class is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientClassIsValid` — Coded value is not recognized as either valid or invalid because it is unknown to this system.

### `PatientClassIsPresent` — MQE0660

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient class is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Death Date (PID-29)

### `PatientDeathDateIsBeforeBirth` — MQE0129

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient death date is before birth
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientDeathDateIsValid` — Date given is before patient birth date.

### `PatientDeathDateIsInFuture` — MQE0130

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient death date is in future
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientDeathDateIsValid` — Date given is in the future.

### `PatientDeathDateIsInvalid` — MQE0131

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient death date is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientDeathDateIsValid` — The death date cannot be translated to a date.

### `PatientDeathDateIsMissing` — MQE0132

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient death date is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientDeathDateIsValid` — The death indicator is marked as dead, but there is no death date.

### `PatientDeathDateIsPresent` — MQE0661

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient death date is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientDeathDateIsValid` — The death indicator is marked as dead, and there is a death date.

## Death Indicator (PID-30)

### `PatientDeathIndicatorIsInconsistent` — MQE0133

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient death indicator is inconsistent
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientDeathIndicatorIsValid` — Patient death indicator says not dead but death date is populated.

### `PatientDeathIndicatorIsMissing` — MQE0134

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient death indicator is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientDeathIndicatorIsValid` — Patient death date was given but death indicator is missing.

### `PatientDeathIndicatorIsPresent` — MQE0662

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient death indicator is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientDeathIndicatorIsValid` — Patient death indicator is present

## Email (PID-13.4)

### `PatientEmailIsInvalid` — MQE0588

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient email is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientEmailIsPresent` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `PatientEmailIsMissing` — MQE0589

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient email is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientEmailIsPresent` — No value found, no data sent, nothing to analyze.

### `PatientEmailIsPresent` — MQE0663

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient email is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientEmailIsPresent` — Value found, something sent, may be valid or invalid

## Ethnicity (PID-22)

### `PatientEthnicityIsDeprecated` — MQE0135

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient ethnicity is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientEthnicityIsValid` — Deprecated code value.

### `PatientEthnicityIsIgnored` — MQE0136

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient ethnicity is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientEthnicityIsInvalid` — MQE0137

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient ethnicity is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientEthnicityIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `PatientEthnicityIsMissing` — MQE0138

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient ethnicity is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientEthnicityIsValid` — No value found, no data sent, nothing to analyze.

### `PatientEthnicityIsUnrecognized` — MQE0139

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient ethnicity is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientEthnicityIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `PatientEthnicityIsPresent` — MQE0664

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient ethnicity is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientEthnicityIsValid` — Value found, something sent, may be valid or invalid

## Gender (PID-8)

### `PatientGenderIsDeprecated` — MQE0143

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient gender is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientGenderIsValid` — Deprecated code value.

### `PatientGenderIsIgnored` — MQE0144

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient gender is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientGenderIsInvalid` — MQE0145

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient gender is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientGenderIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `PatientGenderIsMissing` — MQE0146

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient gender is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientGenderIsValid` — No value found, no data sent, nothing to analyze.

### `PatientGenderIsUnrecognized` — MQE0147

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient gender is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientGenderIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `PatientGenderIsPresent` — MQE0665

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient gender is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientGenderIsValid` — Value found, something sent, may be valid or invalid

## Guardian Address (NK1-4)

### `PatientGuardianAddressIsMissing` — MQE0148

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient guardian address is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinGuardianAddressIsValid` — No value found, no data sent, nothing to analyze.

### `PatientGuardianAddressIsInvalid` — MQE0563

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient guardian address is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinGuardianAddressIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `PatientGuardianAddressIsPresent` — MQE0669

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient guardian address is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Guardian Address City (NK1-4.3)

### `PatientGuardianAddressCityIsMissing` — MQE0149

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient guardian address city is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinGuardianAddressIsValid` — No value found, no data sent, nothing to analyze.
  - `PatientResponsiblePartyIsProperlyFormed` — No value found, no data sent, nothing to analyze.

### `PatientGuardianAddressCityIsPresent` — MQE0666

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient guardian address city is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientResponsiblePartyIsProperlyFormed` — Value found, something sent, may be valid or invalid

## Guardian Address County (NK1-4.9)

### `PatientGuardianAddressCountyIsMissing` — MQE0608

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient guardian address county is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinGuardianAddressIsValid` — No value found, no data sent, nothing to analyze.

### `PatientGuardianAddressCountyIsPresent` — MQE0668

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient guardian address county is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Guardian Address State (NK1-4.4)

### `PatientGuardianAddressStateIsMissing` — MQE0150

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient guardian address state is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinGuardianAddressIsValid` — No value found, no data sent, nothing to analyze.
  - `PatientResponsiblePartyIsProperlyFormed` — No value found, no data sent, nothing to analyze.

### `PatientGuardianAddressStateIsPresent` — MQE0670

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient guardian address state is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientResponsiblePartyIsProperlyFormed` — Value found, something sent, may be valid or invalid

## Guardian Address Street (NK1-4.1)

### `PatientGuardianAddressStreetIsMissing` — MQE0151

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient guardian address street is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinGuardianAddressIsValid` — No value found, no data sent, nothing to analyze.

### `PatientGuardianAddressStreetIsPresent` — MQE0671

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient guardian address street is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Guardian Address Zip (NK1-4.5)

### `PatientGuardianAddressZipIsMissing` — MQE0152

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient guardian address zip is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinGuardianAddressIsValid` — No value found, no data sent, nothing to analyze.
  - `PatientResponsiblePartyIsProperlyFormed` — No value found, no data sent, nothing to analyze.

### `PatientGuardianAddressZipIsPresent` — MQE0673

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient guardian address zip is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientResponsiblePartyIsProperlyFormed` — Value found, something sent, may be valid or invalid

## Guardian Address Zip (NK1-4.6)

### `PatientGuardianAddressCountryIsMissing` — MQE0599

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient guardian address zip is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinGuardianAddressIsValid` — No value found, no data sent, nothing to analyze.

### `PatientGuardianAddressCountryIsPresent` — MQE0667

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient guardian address zip is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Guardian Name (NK1-2)

### `PatientGuardianNameIsMissing` — MQE0155

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient guardian name is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientGuardianNameIsSameAsUnderagePatient` — MQE0156

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient guardian name is same as underage patient
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinNameIsNotSameAsPatient`
  - `PatientResponsiblePartyIsProperlyFormed` — Patient first and last name match the guardian first and last name.

### `PatientGuardianNameIsPresent` — MQE0675

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient guardian name is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Guardian Name First (NK1-2.2)

### `PatientGuardianNameFirstIsMissing` — MQE0153

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient guardian name first is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientResponsiblePartyIsProperlyFormed` — No value found, no data sent, nothing to analyze.

### `PatientGuardianNameFirstIsPresent` — MQE0674

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient guardian name first is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientResponsiblePartyIsProperlyFormed` — Value found, something sent, may be valid or invalid

## Guardian Name Last (NK1-2.1)

### `PatientGuardianNameLastIsMissing` — MQE0154

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient guardian name last is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientResponsiblePartyIsProperlyFormed` — No value found, no data sent, nothing to analyze.

### `PatientGuardianNameLastIsPresent` — MQE0676

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient guardian name last is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientResponsiblePartyIsProperlyFormed` — Value found, something sent, may be valid or invalid

## Guardian Phone (NK1-5)

### `PatientGuardianPhoneIsMissing` — MQE0158

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient guardian phone is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientResponsiblePartyIsProperlyFormed` — No value found, no data sent, nothing to analyze.

### `PatientGuardianPhoneIsPresent` — MQE0677

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient guardian phone is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientResponsiblePartyIsProperlyFormed` — Value found, something sent, may be valid or invalid

## Guardian Relationship (NK1-3)

### `PatientGuardianRelationshipIsMissing` — MQE0159

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient guardian relationship is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientGuardianRelationshipIsPresent` — MQE0678

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient guardian relationship is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Guardian Responsible Party (NK1)

### `PatientGuardianResponsiblePartyIsMissing` — MQE0157

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient guardian responsible party is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientHasResponsibleParty` — Responsible party missing and/or responsible party relationship code missing.

### `PatientGuardianResponsiblePartyIsPresent` — MQE0679

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient guardian responsible party is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Immunity Code

### `PatientImmunityCodeIsDeprecated` — MQE0606

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient immunity code is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientImmunityIsValid` — Deprecated code value.

### `PatientImmunityCodeIsUnrecognized` — MQE0607

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient immunity code is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientImmunityIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

## Immunization Registry Status (PD1-16)

### `PatientImmunizationRegistryStatusIsDeprecated` — MQE0160

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient immunization registry status is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientImmunizationRegistryStatusIsIgnored` — MQE0161

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient immunization registry status is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientImmunizationRegistryStatusIsInvalid` — MQE0162

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient immunization registry status is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientImmunizationRegistryStatusIsMissing` — MQE0163

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient immunization registry status is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientImmunizationRegistryStatusIsUnrecognized` — MQE0164

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient immunization registry status is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientImmunizationRegistryStatusIsPresent` — MQE0680

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient immunization registry status is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Medicaid Number (PID-3)

### `PatientMedicaidNumberIsInvalid` — MQE0167

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient Medicaid number is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientMedicaidNumberIsValid` — Medicaid Number is 9 digits long, does not have six of the same digits in a row and is not '123456789' or '987654321'

### `PatientMedicaidNumberIsMissing` — MQE0168

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient Medicaid number is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientMedicaidNumberIsValid` — No value found, no data sent, nothing to analyze.

### `PatientMedicaidNumberIsPresent` — MQE0681

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient Medicaid number is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientMedicaidNumberIsValid` — Value found, something sent, may be valid or invalid

## Middle Name (PID-5.3)

### `PatientMiddleNameMayBeInitial` — MQE0170

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient middle name may be initial
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientNameMiddleIsInvalid` — MQE0528

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient middle name is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientNameIsValid` — Patient middle name must not be on the specified invalid name list ('UN','UK','UNK', 'UNKN', 'NONE').

### `PatientNameMiddleIsMissing` — MQE0529

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient middle name is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientNameIsValid`

### `PatientNameMiddleIsPresent` — MQE0685

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient middle name is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientNameIsValid`

## Mother's Maiden Name (PID-6.1)

### `PatientMotherSMaidenNameIsMissing` — MQE0171

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient mother's maiden name is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientMothersMaidenNameIsValid` — No value found, no data sent, nothing to analyze.

### `PatientMothersMaidenNameIsInvalid` — MQE0547

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient mother's maiden name is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientMothersMaidenNameIsValid` — Patient maiden name must not be on the specified invalid name list ('X','UN','UK','UNK', 'UNKN', 'NONE').

### `PatientMotherSMaidenNameHasJunkName` — MQE0548

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient mother's maiden name has junk name
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientMothersMaidenNameIsValid` — Patient maiden name must not be on the specified junk name list ('UNKNOWN','NONE','NO LAST NAM','NO LAST NAME', 'NONAME', 'NO NAME', 'EMPTY', 'MISSING').

### `PatientMotherSMaidenNameHasInvalidPrefixes` — MQE0549

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient mother's maiden name has invalid prefixes
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientMothersMaidenNameIsValid` — Patient maiden name must not be on the specified invalid prefixes name list ('ZZ','XX').

### `PatientMotherSMaidenNameIsTooShort` — MQE0550

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient mother's maiden name is too short
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientMothersMaidenNameIsValid` — Patient maiden name must be more than 1 character in length.

### `PatientMotherSMaidenNameIsPresent` — MQE0682

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient mother's maiden name is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientMothersMaidenNameIsValid` — Value found, something sent, may be valid or invalid

## Name (PID-5)

### `PatientNameMayBeTemporaryNewbornName` — MQE0172

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient name may be temporary newborn name
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientNameIsValid` — Patient name must not be on the specified temporary newborn name list (BABY BOY, BABY GIRL, BABY (first name), NEWBORN (first name), BOY BABY, GIRL BABY)

### `PatientNameMayBeTestName` — MQE0551

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient name may be test name
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientNameIsValid` — Patient name must not be on the specified test name list (MICKY MOUSE, DONALD DUCK, TEST PATIENT, TEST(first or last name),  PATIENT(first or last name), BENJAMIN S PETERSON

### `PatientNameHasJunkName` — MQE0173

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient name has junk name
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientNameIsValid` — Patient names must not be on the specified junk name list (first names: B1, G1, G2, UNNAMED, UNKNOWN, NONE, NOFIRSTNAME, NO FIRST NAME, NO FIRSTNAME, NONAME, NO NAME, EMPTY, MISSING, BABY, BABY BOY, BABY GIRL, GIRL, BOY, A BOY, A GIRL, ABABYGIRL, B BOY, B GIRL, BABY OY, BABY BAY, BABY BO, BABY BOY, BABY BOY 2, BABY BOY A, BABY BOY B, BABY BOY #1, BABY BOY 2, BABY BOY1, BABY GIRL 1, BABY GIRL B, BABY GIRL #1, BABY GIRL 1, BABY GIRL A, BABY GIRL B, BABY GIRL ONE, BABY GIRL1, BABY GRIL, BABY M, BABY SISTER, BABY-GIRL, BABYBOY, BABYBOY-1, BABYBOY-2, BABYBOYA, BABYGIR, BABYGIRL, BABYGIRL-A, BABYGIRL-B, BB, BBABYGIRL, BG, BOY #1, BOY #2, BOY 1, BOY 2, BOY 3, BOY A, BOY B, BOY ONE, BOY TWO, BOY+, C BOY, GIRL # 2, GIRL #2, GIRL (L), GIRL A, GIRL B, GIRL TWIN 2, GIRL#1, GIRL#2, TEST GIRL, TWIN BOY, TWIN GIRL A, B2, NEWBORN, TWIN GIRL, BABU GIRL TWIN, BABY BOY TWIN, BABY BOY 1, BBOY, BABY GIRL, BABY GIRL TWO, BABY 1, BABYGIRL A, BABYBOY 2, BBTWO, BBONE, BGONE, BGTWO, B-G, BG2, BG1, MALE, FEMALE) (middle names: UNKNOWN, NONE, NOMIDDLENAME, NO MIDDLE NAME, NO MIDDLENAME, NONAME, NO NAME, EMPTY, MISSING) (last names: UNKNOWN, NONE, NOLASTNAME, NO LAST NAME, NO LASTNAME, NONAME, NO NAME, EMPTY, MISSING)

## Name First (PID-5.2)

### `PatientNameFirstIsInvalid` — MQE0140

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient name first is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientNameIsValid` — Patient first name must not be on the specified invalid name list ('X','U','UN','UK','UNK', 'UNKN', 'NONE').

### `PatientNameFirstIsMissing` — MQE0141

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient name first is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientNameIsValid` — Patient First Name is not indicated

### `PatientNameFirstMayIncludeMiddleInitial` — MQE0142

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient name first may include middle initial
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientNameIsValid` — Patient has first name, but missing middle name. The first name has a space followed by a single character.

### `PatientNameFirstIsPresent` — MQE0683

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient name first is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientNameIsValid` — Patient First Name is indicated

## Name Last (PID-5.1)

### `PatientNameLastIsInvalid` — MQE0165

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient name last is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientNameIsValid` — Patient last name must not be on the specified invalid name list ('X','U','UN','UK','UNK', 'UNKN', 'NONE').

### `PatientNameLastIsMissing` — MQE0166

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient name last is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientNameIsValid`

### `PatientNameLastIsPresent` — MQE0684

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient name last is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientNameIsValid`

## Name Type Code (PID-5.7)

### `PatientNameTypeCodeIsDeprecated` — MQE0405

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient name type code is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientNameTypeCodeIsIgnored` — MQE0406

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient name type code is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientNameTypeCodeIsInvalid` — MQE0407

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient name type code is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientNameTypeCodeIsMissing` — MQE0408

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient name type code is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientNameTypeIsValid` — No value found, no data sent, nothing to analyze.

### `PatientNameTypeCodeIsUnrecognized` — MQE0409

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient name type code is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientNameTypeCodeIsNotValuedLegal` — MQE0516

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient name type code is not valued legal
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientNameTypeIsValid` — Patient Name Type is not 'L' for legal.

### `PatientNameTypeCodeIsPresent` — MQE0686

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient name type code is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientNameTypeIsValid` — Value found, something sent, may be valid or invalid

## Patient (PID)

### `AdministeredVaccinationsCountIsLargerThanExpected` — MQE0568

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient patient has more vaccinations than expected
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminCountIsAsExpectedForAge` — Expecting less than 20 vaccinations given before 6 months of age. Expecting less than 30 vaccinations given before 2 years of age.

### `AdministeredVaccinationsCountIsTwoVaccinationEventsBySixYears` — MQE0770

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient patient has at least two vaccination events before six years of age
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminCountIsAsExpectedForAge` — Patient received vaccinations on two different dates by the age of six.

### `AdministeredVaccinationsCountIsZero` — MQE0771

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient patient is zero
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminCountIsAsExpectedForAge` — Patient received no vaccinations.

### `AdministeredVaccinationsCountIsLessThanFifteenByTwentyFourMonths` — MQE0772

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient patient is less than 15 doses by 24 months
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `VaccinationAdminCountIsAsExpectedForAge` — Patient received less than 15 vaccinations by 24 months of age.

## Phone (PID-13)

### `PatientPhoneIsIncomplete` — MQE0174

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient phone is incomplete
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientPhoneIsValid` — Incomplete code value.

### `PatientPhoneIsInvalid` — MQE0175

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient phone is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientPhoneIsValid` — Only validating North American Phone Numbers. Area code must be 3 valid digits (First digit can't be 0 or 1. Can't end with '11'). Local number must be 7 valid digits (First digit can't be 0 or 1. First 3 digits can't be '555'. 2nd and 3rd digits can't both be '1'.).

### `PatientPhoneIsMissing` — MQE0176

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient phone is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientPhoneIsValid` — No value found, no data sent, nothing to analyze.

### `PatientPhoneIsPresent` — MQE0688

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient phone is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientPhoneIsValid` — Value found, something sent, may be valid or invalid

## Phone Tel Equip Code (PID-13.3)

### `PatientPhoneTelEquipCodeIsDeprecated` — MQE0458

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient phone tel equip code is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientPhoneIsValid` — Deprecated code value.

### `PatientPhoneTelEquipCodeIsIgnored` — MQE0459

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient phone tel equip code is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientPhoneTelEquipCodeIsInvalid` — MQE0460

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient phone tel equip code is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientPhoneIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `PatientPhoneTelEquipCodeIsMissing` — MQE0461

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient phone tel equip code is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientPhoneIsValid` — No value found, no data sent, nothing to analyze.

### `PatientPhoneTelEquipCodeIsUnrecognized` — MQE0462

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient phone tel equip code is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientPhoneIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `PatientPhoneTelEquipCodeIsPresent` — MQE0689

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient phone tel equip code is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientPhoneIsValid` — Value found, something sent, may be valid or invalid

## Phone Tel Use Code (PID-13.2)

### `PatientPhoneTelUseCodeIsDeprecated` — MQE0453

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient phone tel use code is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientPhoneIsValid` — Deprecated code value.

### `PatientPhoneTelUseCodeIsIgnored` — MQE0454

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient phone tel use code is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientPhoneTelUseCodeIsInvalid` — MQE0455

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient phone tel use code is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientPhoneIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `PatientPhoneTelUseCodeIsMissing` — MQE0456

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient phone tel use code is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientPhoneIsValid` — No value found, no data sent, nothing to analyze.

### `PatientPhoneTelUseCodeIsUnrecognized` — MQE0457

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient phone tel use code is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientPhoneIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `PatientPhoneTelUseCodeIsPresent` — MQE0690

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient phone tel use code is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientPhoneIsValid` — Value found, something sent, may be valid or invalid

## Primary Facility Id (PD1-3.3)

### `PatientPrimaryFacilityIdIsDeprecated` — MQE0177

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient primary facility id is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientPrimaryFacilityIdIsIgnored` — MQE0178

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient primary facility id is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientPrimaryFacilityIdIsInvalid` — MQE0179

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient primary facility id is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientPrimaryFacilityIdIsMissing` — MQE0180

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient primary facility id is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — No value found, no data sent, nothing to analyze.

### `PatientPrimaryFacilityIdIsUnrecognized` — MQE0181

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient primary facility id is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientPrimaryFacilityIdIsPresent` — MQE0691

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient primary facility id is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — Value found, something sent, may be valid or invalid

## Primary Facility Name (PD1-3.1)

### `PatientPrimaryFacilityNameIsMissing` — MQE0182

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient primary facility name is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — No value found, no data sent, nothing to analyze.

### `PatientPrimaryFacilityNameIsPresent` — MQE0692

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient primary facility name is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — Value found, something sent, may be valid or invalid

## Primary Language (PID-15)

### `PatientPrimaryLanguageIsDeprecated` — MQE0183

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient primary language is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientPrimaryLanguageIsIgnored` — MQE0184

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient primary language is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientPrimaryLanguageIsInvalid` — MQE0185

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient primary language is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientPrimaryLanguageIsMissing` — MQE0186

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient primary language is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — No value found, no data sent, nothing to analyze.

### `PatientPrimaryLanguageIsUnrecognized` — MQE0187

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient primary language is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — Coded value is not recognized as either valid or invalid because it is unknown to this system.

### `PatientPrimaryLanguageIsPresent` — MQE0693

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient primary language is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — Value found, something sent, may be valid or invalid

## Primary Physician Id (PD1-4.1)

### `PatientPrimaryPhysicianIdIsDeprecated` — MQE0188

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient primary physician id is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientPrimaryPhysicianIdIsIgnored` — MQE0189

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient primary physician id is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientPrimaryPhysicianIdIsInvalid` — MQE0190

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient primary physician id is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientPrimaryPhysicianIdIsMissing` — MQE0191

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient primary physician id is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientPrimaryPhysicianIdIsUnrecognized` — MQE0192

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient primary physician id is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientPrimaryPhysicianIdIsPresent` — MQE0694

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient primary physician id is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Primary Physician Name (PD1-4.2)

### `PatientPrimaryPhysicianNameIsMissing` — MQE0193

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient primary physician name is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientPrimaryPhysicianNameIsValid` — No value found, no data sent, nothing to analyze.

### `PatientPrimaryPhysicianNameIsPresent` — MQE0695

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient primary physician name is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientPrimaryPhysicianNameIsValid` — Value found, something sent, may be valid or invalid

## Protection Indicator (PD1-12)

### `PatientProtectionIndicatorIsDeprecated` — MQE0194

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient protection indicator is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientProtectionIndicatorIsValid` — Deprecated code value.

### `PatientProtectionIndicatorIsIgnored` — MQE0195

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient protection indicator is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientProtectionIndicatorIsInvalid` — MQE0196

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient protection indicator is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientProtectionIndicatorIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `PatientProtectionIndicatorIsMissing` — MQE0197

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient protection indicator is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientProtectionIndicatorIsValid` — No value found, no data sent, nothing to analyze.

### `PatientProtectionIndicatorIsUnrecognized` — MQE0198

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient protection indicator is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientProtectionIndicatorIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `PatientProtectionIndicatorIsValuedAsNo` — MQE0199

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient protection indicator is valued as no
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientProtectionIndicatorIsValid` — Patient Protection Indicator value is 'N'.

### `PatientProtectionIndicatorIsValuedAsYes` — MQE0200

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient protection indicator is valued as yes
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientProtectionIndicatorIsValid` — Patient Protection Indicator value is 'Y'.

### `PatientProtectionIndicatorIsPresent` — MQE0696

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient protection indicator is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Publicity Code (PD1-11)

### `PatientPublicityCodeIsDeprecated` — MQE0201

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient publicity code is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientPublicityCodeIsIgnored` — MQE0202

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient publicity code is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientPublicityCodeIsInvalid` — MQE0203

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient publicity code is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `PatientPublicityCodeIsMissing` — MQE0204

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient publicity code is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — No value found, no data sent, nothing to analyze.

### `PatientPublicityCodeIsUnrecognized` — MQE0205

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient publicity code is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — Coded value is not recognized as either valid or invalid because it is unknown to this system.

### `PatientPublicityCodeIsPresent` — MQE0697

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient publicity code is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — Value found, something sent, may be valid or invalid

## Race (PID-10)

### `PatientRaceIsDeprecated` — MQE0206

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient race is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — Deprecated code value.

### `PatientRaceIsIgnored` — MQE0207

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient race is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientRaceIsInvalid` — MQE0208

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient race is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `PatientRaceIsMissing` — MQE0209

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient race is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — No value found, no data sent, nothing to analyze.

### `PatientRaceIsUnrecognized` — MQE0210

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient race is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — Coded value is not recognized as either valid or invalid because it is unknown to this system.

### `PatientRaceIsPresent` — MQE0698

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient race is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — Value found, something sent, may be valid or invalid

## Registry Id (PID-3)

### `PatientRegistryIdIsMissing` — MQE0211

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient registry id is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientRegistryIdIsPresent` — No value found, no data sent, nothing to analyze.
  - `PatientRegistryIdIsValid` — No value found, no data sent, nothing to analyze.

### `PatientRegistryIdIsUnrecognized` — MQE0212

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient registry id is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientRegistryIdIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `PatientRegistryIdIsPresent` — MQE0699

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient registry id is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientRegistryIdIsPresent` — Value found, something sent, may be valid or invalid

## Registry Status (PD1-16)

### `PatientRegistryStatusIsDeprecated` — MQE0213

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient registry status is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientRegistryStatusIsIgnored` — MQE0214

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient registry status is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientRegistryStatusIsInvalid` — MQE0215

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient registry status is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientRegistryStatusIsMissing` — MQE0216

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient registry status is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientRegistryStatusIsUnrecognized` — MQE0217

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient registry status is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientRegistryStatusIsPresent` — MQE0700

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient registry status is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Ssn (PID-3)

### `PatientSsnIsInvalid` — MQE0218

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient SSN is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientSsnIsValid` — Patient SSN cannot start with 000 or have 00 in the middle section. It must be comprised of exactly 9 digits. It cannot be equal to '123456789' or '987654321'. It cannot have 6 consective digits that are the same.

### `PatientSsnIsMissing` — MQE0219

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient SSN is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientSsnIsValid` — No value found, no data sent, nothing to analyze.

### `PatientSsnIsPresent` — MQE0701

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient SSN is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientSsnIsValid` — Value found, something sent, may be valid or invalid

## Submitter Id (PID-3)

### `PatientSubmitterIdIsMissing` — MQE0220

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient submitter id is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientSubmitterIsValid` — No value found, no data sent, nothing to analyze.

### `PatientSubmitterIdIsPresent` — MQE0703

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient submitter id is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Submitter Id Authority (PID-3.4)

### `PatientSubmitterIdAuthorityIsMissing` — MQE0393

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient submitter id authority is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientSubmitterIsValid` — No value found, no data sent, nothing to analyze.

### `PatientSubmitterIdAuthorityIsPresent` — MQE0702

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient submitter id authority is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Submitter Id Type Code (PID-3.5)

### `PatientSubmitterIdTypeCodeIsMissing` — MQE0394

- **Severity:** Error
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient submitter id type code is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientSubmitterIsValid` — No value found, no data sent, nothing to analyze.

### `PatientSubmitterIdTypeCodeIsDeprecated` — MQE0512

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient submitter id type code is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientSubmitterIdTypeCodeIsInvalid` — MQE0513

- **Severity:** Error
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient submitter id type code is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientSubmitterIdTypeCodeIsUnrecognized` — MQE0514

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient submitter id type code is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientSubmitterIdTypeCodeIsIgnored` — MQE0515

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient submitter id type code is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientSubmitterIdTypeCodeIsPresent` — MQE0704

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient submitter id type code is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientSubmitterIsValid` — Value found, something sent, may be valid or invalid

## System Entry Time

### `PatientCreationIsOnTime` — MQE0575

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient system entry time is on time
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCreationTimeliness` — It has been 30 days or less between the patient birth date and the system entry date.

### `PatientCreationIsLate` — MQE0576

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient system entry time is late
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCreationTimeliness` — It is 30-45 days between the patient birth date and the system entry date.

### `PatientCreationIsVeryLate` — MQE0577

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient system entry time is very late
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCreationTimeliness` — It is 45-60 days between the patient birth date and the system entry date.

### `PatientCreationIsTooLate` — MQE0578

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient system entry time is too late
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCreationTimeliness` — It is over 60 days between the patient birth date and the system entry date.

### `PatientSystemEntryDateIsMissing` — MQE0579

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient system entry time is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCreationDateIsValid` — No value found, no data sent, nothing to analyze.

### `PatientSystemEntryDateIsInvalid` — MQE0580

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient system entry time is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCreationDateIsValid` — Patient system entry date cannot be translated to a date.

### `PatientSystemEntryDateIsInTheFuture` — MQE0582

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient system entry time is in future
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCreationDateIsValid` — Date given is in the future.

### `PatientSystemEntryDateIsPresent` — MQE0705

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient system entry time is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCreationDateIsValid` — Value found, something sent, may be valid or invalid

## Vfc Effective Date (PV1-20.2)

### `PatientVfcEffectiveDateIsBeforeBirth` — MQE0221

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient VFC effective date is before birth
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientFinancialStatusDateIsValid` — Date given is before patient birth date.

### `PatientVfcEffectiveDateIsInFuture` — MQE0222

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient VFC effective date is in future
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientFinancialStatusDateIsValid` — Message received date is before the patient VFC Effective date.

### `PatientVfcEffectiveDateIsInvalid` — MQE0223

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient VFC effective date is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientVfcEffectiveDateIsMissing` — MQE0224

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient VFC effective date is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientFinancialStatusDateIsValid` — No value found, no data sent, nothing to analyze.

### `PatientVfcEffectiveDateIsPresent` — MQE0706

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient VFC effective date is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientFinancialStatusDateIsValid` — Value found, something sent, may be valid or invalid

## Vfc Status (PV1-20.1)

### `PatientVfcStatusIsDeprecated` — MQE0225

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient VFC status is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — Deprecated code value.

### `PatientVfcStatusIsIgnored` — MQE0226

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient VFC status is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientVfcStatusIsInvalid` — MQE0227

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient VFC status is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `PatientVfcStatusIsMissing` — MQE0228

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient VFC status is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — No value found, no data sent, nothing to analyze.

### `PatientVfcStatusIsUnrecognized` — MQE0229

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient VFC status is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — Coded value is not recognized as either valid or invalid because it is unknown to this system.

### `PatientVfcStatusIsPresent` — MQE0707

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Patient VFC status is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `PatientCodesAreValid` — Value found, something sent, may be valid or invalid

## Wic Id (PID-3)

### `PatientWicIdIsInvalid` — MQE0230

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient WIC id is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientWicIdIsMissing` — MQE0231

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient WIC id is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `PatientWicIdIsPresent` — MQE0708

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Patient WIC id is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

