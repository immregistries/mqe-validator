package org.immregistries.dqa.validator.engine.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.immregistries.dqa.validator.engine.common.DateUtility;
import org.joda.time.DateTime;
import org.junit.Test;

public class DateUtilityTest {
	DateUtility datr = DateUtility.INSTANCE;
	
	@Test
	public void testStringParsing() {
		String notADate = "saflj";
		
		assertFalse("it's garbage", datr.isDate(notADate));
		
		String isADate = "20160101";
		assertTrue("it's good, really[" + isADate + "]", datr.isDate(isADate));
//		System.out.println(datr.parseDate(isADate));
		
		isADate = "2016-01-01";
		assertTrue("it's good, really[" + isADate + "]", datr.isDate(isADate));
//		System.out.println(datr.parseDate(isADate));
		
		isADate = "19000101";
		assertTrue("it's good, really[" + isADate + "]", datr.isDate(isADate));
//		System.out.println(datr.parseDate(isADate));
		
	}
	@Test
	public void emptyDateParsing() {
		String notADate = "";
		
		assertFalse("it's emptyString", datr.isDate(notADate));
		
		String nullDate = null;
		
		assertFalse("it's null, really[" + nullDate + "]", datr.isDate(nullDate));
		 
	}
	
	@Test
	public void beforeOrAfterTesting1() {
		String date = "20160101";
		String notBefore = "20000101";
		String notAfter  = "20161231";
		
		boolean beforeOrAfter = datr.isOutsideOfRange(date, notBefore, notAfter);
		assertFalse("Date " + date + " is within dates notBefore[" + notBefore + "] notAfter[" + notAfter + "]", 
				beforeOrAfter);
		
		date = "20200101";
		beforeOrAfter = datr.isOutsideOfRange(date, notBefore, notAfter);
		assertTrue("Date " + date + " is NOT within dates notBefore[" + notBefore + "] notAfter[" + notAfter + "]", 
				beforeOrAfter);
	}
	
	@Test
	public void beforeOrAfterTesting2() {
		String date = "20160101";
		String notBefore = null;
		String notAfter  = "20121231";
		
		boolean outsideOfRange = datr.isOutsideOfRange(date, notBefore, notAfter);
		assertTrue("Date " + date + " is outside of dates notBefore[" + notBefore + "] notAfter[" + notAfter + "]", 
				outsideOfRange);
		
	}
	
	@Test
	public void ageCalculationTest() {
		DateTime today = new DateTime();
		DateTime eighteenYearsAgo = today.minusYears(18);
		DateTime seventeenYearsAgo = eighteenYearsAgo.plusDays(1);
//		System.out.println("Age 18 string: " + eighteenYearsAgo);
//		System.out.println("Age 17 string: " + seventeenYearsAgo);
		
		assertEquals("should calculate to 18", 18, datr.getAge(eighteenYearsAgo.toDate()));
		assertEquals("Should calculate to 17", 17, datr.getAge(seventeenYearsAgo.toDate()));
		assertEquals("should be zero if born today", 0, datr.getAge(today.toDate()));
		assertEquals("Should be zero if it's a null input", 0, datr.getAge(null));
		
		int superLongYears = 18923;
		assertEquals("Should be able to handle a very old age", superLongYears, datr.getAge(today.minusYears(superLongYears).toDate()));
	}
	
	@Test
	public void adultAgeCalculationTest() {
		DateTime today = new DateTime();
		DateTime eighteenYearsAgo = today.minusYears(18);
		DateTime seventeenYearsAgo = eighteenYearsAgo.plusDays(1);
		assertTrue("should be adult", datr.isAdult(eighteenYearsAgo.toDate()));
		assertFalse("should not be an adult if null is sent", datr.isAdult(null));
		assertFalse("Should not be an adult if today is birthday", datr.isAdult(new Date()));
		assertFalse("Should not be an adult if age 17", datr.isAdult(seventeenYearsAgo.toDate()));
	}
}
