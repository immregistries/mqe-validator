package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Date;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

/** Covers issue #102's acceptance criteria: threshold ladder and the two exclusion cases. */
public class PatientCovid2021DoseCountTest {

  private PatientCovid2021DoseCount rule = new PatientCovid2021DoseCount();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqePatient p = new MqePatient();

  @Before
  public void setUp() {
  }

  private void addDose(String cvx, Date adminDate) {
    MqeVaccination v = new MqeVaccination();
    v.setAdminCvxCode(cvx);
    v.setAdminDate(adminDate);
    mr.getVaccinations().add(v);
  }

  private static Date in2021(int month, int day) {
    return new DateTime(2021, month, day, 0, 0).toDate();
  }

  private ValidationRuleResult run() {
    return rule.executeRule(p, mr);
  }

  @Test
  public void threeDosesFiresNothing() {
    for (int i = 0; i < 3; i++) {
      addDose("208", in2021(1, 1 + i));
    }
    assertEquals(0, run().getValidationDetections().size());
  }

  @Test
  public void fourDosesFiresOnly4OrMore() {
    for (int i = 0; i < 4; i++) {
      addDose("208", in2021(1, 1 + i));
    }
    ValidationRuleResult r = run();
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientCovid2021DoseCountIs4OrMore,
        r.getValidationDetections().get(0).getDetection());
  }

  @Test
  public void fiveDosesFires4And5OrMore() {
    for (int i = 0; i < 5; i++) {
      addDose("207", in2021(2, 1 + i));
    }
    ValidationRuleResult r = run();
    assertEquals(2, r.getValidationDetections().size());
    assertTrue(hasDetection(r, Detection.PatientCovid2021DoseCountIs4OrMore));
    assertTrue(hasDetection(r, Detection.PatientCovid2021DoseCountIs5OrMore));
  }

  @Test
  public void sixOrMoreDosesFiresAllThreeThresholds() {
    for (int i = 0; i < 7; i++) {
      addDose("218", in2021(3, 1 + i));
    }
    ValidationRuleResult r = run();
    assertEquals(3, r.getValidationDetections().size());
    assertTrue(hasDetection(r, Detection.PatientCovid2021DoseCountIs4OrMore));
    assertTrue(hasDetection(r, Detection.PatientCovid2021DoseCountIs5OrMore));
    assertTrue(hasDetection(r, Detection.PatientCovid2021DoseCountIs6OrMore));
  }

  @Test
  public void dosesOutside2021AreExcluded() {
    // 4 doses in 2021, plus 4 more just outside the year - should not push the count to 8.
    for (int i = 0; i < 4; i++) {
      addDose("208", in2021(6, 1 + i));
    }
    addDose("208", new DateTime(2020, 12, 31, 0, 0).toDate());
    addDose("208", new DateTime(2022, 1, 1, 0, 0).toDate());
    addDose("208", new DateTime(2020, 1, 1, 0, 0).toDate());
    addDose("208", new DateTime(2022, 12, 31, 0, 0).toDate());
    ValidationRuleResult r = run();
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientCovid2021DoseCountIs4OrMore,
        r.getValidationDetections().get(0).getDetection());
  }

  @Test
  public void nonListedCvxCodesAreExcluded() {
    // 3 counted doses plus 4 non-COVID doses in 2021 - should not reach the 4-dose threshold.
    for (int i = 0; i < 3; i++) {
      addDose("208", in2021(4, 1 + i));
    }
    for (int i = 0; i < 4; i++) {
      addDose("140", in2021(4, 10 + i)); // flu CVX, not in the COVID set
    }
    assertEquals(0, run().getValidationDetections().size());
  }

  private boolean hasDetection(ValidationRuleResult r, Detection d) {
    return r.getValidationDetections().stream().anyMatch(vr -> vr.getDetection() == d);
  }
}
