package org.immregistries.mqe.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.address.SmartyStreetResponse;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.common.AddressFields;
import org.immregistries.mqe.validator.engine.common.AddressValidator;
import org.immregistries.mqe.vxu.MqeAddress;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;
import org.immregistries.mqe.vxu.VxuField;

public class NextOfKinAddressIsValid extends ValidationRule<MqeNextOfKin> {

  private AddressFields fields = new AddressFields(VxuField.NEXT_OF_KIN_ADDRESS,
      VxuField.NEXT_OF_KIN_ADDRESS_STREET, VxuField.NEXT_OF_KIN_ADDRESS_STREET2,
      VxuField.NEXT_OF_KIN_ADDRESS_CITY, VxuField.NEXT_OF_KIN_ADDRESS_STATE,
      VxuField.NEXT_OF_KIN_ADDRESS_COUNTY, VxuField.NEXT_OF_KIN_ADDRESS_COUNTRY,
      VxuField.NEXT_OF_KIN_ADDRESS_ZIP, VxuField.NEXT_OF_KIN_ADDRESS_TYPE);

  private AddressValidator addressValidator = AddressValidator.INSTANCE;

  public NextOfKinAddressIsValid() {
    this.addRuleDocumentation(this.codr.getDetectionsForField(VxuField.NEXT_OF_KIN_ADDRESS));
    this.addRuleDocumentation(this.codr.getDetectionsForField(VxuField.NEXT_OF_KIN_ADDRESS_STREET));
    this.addRuleDocumentation(this.codr.getDetectionsForField(VxuField.NEXT_OF_KIN_ADDRESS_STREET2));
    this.addRuleDocumentation(this.codr.getDetectionsForField(VxuField.NEXT_OF_KIN_ADDRESS_COUNTY));
    this.addRuleDocumentation(this.codr.getDetectionsForField(VxuField.NEXT_OF_KIN_ADDRESS_CITY));
    this.addRuleDocumentation(this.codr.getDetectionsForField(VxuField.NEXT_OF_KIN_ADDRESS_COUNTRY));
    this.addRuleDocumentation(this.codr.getDetectionsForField(VxuField.NEXT_OF_KIN_ADDRESS_ZIP));
    this.addRuleDocumentation(this.codr.getDetectionsForField(VxuField.NEXT_OF_KIN_ADDRESS_TYPE));
    
    this.addImplementationMessage(Detection.NextOfKinAddressIsInvalid, "Next of kin Address is invalid according to Smarty Streets.");

    if (props.isAddressCleanserEnabled()) {
      this.addRuleDocumentation(Detection.NextOfKinAddressIsInvalid);
    }
    
  }

  @Override
  protected ValidationRuleResult executeRule(MqeNextOfKin target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    MqeAddress nokAddress = target.getAddress();

    ValidationRuleResult addrResult = addressValidator
        .getAddressIssuesFor(fields, nokAddress, target);

    issues.addAll(addrResult.getValidationDetections());

    if (nokAddress != null) {

      if (props.isAddressCleanserEnabled()) {
        if (nokAddress != null && !nokAddress.isClean()) {
          ValidationReport r = Detection.NextOfKinAddressIsInvalid.build(target);
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
