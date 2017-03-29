package org.immregistries.dqa.validator.report;

import org.immregistries.dqa.validator.issue.IssueType;

public class FieldIssueScore {
	private int issueCount = 0;
	private double issuePercentDemerit;
	private int issueDemerit = 0;
	private IssueType issueType;
	
	
	
	public int getIssueCount() {
		return issueCount;
	}
	public void setIssueCount(int issueCount) {
		this.issueCount = issueCount;
	}
	public int getIssueDemerit() {
		return issueDemerit;
	}
	public void setIssueDemerit(int issueDemerit) {
		this.issueDemerit = issueDemerit;
	}
	public double getIssuePercentDemerit() {
		return issuePercentDemerit;
	}
	public void setIssuePercentDemerit(double issuePercentDemerit) {
		this.issuePercentDemerit = issuePercentDemerit;
	}
	public IssueType getIssueType() {
		return issueType;
	}
	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}
	
}
