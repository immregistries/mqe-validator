package org.immregistries.mqe.validator.report.codes;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.immregistries.codebase.client.reference.CodesetType;
import org.immregistries.mqe.validator.TestMessageGenerator;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeCollectionTester {

  private static final Logger logger = LoggerFactory.getLogger(CodeCollectionTester.class);

  TestMessageGenerator tmg = new TestMessageGenerator();

  @Test
  public void test() {
    MqeMessageReceived message = tmg.getMsg1();
    CodeCollection cc = new CodeCollection(message);
    System.out.println(cc);
  }

  @Test
  public void testMerging() {
    MqeMessageReceived msg1 = tmg.getMsg1();
    MqeMessageReceived msg2 = tmg.getMsg2();
    MqeMessageReceived msg3 = tmg.getMsg3();
    MqeMessageReceived msg4 = tmg.getMsg4();

    CodeCollection cc1 = new CodeCollection(msg1);
    //assert some stuff
    CodeCollection cc3 = new CodeCollection(msg2);
    //assert some stuff
    CodeCollection cc2 = new CodeCollection(msg3);
    //assert some stuff
    CodeCollection cc4 = new CodeCollection(msg4);
    //assert some stuff
    CodeCollection ccAll = cc1.combine(cc2).combine(cc3).combine(cc4);

    CodeCollection ccDouble = ccAll.combine(ccAll);

    CodeCollection ccQuad = ccDouble.combine(ccDouble);

    logger.warn("test merging");

    reportCounts(ccQuad);
  }

  private void reportCounts(CodeCollection codeCollection) {
    for (CollectionBucket cb : codeCollection.getCodeCountList()) {
      logger.warn("Bucket: " + cb);
    }
  }

  @Test
  public void testCollecting() {
    MqeMessageReceived msg = tmg.getMsg1();
    CodeCollection cc = new CodeCollection(msg);
    logger.warn("test collecting");
    reportCounts(cc);

    List<CollectionBucket> groupCounts = cc.getByType(CodesetType.VACCINE_GROUP);
    assertEquals("Should be zero entries, we aren't collecting this", 0, groupCounts.size());

    msg = tmg.getMsg2();
    cc = new CodeCollection(msg);
    groupCounts = cc.getByType(CodesetType.VACCINE_GROUP);
    assertEquals("Should be zero entries, we aren't collecting this", 0, groupCounts.size());

    msg = tmg.getMsg3();
    cc = new CodeCollection(msg);
    groupCounts = cc.getByType(CodesetType.VACCINE_GROUP);
    assertEquals("Should be zero entries, we aren't collecting this", 0, groupCounts.size());
  }

}
