package connectFourGame;
import java.util.ArrayList;
import java.util.List;


/**
 * Connect Four Game Listener Factory, produce Listener instances.
 *
 */
public class ConnFourListenerFactory {
  private static final List<ConnFourListener> listeners = new ArrayList<ConnFourListener>();
  
  /**
   * generate a new listener.
   * @param listenerType the type of the listener: can be 'gui' or 'log'. Other input or null input
   *        will generate a null object.
   * @return return a new listener or null
   */
  public static ConnFourListener getConnFourListener(String listenerType) {
    ConnFourModel game = ConnFourGameSingletonFactory.getGame();
    if (listenerType == null) {
      return null;
    }
    
    if (listenerType == "gui") {
      ConnFourListener newListener = new ConnFourGUI.Builder().addModel(game).build();
      listeners.add(newListener);
      return newListener;
    }
    
    if (listenerType == "log") {
      ConnFourListener newListener = new ConnFourLogger(game);
      listeners.add(newListener);
      return newListener;
    }
    
    return null;
  }
}
