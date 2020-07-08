package org.immregistries.mqe.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.address.SmartyStreetResponse;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
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
    this.addRuleDetection(Detection.NextOfKinAddressIsMissing);
    this.addRuleDetection(Detection.NextOfKinAddressIsInvalid);
    this.addRuleDetection(Detection.NextOfKinAddressStreetIsMissing);
    this.addRuleDetection(Detection.NextOfKinAddressCityIsMissing);
    this.addRuleDetection(Detection.NextOfKinAddressStateIsMissing);
    this.addRuleDetection(Detection.NextOfKinAddressCountyIsMissing);
    this.addRuleDetection(Detection.NextOfKinAddressCountryIsMissing);
    this.addRuleDetection(Detection.NextOfKinAddressZipIsMissing);
    this.addRuleDetection(Detection.NextOfKinAddressTypeIsMissing);
    this.addRuleDetection(Detection.NextOfKinAddressTypeIsValuedBadAddress);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinAddressTypeIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinAddressTypeIsInvalid);
      id.setImplementationDescription("Address not valid according to Smarty Streets.");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeNextOfKin target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    MqeAddress nokAddress = target.getAddress();

    ValidationRuleResult addrResult =
        addressValidator.getAddressIssuesFor(fields, nokAddress, target);

    issues.addAll(addrResult.getValidationDetections());

    if (nokAddress != null) {

      if (props.isAddressCleanserEnabled()) {
        if (nokAddress != null && !nokAddress.isClean()) {
          ValidationReport r = Detection.NextOfKinAddressIsInvalid.build(target);
          List<SmartyStreetResponse> rList =
              SmartyStreetResponse.codesFromDpv(nokAddress.getCleansingResultCode());
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
        issues.add(
            Detection.NextOfKinAddressTypeIsValuedBadAddress.build(nokAddress.toString(), target));
      }

      issues.addAll(this.codr.handleCodeOrMissing(nokAddress.getTypeCode(),
          VxuField.NEXT_OF_KIN_ADDRESS_TYPE, target));
    }

    passed = issues.size() == 0;

    return buildResults(issues, passed);
  }

}
