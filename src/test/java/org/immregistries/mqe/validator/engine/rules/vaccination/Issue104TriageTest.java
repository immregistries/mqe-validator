package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.junit.Before;
import org.junit.Test;

/**
 * Regression tests recording the evidence for issue #104's triage findings (originally
 * investigated in {@code docs/changes-needed-2028-08.md}):
 *
 * <ul>
 *   <li>AART #3768/#3671 (MQE0734, VaccinationManufacturerCodeIsPresent): already
 *       administered-only in this codebase since at least July 2021 (commit 0f403dc) - not a
 *       fresh implementation, just previously untested.</li>
 *   <li>AART #3666 (NDC is present): already implemented and unconditionally wired in
 *       {@link VaccinationNdcIsValid} - not a fresh implementation.</li>
 * </ul>
 *
 * <p>Named {@code *Test.java} (not this package's usual {@code *Tester.java} convention) so it
 * actually runs under Maven Surefire's default include patterns - see the #104 PR description for
 * why that distinction matters here.
 */
public class Issue104TriageTest {

  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();

  @Before
  public void setUp() {
    mr.setMessageHeader(mh);
    mr.getVaccinations().add(v);
  }

  // --- MQE0734 / AART #3768, #3671: administered-only guard ---

  @Test
  public void administeredDoseWithValidMfrRaisesOnlyPresent() {
    v.setAdministered(true);
    v.setManufacturerCode("ZLB");
    ValidationRuleResult r = new VaccinationMfrIsValid().executeRule(v, mr);
    assertTrue("Administered dose with a recognized MVX code should raise (only) Present",
        r.getValidationDetections().stream()
            .anyMatch(d -> d.getDetection() == Detection.VaccinationManufacturerCodeIsPresent));
  }

  @Test
  public void historicalDoseRaisesNoManufacturerIssuesAtAll() {
    v.setAdministered(false);
    v.setManufacturerCode("ZLB");
    ValidationRuleResult r = new VaccinationMfrIsValid().executeRule(v, mr);
    assertEquals("Historical dose must raise nothing for MQE0734/MVX - not even Present", 0,
        r.getValidationDetections().size());

    // Not even a missing/unrecognized code should raise anything for a historical dose.
    v.setManufacturerCode(null);
    r = new VaccinationMfrIsValid().executeRule(v, mr);
    assertEquals(0, r.getValidationDetections().size());
  }

  // --- NDC is present / AART #3666 ---

  @Test
  public void ndcPresentFiresWhenValueIsPresent() {
    v.setAdminNdcCode("00003-0857-01");
    ValidationRuleResult r = new VaccinationNdcIsValid().executeRule(v, mr);
    assertTrue("NDC-present detection should fire when a value is submitted",
        r.getValidationDetections().stream()
            .anyMatch(d -> d.getDetection() == Detection.VaccinationNDCCodeIsPresent));
  }

  @Test
  public void ndcPresentFiresRegardlessOfAdministeredFlag() {
    // Unlike MQE0734, NDC-present has no isAdministered() guard - fires for historical too.
    v.setAdministered(false);
    v.setAdminNdcCode("00003-0857-01");
    ValidationRuleResult r = new VaccinationNdcIsValid().executeRule(v, mr);
    assertTrue("NDC-present should fire for historical doses too",
        r.getValidationDetections().stream()
            .anyMatch(d -> d.getDetection() == Detection.VaccinationNDCCodeIsPresent));
  }

  @Test
  public void ndcMissingWhenNoValueSubmitted() {
    v.setAdminNdcCode(null);
    ValidationRuleResult r = new VaccinationNdcIsValid().executeRule(v, mr);
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationNDCCodeIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }
}
