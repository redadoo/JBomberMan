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
		  Aggiungiamo degli elementi allo schermo
		  La mappa e il player
		*/
		gamePanel.addToPanel(player.returnLabel());
		gamePanel.addToPanel(map.returnLabel(), map.getPos());

		/*
		*  Movimento
		*/
		newPosPlayer = player.getPos();
		player.moveEntity(player.getPos());

		while (true)
		{	
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

			//System.out.println("x: "+player.getPos().x);
			//System.out.println("y: "+player.getPos().y);


			//Controllo che il personaggio si trovi all'interno della mappa
			if (newPosPlayer.y >= 100 && newPosPlayer.y <= 350 && newPosPlayer.x >= 60 && newPosPlayer.x <= 410)
			{
				boolean collision = false;

				for (int i = 0; i < map.mapCollider.length; i++) 
				{
					//Se collide
					Collider newPlayCollider = new Collider(newPosPlayer, (float) player.getSize().x, (float) player.getSize().y);

					if (Collider.checkCollideBoxes(newPlayCollider, map.mapCollider[i])) 
					{
						System.out.println(" Sto collidendo: ");

						collision = true;
						break; // Se c'è collisione, esci dal ciclo
					}
				}
				
				// Muovi il giocatore solo se non c'è stata alcuna collisione
				if (!collision) 
				{
					player.moveEntity(newPosPlayer);
				}
			}


			Thread.sleep(60);
		}	

	}

	//Testing
	private static void PrintPos(Vector2 pos) {
		System.out.println(" x :   ");
		System.out.println(String.valueOf(pos.x));
		System.out.println(" y :   ");
		System.out.println(String.valueOf(pos.y));
	}

	private static void PrintSize(Vector2 pos, double x, double y) 
	{
		System.out.println("pos :   ");
		System.out.println(String.valueOf(pos.x));
		System.out.println(String.valueOf(pos.y));
		System.out.println("size x :   ");
		System.out.println(String.valueOf(x));
		System.out.println("size y :   ");
		System.out.println(String.valueOf(y));
	}

}  