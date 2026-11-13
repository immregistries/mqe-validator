package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Compares the detection output of {@link VaccineEvaluation} (new, StepIntoCDSI-based path) against
 * {@link VaccineEvaluationLegacy} (original lonestar-based path) for the same input, to ensure the
 * refactor did not change behavior.
 */
public class VaccineEvaluationComparisonTester {

  private static final Logger logger =
      LoggerFactory.getLogger(VaccineEvaluationComparisonTester.class);

  private VaccineEvaluation newRule = new VaccineEvaluation();
  private VaccineEvaluationLegacy legacyRule = new VaccineEvaluationLegacy();

  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqePatient p = new MqePatient();
  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

  @Before
  public void setUpTheObjects() {
    mr.setPatient(p);
  }

  @Test
  public void positiveScenarioMatches() {
    List<MqeVaccination> vaccinations = createPatientVaccinations(true);
    mr.setVaccinations(vaccinations);

    ValidationRuleResult rNew = newRule.executeRule(p, mr);
    ValidationRuleResult rLegacy = legacyRule.executeRule(p, mr);

    logger.info("NEW:    " + rNew.getValidationDetections());
    logger.info("LEGACY: " + rLegacy.getValidationDetections());

    assertEquals("passed flag should match", rLegacy.isRulePassed(), rNew.isRulePassed());
    assertEquals("detection sets should match", detectionKeySet(rLegacy), detectionKeySet(rNew));
  }

  @Test
  public void negativeScenarioMatches() {
    // reset patient/message between scenarios
    mr = new MqeMessageReceived();
    p = new MqePatient();
    mr.setPatient(p);

    List<MqeVaccination> vaccinations = createPatientVaccinations(false);
    mr.setVaccinations(vaccinations);

    ValidationRuleResult rNew = newRule.executeRule(p, mr);
    ValidationRuleResult rLegacy = legacyRule.executeRule(p, mr);

    logger.info("NEW:    " + rNew.getValidationDetections());
    logger.info("LEGACY: " + rLegacy.getValidationDetections());

    assertEquals("passed flag should match", rLegacy.isRulePassed(), rNew.isRulePassed());
    assertEquals("detection sets should match", detectionKeySet(rLegacy), detectionKeySet(rNew));
  }

  private Set<String> detectionKeySet(ValidationRuleResult r) {
    Set<String> keys = new HashSet<>();
    for (ValidationReport vr : r.getValidationDetections()) {
      keys.add(String.valueOf(vr.getDetection()));
    }
    return keys;
  }

  // ---- reused data builders (mirrored from VaccineEvaluationTester) ----

  private List<MqeVaccination> createPatientVaccinations(boolean allVaccines) {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.YEAR, -10);

    p.setBirthDate(cal.getTime());
    List<MqeVaccination> vaccinations = new ArrayList<>();
    if (allVaccines) {
      addVaccination("08", vaccinations, cal); // Hep B at Birth
    }

    cal.add(Calendar.MONTH, 2);
    if (allVaccines) {
      addVaccination("122", vaccinations, cal); // Rotavirus
      addVaccination("20", vaccinations, cal); // DTaP
      addVaccination("10", vaccinations, cal); // IPV
      addVaccination("17", vaccinations, cal); // Hib
      addVaccination("08", vaccinations, cal); // Hep B
      addVaccination("133", vaccinations, cal); // PCV13
    }

    cal.add(Calendar.MONTH, 2);
    if (allVaccines) {
      addVaccination("122", vaccinations, cal);
      addVaccination("20", vaccinations, cal);
      addVaccination("10", vaccinations, cal);
      addVaccination("17", vaccinations, cal);
      addVaccination("08", vaccinations, cal);
      addVaccination("133", vaccinations, cal);
    }

    cal.add(Calendar.MONTH, 2);
    if (allVaccines) {
      addVaccination("122", vaccinations, cal);
      addVaccination("20", vaccinations, cal);
      addVaccination("10", vaccinations, cal);
      addVaccination("17", vaccinations, cal);
      addVaccination("08", vaccinations, cal);
      addVaccination("133", vaccinations, cal);
    }

    cal.add(Calendar.MONTH, 6);
    addVaccination("133", vaccinations, cal); // PCV13
    addVaccination("94", vaccinations, cal); // MMRV
    addVaccination("83", vaccinations, cal); // Hep A

    cal.add(Calendar.MONTH, 3);
    addVaccination("20", vaccinations, cal); // DTaP
    addVaccination("10", vaccinations, cal); // IPV
    addVaccination("17", vaccinations, cal); // Hib
    addVaccination("08", vaccinations, cal); // Hep B

    cal.add(Calendar.MONTH, 3);
    addVaccination("83", vaccinations, cal); // Hep A

    cal.add(Calendar.MONTH, 5);
    addVaccination("20", vaccinations, cal); // DTaP
    addVaccination("10", vaccinations, cal); // IPV

    cal.add(Calendar.MONTH, 24);
    addVaccination("20", vaccinations, cal); // DTaP
    return vaccinations;
  }

  private void addVaccination(String cvxCode, List<MqeVaccination> vaccinations, Calendar cal) {
    MqeVaccination tmp = new MqeVaccination();
    tmp.setAdminDateString(dateFormat.format(cal.getTime()));
    tmp.setAdminCvxCode(cvxCode);
    vaccinations.add(tmp);
  }

}