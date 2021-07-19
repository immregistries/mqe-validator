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
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientGuardianAddressIsInvalid);
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianAddressStreetIsMissing);
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianAddressCityIsMissing);
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianAddressStateIsMissing);
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianAddressCountyIsMissing);
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianAddressCountryIsMissing);
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientGuardianAddressZipIsMissing);
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianAddressTypeIsMissing);
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianAddressTypeIsUnrecognized);
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianAddressTypeIsValuedBadAddress);
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientGuardianAddressIsInvalid);
      id.setImplementationDescription("Address not valid according to Smarty Streets.");
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

    passed = verifyNoIssuesExceptPresent(issues);

    return buildResults(issues, passed);
  }
}
