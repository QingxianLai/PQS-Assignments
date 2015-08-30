
package connFourGameTest;

import connectFourGame.ConnFourModel;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author LaiQX
 *
 */
public class ConnFourModelLogicTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private SimuListenerForTest listener;
  private ConnFourModel model;
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    listener = new SimuListenerForTest();
    model = new ConnFourModel();
    model.addConnFourListener(listener);
    model.gameStart();    
    System.setOut(new PrintStream(outContent));
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {    
  }

  /**
   * Test method for {@link connectFourGame.ConnFourModel#choosePvPMode()}.
   */
  @Test
  public final void testChoosePvPMode() {
    model.choosePvPMode();
    assertEquals("view reset\ngame started: Player1(Yellow)\n", outContent.toString());
  }

  /**
   * Test method for {@link connectFourGame.ConnFourModel#choosePvCMode()}.
   */
  @Test
  public final void testChoosePvCMode() {
    model.choosePvCMode();
    assertEquals("view reset\ngame started: Player1(Yellow)\n", outContent.toString());
  }

  /**
   * Test method for {@link connectFourGame.ConnFourModel#dropDisc(int)}.
   */
  @Test
  public final void testDropDisc() {
    try {
      model.dropDisc(1);
    } catch (Exception e) {
      fail("DropDisc Failed.");
    }
  }
  
  /**
   * Test method for {@link connectFourGame.ConnFourModel#dropDisc(int)}.
   */
  @Test(expected=IllegalArgumentException.class)
  public final void testDropDisc_outOfRange() {
     model.dropDisc(10);
  }
  
  private String lastTwo(String raw) {
    String[] rawSep = raw.split("\n");
    return rawSep[rawSep.length-1];
  }
  
  /**********************************************************************
   *
   * Player against Player test
   *
   **********************************************************************/
  
  
  @Test
  public final void testLogic_PvP_player1win_Vertical() {
    model.choosePvPMode();
    model.dropDisc(1);
    model.dropDisc(2);
    model.dropDisc(1);
    model.dropDisc(2);
    model.dropDisc(1);
    model.dropDisc(2);
    model.dropDisc(1);
    String result = lastTwo(outContent.toString());
    assertEquals("Player1(Yellow) win", result);
  }
  
  @Test
  public final void testLogic_PvP_player1win_Horizontal() {
    model.choosePvPMode();
    model.dropDisc(1);
    model.dropDisc(1);
    model.dropDisc(2);
    model.dropDisc(2);
    model.dropDisc(3);
    model.dropDisc(3);
    model.dropDisc(4);
    String result = lastTwo(outContent.toString());
    assertEquals("Player1(Yellow) win", result);
  }
  
  
  @Test
  public final void testLogic_PvP_player1win_RightDiagenal() {
    model.choosePvPMode();
    model.dropDisc(0);
    model.dropDisc(1);
    model.dropDisc(1);
    model.dropDisc(2);
    model.dropDisc(3);
    model.dropDisc(2);
    model.dropDisc(3);
    model.dropDisc(2);
    model.dropDisc(4);
    model.dropDisc(2);
    String result = lastTwo(outContent.toString());
    assertEquals("Player2(Red) win", result);
  }
  
  
  @Test
  public final void testLogic_PvP_player1win_LeftDiagenal() {
    model.choosePvPMode();
    model.dropDisc(6);
    model.dropDisc(5);
    model.dropDisc(5);
    model.dropDisc(4);
    model.dropDisc(3);
    model.dropDisc(4);
    model.dropDisc(3);
    model.dropDisc(4);
    model.dropDisc(2);
    model.dropDisc(4);
    String result = lastTwo(outContent.toString());
    assertEquals("Player2(Red) win", result);
  }
  
  
  @Test
  public final void testLogic_PvP_player2win() {
    model.choosePvPMode();
    model.dropDisc(1);
    model.dropDisc(2);
    model.dropDisc(1);
    model.dropDisc(2);
    model.dropDisc(1);
    model.dropDisc(2);
    model.dropDisc(4);
    model.dropDisc(2);
    String result = lastTwo(outContent.toString());
    assertEquals("Player2(Red) win", result);
  }
  
  
  @Test
  public final void testLogic_PvP_columnFull() {
    model.choosePvPMode();
    model.dropDisc(1);
    model.dropDisc(1);
    model.dropDisc(1);
    model.dropDisc(1);
    model.dropDisc(1);
    model.dropDisc(1);
    model.dropDisc(1);
    String result = lastTwo(outContent.toString());
    assertEquals("This column is full, please try another one.", result);
  }
  
  
  @Test
  public final void testLogic_PvP_draw() {
    model.choosePvPMode();
    model.dropDisc(1);
    model.dropDisc(2);
    model.dropDisc(1);
    model.dropDisc(2);
    model.dropDisc(1);
    model.dropDisc(2);
    model.dropDisc(2);
    model.dropDisc(1);
    model.dropDisc(2);
    model.dropDisc(1);
    model.dropDisc(2);
    model.dropDisc(1);
    
    model.dropDisc(3);
    model.dropDisc(4);
    model.dropDisc(3);
    model.dropDisc(4);
    model.dropDisc(3);
    model.dropDisc(4);
    model.dropDisc(4);
    model.dropDisc(3);
    model.dropDisc(4);
    model.dropDisc(3);
    model.dropDisc(4);
    model.dropDisc(3);

    model.dropDisc(5);
    model.dropDisc(6);
    model.dropDisc(5);
    model.dropDisc(6);
    model.dropDisc(5);
    model.dropDisc(6);
    model.dropDisc(6);
    model.dropDisc(5);
    model.dropDisc(6);
    model.dropDisc(5);
    model.dropDisc(6);
    model.dropDisc(5);
    
    model.dropDisc(0);
    model.dropDisc(0);
    model.dropDisc(0);
    model.dropDisc(0);
    model.dropDisc(0);
    model.dropDisc(0);
    String result = lastTwo(outContent.toString());
    assertEquals("draw", result);
  }
    

}
