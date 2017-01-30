package org.immregistries.dqa.validator.engine.common;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;

public enum DateUtility {
	INSTANCE;
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DateUtility.class);
	
	private final DateTimeFormatter dtf1 = DateTimeFormat.forPattern("yyyyMMdd");
	private final DateTimeFormatter dtf2 = DateTimeFormat.forPattern("yyyy-MM-dd");
	private final DateTimeFormatter dtf3 = DateTimeFormat.forPattern("yyyy/MM/dd");
	private final DateTimeFormatter[] DATE_FORMATS = {dtf1,dtf2,dtf3};
	
	public Date parseDate(String dateString) {
		
		DateTime dt = parseDateTime(dateString);
		
		if (dt != null) {
			return dt.toDate();
		}
		
		return null;
	}
	
	public DateTime parseDateTime(String dateString) {
		
		if (StringUtils.isEmpty(dateString)) {
			return null;
		}
		
		//This makes the class more flexible. 
		for (DateTimeFormatter dateFormatter : DATE_FORMATS) {
			try {
				DateTime dt = DateTime.parse(dateString, dateFormatter);
				return dt;
			} catch (IllegalArgumentException iae) {
				continue;//try the next format. 
			}
		}
		
		return null;
	}
	
//	This puts a dateTime object to the DQA's expected String format. 
	public String toString(DateTime input) {
		return input.toString(dtf1);
	}
	
	public String toString(Date input) {
		return new DateTime(input).toString(dtf1);
	}
	
	public boolean isDate(String dateString) {
		return parseDate(dateString) != null;
	}
	
	public boolean isOutsideOfRange(String isThis, String beforeThis, String orAfterThis) {
		logger.debug("dates to parse for evaluating isOutsideOfRange: isThis["+isThis+"] beforeThis["+beforeThis+"] orAFterThis["+orAfterThis+"]");

		Date isThisDate = parseDate(isThis);
		Date beforeThisDate = parseDate(beforeThis);
		Date orAfterThisDate = parseDate(orAfterThis);
		
		return isDateOutsideOfRange(isThisDate, beforeThisDate, orAfterThisDate);
	}
	
	/**
	 * This evaluates whether the given date is within the second and third dates privided. 
	 * a "true" answer means the given date is outside of the range. 
	 * @param isThis
	 * @param beforeThis
	 * @param orAfterThis
	 * @return
	 */
	public boolean isDateOutsideOfRange(Date isThis, Date beforeThis, Date orAfterThis) {
		if (isThis == null || (beforeThis == null && orAfterThis == null)) {
			logger.debug("One of these is not a date: isThis["+isThis+"] beforeThis["+beforeThis+"] orAFterThis["+orAfterThis+"]");
			return false;
		}
		
		if (isAfterDate(isThis, orAfterThis)) {
			logger.debug("date " + isThis + " is AFTER end of range:" + orAfterThis);
			return true;
		} else if (isBeforeDate(isThis, beforeThis)) {
			logger.debug("date " + isThis + " is BEFORE beginning of range:" + beforeThis);
			return true;
		}
		logger.debug("date " + isThis + " is within range " + beforeThis + " to " + orAfterThis);
		return false;
	}
	
	/**
	 * This effectively truncates the time part of the date sent in
	 * and compares the Calendar Date itself.  
	 * So in order to be After, it has to be a different date altogether. 
	 * @param dFirst the date that should be after the other date
	 * @param dSecond the date that should be before. 
	 * @return
	 */
	public boolean isAfterDate(Date isThisDate, Date afterThis) {
		//NOTE:  You can't just call the isBeforeDate method
		//because of how it treats equals.  
		//IF it's equal, it's not after, and it's not before. 
		if (isThisDate == null || afterThis == null) {
			return false;
		}
		LocalDate thisIs = new LocalDate(isThisDate);
		LocalDate isItAfterThis = new LocalDate(afterThis);
		
		return thisIs.isAfter(isItAfterThis);
	}
	
	/**
	 * This effectively truncates the time part of the date sent in
	 * and compares the Calendar Date itself.  
	 * So in order to be before, it has to be a different date altogether. 
	 * @param isThis the date that should be before the other date
	 * @param beforeThis the date that should be after. 
	 * @return
	 */
	public boolean isBeforeDate(Date isThis, Date beforeThis) {
		if (isThis == null || beforeThis == null) {
			return false;
		}
		
		LocalDate thisDt = new LocalDate(isThis);
		LocalDate boundaryDt = new LocalDate(beforeThis);
		
		return thisDt.isBefore(boundaryDt);
	}
	
//	protected Date trunc(Date d) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(d);
//		cal.set(Calendar.HOUR_OF_DAY, 0);
//		cal.set(Calendar.MINUTE, 0);
//		cal.set(Calendar.SECOND, 0);
//		cal.set(Calendar.MILLISECOND, 0);
//		return cal.getTime();
//	}

	public int monthsBetween(Date dateOne, Date dateTwo) {
		LocalDate d1 = new LocalDate(dateOne);
		LocalDate d2 = new LocalDate(dateTwo);
		
		int months = Months.monthsBetween(d1, d2).getMonths();
		
		return months;
	}
	
	public boolean isAdult(Date birthDate) {
		return this.getAge(birthDate) >= 18;
	}

	public int getAge(Date birthDate) {
		DateTime bDateTime = new DateTime(birthDate);
		return Years.yearsBetween(bDateTime, new DateTime()).getYears();
	}
	
	
}
