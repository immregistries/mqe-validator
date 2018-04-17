package org.immregistries.dqa.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.validator.address.SmartyStreetResponse;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.common.AddressFields;
import org.immregistries.dqa.validator.engine.common.AddressValidator;
import org.immregistries.dqa.vxu.DqaAddress;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;
import org.immregistries.dqa.vxu.VxuField;

public class NextOfKinAddressIsValid extends ValidationRule<DqaNextOfKin> {

  private AddressFields fields = new AddressFields(VxuField.NEXT_OF_KIN_ADDRESS,
      VxuField.NEXT_OF_KIN_ADDRESS_STREET, VxuField.NEXT_OF_KIN_ADDRESS_STREET2,
      VxuField.NEXT_OF_KIN_ADDRESS_CITY, VxuField.NEXT_OF_KIN_ADDRESS_STATE,
      VxuField.NEXT_OF_KIN_ADDRESS_COUNTY, VxuField.NEXT_OF_KIN_ADDRESS_COUNTRY,
      VxuField.NEXT_OF_KIN_ADDRESS_ZIP, VxuField.NEXT_OF_KIN_ADDRESS_TYPE);

  private AddressValidator addressValidator = AddressValidator.INSTANCE;

  public NextOfKinAddressIsValid() {

    this.ruleDetections.addAll(Arrays.asList(
        Detection.NextOfKinAddressIsDifferentFromPatientAddress,
        Detection.NextOfKinAddressTypeIsValuedBadAddress));

    this.ruleDetections.addAll(this.codr.getDetectionsForField(VxuField.NEXT_OF_KIN_ADDRESS));
    this.ruleDetections
        .addAll(this.codr.getDetectionsForField(VxuField.NEXT_OF_KIN_ADDRESS_STREET));
    this.ruleDetections
        .addAll(this.codr.getDetectionsForField(VxuField.NEXT_OF_KIN_ADDRESS_STREET2));
    this.ruleDetections
        .addAll(this.codr.getDetectionsForField(VxuField.NEXT_OF_KIN_ADDRESS_COUNTY));
    this.ruleDetections
        .addAll(this.codr.getDetectionsForField(VxuField.NEXT_OF_KIN_ADDRESS_COUNTRY));
    this.ruleDetections.addAll(this.codr.getDetectionsForField(VxuField.NEXT_OF_KIN_ADDRESS_ZIP));
    this.ruleDetections.addAll(this.codr.getDetectionsForField(VxuField.NEXT_OF_KIN_ADDRESS_TYPE));

    if (props.isAddressCleanserEnabled()) {
      this.ruleDetections.add(Detection.NextOfKinAddressIsInvalid);
    }
  }

  @Override
  protected ValidationRuleResult executeRule(DqaNextOfKin target, DqaMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    DqaAddress nokAddress = target.getAddress();
    DqaAddress p = m.getPatient().getPatientAddress();

    ValidationRuleResult addrResult = addressValidator
        .getAddressIssuesFor(fields, nokAddress, target);

    issues.addAll(addrResult.getValidationDetections());

    if (nokAddress != null) {

      if (props.isAddressCleanserEnabled()) {
        if (nokAddress != null && !nokAddress.isClean()) {
          ValidationReport r = Detection.PatientGuardianAddressIsInvalid.build(target);
          List<SmartyStreetResponse> rList = SmartyStreetResponse
              .codesFromDpv(nokAddress.getCleansingResultCode());
          if (rList.size() > 0) {
            StringBuilder b = new StringBuilder(":");
            for (SmartyStreetResponse rz : rList) {
              b.append(" ").append(rz.title);
            }
            r.setAdditionalMessage(b.toString());
          }
          issues.add(r);
        }
      }

//TODO: this functionality is also in NextOfKinAddressIsSameAsPatientAddress, which should
//      if (!nokAddress.equals(p)) {
//        // we use?
//        issues.add(Detection.NextOfKinAddressIsDifferentFromPatientAddress.build(
//            nokAddress.toString(), target));
//      }

      if (nokAddress.getTypeCode() != null && "BA".equals(nokAddress.getTypeCode())) {
        issues.add(Detection.NextOfKinAddressTypeIsValuedBadAddress.build(nokAddress.toString(),
            target));
      }

      if (StringUtils.isBlank(nokAddress.getTypeCode())) {
        issues.add(Detection.NextOfKinAddressTypeIsMissing.build(target));
      } else {
        issues.addAll(this.codr
            .handleCode(nokAddress.getTypeCode(), VxuField.NEXT_OF_KIN_ADDRESS_TYPE, target));
      }
    }

    passed = issues.size() == 0;

    return buildResults(issues, passed);
  }

}
