package src;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/*
 * Classe per la gestione degli input da tastiera
 */
public class Keyboard 
{
    private static final Map<Integer, Boolean> pressedKeys = new HashMap<>();

    static 
    {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(event -> 
        {
            synchronized (Keyboard.class) 
            {
                if (event.getID() == KeyEvent.KEY_PRESSED) pressedKeys.put(event.getKeyCode(), true);
                else if (event.getID() == KeyEvent.KEY_RELEASED) pressedKeys.put(event.getKeyCode(), false);
                return false;
            }
        });
    }

    //Ritorna un valore booleano se un tasto viene cliccato
    public static boolean isKeyPressed(int keyCode) { return pressedKeys.getOrDefault(keyCode, false); }
}
