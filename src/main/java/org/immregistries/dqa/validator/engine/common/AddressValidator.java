package org.immregistries.dqa.validator.engine.common;

import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.hl7util.model.Hl7Location;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.IssueType;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaAddress;
import org.immregistries.dqa.vxu.MetaFieldInfoData;
import org.immregistries.dqa.vxu.VxuField;

import java.util.ArrayList;
import java.util.List;
 
/**
 * This is to evaluate the basic expectations for an address in the system
 * this does not guarantee that the address is real or that the street address is properly formatted.
 * this evaluates the very minimum required for the address to be evaluated. 
 * @author Josh Hull
 *
 */
public enum AddressValidator {
	INSTANCE;
	private CommonRules common = CommonRules.INSTANCE;
	
	public ValidationRuleResult getAddressIssuesFor(AddressFields fields, DqaAddress a, MetaFieldInfoData meta) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		if (a == null) {
			issues.add(makeIssue(fields.getAddress(), IssueType.MISSING, meta));
			passed = false;
		} else {
			addStreetIssues(a.getStreet2(), fields.getStreet2Field(), issues, meta);
			//Don't care about street 2 issues for "passed".
			int baseline = issues.size();

			//Do care about the rest of the issues for the "passed" designation. 
			addStreetIssues(a.getStreet(), fields.getStreetField(), issues, meta);
			addCityIssues(a.getCity(), fields.getCityField(), issues, meta);
			addStateIssues(a.getStateCode(), fields.getStateField(), issues, meta);
			addZipIssues(a.getZip(), fields.getZipField(), issues, meta);
			addCountyIssues(a.getCountyParishCode(), fields.getCountyField(),issues, meta);
			addCountryIssues(a.getCountryCode(), fields.getCountryField(),issues, meta);

			if (issues.size() > baseline) {
				passed = false;
			}
		}
		
		ValidationRuleResult result = new ValidationRuleResult();
		result.setIssues(issues);
		result.setRulePassed(passed);
		return result;
	}

	protected void addStreetIssues(String street, VxuField field, List<ValidationIssue> issues, MetaFieldInfoData meta) {
		if (common.isEmpty(street)) {
			issues.add(makeIssue(field, IssueType.MISSING, meta));
		} else {
			if (!validCity(street)) {
				issues.add(makeIssue(field, IssueType.INVALID, street, meta));
			}
		}
	}
	
	protected void addCityIssues(String city, VxuField field, List<ValidationIssue> issues, MetaFieldInfoData meta) {
		if (common.isEmpty(city)) {
			issues.add(makeIssue(field, IssueType.MISSING, meta));
			issues.add(makeIssue(field, IssueType.MISSING, meta));
		} else {
			if (!validCity(city)) {
			    issues.add(makeIssue(field, IssueType.INVALID, city, meta));
			}
		}
	}

	protected ValidationIssue makeIssue(VxuField field, IssueType type, String value, MetaFieldInfoData meta) {
      Detection detection = Detection.get(field, type);
      return detection.build(value, meta);
  }

    protected ValidationIssue makeIssue(VxuField field, IssueType type, MetaFieldInfoData meta) {
        Detection detection = Detection.get(field, type);
        return detection.build(meta);
    }
	
	protected void addStateIssues(String state, VxuField field, List<ValidationIssue> issues, MetaFieldInfoData meta) {
		if (common.isEmpty(state)) {
			issues.add(makeIssue(field, IssueType.MISSING, meta));
		} else {
			if (!validState(state)) {
				issues.add(makeIssue(field, IssueType.INVALID, state, meta));
			}
		}
	}
	
	protected void addZipIssues(String zip, VxuField field, List<ValidationIssue> issues, MetaFieldInfoData meta) {
		if (common.isEmpty(zip)) {
			issues.add(makeIssue(field,  IssueType.MISSING, meta));
		} else {
			if (!isValidZip(zip)) {
				issues.add(makeIssue(field, IssueType.INVALID, zip, meta));
			}
		}
	}
	
	protected void addCountyIssues(String county, VxuField field, List<ValidationIssue> issues, MetaFieldInfoData meta) {
		if (common.isEmpty(county)) {
			issues.add(makeIssue(field,  IssueType.MISSING, meta));
		} 
	}
	
	protected void addCountryIssues(String country, VxuField field, List<ValidationIssue> issues, MetaFieldInfoData meta) {
		if (common.isEmpty(country)) {
			issues.add(makeIssue(field,  IssueType.MISSING, meta));
		} else {
			if (!validCountryCode(country)) {
				issues.add(makeIssue(field, IssueType.INVALID, country, meta));
			}
		}
	}
	
	protected boolean validCity(String city) {
	      return 
	     	   StringUtils.isNotBlank(city) 
	    	&& !city.equalsIgnoreCase("ANYTOWN") 
	    	&& city.length() > 1
	    	;
	}
	
	protected boolean validState(String state) {
	      return 
	     	   StringUtils.isNotBlank(state) 
	    	&& state.length() > 1
	    	;
	}

	public boolean isValid(DqaAddress a) {
		boolean valid = 
				validCity(a.getCity())
				&& validState(a.getStateCode())
				&& (validCountryCode(a.getCountryCode())|| validCountryCode(a.getCountryCode()))
				&& validStreetAddress(a.getStreet());
				
		if (!valid) {
			return false;
		}
		
		//now evaluate conditional stuff. 
		
		//only evalutes if the previous was true. 
		if ("USA".equals(a.getCountryCode())) {
			valid = isValidZip(a.getZip());
		}
		
		if (!valid) {
			return false;
		}
		
		return valid;
	}
	
	protected boolean validCountryCode(String countryCode) {
		return "USA".equalsIgnoreCase(countryCode) ||  "us".equalsIgnoreCase(countryCode)
			|| "mx".equalsIgnoreCase(countryCode) || "mex".equalsIgnoreCase(countryCode);
	}
	
	protected boolean validStreetAddress(String street) {
		return StringUtils.isNotEmpty(street);
	}

	protected boolean isValidZip(String zip) {
	        int dash = zip.indexOf('-');
	        boolean valid = true;
	        if (dash == -1)
	        {
	          if (zip.length() != 5)
	          {
	            valid = false;
	          }
	        } else if (dash != 5)
	        {
	          valid = false;
	        }
	        if (valid && zip.length() >= 5)
	        {
	          for (int i = 0; i < zip.length(); i++)
	          {
	            if (i == 5)
	            {
	              continue;
	            }
	            char c = zip.charAt(i);
	            if (c < '0' || c > '9')
	            {
	              valid = false;
	              break;
	            }
	          }
	        }
	        
	        return valid;
	}
	
}
