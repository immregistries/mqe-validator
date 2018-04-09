package org.immregistries.dqa.validator.engine.common;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.validator.detection.DetectionType;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaAddress;
import org.immregistries.dqa.vxu.MetaFieldInfoData;

/**
 * This is to evaluate the basic expectations for an address in the system this does not guarantee
 * that the address is real or that the street address is properly formatted. this evaluates the
 * very minimum required for the address to be evaluated.
 *
 * @author Josh Hull
 */
public enum AddressValidator {
  INSTANCE;
  private CommonRules common = CommonRules.INSTANCE;

  public ValidationRuleResult getAddressIssuesFor(AddressFields fields, DqaAddress a, MetaFieldInfoData meta) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    if (a == null) {
      issues.add(new ValidationReport(fields.getAddress(), DetectionType.MISSING, meta));
      passed = false;
    } else {
      if (common.isEmpty(a.getStreet())) {
        issues.add(new ValidationReport(fields.getStreetField(), DetectionType.MISSING, meta));
      }
      if (common.isEmpty(a.getCity())) {
        issues.add(new ValidationReport(fields.getCityField(), DetectionType.MISSING, meta));
      }
      if (common.isEmpty(a.getStateCode())) {
        issues.add(new ValidationReport(fields.getStateField(), DetectionType.MISSING, meta));
      }
      if (common.isEmpty(a.getZip())) {
        issues.add(new ValidationReport(fields.getZipField(), DetectionType.MISSING, meta));
      }

      if (issues.size() > 0) {
        passed = false;
        issues.add(new ValidationReport(fields.getAddress(), DetectionType.INCOMPLETE, meta));
        //it can pass if it has the four basic ingredients.
      }

      if (common.isEmpty(a.getCountyParishCode())) {
        issues.add(new ValidationReport(fields.getCountyField(), DetectionType.MISSING, meta));
      }
      if (common.isEmpty(a.getCountryCode())) {
        issues.add(new ValidationReport(fields.getCountryField(), DetectionType.MISSING, meta));
      }
    }

    ValidationRuleResult result = new ValidationRuleResult();
    result.setValidationDetections(issues);
    result.setRulePassed(passed);
    return result;
  }

  public boolean isValid(DqaAddress a) {
    return
           StringUtils.isNotBlank(a.getStateCode())
        && StringUtils.isNotBlank(a.getCity())
        && StringUtils.isNotBlank(a.getStreet())
        && StringUtils.isNotBlank(a.getZip());
  }

}
