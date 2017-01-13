package org.immregistries.dqa.validator.engine.issues;

/**
 * The intention is to generalize 
 * the interface for the various types of things we report. 
 * 
 * @author Josh Hull
 */
public interface Reportable {

	IssueLevel getIssueLevel();
	
}
