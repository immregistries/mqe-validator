package org.immregistries.mqe.validator.engine;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.TestMessageGenerator;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.rules.patient.PatientBirthDateIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientExists;
import org.immregistries.mqe.validator.engine.rules.patient.PatientIsUnderage;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.parse.HL7MessageParser;
import org.junit.Test;

@SuppressWarnings("rawtypes")
public class ValidationRunnerTest {

  ValidationUtility util = ValidationUtility.INSTANCE;
  MessageValidator v = MessageValidator.INSTANCE;

  /**
   * This is testing the validation runner class specifically.
   */
  @Test
  public void validatePatient() {

    List<ValidationRule> testRules = Arrays.asList(new ValidationRule[]{
        new PatientBirthDateIsValid(),
        new PatientExists(),
        new PatientIsUnderage()
    });

    MqeMessageReceived m = getFreshMessage();
    m.getPatient().setBirthDateString("20160101");

    ValidationRunner vr = ValidationRunner.INSTANCE;

    List<ValidationRulePair> rpList = util.buildRulePairs(testRules, m.getPatient(), m);

    List<ValidationRuleResult> results = vr.processValidationRules(rpList, null);

    assertEquals("Should have about three rules that ran", 3, results.size());

    //let's make the birth date bad!
    m.getPatient().setBirthDateString("");
    results = vr.processValidationRules(rpList, null);
    assertEquals("Should have about two rules that ran", 2, results.size());

    List<Class> passed = util.getPassedFromResults(results);
    assertEquals("only one that passes though", 1, passed.size());

    assertEquals("passed should only contain PatientExists.class", PatientExists.class,
        passed.get(0));

    List<Class> failed = util.getFailedFromResults(results);
    assertEquals("only one that fails too", 1, failed.size());
    assertEquals("failure should be PatientBirthDateIsValid.class", PatientBirthDateIsValid.class,
        failed.get(0));

  }

  private MqeMessageReceived getFreshMessage() {
    MqeMessageReceived mr = new MqeMessageReceived();
    return mr;
  }

  private MqeMessageReceived getFullMessage() {
    TestMessageGenerator gen = new TestMessageGenerator();
    String vxu = gen.getExampleVXU_1();
    HL7MessageParser hl7conv = HL7MessageParser.INSTANCE;
    MqeMessageReceived mr = hl7conv.extractMessageFromText(vxu);
    return mr;
  }

  @Test
  public void validationWorkflowFull() {
    MqeMessageReceived mr = getFullMessage();
    List<ValidationRuleResult> violations = v.validateMessage(mr);
    System.out.println("Rules Run..." + violations.size());
    System.out.println("Reporting on " + violations.size() + " results");

    for (ValidationRuleResult vrr : violations) {
      if (vrr.isRulePassed()) {
//				System.out.println("PASSED!" + vrr.getRuleClass());
      } else {
        System.out.println(
            "RULE: " + vrr.getRuleClass() + " ISSUEs " + vrr.getValidationDetections().size());
        for (ValidationReport f : vrr.getValidationDetections()) {
          if (f.isError()) {
            System.out.println("     ERROR Issue: " + f.getDetection());
          } else {
            System.out.println("     WARN  Issue: " + f.getDetection());
          }
        }
      }
    }
  }


}
