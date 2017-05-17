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

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests a whole bunch of individual codes used for the patient.
 * Ethnicity
 * Sex/Gender
 * Name type
 * Facility ID
 * Facility name
 * Primary language
 * Publicity
 * Race
 * VFC/Financial eligibility
 * Created by Allison on 5/10/2017.
 */
public class PatientCodesAreValidTester {
    private PatientCodesAreValid rule = new PatientCodesAreValid();

    // TODO: deprecated/invalid values come up as "unrecognized" instead

    /*
     * Parts required for the test:
     */
    private DqaMessageHeader mh = new DqaMessageHeader();
    private DqaMessageReceived mr = new DqaMessageReceived();
    private DqaPatient p = new DqaPatient();

    private static final Logger logger = LoggerFactory.getLogger(PatientCodesAreValidTester.class);

    /**
     * Sets up the objects with valid codes.
     */
    @Before
    public void setUpTheObjects() {
        p.setEthnicity("2186-5");               // "not Hispanic or Latino"
        p.setSexCode("M");                      // "male"
        p.setNameTypeCode("L");                 // "legal name"
        p.setFacilityIdNumber("123");           // probably not a real facility ID...
        p.setFacilityName("abc");               // DEFINITELY not a real facility name
        p.setPrimaryLanguageCode("eng");        // "English"
        p.setPublicityCode("01");               // "No reminder/recall"
        p.setRaceCode("1002-5");                // "American Indian or Alaska Native"
        p.setFinancialEligibilityCode("MIA14"); // VFC status; "Medicaid-Non VFC"

        mh.setMessageDate(new Date());          // today's date.
        mr.setMessageHeader(mh);
        mr.setPatient(p);
    }

    /**
     * Tests the basic rule with valid data.
     * (should be true)
     */
    @Test
    public void testRule() {
        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(r.isRulePassed());
    }

    // PATIENT_ETHNICITY

    /**
     * Tests a null ethnicity code.
     */
    @Test
    public void testRuleNullEthnicity() {
        p.setEthnicity(null);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientEthnicityIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Tests an unrecognized ethnicity code.
     */
    @Test
    public void testRuleUnrecognizedEthnicity() {
        p.setEthnicity("abc");

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientEthnicityIsUnrecognized == r.getIssues().get(0).getIssue());
    }

    /**
     * Tests a deprecated ethnicity code.
     */
    @Test
    public void testRuleDeprecatedEthnicity() {
        p.setEthnicity("H");    // "Hispanic"

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientEthnicityIsDeprecated == r.getIssues().get(0).getIssue());
    }

    // PATIENT_GENDER

    /**
     * Tests an unrecognized sex/gender code.
     */
    @Test
    public void testRuleUnrecognizedSexCode() {
        p.setSexCode("Q");

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientGenderIsUnrecognized == r.getIssues().get(0).getIssue());
    }

    /**
     * Tests a null sex/gender code.
     */
    @Test
    public void testRuleNullSexCode() {
        p.setSexCode(null);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientGenderIsMissing == r.getIssues().get(0).getIssue());
    }

    // PATIENT_NAME_TYPE_CODE

    /**
     * Test with the type code set to something other than "legal".
     */
    @Test
    public void testRuleNameTypeNotLegal() {
        p.setNameTypeCode("D"); // D means "display name"

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientNameTypeCodeIsNotValuedLegal == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with the type code null.
     */
    @Test
    public void testRuleNullNameType() {
        p.setNameTypeCode(null);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(2 == r.getIssues().size());
        // PatientNameTypeCodeIsMissing and PatientNameTypeCodeIsNotValuedLegal
    }

    /**
     * Test with the type code empty.
     */
    @Test
    public void testRuleEmptyNameType() {
        p.setNameTypeCode("");

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(2 == r.getIssues().size());
        // PatientNameTypeCodeIsMissing and PatientNameTypeCodeIsNotValuedLegal
    }

    /**
     * Test with the type code set to an unrecognized value.
     */
    @Test
    public void testRuleUnrecognizedNameType() {
        p.setNameTypeCode("Q");

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(2 == r.getIssues().size());
        // PatientNameTypeCodeIsUnrecognized and PatientNameTypeCodeIsNotValuedLegal
    }

    // PATIENT_PRIMARY_FACILITY_ID and PATIENT_PRIMARY_FACILITY_NAME

    /**
     * Test with null facility ID.
     * (should be false)
     */
    @Test
    public void testRuleNullFacilityId() {
        p.setFacilityIdNumber(null);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientPrimaryFacilityIdIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with null facility name.
     * (should be false)
     */
    @Test
    public void testRuleNullFacilityName() {
        p.setFacilityName(null);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientPrimaryFacilityNameIsMissing == r.getIssues().get(0).getIssue());
    }

    // PATIENT_PRIMARY_LANGUAGE

    /**
     * Test with deprecated language code.
     */
    @Test
    public void testRuleDeprecatedLanguage() {
        p.setPrimaryLanguage("Ar"); // "Arabic"; current code is "ara"

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientPrimaryLanguageIsDeprecated == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with unrecognized language code.
     */
    @Test
    public void testRuleUnrecognizedLanguage() {
        p.setPrimaryLanguage("abc");

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientPrimaryLanguageIsUnrecognized == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with missing language code.
     */
    @Test
    public void testRuleNullLanguage() {
        p.setPrimaryLanguage(null);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientPrimaryLanguageIsMissing == r.getIssues().get(0).getIssue());
    }

    // PATIENT_PUBLICITY_CODE

    /**
     * Test with unrecognized publicity code.
     */
    @Test
    public void testRuleUnrecognizedPublicity() {
        p.setPublicityCode("abc");

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientPublicityCodeIsUnrecognized == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with null publicity code.
     */
    @Test
    public void testRuleNullPublicity() {
        p.setPublicityCode(null);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientPublicityCodeIsMissing == r.getIssues().get(0).getIssue());
    }

    // PATIENT_RACE

    /**
     * Test a deprecated race code.
     */
    @Test
    public void testRuleDeprecatedRace() {
        p.setRaceCode("I"); // "American Indian or Alaska Native"; the current one is "1002-5"

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientRaceIsDeprecated == r.getIssues().get(0).getIssue());
    }

    /**
     * Test a null race code.
     */
    @Test
    public void testRuleNullRace() {
        p.setRaceCode(null);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientRaceIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test an unrecognized race code.
     */
    @Test
    public void testRuleUnrecognizedRace() {
        p.setRaceCode("abc");

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientRaceIsUnrecognized == r.getIssues().get(0).getIssue());
    }

    // PATIENT_VFC_STATUS

    /**
     * Test an invalid eligibility code.
     * (should be false)
     */
    @Test
    public void testRuleInvalidEligibility() {
        p.setFinancialEligibilityCode("V00");   // "VFC eligibility unknown"

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientVfcStatusIsInvalid == r.getIssues().get(0).getIssue());
    }

    /**
     * Test an unrecognized eligibility code.
     */
    @Test
    public void testRuleUnrecognizedEligibility() {
        p.setFinancialEligibilityCode("abc");

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientVfcStatusIsUnrecognized == r.getIssues().get(0).getIssue());
    }

    /**
     * Test a null eligibility code.
     */
    @Test
    public void testRuleNullEligibility() {
        p.setFinancialEligibilityCode(null);

        ValidationRuleResult r = rule.executeRule(p, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.PatientVfcStatusIsMissing == r.getIssues().get(0).getIssue());
    }
}
