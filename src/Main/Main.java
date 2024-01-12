package Src.Main;

import Src.Sound.AudioPlayer;

public class Main {

	/**
     * The main method is the entry point for the game application.
     * It initializes the game window, creates a game panel, starts a thread for background music,
     * and begins the game loop on the game panel.
     * @author Giosuè
     * @param args Command-line arguments (not used in this application).
     */
	public static void main(String[] args)
	{
        // Create a game window.
		Window window = new Window();

        // Create a game panel.
		GamePanel gamePanel = new GamePanel();
		
        // Create a thread for background music and start it.
		Thread MusicThread = new Thread(new AudioPlayer("Resource/Fizzy.wav"));
	/* 	MusicThread.start();  */

		// Add the game panel to the window and initialize the window.
		window.add(gamePanel);
		window.iniWindow();
		
        // Start the game loop on the game panel.
		gamePanel.StartGameThread();
	}
}
