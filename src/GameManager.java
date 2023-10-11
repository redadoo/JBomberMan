package src;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/* import sun.auscreen.AuscreenPlayer;
import sun.auscreen.AuscreenStream; */



public class GameManager
{  
	private Ui ui;

	static public void Print(String x)
	{
		System.out.print(x);
	}

	static public void PrintPos(Vector2 pos)
	{
		Print("x :   ");
		Print(String.valueOf(pos.x));
		Print("y :   ");
		Print(String.valueOf(pos.y));
		Print("\n");
	}

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		JFrame	screen = new JFrame();
		JPanel  panel = new JPanel();

		Vector2 PlayerPos = new Vector2(-130,-120);
		Vector2 PlayerSize = new Vector2(1,1);
		int		offSet = 10;
		Player player = new Player("src/sprite_4.png", PlayerPos, PlayerSize);
		ImageIcon map = new ImageIcon("src/mappa.png");
		
		java.awt.Image image = map.getImage();
		java.awt.Image newimg = image.getScaledInstance(512, 328,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
		ImageIcon newImageIcon = new ImageIcon(newimg);

		JLabel mappa = new JLabel(newImageIcon);
		/*
		* Settiamo lo screen
		*/

		screen.setSize(512, 448);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setTitle("Snes Bomberman"); 					//Titolo in alto a sinistra -> della window
        screen.setLocationRelativeTo(null); 				//Far partire la window dal centro dello schermo
		panel.setBackground(Color.black);
		screen.add(panel);
		panel.add(player.returnLabel());
		panel.add(mappa);
		mappa.setLocation(-130, 10);
		screen.setVisible(true);

		
		while (true)
		{
			if (Keyboard.isKeyPressed(KeyEvent.VK_D))
			{
				PrintPos(player.getPos());
				
				//Settiamo la nuova posizione
				Vector2 newPos = new Vector2(player.getPos().x + offSet,player.getPos().y);
				//Passimao la nuova posizione da impostare per lo spostamento
				player.moveEntity(newPos);				
 			}
			// Tasto in alto
			if (Keyboard.isKeyPressed(KeyEvent.VK_W))
			{
				PrintPos(player.getPos());

				Vector2 newPos = new Vector2(player.getPos().x ,player.getPos().y - offSet);
				player.moveEntity(newPos);
 			}
			// Tasto a sinistra
			if (Keyboard.isKeyPressed(KeyEvent.VK_A))
			{
				PrintPos(player.getPos());

				Vector2 newPos = new Vector2(player.getPos().x - offSet,player.getPos().y);

				player.moveEntity(newPos);			
 			} 
			// Tasto in basso
			if (Keyboard.isKeyPressed(KeyEvent.VK_S))
			{
				PrintPos(player.getPos());

				Vector2 newPos = new Vector2(player.getPos().x ,player.getPos().y + offSet);
				player.moveEntity(newPos);		
 			}
			
			
			Thread.sleep(60);

		}	
	}
}  