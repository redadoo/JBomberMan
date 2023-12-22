package src;

import javax.swing.JFrame;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import src.lib.Vector2;
import java.awt.Color;
/*
 * Class to manage the Panel of game
 */
public class GamePanel 
{
	Vector2 size;
	String  name;
	JPanel  panel;
	JFrame	screen;
	
	/*
	 * Costructor class GamePanel
	 */
	public GamePanel(JFrame	screen, JPanel  panel, Vector2 size, String name)
	{
		this.screen = screen;
		this.panel = panel;
		this.size = size;
		this.name = name;

		setSize(size);
		panel.setBackground(Color.black);
		screen.setResizable(false);					  			// You can't resize the game screen
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// Sets the default operation when closing the screen
		screen.setTitle(name);									// Title in the upper left -> of the window
		screen.setLocationRelativeTo(null);						// Start the window from the center of the screen
		screen.setVisible(true);
		/* screen.getContentPane().setLayout( new FlowLayout() ); */
		this.addToScreen(this.returnJPanel());

	}

	/*
	 * Add elements to the Panel
	 */
	void addToPanel(Component comp, boolean Visible)
	{
		this.panel.add(comp);
		comp.setVisible(true);
		screen.setVisible(true);
	}

	/*
	 * Add elements to the Panel
	 */
	void addToPanel(Component comp)
	{
		this.panel.add(comp);
		screen.setVisible(true);
	}

	/*
	 * Add elements to the Panel
	 */
	void addToPanel(Component comp, Vector2 pos)
	{
		this.panel.add(comp);
		screen.setVisible(true);
		comp.setLocation((int)pos.x, (int)pos.y);
	}

	/*
	 * Add elements to the Screen
	 */
	void addToScreen(Component comp)
	{
		this.screen.add(comp);
		screen.setVisible(true);
	}

	/*
	 * Set size of panel
	 */
	void setSize(Vector2 size)
	{
		this.screen.setSize((int)size.x, (int)size.y);
	}

	/*
	 * Get JPanel
	 */
	JPanel returnJPanel()
	{
		return panel;
	}

	/*
	 * Get JFrame
	 */
	JFrame returnJFrame()
	{
		return screen;
	}
}
