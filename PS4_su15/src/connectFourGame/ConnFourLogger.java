package connectFourGame;
import java.awt.Color;


/**
 * An Logger Listener of the Connect Four Game.
 *
 */
public class ConnFourLogger implements ConnFourListener {
  
  /**
   * constructor of the logger.
   * @param game the connect four model.
   */
  public ConnFourLogger(ConnFourModel game) {
    game.addConnFourListener(this);
  }
  
  
  @Override
  public void gameStarted(String firstPlayer) {
    System.out.println("game start!");
  }

  
  @Override
  public void dropedDisc(int pos, Color color) {
    System.out.println("drop dics in "+ pos + " of color: " + color.toString());
    
  }

  
  @Override
  public void showTextNextPlayer(String nextPlayer) {
     System.out.println(nextPlayer + "'s turn.");
  }

  
  @Override
  public void showWinPlayer(String player) {
    System.out.println(player + " wins!");
  }

  
  @Override
  public void showErrorMessage(String err) {
    System.out.println("Error: " + err);
  }
  

  @Override
  public void resetView() {
    System.out.println("View reset");
  }
  
  
  @Override
  public void isDraw() {
    System.out.println("All cells are filled , no winner, game over!");
  }

  
  @Override
  public void showWelcomeMessage() {
    System.out.println("welcome!");
  }
}
