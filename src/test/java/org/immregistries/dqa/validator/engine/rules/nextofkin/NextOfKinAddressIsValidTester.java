package org.immregistries.dqa.validator.engine.rules.nextofkin;

import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.vxu.DqaMessageHeader;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;
import org.immregistries.dqa.vxu.DqaPatient;
import org.immregistries.dqa.vxu.DqaAddress;
import org.immregistries.dqa.vxu.DqaPatientAddress;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Allison on 5/4/2017.
 */
public class NextOfKinAddressIsValidTester {
    private NextOfKinAddressIsValid rule = new NextOfKinAddressIsValid();

    // Parts required for the test
    private DqaMessageHeader mh = new DqaMessageHeader();
    private DqaMessageReceived mr = new DqaMessageReceived();
    private DqaNextOfKin nok = new DqaNextOfKin();
    private DqaPatient p = new DqaPatient();

    private static final Logger logger = LoggerFactory.getLogger(NextOfKinAddressIsValidTester.class);

    /**
     * Sets up the objects needed for the test. Next of kin and patient need the same address.
     */
    @Before
    public void setUpTheObjects() {
        DqaAddress addr = new DqaAddress();
        addr.setStreet("233 Cherokee Ln");
        addr.setStreet2("Apt 106");
        addr.setCity("Flint");
        addr.setStateCode("MI");
        addr.setZip("49501");
        addr.setCountryCode("USA");
        addr.setCountyParishCode("73");
        addr.setTypeCode("P");

        nok.setAddress(addr);
        setNextOfKin();

        p.getPatientAddressList().add(new DqaPatientAddress(addr));

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
        ValidationRuleResult r = rule.executeRule(nok, mr);
        assertTrue(r.isRulePassed());
    }

    /**
     * Test with a null (missing) address.
     */
    @Test
    public void testRuleNullAddress() {
        nok.setAddress(null);
        setNextOfKin();

        p.getPatientAddressList().clear();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinAddressIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with missing (null) street 1.
     */
    @Test
    public void testRuleNullStreet() {
        nok.getAddress().setStreet(null);
        setNextOfKin();

        DqaPatientAddress paddr = new DqaPatientAddress(nok.getAddress());
        p.getPatientAddressList().clear();
        p.getPatientAddressList().add(paddr);

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinAddressStreetIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with missing (null) street 2.
     */
    @Test
    public void testRuleNullStreet2() {
        nok.getAddress().setStreet2(null);
        setNextOfKin();

        DqaPatientAddress paddr = new DqaPatientAddress(nok.getAddress());
        p.getPatientAddressList().clear();
        p.getPatientAddressList().add(paddr);

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinAddressStreet2IsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with missing city.
     */
    @Test
    public void testRuleMissingCity() {
        nok.getAddress().setCity(null);
        setNextOfKin();

        DqaPatientAddress paddr = new DqaPatientAddress(nok.getAddress());
        p.getPatientAddressList().clear();
        p.getPatientAddressList().add(paddr);

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(r.getIssues().size() > 0);
        assertEquals(Detection.NextOfKinAddressCityIsMissing , r.getIssues().get(0).getIssue());
    }

    /**
     * Test with missing (null) state.
     */
    @Test
    public void testRuleNullState() {
        nok.getAddress().setStateCode(null);
        setNextOfKin();

        DqaPatientAddress paddr = new DqaPatientAddress(nok.getAddress());
        p.getPatientAddressList().clear();
        p.getPatientAddressList().add(paddr);

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinAddressStateIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with missing zip.
     */
    @Test
    public void testRuleMissingZip() {
        nok.getAddress().setZip(null);
        setNextOfKin();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinAddressZipIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with invalid zip.
     */
    @Test
    public void testRuleInvalidZip() {
        nok.getAddress().setZip("abc");
        setNextOfKin();

        // TODO this invalidates Canadian zipcodes too :/

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinAddressZipIsInvalid == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with missing (null) country.
     */
    @Test
    public void testRuleNullCountry() {
        nok.getAddress().setCountryCode(null);
        setNextOfKin();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinAddressCountryIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with "bad address" address type.
     */
    @Test
    public void testRuleBadAddressType() {
        nok.getAddress().setTypeCode("BA");
        setNextOfKin();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinAddressTypeIsValuedBadAddress == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with unrecognized address type.
     */
    @Test
    public void testRuleUnrecognizedAddressType() {
        nok.getAddress().setTypeCode("abc");
        setNextOfKin();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinAddressTypeIsUnrecognized == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with missing (null) address type.
     */
    @Test
    public void testRuleNullAddressType() {
        nok.getAddress().setTypeCode(null);
        setNextOfKin();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinAddressTypeIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with address different from patient address.
     * ("different" is defined as city, state, street, or street 2 being different)
     */
    @Test
    public void testRuleDifferentFromPatient() {
        nok.getAddress().setStreet("123 Main St.");
        setNextOfKin();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinAddressIsDifferentFromPatientAddress == r.getIssues().get(0).getIssue());
    }

    /**
     * Set the next-of-kin in the message.
     */
    private void setNextOfKin() {
        List<DqaNextOfKin> noks = new ArrayList<>();
        noks.add(nok);
        mr.setNextOfKins(noks);
    }
}
