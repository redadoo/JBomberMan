package src;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Vector;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;

import src.lib.Collider;
import src.lib.Vector2;


public class GameManager
{
	static int					j = 0;
	/*
	 *	Function to check if happen a collision
	 */
	public static boolean CheckCollision(Collider newCollider,Vector<Collider> colliderArray, Map map)
	{
		boolean	isPlayerCollide = false;

		// Collider Alarm
		for (int w = 0; w < colliderArray.size(); w++) {
			if (checkCollideBoxes(newCollider, colliderArray.elementAt(w)) == true)
				isPlayerCollide = true;
		}
		
		// Collider internal objec
		for (int v = 0; v < map.mapCollider.length; v++) 
		{
			if (checkCollideBoxes(newCollider, map.mapCollider[v]) == true)
				isPlayerCollide = true;
		}

		return isPlayerCollide;
	}

	/*
	 *	Function to manage the input from keyboards 
	*/
	public static Vector2 InputHandler(Player player, GamePanel gamePanel, Map map, int i) throws IOException
	{
		Vector2				newPosPlayer;

		newPosPlayer = player.getPos();
		
		// Space button to add bomb in the map
		if (Keyboard.isKeyPressed(KeyEvent.VK_SPACE) && player.getNumbBomb() >= 1)
		{
			if (player.PlaceBomb(gamePanel) == true)
			{
					gamePanel.panel.remove(map.returnLabel());
					gamePanel.addToPanel(map.returnLabel());
			}
		}

		// Escape button to close game
		if (Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE))
			System.exit(1);
			
			
		if (j == 4)
			j = 0;
		// Right button
		if (Keyboard.isKeyPressed(KeyEvent.VK_D))
		{
			// Frequency update sprites
			if (i % 3 == 0)
			{
				player.walkSprite(0,j);
				j++;
			}
			newPosPlayer = (new Vector2(player.getPos().x + player.returnMoveDistance(), player.getPos().y));
		}
		else if (Keyboard.isKeyJustReleased(KeyEvent.VK_D))
		{
			j = 0;
			player.changeSpirte(player.RightArray[1]);
		}
		// Up button
		if (Keyboard.isKeyPressed(KeyEvent.VK_W))
		{
			if (i % 3 == 0)
			{
				player.walkSprite(1,j);
				j++;
			}			
			newPosPlayer = (new Vector2(player.getPos().x ,player.getPos().y - player.returnMoveDistance()));
		}
		else if (Keyboard.isKeyJustReleased(KeyEvent.VK_W))
		{
			j = 0;
			player.changeSpirte(player.BackArray[1]);
		}
		// Left button
		if (Keyboard.isKeyPressed(KeyEvent.VK_A))
		{
			if (i % 3 == 0)
			{
				player.walkSprite(2,j);
				j++;
			}	
			newPosPlayer = (new Vector2(player.getPos().x - player.returnMoveDistance(), player.getPos().y));
		}
		else if (Keyboard.isKeyJustReleased(KeyEvent.VK_A))
		{
			j = 0;
			player.changeSpirte(player.LeftArray[1]);
		}
		// Down button
		if (Keyboard.isKeyPressed(KeyEvent.VK_S))
		{
			if (i % 3 == 0)
			{
				player.walkSprite(3,j);
				j++;
			}	
			newPosPlayer = (new Vector2(player.getPos().x, player.getPos().y + player.returnMoveDistance()));
		}
		else if (Keyboard.isKeyJustReleased(KeyEvent.VK_S))
		{
			j = 0;
			player.changeSpirte(player.FrontArray[1]);
		}
		return newPosPlayer;
	}

	/*
	 *	Function to change sprite of Entity 
	*/
	public static void SpriteChanges(Entity[] ObjectArray, Player player, int i) throws IOException
	{
		// Adjust the update frequency of sprites
		for(int x = 0; x < ObjectArray.length; x++) {
			if (x < ObjectArray.length -1)
				ObjectArray[x].NextSprite();

			else if (x >= ObjectArray.length -1 && i % 18 == 0)
				ObjectArray[x].NextSprite();
		}

		// Timer to change the bomb's sprites
		for(int x = 0; x < player.bombs.size(); x++) 
		{
			if (i % 5 == 0 && player.bombs.elementAt(x).ReturnStatus() == false) 
				player.bombs.elementAt(x).NextSprite();
		}
	}
	
	/*
	 *	Init the sprite of the different Entity 
	*/
	public static Entity[] InitObjects(GamePanel gamePanel) throws IOException 
	{
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


		Entity clock = new Entity(new Vector2(243,-177), new Vector2(28,40), "src/Resource/Sprites/ClockSprite/sprite_0.png") {};
		clock.InitSpriteArray(ClockSprites);

		Alarm alarm = new Alarm( "src/Resource/Sprites/OstacoliSprites/sprite_0.png", new Vector2(49, -30), new Vector2(30, 40));

		alarm.InitSpriteArray(AlarmSprites);

		Alarm alarm2 = new Alarm( "src/Resource/Sprites/OstacoliSprites/sprite_0.png", new Vector2(113, -3), new Vector2(30, 40));

		alarm2.InitSpriteArray(AlarmSprites);

		Alarm alarm3 = new Alarm( "src/Resource/Sprites/OstacoliSprites/sprite_0.png", new Vector2(370, -3), new Vector2(30, 40));

		alarm3.InitSpriteArray(AlarmSprites);

		gamePanel.addToPanel(clock.getLabel());
		gamePanel.addToPanel(alarm.getLabel());
		gamePanel.addToPanel(alarm2.getLabel());
		gamePanel.addToPanel(alarm3.getLabel());

		return (new Entity[]{alarm,alarm2,alarm3,clock});
	}
	
	/*
	 *	Controller Function
	*/
	public static void main(String[] args) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
		
		int					i = 0;
		boolean				isPlayerCollide = false;
		Entity[]			ObjectArray;
		Vector2				newPosPlayer;
		Vector<Collider>	colliderArray = new Vector<Collider>();

		// Thread to manage the music
		Thread MusicThread = new Thread(new AudioPlayer("src/Resource/Fizzy.wav"));
		
		// Init screen
		GamePanel gamePanel = new GamePanel(new JFrame(), new JPanel(new BorderLayout()), new Vector2(512, 450), "Snes Bomberman");

		// Init map
		Map map = new Map("src/Resource/Maps/map_1.png", new Vector2(0, 0), new Vector2(512, 410));
		
		// Init Player 
		Player player = new Player("src/Resource/Player/BackSprite/PlayerBack_1.png", new Vector2(50,-120), new Vector2(30, 40));

		

		FlyHead enemy1 = new FlyHead("src/Resource/FlyHead/FlyHead_0.png", new Vector2(50,140), new Vector2(30, 40));

		// Init the object in the map
		gamePanel.addToPanel(enemy1.getLabel());
		gamePanel.addToPanel(player.getLabel());
		ObjectArray = InitObjects(gamePanel);
		gamePanel.addToPanel(map.returnLabel());

		newPosPlayer = player.getPos();
		
		player.moveEntity(player.getPos());
		enemy1.moveEntity(enemy1.getPos());
		for (Entity entity : ObjectArray) 
			entity.moveEntity(entity.getPos());

		for (int  z = 0; z < ObjectArray.length; z++) 
		{
			if (z < ObjectArray.length - 2)
				colliderArray.add(ObjectArray[z].getCollider());
		}
		
		/* MusicThread.start(); */

 		//Moviment
		while (true)
		{
			enemy1.EnemyRoutine();

			if (i == 1000) 
				i = 0;

			Vector2 newEnemyPos = new Vector2(enemy1.getPos().x,enemy1.getPos().y - enemy1.getDir().y);
			enemy1.isCollided = CheckCollision(new Collider(newEnemyPos, enemy1.getSize().x, enemy1.getSize().y), colliderArray,map); 


			newPosPlayer = InputHandler(player, gamePanel, map, i);
			isPlayerCollide = CheckCollision(new Collider(newPosPlayer, player.getSize().x, player.getSize().y), colliderArray,map);
		
			// Check perimeter
			if (isPlayerCollide == false && newPosPlayer.y >= -120 && newPosPlayer.y <= 150 && newPosPlayer.x >= 50 && newPosPlayer.x <= 435)
				player.moveEntity(newPosPlayer);
			if (enemy1.isCollided == false && newEnemyPos.y >= -120 && newEnemyPos.y <= 150 && newEnemyPos.x >= 50 && newEnemyPos.x <= 435)
				enemy1.moveEntity(newEnemyPos);
			else
				enemy1.changeDir();
				
			SpriteChanges(ObjectArray,player,i);

			isPlayerCollide = false;

			Thread.sleep(60);
			i++;
		}
		}

		/*
		 * Check if 2 collider make contact
		*/
		public static Boolean checkCollideBoxes(Collider a, Collider b) {

			if (a.pos.x + a.xSize >= b.pos.x && a.pos.x <= b.pos.x + b.xSize && a.pos.y + a.ySize >= b.pos.y && a.pos.y <= b.pos.y + b.ySize)
    			return true;
			return false;
		}
}  