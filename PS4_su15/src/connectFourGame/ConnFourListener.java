package connectFourGame;
import java.awt.Color;


/**
 * Connect Four listener interface
 *
 */
public interface ConnFourListener {
  
  /**
   * Actions when application starts. 
   */
  void showWelcomeMessage();
  
  
  /**
   * Actions when game starts
   * @param firstPlayer : the name of the first Player
   * 
   */
  void gameStarted(String firstPlayer);
  
  
  /**
   * actions when a disc is dropped into the grid.
   * @param pos the position in which the disc is to be put
   * @param color the color of the disc
   * 
   */
  void dropedDisc(int pos, Color color);
  
  
  /**
   * actions when it turns to next player
   * @param nextPlayer The name of next player
   * 
   */
  void showTextNextPlayer(String nextPlayer);
  
  
  /**
   * actions when one of the players wins the game.
   * @param player The name of the player who win the game.
   * 
   */
  void showWinPlayer(String player);
  
  
  /**
   * Actions when receives an error message
   * @param err The error message.
   * 
   */
  void showErrorMessage(String err);
  
  
  /**
   * Actions when it is a draw game.
   */
  void isDraw();
  
  
  /**
   * reset the listener view. 
   */
  void resetView();
}
