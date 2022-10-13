package org.immregistries.mqe.validator.engine.common;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.DetectionType;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MetaFieldInfoData;
import org.immregistries.mqe.vxu.MqeAddress;
import org.immregistries.mqe.vxu.VxuField;

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

  private List<ValidationReport> buildMissing(VxuField f, MetaFieldInfoData meta) {
    List<ValidationReport> vr = new ArrayList<>();
    Detection d = Detection.get(f, DetectionType.MISSING);
    if (d!=null) {
      vr.add(d.build(meta));
    }
    return vr;
  }

  private List<ValidationReport> buildPresent(VxuField f, MetaFieldInfoData meta) {
    List<ValidationReport> vr = new ArrayList<>();
    Detection d = Detection.get(f, DetectionType.PRESENT);
    if (d!=null) {
      vr.add(d.build(meta));
    }
    return vr;
  }
  
  private List<ValidationReport> buildIncomplete(VxuField f, MetaFieldInfoData meta) {
    List<ValidationReport> vr = new ArrayList<>();
    Detection d = Detection.get(f, DetectionType.INCOMPLETE);
    if (d!=null) {
      vr.add(d.build(meta));
    }
    return vr;
  }

  public ValidationRuleResult getAddressIssuesFor(AddressFields fields, MqeAddress a, MetaFieldInfoData meta) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    if (a == null) {
        issues.addAll(buildMissing(fields.getAddress(), meta));
        passed = false;
    } else {
      if(this.isValid(a)) {
        issues.addAll(buildPresent(fields.getAddress(), meta));
      } else {
        issues.addAll(buildMissing(fields.getAddress(), meta));
      }

      if (common.isEmpty(a.getStreet())) {
        issues.addAll(buildMissing(fields.getStreetField(), meta));
      } else {
        issues.addAll(buildPresent(fields.getStreetField(), meta));
      }
      if (common.isEmpty(a.getCity())) {
        issues.addAll(buildMissing(fields.getCityField(), meta));
      } else {
        issues.addAll(buildPresent(fields.getCityField(), meta));
      }
      if (common.isEmpty(a.getStateCode())) {
        issues.addAll(buildMissing(fields.getStateField(), meta));
      } else {
        issues.addAll(buildPresent(fields.getStateField(), meta));
      }
      if (common.isEmpty(a.getZip())) {
        issues.addAll(buildMissing(fields.getZipField(), meta));
      } else {
        issues.addAll(buildPresent(fields.getZipField(), meta));
      }

      if (issues.size() > 0) {
        passed = verifyNoIssuesExceptPresent(issues);
        issues.addAll(buildIncomplete(fields.getAddress(), meta));
        //it can pass if it has the four basic ingredients.
      }

      if (common.isEmpty(a.getCountyParishCode())) {
        issues.addAll(buildMissing(fields.getCountyField(), meta));
      } else {
        issues.addAll(buildPresent(fields.getCountyField(), meta));
      }
      if (common.isEmpty(a.getCountryCode())) {
        issues.addAll(buildMissing(fields.getCountryField(), meta));
      } else {
        issues.addAll(buildPresent(fields.getCountryField(), meta));
      }
    }

    ValidationRuleResult result = new ValidationRuleResult();
    result.setValidationDetections(issues);
    result.setRulePassed(passed);
    return result;
  }

  public boolean isValid(MqeAddress a) {
    return
           StringUtils.isNotBlank(a.getStateCode())
        && StringUtils.isNotBlank(a.getCity())
        && StringUtils.isNotBlank(a.getStreet())
        && StringUtils.isNotBlank(a.getZip());
  }

  protected boolean verifyNoIssuesExceptPresent(List<ValidationReport> issues) {
    if (issues.size() == 0) {
      return true;
    }
    for (ValidationReport vr : issues) {
      if (vr.getDetection().getDetectionType() != DetectionType.PRESENT) {
        return false;
      }
    }
    return true;
  }

}
