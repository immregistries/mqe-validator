package org.immregistries.dqa.validator.engine.rules.nextofkin;

import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.vxu.DqaMessageHeader;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Allison on 5/4/2017.
 */
public class NextOfKinNameIsValidTester {
    private NextOfKinNameIsValid rule = new NextOfKinNameIsValid();

    // Parts required for the test
    private DqaMessageHeader mh = new DqaMessageHeader();
    private DqaMessageReceived mr = new DqaMessageReceived();
    private DqaNextOfKin nok = new DqaNextOfKin();

    private static final Logger logger = LoggerFactory.getLogger(NextOfKinNameIsValidTester.class);

    /**
     * Sets up the objects needed for the test. Next of kin and patient need the same address.
     */
    @Before
    public void setUpTheObjects() {
        nok.setNameFirst("Lois");
        nok.setNameLast("Lane");
        setNextOfKin();

        mh.setMessageDate(new Date());
        mr.setMessageHeader(mh);
    }

    /**
     * Test the basic rule with a valid first and last name.
     * (should be true)
     */
    @Test
    public void testRule() {
        ValidationRuleResult r = rule.executeRule(nok, mr);
        assertTrue(r.isRulePassed());
    }

    /**
     * Test with a missing first name.
     * (should be false)
     */
    @Test
    public void testRuleNoFirstName() {
        nok.setNameFirst(null);
        setNextOfKin();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.NextOfKinNameFirstIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with a missing last name.
     * (should be false)
     */
    @Test
    public void testRuleNoLastName() {
        nok.setNameLast(null);
        setNextOfKin();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.NextOfKinNameLastIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with both parts of the name missing.
     * (should be false)
     */
    @Test
    public void testRuleNoName() {
        nok.setNameFirst(null);
        nok.setNameLast(null);
        setNextOfKin();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && MessageAttribute.NextOfKinNameIsMissing == r.getIssues().get(0).getIssue());
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
