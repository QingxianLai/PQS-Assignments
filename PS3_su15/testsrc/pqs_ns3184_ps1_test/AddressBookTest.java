/**
 * 
 */
package pqs_ns3184_ps1_test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import nyu.pqs.ns3184.ps1.*;
/**
 * @author LaiQX
 *
 */
public class AddressBookTest {
  private AddressBook addressBook;
  private static ContactName name1;
  private static PhoneNumber phoneNumber1;
  private static Contact contact1;
  private static ContactName name2;
  private static PhoneNumber phoneNumber2;
  private static EmailAddress email2;
  private static Note note2;
  private static PostalAddress address2;
  private static Contact contact2;
  
  
  /**
   * @throws java.lang.Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    name1 = new ContactName("Jack","Foo","Bar");
    phoneNumber1 = new PhoneNumber("111-111-1111");
    contact1 = new Contact(name1,phoneNumber1);
    
    name2 = new ContactName("Steve","Un", "Owen");
    phoneNumber2 = new PhoneNumber("233-333-2333");
    email2 = new EmailAddress("steveUO@gmail.com");
    note2 = new Note("how do you turn this on!");
    address2 = new PostalAddress("31astreet", "apt2b", "new york city", "NY", "USA", "00120");
    contact2 = new Contact(name2, phoneNumber2, address2, email2, note2);
  }

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    addressBook = new AddressBook();
  }

  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.AddressBook#AddContact(nyu.pqs.ns3184.ps1.Contact)}.
   */
  @Test
  public void testAddContact_addNullContact() {
    assertFalse(addressBook.AddContact(null));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.AddressBook#AddContact(nyu.pqs.ns3184.ps1.Contact)}.
   */
  @Test
  public void testAddContact_addSimpleContact() {
    assertTrue(addressBook.AddContact(contact1));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.AddressBook#AddContact(nyu.pqs.ns3184.ps1.Contact)}.
   */
  @Test
  public void testAddContact_addFullContact() {
    assertTrue(addressBook.AddContact(contact2));
  }

  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.AddressBook#removeContact(nyu.pqs.ns3184.ps1.Contact)}.
   */
  @Test
  public void testRemoveContact_removeNull() {
    addressBook.AddContact(contact1);
    assertFalse(addressBook.removeContact(null));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.AddressBook#removeContact(nyu.pqs.ns3184.ps1.Contact)}.
   */
  @Test
  public void testRemoveContact_removeContact() {
    addressBook.AddContact(contact1);
    assertTrue(addressBook.removeContact(contact1));
  }

  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.AddressBook#save(java.lang.String)}.
   * Found a bug here: private variable file cannot be set, so this method will always throws a 
   * NullpointerException. 
   * 
   * @throws IOException 
   */
  @Ignore
  @Test
  public void testSave() throws IOException {
     fail("This is a Buggy method, can't run successfully");
  }

  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.AddressBook#read(java.lang.String)}.
   * @throws IOException 
   */
  @Test
  public void testRead_emptyPath() throws IOException {
    assertFalse(addressBook.read(""));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.AddressBook#read(java.lang.String)}.
   * Bug Bug Bug, can't read files that contains null fields.
   * @throws IOException 
   */
  @Test
  public void testRead_ExistPath() throws IOException {
    String path = System.getProperty("user.dir") + "/savefile";
    PrintWriter writer = new PrintWriter(path, "UTF-8");
    writer.print(contact2.toCSV());
    writer.close();
    assertTrue(addressBook.read(path));
    File file = new File(path);
    file.delete();
  }

  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.AddressBook#search(java.lang.String)}.
   * Bug here: not equal method, can't verify the search result.
   */
  @Test
  public void testSearch_match() {
    addressBook.AddContact(contact2);
    List<Contact> list = new ArrayList<Contact>();
    list.add(contact2);
    assertArrayEquals(list.toArray(),addressBook.search("steve").toArray());
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.AddressBook#search(java.lang.String)}.
   */
  @Test
  public void testSearch_notMatch() {
    addressBook.AddContact(contact2);
    List<Contact> list = new ArrayList<Contact>();
    list.add(contact2);
    assertFalse(list.toArray()==addressBook.search("saeve").toArray());
  }

}
