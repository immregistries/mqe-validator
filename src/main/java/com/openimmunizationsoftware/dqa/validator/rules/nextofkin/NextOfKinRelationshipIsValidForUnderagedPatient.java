package com.openimmunizationsoftware.dqa.validator.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.NextOfKin;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientIsUnderage;

public class NextOfKinRelationshipIsValidForUnderagedPatient extends ValidationRule<NextOfKin> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {PatientIsUnderage.class};
	}
	
	@Override
	protected ValidationRuleResult executeRule(NextOfKin target, MessageReceived m) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		String relationship = target.getRelationshipCode();

		if (StringUtils.isNotEmpty(relationship)) {
			if (!target.isResponsibleParty()) {
				if (target.isChild()) {
					issues.add(PotentialIssue.NextOfKinRelationshipIsUnexpected.build(relationship));
					passed = false;
				} else {
					issues.add(PotentialIssue.NextOfKinRelationshipIsNotResponsibleParty.build(relationship));
					passed = false;
				}
			}
		}
		
		return buildResults(issues, passed);
	}
	
	

}

//boolean isResponsibleParty = false;
//If it has a relationship...
//if (StringUtils.isNotEmpty(relationship)) {
//
//  String relationshipCode = target.getRelationshipCode();
//  
//  // Check for unexpected relationship
//  if (NextOfKin.RELATIONSHIP_CHILD.equals(relationshipCode) || NextOfKin.RELATIONSHIP_FOSTER_CHILD.equals(relationshipCode)
//      || NextOfKin.RELATIONSHIP_STEPCHILD.equals(relationshipCode))
//  {
//    // Normally don't expect under age patient to have a child recorded on
//    // record. Often this issue happens when the relationship is being
//    // recorded backwards, from the patient to the NK1, instead of from
//    // the NK1 to the patient.
//    registerIssue(this.pi.NextOfKinRelationshipIsUnexpected);
//  } else
//  {
//    String[] responsibleParties = { NextOfKin.RELATIONSHIP_CARE_GIVER, NextOfKin.RELATIONSHIP_FATHER, NextOfKin.RELATIONSHIP_GRANDPARENT,
//        NextOfKin.RELATIONSHIP_MOTHER, NextOfKin.RELATIONSHIP_PARENT, NextOfKin.RELATIONSHIP_GUARDIAN };
//    for (String compare : responsibleParties)
//    {
//      if (compare.equals(relationshipCode))
//      {
//        isResponsibleParty = true;
//        break;
//      }
//    }
//  }
//  if (!isResponsibleParty)
//    {
//      registerIssue(pi.NextOfKinRelationshipIsNotResponsibleParty);
//    }
