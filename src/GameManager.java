package src;

import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameManager
{  
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		Vector2 newPosPlayer;
		// Inizializzazione del schermo 
		GamePanel gamePanel = new GamePanel(new JFrame(), new JPanel(), new Vector2(512, 448), "Snes Bomberman");

		// Inizializzazione della mappa
		Map map = new Map("src/Resource/Maps/stage_0.png", new Vector2(0, 83), new Vector2(497, 328));
		
		// Inizializzazione del Player 
		Player player = new Player("src/Resource/PlayerSprite/PlayerFront.png", new Vector2(60,100), new Vector2(25, 25));
		
		/*
		* Aggiungiamo degli elementi allo schermo 
		  La mappa e il player
		*/
		gamePanel.addToPanel(player.returnLabel());
		gamePanel.addToPanel(map.returnLabel(), map.getPos());

		/*
		*  Movimento
		*/
		newPosPlayer = player.getPos();
		while (true)
		{
			//Posizione iniziale al lancio del programma
			player.moveEntity(player.getPos());

			// Tasto a destra
			if (Keyboard.isKeyPressed(KeyEvent.VK_D))
				newPosPlayer = (new Vector2(player.getPos().x + player.returnMoveDistance(), player.getPos().y));			
			// Tasto in alto
			if (Keyboard.isKeyPressed(KeyEvent.VK_W))
				newPosPlayer = (new Vector2(player.getPos().x ,player.getPos().y - player.returnMoveDistance()));			
			// Tasto a sinistra
			if (Keyboard.isKeyPressed(KeyEvent.VK_A))
				newPosPlayer = (new Vector2(player.getPos().x - player.returnMoveDistance(), player.getPos().y));			
			// Tasto in basso
			if (Keyboard.isKeyPressed(KeyEvent.VK_S))
				newPosPlayer = (new Vector2(player.getPos().x, player.getPos().y + player.returnMoveDistance()));				
			
			//Controllo che il personaggio si trovi all'interno della mappa
			if (newPosPlayer.y >= 100 && newPosPlayer.y <= 350 && newPosPlayer.x >= 60 && newPosPlayer.x <= 410)
			{
				//Se sta all'interno, controllo che non passi sopra altri oggetti contenuti in mapCollider
				for(int i = 0; i < map.mapCollider.length; i++)
				{
					Collider newPlayerCollider = new Collider(newPosPlayer, player.getSize().x, player.getSize().y);
					if (Collider.checkCollideBoxes(newPlayerCollider, map.mapCollider[i]))
					{
						System.out.println("caz");
						player.moveEntity(newPosPlayer);
						//Se il personaggio si muove, deve finire il for
						break;
					}
					else
					{
						System.out.println("caz");
						break;
					}
				}
			}
			Thread.sleep(60);
		}	
	}
}  