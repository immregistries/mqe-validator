package org.immregistries.dqa.validator.report.codes;

import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.validator.TestMessageGenerator;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CodeCollectionTester {
    private static final Logger logger = LoggerFactory.getLogger(CodeCollectionTester.class);

    TestMessageGenerator tmg = new TestMessageGenerator();

    @Test
    public void test() {
        DqaMessageReceived message = tmg.getMsg1();
        CodeCollection cc = new CodeCollection(message);
        System.out.println(cc);
    }

    @Test
    public void testMerging() {
        DqaMessageReceived msg1 = tmg.getMsg1();
        DqaMessageReceived msg2 = tmg.getMsg2();
        DqaMessageReceived msg3 = tmg.getMsg3();
        DqaMessageReceived msg4 = tmg.getMsg4();

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

        logger.warn("got here");

        reportCounts(ccQuad);
    }

    private void reportCounts(CodeCollection codeCollection) {
        for (CollectionBucket key : codeCollection.getVaccineCodeCounts().keySet()) {
            Map<String, Integer> counts = codeCollection.getVaccineCodeCounts().get(key);
            logger.warn(key.getLabel() + " : " + counts);
        }
    }

    private void reportCounts(CodeCollection codeCollection, CodesetType reportThis) {
        for (CollectionBucket key : codeCollection.getVaccineCodeCounts().keySet()) {
            if (key.getType() == reportThis) {
                Map<String, Integer> counts = codeCollection.getVaccineCodeCounts().get(key);
                logger.warn(key.getLabel() + " : " + counts);
            }
        }
    }

    @Test
    public void testCollecting() {
        DqaMessageReceived msg = tmg.getMsg1();
        CodeCollection cc = new CodeCollection(msg);
        Map<CollectionBucket, Map<String, Integer>> groupCounts = cc.getByType(CodesetType.VACCINE_GROUP);
        assertEquals("Should one group and age", 1, groupCounts.size());
        reportCounts(cc, CodesetType.VACCINE_GROUP);

        for (CollectionBucket cb : groupCounts.keySet()) {
            assertEquals("Should be age 4", "4", cb.getCollectionMetadata());
            Map<String, Integer> groupMap = groupCounts.get(cb);
            assertEquals("Should be three groups represented", 3, groupMap.size());
            for (String group : groupMap.keySet()) {
                Integer i = groupMap.get(group);
                assertEquals("Should only be one group represented for the three groups", new Integer(1), i);
            }
        }

        msg = tmg.getMsg2();
        cc = new CodeCollection(msg);
        groupCounts = cc.getByType(CodesetType.VACCINE_GROUP);
        assertEquals("Should be one group age", 1, groupCounts.size());
        reportCounts(cc, CodesetType.VACCINE_GROUP);

        msg = tmg.getMsg3();
        cc = new CodeCollection(msg);
        groupCounts = cc.getByType(CodesetType.VACCINE_GROUP);
        assertEquals("Should be one group age", 1, groupCounts.size());
        reportCounts(cc, CodesetType.VACCINE_GROUP);
    }

}
