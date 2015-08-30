package connectFourGame;

/**
 * Singleton Factory of the Connect Four Game Model.
 * @author LaiQX
 *
 */
public class ConnFourGameSingletonFactory {
  private static ConnFourModel game;
  
  private ConnFourGameSingletonFactory() {
    game = new ConnFourModel();
  }
  
  /**
   * always return the same model instance.
   * @return the game model
   */
  public static ConnFourModel getGame() {
    if (game == null) {
      new ConnFourGameSingletonFactory();
    }
    return game;
  }
}
