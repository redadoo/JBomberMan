package Src.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import Src.Main.GamePanel;
import Src.Title.Title;
import Src.lib.Collider;
import Src.lib.Vector2;

/**
 * Class to manage the FlyHead enemy
*/
public class FlyHead extends Entity
{
	private int        					pointDrop = 100;
	private	ArrayList<BufferedImage>	FrontVector;
	private	ArrayList<BufferedImage>	BackVector;
	private GamePanel					gp;

	/**
	 * Costructor class FlyHead
	 * @param pos the position where set the player 
	 * @param size the size to be assegn to player
	 * @throws IOException
	*/
	public FlyHead(GamePanel gp,Vector2 pos, Vector2 size) throws IOException
	{
		super(new Vector2(pos.x,pos.y - 10), size);
		FrontVector = new ArrayList<BufferedImage>();
		BackVector = new ArrayList<BufferedImage>();
		this.gp = gp; 
		getflyHeadImage();
		initFlyHead();
	}

	/**
	 * Init the FlyHead
	 */
	public void initFlyHead()
	{
		int randomNumber = new Random().nextInt(2) + 1;
		if (randomNumber == 2)
			randomNumber = -1;
		dir = new Vector2(0, randomNumber);
		if (dir.y == -1)
			sprite = BackVector.get(spriteIndex);
		else
			sprite = FrontVector.get(spriteIndex);
		setSprite(sprite);
	}
	
	/**
	 * Method to get the remain enemy's life 
	 * @return the main life of enemy
	*/
	public int getPointDrop() { return pointDrop; }

	/**
	 * Method to get the direction of enemy
	 * @return direction
	 */
	public Vector2 getDir() { return dir; }

	/**
	 * Method to loads Enemy sprite images for different directions (front and back).
	 */
	public void getflyHeadImage()
	{
		try {

			FrontVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/FlyHead/FrontSprite/FlyHead_0.png")));
			FrontVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/FlyHead/FrontSprite/FlyHead_1.png")));
			FrontVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/FlyHead/FrontSprite/FlyHead_2.png")));
			FrontVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/FlyHead/FrontSprite/FlyHead_3.png")));

			BackVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/FlyHead/BackSprite/FlyHead_4.png")));
			BackVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/FlyHead/BackSprite/FlyHead_5.png")));
			BackVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/FlyHead/BackSprite/FlyHead_6.png")));
			BackVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/FlyHead/BackSprite/FlyHead_7.png")));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to set the sprite
	 */
	public void setSprite(BufferedImage sprite)
	{
		this.sprite = sprite;
	}
	
	/**
	 * Method to update the status of enemy
	 */
	public void Update()
	{

		Collider tmp = new Collider(
			new Vector2((pos.x + (dir.x * speed)) + 3, (pos.y + (dir.y * speed)) + 20), 
			size.x - 6,
			size.y - 15);
		
		if (gp.mapManager.isEntityInsidePerimeter(tmp) && !gp.cChecker.CheckTitle(tmp) && gp.cChecker.CheckBomb(tmp) && gp.cChecker.CheckOtherEnemies(tmp, this)) {
			isCollided = false;
		} else {
			isCollided = true;
		}		

		if (!isCollided)
		{
			coll = tmp;
			
			pos.x += (dir.x * speed);
			pos.y += (dir.y * speed);
		}
		else
			dir.y = -dir.y;

		if (frameCount == 3)
		{
			spriteIndex++;
			if(spriteIndex == 4)
				spriteIndex = 0;

			if (dir.y == -1)
				setSprite(BackVector.get(spriteIndex));
			else
				setSprite(FrontVector.get(spriteIndex));

			frameCount = 0;
		}

		frameCount++;
	}

	/**
	 * Method to draws the FlyHead on the provided Graphics2D object.
	 * @param g2 The Graphics2D object on which the player will be drawn.
	 */
	public void Draw(Graphics2D g2)
	{
		g2.drawImage(sprite,pos.x,pos.y,size.x,size.y,null);
	}
	
	/**
	 * Gets the title based on the position and size.
	 * @return the Title object associated with the position and size
	*/
	public Title getTitle() 
	{ 		
		return gp.mapManager.GetTitleFromPos(coll.pos,size);
	}

}