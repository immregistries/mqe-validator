package com.openimmunizationsoftware.dqa.validator.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.openimmunizationsoftware.dqa.model.types.Address;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.IssueType;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;
 
/**
 * This is to evaluate the basic expectations for an address in the system
 * this does not guarantee that the address is real or that the street address is properly formatted.
 * this evaluates the very minimum required for the address to be evaluated. 
 * @author Josh
 *
 */
public enum AddressValidator {
	INSTANCE;
	private CommonRules common = CommonRules.INSTANCE;
	
	public ValidationRuleResult getAddressIssuesFor(AddressFields fields, Address a) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		if (a == null) {
			issues.add(PotentialIssue.buildIssue(fields.getAddress(),
					IssueType.MISSING));
			passed = false;
		} else {

			addStreetIssues(a.getStreet2(), fields.getStreet2Field(), issues);
			//Don't care about street 2 issues for "passed".
			int baseline = issues.size();

			//Do care about the rest of the issues for the "passed" designation. 
			addStreetIssues(a.getStreet(), fields.getStreetField(), issues);
			addCityIssues(a.getCity(), fields.getCityField(), issues);
			addStateIssues(a.getStateCode(), fields.getStateField(), issues);
			addZipIssues(a.getZip(), fields.getZipField(), issues);
			addCountyIssues(a.getCountyParishCode(), fields.getCountyField(),issues);
			addCountryIssues(a.getCountryCode(), fields.getCountryField(),issues);

			if (issues.size() > baseline) {
				passed = false;
			}
		}
		
		ValidationRuleResult result = new ValidationRuleResult();
		result.setIssues(issues);
		result.setRulePassed(passed);
		return result;
	}

	protected void addStreetIssues(String street, IssueField field, List<IssueFound> issues) {
		if (common.isEmpty(street)) {
			issues.add(PotentialIssue.buildIssue(field, IssueType.MISSING));
		} else {
			if (!validCity(street)) {
				issues.add(PotentialIssue.buildIssue(field, IssueType.INVALID, street));
			}
		}
	}
	
	protected void addCityIssues(String city, IssueField field, List<IssueFound> issues) {
		if (common.isEmpty(city)) {
			issues.add(PotentialIssue.buildIssue(field, IssueType.MISSING));
		} else {
			if (!validCity(city)) {
				issues.add(PotentialIssue.buildIssue(field, IssueType.INVALID, city));
			}
		}
	}
	
	protected void addStateIssues(String state, IssueField field, List<IssueFound> issues) {
		if (common.isEmpty(state)) {
			issues.add(PotentialIssue.buildIssue(field, IssueType.MISSING));
		} else {
			if (!validState(state)) {
				issues.add(PotentialIssue.buildIssue(field, IssueType.INVALID, state));
			}
		}
	}
	
	protected void addZipIssues(String zip, IssueField field, List<IssueFound> issues) {
		if (common.isEmpty(zip)) {
			issues.add(PotentialIssue.buildIssue(field,  IssueType.MISSING));
		} else {
			if (!isValidZip(zip)) {
				issues.add(PotentialIssue.buildIssue(field, IssueType.INVALID, zip));
			}
		}
	}
	
	protected void addCountyIssues(String county, IssueField field, List<IssueFound> issues) {
		if (common.isEmpty(county)) {
			issues.add(PotentialIssue.buildIssue(field,  IssueType.MISSING));
		} 
	}
	
	protected void addCountryIssues(String country, IssueField field, List<IssueFound> issues) {
		if (common.isEmpty(country)) {
			issues.add(PotentialIssue.buildIssue(field,  IssueType.MISSING));
		} else {
			if (!validCountryCode(country)) {
				issues.add(PotentialIssue.buildIssue(field, IssueType.INVALID, country));
			}
		}
	}
	
	protected boolean validCity(String city) {
	      return 
	     	   !common.isEmpty(city) 
	    	&& !city.equalsIgnoreCase("ANYTOWN") 
	    	&& city.length() > 1
	    	;
	}
	
	protected boolean validState(String state) {
	      return 
	     	   !common.isEmpty(state) 
	    	&& state.length() > 1
	    	;
	}

	public boolean isValid(Address a) {
		boolean valid = 
				validCity(a.getCity())
				&& validState(a.getStateCode())
				&& (validCountryCode(a.getCountryCode())|| validCountryCode(a.getCountry().getCode()))
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
