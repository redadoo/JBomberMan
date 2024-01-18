package Src.Main;

import java.io.IOException;

import Src.Sound.AudioPlayer;
import Src.Utils.Window;

/**
 * Class Main
*/
public class Main 
{

	/**
     * This is the main class that initializes the game window, creates a game panel, starts a thread for background music,
     * and begins the game loop on the game panel.
     * @author Giosuè
     * @param args Command-line arguments
	 * @throws IOException 
     */
	public static void main(String[] args) throws IOException
	{
        // Create a game window
		Window window = new Window();

        // Create a game panel
		GamePanel gamePanel = new GamePanel();

        // Create a thread for background music and start it
		Thread MusicThread = new Thread(new AudioPlayer("Resource/Music/Super-Bomberman-Level-1-_ost-snes_.wav"));
		/* MusicThread.start();  */

		// Add the game panel to the window and initialize the window
		window.add(gamePanel);
		window.iniWindow();
		
        // Start the game loop on the game panel
		gamePanel.StartGameThread();
	}
}
