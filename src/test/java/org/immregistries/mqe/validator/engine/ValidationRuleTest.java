package org.immregistries.mqe.validator.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.DetectionType;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntityLists;
import org.immregistries.mqe.validator.engine.rules.patient.PatientBirthDateIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientExists;
import org.immregistries.mqe.validator.engine.rules.patient.PatientIsUnderage;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdminAfterBirthDate;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdminDateIsValid;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.MqeAddress;
import org.immregistries.mqe.vxu.MqePhoneNumber;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ValidationRuleTest {
  
  private static final Logger logger = LoggerFactory.getLogger(ValidationRuleTest.class);

  private static final ValidationUtility util = ValidationUtility.INSTANCE;
  private static final ValidationRunner runner = ValidationRunner.INSTANCE;

  @Test
  public void whatClassDoesItReport() {
    ValidationRule<MqeVaccination> vr = new VaccinationAdminAfterBirthDate();
    assertEquals(VaccinationAdminAfterBirthDate.class, vr.getClass());
  }

//	So..for a full fledged test, I'd need to parse a message into the model, and then invoke the validators. 
//  for a unit-test I want to test each rule, which can usually be done by calling the methods in the implementations. 

  @Test
  public void test3() {
    //let's say we start with a MessageReceived:
    MqeMessageReceived mr = new MqeMessageReceived();
    MqePatient p = new MqePatient();
    p.setBirthDateString("20160101");
    mr.setPatient(p);

    ValidationRule<MqePatient> vr = new PatientBirthDateIsValid();

    List<ValidationReport> issues = vr.executeRule(p, mr).getValidationDetections();

    assertEquals("should be zero issues", 0, issues.size());

    p.setBirthDateString("");
    issues = vr.executeRule(p, mr).getValidationDetections();
    assertEquals("should be just one issue for missing the birthdate. ", 1, issues.size());

  }

  @Test
  public void testPatientIsUnderageDependencies() {
    List<Class> passed = new ArrayList<Class>();
    ValidationRule pu = new PatientIsUnderage();

    assertEquals("should be two dependencies: ", 2, pu.getDependencies().length);

    boolean dependenciesMet = pu.dependenciesAreMet(passed);
    assertEquals(
        "PatientIsUnderage should not pass until birth date is validated and patient exists ",
        false, dependenciesMet);

    passed.add(PatientExists.class);

    dependenciesMet = pu.dependenciesAreMet(passed);
    assertEquals("PatientIsUnderage should not pass yet. It still needs one more rule to pass.",
        false, dependenciesMet);

    passed.add(PatientBirthDateIsValid.class);
    dependenciesMet = pu.dependenciesAreMet(passed);
    assertEquals("PatientIsUnderage should pass!  All dependencies are present.", true,
        dependenciesMet);
  }

  @Test
  public void testDependency() {

    List<Class<? extends ValidationRule>> passed = new ArrayList<Class<? extends ValidationRule>>();

    ValidationRule vr = new PatientExists();
    boolean dependenciesMet = vr.dependenciesAreMet(passed);

    assertEquals("PatientExists deps should be met", true, dependenciesMet);

    ValidationRule pu = new PatientIsUnderage();
    dependenciesMet = pu.dependenciesAreMet(passed);
    assertEquals("PatientIsUnderage should not run until birth date is validated. ", false,
        dependenciesMet);

    vr = new PatientBirthDateIsValid();
    dependenciesMet = vr.dependenciesAreMet(passed);

    assertEquals("PatientBirthDateIsValid deps should not be met", false, dependenciesMet);

    passed.add(PatientExists.class);
    dependenciesMet = vr.dependenciesAreMet(passed);

    assertEquals("PatientBirthDateIsValid deps should NOW be met", true, dependenciesMet);

    vr = new VaccinationAdminAfterBirthDate();
    dependenciesMet = vr.dependenciesAreMet(passed);

    assertEquals("VaccinationAdminAfterBirthDate Deps should not be met!", false, dependenciesMet);

    passed.add(VaccinationAdminDateIsValid.class);

    dependenciesMet = vr.dependenciesAreMet(passed);

    assertEquals("VaccinationAdminAfterBirthDate Deps should not be met 2!", false,
        dependenciesMet);

    passed.add(PatientBirthDateIsValid.class);

    dependenciesMet = vr.dependenciesAreMet(passed);

    assertEquals("VaccinationAdminAfterBirthDate Deps should be met!", true, dependenciesMet);

    dependenciesMet = pu.dependenciesAreMet(passed);

    assertEquals("PatientIsUnderage should be able to run now", true, dependenciesMet);

  }


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

    List<ValidationRulePair> rpList = util.buildRulePairs(testRules, m.getPatient(), m);

    List<ValidationRuleResult> results = runner.processValidationRules(rpList, null);

    assertEquals("Should have about three rules that ran", 3, results.size());

    //let's make the birth date bad!
    m.getPatient().setBirthDateString("");
    results = runner.processValidationRules(rpList, null);
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

  /**
   * This test runs all the rules against an empty message to get
   * the full armament of detections.
   */
  @Test
  public void AllPatientRules() {
    List<ValidationRule> patientRules = ValidationRuleEntityLists.PATIENT_RULES.getRules();

    Map<Detection, String> expectedMissingDetections = new HashMap<>();
    //then get the list of everything that can be raised, and pick out the types that are MISSING.
    for (ValidationRule r : patientRules) {
      Set<Detection> ruleDetections = r.ruleDetections;
      for (Detection d : ruleDetections) {
        if (d != null && DetectionType.MISSING == d.getDetectionType()) {
          expectedMissingDetections.put(d, r.getClass().getSimpleName());
          System.out.println("Expected: " + d + " -> " + r.getClass().getSimpleName());
        };
      }
    }

    System.out.println("Expected Detections: " + expectedMissingDetections.size());

    List<ValidationReport> validationReports = new ArrayList<ValidationReport>();

    MqeMessageReceived mr = getEmptyMessage();

//    mr.getPatient().setBirthDateString("20160101");

    //This executes all the rules.  dependencies are not considered.
    for (ValidationRule rule : patientRules) {
      try {
        List<ValidationReport> ruleDetections = rule.executeRule(mr.getPatient(), mr)
            .getValidationDetections();
        validationReports.addAll(ruleDetections);

      } catch (Exception e) {
        logger.error("Woah... nasty.  " + e.getLocalizedMessage(), e);
        assertTrue("oops.  exception in " + rule.getClass() + "  " + e.getLocalizedMessage(),
            false);
      }
    }
    System.out.println("Issues.size(): " + validationReports.size());

    Map<Detection, String> reduce = new HashMap<>(expectedMissingDetections);

    List<Detection> extras = new ArrayList<>();
    for (ValidationReport validationReport : validationReports) {
      System.out.println("Detection: " + validationReport.getDetection() + " --> " + validationReport.getHl7LocationList());
      if (!reduce.containsKey(validationReport.getDetection())) {
        extras.add(validationReport.getDetection());
      } else {
        reduce.remove(validationReport.getDetection());
      }
    }

    System.out.println("\nNot Detected:");
    for (Detection d : reduce.keySet()) {
      System.out.println("----: " + d + " --> " + reduce.get(d));
    }

    System.out.println("\nExtra Detections:");
    for (Detection d : extras) {
      System.out.println("----: " + d + " --> " + reduce.get(d));
    }


    int excludedCount = 0;
//    We expect not to detect the following:
//          PatientPhoneTelUseCodeIsMissing (phone is missing.  this won't trigger. )
    excludedCount++;
//          PatientDeathDateIsMissing (only raised if a death flag is present)
    excludedCount++;
//          PatientAddressTypeIsMissing (can't be missing if the whole address is missing)
    excludedCount++;
//          PatientPhoneTelUseCodeIsMissing (the whole phone is missing, so it doesn't check this)
    excludedCount++;
//          PatientPhoneTelEquipCodeIsMissing (the whole phone is missing so it doesn't check this)
    excludedCount++;
//          PatientBirthOrderIsMissing (this won't be missing unless there's a birth order)
    excludedCount++;
//          PatientObjectIsMissing (we have to have an object to run these tests)
    excludedCount++;

    assertEquals("should be some issues",
        expectedMissingDetections.size() - excludedCount,
        validationReports.size());
  }

  @Test
  public void vaccinationRules() throws Exception {
    MqeMessageReceived mr = getFreshMessage();
    List<ValidationRule> vv = ValidationRuleEntityLists.VACCINATION_RULES.getRules();
    for (ValidationRule<MqeVaccination> vr : vv) {
      for (MqeVaccination v : mr.getVaccinations()) {
        List<ValidationReport> issues = vr.executeRule(v, mr).getValidationDetections();
      }
    }
  }

  private MqeMessageReceived getEmptyMessage() {
    MqeMessageReceived mr = new MqeMessageReceived();
    MqePatient p = new MqePatient();
    mr.setPatient(p);

    return mr;
  }

  private MqeMessageReceived getFreshMessage() {
    MqeMessageReceived mr = new MqeMessageReceived();
    MqePatient p = new MqePatient();
    p.setNameFirst("Johnathan");
    p.setNameMiddle("JingleHeimer");
    p.setPhone(new MqePhoneNumber("5175555454"));
    p.setNameLast("Scmitt");
    p.setAliasFirst("John");
    p.setNameTypeCode("Superhero");
    p.setMotherMaidenName("Cobbler");
    MqeAddress a = new MqeAddress();
    p.getPatientAddressList().add(a);
    a.setCity("Lansing");
    a.setStreet("121 Main");
    a.setStateCode("MI");
    a.setZip("48910");
    a.setCountyParishCode("42");
    a.setCountryCode("US");
    p.setBirthMultipleIndicator("N");
    p.setBirthOrder("1");
//		p.setBirthDateString("20160101");
    mr.setPatient(p);
    return mr;
  }


}

