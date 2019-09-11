package org.immregistries.mqe.validator.engine.common;

import static org.junit.Assert.assertEquals;
import org.immregistries.mqe.vxu.MqeAddress;
import org.junit.Before;
import org.junit.Test;

public class AddressValidatorTest {

  @Before
  public void buildNewAddress() {
    MqeAddress a = new MqeAddress();
    a.setCity("Lansing");
    a.setStateCode("MI");
    a.setStreet("1221 Mechanic");
    a.setZip("48910");
    a.setCountryCode("USA");
    a.setCountyParishCode("43");
    this.address = a;
  }

  private MqeAddress address;
  private AddressValidator av = AddressValidator.INSTANCE;

  @Test
  public void testBasic() {
    System.out.println(address);
    assertEquals("it's valid!", true, av.isValid(address));

    address.setCity("");

    assertEquals("it's crappy!", false, av.isValid(address));
  }
}
