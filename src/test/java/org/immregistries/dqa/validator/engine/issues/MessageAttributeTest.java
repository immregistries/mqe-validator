package org.immregistries.dqa.validator.engine.issues;

import static org.junit.Assert.assertFalse;

import java.util.HashSet;
import java.util.Set;

import org.immregistries.dqa.validator.detection.Detection;
import org.junit.Test;

public class MessageAttributeTest {

  @Test
  public void test() {
    Set<String> set = new HashSet<>();
    boolean foundAtLeastOneDuplicated = false;
    for (Detection ma : Detection.values()) {
      if (set.contains(ma.getDqaErrorCode())) {
        System.err.println("Duplicate DQA code: " + ma.getDqaErrorCode());
        foundAtLeastOneDuplicated = true;
      }
      set.add(ma.getDqaErrorCode());
    }
    assertFalse(
        "At least one DQA code duplicate was found in the MessageAttribute enum. DQA code must be unique. ",
        foundAtLeastOneDuplicated);
  }
}
