package connectFourGame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class ConnFourModel {
  
  private enum Players {
    P1("Player1", Color.YELLOW, "Yellow"), 
    P2("Player2", Color.RED, "Red"), 
    COM("Computer", Color.RED, "Red");
    
    private String name;
    private Color color;
    private String colorName;
    
    private Players(String name, Color color, String colorName) {
      this.name = name;
      this.color = color;
      this.colorName = colorName;
    }
    
    @Override
    public String toString(){
      return name + "(" + colorName + ")";
    }
  }
  
  
  private static final int NUM_COLUMN = 7;
  private static final int NUM_ROW = 6;
  private boolean isP1; 
  private boolean isPvP;
  private int cellLeft = NUM_ROW * NUM_COLUMN;
  private Players[][] grid;
  private final List<ConnFourListener> listeners = new LinkedList<ConnFourListener>();
  
  
  /**
   * constructor, initialize isP1 and grid.
   */
  public ConnFourModel(){
    isP1 = true;
    grid = new Players[NUM_ROW][NUM_COLUMN];
  }


  /**
   * store the listener.
   * @param listener ConnFourListener
   */
  public void addConnFourListener(ConnFourListener listener) {
    if (listener == null) {
      throw new IllegalArgumentException("null listener");
    }
    listeners.add(listener);
  }


  /**
   * open the application.
   */
  public void gameStart() {
    fireWelComeEvent();
  }


  /**
   * select the Player against Player mode and start a new game.
   */
  public void choosePvPMode() {
    isPvP = true;
    resetGame();
    fireStartEvent();
  }


  /**
   * select the Player against Computer mode and start a new game.
   */
  public void choosePvCMode() {
    isPvP = false;
    resetGame();
    fireStartEvent();
  }


  /**
   * drop a disc to selected column. if it is PvP, it will turn to the next player. 
   * If it is PvC, it will simulate the move of Computer, then turn to player.
   * @param column: the number of the column. only accept integer from 0 to 6, otherwise
   *        it will throw an IllegalArgumentException(); 
   */
  public void dropDisc(int column) {
    if (column<0 || column>6) {
      throw new IllegalArgumentException();
    }
    // game not end.
    boolean isContinuing = dropSingleDisc(column);
    if (!isPvP  && isContinuing) {
      computerMove();
    }
  }


  private void computerMove() {
    // the finally chosen column to drop the disc.
    int finalMove = -1;
 
    ArrayList<Integer> availableColumn = new ArrayList<Integer>();
    for (int i = 0; i< NUM_COLUMN; i++) {
      int pos = getNextPos(i);
      if (pos >= 0) {
        availableColumn.add(i);
      } else {
        continue;
      }
      // check if the Computer can win with this move. 
      if (checkFour(Players.COM, pos)) {
        finalMove = i;
        break;
      };
    }
    if (finalMove < 0) {
      finalMove = availableColumn.get(new Random().nextInt(availableColumn.size()));
    }
    dropSingleDisc(finalMove);
  }


  private boolean dropSingleDisc(int column) {
    
    
    assert(column>=0 && column<=6);
    Players curPlayer = currentPlayer();
    
    int nextPosition = getNextPos(column);
    if (nextPosition < 0) {
      fireErrorEvent("This column is full, please try another one.");
      return false;
    }
    
    putToPosition(nextPosition);
    
    if (cellLeft<=0) {
      if (checkFour(curPlayer, nextPosition)) {
        fireWinEvent(currentPlayer().toString());
      } else {
        fireDrawEvent();
      }
      return false;
    }
    
    if (checkFour(curPlayer, nextPosition)) {
      fireWinEvent(currentPlayer().toString());
      return false;
    } else {
      fireNextTurnEvent();
      return true;
    }
  }
  
  private void putToPosition(int pos) {
    int row = pos/NUM_COLUMN;
    int column = pos%NUM_COLUMN;
    grid[row][column] = currentPlayer();
    cellLeft--;
    fireDropEvent(pos, currentPlayer().color);
  }
  
  
  private int getNextPos(int column) {
    for (int i = NUM_ROW-1; i>=0; i--) {
      if (grid[i][column]==null) {
        int pos = NUM_COLUMN * i + column;
        return pos;
      }
    }
    return -1;
  }
  
  
  private Players currentPlayer() {
    if (isPvP) {
      return isP1? Players.P1: Players.P2;
    } else {
      return isP1? Players.P1: Players.COM;
    }
  }


  private void resetGame() {
    isP1 = true;
    grid = new Players[NUM_ROW][NUM_COLUMN];
    cellLeft = NUM_ROW * NUM_COLUMN;
    for (ConnFourListener listener: listeners) {
      listener.resetView();
    }
  }


  private void fireWelComeEvent() {
    for (ConnFourListener listener: listeners) {
      listener.showWelcomeMessage();
    }
  }


  private void fireStartEvent() {
    for (ConnFourListener listener: listeners) {
      listener.gameStarted(Players.P1.toString());
    }
  }


  private void fireNextTurnEvent() {
    isP1  = !isP1;
    for (ConnFourListener listener: listeners) {
      listener.showTextNextPlayer(currentPlayer().toString());
    }
  }

  private void fireDropEvent(int pos, Color color) {
    for (ConnFourListener listener: listeners) {
      listener.dropedDisc(pos, color);
    }
  }


  private void fireDrawEvent() {
    for (ConnFourListener listener: listeners) {
      listener.isDraw();
    }
  }

  private void fireWinEvent(String winPlayer) {
    for (ConnFourListener listener: listeners) {
      listener.showWinPlayer(currentPlayer().toString());
    }
    
  }


  private void fireErrorEvent(String err) {
    for (ConnFourListener listener: listeners) {
      listener.showErrorMessage(err);
    }
    
  }

  private boolean checkFour(Players currentPlayer, int pos) {
    int x = pos/NUM_COLUMN;
    int y = pos%NUM_COLUMN;
    
    if (horizontalCheck(currentPlayer, x, y)) {
      return true;
    }
    if (verticalCheck(currentPlayer, x, y)) {
      return true;
    }
    if (rightDiagonalCheck(currentPlayer, x, y)) {
      return true;
    }
    if (leftDiagonalCheck(currentPlayer, x, y)) {
      return true;
    }
  
    return false;
  }


  private boolean horizontalCheck(Players player, int x, int y) {
    int maxlength = 1;
    
    for (int i = 1; i<=3; i++) {
      if (y-i<0) {
        break;
      }
      if (grid[x][y-i] == player) {
        maxlength ++;
      } else {
        break;
      }
    }
    
    for (int i = 1; i<=3; i++) {
      if (y+i > 6) {
        break;
      }
      if (grid[x][y+i] == player) {
        maxlength ++;
      } else {
        break;
      }
    }
    
    return maxlength >= 4;
  }


  private boolean verticalCheck(Players player, int x, int y) {
    int maxlength = 1;
    
    for (int i = 1; i<=3; i++) {
      if (x-i<0) {
        break;
      }
      if (grid[x-i][y] == player) {
        maxlength ++;
      } else {
        break;
      }
    }
    
    for (int i = 1; i<=3; i++) {
      if (x+i > 5) {
        break;
      }
      if (grid[x+i][y] == player) {
        maxlength ++;
      } else {
        break;
      }
    }
    
    return maxlength >= 4;
  }


  private boolean rightDiagonalCheck(Players player, int x, int y) {
    int maxlength = 1;
    
    for (int i = 1; i<=3; i++) {
      if (x-i<0 || y-i<0) {
        break;
      }
      if (grid[x-i][y-i] == player) {
        maxlength ++;
      } else {
        break;
      }
    }
    
    for (int i = 1; i<=3; i++) {
      if (x+i > 5 || y+i >6) {
        break;
      }
      if (grid[x+i][y+i] == player) {
        maxlength ++;
      } else {
        break;
      }
    }
    
    return maxlength >= 4;
  }


  private boolean leftDiagonalCheck(Players player, int x, int y) {
    int maxlength = 1;
    
    for (int i = 1; i<=3; i++) {
      if (x-i < 0 || y+i > 6) {
        break;
      }
      if (grid[x-i][y+i] == player) {
        maxlength ++;
      } else {
        break;
      }
    }
    
    for (int i = 1; i<=3; i++) {
      if (x+i > 5 || y-i < 0) {
        break;
      }
      if (grid[x+i][y-i] == player) {
        maxlength ++;
      } else {
        break;
      }
    }
    
    return maxlength >= 4;
  }
  
}
