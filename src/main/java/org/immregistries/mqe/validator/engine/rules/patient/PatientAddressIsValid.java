package org.immregistries.mqe.validator.engine.rules.patient;

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
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.VxuField;

public class PatientAddressIsValid extends ValidationRule<MqePatient> {

  private AddressFields fields = new AddressFields(VxuField.PATIENT_ADDRESS,
      VxuField.PATIENT_ADDRESS_STREET, VxuField.PATIENT_ADDRESS_STREET2,
      VxuField.PATIENT_ADDRESS_CITY, VxuField.PATIENT_ADDRESS_STATE,
      VxuField.PATIENT_ADDRESS_COUNTY, VxuField.PATIENT_ADDRESS_COUNTRY,
      VxuField.PATIENT_ADDRESS_ZIP, VxuField.PATIENT_ADDRESS_TYPE);

  private AddressValidator addressValidator = AddressValidator.INSTANCE;

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class,};
  }

  public PatientAddressIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientAddressIsMissing);
      id.setImplementationDescription("Patient Address was not indicated");
      id.setHowToFix(
          "Please ensure that address for this patient has been entered. If the patient does have an address, please verify that the system is configured to send that address to the IIS. ");
      id.setWhyToFix(
          "The patient address is used first to ensure that the patient record is properly matched with other previously submitted data. The address may also be used to generate geographic coverage reports or support reminder/recall activities. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientAddressIsInvalid);
      id.setImplementationDescription(
          "Patient Address is not recognized by the address checker as a valid address");
      id.setHowToFix(
          "Please review and correct the address for this patient, it is not recognized by the address checker. Once the address has been corrected, please resubmit the record. ");
      id.setWhyToFix(
          "Recognizable addresses help to improve patient matching, allow geographic analysis of vaccination rates, and support reminder/recall activities.");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientAddressStreetIsMissing);
      id.setImplementationDescription("Patient Address street is not indicated");
      id.setHowToFix(
          "Please ensure that the full address for this patient has been entered. Once the address has been corrected, please resubmit the record. ");
      // TODO Complete WhyToFix
      id.setWhyToFix(
          "Recognizable addresses help to improve patient matching, allow geographic analysis of vaccination rates, and support reminder/recall activities.");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientAddressCityIsMissing);
      id.setHowToFix(
          "Please ensure that the full address for this patient has been entered. Once the address has been corrected, please resubmit the record. ");
      id.setWhyToFix(
          "Recognizable addresses help to improve patient matching, allow geographic analysis of vaccination rates, and support reminder/recall activities.");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientAddressStateIsMissing);
      id.setHowToFix(
          "Please ensure that the full address for this patient has been entered. Once the address has been corrected, please resubmit the record. ");
      id.setWhyToFix(
          "Recognizable addresses help to improve patient matching, allow geographic analysis of vaccination rates, and support reminder/recall activities.");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientAddressCountyIsMissing);
      id.setHowToFix(
          "Patient address county was not specified. Please contact your software vendor and request that the county be indicated for all address submitted to the IIS.");
      id.setWhyToFix(
          "The patient address county may be used by the IIS to ensure immunization coverage rates by county or to associate the patient to a specific county health program. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientAddressCountryIsMissing);
      id.setHowToFix(
          "Patient address country was not specified. Please contact your software vendor and request that country be set for all addresses. ");
      id.setWhyToFix(
          "Recognizable addresses help to improve patient matching, allow geographic analysis of vaccination rates, and support reminder/recall activities. Indicating the country will help ensure that the address is properly understood. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientAddressZipIsMissing);
      id.setHowToFix(
          "Please ensure that the full address for this patient has been entered. Once the address has been corrected, please resubmit the record. ");
      id.setWhyToFix(
          "Recognizable addresses help to improve patient matching, allow geographic analysis of vaccination rates, and support reminder/recall activities.");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientAddressTypeIsMissing);
      id.setHowToFix(
          "Patient address type was not specified. Please contact your software vendor and request that the address type always be set when sending addresses. ");
      id.setWhyToFix(
          "Recognizable addresses help to improve patient matching, allow geographic analysis of vaccination rates, and support reminder/recall activities. The address type will ensure that the IIS understands the type of addres being sent. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientAddressTypeIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix(
          "Patient address type was not recognized. Please contact your software vendor and request that the address type always be set with a proper value when sending addresses. ");
      id.setWhyToFix(
          "Recognizable addresses help to improve patient matching, allow geographic analysis of vaccination rates, and support reminder/recall activities. The correct address type will ensure that the IIS understands the type of addres being sent. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientAddressTypeIsValuedBadAddress);
      id.setHowToFix(
          "The patient address was indicated to be a bad address, which means it should not be considered as a good address for this patient. Please verify that the patient indeed has been marked to have a bad address and if this is correct. If it is then contact your software vendor and request that addresses marked as bad are never transmitted to the IIS. ");
      id.setWhyToFix(
          "Recognizable addresses help to improve patient matching, allow geographic analysis of vaccination rates, and support reminder/recall activities. Better to send a blank address than a bad one that may cause the IIS confusion. ");
    }


    ImplementationDetail id = this.addRuleDetection(Detection.PatientAddressIsInvalid);
    id.setImplementationDescription("Patient Address is invalid according to Smarty Streets.");
    id.setHowToFix(
        "The patient address does not refer to a valid mailable address. Please review and either update or remove the patient address. ");
    id.setWhyToFix(
        "Recognizable addresses help to improve patient matching, allow geographic analysis of vaccination rates, and support reminder/recall activities. If the address is incorrect is better to indicate that the patient has no address rather than send an address that us unusable. ");

    if (props.isAddressCleanserEnabled()) {
      this.addRuleDetection(Detection.PatientAddressIsInvalid);
    }
  }


  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    MqeAddress a = target.getPatientAddress();


    if (a != null) {
      ValidationRuleResult result = addressValidator.getAddressIssuesFor(fields, a, target);
      issues.addAll(result.getValidationDetections());

      if (!a.isEmpty()) {
        if (props.isAddressCleanserEnabled()) {
          if (!a.isClean()) {
            ValidationReport r = Detection.PatientAddressIsInvalid.build(target);
            List<SmartyStreetResponse> rList =
                SmartyStreetResponse.codesFromDpv(a.getCleansingResultCode());
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

        if (a.getTypeCode() != null && "BA".equals(a.getTypeCode())) {
          issues.add(Detection.PatientAddressTypeIsValuedBadAddress.build(a.toString(), target));
        }

        issues.addAll(
            this.codr.handleCodeOrMissing(a.getTypeCode(), VxuField.PATIENT_ADDRESS_TYPE, target));
      } else {
        issues.add(Detection.PatientAddressIsMissing.build(target));
      }
    } else {
      issues.add(Detection.PatientAddressIsMissing.build(target));
    }

    passed = issues.size() == 0;

    return buildResults(issues, passed);
  }

}
