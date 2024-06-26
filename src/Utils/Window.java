package src.Utils;

import javax.swing.JFrame;

/**
 * Class Window that extends JFrame and represents the main window of the game
 */
public class Window extends JFrame
{

	/**
     * Constructor class Window initializes JFrame properties, including default close operation, resizable status, and title
    */
	public Window()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Bomberman-snes");
	}
	
    /**
     * Initializes the window's layout and makes it visible
     * Packs the components, centers the window on the screen, and sets it to be visible.
    */
	public void iniWindow()
	{
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
