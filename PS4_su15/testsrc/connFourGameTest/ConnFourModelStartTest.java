package connFourGameTest;

import connectFourGame.ConnFourModel;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConnFourModelStartTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    System.setOut(new PrintStream(outContent));
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
    System.setOut(null);
  }

  /**
   * Test method for {@link ConnFourModel#ConnFourModel()}.
   */
  @Test
  public final void testConnFourModel() {
    try {
      ConnFourModel game = new ConnFourModel();
    } catch (Exception e) {
      fail("constructor failed!");
    }
  }

  
  /**
   * Test method for {@link ConnFourModel#addConnFourListener(ConnFourListener)}.
   */
  @Test
  public final void testAddConnFourListener_successfully() {
    SimuListenerForTest listener = new SimuListenerForTest();
    ConnFourModel model = new ConnFourModel();
    try {
      model.addConnFourListener(listener);
    } catch(Exception e) {
      fail("add listener failed!");
    }
  }
  
  
  /**
   * Test method for {@link ConnFourModel#addConnFourListener(ConnFourListener)}.
   */
  @Test(expected=IllegalArgumentException.class)
  public final void testAddConnFourListener_addNull() {
    SimuListenerForTest listener = null;
    ConnFourModel model = new ConnFourModel();
    model.addConnFourListener(listener);
  }

  /**
   * Test method for {@link ConnFourModel#gameStart()}.
   */
  @Test
  public final void testGameStart() {
    SimuListenerForTest listener = new SimuListenerForTest();
    ConnFourModel model = new ConnFourModel();
    model.addConnFourListener(listener);
    model.gameStart();
    assertEquals("welcome\n\n", outContent.toString());
  }
}
