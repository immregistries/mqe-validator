package com.openimmunizationsoftware.dqa.transform;

import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageHeader;
import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.NextOfKin;
import com.openimmunizationsoftware.dqa.model.Observation;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.model.types.PatientImmunity;
import com.openimmunizationsoftware.dqa.validator.common.DateUtility;

public class MessageTransformer {

	private DateUtility du = DateUtility.INSTANCE;
	private AddressCleanser ac = AddressCleanserDefault.INSTANCE;
	
	//given an HL7 object model, put it into the VXU business object model. 
	public void transform(MessageReceived mr) {
		transformHeaderData(mr.getMessageHeader());
	}

	protected void transformHeaderData(MessageHeader mh) {
		if (mh != null) {
			mh.setMessageDate(du.parseDate(mh.getMessageDateString()));
		}
	}

	protected void transformObservations(MessageReceived mr) {
		for (Vaccination v : mr.getVaccinations()) {
			List<Observation> obxList = v.getObservations();
			for (Observation o : obxList) {
				
			}
		}

	}
	
	protected void transformPatientData(MessageReceived mr) {
		Patient p = mr.getPatient();
		ac.cleanThisAddress(p.getAddress());
		
	}
	
	protected void transformNextOfKinData(MessageReceived mr) {
		List<NextOfKin> kinList = mr.getNextOfKins();
		
		for (NextOfKin kin : kinList) {
			ac.cleanThisAddress(kin.getAddress());
		}
		
	}
	
	/*
	 * I need to create a set of methods that will 
	 * transform an observation into a business object.  
	 */
//	if (observation.getObservationIdentifierCode().equals(OBX_DISEASE_WITH_PRESUMED_IMMUNITY))
	public PatientImmunity toPatientImmunity(Observation ob) {

	PatientImmunity patientImmunity = new PatientImmunity();
	patientImmunity.setImmunityCode(ob.getObservationValue());
//	patient.getPatientImmunityList().add(patientImmunity);
//	handleCodeReceived(patientImmunity.getImmunity(), PotentialIssues.Field.PATIENT_IMMUNITY_CODE);

	return new PatientImmunity();
	
	}
	//From Validation.java
//	 for (Observation observation : vaccination.getObservations())
//	    {
//	      skippableItem = observation;
//	      handleCodeReceived(observation.getValueType(), PotentialIssues.Field.OBSERVATION_VALUE_TYPE);
//	      handleCodeReceived(observation.getObservationIdentifier(), PotentialIssues.Field.OBSERVATION_IDENTIFIER_CODE);
//	      if (!observation.isSkipped())
//	      {
//	        if (financialEligibilityCode == null && observation.getObservationIdentifierCode().equals(OBX_VACCINE_FUNDING))
//	        {
//	          if (notEmpty(observation.getObservationValue(), pi.ObservationValueIsMissing))
//	          {
//	            financialEligibilityCode = observation.getObservationValue();
//	          }
//	        } else if (observation.getObservationIdentifierCode().equals(OBX_VACCINE_TYPE)
//	            || observation.getObservationIdentifierCode().equals(OBX_VIS_PRESENTED)
//	            || observation.getObservationIdentifierCode().equals(OBX_VIS_PUBLISHED))
//	        {
//	          String key = observation.getObservationSubId();
//	          VaccinationVIS vaccinationVIS = vaccinationVISMap.get(key);
//	          if (vaccinationVIS == null)
//	          {
//	            vaccinationVIS = new VaccinationVIS();
//	            vaccinationVIS.setVaccination(vaccination);
//	            vaccinationVISMap.put(key, vaccinationVIS);
//	            vaccination.getVaccinationVisList().add(vaccinationVIS);
//	          }
//	          if (observation.getObservationIdentifierCode().equals(OBX_VACCINE_TYPE))
//	          {
//	            vaccinationVIS.setCvxCode(observation.getObservationValue());
//	          } else if (observation.getObservationIdentifierCode().equals(OBX_VIS_PRESENTED))
//	          {
//	            if (!observation.getObservationValue().equals(""))
//	            {
//	              vaccinationVIS.setPresentedDate(createDate(pi.VaccinationVisPresentedDateIsInvalid, observation.getObservationValue()));
//	            }
//	          } else if (observation.getObservationIdentifierCode().equals(OBX_VIS_PUBLISHED))
//	          {
//
//	            if (!observation.getObservationValue().equals(""))
//	            {
//	              vaccinationVIS.setPublishedDate(createDate(pi.VaccinationVisPublishedDateIsInvalid, observation.getObservationValue()));
//	            }
//	          }
//	        } else if (observation.getObservationIdentifierCode().equals(OBX_DISEASE_WITH_PRESUMED_IMMUNITY))
//	        {
//
//	          PatientImmunity patientImmunity = new PatientImmunity();
//	          patientImmunity.setPatient(patient);
//	          patientImmunity.setImmunityCode(observation.getObservationValue());
//	          patient.getPatientImmunityList().add(patientImmunity);
//	          handleCodeReceived(patientImmunity.getImmunity(), PotentialIssues.Field.PATIENT_IMMUNITY_CODE);
//	        }
//	      }
//	    }
}
