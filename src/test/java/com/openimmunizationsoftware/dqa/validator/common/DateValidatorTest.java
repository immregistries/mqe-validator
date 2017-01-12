package com.openimmunizationsoftware.dqa.validator.common;

import static org.junit.Assert.*;

import org.junit.Test;

public class DateValidatorTest {
	DateUtility datr = DateUtility.INSTANCE;
	
	@Test
	public void testStringParsing() {
		String notADate = "saflj";
		
		assertFalse("it's garbage", datr.isDate(notADate));
		
		String isADate = "20160101";
		
		assertTrue("it's good, really[" + isADate + "]", datr.isDate(isADate));
		 
		System.out.println(datr.parseDate(isADate));
	}

}
