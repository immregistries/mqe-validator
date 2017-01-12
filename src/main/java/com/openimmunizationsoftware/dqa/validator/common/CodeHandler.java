package com.openimmunizationsoftware.dqa.validator.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openimmunizationsoftware.dqa.model.CodeReceived;
import com.openimmunizationsoftware.dqa.model.CodeStatus;
import com.openimmunizationsoftware.dqa.model.types.CodedEntity;
import com.openimmunizationsoftware.dqa.model.types.Id;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.IssueType;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public enum CodeHandler {
		INSTANCE;
	
	  private static final Logger LOGGER = LoggerFactory.getLogger(CodeHandler.class);
	  
	  public List<IssueFound> handleCode(Id id, IssueField field)
	  {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		
	    if (id == null || StringUtils.isEmpty(id.getNumber())) {
	    	issues.add(PotentialIssue.get(field, IssueType.MISSING).build());
	    } else {
	    	LOGGER.error("handleCode::Id This is not implemented yet!");
	    	CodeReceived cr = new CodeReceived();
	    	issues.addAll(evaluateStatus(cr, field));
		}
	    
	    return issues;
	}
	  
	protected List<IssueFound> evaluateStatus(CodeReceived cr, IssueField field) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		
		if (cr == null) {
			issues.add(PotentialIssue.buildIssue(field,  IssueType.MISSING));
		}
		
		CodeStatus status = cr.getCodeStatus();
		
		if (status == null) {
			issues.add(PotentialIssue.buildIssue(field, IssueType.UNRECOGNIZED, cr));
		} else {
			if (status.isInvalid()) {
					issues.add(PotentialIssue.buildIssue(field, IssueType.INVALID, cr));
			} else if (status.isUnrecognized()) {
					issues.add(PotentialIssue.buildIssue(field, IssueType.UNRECOGNIZED, cr));
			} else if (status.isDeprecated()) {
					issues.add(PotentialIssue.buildIssue(field, IssueType.DEPRECATE, cr));
			} else if (status.isIgnored()) {
					issues.add(PotentialIssue.buildIssue(field, IssueType.IGNORED, cr));
			}
		}
		
		return issues;
	}

	public List<IssueFound> handleCode(CodedEntity codedEntity, IssueField field) {
		
		List<IssueFound> issues = new ArrayList<IssueFound>();
		
		if (codedEntity == null || StringUtils.isEmpty(codedEntity.getCode())) {
			issues.add(PotentialIssue.get(field, IssueType.MISSING).build());
		} else {
	    	LOGGER.error("handleCode::CodedEntity This is not implemented yet!");
			
	    	CodeReceived cr = new CodeReceived();
//
//			CodeTable codeTable = CodesReceived.getCodeTable(codedEntity
//					.getTableType());
//
//			cr = getCodeReceived(codedEntity.getCode(), codedEntity.getText(),
//					codeTable, context);
//
	    	issues.addAll(evaluateStatus(cr, field));
		
		}

		return issues;
	}
}
