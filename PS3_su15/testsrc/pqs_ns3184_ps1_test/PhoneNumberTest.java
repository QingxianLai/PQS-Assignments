/**
 * 
 */
package pqs_ns3184_ps1_test;

import static org.junit.Assert.*;
import nyu.pqs.ns3184.ps1.PhoneNumber;
import org.junit.Test;

/**
 * @author LaiQX
 *
 */
public class PhoneNumberTest {

  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.PhoneNumber#getPhoneNumber()}.
   */
  @Test
  public void testGetPhoneNumber() {
    PhoneNumber phoneNumber = new PhoneNumber("223-322-2354");
    assertEquals("223-322-2354", phoneNumber.getPhoneNumber());
  }

  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.PhoneNumber#search(java.lang.String)}.
   */
  @Test
  public void testSearch_match() {
    PhoneNumber phoneNumber = new PhoneNumber("223-322-2354");
    assertTrue(phoneNumber.search("322"));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.PhoneNumber#search(java.lang.String)}.
   */
  @Test
  public void testSearch_notMatch() {
    PhoneNumber phoneNumber = new PhoneNumber("223-322-2354");
    assertFalse(phoneNumber.search("222"));
  }

}
