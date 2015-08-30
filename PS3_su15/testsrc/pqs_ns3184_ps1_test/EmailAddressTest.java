/**
 * 
 */
package pqs_ns3184_ps1_test;

import static org.junit.Assert.*;
import nyu.pqs.ns3184.ps1.EmailAddress;

import org.junit.Test;

/**
 * @author LaiQX
 *
 */
public class EmailAddressTest {

  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.EmailAddress#getEmailaddress()}.
   */
  @Test
  public void testGetEmailaddress() {
    EmailAddress email = new EmailAddress("Foo@Bar.cc");
    assertEquals("Foo@Bar.cc", email.getEmailaddress());
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.EmailAddress#getEmailaddress()}.
   */
  @Test
  public void testGetEmailaddress_EmptyAddress() {
    EmailAddress email = new EmailAddress("");
    assertEquals("", email.getEmailaddress());
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.EmailAddress#getEmailaddress()}.
   */
  @Test
  public void testGetEmailaddress_nullAddress() {
    EmailAddress email = new EmailAddress(null);
    assertNull(email.getEmailaddress());
  }

  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.EmailAddress#search(java.lang.String)}.
   * 
   * Found Bug here: cannot match queries having capitalized characters.And the searchquery and 
   * instance variable cannot be null, otherwise it will throw an Exception.
   */
  @Test
  public void testSearch() {
    EmailAddress email = new EmailAddress("Foo@Bar.cc");
    assertTrue(email.search("foo@"));
  }

}
