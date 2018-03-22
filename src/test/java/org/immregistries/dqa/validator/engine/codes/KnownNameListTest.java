package org.immregistries.dqa.validator.engine.codes;

import static org.junit.Assert.assertEquals;

import org.immregistries.dqa.validator.engine.codes.KnownName.NameType;
import org.junit.Test;

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
