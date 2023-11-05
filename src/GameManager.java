package src;

import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameManager
{  

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		// Inizializzazione del schermo 
		GamePanel gamePanel = new GamePanel(new JFrame(), new JPanel(), new Vector2(512, 448), "Snes Bomberman");

		// Inizializzazione della mappa
		Map map = new Map("src/Resource/Maps/stage_0.png", new Vector2(0, 83), new Vector2(497, 328));
		
		// Inizializzazione del Player 
		Player player = new Player("src/Resource/PlayerSprite/PlayerFront.png", new Vector2(-130,-120), new Vector2(25, 25));
		
		/*
		* Aggiungiamo degli elementi allo schermo 
		  La mappa e il player
		*/
		gamePanel.addToPanel(player.returnLabel());
		gamePanel.addToPanel(map.returnLabel(), map.getPos());

		
		/*
		*  Movimento
		*/
		while (true)
		{
			//Posizione iniziale al lancio del programma
			player.moveEntity(player.getPos());
			
			// Tasto a destra
			if (Keyboard.isKeyPressed(KeyEvent.VK_D))
				player.moveEntity(new Vector2(player.getPos().x + player.returnMoveDistance(), player.getPos().y));			
			// Tasto in alto
			if (Keyboard.isKeyPressed(KeyEvent.VK_W))
				player.moveEntity(new Vector2(player.getPos().x ,player.getPos().y - player.returnMoveDistance()));			
			// Tasto a sinistra
			if (Keyboard.isKeyPressed(KeyEvent.VK_A))
				player.moveEntity(new Vector2(player.getPos().x - player.returnMoveDistance(), player.getPos().y));			
			// Tasto in basso
			if (Keyboard.isKeyPressed(KeyEvent.VK_S))
				player.moveEntity(new Vector2(player.getPos().x, player.getPos().y + player.returnMoveDistance()));				
			/* map.moveEntity(player.getPos()); */
			
			Thread.sleep(60);
		}	
	}
}  