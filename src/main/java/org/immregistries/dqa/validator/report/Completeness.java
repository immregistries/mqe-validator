package org.immregistries.dqa.validator.report;

/**
 * Completeness measures how many required, expected and recommended fields have been received and
 * also indicates if expected vaccinations have been reported.
 *
 * @author Josh
 */

public class Completeness {
  //
  // // Measurement Score Description Weight
  // // Patient
  // // Vaccination
  // // Vaccine Group
  // // Patient
  //
  // // Patient Fields Score Description Weight
  // // Overall
  // // Required
  // // Expected
  // // Recommended
  //
  // // Required HL7 Count Percent Description Weight
  // Patient Id PID-3
  // First Name PID-5.2
  // Last Name PID-5.1
  // Birth Date PID-7
  // Sex PID-8
  // Address PID-11
  // - Street PID-11
  // - City PID-11
  // - State PID-11
  // - Zip PID-11
  //
  // // Expected HL7 Count Percent Description Weight
  // Middle Name PID-5.3
  // Phone PID-13
  // Mother's Maiden PID-6
  //
  // // Recommended HL7 Count Percent Description Weight
  // Ethnicity PID-22
  // - Deprecated
  // Race PID-10
  // - Unrecognized
  // Responsible Party NK1
  // - First Name NK1
  // - Last Name NK1
  // - Relationship NK1
  //
  // // Optional HL7 Count Percent
  // Primary Language PID-15
  // Resp Party Address NK1-4
  // Resp Party Phone NK1-5
  // Address County PID-11.6
  // Registry Status PD1-16
  // Publicity Indicator PD1-11
  //
  // // Vaccination
  //
  // // Vaccination Fields Score Description Weight
  // Overall
  // Required
  // Expected
  // Recommended
  //
  // // Required HL7 Count Percent Description Weight
  // Vaccination Date RXA-3
  // Vaccination Code RXA-5
  // Information Source RXA-9
  // VFC Status OBX-5
  // - Unrecognized
  //
  // // Expected HL7 Count Percent Description Weight
  // CVX Code RXA-5
  // Lot Number RXA-15
  // Manufacturer RXA-17
  // - Unrecognized
  //
  // // Recommended HL7 Count Percent Description Weight
  // Admin Amount RXA-6
  // Completion Status RXA-20
  //
  // // Optional HL7 Count Percent
  // Action Code RXA-21
  // Refusal Reason RXA-18
  // Lot Expiration Date RXA-16
  // System Entry Date RXA-22
  // Vaccination Id ORC-3
  // Vaccine Group
  //
  //

}
