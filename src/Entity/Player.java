package Src.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.imageio.ImageIO;

import Src.Entity.Bomb.BombState;
import Src.Main.GamePanel;
import Src.Manager.BombManager;
import Src.Title.Title;
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
	*/
	public Player(GamePanel gp)
	{
		super(gp.mapManager.ReturnPlayerPos(), new Vector2(32,42), null);
		
		this.gp = gp;
		this.keyH = gp.keyh;
		InitPlayer();
	}

	/**
	 * Initializes the player by creating sprite vectors, loading player images,
	 * and setting up initial parameters such as speed, sprite index, and collision area.
	 * Assumes the existence of player sprite images in specific directories.
	*/
	public void InitPlayer() 
	{
		getPlayerImage();
		bombManager = new BombManager(gp);
		sprite = FrontVector.elementAt(1);
		
		speed = 2;
		spriteIndex = 0;
		frameCount = 0;
		life = 0;
		point = 0;
		cooldownDamage = 0;

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

		gp.gameManager.setAvatar(this);

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
	 * Method to manage the direction of player
	 * @throws IOException
	*/
	public void HandlePlayerInput() throws IOException
	{
		if (spriteIndex == 4)
			spriteIndex = 0;

		// Reset sprite index if no keys are pressed.
		if (keyH.anyKey == false)
			spriteIndex = 1;

		if(keyH.space == true)
		{
			bombManager.PlaceBomb(getTitle());
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

		
		Collider tmp = new Collider(
			new Vector2((pos.x + (dir.x * speed)) + 3, (pos.y + (dir.y * speed)) + 20), 
			size.x - 6,
			size.y - 15);

		// Check for collisions with map titles.
		isCollided = gp.cChecker.CheckTitle(tmp);
			
		for (Bomb bomb : bombManager.getBombList()) 
		{
			if (bomb.myState == BombState.Placed)
			{
				if (bomb.isPlayerHover && !bomb.coll.rec.intersects(tmp.rec))
				{
					System.out.println("nazione");
					bomb.isPlayerHover = false;
				}
				if(gp.cChecker.CheckTitle(tmp) == false)
				{
					if (!bomb.isPlayerHover && bomb.coll.rec.intersects(tmp.rec))
					{
						System.out.println("levante");
						isCollided = true;
					}					
				}
				else
					isCollided = gp.cChecker.CheckTitle(tmp);
			}
		}
			

		if(isCollided == false && gp.mapManager.isEntityInsidePerimeter(tmp))
		{
			coll = new Collider(
				new Vector2((pos.x + (dir.x * speed)) + 3, (pos.y + (dir.y * speed)) + 20), 
				size.x - 6,
				size.y - 15);

			pos.x += (dir.x * speed);
			pos.y += (dir.y * speed);
		}

	}


	/**
	 * Method to updates the player's position and sprite based on user input and game logic
	 * Handles movement in four directions (up, down, left, right) and sprite animation
	 * @throws IOException
	*/
	public void Update() throws IOException
	{

		int animationRatio;
		int maxSpriteIndex;

		//when player take damege 
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
			animationRatio = 12;
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
		
		gp.gameManager.updateUserData(this);

	}

	/**
	 * Method to draws the player on the provided Graphics2D object.
	 * @param g2 The Graphics2D object on which the player will be drawn.
	*/
	public void Draw(Graphics2D g2)
	{
		g2.drawImage(sprite,pos.x, pos.y, size.x, size.y,null);
		g2.drawRect(coll.rec.x,coll.rec.y,coll.rec.width,coll.rec.height);
		bombManager.Draw(g2);
 	}

	/**
 	* Method to comunicate the status of player whrùen it change
	* @param o The observable object that this observer is registered to
	* @param arg The argument passed to the `notifyObservers` method
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

	/**
	 * Method to add points
	*/
	public void addPoint(int point){this.point += point;}

	/**
	 * Method to get points
	*/
	public int getPoint(){return this.point;}

	/**
	 * Retrieves the title from the specified position and size using the MapManager.
	 *
	 * This method utilizes the MapManager to retrieve the title located at the specified
	 * position and size within the map. The MapManager is responsible for managing the
	 * mapping of positions to titles.
	 *
	 * @return The title located at the specified position and size.
	 *  
	 */
	public Title getTitle() { return gp.mapManager.GetTitleFromPos(coll.pos,size); }
	
}
