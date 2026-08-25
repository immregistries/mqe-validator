package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.address.Country;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.MqeAddress;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.TargetType;

/**
 * Implements {@code PatientAddressZipIsInvalid} (MQE0112, issue #100): flags US ZIP codes that
 * are structurally bad - wrong length, one of the ten repeated-digit placeholders, or outside the
 * real-world assigned range ({@code 00501}-{@code 99950}).
 *
 * <p>Scope decisions (confirmed before implementing):
 * <ul>
 *   <li><b>Patient only</b> for now - Next-of-kin/Guardian have their own unimplemented zip
 *       detections, tracked separately.</li>
 *   <li><b>US-only</b>: only runs when {@code PATIENT_ADDRESS_COUNTRY} is US, or blank (treated
 *       as the domestic default) - a valid foreign postal code shouldn't be flagged as a bad US
 *       zip just for not looking like one.</li>
 *   <li><b>ZIP+4</b> ({@code 12345-6789}): the first 5 digits are extracted and validated: e.g.
 *       {@code 00500-1234} is invalid (bad leading 5), {@code 00501-1234} is valid.</li>
 *   <li><b>Non-numeric</b> values are explicitly invalid (can't satisfy the range rules).</li>
 * </ul>
 */
@ValidationRuleEntry(TargetType.Patient)
public class PatientAddressZipIsValid extends ValidationRule<MqePatient> {

  private static final Pattern ZIP_PLUS_4 = Pattern.compile("\\d{5}-\\d{4}");
  private static final Pattern FIVE_DIGITS = Pattern.compile("\\d{5}");
  private static final Set<String> REPEATED_DIGIT_ZIPS = new HashSet<>(Arrays.asList(
      "00000", "11111", "22222", "33333", "44444", "55555", "66666", "77777", "88888", "99999"));
  private static final int LOWEST_ASSIGNED_ZIP = 501;
  private static final int HIGHEST_ASSIGNED_ZIP = 99950;

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class};
  }

  public PatientAddressZipIsValid() {
    ImplementationDetail id = this.addRuleDetection(Detection.PatientAddressZipIsInvalid);
    id.setImplementationDescription(
        "US ZIP code (5-digit, or first 5 digits of a ZIP+4) is not exactly 5 digits, is one of "
            + "the ten repeated-digit placeholders (00000, 11111, ... 99999), or falls outside "
            + "the real-world assigned range 00501-99950. Only checked when the address country "
            + "is US or blank (treated as domestic).");
    id.setWhyToFix(
        "A structurally invalid ZIP can't be used for geographic lookups (e.g. VFC eligibility, "
            + "catchment-area reporting) and often indicates a data entry error or placeholder value.");
    id.setHowToFix("Correct the ZIP code in the source record, or provide the correct 5-digit US ZIP.");
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();

    MqeAddress a = target.getPatientAddress();
    if (a != null && !isUsOrUnspecifiedCountry(a.getCountryCode())) {
      return buildResults(issues, true);
    }

    String rawZip = a == null ? null : a.getZip();
    if (StringUtils.isNotBlank(rawZip) && isInvalidZip(rawZip.trim())) {
      issues.add(Detection.PatientAddressZipIsInvalid.build(rawZip, target));
    }

    return buildResults(issues, true);
  }

  private boolean isUsOrUnspecifiedCountry(String countryCode) {
    if (StringUtils.isBlank(countryCode)) {
      return true;
    }
    return Country.US.equals(Country.fromString(countryCode));
  }

  private boolean isInvalidZip(String rawZip) {
    String zip = ZIP_PLUS_4.matcher(rawZip).matches() ? rawZip.substring(0, 5) : rawZip;

    if (!FIVE_DIGITS.matcher(zip).matches()) {
      // Covers both "length not equal to 5" and non-numeric values in one check, since a
      // non-numeric value can never match \d{5} either.
      return true;
    }
    if (REPEATED_DIGIT_ZIPS.contains(zip)) {
      return true;
    }
    int numericZip = Integer.parseInt(zip);
    return numericZip < LOWEST_ASSIGNED_ZIP || numericZip > HIGHEST_ASSIGNED_ZIP;
  }

}
