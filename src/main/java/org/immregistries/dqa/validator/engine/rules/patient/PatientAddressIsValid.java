package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.dqa.validator.address.SmartyStreetResponse;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.common.AddressFields;
import org.immregistries.dqa.validator.engine.common.AddressValidator;
import org.immregistries.dqa.vxu.DqaAddress;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.immregistries.dqa.vxu.VxuField;

public class PatientAddressIsValid extends ValidationRule<DqaPatient> {

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
    this.ruleDetections.addAll(Arrays.asList(Detection.PatientAddressTypeIsValuedBadAddress));
    this.ruleDetections.addAll(this.codr.getDetectionsForField(VxuField.PATIENT_ADDRESS));
    this.ruleDetections.addAll(this.codr.getDetectionsForField(VxuField.PATIENT_ADDRESS_STREET));
    this.ruleDetections.addAll(this.codr.getDetectionsForField(VxuField.PATIENT_ADDRESS_STREET2));
    this.ruleDetections.addAll(this.codr.getDetectionsForField(VxuField.PATIENT_ADDRESS_CITY));
    this.ruleDetections.addAll(this.codr.getDetectionsForField(VxuField.PATIENT_ADDRESS_STATE));
    this.ruleDetections.addAll(this.codr.getDetectionsForField(VxuField.PATIENT_ADDRESS_COUNTY));
    this.ruleDetections.addAll(this.codr.getDetectionsForField(VxuField.PATIENT_ADDRESS_COUNTRY));
    this.ruleDetections.addAll(this.codr.getDetectionsForField(VxuField.PATIENT_ADDRESS_ZIP));
    this.ruleDetections.addAll(this.codr.getDetectionsForField(VxuField.PATIENT_ADDRESS_TYPE));

    if (props.isAddressCleanserEnabled()) {
      this.ruleDetections.add(Detection.PatientAddressIsInvalid);
    }
  }

  @Override
  protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    DqaAddress a = target.getPatientAddress();

    ValidationRuleResult result = addressValidator.getAddressIssuesFor(fields, a, target);
    issues.addAll(result.getValidationDetections());

    if (a != null) {

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

      issues.addAll(this.codr.handleCode(a.getTypeCode(), VxuField.PATIENT_ADDRESS_TYPE, target));
    }

    passed = issues.size() == 0;

    return buildResults(issues, passed);
  }

}
