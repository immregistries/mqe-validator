package org.immregistries.dqa.validator.engine.common;

import static org.junit.Assert.assertEquals;

import org.immregistries.dqa.vxu.DqaAddress;
import org.junit.Before;
import org.junit.Test;

public class AddressValidatorTest {

  @Before
  public void buildNewAddress() {
    DqaAddress a = new DqaAddress();
    a.setCity("Lansing");
    a.setStateCode("MI");
    a.setStreet("1221 Mechanic");
    a.setZip("48910");
    a.setCountryCode("USA");
    a.setCountyParishCode("43");
    this.address = a;
  }

  private DqaAddress address;
  private AddressValidator av = AddressValidator.INSTANCE;

  @Test
  public void testBasic() {
    System.out.println(address);
    assertEquals("it's valid!", true, av.isValid(address));

    address.setCity("");

    assertEquals("it's crappy!", false, av.isValid(address));
  }
}
