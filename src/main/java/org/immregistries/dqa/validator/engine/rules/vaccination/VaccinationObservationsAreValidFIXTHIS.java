package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.IssueField;
import org.immregistries.dqa.validator.engine.issues.MessageAttribute;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.Observation;
import org.immregistries.dqa.validator.model.DqaVaccination;
import org.immregistries.dqa.validator.model.VaccinationVIS;
import org.immregistries.dqa.validator.model.hl7types.PatientImmunity;

public class VaccinationObservationsAreValidFIXTHIS extends
		ValidationRule<DqaVaccination> {
//TODO: put most of this code into the transform layer. 
	@Override
	protected final Class[] getDependencies() {
		return new Class[] { VaccinationIsAdministered.class };
	}

	private static final String OBX_VACCINE_FUNDING = "64994-7";
	private static final String OBX_VACCINE_TYPE = "30956-7";
	private static final String OBX_VIS_PUBLISHED = "29768-9";
	private static final String OBX_VIS_PRESENTED = "29769-7";
	private static final String OBX_DISEASE_WITH_PRESUMED_IMMUNITY = "59784-9";

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = false;

		String financialEligibilityCode = null;
		Map<String, VaccinationVIS> vaccinationVISMap = new HashMap<String, VaccinationVIS>();

		for (Observation observation : target.getObservations()) {
			issues.addAll(codr.handleCode(observation.getValueTypeCode(), IssueField.OBSERVATION_VALUE_TYPE));
			issues.addAll(codr.handleCode(observation.getIdentifierCode(), IssueField.OBSERVATION_IDENTIFIER_CODE));
			if (financialEligibilityCode == null
					&& observation.getIdentifierCode().equals(
							OBX_VACCINE_FUNDING)) {
				if (common.isEmpty(observation.getValue())) {
					issues.add(MessageAttribute.ObservationValueIsMissing.build());
				} else {
					financialEligibilityCode = observation.getValue();
				}

			} else if (observation.getIdentifierCode().equals(OBX_VACCINE_TYPE)
					|| observation.getIdentifierCode().equals(OBX_VIS_PRESENTED)
					|| observation.getIdentifierCode().equals(OBX_VIS_PUBLISHED)) {
				String key = observation.getSubId();
				VaccinationVIS vaccinationVIS = vaccinationVISMap.get(key);
				if (vaccinationVIS == null) {
					vaccinationVIS = new VaccinationVIS();
					vaccinationVIS.setVaccination(target);
					vaccinationVISMap.put(key, vaccinationVIS);
					target.getVaccinationVisList().add(vaccinationVIS);
				}

				if (observation.getIdentifierCode().equals(OBX_VACCINE_TYPE)) {
					vaccinationVIS.setCvxCode(observation.getValue());
				} else if (observation.getIdentifierCode().equals(
						OBX_VIS_PRESENTED)) {
					if (!observation.getValue().equals("")) {
						if (!datr.isDate(observation.getValue())) {
							issues.add(MessageAttribute.VaccinationVisPresentedDateIsInvalid
									.build());
							vaccinationVIS.setPresentedDate(datr
									.parseDate(observation.getValue()));
						}
					} else if (observation.getIdentifierCode().equals(
							OBX_VIS_PUBLISHED)) {

						if (!datr.isDate(observation.getValue())) {
							issues.add(MessageAttribute.VaccinationVisPublishedDateIsInvalid
									.build());
						} else {
							vaccinationVIS.setPublishedDate(datr
									.parseDate(observation.getValue()));
						}
					}
				} else if (observation.getIdentifierCode().equals(
						OBX_DISEASE_WITH_PRESUMED_IMMUNITY)) {
					PatientImmunity patientImmunity = new PatientImmunity();
					patientImmunity.setImmunityCode(observation.getValue());
					// patient.getPatientImmunityList().add(patientImmunity);
					issues.addAll(codr.handleCode(
							patientImmunity.getImmunity(),
							IssueField.PATIENT_IMMUNITY_CODE));
				}
			}
			if (vaccinationVISMap.size() == 0) {
				if (target.isAdministered()) {
					issues.add(MessageAttribute.VaccinationVisIsMissing.build());
				}
			} else {
				boolean firstOne = true;
				int positionId = 0;
				for (VaccinationVIS vaccinationVIS : target
						.getVaccinationVisList()) {
					positionId++;
					vaccinationVIS.setPositionId(positionId);
					// issues.addAll(codr.handleCode(vaccinationVIS.getDocument(),
					// IssueField.VACCINATION_VIS_DOCUMENT_TYPE,
					// target.isAdministered());

					if (target.isAdministered()) {
						issues.addAll(codr.handleCode(vaccinationVIS.getCvx(), IssueField.VACCINATION_VIS_CVX_CODE));
					}

					if (vaccinationVIS.getPublishedDate() == null) {
						if (target.isAdministered()) {
							issues.add(MessageAttribute.VaccinationVisPublishedDateIsMissing
									.build());
						}
					}
					if (vaccinationVIS.getPresentedDate() == null) {
						if (target.isAdministered()) {
							issues.add(MessageAttribute.VaccinationVisPresentedDateIsMissing
									.build());
						}
					} else {
						if (target.getAdminDate() != null) {
							if (target.getAdminDate().after(
									vaccinationVIS.getPresentedDate())) {
								issues.add(MessageAttribute.VaccinationVisPresentedDateIsAfterAdminDate
										.build());
							} else if (target.getAdminDate().before(
									vaccinationVIS.getPresentedDate())) {
								issues.add(MessageAttribute.VaccinationVisPresentedDateIsNotAdminDate
										.build());
							}
						}
						if (vaccinationVIS.getPublishedDate() != null) {
							if (vaccinationVIS.getPresentedDate().before(
									vaccinationVIS.getPublishedDate())) {
								issues.add(MessageAttribute.VaccinationVisPresentedDateIsBeforePublishedDate
										.build());
							}
						}
					}
					if (vaccinationVIS.getDocumentCode().equals("")) {
						if (vaccinationVIS.getCvxCode().equals("")
								|| vaccinationVIS.getPublishedDate() == null) {
							// TODO verify that code and date are recognized,
							// this logic will
							// work for now
							issues.add(MessageAttribute.VaccinationVisIsUnrecognized
									.build());
							if (firstOne && target.isAdministered()) {
								issues.add(MessageAttribute.VaccinationVisIsMissing
										.build());
							}
						}
					}
					firstOne = false;
				}
			}
		}
		passed = (issues.size() == 0);
		return buildResults(issues, passed);
	}
}
