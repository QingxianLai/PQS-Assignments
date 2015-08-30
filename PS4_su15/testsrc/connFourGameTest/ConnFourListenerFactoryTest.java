package connFourGameTest;

import connectFourGame.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ConnFourListenerFactoryTest {

  @Test
  public final void testGetConnFourListener_guiInstance() {
    Object listener = ConnFourListenerFactory.getConnFourListener("gui");
    assertTrue(listener instanceof ConnFourGUI);
  }
  
  @Test
  public final void testGetConnFourListener_loggerInstance() {
    Object listener = ConnFourListenerFactory.getConnFourListener("log");
    assertTrue(listener instanceof ConnFourLogger);
  }
  
  @Test
  public final void testGetConnFourListener_wrongInput() {
    Object listener = ConnFourListenerFactory.getConnFourListener("logg");
    assertNull(listener);
  }
  
  @Test
  public final void testGetConnFourListener_nullInput() {
    Object listener = ConnFourListenerFactory.getConnFourListener(null);
    assertNull(listener);
  }

}
