package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.text.SimpleDateFormat;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientBirthDateIsValidTest {

  private PatientBirthDateIsValid rule = new PatientBirthDateIsValid();

  // Parts required for the test
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqePatient p = new MqePatient();
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

  private static final Logger logger = LoggerFactory.getLogger(PatientAddressIsValidTester.class);

  @Test
  public void testPatientBirthDateIsNotDate() {
    this.testDateFor("HHHHHHHH",Detection.PatientBirthDateIsInvalid);
    this.testDateFor("123",Detection.PatientBirthDateIsInvalid);
    this.testDateFor("1234567890",Detection.PatientBirthDateIsInvalid);
  }

  @Test
  public void testPatientBirthDateIsMissing() {
    this.testDateFor("", Detection.PatientBirthDateIsMissing);
    this.testDateFor(null, Detection.PatientBirthDateIsMissing);
    this.testDateFor("         ", Detection.PatientBirthDateIsMissing);
  }

  private void testDateFor(String date, Detection d) {
    p.setBirthDateString(date);
    int expectedDetectionCount = 0;
    boolean passedExpected = true;

    if (d!=null) {
      passedExpected = false;
      expectedDetectionCount = 1;
    }
    if(!StringUtils.isBlank(date)) {
      // Is present
      expectedDetectionCount++;
    }
    ValidationRuleResult r = rule.executeRule(p, mr);
    if (!passedExpected) {
      assertFalse(r.isRulePassed());
    } else {
      assertTrue(r.isRulePassed());
    }
    System.out.println(r.getValidationDetections());
    assertEquals("Date[" + p.getBirthDateString()+ "]", expectedDetectionCount,
        r.getValidationDetections().size());

    if (expectedDetectionCount == 1) {
      assertEquals("Expected detection for date["+date+"]", d,
          r.getValidationDetections().get(0).getDetection());
    }
  }


}
