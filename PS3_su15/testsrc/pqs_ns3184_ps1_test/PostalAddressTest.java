/**
 * 
 */
package pqs_ns3184_ps1_test;

import static org.junit.Assert.*;

import org.junit.Test;

import nyu.pqs.ns3184.ps1.PostalAddress;
/**
 * @author LaiQX
 *
 */
public class PostalAddressTest {

  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.PostalAddress#search(java.lang.String)}.
   * Same bug as in ContactName and EmailAddress
   */
  @Test
  public void testSearch_matchStreet() {
    PostalAddress address = new PostalAddress("30astreet", "apt1b", "new york city", "NY", 
        "SS", "00000");
    assertTrue(address.search("street"));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.PostalAddress#search(java.lang.String)}.
   * Same bug as in ContactName and EmailAddress
   */
  @Test
  public void testSearch_matchApt() {
    PostalAddress address = new PostalAddress("30astreet", "apt1b", "new york city", "NY", 
        "SS", "00000");
    assertTrue(address.search("1b"));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.PostalAddress#search(java.lang.String)}.
   * Same bug as in ContactName and EmailAddress
   */
  @Test
  public void testSearch_matchCity() {
    PostalAddress address = new PostalAddress("30astreet", "apt1b", "new york city", "NY", 
        "SS", "00000");
    assertTrue(address.search("new york"));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.PostalAddress#search(java.lang.String)}.
   * Same bug as in ContactName and EmailAddress
   */
  @Test
  public void testSearch_matchState() {
    PostalAddress address = new PostalAddress("30astreet", "apt1b", "new york city", "NY", 
        "SS", "00000");
    assertTrue(address.search("ny"));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.PostalAddress#search(java.lang.String)}.
   * Same bug as in ContactName and EmailAddress
   */
  @Test
  public void testSearch_matchCounty() {
    PostalAddress address = new PostalAddress("30astreet", "apt1b", "new york city", "NY", 
        "SS", "00000");
    assertTrue(address.search("ss"));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.PostalAddress#search(java.lang.String)}.
   * Same bug as in ContactName and EmailAddress
   */
  @Test
  public void testSearch_matchZipcode() {
    PostalAddress address = new PostalAddress("30astreet", "apt1b", "new york city", "NY", 
        "SS", "000120");
    assertTrue(address.search("120"));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.PostalAddress#search(java.lang.String)}.
   * Same bug as in ContactName and EmailAddress
   */
  @Test
  public void testSearch_notMatch() {
    PostalAddress address = new PostalAddress("30astreet", "apt1b", "new york city", "NY", 
        "SS", "00000");
    assertFalse(address.search("not"));
  }

}
