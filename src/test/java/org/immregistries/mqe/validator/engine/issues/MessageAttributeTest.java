package org.immregistries.mqe.validator.engine.issues;

import static org.junit.Assert.assertFalse;
import java.util.HashSet;
import java.util.Set;
import org.immregistries.mqe.validator.detection.Detection;
import org.junit.Test;

public class MessageAttributeTest {

  @Test
  public void test() {
    Set<String> set = new HashSet<>();
    boolean foundAtLeastOneDuplicated = false;
    for (Detection ma : Detection.values()) {
      if (set.contains(ma.getMqeMqeCode())) {
        System.err.println("Duplicate MQE code: " + ma.getMqeMqeCode());
        foundAtLeastOneDuplicated = true;
      }
      set.add(ma.getMqeMqeCode());
    }
    assertFalse(
        "At least one MQE code duplicate was found in the MessageAttribute enum. MQE code must be unique. ",
        foundAtLeastOneDuplicated);
  }
}
