package org.immregistries.mqe.validator.detection;


import java.util.Set;

import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntityLists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImplementationDocumentationTest {

	private static final Logger logger = LoggerFactory.getLogger(ImplementationDocumentationTest.class);
	
	@Test
	public void test() {
		try {
		Set<ImplementationDetail> implementationDocumentations = ValidationRuleEntityLists.getImplementationDocumentations();
		for (ImplementationDetail implementationDetail : implementationDocumentations) {
			System.out.println(implementationDetail.toString());
		}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception");
		}
	}
	
	

}
