package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.vxu.DqaMessageHeader;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Allison on 5/3/2017.
 */
public class PatientDeathDateIsValidTester {
    private PatientDeathDateIsValid rule = new PatientDeathDateIsValid();

    // Parts required for the test
    private DqaMessageHeader mh = new DqaMessageHeader();
    private DqaMessageReceived mr = new DqaMessageReceived();
    private DqaPatient p = new DqaPatient();

    private static final Logger logger = LoggerFactory.getLogger(PatientDeathDateIsValidTester.class);
    private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

    /**
     * Sets up the objects with valid information.
     */
    @Before
    public void setUpTheObjects() {
        String yesterday = format.format(addDays(new Date(), -1));
        p.setBirthDateString(yesterday);
        p.setDeathDateString(yesterday);
        p.setDeathIndicator("Y");

        mh.setMessageDate(new Date());
        mr.setMessageHeader(mh);
        mr.setPatient(p);
    }

    /**
     * Test the basic rule with a valid death date.
     * (should be true)
     */
    @Test
    public void testRule() {
        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(r.isRulePassed());
    }

    /**
     * Test a null death date.
     */
    @Test
    public void testRuleNullDate() {
        p.setDeathDateString(null);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.PatientDeathDateIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test a death date before the birth date.
     * (should be false)
     */
    @Test
    public void testRuleDeathBeforeBirth() {
        // set the death date to before the birth date
        String dateString = format.format(addDays(new Date(), -2));
        p.setDeathDateString(dateString);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.PatientDeathDateIsBeforeBirth == r.getIssues().get(0).getIssue());
    }

    /**
     * Test a death date in the future.
     * (should be false)
     */
    @Test
    public void testRuleDateInFuture() {
        // set the death date to tomorrow's date
        String dateString = format.format(addDays(new Date(), 1));
        p.setDeathDateString(dateString);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.PatientDeathDateIsInFuture == r.getIssues().get(0).getIssue());
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
