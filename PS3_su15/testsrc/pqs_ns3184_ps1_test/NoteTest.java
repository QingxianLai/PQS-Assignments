/**
 * 
 */
package pqs_ns3184_ps1_test;

import static org.junit.Assert.*;
import nyu.pqs.ns3184.ps1.Note;
import org.junit.Test;

/**
 * @author LaiQX
 *
 */
public class NoteTest {

  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.Note#search(java.lang.String)}.
   * 
   * same bug as in ContactName and EmailAddress
   */
  @Test
  public void testSearch_match() {
    Note note = new Note("abcdef12345");
    assertTrue(note.search("abc"));
  }
  
  /**
   * Test method for {@link nyu.pqs.ns3184.ps1.Note#search(java.lang.String)}.
   * 
   * same bug as in ContactName and EmailAddress
   */
  @Test
  public void testSearch_Notmatch() {
    Note note = new Note("abcdef12345");
    assertFalse(note.search("abc123"));
  }
}
