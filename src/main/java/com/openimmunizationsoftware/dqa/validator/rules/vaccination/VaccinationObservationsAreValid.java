package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Observation;
import com.openimmunizationsoftware.dqa.model.Skippable;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.model.VaccinationVIS;
import com.openimmunizationsoftware.dqa.model.types.PatientImmunity;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class VaccinationObservationsAreValid extends
		ValidationRule<Vaccination> {
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
	protected ValidationRuleResult executeRule(Vaccination target,
			MessageReceived m) {

		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = false;

		String financialEligibilityCode = null;
		Skippable skippableItem = null;
		Map<String, VaccinationVIS> vaccinationVISMap = new HashMap<String, VaccinationVIS>();

		for (Observation observation : target.getObservations()) {
			skippableItem = observation;
			issues.addAll(codr.handleCode(observation.getValueType(),
					IssueField.OBSERVATION_VALUE_TYPE));
			issues.addAll(codr.handleCode(
					observation.getObservationIdentifier(),
					IssueField.OBSERVATION_IDENTIFIER_CODE));
			if (!observation.isSkipped()) {
				if (financialEligibilityCode == null
						&& observation.getObservationIdentifierCode().equals(
								OBX_VACCINE_FUNDING)) {
					if (common.isEmpty(observation.getObservationValue())) {
						issues.add(PotentialIssue.ObservationValueIsMissing
								.build());
					} else {
						financialEligibilityCode = observation
								.getObservationValue();
					}

				} else if (observation.getObservationIdentifierCode().equals(
						OBX_VACCINE_TYPE)
						|| observation.getObservationIdentifierCode().equals(
								OBX_VIS_PRESENTED)
						|| observation.getObservationIdentifierCode().equals(
								OBX_VIS_PUBLISHED)) {
					String key = observation.getObservationSubId();
					VaccinationVIS vaccinationVIS = vaccinationVISMap.get(key);
					if (vaccinationVIS == null) {
						vaccinationVIS = new VaccinationVIS();
						vaccinationVIS.setVaccination(target);
						vaccinationVISMap.put(key, vaccinationVIS);
						target.getVaccinationVisList().add(vaccinationVIS);
					}
					if (observation.getObservationIdentifierCode().equals(
							OBX_VACCINE_TYPE)) {
						vaccinationVIS.setCvxCode(observation
								.getObservationValue());
					} else if (observation.getObservationIdentifierCode()
							.equals(OBX_VIS_PRESENTED)) {
						if (!observation.getObservationValue().equals("")) {
							if (!datr.isDate(observation.getObservationValue())) {
								issues.add(PotentialIssue.VaccinationVisPresentedDateIsInvalid
										.build());
								vaccinationVIS.setPresentedDate(datr
										.parseDate(observation
												.getObservationValue()));
							}
						} else if (observation.getObservationIdentifierCode()
								.equals(OBX_VIS_PUBLISHED)) {

							if (!datr.isDate(observation.getObservationValue())) {
								issues.add(PotentialIssue.VaccinationVisPublishedDateIsInvalid
										.build());
							} else {
								vaccinationVIS.setPublishedDate(datr
										.parseDate(observation
												.getObservationValue()));
							}
						}
					} else if (observation.getObservationIdentifierCode()
							.equals(OBX_DISEASE_WITH_PRESUMED_IMMUNITY)) {
						PatientImmunity patientImmunity = new PatientImmunity();
						patientImmunity.setImmunityCode(observation.getObservationValue());
						// patient.getPatientImmunityList().add(patientImmunity);
						issues.addAll(codr.handleCode(
								patientImmunity.getImmunity(),
								IssueField.PATIENT_IMMUNITY_CODE));
					}
				}
			}
			skippableItem = target;
			if (vaccinationVISMap.size() == 0) {
				if (target.isAdministered()) {
					issues.add(PotentialIssue.VaccinationVisIsMissing.build());
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
							issues.add(PotentialIssue.VaccinationVisPublishedDateIsMissing
									.build());
						}
					}
					if (vaccinationVIS.getPresentedDate() == null) {
						if (target.isAdministered()) {
							issues.add(PotentialIssue.VaccinationVisPresentedDateIsMissing
									.build());
						}
					} else {
						if (target.getAdminDate() != null) {
							if (target.getAdminDate().after(
									vaccinationVIS.getPresentedDate())) {
								issues.add(PotentialIssue.VaccinationVisPresentedDateIsAfterAdminDate
										.build());
							} else if (target.getAdminDate().before(
									vaccinationVIS.getPresentedDate())) {
								issues.add(PotentialIssue.VaccinationVisPresentedDateIsNotAdminDate
										.build());
							}
						}
						if (vaccinationVIS.getPublishedDate() != null) {
							if (vaccinationVIS.getPresentedDate().before(
									vaccinationVIS.getPublishedDate())) {
								issues.add(PotentialIssue.VaccinationVisPresentedDateIsBeforePublishedDate
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
							issues.add(PotentialIssue.VaccinationVisIsUnrecognized
									.build());
							if (firstOne && target.isAdministered()) {
								issues.add(PotentialIssue.VaccinationVisIsMissing
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
