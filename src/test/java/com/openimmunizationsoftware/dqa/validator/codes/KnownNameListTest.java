package com.openimmunizationsoftware.dqa.validator.codes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.openimmunizationsoftware.dqa.validator.codes.model.KnownName.NameType;

public class KnownNameListTest {

	KnowNameList listr = KnowNameList.INSTANCE;
	@Test
	public void test() {
		boolean matches = listr.matches(NameType.UNNAMED_NEWBORN, "", "LEGALLAST", "");
		
		if (matches) {
			System.out.println("UM... why...");
		}
		
		assertEquals("shouldn't be a match...", false, matches );
	}

}
