<!-- GENERATED FILE. Do not hand edit.
     Regenerate with DetectionMarkdownGenerator (see docs/detections/README.md). -->

# Next-of-kin detections

[Back to index](index.md)

## Address (NK1-4)

### `NextOfKinAddressIsDifferentFromPatientAddress` — MQE0056

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address is different from patient address
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsSameAsPatientAddress` — The City/State/Street/Street2 are different between Next Of Kin address and Patient address.

### `NextOfKinAddressIsMissing` — MQE0057

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsValid` — No value found, no data sent, nothing to analyze.

### `NextOfKinAddressIsInvalid` — MQE0564

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsValid` — Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value.

### `NextOfKinAddressIsPresent` — MQE0627

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsValid` — Value found, something sent, may be valid or invalid

## Address City (NK1-4.3)

### `NextOfKinAddressCityIsInvalid` — MQE0058

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin address city is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinAddressCityIsMissing` — MQE0059

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address city is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsValid` — No value found, no data sent, nothing to analyze.

### `NextOfKinAddressCityIsPresent` — MQE0624

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address city is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsValid` — Value found, something sent, may be valid or invalid

## Address Country (NK1-4.6)

### `NextOfKinAddressCountryIsDeprecated` — MQE0060

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin address country is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinAddressCountryIsIgnored` — MQE0061

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin address country is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinAddressCountryIsInvalid` — MQE0062

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin address country is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinAddressCountryIsMissing` — MQE0063

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address country is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsValid` — No value found, no data sent, nothing to analyze.

### `NextOfKinAddressCountryIsUnrecognized` — MQE0064

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin address country is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinAddressCountryIsPresent` — MQE0625

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address country is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsValid` — Value found, something sent, may be valid or invalid

## Address County (NK1-4.9)

### `NextOfKinAddressCountyIsDeprecated` — MQE0065

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin address county is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinAddressCountyIsIgnored` — MQE0066

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin address county is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinAddressCountyIsInvalid` — MQE0067

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin address county is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinAddressCountyIsMissing` — MQE0068

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address county is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsValid` — No value found, no data sent, nothing to analyze.

### `NextOfKinAddressCountyIsUnrecognized` — MQE0069

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin address county is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinAddressCountyIsPresent` — MQE0626

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address county is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsValid` — Value found, something sent, may be valid or invalid

## Address State (NK1-4.4)

### `NextOfKinAddressStateIsDeprecated` — MQE0070

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin address state is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinAddressStateIsIgnored` — MQE0071

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin address state is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinAddressStateIsInvalid` — MQE0072

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin address state is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinAddressStateIsMissing` — MQE0073

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address state is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsValid` — No value found, no data sent, nothing to analyze.

### `NextOfKinAddressStateIsUnrecognized` — MQE0074

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin address state is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinAddressStateIsPresent` — MQE0628

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address state is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsValid` — Value found, something sent, may be valid or invalid

## Address Street (NK1-4.1)

### `NextOfKinAddressStreetIsMissing` — MQE0075

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address street is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsValid` — No value found, no data sent, nothing to analyze.

### `NextOfKinAddressStreetIsPresent` — MQE0630

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address street is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsValid` — Value found, something sent, may be valid or invalid

## Address Street2 (NK1-4.2)

### `NextOfKinAddressStreet2IsMissing` — MQE0076

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin address street2 is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinAddressStreet2IsPresent` — MQE0629

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin address street2 is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Address Type (NK1-4.7)

### `NextOfKinAddressTypeIsDeprecated` — MQE0395

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin address type is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinAddressTypeIsIgnored` — MQE0396

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin address type is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinAddressTypeIsInvalid` — MQE0397

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address type is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsValid` — Address not valid according to Smarty Streets.

### `NextOfKinAddressTypeIsMissing` — MQE0398

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address type is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsValid` — No value found, no data sent, nothing to analyze.

