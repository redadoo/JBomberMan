package Src.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.imageio.ImageIO;

import Src.Main.GamePanel;
import Src.Manager.BombManager;
import Src.Manager.TitleManager;
import Src.Utils.KeyHandler;
import Src.lib.Collider;
import Src.lib.Vector2;

/**
 * Class Player that contains fields for handling animation frames, storing sprite images for different directions,
 * and references to the game panel and key handler for input processing.
 */
public class Player extends Entity implements Observer
{
	public	int							life;
	private int							point;
	public	String						Avatar;
	private	int							frameCount; // Counter for frame animation.
	private	Vector<BufferedImage>		RightVector;  // Vector for right-facing player sprites.
	private	Vector<BufferedImage>		LeftVector; // Vector for left-facing player sprites.
	private	Vector<BufferedImage>		FrontVector; // Vector for front-facing player sprites.
	private	Vector<BufferedImage>		BackVector;// Vector for back-facing player sprites.
	private GamePanel 					gp; // Reference to the game panel.
	private KeyHandler 					keyH;// Reference to the key handler for player input.
	public	BombManager					bombManager;
	public 	double						cooldownDamage;
	public	boolean						takeDamage;
	/**
	 * Constructor class Player 
	 * Initializes a player object with the specified parameters, including the game panel,
	 * key handler, and title manager. Sets the initial position, size, and other properties of the player.
	 * @param gp The GamePanel instance to which the player belongs.
	 * @param keyH The KeyHandler responsible for handling player input.
	 * @param mapManager The TitleManager providing map-related functionality.
	 * @throws IOException
	 */
	public Player(GamePanel gp)
	{
		super(gp.mapManager.ReturnPlayerPos(), new Vector2(32,42), null);
		
		this.gp = gp;
		this.keyH = gp.keyh;

		cooldownDamage = 0;
		InitPlayer();
	}

	/**
	 * Initializes the player by creating sprite vectors, loading player images,
	 * and setting up initial parameters such as speed, sprite index, and collision area.
	 * Assumes the existence of player sprite images in specific directories.
	 * @throws IOException
	 */
	public void InitPlayer()
	{
		getPlayerImage();
		bombManager = new BombManager(gp);
		sprite = FrontVector.elementAt(1);
		
		speed = 2;
		spriteIndex = 0;
		frameCount = 0;
		life = 5;
		point = 0;
	}

