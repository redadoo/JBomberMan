package src;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/*
 * Class to manage interaction Keyboards
 */
public class Keyboard 
{
    private static final Map<Integer, Boolean> pressedKeys = new HashMap<>();
    private static final Map<Integer, Boolean> justReleasedKeys = new HashMap<>();


    static {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(event -> {
            synchronized (Keyboard.class) {
                if (event.getID() == KeyEvent.KEY_PRESSED) {
                    pressedKeys.put(event.getKeyCode(), true);
                    justReleasedKeys.put(event.getKeyCode(), false);
                } else if (event.getID() == KeyEvent.KEY_RELEASED) {
                    pressedKeys.put(event.getKeyCode(), false);
                    justReleasedKeys.put(event.getKeyCode(), true);
                }
                return false;
            }
        });
    }

    /*
     *  Returns a boolean value if a key is clicked 
     */
    public static boolean isKeyPressed(int keyCode) { return pressedKeys.getOrDefault(keyCode, false); }
    public static boolean isKeyJustReleased(int keyCode) {
        boolean justReleased = justReleasedKeys.getOrDefault(keyCode, false);
        if (justReleased) {
            // Reset the state after checking
            justReleasedKeys.put(keyCode, false);
        }
        return justReleased;
    }

}
