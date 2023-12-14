package src;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Vector;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameManager
{
	public static Entity[] InitObjects(GamePanel gamePanel) throws IOException {
		String []ClockSprites =new String[]{ "src/Resource/Sprites/ClockSprite/sprite_0.png", 
											 "src/Resource/Sprites/ClockSprite/sprite_1.png",
											 "src/Resource/Sprites/ClockSprite/sprite_2.png",
											 "src/Resource/Sprites/ClockSprite/sprite_3.png",
											 "src/Resource/Sprites/ClockSprite/sprite_4.png",
											 "src/Resource/Sprites/ClockSprite/sprite_5.png",
											 "src/Resource/Sprites/ClockSprite/sprite_6.png",
											 "src/Resource/Sprites/ClockSprite/sprite_7.png",
											};

		String []AlarmSprites =new String[]{ "src/Resource/Sprites/OstacoliSprites/sprite_0.png", 
											"src/Resource/Sprites/OstacoliSprites/sprite_1.png",
											"src/Resource/Sprites/OstacoliSprites/sprite_2.png",
											"src/Resource/Sprites/OstacoliSprites/sprite_3.png"};


		Entity clock = new Entity(new Vector2(243,10), new Vector2(28,40), "src/Resource/Sprites/ClockSprite/sprite_0.png") {};
		clock.InitSpriteArray(ClockSprites);

		Alarm alarm = new Alarm( "src/Resource/Sprites/OstacoliSprites/sprite_0.png", new Vector2(49, 160), new Vector2(30, 40));

		alarm.InitSpriteArray(AlarmSprites);

		Alarm alarm2 = new Alarm( "src/Resource/Sprites/OstacoliSprites/sprite_0.png", new Vector2(113, 183), new Vector2(30, 40));

		alarm2.InitSpriteArray(AlarmSprites);

		Alarm alarm3 = new Alarm( "src/Resource/Sprites/OstacoliSprites/sprite_0.png", new Vector2(370, 183), new Vector2(30, 40));

		alarm3.InitSpriteArray(AlarmSprites);

		gamePanel.addToPanel(clock.getLabel());
		gamePanel.addToPanel(alarm.getLabel());
		gamePanel.addToPanel(alarm2.getLabel());
		gamePanel.addToPanel(alarm3.getLabel());

		return (new Entity[]{alarm,alarm2,alarm3,clock});

	}
	public static void main(String[] args) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
		
		int					i = 0;
		boolean				isPlayerCollide = false;
		Collider			newPlayerCollider;
		Entity[]			ObjectArray;
		Vector2				newPosPlayer;
		Vector<Collider>	colliderArray = new Vector<Collider>();

		//Creazione di un thread per la gestione della musica
		Thread MusicThread = new Thread(new AudioPlayer("C:/Users/edoar/Desktop/JavaGame/src/Resource/Fizzy.wav"));
		
		// Inizializzazione del schermo 
		GamePanel gamePanel = new GamePanel(new JFrame(), new JPanel(), new Vector2(512, 450), "Snes Bomberman");

		// Inizializzazione della mappa
		Map map = new Map("src/Resource/Maps/map_1.png", new Vector2(0, 0), new Vector2(512, 410));
		
		// Inizializzazione del Player 
		Player player = new Player("src/Resource/PlayerSprite/PlayerFront.png", new Vector2(50,65), new Vector2(30, 40));

		gamePanel.addToPanel(player.getLabel());
		ObjectArray = InitObjects(gamePanel);
		gamePanel.addToPanel(map.returnLabel(), map.getPos());
		
		newPosPlayer = player.getPos();
		player.moveEntity(player.getPos());

		for (Entity entity : ObjectArray) 
			entity.moveEntity(entity.getPos());

		for (int j = 0; j < ObjectArray.length; j++) {
			if (j < ObjectArray.length -1)
				colliderArray.add(ObjectArray[j].getCollider());
		}
		
		/*
		*  Movimento
		*/
		MusicThread.start();
		while (true)
		{	
			if (Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE))
				System.exit(1);
			
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
			
			newPlayerCollider = new Collider(newPosPlayer, player.getSize().x, player.getSize().y);

			if (newPosPlayer.x > player.getPos().x) player.changeSpirte("src/Resource/PlayerSprite/PlayerRight.png");
			if (newPosPlayer.x < player.getPos().x) player.changeSpirte("src/Resource/PlayerSprite/PlayerLeft.png");
			if (newPosPlayer.y < player.getPos().y) player.changeSpirte("src/Resource/PlayerSprite/PlayerBack.png");
			if (newPosPlayer.y > player.getPos().y) player.changeSpirte("src/Resource/PlayerSprite/PlayerFront.png");
			
			// Colider Alarm
			for (int j = 0; j < colliderArray.size(); j++) {

				if (checkCollideBoxes(newPlayerCollider, colliderArray.elementAt(j)) == true)
				{
					System.out.println("testc");
					isPlayerCollide = true;
				}	
			}
			
			// Collider internal objec
			for (int j = 0; j < map.mapCollider.length; j++) {
				if (checkCollideBoxes(newPlayerCollider, map.mapCollider[j]) == true)
				{
					System.out.println("a");
					isPlayerCollide = true;
				}	
			}
			
			if (isPlayerCollide == false && newPosPlayer.y >= 60 && newPosPlayer.y <= 340 && newPosPlayer.x >= 50 && newPosPlayer.x <= 440)
				player.moveEntity(newPosPlayer);
				
			// Adjust the update frequency of sprites
			for(int x = 0; x < ObjectArray.length; x++) {
				if (x < ObjectArray.length -1)
				ObjectArray[x].NextSprite();
				else if (x >= ObjectArray.length -1 && i == 15)
				{
					ObjectArray[x].NextSprite();
					i = 0;
				}
			}

			i++;
			isPlayerCollide = false;
			Thread.sleep(60);
			}	
			
		}

		public static Boolean checkCollideBoxes(Collider colliderA, Collider colliderB) {
			boxCollider a = colliderA.returnBoxCollider();
			boxCollider b = colliderB.returnBoxCollider();
			if (a.pos.x + a.xSize >= b.pos.x && a.pos.x <= b.pos.x + b.xSize && a.pos.y + a.ySize >= b.pos.y && a.pos.y <= b.pos.y + b.ySize)
    		return true;
		return false;
		}
}  