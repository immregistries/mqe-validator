package org.immregistries.mqe.validator.engine.codes;

import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.core.util.DateUtility;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;

/**
 * Scores how much a submitted dose "looks administered" (as opposed to historical), based on how
 * much supporting data was sent and how recently the dose was given. Drives both
 * {@code MQE0327}/{@link org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationSourceIsAdministeredButAppearsHistorical}
 * (sender marked administered, but the score says historical) and
 * {@code MQE0329}/{@link org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationSourceIsHistoricalButAppearsAdministered}
 * (sender marked historical, but the score says administered) - see
 * {@link #administeredLikelihoodScore(MqeVaccination, MqeMessageReceived)} for the point table.
 * Each of those two rules applies its own {@code >= 10} / {@code < 10} threshold check against the
 * score returned here.
 */
public enum AdministeredLikelihood {
  INSTANCE;

  /** Score at or above which a dose is considered to "look administered" (below: "looks historical"). */
  public static final int ADMINISTERED_LIKELY_THRESHOLD = 10;

  private DateUtility datr = DateUtility.INSTANCE;

  /**
   * Point table (max possible score: 26):
   * <ul>
   *   <li>Admin date within 1 month of the message's received date: +5</li>
   *   <li>Lot number present: +2</li>
   *   <li>Expiration date present: +2</li>
   *   <li>Manufacturer (MVX) code present: +2</li>
   *   <li>Financial eligibility code present: +2</li>
   *   <li>Body route code present: +1</li>
   *   <li>Body site code present: +1</li>
   *   <li>Amount present and not "999" or "0": +3</li>
   *   <li>Facility ID or facility name present: +4</li>
   *   <li>"Given by" person (ID or first/last name) present: +4</li>
   * </ul>
   * See {@link #ADMINISTERED_LIKELY_THRESHOLD} for how callers interpret the result.
   */
  public int administeredLikelihoodScore(MqeVaccination vaccination, MqeMessageReceived message) {

    // Created rough scoring system that gives a point to other attributes
    // that suggest a vaccination
    // was administered or not. The idea is that if the sender knows a lot
    // about the vaccination and it
    // was given recently then it is probably administered. Otherwise it must
    // be historical.
    int administeredScore = 0;
    if (vaccination.getAdminDate() != null) {
      int elapsed = datr.monthsBetween(vaccination.getAdminDate(), message.getReceivedDate());

      if (elapsed < 1) {
        administeredScore += 5;
      }
    }
    if (!StringUtils.isBlank(vaccination.getLotNumber())) {
      administeredScore += 2;
    }
    if (vaccination.getExpirationDate() != null) {
      administeredScore += 2;
    }
    if (!StringUtils.isBlank(vaccination.getManufacturerCode())) {
      administeredScore += 2;
    }
    if (!StringUtils.isBlank(vaccination.getFinancialEligibilityCode())) {
      administeredScore += 2;
    }
    if (!StringUtils.isBlank(vaccination.getBodyRouteCode())) {
      administeredScore += 1;
    }
    if (!StringUtils.isBlank(vaccination.getBodySiteCode())) {
      administeredScore += 1;
    }
    if (!StringUtils.isBlank(vaccination.getAmount()) && !vaccination.getAmount().equals("999")
        && !vaccination.getAmount().equals("0")) {
      administeredScore += 3;
    }
    if (!StringUtils.isBlank(vaccination.getFacilityIdNumber())
        || !StringUtils.isBlank(vaccination.getFacilityName())) {
      administeredScore += 4;
    }
    if (vaccination.getGivenBy() != null || !StringUtils.isBlank(vaccination.getGivenByNameFirst())
        || !StringUtils.isBlank(vaccination.getGivenByNameLast())) {
      administeredScore += 4;
    }

    return administeredScore;

  }
}
