package org.immregistries.mqe.validator.engine;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import static org.junit.Assert.assertEquals;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntityLists;
import org.junit.Test;

public class ValidationRulesOverlapTest {
	
	  // Checks for duplicate detections
	  public Map<Detection, List<String>> hasDuplicateDetection(List<ValidationRule> rules) {
		  Map<Detection, String> detections = new HashMap<Detection, String>();
		  Map<Detection, List<String>> duplicates = new HashMap<Detection, List<String>>();
		  boolean hasDuplicates = false;
		  
		  for(ValidationRule rule : rules){
			  Set<Detection> ruleDetections = rule.getRuleDetections();
			  for(Detection d: ruleDetections) {
				  if(detections.containsKey(d)) {
	    				if(duplicates.containsKey(d)) {
	    					duplicates.get(d).add(rule.getClass().getName());
	    				} else {
	    					duplicates.put(d, new ArrayList<>(Arrays.asList(detections.get(d), rule.getClass().getName())));
	    				}
				  } else {
	    				detections.put(d, rule.getClass().getName());
				  }
			  }
		  }
		  return duplicates;
	  }
	  
	  public String printDuplicates(Map<Detection, List<String>> duplicates) {
		  StringBuilder log = new StringBuilder();
		  for(Entry<Detection, List<String>> entry: duplicates.entrySet()) {
			  log.append("Detection "+ entry.getKey().getMqeMqeCode() + " - " + entry.getKey().getDisplayText() + " is used in rules :\n");
			  for(String rule: entry.getValue()) {
				  log.append(" * "+rule+ "\n");
			  }
		  }
		  return log.toString();
	  }
	  
	  @Test
	  public void messageHeaderHasDuplicate() {
		  Map<Detection, List<String>> duplicates = this.hasDuplicateDetection(ValidationRuleEntityLists.MESSAGE_HEADER_RULES.getRules());
		  assertEquals(this.printDuplicates(duplicates), duplicates.size(), 0);
	  }
	  
	  @Test
	  public void patientHasDuplicate() {
		  Map<Detection, List<String>> duplicates = this.hasDuplicateDetection(ValidationRuleEntityLists.PATIENT_RULES.getRules());
		  assertEquals(this.printDuplicates(duplicates), duplicates.size(), 0);
	  }
	  
	  @Test
	  public void vaccinationHasDuplicate() {
		  Map<Detection, List<String>> duplicates = this.hasDuplicateDetection(ValidationRuleEntityLists.VACCINATION_RULES.getRules());
		  assertEquals(this.printDuplicates(duplicates), duplicates.size(), 0);
	  }
	  
	  @Test
	  public void nextOfKinHasDuplicate() {
		  Map<Detection, List<String>> duplicates = this.hasDuplicateDetection(ValidationRuleEntityLists.NEXT_OF_KIN_RULES.getRules());
		  assertEquals(this.printDuplicates(duplicates), duplicates.size(), 0);
	  }


}
