package org.immregistries.mqe.validator.engine.codes;

import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.core.util.DateUtility;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;

public enum AdministeredLikelihood {
  INSTANCE;

  private DateUtility datr = DateUtility.INSTANCE;

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
