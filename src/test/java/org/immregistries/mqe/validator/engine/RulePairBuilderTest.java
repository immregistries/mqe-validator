package org.immregistries.mqe.validator.engine;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.MqeCode;
import org.immregistries.mqe.validator.engine.rules.patient.PatientIsUnderage;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings({"unchecked", "rawtypes"})
public class RulePairBuilderTest {
  RulePairBuilder rpb = RulePairBuilder.INSTANCE;

  @Test
  public void onlyFirstNameDetection() {
    List<MqeCode> codes = Arrays.asList(Detection.PatientBirthDateIsUnderage.mqeCode);
    rpb.setActiveDetections(codes);
    Set<ValidationRule> rules = rpb.getAllCurrentRules();
    Assert.assertEquals("should have three rules to find this detection",3, rules.size());
//    PatientExists, PatientBirthDateIsValid, PatientIsUnderage,
  }

  @Test
  public void getRuleClassTest() {
    Assert.assertEquals(1, rpb.getRuleClasses().size());
  }

  @Test
  public void testFilter() {
    rpb.resetRules();
    List<MqeCode> codes = Arrays.asList(Detection.PatientBirthDateIsUnderage.mqeCode);
    Set<ValidationRule> rules = rpb.filterRules(rpb.getAllCurrentRules(), codes);
    Assert.assertEquals("should have ONE rules to find this detection",1, rules.size());
    System.out.println(rules);
    Assert.assertEquals("Rule should be PatientBirthdate related", PatientIsUnderage.class, rules.stream().findFirst().get().getClass());
  }

}
