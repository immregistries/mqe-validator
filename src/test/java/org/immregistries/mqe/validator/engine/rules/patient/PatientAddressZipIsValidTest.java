package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeAddress;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.junit.Before;
import org.junit.Test;

/** Covers issue #100's acceptance criteria for PatientAddressZipIsInvalid (MQE0112). */
public class PatientAddressZipIsValidTest {

  private PatientAddressZipIsValid rule = new PatientAddressZipIsValid();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqePatient p = new MqePatient();
  private MqeAddress a;

  @Before
  public void setUp() {
    a = p.getPatientAddress();
  }

  private boolean fires(String zip) {
    a.setZip(zip);
    ValidationRuleResult r = rule.executeRule(p, mr);
    boolean fired = r.getValidationDetections().stream()
        .anyMatch(d -> d.getDetection() == Detection.PatientAddressZipIsInvalid);
    assertTrue("Rule should always pass (ACCEPT-severity signal, not a blocking error)",
        r.isRulePassed());
    return fired;
  }

  // --- Boundary cases from the issue's AC ---

  @Test
  public void boundary_00500_isInvalid() {
    assertTrue(fires("00500"));
  }

  @Test
  public void boundary_00501_isValid() {
    assertTrue(!fires("00501"));
  }

  @Test
  public void boundary_99950_isValid() {
    assertTrue(!fires("99950"));
  }

  @Test
  public void boundary_99951_isInvalid() {
    assertTrue(fires("99951"));
  }

  // --- Repeated-digit disallowed values ---

  @Test
  public void repeatedDigitZipsAreInvalid() {
    for (String zip : new String[] {"00000", "11111", "22222", "33333", "44444", "55555", "66666",
        "77777", "88888", "99999"}) {
      assertTrue(zip + " should be invalid", fires(zip));
    }
  }

  // --- Length mismatch ---

  @Test
  public void tooShortIsInvalid() {
    assertTrue(fires("1234"));
  }

  @Test
  public void tooLongIsInvalid() {
    assertTrue(fires("123456"));
  }

  // --- Non-numeric ---

  @Test
  public void nonNumericIsInvalid() {
    assertTrue(fires("ABCDE"));
  }

  // --- ZIP+4: extract first 5 digits before validating ---

  @Test
  public void zipPlus4WithValidLeading5IsValid() {
    assertTrue(!fires("00501-1234"));
  }

  @Test
  public void zipPlus4WithBadLeading5IsInvalid() {
    assertTrue(fires("00500-1234"));
  }

  // --- Ordinary valid zip ---

  @Test
  public void ordinaryValidZipDoesNotFire() {
    assertTrue(!fires("94103"));
  }

  // --- Blank: Missing already covers this, Invalid should not also fire ---

  @Test
  public void blankZipDoesNotFireInvalid() {
    assertTrue(!fires(""));
    assertTrue(!fires(null));
  }

  // --- Country gating: US-only (blank treated as domestic default) ---

  @Test
  public void nonUsCountrySkipsValidation() {
    a.setCountryCode("CA");
    assertTrue("A bad-by-US-rules zip should not fire for a non-US address",
        !fires("1234"));
  }

  @Test
  public void blankCountryIsTreatedAsDomestic() {
    a.setCountryCode(null);
    assertTrue(fires("00500"));
  }

  @Test
  public void explicitUsCountryValidatesNormally() {
    a.setCountryCode("US");
    assertTrue(fires("00500"));
    a.setCountryCode("USA");
    assertTrue(fires("00500"));
  }
}
