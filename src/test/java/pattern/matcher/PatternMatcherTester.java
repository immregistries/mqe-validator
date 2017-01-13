package pattern.matcher;

import org.junit.Test;

public class PatternMatcherTester {

	@Test
	public void test() {
		
		String[] segs = { 
			"ISA", 
			"GS", 
			"ST", 
			"BPR", 
			"NTE", 
			"TRN", 
			"CUR", 
			"REF",
			"DTM", 
			"N1", 
			"N3", 
			"N4", 
			"REF", 
			"PER",
			"N1",
			"N3",
			"N4",
			"REF",
			"REF",
			"RDM"
		};
		
		
		
	}
	
	//so basically, it needs to run the first lsit against expected lists...
	//you need something to read and execute the expectations. 
	
	//Expected first segment: ISA.  Expected MIN: 1 MAX: 1...
	//So the first thing it should check is whether that's there. 
	//YUP...  okay.  
	//The harder ones are the zero or one... 
	//Like the CUR segment...  okay.  is it there?  Yes.  if not. okay. but it can't be anywhere else. 
	//

		

	/*

{"id": "ISA", "min": 1, "max": 1, "name": "Interchange", "loopid": "", "loop": [
  {"id": "GS", "min": 1, "max": -1, "name": "Functional Group", "loopid": "", "loop": [
    {"id": "ST", "min": 1, "max": -1, "name": "Health Care Claim Payment/Advice", "loopid": "837", "loop": [
      {"id": "BPR", "min": 1, "max": 1, "name": "Financial Information"},
      {"id": "NTE", "min": 0, "max": -1, "name": "Reassociation Trace Number"},
      {"id": "TRN", "min": 0, "max": 1, "name": "Foreign Currency Information"},
      {"id": "CUR", "min": 0, "max": 1, "name": "Receiver Identification"},
      {"id": "REF", "min": 0, "max": -1, "name": "Version Identification"},
      {"id": "DTM", "min": 0, "max": -1, "name": "Production Date"},
      {"id": "N1", "min": 1, "max": 1, "name": "Payer Identification", "loopid": "1000A", "loop": [
        {"id": "N3", "min": 0, "max": -1, "name": "Payer Address"},
        {"id": "N4", "min": 0, "max": 1, "name": "Payer City, State ZIP Code"},
        {"id": "REF", "min": 0, "max": -1, "name": "Additional Payer Information"},
        {"id": "PER", "min": 1, "max": -1, "name": "Payer Contact Information"}
        ]},
      {"id": "N1", "min": 1, "max": 1, "name": "Payee Identification", "loopid": "1000B", "loop": [
        {"id": "N3", "min": 0, "max": 1, "name": "Payee Address"},
        {"id": "N4", "min": 1, "max": 1, "name": "Payee City, State, ZIP Code"},
        {"id": "REF", "min": 0, "max": -1, "name": "Payee Additional Identification"},
        {"id": "RDM", "min": 0, "max": 1, "name": "Remittance Delivery Method"}
        ]},
      {"id": "LX", "min": 0, "max": -1, "name": "Header Number", "loopid": "2000", "loop": [
        {"id": "TS3", "min": 0, "max": 1, "name": "Provider Summary Information"},
        {"id": "TS2", "min": 0, "max": 1, "name": "Provider Supplemental Summary Information"},
        {"id": "CLP", "min": 1, "max": -1, "name": "Claim Payment Information", "loopid": "2100", "loop": [
          {"id": "CAS", "min": 0, "max": 99, "name": "Claim Adjustment"},
          {"id": "NM1", "min": 1, "max": 7, "name": ""},
          {"id": "MIA", "min": 0, "max": 1, "name": "Inpatient Adjudication Information"},
          {"id": "MOA", "min": 0, "max": 1, "name": "Outpaient Adjudication Information"},
          {"id": "REF", "min": 0, "max": 15, "name": ""},
          {"id": "DTM", "min": 0, "max": 9, "name": ""},
          {"id": "PER", "min": 0, "max": 3, "name": "Claim Contact Information"},
          {"id": "AMT", "min": 0, "max": 20, "name": "Claim Supplemental Information"},
          {"id": "QTY", "min": 0, "max": 20, "name": "Claim Supplemental Information Quantity"},
          {"id": "SVC", "min": 0, "max": 999, "name": "Service Payment Information", "loopid": "2110", "loop": [
            {"id": "DTM", "min": 0, "max": 9, "name": "Service Date"},
            {"id": "CAS", "min": 0, "max": 99, "name": "Service Adjustment"},
            {"id": "REF", "min": 0, "max": 99, "name": ""},
            {"id": "AMT", "min": 0, "max": 20, "name": "Service Supplemental Amount"},
            {"id": "QTY", "min": 0, "max": 20, "name": "Service Supplemental Quantity"},
            {"id": "LQ", "min": 0, "max": 99, "name": "Health Care Remark Codes"}
            ]}
          ]}
        ]},
      {"id": "PLB", "min": 0, "max": -1, "name": "Provider Adjustment"},
      {"id": "SE", "min": 1, "max": 1, "name": "Transaction Set Trailer"}
      ]},
    {"id": "GE", "min": 1, "max": 1, "name": "Functional Group Trailer"}
    ]},
  {"id": "IEA", "min": 1, "max": 1, "name": "Interchange Trailer"}
  ]}



	 */
}
