package connectFourGame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * Graphical User Interface of the Connect Four game
 *
 */
public class ConnFourGUI implements ConnFourListener {
  
  // default confs
  private static final int NUM_COLUMN = 7;
  private static final int NUM_ROW = 6;
  private static final int DEFAULT_WIDE = 600;
  private static final int DEFAULT_HEIGHT = 650;
  
  // UI components
  private JTextArea textArea = new JTextArea();
  private JButton[] dropButtons = new JButton[NUM_COLUMN];
  private GridUnit[] grid = new GridUnit[NUM_ROW * NUM_COLUMN];
  
  private final ConnFourModel game;  
  
  
  /**
   * Class Builder, take the model as parameter:
   *
   */
  public static class Builder {
    private ConnFourModel model = null;
    
    public Builder addModel(ConnFourModel val) {
      model = val;
      return this;
    }
    
    public ConnFourGUI build() { 
      return new ConnFourGUI(this);
    }
  }
  
  
  private ConnFourGUI(Builder builder) {
    this.game = builder.model;
    game.addConnFourListener(this);
  
    // The Window Frame
    JFrame frame = new JFrame("Connect Four!");
    JMenuBar menubar = constructMenu();
    frame.setJMenuBar(menubar);
    JPanel mainPanel = constructBoard();
    frame.getContentPane().add(mainPanel);
    
    // frame confs
    frame.setSize(DEFAULT_WIDE, DEFAULT_HEIGHT);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
  

  private JPanel constructBoard() {
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    
    // Top panel is a textArea.
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    topPanel.add(textArea, BorderLayout.CENTER);
    topPanel.setPreferredSize(new Dimension(DEFAULT_WIDE, 30));
    mainPanel.add(topPanel, BorderLayout.NORTH);
    
    // Control panel consist of two sub panel: button panel and discs grid panel
    JPanel controlPanel = constructControlPanel();
    mainPanel.add(controlPanel, BorderLayout.CENTER);

    return mainPanel;
  }

  
  private JPanel constructControlPanel() {
    JPanel  controlPanel = new JPanel();
    controlPanel.setLayout(new BorderLayout());   
    
    // Button panel locate at the top of control panel and has 7 buttons.
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1,NUM_COLUMN));
    for (int i = 0; i< NUM_COLUMN; i++) {
      dropButtons[i] = new JButton("Drop");
      buttonPanel.add(dropButtons[i]);
    }
    controlPanel.add(buttonPanel,BorderLayout.NORTH);
    
    // grid panel is a 6 * 7 grid frame. 
    JPanel gridPanel = new JPanel();
    gridPanel.setLayout(new GridLayout(NUM_ROW, NUM_COLUMN));
    for (int i = 0; i< NUM_ROW * NUM_COLUMN; i++) {
      grid[i] = new GridUnit(Color.WHITE);
      gridPanel.add(grid[i]);
    }
    controlPanel.add(gridPanel, BorderLayout.CENTER);
    
    return controlPanel;
  }

  
  private JMenuBar constructMenu() {
    // Menu Bar
    JMenuBar menuBar = new JMenuBar();
    
    // create `start` menu on the menuBar.
    JMenu start = new JMenu("Start");   
    
    // create three buttons in the `start` menu.
    JMenuItem PvP = new JMenuItem("Player vs Player");
    PvP.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        unbindDropButton();
        game.choosePvPMode();
      }
    });
    
    JMenuItem PvC = new JMenuItem("Player vs Computer");
    PvC.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        unbindDropButton();
        game.choosePvCMode();        
      }
    });
    
    JMenuItem exit = new JMenuItem("Exit");
    exit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }      
    });
    
    start.add(PvP);
    start.add(PvC);
    start.add(exit);
    menuBar.add(start); 
    
    return menuBar;
  }


  private void bindDropButton() {
    for (int i= 0; i< NUM_COLUMN; i++) {
      final int button_column = i;      
      dropButtons[i].addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          game.dropDisc(button_column);
        }
      });
    }
  }
  
  
  private void unbindDropButton() {
    for (int i= 0; i< NUM_COLUMN; i++) {
       for (ActionListener al: dropButtons[i].getActionListeners()) {
         dropButtons[i].removeActionListener(al);
       }
    }
  }
  

  @Override
  public void showWelcomeMessage() {
    textArea.setText("Welcome to Connect Four, choose a mode  in start menu to start the game");
  }


  @Override
  public void gameStarted(String firstPlayer) {
    bindDropButton();
    textArea.setText("Game Started, " + firstPlayer + ", please select a column to drop a disc.");    
  }


  @Override
  public void dropedDisc(int pos, Color color) {
    grid[pos].changeColor(color);    
  }


  @Override
  public void showTextNextPlayer(String nextPlayer) {
    textArea.setText(nextPlayer + "'s turn, please select a column to drop a disc.");   
  }


  @Override
  public void showWinPlayer(String player) {
    unbindDropButton();
    textArea.setText(player + " win!, Congratulation!"); 
  }
  
  
  @Override
  public void resetView() {
    textArea.setText("");
    for (int i = 0; i < grid.length; i++) {
      grid[i].changeColor(Color.WHITE);
    }
  }


  @Override
  public void showErrorMessage(String err) {
    textArea.setText(err);
  }


  @Override
  public void isDraw() {
    unbindDropButton();
    textArea.setText("All cells are filled , no winner, game over!");
  }  
}
