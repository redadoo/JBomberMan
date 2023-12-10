package src;

import javax.swing.JFrame;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.awt.Graphics2D;
/*
 * Classe per la gestione del pannello di gioco
 */
public class GamePanel 
{
	Vector2 size;
	String  name;
	JPanel  panel;
	JFrame	screen;
	
	public GamePanel(JFrame	screen, JPanel  panel, Vector2 size, String name)
	{
		this.screen = screen;
		this.panel = panel;
		this.size = size;
		this.name = name;

		setSize(size);
		panel.setBackground(Color.black);
		screen.setResizable(false);					  //Non si può ridimensionare la schermata di gioco
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	  // Imposta l'operazione di default quando si chiude la schermata
		screen.setTitle(name); 					              	  //Titolo in alto a sinistra -> della window
		screen.setLocationRelativeTo(null); 			          //Far partire la window dal centro dello schermo
		screen.setVisible(true);
		this.addToScreen(this.returnJPanel());

	}

	void addToPanel(Component comp)
	{
		this.panel.add(comp);
		screen.setVisible(true);
	}
	
	void addToPanel(Component comp, Vector2 pos)
	{
		this.panel.add(comp);
		screen.setVisible(true);
		comp.setLocation((int)pos.x, (int)pos.y);
	}

	void addToScreen(Component comp)
	{
		this.screen.add(comp);
		screen.setVisible(true);
	}

	void setSize(Vector2 size)
	{
		this.screen.setSize((int)size.x, (int)size.y);
	}

	JPanel returnJPanel()
	{
		return panel;
	}

	JFrame returnJFrame()
	{
		return screen;
	}
}
