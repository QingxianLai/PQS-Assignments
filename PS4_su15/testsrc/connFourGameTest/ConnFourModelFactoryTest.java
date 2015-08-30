package connFourGameTest;

import connectFourGame.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ConnFourModelFactoryTest {

  @Test
  public final void testGetGame_equal() {
    ConnFourModel game1 = ConnFourGameSingletonFactory.getGame();
    ConnFourModel game2 = ConnFourGameSingletonFactory.getGame();
    assertEquals(game1,game2);
  }
  
  @Test
  public final void testGetGame_unequal() {
    ConnFourModel game1 = ConnFourGameSingletonFactory.getGame();
    ConnFourModel game2 = new ConnFourModel();
    assertNotEquals(game1,game2);
  }

}
