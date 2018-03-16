package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.vxu.DqaMessageHeader;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.immregistries.dqa.vxu.DqaPatientAddress;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Created by Allison on 5/12/2017.
 */
public class PatientAddressIsValidTester {
    private PatientAddressIsValid rule = new PatientAddressIsValid();

    // Parts required for the test
    private DqaMessageHeader mh = new DqaMessageHeader();
    private DqaMessageReceived mr = new DqaMessageReceived();
    private DqaPatient p = new DqaPatient();

    private static final Logger logger = LoggerFactory.getLogger(PatientAddressIsValidTester.class);

    /**
     * Sets up the objects needed for the test. Next of kin and patient need the same address.
     */
    @Before
    public void setUpTheObjects() {
        DqaPatientAddress addr = new DqaPatientAddress();
        addr.setStreet("233 Cherokee Ln");
        addr.setStreet2("Apt 106");
        addr.setCity("Flint");
        addr.setStateCode("MI");
        addr.setZip("49501");
        addr.setCountryCode("USA");
        addr.setCountyParishCode("73");
        addr.setTypeCode("P");

        p.getPatientAddressList().add(addr);

        mh.setMessageDate(new Date());
        mr.setMessageHeader(mh);
        mr.setPatient(p);
    }

    /**
     * Test the basic rule with a valid address.
     * (should be true)
     */
    @Test
    public void testRule() {
        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(r.isRulePassed());
    }

    /**
     * Test with a null (missing) address.
     */
    @Test
    public void testRuleNullAddress() {
        p.getPatientAddressList().clear();
        p.getPatientAddressList().add(null);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.PatientAddressIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with missing (null) street 1.
     */
    @Test
    public void testRuleNullStreet() {
        p.getPatientAddress().setStreet(null);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.PatientAddressStreetIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with missing (null) street 2.
     */
    @Test
    public void testRuleNullStreet2() {
        p.getPatientAddress().setStreet2(null);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.PatientAddressStreet2IsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with missing city.
     */
    @Test
    public void testRuleMissingCity() {
        p.getPatientAddress().setCity(null);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.PatientAddressCityIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with missing (null) state.
     */
    @Test
    public void testRuleNullState() {
        p.getPatientAddress().setStateCode(null);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.PatientAddressStateIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with missing zip.
     */
    @Test
    public void testRuleMissingZip() {
        p.getPatientAddress().setZip(null);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.PatientAddressZipIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with invalid zip.
     */
    @Test
    public void testRuleInvalidZip() {
        p.getPatientAddress().setZip("abc");

        // TODO this invalidates Canadian zipcodes too :/

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.PatientAddressZipIsInvalid == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with missing (null) country.
     */
    @Test
    public void testRuleNullCountry() {
        p.getPatientAddress().setCountryCode(null);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.PatientAddressCountryIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with missing (null) county.
     */
    @Test
    public void testRuleNullCounty() {
        p.getPatientAddress().setCountyParishCode(null);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.PatientAddressCountyIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with "bad address" address type.
     */
    @Test
    public void testRuleBadAddressType() {
        p.getPatientAddress().setTypeCode("BA");

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.PatientAddressTypeIsValuedBadAddress == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with unrecognized address type.
     */
    @Test
    public void testRuleUnrecognizedAddressType() {
        p.getPatientAddress().setTypeCode("abc");

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.PatientAddressTypeIsUnrecognized == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with missing (null) address type.
     */
    @Test
    public void testRuleNullAddressType() {
        p.getPatientAddress().setTypeCode(null);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.PatientAddressTypeIsMissing == r.getIssues().get(0).getIssue());
    }
}
