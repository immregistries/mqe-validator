package org.immregistries.dqa.validator.engine;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.model.DqaMessageHeader;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaNextOfKin;
import org.immregistries.dqa.validator.model.DqaPatient;
import org.immregistries.dqa.validator.model.DqaVaccination;

public enum RulePairBuilder {
	INSTANCE;
	private static final ValidationUtility util = ValidationUtility.INSTANCE;
	/*
	 * The Rule List Builders!!!
	 */
	
	protected List<ValidationRulePair> buildPatientRulePairs(DqaPatient p, DqaMessageReceived mr) {
		return util.buildRulePairs(ValidationRuleEntityLists.PATIENT_RULES.getRules(),  p,  mr);
	}
	
	protected List<ValidationRulePair> buildMessageHeaderRulePairs(DqaMessageHeader mh, DqaMessageReceived mr) {
		return util.buildRulePairs(ValidationRuleEntityLists.MESSAGE_RULES.getRules(),  mh,  mr);
	}

	protected List<ValidationRulePair> buildVaccinationRulePairs(DqaVaccination v, DqaMessageReceived mr) {
		return util.buildRulePairs(ValidationRuleEntityLists.VACCINATION_RULES.getRules(), v,  mr);
	}
	
	protected List<ValidationRulePair> buildNextOfKinRulePairs(DqaNextOfKin n, DqaMessageReceived mr) {
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
	public List<ValidationRulePair> buildHeaderAndPatientRuleList(DqaMessageReceived m) {
		
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
	public List<List<ValidationRulePair>> buildListItemRuleLists(DqaMessageReceived m) {
		List<List<ValidationRulePair>> ruleSets = new ArrayList<List<ValidationRulePair>>();
		
		//The Vaccination rules. 
		for (DqaVaccination v : m.getVaccinations()) {
			ruleSets.add(this.buildVaccinationRulePairs(v, m));
		}
		//And NOK rules. 
		for (DqaNextOfKin n : m.getNextOfKins()) {
			ruleSets.add(this.buildNextOfKinRulePairs(n, m));
		}

		return ruleSets;
	}
}
