package org.immregistries.dqa.validator.engine.rules.nextofkin;

import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.vxu.DqaMessageHeader;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;
import org.immregistries.dqa.vxu.DqaPhoneNumber;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Allison on 5/4/2017.
 */
public class NextOfKinPhoneIsValidTester {
    private NextOfKinPhoneIsValid rule = new NextOfKinPhoneIsValid();

    // Parts required for the test
    private DqaMessageHeader mh = new DqaMessageHeader();
    private DqaMessageReceived mr = new DqaMessageReceived();
    private DqaNextOfKin nok = new DqaNextOfKin();

    private static final Logger logger = LoggerFactory.getLogger(NextOfKinPhoneIsValidTester.class);

    /**
     * Sets up the objects needed for the test. Next of kin and patient need the same address.
     */
    @Before
    public void setUpTheObjects() {
        DqaPhoneNumber phone = new DqaPhoneNumber("810", "9573567");
        nok.setPhone(phone);
        setNextOfKin();

        mh.setMessageDate(new Date());
        mr.setMessageHeader(mh);
    }

    /**
     * Test the basic rule with a valid phone number.
     * (should be true)
     */
    @Test
    public void testRule() {
        ValidationRuleResult r = rule.executeRule(nok, mr);
        assertTrue(r.isRulePassed());
    }

    /**
     * Test with missing area code.
     * (should be false)
     */
    @Test
    public void testRuleNoAreaCode() {
        nok.getPhone().setAreaCode(null);
        setNextOfKin();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinPhoneNumberIsIncomplete == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with missing local number (the 7-digit part).
     */
    @Test
    public void testRuleNoLocalNumber() {
        nok.getPhone().setLocalNumber(null);
        setNextOfKin();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinPhoneNumberIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with missing phone number overall.
     */
    @Test
    public void testRuleNullPhone() {
        nok.setPhone(null);
        setNextOfKin();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinPhoneNumberIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with invalid phone number (bad format)
     */
    @Test
    public void testRuleBadFormat() {
        nok.getPhone().setAreaCode("1234");
        setNextOfKin();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinPhoneNumberIsInvalid == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with invalid phone number (bad area code)
     */
    @Test
    public void testRuleBadAreaCode() {
        nok.getPhone().setAreaCode("000");
        setNextOfKin();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinPhoneNumberIsInvalid == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with invalid phone number (bad local number)
     */
    @Test
    public void testRuleBadLocalNumber() {
        nok.getPhone().setLocalNumber("abc");
        setNextOfKin();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinPhoneNumberIsInvalid == r.getIssues().get(0).getIssue());
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
