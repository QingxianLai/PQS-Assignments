package connFourGameTest;

import java.awt.Color;

import connectFourGame.ConnFourListener;

public class SimuListenerForTest implements ConnFourListener {

  @Override
  public void showWelcomeMessage() {
    System.out.println("welcome\n");
  }

  @Override
  public void gameStarted(String firstPlayer) {
    System.out.printf("game started: %s\n", firstPlayer);
  }

  @Override
  public void dropedDisc(int pos, Color color) {
    System.out.printf("drop disc (%s) to position (%s)\n", color.toString(), pos);
  }

  @Override
  public void showTextNextPlayer(String nextPlayer) {
    System.out.printf("next player: %s\n", nextPlayer);
  }

  @Override
  public void showWinPlayer(String player) {
    System.out.printf("%s win\n", player);
  }

  @Override
  public void showErrorMessage(String err) {
    System.out.print(err+"\n");
  }

  @Override
  public void isDraw() {
    System.out.print("draw\n");
  }

  @Override
  public void resetView() {
    System.out.print("view reset\n");
  }

}
