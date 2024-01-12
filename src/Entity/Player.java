package Src.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import Src.Main.GamePanel;
import Src.Main.KeyHandler;
import Src.Title.TitleManager;
import Src.lib.Collider;
import Src.lib.Vector2;

/**
 * The Player class represents a game character with movement and animation capabilities.
 * It contains fields for handling animation frames, storing sprite images for different directions,
 * and references to the game panel and key handler for input processing.
 */
public class Player extends Entity{
	
	private int							life;
	private int							point;
	private	int							frameCount; // Counter for frame animation.
	private	Vector<BufferedImage>		RightVector;  // Vector for right-facing player sprites.
	private	Vector<BufferedImage>		LeftVector; // Vector for left-facing player sprites.
	private	Vector<BufferedImage>		FrontVector; // Vector for front-facing player sprites.
	private	Vector<BufferedImage>		BackVector;// Vector for back-facing player sprites.
	private GamePanel 					gp; // Reference to the game panel.
	private KeyHandler 					keyH;// Reference to the key handler for player input.
	public	BombManager					bombManager;

	/**
	 * Constructor for the Player class.
	 * Initializes a player object with the specified parameters, including the game panel,
	 * key handler, and title manager. Sets the initial position, size, and other properties of the player.
	 *
	 * @param gp The GamePanel instance to which the player belongs.
	 * @param keyH The KeyHandler responsible for handling player input.
	 * @param mapManager The TitleManager providing map-related functionality.
	 * @throws IOException
	 */
	public Player(GamePanel gp, KeyHandler keyH,TitleManager mapManager)
	{
		super(mapManager.ReturnPlayerPos(), new Vector2(32,42), null);
		this.gp = gp;
		this.keyH = keyH;

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
		RightVector = new Vector<BufferedImage>();
		LeftVector = new Vector<BufferedImage>();
		BackVector = new Vector<BufferedImage>();
		FrontVector = new Vector<BufferedImage>();

		getPlayerImage();
		bombManager = new BombManager(gp);
		sprite = FrontVector.elementAt(1);
		
		speed = 2;
		spriteIndex = 0;
		frameCount = 0;
	}

	/**
	 * Loads player sprite images for different directions (front, back, left, right).
	 * Populates the respective vectors with image resources for animation.
	 */
	public void getPlayerImage()
	{
		try {
 			RightVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/RightSprite/PlayerRight_0.png")));
 			RightVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/RightSprite/PlayerRight_1.png")));
 			RightVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/RightSprite/PlayerRight_2.png")));
 			RightVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/RightSprite/PlayerRight_1.png")));

			LeftVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/LeftSprite/PlayerLeft_0.png")));
			LeftVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/LeftSprite/PlayerLeft_1.png")));
			LeftVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/LeftSprite/PlayerLeft_2.png")));
			LeftVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/LeftSprite/PlayerLeft_1.png")));

			FrontVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/FrontSprite/PlayerFront_0.png")));
			FrontVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/FrontSprite/PlayerFront_1.png")));
			FrontVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/FrontSprite/PlayerFront_2.png")));
			FrontVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/FrontSprite/PlayerFront_1.png")));

			BackVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/BackSprite/PlayerBack_0.png")));
			BackVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/BackSprite/PlayerBack_1.png")));
			BackVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/BackSprite/PlayerBack_2.png")));
			BackVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/BackSprite/PlayerBack_1.png")));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates the player's position and sprite based on user input and game logic.
	 * Handles movement in four directions (up, down, left, right) and sprite animation.
	 * @throws IOException
	 */
	public void Update() throws IOException
	{
		
    	// Reset sprite index if no keys are pressed.
		if (keyH.anyKey == false)
		{
			spriteIndex = 1;
		}

		if(keyH.space == true)
		{
			bombManager.PlaceBomb(coll.rec);
		}

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

		if(isCollided == false && coll.pos.y > 120 && coll.pos.y <  445 && coll.pos.x > 45 && coll.pos.x < 435)
		{
			pos.x += (dir.x * speed);
			pos.y += (dir.y * speed);
		}

    	// Perform sprite animation every 15 frames.
		if (frameCount == 15)
		{
			spriteIndex++;
			if(spriteIndex == 3)
				spriteIndex = 0;
			frameCount = 0;
		}
    	// Increment frame count for animation.
		frameCount++;
		dir = new Vector2(0,0);
		bombManager.Update();

	}

	/**
	 * Draws the player on the provided Graphics2D object.
	 * @param g2 The Graphics2D object on which the player will be drawn.
	 */
	public void Draw(Graphics2D g2)
	{
		bombManager.Draw(g2);

		g2.drawImage(sprite,pos.x,pos.y,size.x,size.y,null);
		
		/* if(keyH.debug == true) */
		g2.drawRect(coll.rec.x, coll.rec.y, coll.rec.width , coll.rec.height);
	}

}
