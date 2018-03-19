package org.immregistries.dqa.validator.engine.rules.vaccination;

import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.vxu.DqaMessageHeader;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VaccineProductIsValidTester {
    private VaccinationProductIsValid rule = new VaccinationProductIsValid();

    // Parts required for the test
    private DqaMessageHeader mh = new DqaMessageHeader();
    private DqaMessageReceived mr = new DqaMessageReceived();
    private DqaVaccination v = new DqaVaccination();

    private static final Logger logger = LoggerFactory.getLogger(VaccineProductIsValidTester.class);

    /**
     * Sets up the objects needed for the test.
     */
    @Before
    public void setUpTheObjects() {
        mh.setMessageDate(new Date());
        mr.setMessageHeader(mh);
        mr.getVaccinations().add(v);
    }

    /**
     * Test the basic rule with a valid type code.
     * (should be true)
     */
    @Test
    public void testRule() {
        v.setProduct("KINRIX");
        ValidationRuleResult r = rule.executeRule(v, mr);
        logger.info(r.getIssues().toString());
        assertTrue(r.isRulePassed());
    }

    /**
     * Test the rule with a null type code.
     */
    @Test
    public void testRuleNullType() {
        v.setProduct(null);
        ValidationRuleResult r = rule.executeRule(v, mr);
        logger.info(r.getIssues().toString());
        assertEquals("Should be one issue when value is invalid"
            , 1, r.getIssues().size());
        assertEquals("Should be Unrecognized",Detection.VaccinationProductIsMissing , r.getIssues().get(0).getIssue());
    }

    /**
     * Test the rule with a null type code.
     */
    @Test
    public void testRuleInvalidValue() {
        v.setProduct("KINRIXOS");
        ValidationRuleResult r = rule.executeRule(v, mr);
        logger.info(r.getIssues().toString());
        assertEquals("Should be one issue when value is invalid"
            , 1, r.getIssues().size());
        assertEquals("Should be Unrecognized",Detection.VaccinationProductIsUnrecognized , r.getIssues().get(0).getIssue());
    }
}
