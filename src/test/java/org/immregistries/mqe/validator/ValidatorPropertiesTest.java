package org.immregistries.mqe.validator;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidatorPropertiesTest {

  private static final Logger logger = LoggerFactory.getLogger(ValidatorPropertiesTest.class);

  @Test
  public void test1() {
    //1. can we get an instance.
    ValidatorProperties props = ValidatorProperties.INSTANCE;
    assertNotNull("properties shouldn't be null", props);
    //2. let's read some stuff...
    assertNotNull("Should have a value ", props.getSsApiAuthId());
  }
}
