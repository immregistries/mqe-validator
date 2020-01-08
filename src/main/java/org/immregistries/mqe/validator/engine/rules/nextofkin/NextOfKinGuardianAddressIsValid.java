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

/**
 * Created by Allison on 5/9/2017.
 */
public class NextOfKinGuardianAddressIsValid extends ValidationRule<MqeNextOfKin> {

  private AddressFields fields = new AddressFields(VxuField.PATIENT_GUARDIAN_ADDRESS,
      VxuField.PATIENT_GUARDIAN_ADDRESS_STREET, VxuField.PATIENT_GUARDIAN_ADDRESS_STREET2,
      VxuField.PATIENT_GUARDIAN_ADDRESS_CITY, VxuField.PATIENT_GUARDIAN_ADDRESS_STATE,
      VxuField.PATIENT_GUARDIAN_ADDRESS_COUNTY, VxuField.PATIENT_GUARDIAN_ADDRESS_COUNTRY,
      VxuField.PATIENT_GUARDIAN_ADDRESS_ZIP, VxuField.PATIENT_GUARDIAN_ADDRESS_TYPE);

  private AddressValidator addressValidator = AddressValidator.INSTANCE;

  public NextOfKinGuardianAddressIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientGuardianAddressIsMissing);
      id.setHowToFix("Indicate the address for the guardian/parent. ");
      id.setWhyToFix("Most IIS do not read the guardian/parent address, but some do look for an address here. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientGuardianAddressIsInvalid);
      id.setHowToFix("The address for the guardian/parent is not considered a valid address.  Please review the address for all persons connected with this patient and either remove it or update it. ");
      id.setWhyToFix("Guardian/parent address may be used for Reminder/Recall purposes. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianAddressStreetIsMissing);
      id.setHowToFix("The first line of the guardian/parent address is missing. Please review the address for all persons connected with this patient and either remove it or update it.");
      id.setWhyToFix("Guardian/parent address may be used for Reminder/Recall purposes. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianAddressCityIsMissing);
      id.setHowToFix("The city of the guardian/parent address is missing. Please review the address for all persons connected with this patient and either remove it or update it.");
      id.setWhyToFix("Guardian/parent address may be used for Reminder/Recall purposes. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianAddressStateIsMissing);
      id.setHowToFix("The state of the guardian/parent address is missing. Please review the address for all persons connected with this patient and either remove it or update it.");
      id.setWhyToFix("Guardian/parent address may be used for Reminder/Recall purposes. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianAddressCountyIsMissing);
      id.setHowToFix("Send county with the guardian/parent address. ");
      id.setWhyToFix("No known reason for why an IIS would need to know the guardian/parent county. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianAddressCountryIsMissing);
      id.setHowToFix("Contact the software vendor and request that when sending a guardian/parent address to ensure that the country is indicated. ");
      id.setWhyToFix("Some IIS might not read the address correctly unless the country is indicated. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientGuardianAddressZipIsMissing);
      id.setHowToFix("The zip code of the guardian/parent address is missing. Please review the address for all persons connected with this patient and either remove it or update it.");
      id.setWhyToFix("Guardian/parent address may be used for Reminder/Recall purposes. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianAddressTypeIsMissing);
      id.setHowToFix("Contact the software vendor and request that when sending a guardian/parent address to ensure that the address type is indicated. ");
      id.setWhyToFix("Some IIS might not read the address correctly unless the address type is indicated. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianAddressTypeIsUnrecognized);
      id.setHowToFix("Contact the software vendor and request that when sending a guardian/parent address to ensure that the address type is indicated with a valid address type code. ");
      id.setWhyToFix("Some IIS might not read the address correctly unless the address type is indicated properly. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianAddressTypeIsValuedBadAddress);
      id.setHowToFix("The address for the guardian/parent has been marked in your system as a bad address. The IIS should ignore this address but ideally it should never be sent. Contact your software vendor to ask that bad addresses be excluded from being sent to IIS. ");
      id.setWhyToFix("Guardian/parent address may be used for Reminder/Recall purposes, but bad addresses are not useful to the IIS and may be improperly understood as a good address.  ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientGuardianAddressIsInvalid);
      id.setImplementationDescription("Address not valid according to Smarty Streets.");
      id.setHowToFix("Contact the software vendor and request that when sending a guardian/parent address to ensure that this address type is not submitted to the IIS. ");
      id.setWhyToFix("The IIS may not be able to support every type of address. ");
    }
    if (props.isAddressCleanserEnabled()) {
      this.addRuleDetection(Detection.PatientGuardianAddressIsInvalid);
    }
  }

  protected ValidationRuleResult executeRule(MqeNextOfKin target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    // TODO: finish this and test it--is guardian the responsible party? if so, this should already
    // be addressed by the guardian/parent address validation

    MqeAddress nokAddress = target.getAddress();
    //MqeAddress p = m.getPatient().getPatientAddress();

    ValidationRuleResult addrResult =
        addressValidator.getAddressIssuesFor(fields, nokAddress, target);

    issues.addAll(addrResult.getValidationDetections());

    if (nokAddress != null) {

      if (props.isAddressCleanserEnabled()) {
        if (nokAddress != null && !nokAddress.isClean()) {
          ValidationReport r = Detection.PatientGuardianAddressIsInvalid.build(target);
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
        issues.add(Detection.PatientGuardianAddressTypeIsValuedBadAddress
            .build(nokAddress.toString(), target));
      }

      issues.addAll(this.codr.handleCodeOrMissing(nokAddress.getTypeCode(),
          VxuField.PATIENT_GUARDIAN_ADDRESS_TYPE, target));
    }

    passed = (issues.size() == 0);

    return buildResults(issues, passed);
  }
}
