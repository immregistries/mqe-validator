package com.openimmunizationsoftware.dqa.validator.common;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import com.openimmunizationsoftware.dqa.model.types.Name;

public enum CommonRules {
	INSTANCE;
	
	private static final DateUtility datr = DateUtility.INSTANCE;
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CommonRules.class);

	
	public boolean isEmpty(String value) {
		//LOGGER.debug("Evaluating: evaluateIsMissing for value: [" + value + "]");
		return StringUtils.isBlank(value);
	}
	
	public boolean isValidDate(String dateString) {
		//LOGGER.debug("Evaluating: evaluateIsValidDate for value: [" + dateString + "]");
		return datr.isDate(dateString);
	}

	public Date parseDateFrom(String dateString) {
		return datr.parseDate(dateString);
	}
	
	  public boolean isValidIdentifier(String numericId, int expectedLength)
	  {
	    if (numericId == null || 
	    	numericId.length() != expectedLength
	    //Validate that it doesn't match a couple common testing numbers: 
	    	|| numericId.equals("123456789") 
	    	|| numericId.equals("987654321") 
	    	|| hasTooManyConsecutiveChars(numericId, 6)) //six of the same digits in a row. 
	    { 
	    	return false;
	    }

	    return true;
	  }
	  
	  public boolean hasTooManyConsecutiveChars(String input, int maxConsecutiveChars)
	  {
		    char lastC = 'S';
		    int count = 0;
		    for (char c : input.toCharArray())
		    {
		      if (lastC == c)
		      {
		        count++;
		      } else
		      {
		        count = 1;
		      }
		      if (count > maxConsecutiveChars)
		      {
		        return true;
		      }
		      lastC = c;
		    }

		    return false;
		  }
	  
	  public boolean isValidNameChars(String s)
	  {
	    for (char c : s.toUpperCase().toCharArray())
	    {
	      if ((c < 'A' || c > 'Z') && c != '-' && c != '\'' && c != ' ' && c != '.')
	      {
	        return false;
	      }
	    }
	    return true;
	  }
	  
	  public boolean isEmpty(Name name)
	  {
	    boolean empty = true;
	    if (name != null)
	    {
	      if (name.getFirst() != null && !name.getFirst().equals(""))
	      {
	        empty = false;
	      } else if (name.getLast() != null && !name.getLast().equals(""))
	      {
	        empty = false;
	      }
	    }
	    return empty;
	  }
}
