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
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinAddressIsMissing);
      id.setHowToFix("Indicate the address for the next-of-kin. ");
      id.setWhyToFix("Most IIS do not read the next-of-kin address, but some do look for an address here. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinAddressIsInvalid);
      id.setHowToFix("The address for the next-of-kin is not considered a valid address.  Please review the address for all persons connected with this patient and either remove it or update it. ");
      id.setWhyToFix("Next-of-kin address may be used for Reminder/Recall purposes. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinAddressStreetIsMissing);
      id.setHowToFix("The first line of the next-of-kin address is missing. Please review the address for all persons connected with this patient and either remove it or update it.");
      id.setWhyToFix("Next-of-kin address may be used for Reminder/Recall purposes. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinAddressCityIsMissing);
      id.setHowToFix("The city of the next-of-kin address is missing. Please review the address for all persons connected with this patient and either remove it or update it.");
      id.setWhyToFix("Next-of-kin address may be used for Reminder/Recall purposes. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinAddressStateIsMissing);
      id.setHowToFix("The state of the next-of-kin address is missing. Please review the address for all persons connected with this patient and either remove it or update it.");
      id.setWhyToFix("Next-of-kin address may be used for Reminder/Recall purposes. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinAddressCountyIsMissing);
      id.setHowToFix("Send county with the next-of-kin address. ");
      id.setWhyToFix("No known reason for why an IIS would need to know the next-of-kin county. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinAddressCountryIsMissing);
      id.setHowToFix("Contact the software vendor and request that when sending an address to ensure that the country is indicated. ");
      id.setWhyToFix("Some IIS might not read the address correctly unless the country is indicated. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinAddressZipIsMissing);
      id.setHowToFix("The zip of the next-of-kin address is missing. Please review the address for all persons connected with this patient and either remove it or update it.");
      id.setWhyToFix("Next-of-kin address may be used for Reminder/Recall purposes. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinAddressTypeIsMissing);
      id.setHowToFix("Contact the software vendor and request that when sending an address to ensure that the address type is indicated. ");
      id.setWhyToFix("Some IIS might not read the address correctly unless the address type is indicated. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinAddressTypeIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("Contact the software vendor and request that when sending an address to ensure that the address type is indicated with a valid address type code. ");
      id.setWhyToFix("Some IIS might not read the address correctly unless the address type is indicated properly. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.NextOfKinAddressTypeIsValuedBadAddress);
      id.setHowToFix("The address for the next-of-kin has been marked in your system as a bad address. The IIS should ignore this address but ideally it should never be sent. Contact your software vendor to ask that bad addresses be excluded from being sent to IIS. ");
      id.setWhyToFix("Next-of-kin address may be used for Reminder/Recall purposes, but bad addresses are not useful to IIS and may be improperly understood as a good adddress.  ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinAddressTypeIsInvalid);
      id.setImplementationDescription("Address not valid according to Smarty Streets.");
      id.setHowToFix("Contact the software vendor and request that when sending an address to ensure that this address type is not submitted to the IIS. ");
      id.setWhyToFix("The IIS may not be able to support every type of address. ");
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
