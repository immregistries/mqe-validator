package org.immregistries.dqa.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.rules.patient.PatientIsUnderage;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;

public class NextOfKinRelationshipIsValidForUnderagedPatient extends ValidationRule<DqaNextOfKin> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {PatientIsUnderage.class};
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaNextOfKin target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		String relationship = target.getRelationshipCode();

		if (StringUtils.isNotEmpty(relationship)) {
			if (!target.isResponsibleRelationship()) {
				if (target.isChildRelationship()) {
					issues.add(MessageAttribute.NextOfKinRelationshipIsUnexpected.build(relationship));
					passed = false;
				} else {
					issues.add(MessageAttribute.NextOfKinRelationshipIsNotResponsibleParty.build(relationship));
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
