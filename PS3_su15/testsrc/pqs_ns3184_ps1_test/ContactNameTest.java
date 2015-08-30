/**
 * 
 */
package pqs_ns3184_ps1_test;

import static org.junit.Assert.*;
import nyu.pqs.ns3184.ps1.ContactName;

import org.junit.Test;

/**
 * Test Class ContactName
 * 
 * @author LaiQX
 *
 */
public class ContactNameTest {
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.ContactName#getFirstName()}.
   */
  @Test
  public void testGetFirstName_hasFirstName() {
    ContactName fullName = new ContactName("Jack","Foo","Bar");
    assertEquals("Jack",fullName.getFirstName());
  }

  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.ContactName#getFirstName()}.
   */
  @Test
  public void testGetFirstName_nullFirstName() {
    ContactName nullFirstName = new ContactName(null,"Foo","Bar");
    assertNull(nullFirstName.getFirstName());
  }

  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.ContactName#getLastName()}.
   */
  @Test
  public void testGetLastName_hasLastName() {
    ContactName fullName = new ContactName("Jack","Foo","Bar");    
    assertEquals("Bar",fullName.getLastName());
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.ContactName#getLastName()}.
   */
  @Test
  public void testGetLastName_NullLastName() {
    ContactName nullLastName = new ContactName("Jack","Foo",null);    
    assertNull(nullLastName.getLastName());
  }

  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.ContactName#getMiddleName()}.
   */
  @Test
  public void testGetMiddleName_hasMiddleName() {
    ContactName fullName = new ContactName("Jack","Foo","Bar");
    assertEquals("Foo", fullName.getMiddleName());
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.ContactName#getMiddleName()}.
   */
  @Test
  public void testGetMiddleName_nullMiddleName() {
    ContactName nullMiddleName = new ContactName("Jack",null,"Bar");
    assertNull(nullMiddleName.getMiddleName());
  }

  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.ContactName#search(java.lang.String)}.
   * 
   * 
   * Found Bug here, if the searchQuery contains Capitalized Characters, this will return False,
   * Since the methods only convert the local fields to lower case.
   * Besides, when one of the fields is null, it will throw an exception since it try to use 
   * tolowercase() method to a null. 
   */
  @Test
  public void testSearch_FirstNameMatch() {
    ContactName fullName = new ContactName("Jack","Foo","Bar");
    assertTrue(fullName.search("ja"));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.ContactName#search(java.lang.String)}.
   * 
   * 
   * Found Bug here, if the searchQuery contains Capitalized Characters, this will return False,
   * Since the methods only convert the local fields to lower case.
   * Besides, when one of the fields is null, it will throw an exception since it try to use 
   * tolowercase() method to a null. 
   */
  @Test
  public void testSearch_MiddleNameMatch() {
    ContactName fullName = new ContactName("Jack","Foo","Bar");
    assertTrue(fullName.search("oo"));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.ContactName#search(java.lang.String)}.
   * 
   * 
   * Found Bug here, if the searchQuery contains Capitalized Characters, this will return False,
   * Since the methods only convert the local fields to lower case.
   * Besides, when one of the fields is null, it will throw an exception since it try to use 
   * tolowercase() method to a null. 
   */
  @Test
  public void testSearch_LastNameMatch() {
    ContactName fullName = new ContactName("Jack","Foo","Bar");
    assertTrue(fullName.search("ba"));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.ContactName#search(java.lang.String)}.
   * 
   * Found Bug here, if the search Query is null, it will throw a nullPointerException.
   */
  @Test
  public void testSearch_onlyFirstName() {
    ContactName fullName = new ContactName("Jack");
    assertTrue(fullName.search("ja"));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.ContactName#search(java.lang.String)}.
   * 
   * Found Bug here, if the search Query is null, it will throw a nullPointerException.
   */
  @Test
  public void testSearch_searchNameNotMatch() {
    ContactName fullName = new ContactName("Jack","Foo","Bar");
    assertFalse(fullName.search("ca"));
  }
}
