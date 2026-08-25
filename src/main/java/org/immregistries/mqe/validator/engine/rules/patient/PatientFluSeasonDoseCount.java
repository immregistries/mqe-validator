package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.common.DoseCounting;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.TargetType;
import org.joda.time.DateTime;
import org.joda.time.Years;

/**
 * Experimental mismerge signal (issue #101) flagging patients with 2 or more flu doses within a
 * single flu season (September through March, spanning the calendar-year boundary), counting
 * only doses where the patient was 10 or older at the time of that specific vaccination.
 * Intentionally hard-coded - not a general config-driven mechanism (see
 * {@link org.immregistries.mqe.validator.engine.common.DoseCounting}).
 *
 * <p>A "flu season" runs Sep(year) through Mar(year+1), labeled by its starting year - e.g. a
 * dose given in Nov 2025 or Feb 2026 both belong to the "2025" season. Doses given Apr-Aug fall
 * outside any flu season window and are never counted. This rule fires at most once per patient:
 * it checks every season the patient has qualifying doses in, and fires if <b>any</b> single
 * season reaches the threshold - it does not require the doses to literally be counted twice.
 */
@ValidationRuleEntry(TargetType.Patient)
public class PatientFluSeasonDoseCount extends ValidationRule<MqePatient> {

  private static final Set<String> FLU_CVX_CODES = new HashSet<>(Arrays.asList(
      "205", "168", "197", "135", "161", "166", "149", "111", "171", "186", "153", "320", "185",
      "155", "144", "231", "200", "201", "202", "150", "158", "140", "141"));
  private static final int MIN_AGE_YEARS = 10;
  private static final int THRESHOLD = 2;

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientBirthDateIsValid.class};
  }

  public PatientFluSeasonDoseCount() {
    super();
    ImplementationDetail id = this.addRuleDetection(Detection.PatientFluSeasonDoseCountIs2OrMore);
    id.setImplementationDescription("Patient has 2 or more doses of CVX " + FLU_CVX_CODES
        + " within a single flu season (September through March, spanning the calendar-year "
        + "boundary), counting only doses where the patient was 10 or older at the time of that "
        + "specific vaccination.");
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();

    DateTime birthDate = new DateTime(target.getBirthDate());
    Set<String> candidateSeasons = candidateSeasons(m.getVaccinations());

    for (String season : candidateSeasons) {
      int count = DoseCounting.countMatchingDoses(m.getVaccinations(), FLU_CVX_CODES,
          adminDate -> season.equals(seasonKey(adminDate))
              && ageAtDate(birthDate, adminDate) >= MIN_AGE_YEARS);
      if (count >= THRESHOLD) {
        issues.add(Detection.PatientFluSeasonDoseCountIs2OrMore.build(target));
        break;
      }
    }

    return buildResults(issues, true);
  }

  private static Set<String> candidateSeasons(List<MqeVaccination> vaccinations) {
    Set<String> seasons = new HashSet<>();
    for (MqeVaccination v : vaccinations) {
      if (FLU_CVX_CODES.contains(v.getAdminCvxCode()) && v.getAdminDate() != null) {
        String season = seasonKey(new DateTime(v.getAdminDate()));
        if (season != null) {
          seasons.add(season);
        }
      }
    }
    return seasons;
  }

  private static int ageAtDate(DateTime birthDate, DateTime date) {
    return Years.yearsBetween(birthDate.toLocalDate(), date.toLocalDate()).getYears();
  }

  /**
   * @return the season this date falls in, labeled by its starting year (e.g. "2025" for the
   *     season running Sep 2025 - Mar 2026), or {@code null} if the date is outside any flu
   *     season window (Apr-Aug).
   */
  private static String seasonKey(DateTime adminDate) {
    int month = adminDate.getMonthOfYear();
    int year = adminDate.getYear();
    if (month >= 9) {
      return String.valueOf(year);
    } else if (month <= 3) {
      return String.valueOf(year - 1);
    } else {
      return null;
    }
  }

}
