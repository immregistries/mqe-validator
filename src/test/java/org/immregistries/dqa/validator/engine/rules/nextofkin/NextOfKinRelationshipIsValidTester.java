package org.immregistries.dqa.validator.engine.rules.nextofkin;

import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
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
 * Created by Allison on 5/9/2017.
 */
public class NextOfKinRelationshipIsValidTester {
    private NextOfKinRelationshipIsValidForUnderagedPatient rule = new NextOfKinRelationshipIsValidForUnderagedPatient();

    // Parts required for the test
    private DqaMessageHeader mh = new DqaMessageHeader();
    private DqaMessageReceived mr = new DqaMessageReceived();
    private DqaNextOfKin nok = new DqaNextOfKin();

    private static final Logger logger = LoggerFactory.getLogger(NextOfKinRelationshipIsValidTester.class);

    /**
     * Sets up the objects needed for the test. Next of kin and patient need the same address.
     */
    @Before
    public void setUpTheObjects() {
        nok.setRelationshipCode("MTH"); // MTH = mother
        setNextOfKin();

        mh.setMessageDate(new Date());
        mr.setMessageHeader(mh);
    }

    /**
     * Test the basic rule with a valid and expected relationship.
     * (should be true)
     */
    @Test
    public void testRule() {
        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(r.isRulePassed());
    }

    /**
     * Test with a null relationship code.
     */
    @Test
    public void testRuleNullRelationship() {
        nok.setRelationshipCode(null);
        setNextOfKin();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinRelationshipIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with an empty relationship code.
     */
    @Test
    public void testRuleEmptyRelationship() {
        nok.setRelationshipCode("");
        setNextOfKin();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinRelationshipIsMissing == r.getIssues().get(0).getIssue());
    }

    /**
     * Test where next of kin is not responsible party.
     */
    @Test
    public void testRuleNotResponsibleParty() {
        nok.setRelationshipCode("BRO"); // siblings don't count as responsible parties
        setNextOfKin();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinRelationshipIsNotResponsibleParty == r.getIssues().get(0).getIssue());
    }

    /**
     * Test where next of kin is the child.
     */
    @Test
    public void testRuleIsChild() {
        nok.setRelationshipCode("CHD");
        setNextOfKin();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinRelationshipIsUnexpected == r.getIssues().get(0).getIssue());
    }

    /**
     * Test with an unrecognized relationship code.
     */
    @Test
    public void testRuleUnrecognizedRelationship() {
        nok.setRelationshipCode("abc");
        setNextOfKin();

        ValidationRuleResult r = rule.executeRule(nok, mr);
        logger.info(r.getIssues().toString());
        assertTrue(1 == r.getIssues().size()
                && Detection.NextOfKinRelationshipIsUnrecognized == r.getIssues().get(0).getIssue());
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
