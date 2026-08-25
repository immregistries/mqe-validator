package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertEquals;
import java.util.Date;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

/**
 * Covers issue #101's acceptance criteria: season boundary behavior (Sep-Mar crossing the
 * calendar-year boundary), the age-10 condition, positive/negative threshold cases, and
 * off-window doses.
 */
public class PatientFluSeasonDoseCountTest {

  private PatientFluSeasonDoseCount rule = new PatientFluSeasonDoseCount();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqePatient p = new MqePatient();

  private static final Date BIRTH_DATE_AGE_12_AS_OF_2025 = new DateTime(2013, 6, 1, 0, 0).toDate();

  @Before
  public void setUp() {
    p.setBirthDate(BIRTH_DATE_AGE_12_AS_OF_2025);
  }

  private void addDose(String cvx, Date adminDate) {
    MqeVaccination v = new MqeVaccination();
    v.setAdminCvxCode(cvx);
    v.setAdminDate(adminDate);
    mr.getVaccinations().add(v);
  }

  private static Date date(int year, int month, int day) {
    return new DateTime(year, month, day, 0, 0).toDate();
  }

  private boolean fires() {
    ValidationRuleResult r = rule.executeRule(p, mr);
    return r.getValidationDetections().stream()
        .anyMatch(d -> d.getDetection() == Detection.PatientFluSeasonDoseCountIs2OrMore);
  }

  @Test
  public void oneDoseFiresNothing() {
    addDose("140", date(2025, 10, 1));
    assertEquals(false, fires());
  }

  @Test
  public void twoDosesSameSeasonWithinCalendarYearFires() {
    addDose("140", date(2025, 9, 15));
    addDose("140", date(2025, 11, 1));
    assertEquals(true, fires());
  }

  @Test
  public void twoDosesSameSeasonCrossingYearBoundaryFires() {
    // Nov 2025 and Feb 2026 are both in the "2025" season (Sep 2025 - Mar 2026).
    addDose("140", date(2025, 11, 15));
    addDose("140", date(2026, 2, 1));
    assertEquals(true, fires());
  }

  @Test
  public void twoDosesInDifferentSeasonsDoesNotFire() {
    // Mar 2025 belongs to the "2024" season; Sep 2025 starts the "2025" season - different seasons.
    addDose("140", date(2025, 3, 1));
    addDose("140", date(2025, 9, 1));
    assertEquals(false, fires());
  }

  @Test
  public void dosesOutsideSeptemberThroughMarchWindowAreExcluded() {
    // Apr-Aug 2025 are all outside any flu season window.
    addDose("140", date(2025, 4, 1));
    addDose("140", date(2025, 6, 15));
    addDose("140", date(2025, 8, 31));
    assertEquals(false, fires());
  }

  @Test
  public void nonListedCvxCodesAreExcluded() {
    addDose("140", date(2025, 10, 1));
    addDose("208", date(2025, 10, 15)); // COVID CVX, not in the flu set
    assertEquals(false, fires());
  }

  @Test
  public void underAge10DosesAreExcluded() {
    p.setBirthDate(date(2020, 1, 1)); // 5 years old as of the 2025 season
    addDose("140", date(2025, 9, 15));
    addDose("140", date(2025, 11, 1));
    assertEquals(false, fires());
  }

  @Test
  public void ageIsCheckedPerDoseNotOnceForTheWholeSeason() {
    // Patient turns 10 in Dec 2025, mid-season (the "2025" season runs Sep 2025 - Mar 2026).
    p.setBirthDate(date(2015, 12, 15));
    addDose("140", date(2025, 10, 1)); // still 9 at this dose - must not count
    addDose("140", date(2026, 1, 1)); // 10 by now - counts
    // Only 1 of the 2 doses in this season qualifies by age, so this must not fire yet.
    assertEquals(false, fires());

    // A second post-birthday dose in the same season brings the qualifying count to 2.
    addDose("140", date(2026, 2, 1));
    assertEquals(true, fires());
  }

  @Test
  public void threeDosesInOneSeasonStillFiresOnlyOnce() {
    addDose("140", date(2025, 9, 1));
    addDose("140", date(2025, 10, 1));
    addDose("140", date(2025, 11, 1));
    ValidationRuleResult r = rule.executeRule(p, mr);
    assertEquals(1, r.getValidationDetections().size());
  }
}
