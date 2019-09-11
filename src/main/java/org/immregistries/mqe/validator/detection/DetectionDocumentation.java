package org.immregistries.mqe.validator.detection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.text.WordUtils;
import org.immregistries.mqe.hl7util.SeverityLevel;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntityLists;
import org.immregistries.mqe.vxu.VxuField;
import org.immregistries.mqe.vxu.VxuObject;
import org.reflections.Reflections;

public class DetectionDocumentation {
	
	public static class DetectionDocumentationPayload {
		private String description;
		private boolean active;
		private Detection detection;
		private Map<String, String> implementationDetailsPerRule;
		private String text;
		private String code;
		
		public DetectionDocumentationPayload(){}
		public DetectionDocumentationPayload(String description, boolean active, Detection detection, String text, String code, Map<String, String> implementationDetailsPerRule) {
			super();
			this.description = description;
			this.active = active;
			this.detection = detection;
			this.text = text;
			this.implementationDetailsPerRule = implementationDetailsPerRule;
			this.code = code;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public boolean isActive() {
			return active;
		}
		public void setActive(boolean active) {
			this.active = active;
		}
		public Detection getDetection() {
			return detection;
		}
		public void setDetection(Detection detection) {
			this.detection = detection;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public Map<String, String> getImplementationDetailsPerRule() {
			return implementationDetailsPerRule;
		}
		public void setImplementationDetailsPerRule(Map<String, String> implementationDetailsPerRule) {
			this.implementationDetailsPerRule = implementationDetailsPerRule;
		}
		
		
	}
	
	protected Map<String, Map<String, Map<String, List<DetectionDocumentationPayload>>>> detections;
	
	public int sizeForObject(String o){
		Map<String, Map<String, List<DetectionDocumentationPayload>>> sub = this.detections.get(o);
		int count = 0;
		if(sub != null){
			for(String field : sub.keySet()){
				for(String severity : sub.get(field).keySet()){
					count += sub.get(field).get(severity).size();
				}
			}
		}
		return count;
	}
	
	public int sizeForField(String o, String v){
		Map<String, List<DetectionDocumentationPayload>> sub = this.detections.get(o) != null ? this.detections.get(o).get(v) : null;
		int count = 0;
		if(sub != null){
			for(String severity : sub.keySet()){
				count += sub.get(severity).size();
			}
		}
		return count;
	}
	
	public int sizeForSeverity(String o, String v, String s){
		try {
			return this.detections.get(o).get(v).get(s).size();
		}
		catch(Exception e){
			return 0;
		}
	}
	
	public Map<String, Map<String, Map<String, List<DetectionDocumentationPayload>>>> getDetections() {
		return detections;
	}

	public void setDetections(
			Map<String, Map<String, Map<String, List<DetectionDocumentationPayload>>>> detections) {
		this.detections = detections;
	}

	public static DetectionDocumentation getDetectionDocumentation() throws NoSuchFieldException, SecurityException {
		DetectionDocumentation documentation = new DetectionDocumentation();
		documentation.detections = new HashMap<>();
		
		Reflections reflections = new Reflections("org.immregistries.mqe");
		Set<Class<? extends ValidationRule>> subTypes = reflections.getSubTypesOf(ValidationRule.class);
		List<ValidationRule> rules = new ArrayList<>();
		for(Class<? extends ValidationRule> rule : subTypes) {
			try {
				rules.add(rule.newInstance());
			} catch (Exception e) {
			}
		}
		
		for(Detection detection : Detection.values()){
			addVxuObject(documentation.detections, detection.getTargetObject().getDescription(), detection, getImplementationDetails(rules));
		}
		return documentation;
	}
	
	private static void addVxuObject(Map<String, Map<String, Map<String, List<DetectionDocumentationPayload>>>> detections, String object, Detection detection, Map<String, Map<String, String>> implementationDetails) throws NoSuchFieldException, SecurityException{
		String field_str = WordUtils.capitalizeFully(detection.getTargetField().getFieldDescription()) + (detection.getTargetField().getHl7Locator() != null ? " ("+detection.getTargetField().getHl7Locator().toUpperCase()+")" : "");
		if(detections.containsKey(object)){
			addVxuField(detections.get(object), field_str, detection, implementationDetails);
		}
		else {
			Map<String, Map<String, List<DetectionDocumentationPayload>>> tmp = new HashMap<>();
			detections.put(object, tmp);
			addVxuField(tmp, field_str, detection, implementationDetails);
		}
	}
	
	private static void addVxuField(Map<String, Map<String, List<DetectionDocumentationPayload>>> detections, String field, Detection detection, Map<String, Map<String, String>> implementationDetails) throws NoSuchFieldException, SecurityException{
		if(detections.containsKey(field)){
			addSeverityLevel(detections.get(field), detection.getSeverity().getCode(), detection, implementationDetails);
		}
		else {
			Map<String, List<DetectionDocumentationPayload>> tmp = new HashMap<>();
			detections.put(field, tmp);
			addSeverityLevel(tmp, detection.getSeverity().getCode(), detection, implementationDetails);
		}
	}
	
	private static void addSeverityLevel(Map<String, List<DetectionDocumentationPayload>> detections, String severity, Detection detection, Map<String, Map<String, String>> implementationDetails) throws NoSuchFieldException, SecurityException{
		if(detections.containsKey(severity)){
			detections.get(severity).add(payload(detection, implementationDetails));
		}
		else {
			List<DetectionDocumentationPayload> tmp = new ArrayList<>();
			detections.put(severity, tmp);
			tmp.add(payload(detection, implementationDetails));
		}
	}
	
	private static DetectionDocumentationPayload payload(Detection detection, Map<String, Map<String, String>> implementationDetails) throws NoSuchFieldException, SecurityException{
		boolean active = ValidationRuleEntityLists.activeDetections().contains(detection);
		String description = "";
		
		if(Detection.class.getField(detection.name()).isAnnotationPresent(Documentation.class)){
			description = Detection.class.getField(detection.name()).getAnnotation(Documentation.class).value();
		}
		
		
		
		return new DetectionDocumentationPayload(description, active, detection, detection.getDisplayText(), detection.getMqeMqeCode(), implementationDetails.get(detection.getMqeMqeCode()));
	}
	
	// code - rule - details
	private static Map<String, Map<String, String>> getImplementationDetails(List<ValidationRule> rules) {
		Map<String, Map<String, String>> implementationDetails = new HashMap<>();
		for(ValidationRule rule: rules) {
			String ruleName = rule.getClass().getSimpleName();
			Set<ImplementationDetail> ruleDetails = rule.getImplementationDocumentation();
			
			for(ImplementationDetail details: ruleDetails) {
				if(implementationDetails.containsKey(details.getDetection().getMqeMqeCode())) {
					implementationDetails.get(details.getDetection().getMqeMqeCode()).put(ruleName, details.getImplementationDescription());
				} else {
					Map<String, String> implementation = new HashMap<>();
					implementation.put(ruleName, details.getImplementationDescription());
					implementationDetails.put(details.getDetection().getMqeMqeCode(), implementation);
				}
			}
		}
		
		return implementationDetails;
	}

}
