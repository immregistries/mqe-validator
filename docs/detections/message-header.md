<!-- GENERATED FILE. Do not hand edit.
     Regenerate with DetectionMarkdownGenerator (see docs/detections/README.md). -->

# Message Header detections

[Back to index](index.md)

## Accept Ack Type (MSH-15)

### `MessageAcceptAckTypeIsMissing` — MQE0006

- **Severity:** Accept
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header accept ack type is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageHeaderFieldsArePresent` — Message Accept Acknowledgment Type is missing

### `MessageAcceptAckTypeIsPresent` — MQE0609

- **Severity:** Accept
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header accept ack type is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageHeaderFieldsArePresent` — Message Accept Acknowledgment Type is present

## App Ack Type (MSH-16)

### `MessageAppAckTypeIsMissing` — MQE0410

- **Severity:** Accept
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header app ack type is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageHeaderFieldsArePresent` — Message Application Acknowledgment Type is missing

### `MessageAppAckTypeIsPresent` — MQE0610

- **Severity:** Accept
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header app ack type is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageHeaderFieldsArePresent` — Message Application Acknowledgment Type is present

## Message Control Id (MSH-10)

### `MessageMessageControlIdIsMissing` — MQE0014

- **Severity:** Warn
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header message control id is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageHeaderFieldsArePresent` — Message Control Id is not indicated

### `MessageMessageControlIdIsPresent` — MQE0611

- **Severity:** Accept
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header message control id is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageHeaderFieldsArePresent` — Message Control Id is present

## Message Date (MSH-7)

### `MessageMessageDateIsInFuture` — MQE0015

- **Severity:** Error
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header message date is in future
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageHeaderDateIsValid` — Message Header date is over 2 hours into the future.

### `MessageMessageDateIsMissing` — MQE0017

- **Severity:** Error
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header message date is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageHeaderDateIsValid` — Message Header date was not indicated.

### `MessageMessageDateIsNotPrecise` — MQE0526

- **Severity:** Warn
- **Wiring:** Defined but not currently wired to any rule
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header message date is not precise
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `MessageMessageDateTimezoneIsMissing` — MQE0527

- **Severity:** Warn
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header message date is missing timezone
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageHeaderDateIsValid` — Message Header date timezone was not indicated.

### `MessageMessageDateIsUnexpectedFormat` — MQE0531

- **Severity:** Error
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header message date is an unexpected format
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageHeaderDateIsValid` — Message Header date cannot be translated to a date

### `MessageMessageDateIsPresent` — MQE0612

- **Severity:** Accept
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header message date is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageHeaderDateIsValid` — Message Header date was indicated.

### `MessageMessageDateTimezoneIsPresent` — MQE0613

- **Severity:** Accept
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header message date is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageHeaderDateIsValid` — Message Header date timezone was indicated.

## Message Profile Id (MSH-21)

### `MessageMessageProfileIdIsMissing` — MQE0439

- **Severity:** Accept
- **Wiring:** Defined but not currently wired to any rule
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header message profile id is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `MessageMessageProfileIdIsPresent` — MQE0614

- **Severity:** Accept
- **Wiring:** Defined but not currently wired to any rule
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header message profile id is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Message Trigger (MSH-9.2)

### `MessageMessageTriggerIsMissing` — MQE0018

- **Severity:** Warn
- **Wiring:** Defined but not currently wired to any rule
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header message trigger is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `MessageMessageTriggerIsPresent` — MQE0615

- **Severity:** Accept
- **Wiring:** Defined but not currently wired to any rule
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header message trigger is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Message Type (MSH-9.1)

### `MessageMessageTypeIsMissing` — MQE0020

- **Severity:** Warn
- **Wiring:** Defined but not currently wired to any rule
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header message type is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `MessageMessageTypeIsPresent` — MQE0616

- **Severity:** Accept
- **Wiring:** Defined but not currently wired to any rule
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header message type is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Processing Id (MSH-11)

### `MessageProcessingIdIsMissing` — MQE0023

- **Severity:** Accept
- **Wiring:** Defined but not currently wired to any rule
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header processing id is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `MessageProcessingIdIsPresent` — MQE0617

- **Severity:** Accept
- **Wiring:** Defined but not currently wired to any rule
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header processing id is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Receiving Application (MSH-5)

### `MessageReceivingApplicationIsMissing` — MQE0030

- **Severity:** Accept
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header receiving application is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageHeaderFieldsArePresent` — Message Receiving Application is not indicated

### `MessageReceivingApplicationIsPresent` — MQE0618

- **Severity:** Accept
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header receiving application is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageHeaderFieldsArePresent` — Message Receiving Application is indicated

## Receiving Facility (MSH-6)

### `MessageReceivingFacilityIsMissing` — MQE0032

- **Severity:** Accept
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header receiving facility is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageHeaderFieldsArePresent` — Message Receiving Facility is not indicated

### `MessageReceivingFacilityIsPresent` — MQE0619

- **Severity:** Accept
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header receiving facility is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageHeaderFieldsArePresent` — Message Receiving Facility is indicated

## Sending Application (MSH-3)

### `MessageSendingApplicationIsMissing` — MQE0035

- **Severity:** Accept
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header sending application is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageHeaderFieldsArePresent` — Message Sending Application is not indicated

### `MessageSendingApplicationIsPresent` — MQE0620

- **Severity:** Accept
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header sending application is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageHeaderFieldsArePresent` — Message Sending Application is indicated

## Sending Facility (MSH-4)

### `MessageSendingFacilityIsMissing` — MQE0037

- **Severity:** Warn
- **Wiring:** Defined but not currently wired to any rule
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header sending facility is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `MessageSendingFacilityIsPresent` — MQE0621

- **Severity:** Accept
- **Wiring:** Defined but not currently wired to any rule
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header sending facility is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Sending Responsible Organization (MSH-22)

### `MessageSendingResponsibleOrganizationIsMissing` — MQE0556

- **Severity:** Warn
- **Wiring:** Defined but not currently wired to any rule
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header sending responsible organization is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `MessageSendingResponsibleOrganizationIsPresent` — MQE0622

- **Severity:** Accept
- **Wiring:** Defined but not currently wired to any rule
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header sending responsible organization is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

## Version (MSH-12)

### `MessageVersionIsMissing` — MQE0038

- **Severity:** Accept
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header version is missing
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageVersionIsValid` — Message Version is not indicated

### `MessageVersionIsUnrecognized` — MQE0039

- **Severity:** Warn
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header version is unrecognized
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageVersionIsValid` — Message version is not a version of 2.3, 2.4, or 2.5

### `MessageVersionIsInvalid` — MQE0523

- **Severity:** Warn
- **Wiring:** Defined but not currently wired to any rule
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header version is invalid
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_

### `MessageVersionIsPresent` — MQE0623

- **Severity:** Accept
- **Wiring:** Active - wired to at least one rule below
- **Lifecycle:** _not set - add `@DetectionStatus(...)` on this constant in Detection.java_
- **Message shown to submitters:** Message Header version is present
- **What this means:** _not yet documented - add `@Documentation("...")` on this constant in Detection.java_
- **Implemented by:**
  - `MessageVersionIsValid` — Message Version is indicated

