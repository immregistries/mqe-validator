package org.immregistries.mqe.validator.detection;


import static org.junit.Assert.assertFalse;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntityLists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImplementationDocumentationTest {

	private static final Logger logger = LoggerFactory.getLogger(ImplementationDocumentationTest.class);
	
	@Test
	public void test() {
		Set<ImplementationDetail> implementationDocumentations = ValidationRuleEntityLists.getImplementationDocumentations();
		for (ImplementationDetail implementationDetail : implementationDocumentations) {
			logger.info(implementationDetail.toString());
			assertFalse(StringUtils.isBlank(implementationDetail.getImplementationDescription()));
		}

	}
	
	

}
