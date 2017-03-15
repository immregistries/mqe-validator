package org.immregistries.dqa.validator.report;

import java.util.HashMap;
import java.util.Map;

import org.immregistries.dqa.validator.issue.IssueObject;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.vxu.code.CodeReceived;
import org.joda.time.DateTime;

public class DqaMessageMetrics {
	private Map<IssueObject, Integer> objectCounts = new HashMap<>();
	private Map<MessageAttribute, Integer> attributeCounts = new HashMap<>();
	private Map<CodeReceived, Integer> codeCounts = new HashMap<>();

	private DateTime firstMessageReceived;
	private DateTime lastMessageReceived;
	
	public Map<IssueObject, Integer> getObjectCounts() {
		return objectCounts;
	}
	public void setObjectCounts(Map<IssueObject, Integer> objectCounts) {
		this.objectCounts = objectCounts;
	}
	public Map<MessageAttribute, Integer> getAttributeCounts() {
		return attributeCounts;
	}
	public void setAttributeCounts(Map<MessageAttribute, Integer> attributeCounts) {
		this.attributeCounts = attributeCounts;
	}
	public Map<CodeReceived, Integer> getCodeCounts() {
		return codeCounts;
	}
	public void setCodeCounts(Map<CodeReceived, Integer> codeCounts) {
		this.codeCounts = codeCounts;
	}
	public DateTime getFirstMessageReceived() {
		return firstMessageReceived;
	}
	public void setFirstMessageReceived(DateTime firstMessageReceived) {
		this.firstMessageReceived = firstMessageReceived;
	}
	public DateTime getLastMessageReceived() {
		return lastMessageReceived;
	}
	public void setLastMessageReceived(DateTime lastMessageReceived) {
		this.lastMessageReceived = lastMessageReceived;
	}
	@Override
	public String toString() {
		return "DqaMessageMetrics [objectCounts=" + objectCounts
				+ ", attributeCounts=" + attributeCounts + ", codeCounts="
				+ codeCounts + ", firstMessageReceived=" + firstMessageReceived
				+ ", lastMessageReceived=" + lastMessageReceived + "]";
	}
	
	
	
}
