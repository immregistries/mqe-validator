package org.immregistries.dqa.validator.engine.codes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.immregistries.dqa.codebase.client.generated.Code;
import org.junit.Test;

public class CodeRepositoryTest {

	CodeRepository repo = CodeRepository.INSTANCE;
	
	@Test
	public void testGetProductCode() {
		Code product = repo.getVaccineProduct("113","PMC", "20160101");
		assertNotNull("Should have found a DECAVAC product for 20160101", product);
		assertEquals("Should be DECAVAC for 20160101", "DECAVAC", product.getValue());
		
		product = repo.getVaccineProduct("113","PMC", "19500101");
		assertNotNull("Should have found a product for 19500101", product);
		assertEquals("Should be TENIVAC for 19500101. It is the closest", "TENIVAC", product.getValue());
		
		product = repo.getVaccineProduct("113","PMC", "19840101");
		assertNotNull("Should have found a TENVAC product", product);
		assertEquals("Should be TENIVAC for 19840101", "TENIVAC", product.getValue());
		
		product = repo.getVaccineProduct("113","PMC", "20030101");
		assertNotNull("Should have found a DECAVAC product for 20030101", product);
		assertEquals("Should be DECAVAC for 20030101", "DECAVAC", product.getValue());
		
		
	}
}

/*
<code>
<value>TENIVAC</value>
<label>Tenivac</label>
<description></description>
<code-status>
    <status>Valid</status>
</code-status>
<reference>
    <link-to codeset="VACCINATION_CVX_CODE">113</link-to>
    <link-to codeset="VACCINATION_MANUFACTURER_CODE">PMC</link-to>
</reference>
<use-date>
    <not-before>19840101</not-before>
    <not-expected-before>19840101</not-expected-before>
</use-date>
</code>
-----rebranding????
<code>
<value>DECAVAC</value>
<label>Td (adult) preservative free</label>
<description></description>
<code-status>
    <status>Valid</status>
</code-status>
<reference>
    <link-to codeset="VACCINATION_CVX_CODE">113</link-to>
    <link-to codeset="VACCINATION_MANUFACTURER_CODE">PMC</link-to>
</reference>
<use-date>
    <not-before>20030101</not-before>
    <not-expected-before>20030101</not-expected-before>
</use-date>
</code>

*/