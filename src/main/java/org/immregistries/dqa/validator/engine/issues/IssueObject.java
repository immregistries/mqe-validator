package org.immregistries.dqa.validator.engine.issues;

import java.util.HashMap;
import java.util.Map;

public enum IssueObject {
	  GENERAL( "General", "GEN")
	, HL7( "HL7", "HL7")
	, HL7_MSH( "HL7 MSH", "MSH")
	, HL7_NK1( "HL7 NK1", "NK1")
	, HL7_OBX( "HL7 OBX", "OBX")
	, HL7_ORC( "HL7 ORC", "ORC")
	, HL7_PD1( "HL7 PD1", "PD1")
	, HL7_PID( "HL7 PID", "PID")
	, HL7_PV1( "HL7 PV1", "PV1")
	, HL7_RXA( "HL7 RXA", "RXA")
	, HL7_RXR( "HL7 RXR", "RXR")
	, NEXT_OF_KIN( "Next-of-kin", "NK1")
	, OBSERVATION( "Observation", "OBX")
	, PATIENT( "Patient", "PID")
	, VACCINATION( "Vaccination", "VAC")
	;
	
	  
	private static Map<String, IssueObject> mappd = new HashMap<String, IssueObject>();
	
	static {
		for (IssueObject obj : IssueObject.values()) {
			mappd.put(obj.getDescription(), obj);
		}
	}
	
	private String description;
	private String location;
	private IssueObject(String desc, String location) {
		this.description = desc;
		this.location = location;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static IssueObject getByDesc(String desc) {
		return mappd.get(desc);
	}

	public String getLocation() {
		return location;
	}

}
