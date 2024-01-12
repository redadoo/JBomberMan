package Src.Main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The KeyHandler class implements KeyListener and handles keyboard input for the game
 * It tracks the state of specific keys (W, S, A, D) and provides boolean flags to indicate key presses
 */
public class KeyHandler implements KeyListener 
{

  	//Flags indicating the state of specific keys
	public boolean upPressed,downPressed,leftPressed,rightPressed,anyKey,space,debug; 

    /**
     * Overrides the keyTyped method from KeyListener
     * @param e The KeyEvent representing the key typed event
     */
	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

    /**
     * Overrides the keyPressed method from KeyListener
     * Sets flags based on the pressed keys
     * @param e The KeyEvent representing the key pressed event
     */
	@Override
	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
		
		anyKey = true;
		if(code == KeyEvent.VK_SPACE)
			space = true;
		if(code == KeyEvent.VK_F1)
			debug = true;
		if(code == KeyEvent.VK_W)
			upPressed = true;
		if(code == KeyEvent.VK_S)
			downPressed = true;
		if(code == KeyEvent.VK_A)
			leftPressed = true;
		if(code == KeyEvent.VK_D)
			rightPressed = true;
	}

    /**
     * Overrides the keyReleased method from KeyListener
     * Resets flags when keys are released
     * @param e The KeyEvent representing the key released event
     */
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		anyKey = false;
		if(code == KeyEvent.VK_SPACE)
			space = false;
		if(code == KeyEvent.VK_F1)
			debug = false;
		if(code == KeyEvent.VK_W)
			upPressed = false;
		if(code == KeyEvent.VK_S)
			downPressed = false;
		if(code == KeyEvent.VK_A)
			leftPressed = false;
		if(code == KeyEvent.VK_D)
			rightPressed = false;
	}
	
}
