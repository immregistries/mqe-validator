package org.immregistries.mqe.validator.engine.rules.nextofkin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Allison on 5/9/2017.
 */
public class NextOfKinRelationshipIsValidTester {

  private NextOfKinRelationshipIsValidForUnderagedPatient rule = new NextOfKinRelationshipIsValidForUnderagedPatient();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeNextOfKin nok = new MqeNextOfKin();

  private static final Logger logger = LoggerFactory
      .getLogger(NextOfKinRelationshipIsValidTester.class);

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
    logger.info(r.getValidationDetections().toString());
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
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.NextOfKinRelationshipIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test with an empty relationship code.
   */
  @Test
  public void testRuleEmptyRelationship() {
    nok.setRelationshipCode("");
    setNextOfKin();

    ValidationRuleResult r = rule.executeRule(nok, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.NextOfKinRelationshipIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test where next of kin is not responsible party.
   */
  @Test
  public void testRuleNotResponsibleParty() {
    nok.setRelationshipCode("BRO"); // siblings don't count as responsible parties
    setNextOfKin();

    ValidationRuleResult r = rule.executeRule(nok, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.NextOfKinRelationshipIsNotResponsibleParty,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test where next of kin is the child.
   */
  @Test
  public void testRuleIsChild() {
    nok.setRelationshipCode("CHD");
    setNextOfKin();

    ValidationRuleResult r = rule.executeRule(nok, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.NextOfKinRelationshipIsUnexpected,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test with an unrecognized relationship code.
   */
  @Test
  public void testRuleUnrecognizedRelationship() {
    nok.setRelationshipCode("abc");
    setNextOfKin();

    ValidationRuleResult r = rule.executeRule(nok, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.NextOfKinRelationshipIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Set the next-of-kin in the message.
   */
  private void setNextOfKin() {
    List<MqeNextOfKin> noks = new ArrayList<>();
    noks.add(nok);
    mr.setNextOfKins(noks);
  }
}
