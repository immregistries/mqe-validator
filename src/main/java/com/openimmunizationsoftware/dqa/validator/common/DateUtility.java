package com.openimmunizationsoftware.dqa.validator.common;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;

public enum DateUtility {
	INSTANCE;
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DateUtility.class);
	
	private static final String FORMAT_STRING = "yyyy-MM-dd";
	private static final String FORMAT_STRING_2 = "yyyyMMdd";

	DateTimeFormatter dtf = DateTimeFormat.forPattern(FORMAT_STRING);
	DateTimeFormatter dtf2 = DateTimeFormat.forPattern(FORMAT_STRING_2);
	
	public Date parseDate(String dateString) {
		try {
			DateTime dt = DateTime.parse(dateString, dtf);
			return dt.toDate();
		} catch (IllegalArgumentException iae) {
			//Try the other format:
			try {
			DateTime dt = DateTime.parse(dateString, dtf2);
			return dt.toDate();
			} catch (IllegalArgumentException iae2) {
				return null;	
			}
		}
	}
	
	public boolean isDate(String dateString) {
		return parseDate(dateString) != null;
	}
	
	/**
	 * This effectively truncates the time part of the date sent in
	 * and compares the Calendar Date itself.  
	 * So in order to be before, it has to be a different date altogether. 
	 * @param before the date that should be before the other date
	 * @param after the date that should be after. 
	 * @return
	 */
	public boolean isBeforeDate(Date before, Date after) {
		if (before == null) {
			return false;
		}
		LocalDate bfr = new LocalDate(before);
		LocalDate aftr = new LocalDate(after);
		
		return bfr.isBefore(aftr);
		
	}
	
	/**
	 * This effectively truncates the time part of the date sent in
	 * and compares the Calendar Date itself.  
	 * So in order to be After, it has to be a different date altogether. 
	 * @param dFirst the date that should be after the other date
	 * @param dSecond the date that should be before. 
	 * @return
	 */
	public boolean isAfterDate(Date after, Date before) {
		//NOTE:  You can't just call the isBeforeDate method
		//because of how it treats equals.  
		//IF it's equal, it's not after, and it's not before. 
		if (after == null) {
			return false;
		}
		LocalDate aft = new LocalDate(after);
		LocalDate bfr = new LocalDate(before);
		
		return aft.isAfter(bfr);
	}
	
	protected Date trunc(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public int monthsBetween(Date dateOne, Date dateTwo) {
		LocalDate d1 = new LocalDate(dateOne);
		LocalDate d2 = new LocalDate(dateTwo);
		
		int months = Months.monthsBetween(d1, d2).getMonths();
		
		return months;
	}
	
	
}
