package org.immregistries.mqe.validator.engine.rules.patient;

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
    return new Class[]{PatientExists.class,};
  }

  public PatientAddressIsValid() {
    this.addRuleDetection(Detection.PatientAddressTypeIsValuedBadAddress);
    this.addRuleDetections(this.codr.getDetectionsForField(VxuField.PATIENT_ADDRESS));
    this.addRuleDetections(this.codr.getDetectionsForField(VxuField.PATIENT_ADDRESS_STREET));
    this.addRuleDetections(this.codr.getDetectionsForField(VxuField.PATIENT_ADDRESS_CITY));
    this.addRuleDetections(this.codr.getDetectionsForField(VxuField.PATIENT_ADDRESS_STATE));
    this.addRuleDetections(this.codr.getDetectionsForField(VxuField.PATIENT_ADDRESS_COUNTY));
    this.addRuleDetections(this.codr.getDetectionsForField(VxuField.PATIENT_ADDRESS_COUNTRY));
    this.addRuleDetections(this.codr.getDetectionsForField(VxuField.PATIENT_ADDRESS_ZIP));
    this.addRuleDetections(this.codr.getDetectionsForField(VxuField.PATIENT_ADDRESS_TYPE));
    
    ImplementationDetail id = this.addRuleDetection(Detection.PatientAddressIsInvalid);id.setImplementationDescription("Patient Address is invalid according to Smarty Streets.");    
    
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
            List<SmartyStreetResponse> rList = SmartyStreetResponse
                .codesFromDpv(a.getCleansingResultCode());
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

        issues.addAll(this.codr.handleCodeOrMissing(a.getTypeCode(), VxuField.PATIENT_ADDRESS_TYPE, target));
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
