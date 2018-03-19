package org.immregistries.dqa.validator.report.codes;

import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.validator.TestMessageGenerator;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VaccineCollectionTester {
    private static final Logger logger = LoggerFactory.getLogger(VaccineCollectionTester.class);

    TestMessageGenerator tmg = new TestMessageGenerator();

    @Test
    public void test() {
        DqaMessageReceived message = tmg.getMsg1();
        VaccineCollection cc = new VaccineCollection(message);
        System.out.println(cc);
    }

    @Test
    public void testMerging() {
        DqaMessageReceived msg1 = tmg.getMsg1();
        DqaMessageReceived msg2 = tmg.getMsg2();
        DqaMessageReceived msg3 = tmg.getMsg3();
        DqaMessageReceived msg4 = tmg.getMsg4();

        VaccineCollection cc1 = new VaccineCollection(msg1);
        reportCounts(cc1, "cc1");
        assertEquals("cc1 - Should three shot type in this message total. ", 3, cc1.getCodeCountList().size());
        //assert some stuff
        VaccineCollection cc2 = new VaccineCollection(msg2);
        reportCounts(cc2, "cc2");
        assertEquals("cc2 - Should one shot type in this message total. ", 1, cc2.getCodeCountList().size());
        //assert some stuff
        VaccineCollection cc3 = new VaccineCollection(msg3);
        reportCounts(cc3, "cc3");
        //interestingly - a 998 is in this message as non-admin (historical)...  not sure what we will get out of that.
        assertEquals("cc3 - Should two shot type in this message total. ", 2, cc3.getCodeCountList().size());
        //assert some stuff
        VaccineCollection cc4 = new VaccineCollection(msg4);
        reportCounts(cc4, "cc4");
        assertEquals("cc4 - Should one shot type in this message total. ", 1, cc4.getCodeCountList().size());
        //assert some stuff
        VaccineCollection cc12 = cc1.combine(cc2);
        reportCounts(cc12, "cc12");
        assertEquals("cc12 - Combined, there 4. ", 4, cc12.getCodeCountList().size());

        VaccineCollection ccAll = cc12.combine(cc3).combine(cc4);
        reportCounts(ccAll, "ccAll");
        assertEquals("ccAll - Combined, there 7 ", 7, ccAll.getCodeCountList().size());

        VaccineCollection ccDouble = ccAll.combine(ccAll);
        reportCounts(ccDouble, "ccDouble");
        assertEquals("ccDouble - Combined, there 7 ", 7, ccDouble.getCodeCountList().size());

        VaccineCollection ccQuad = ccDouble.combine(ccDouble);
        reportCounts(ccQuad, "ccQuad");
        assertEquals("ccQuad - Combined, there 7 ", 7, ccQuad.getCodeCountList().size());

        logger.warn("got here");

        reportCounts(ccQuad);
    }

    private void reportCounts(VaccineCollection VaccineCollection, String... label) {
        reportCounts(VaccineCollection.getCodeCountList(), label);
    }

    private void reportCounts(List<VaccineBucket> list, String... label) {

        logger.warn("**************** PRINTING LIST: " + Arrays.toString(label) + "  ****************");
        for (VaccineBucket cb : list) {
            logger.warn("VBucket: " + cb);
        }
    }

    @Test
    public void testCollecting() {
        DqaMessageReceived msg = tmg.getMsg1();
        VaccineCollection cc = new VaccineCollection(msg);
        reportCounts(cc);

        List<VaccineBucket> groupCounts = cc.getByCvx("83");
        reportCounts(groupCounts);
        assertEquals("Should be one entry. There was one shot for 83 ", 1, groupCounts.size());
        for (VaccineBucket cb : groupCounts) {
            assertEquals("Should be age 4", 4, cb.getAge());
        }
        msg = tmg.getMsg2();
        cc = new VaccineCollection(msg);
        reportCounts(cc);
        assertEquals("Should be one shot in this message total. ", 1, cc.getCodeCountList().size());
        groupCounts = cc.getByCvx("83");
        assertEquals("Should be one 83 in this message total. ", 1, groupCounts.size());
        assertEquals("Should be age 2", 2, groupCounts.get(0).getAge());

//
//        msg = tmg.getMsg3();
//        cc = new VaccineCollection(msg);
//        groupCounts = cc.getByType(CodesetType.VACCINE_GROUP);
//        assertEquals("Should be one group age", 1, groupCounts.size());
    }

}
