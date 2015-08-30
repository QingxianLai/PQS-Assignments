package connectFourGame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


/**
 * The Cell JPanel Unit in the grid panel.
 *
 */
public class GridUnit extends JPanel{

  private static final long serialVersionUID = 7144770768326130949L;
  private int height = 100;
  private int wide = 100;
  private JPanel innerCircle;
  
  /**
   * The circle shape inside the spare unit cell.
   *
   */
  private class InnerCircle extends JPanel {

    private static final long serialVersionUID = -1387202822206855609L;
    private Color color;
    
    public InnerCircle(Color color) {
      super();
      this.color = color;
    }
    
    @Override
    public void paintComponent(Graphics g) {
      drawCenteredCircle(g, wide/2 - 5, height/2-10, wide/2 );
    }

    private void drawCenteredCircle(Graphics g, int x, int y, int r) {
      x = x - (r/2);
      y = y - (r/2);
      g.setColor(Color.BLACK);
      g.drawOval(x-1, y-1, r+2, r+2);
      g.setColor(color);
      g.fillOval(x, y, r, r);
    }
  }
  
  
  /**
   * constructor of grid unit
   * @param color the color of the inner circle.
   */
  public GridUnit(Color color){
    super();
    this.setLayout(new BorderLayout());
    this.setBackground(Color.CYAN);
    this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
    innerCircle = new InnerCircle(color);
    
    this.add(innerCircle, BorderLayout.CENTER);
  }
  
  
  /**
   * change the color of inner circle. 
   * @param color 
   */
  public void changeColor(Color color) {
    this.remove(innerCircle);
    this.innerCircle = new InnerCircle(color);
    this.add(innerCircle, BorderLayout.CENTER);
    this.revalidate();
    this.repaint();
  }
}
