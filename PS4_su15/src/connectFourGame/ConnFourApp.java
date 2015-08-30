package connectFourGame;
/**
 * Connect Four game Application
 */
public class ConnFourApp {
  
  /**
   * start the game with a GUI and a logger
   */
  public void startApp() {
    ConnFourModel game = ConnFourGameSingletonFactory.getGame();
    ConnFourListenerFactory.getConnFourListener("gui"); 
    ConnFourListenerFactory.getConnFourListener("log");
    game.gameStart();
  }
  
  /**
   * Main method
   * @param args
   */
  public static void main(String[] args) {
    ConnFourApp app = new ConnFourApp();
    app.startApp();
  }
}