package org.immregistries.dqa.validator.engine.issues;

public enum IssueLevel {

	/**
	 * Data needs to be fixed, and the message should be resubmitted. 
	 */
	  ERROR("E", "Error", "Rejected with Errors")
	/**
	 * Data needs to be fixed, but we can deal with it in this message. No need to resubmit. 
	 */
	, WARN("W", "Warn", "Accepted with Warnings")
	/**
	 * This means an issue is acceptable, but we're not going to tell anyone about it, except maybe on a report.
	 */
	, ACCEPT("A", "Accept", "Accepted") 
	/**
	 * This means an issues is acceptable, and we do want to tell people.
	 */
	, INFO("I", "Info", "Informational") 
	;

	private String actionCode = "";
	private String actionLabel = "";
	private String actionDescription = "";

	private IssueLevel(String actionCode, String actionLabel, String actionDesc) {
		this.actionCode = actionCode;
		this.actionLabel = actionLabel;
		this.actionDescription = actionDesc;
	}
	
	public String getActionLabel() {
		return this.actionLabel;
	}
	public String getActionCode() {
		return actionCode;
	}
	
	public String getActionDescription() {
		return this.actionDescription;
	}
	
}
