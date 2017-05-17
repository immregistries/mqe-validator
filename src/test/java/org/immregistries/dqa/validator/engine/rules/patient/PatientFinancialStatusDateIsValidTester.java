package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.vxu.DqaMessageHeader;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Allison on 5/3/2017.
 */
public class PatientFinancialStatusDateIsValidTester {
    private PatientFinancialStatusDateIsValid rule = new PatientFinancialStatusDateIsValid();

    // Parts required for the test
    private DqaMessageHeader mh = new DqaMessageHeader();
    private DqaMessageReceived mr = new DqaMessageReceived();
    private DqaPatient p = new DqaPatient();

    private static final Logger logger = LoggerFactory.getLogger(PatientFinancialStatusDateIsValidTester.class);

    /**
     * Sets up the objects with a valid VFC effective date (yesterday's date) and a message date of today.
     */
    @Before
    public void setUpTheObjects() {
        Date yesterday = addDays(new Date(), -1);

        p.setBirthDate(yesterday);
        p.setFinancialEligibilityDate(yesterday);

        mh.setMessageDate(new Date());
        mr.setMessageHeader(mh);
        mr.setPatient(p);
    }

    /**
     * Test the basic rule with a valid eligibility date.
     * (should be true)
     */
    @Test
    public void testRule() {
        ValidationRuleResult r = rule.executeRule(p, mr);
        assertTrue(r.isRulePassed());
    }

    /**
     * Test a null eligibility date.
     */
    @Test
    public void testRuleNullDate() {
        p.setFinancialEligibilityDate(null);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientVfcEffectiveDateIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test an eligibility date before the birth date.
     * (should be false)
     */
    @Test
    public void testRuleDateBeforeBirth() {
        // set the eligibility date to the day before the birth date
        p.setFinancialEligibilityDate(addDays(p.getBirthDate(), -1));

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientVfcEffectiveDateIsBeforeBirth == r.getIssues().get(0).getIssue());
    }

    /**
     * Test a future date.
     * (should be false)
     */
    @Test
    public void testRuleFutureDate() {
        // set the eligibility date to tomorrow's date
        p.setFinancialEligibilityDate(addDays(new Date(), 1));

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientVfcEffectiveDateIsInFuture == r.getIssues().get(0).getIssue());
    }

    /**
     * Conveniently add or subtract days from a date.
     *
     * @param d         date we're currently messing with
     * @param daysToAdd number of days to add (can be positive or negative)
     * @return original date, plus or minus whatever we wanted to add to it
     */
    private Date addDays(Date d, Integer daysToAdd) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DAY_OF_YEAR, daysToAdd);
        return cal.getTime();
    }
}