	/**
	 * Loads player sprite images for different directions (front, back, left, right).
	 * Populates the respective vectors with image resources for animation.
	 */
	public void getPlayerImage()
	{
		RightVector = new Vector<BufferedImage>();
		LeftVector = new Vector<BufferedImage>();
		BackVector = new Vector<BufferedImage>();
		FrontVector = new Vector<BufferedImage>();

		gp.gameManager.transferData(this);
		Avatar += "Player/";

		try {
 			RightVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Players/" + Avatar + "RightSprite/PlayerRight_0.png")));
 			RightVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Players/" + Avatar + "RightSprite/PlayerRight_1.png")));
 			RightVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Players/" + Avatar + "RightSprite/PlayerRight_2.png")));
 			RightVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Players/" + Avatar + "RightSprite/PlayerRight_1.png")));

			LeftVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Players/" + Avatar + "LeftSprite/PlayerLeft_0.png")));
			LeftVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Players/" + Avatar + "LeftSprite/PlayerLeft_1.png")));
			LeftVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Players/" + Avatar + "LeftSprite/PlayerLeft_2.png")));
			LeftVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Players/" + Avatar + "LeftSprite/PlayerLeft_1.png")));

			FrontVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Players/" + Avatar + "FrontSprite/PlayerFront_0.png")));
			FrontVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Players/" + Avatar + "FrontSprite/PlayerFront_1.png")));
			FrontVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Players/" + Avatar + "FrontSprite/PlayerFront_2.png")));
			FrontVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Players/" + Avatar + "FrontSprite/PlayerFront_1.png")));

			BackVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Players/" + Avatar + "BackSprite/PlayerBack_0.png")));
			BackVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Players/" + Avatar + "BackSprite/PlayerBack_1.png")));
			BackVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Players/" + Avatar + "BackSprite/PlayerBack_2.png")));
			BackVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Players/" + Avatar + "BackSprite/PlayerBack_1.png")));

			spriteVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Players/" + Avatar + "death/_PlayerDeathSprite00.png")));
			spriteVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Players/" + Avatar + "death/_PlayerDeathSprite01.png")));
			spriteVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Players/" + Avatar + "death/_PlayerDeathSprite02.png")));
			spriteVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Players/" + Avatar + "death/_PlayerDeathSprite03.png")));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Manage the direction of player
	 */
	public void HandlePlayerInput() throws IOException
	{
		if (spriteIndex == 4)
			spriteIndex = 0;
		// Reset sprite index if no keys are pressed.
		if (keyH.anyKey == false)
			spriteIndex = 1;

		if(keyH.space == true)
			bombManager.PlaceBomb(coll.rec);

		if(keyH.upPressed == true)
		{
			dir.y = -1;
			setSprite(BackVector.elementAt(spriteIndex));
		}
		else if(keyH.downPressed == true)
		{
			dir.y = 1;
			setSprite(FrontVector.elementAt(spriteIndex));
		}
		else if(keyH.leftPressed == true)
		{
			dir.x = -1;
			setSprite(LeftVector.elementAt(spriteIndex));
		}
		else if(keyH.rightPressed == true)
		{
			dir.x = 1;
			setSprite(RightVector.elementAt(spriteIndex));
		}

		coll = new Collider(
			new Vector2((pos.x + (dir.x * speed)) + 3, (pos.y + (dir.y * speed)) + 20), 
			size.x - 6,
			size.y - 15);

		// Check for collisions with map titles.
		isCollided = gp.cChecker.CheckTitle(coll);

		if(isCollided == false && gp.mapManager.isEntityInsidePerimeter(coll))
		{
			pos.x += (dir.x * speed);
			pos.y += (dir.y * speed);
		}
	}


	/**
	 * Updates the player's position and sprite based on user input and game logic.
	 * Handles movement in four directions (up, down, left, right) and sprite animation.
	 * @throws IOException
	 */
	public void Update() throws IOException
	{
		int animationRatio;
		int maxSpriteIndex;

		if (takeDamage == true)
		{
			animationRatio = 20;
			maxSpriteIndex = 4;
			
			if(cooldownDamage == 0)
			{
				spriteIndex = 0;
				cooldownDamage = gp.elapsedTime;
			}

			sprite = spriteVector.get(spriteIndex);
			
			if(gp.elapsedTime >= cooldownDamage + 3)
			{
				cooldownDamage = 0;
				takeDamage = false;
				pos = new Vector2(48,90);
				coll.setPos(pos);
				sprite = FrontVector.get(1);
			}
		}
		else
		{
			animationRatio = 15;
			maxSpriteIndex = 3;
			HandlePlayerInput();
		}
		if (frameCount % animationRatio == 0)
		{
			spriteIndex++;
			if(spriteIndex == maxSpriteIndex)
			{
				if (takeDamage == true)
					spriteIndex = maxSpriteIndex - 1;
				else
					spriteIndex = 0;
			}
			frameCount = 0;
		}
		// Increment frame count for animation.
		frameCount++;
		// reset dir every frame
		dir = new Vector2(0,0);
		//update bombmanager
		bombManager.Update();

	}

	/**
	 * Draws the player on the provided Graphics2D object.
	 * @param g2 The Graphics2D object on which the player will be drawn.
	 */
	public void Draw(Graphics2D g2)
	{
		
		g2.drawImage(sprite,pos.x,pos.y,size.x,size.y,null);
		
		bombManager.Draw(g2);
		g2.drawRect(coll.rec.x, coll.rec.y, coll.rec.width , coll.rec.height);
		
	}

	/**
	 * Function to comunicate the status of player
	 * @param o
	 * @param arg
	*/
	@Override
	public void update(Observable o, Object arg) 
	{
		if((boolean)arg == true && takeDamage == false)
		{
			if (life == 0)
				gp.gameManager.death();
			life--;
			takeDamage = true;
		}
	}

	public void addPoint(int point){this.point += point;}

	public int getPoint(){return this.point;}
}