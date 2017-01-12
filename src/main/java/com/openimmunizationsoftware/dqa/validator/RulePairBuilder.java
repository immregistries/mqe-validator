package com.openimmunizationsoftware.dqa.validator;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageHeader;
import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.NextOfKin;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.model.Vaccination;

public enum RulePairBuilder {
	INSTANCE;
	private static final ValidationUtility util = ValidationUtility.INSTANCE;
	/*
	 * The Rule List Builders!!!
	 */
	
	protected List<ValidationRulePair> buildPatientRulePairs(Patient p, MessageReceived mr) {
		return util.buildRulePairs(ValidationRuleEntityLists.PATIENT_RULES.getRules(),  p,  mr);
	}
	
	protected List<ValidationRulePair> buildMessageHeaderRulePairs(MessageHeader mh, MessageReceived mr) {
		return util.buildRulePairs(ValidationRuleEntityLists.MESSAGE_RULES.getRules(),  mh,  mr);
	}

	protected List<ValidationRulePair> buildVaccinationRulePairs(Vaccination v, MessageReceived mr) {
		return util.buildRulePairs(ValidationRuleEntityLists.VACCINATION_RULES.getRules(), v,  mr);
	}
	
	protected List<ValidationRulePair> buildNextOfKinRulePairs(NextOfKin n, MessageReceived mr) {
		return util.buildRulePairs(ValidationRuleEntityLists.NEXT_OF_KIN_RULES.getRules(), n,  mr);
	}
	
	/*
	 * The Rule Categories!!!
	 */
	/**
	 * This builds a list of rules for all of the higher-order entities. 
	 * These are entities that are not in a list, and who's validations matter down the line for other
	 * entities, like the Vaccinations and NOK's.  
	 * @param m
	 * @return
	 */
	public List<ValidationRulePair> buildHeaderAndPatientRuleList(MessageReceived m) {
		
		List<ValidationRulePair> headerAndPatientRules = this.buildMessageHeaderRulePairs(m.getMessageHeader(), m);
		headerAndPatientRules.addAll(this.buildPatientRulePairs(m.getPatient(), m));
				
		return headerAndPatientRules;
	}
	
	/**
	 * This builds list of lists of rules for the list-item (secondary order) entities. 
	 * These are things like a Vaccination, for which we do not want cross-dependency fulfillment.  If a 
	 * vaccination's rules are fulfilled, we intend for that pass to fulfill only dependencies for the vaccination
	 * for which it passed. Hence, these rules are treated slightly differently in that their passed rules
	 * do not propagate on to further dependency checks - only their own.  
	 * @param m
	 * @return
	 */
	public List<List<ValidationRulePair>> buildListItemRuleLists(MessageReceived m) {
		List<List<ValidationRulePair>> ruleSets = new ArrayList<List<ValidationRulePair>>();
		
		//The Vaccination rules. 
		for (Vaccination v : m.getVaccinations()) {
			ruleSets.add(this.buildVaccinationRulePairs(v, m));
		}
		//And NOK rules. 
		for (NextOfKin n : m.getNextOfKins()) {
			ruleSets.add(this.buildNextOfKinRulePairs(n, m));
		}

		return ruleSets;
	}
}