### `NextOfKinAddressTypeIsUnrecognized` — MQE0399

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address type is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsValid` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `NextOfKinAddressTypeIsValuedBadAddress` — MQE0522

- **Severity:** Info
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address type is valued bad address
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsValid` — Address Type indicates the address is a bad address.

### `NextOfKinAddressTypeIsPresent` — MQE0631

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address type is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsValid` — Value found, something sent, may be valid or invalid

## Address Zip (NK1-4.5)

### `NextOfKinAddressZipIsInvalid` — MQE0077

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin address zip is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinAddressZipIsMissing` — MQE0078

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address zip is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsValid` — No value found, no data sent, nothing to analyze.

### `NextOfKinAddressZipIsPresent` — MQE0632

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin address zip is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinAddressIsValid` — Value found, something sent, may be valid or invalid

## Name (NK1-2)

### `NextOfKinNameIsMissing` — MQE0079

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin name is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinNameIsValid` — No value found for both first and last name, no data sent, nothing to analyze.

### `NextOfKinNameIsPresent` — MQE0634

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin name is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinNameIsValid` — Value not found for first and last name

## Name First (NK1-2.2)

### `NextOfKinNameFirstIsMissing` — MQE0080

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin name first is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinNameIsValid` — Next of Kin First Name is not indicated

### `NextOfKinNameFirstIsPresent` — MQE0633

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin name first is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinNameIsValid` — Next of Kin First Name is indicated

## Name Last (NK1-2.1)

### `NextOfKinNameLastIsMissing` — MQE0081

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin name last is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinNameIsValid` — Next of Kin Last Name is not indicated

### `NextOfKinNameLastIsPresent` — MQE0635

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin name last is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinNameIsValid` — Next of Kin Last Name is indicated

## Phone (NK1-5)

### `NextOfKinPhoneNumberIsIncomplete` — MQE0082

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin phone is incomplete
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinPhoneIsValid` — Next of kin phone number is missing area code or local number.

### `NextOfKinPhoneNumberIsInvalid` — MQE0083

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin phone is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinPhoneIsValid` — Phone number is invalid according to the North American Numbering Plan (NANP).

### `NextOfKinPhoneNumberIsMissing` — MQE0084

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin phone is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinPhoneNumberIsPresent` — MQE0636

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin phone is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Relationship (NK1-3)

### `NextOfKinRelationshipIsDeprecated` — MQE0085

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin relationship is deprecated
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinRelationshipIsIgnored` — MQE0086

- **Severity:** Info
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin relationship is ignored
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinRelationshipIsInvalid` — MQE0087

- **Severity:** Warn
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin relationship is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinRelationshipIsMissing` — MQE0088

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin relationship is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinRelationshipIsValidForUnderagedPatient` — Next of Kin Relationship is not indicated

### `NextOfKinRelationshipIsNotResponsibleParty` — MQE0089

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin relationship is not responsible party
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinRelationshipIsValidForUnderagedPatient` — The next of kin for an underage patient is expected to be a caregiver, father, grandparent, mother, parent, or guardian.

### `NextOfKinRelationshipIsUnexpected` — MQE0485

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin relationship is unexpected
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinRelationshipIsValidForUnderagedPatient` — An underage patient is not expecting a next of kin that is a child, step child, or foster child.

### `NextOfKinRelationshipIsUnrecognized` — MQE0090

- **Severity:** Warn
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin relationship is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinRelationshipIsValidForUnderagedPatient` — Code submitted is not recognized as either valid or invalid because it is unknown to this system.

### `NextOfKinRelationshipIsPresent` — MQE0637

- **Severity:** Accept
- **Status:** Active - wired to at least one rule below
- **Message shown to submitters:** Next-of-kin relationship is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `NextOfKinRelationshipIsValidForUnderagedPatient` — Next of Kin Relationship is indicated

## Ssn (NK1-33)

### `NextOfKinSsnIsMissing` — MQE0091

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin SSN is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `NextOfKinSsnIsPresent` — MQE0638

- **Severity:** Accept
- **Status:** Defined but not currently wired to any rule
- **Message shown to submitters:** Next-of-kin SSN is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

