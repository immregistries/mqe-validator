package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Date;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

/**
 * Covers issue #99's acceptance criteria: each exact day 2-7 fires its own detection, and nearby
 * non-matching days (0, 1, 8+) fire nothing from this rule.
 */
public class VaccinationCreationExactDayTimelinessTest {

  private VaccinationCreationExactDayTimeliness rule = new VaccinationCreationExactDayTimeliness();
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();

  private static final Date ADMIN_DATE = new DateTime(2026, 1, 1, 0, 0).toDate();

  @Before
  public void setUp() {
    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    mr.getVaccinations().add(v);
    v.setAdminDate(ADMIN_DATE);
  }

  private void setEntryDaysAfterAdmin(int days) {
    v.setSystemEntryDate(new DateTime(ADMIN_DATE).plusDays(days).toDate());
  }

  @Test
  public void exactly2DaysFiresOnlyThatDetection() {
    setEntryDaysAfterAdmin(2);
    assertFiresOnly(Detection.VaccinationCreationIsExactly2Days);
  }

  @Test
  public void exactly3DaysFiresOnlyThatDetection() {
    setEntryDaysAfterAdmin(3);
    assertFiresOnly(Detection.VaccinationCreationIsExactly3Days);
  }

  @Test
  public void exactly4DaysFiresOnlyThatDetection() {
    setEntryDaysAfterAdmin(4);
    assertFiresOnly(Detection.VaccinationCreationIsExactly4Days);
  }

  @Test
  public void exactly5DaysFiresOnlyThatDetection() {
    setEntryDaysAfterAdmin(5);
    assertFiresOnly(Detection.VaccinationCreationIsExactly5Days);
  }

  @Test
  public void exactly6DaysFiresOnlyThatDetection() {
    setEntryDaysAfterAdmin(6);
    assertFiresOnly(Detection.VaccinationCreationIsExactly6Days);
  }

  @Test
  public void exactly7DaysFiresOnlyThatDetection() {
    setEntryDaysAfterAdmin(7);
    assertFiresOnly(Detection.VaccinationCreationIsExactly7Days);
  }

  @Test
  public void dayZeroFiresNothing() {
    setEntryDaysAfterAdmin(0);
    assertFiresNothing();
  }

  @Test
  public void dayOneFiresNothing() {
    setEntryDaysAfterAdmin(1);
    assertFiresNothing();
  }

  @Test
  public void dayEightAndBeyondFireNothing() {
    setEntryDaysAfterAdmin(8);
    assertFiresNothing();
    setEntryDaysAfterAdmin(30);
    assertFiresNothing();
  }

  private void assertFiresOnly(Detection expected) {
    ValidationRuleResult r = rule.executeRule(v, mr);
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(expected, r.getValidationDetections().get(0).getDetection());
    assertTrue("Should pass (informational ACCEPT-severity signal)", r.isRulePassed());
  }

  private void assertFiresNothing() {
    ValidationRuleResult r = rule.executeRule(v, mr);
    assertEquals(0, r.getValidationDetections().size());
    assertTrue(r.isRulePassed());
  }
}
