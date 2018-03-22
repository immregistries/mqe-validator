package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.TestMessageGenerator;
import org.immregistries.dqa.validator.engine.MessageValidator;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.vxu.DqaMessageHeader;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.immregistries.dqa.vxu.parse.HL7MessageParser;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Allison on 5/3/2017.
 */
public class PatientDeathDateIsValidTester {
    private PatientDeathDateIsValid rule = new PatientDeathDateIsValid();
    private HL7MessageParser parser = HL7MessageParser.INSTANCE;
    private MessageValidator validator = MessageValidator.INSTANCE;
    private TestMessageGenerator genr = new TestMessageGenerator();

    // Parts required for the test
    private DqaMessageHeader mh = new DqaMessageHeader();
    private DqaMessageReceived mr = new DqaMessageReceived();
    private DqaPatient p = new DqaPatient();

    private static final Logger logger = LoggerFactory.getLogger(PatientDeathDateIsValidTester.class);
    private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

    private final String yesterday = format.format(addDays(new Date(), -1));
    private final String twoDaysAgo = format.format(addDays(new Date(), -2));
    private final String today = format.format(new Date());

    /**
     * Sets up the objects with valid information before EACH TEST.
     */
    @Before
    public void setUpTheObjects() {
        mh.setMessageDateString(today);
        p.setBirthDateString(twoDaysAgo);
        p.setDeathDateString(yesterday);
        p.setDeathIndicator("Y");
        mr.setMessageHeader(mh);
        mr.setPatient(p);
    }

    @Test
    public void testFullMessage() {
        String message = genr.getExampleVXU_1();
        DqaMessageReceived received = parser.extractMessageFromText(message);
        ValidationRuleResult r = rule.executeRule(received.getPatient(), received);
        assertEquals("Should have one issue", 1 , r.getValidationDetections().size());
        assertEquals("Should be a specific issue. ", Detection.PatientDeathDateIsInvalid , r.getValidationDetections().get(0).getDetection());
    }

    @Test
    public void testFullMessageFullValidation() {
        String message = genr.getExampleVXU_1();
        DqaMessageReceived received = parser.extractMessageFromText(message);
        List<ValidationRuleResult> list = validator.validateMessage(received);
        boolean found = false;
        for (ValidationRuleResult result : list) {
            logger.info("rule: " + result.getRuleClass());
            if (PatientDeathDateIsValid.class.equals(result.getRuleClass())) {
                System.out.println("Should have an issue for this class");
                System.out.println("Issues: " + result.getValidationDetections());
                found = true;
                assertEquals("Should have an issue", 1, result.getValidationDetections().size());
                assertEquals("Should be a specific issue. ", Detection.PatientDeathDateIsInvalid , result.getValidationDetections().get(0).getDetection());
            }
        }
        assertTrue("Should be found!", found);
        ValidationRuleResult r = rule.executeRule(received.getPatient(), received);
        assertEquals("Should have one issue", 1 , r.getValidationDetections().size());
        assertEquals("Should be a specific issue. ", Detection.PatientDeathDateIsInvalid , r.getValidationDetections().get(0).getDetection());
    }

    /**
     * Test the basic rule with a valid death date.
     * (should be true)
     */
    @Test
    public void testRule() {
        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getValidationDetections().toString());
        assertEquals("Rule should not detect any problems", 0, r.getValidationDetections().size());
        assertEquals("Rule should pass", true, r.isRulePassed());
    }

    /**
     * Test a null death date.
     */
    @Test
    public void testRuleNullDate() {
        p.setDeathDateString(null);
        p.setDeathIndicator("Y");
        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getValidationDetections().toString());
        assertEquals("Should have one issue", 1 , r.getValidationDetections().size());
        assertEquals("Should be a specific issue. ", Detection.PatientDeathDateIsMissing , r.getValidationDetections().get(0).getDetection());
    }

    /**
     * Test a death date before the birth date.
     * (should be false)
     */
    @Test
    public void testRuleDeathBeforeBirth() {
        // set the death date to before the birth date
        p.setDeathDateString(twoDaysAgo);
        p.setBirthDateString(yesterday);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getValidationDetections().toString());
        assertEquals("should have one issue", 1, r.getValidationDetections().size());
        assertEquals(Detection.PatientDeathDateIsBeforeBirth, r.getValidationDetections().get(0).getDetection());
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
        logger.info(r.getValidationDetections().toString());
        assertEquals("should have one issue", 1, r.getValidationDetections().size());
        assertEquals(Detection.PatientDeathDateIsInFuture, r.getValidationDetections().get(0).getDetection());
    }

    /**
     * Test an invalid date.
     * (should be false)
     */
    @Test
    public void testInvalidDate() {
        // set the death date to tomorrow's date
        String dateString = "NOTADATE";
        p.setDeathDateString(dateString);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getValidationDetections().toString());
        assertEquals("should have one issue", 1, r.getValidationDetections().size());
        assertEquals("Issue should indicate an invalid death date string", Detection.PatientDeathDateIsInvalid, r.getValidationDetections().get(0).getDetection());
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
