/**
 * 
 */
package pqs_ns3184_ps1_test;

import static org.junit.Assert.*;
import nyu.pqs.ns3184.ps1.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author LaiQX
 *
 */
public class ContactTest {
  private Contact contact;
  private ContactName name;
  private EmailAddress email;
  private Note note;
  private PhoneNumber phoneNumber;
  private PostalAddress address;
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    name = new ContactName("Steve","Foo","Bar");
    email = new EmailAddress("SteveFB@cc.com");
    note = new Note("elkdils;");
    phoneNumber = new PhoneNumber("123-456-7890");
    address = new PostalAddress("30astreet", "apt2b", "new york city", "NY", "US",
        "012345");
    contact=new Contact(name, phoneNumber, address, email, note);
  }
  
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.Contact#Contact(ContactName, PhoneNumber)}
   * Since the equals method isn't overridden, we can't test the rightness of the constructed 
   * Contact object.
   */
  @Test
  public void testSimpleConstructor() {
    try {
      Contact simpleContact = new Contact(name,phoneNumber);
    } catch  (Exception e) {
      fail("Can't construct simple cantact with only name and phone Number");
    }
  }
 
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.Contact#getName()}
   */
  @Test
  public void testGetName() {
    assertEquals(name,contact.getName());
  }
  
  /**
   * Test method for {@link nyu.pas.ns3184.ps1.Contact#getPostalAddress()}
   */
  @Test
  public void testGetPostalAddress() {
    assertEquals(address,contact.getPostalAddress());
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.Contact#getEmailId()}
   */
  @Test
  public void testGetEmailAddress() {
    assertEquals(email,contact.getEmailId());
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.Contact#getPhoneNumber()}
   */
  @Test
  public void testGetPhoneNumber() {
    assertEquals(phoneNumber,contact.getPhoneNumber());
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.Contact#getNote()}
   */
  @Test
  public void testGetNote() {
    assertEquals(note,contact.getNote());
  }
  
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.Contact#search(java.lang.String)}.
   * same bug as in ContactName and EmailAddress
   */
  @Test
  public void testSearch_name() {
    assertTrue(contact.search("steve"));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.Contact#search(java.lang.String)}.
   * same bug as in ContactName and EmailAddress
   */
  @Test
  public void testSearch_email() {
    assertTrue(contact.search("@"));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.Contact#search(java.lang.String)}.
   * same bug as in ContactName and EmailAddress
   */
  @Test
  public void testSearch_note() {
    assertTrue(contact.search("lkdi"));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.Contact#search(java.lang.String)}.
   * same bug as in ContactName and EmailAddress
   */
  @Test
  public void testSearch_phoneNumber() {
    assertTrue(contact.search("456"));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.Contact#search(java.lang.String)}.
   * same bug as in ContactName and EmailAddress
   */
  @Test
  public void testSearch_postalAddress() {
    assertTrue(contact.search("2b"));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.Contact#search(java.lang.String)}.
   * same bug as in ContactName and EmailAddress
   */
  @Test
  public void testSearch_notMatch() {
    assertFalse(contact.search("lsidk"));
  }

  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.Contact#toCSV()}.
   */
  @Test
  public void testToCSV_fullContact() {
    String csvString = contact.toCSV();
    String realString = "'Steve','Foo','Bar','30astreet','apt2b','new york city','NY',"
        + "'US','012345','123-456-7890','SteveFB@cc.com','elkdils;'\n";
    assertEquals(realString,csvString);
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.Contact#toCSV()}.
   */
  @Test
  public void testToCSV_nullContactName() {
    Contact nullNameContact=new Contact(null, phoneNumber, address, email, note);
    String csvString = nullNameContact.toCSV();
    String realString = "'','','','30astreet','apt2b','new york city','NY',"
        + "'US','012345','123-456-7890','SteveFB@cc.com','elkdils;'\n";
    assertEquals(realString,csvString);
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.Contact#toCSV()}.
   */
  @Test
  public void testToCSV_nullPhoneNumber() {
    Contact nullPhoneNumberContact=new Contact(name, null, address, email, note);
    String csvString = nullPhoneNumberContact.toCSV();
    String realString = "'Steve','Foo','Bar','30astreet','apt2b','new york city','NY',"
        + "'US','012345','','SteveFB@cc.com','elkdils;'\n";
    assertEquals(realString,csvString);
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.Contact#toCSV()}.
   */
  @Test
  public void testToCSV_nullEmail() {
    Contact nullEmailContact=new Contact(name, phoneNumber, address, null, note);
    String csvString = nullEmailContact.toCSV();
    String realString = "'Steve','Foo','Bar','30astreet','apt2b','new york city','NY',"
        + "'US','012345','123-456-7890','','elkdils;'\n";
    assertEquals(realString,csvString);
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.Contact#toCSV()}.
   */
  @Test
  public void testToCSV_nullAddress() {
    Contact nullAddressContact=new Contact(name, phoneNumber, null, email, note);
    String csvString = nullAddressContact.toCSV();
    String realString = "'Steve','Foo','Bar','','','','','','','123-456-7890',"
        + "'SteveFB@cc.com','elkdils;'\n";
    assertEquals(realString,csvString);
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.Contact#toCSV()}.
   */
  @Test
  public void testToCSV_nullNote() {
    Contact nullNoteContact=new Contact(name, phoneNumber, address, email, null);
    String csvString = nullNoteContact.toCSV();
    String realString = "'Steve','Foo','Bar','30astreet','apt2b','new york city','NY',"
        + "'US','012345','123-456-7890','SteveFB@cc.com',''\n";
    assertEquals(realString,csvString);
  }

}
