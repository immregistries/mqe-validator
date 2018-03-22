package org.immregistries.dqa.validator.detection;

import java.util.HashMap;
import java.util.Map;

public enum MessageObject {
	  GENERAL(        "General", "GEN")
	, MESSAGE_HEADER( "Message Header", "MSH")
	, NEXT_OF_KIN(    "Next-of-kin", "NK1")
	, OBSERVATION(    "Observation", "OBX")
	, PATIENT(        "Patient", "PID")
	, VACCINATION(    "Vaccination", "VAC")
	;
	
	  
	private static Map<String, MessageObject> mappd = new HashMap<String, MessageObject>();
	
	static {
		for (MessageObject obj : MessageObject.values()) {
			mappd.put(obj.getDescription(), obj);
		}
	}
	
	private String description;
	private String location;
	private MessageObject(String desc, String location) {
		this.description = desc;
		this.location = location;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static MessageObject getByDesc(String desc) {
		return mappd.get(desc);
	}

	public String getLocation() {
		return location;
	}

}
