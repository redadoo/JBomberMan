package Src.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;

import Src.Main.GamePanel;
import Src.lib.Vector2;

/**
 *  Class to manage the FlyHead enemy
*/
public class FlyHead extends Entity
{
	private int        					pointDrop = 100;
	private	Vector<BufferedImage>		FrontVector; // Vector for front-facing Enemy sprites.
	private	Vector<BufferedImage>		BackVector;// Vector for back-facing Enemy sprites.
	
	/**
	 * Costructor class FlyHead
	*/
	public FlyHead(Vector2 pos, Vector2 size) throws IOException
	{
		super(pos, size);
		FrontVector = new Vector<BufferedImage>();
		BackVector = new Vector<BufferedImage>();
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
	 * Remain enemy's life 
	 * @return the main life of enemy
	*/
	public int getPointDrop() { return pointDrop; }

	/**
	 * To get the direction of enemy
	 * @return direction
	 */
	public Vector2 getDir() { return dir; }

	/**
	 * Loads Enemy sprite images for different directions (front and back).
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
	 * Function to set the sprite
	 */
	public void setSprite(BufferedImage sprite)
	{
		this.sprite = sprite;
	}
	
	/**
	 * Function to update the status of enemy
	 */
	public void Update(GamePanel gp)
	{
		coll.setPos(new Vector2((pos.x + (dir.x * speed)) + 3, (pos.y + (dir.y * speed)) + 20));
		coll.rec.height = size.x - 6; 
		coll.rec.width = size.y - 15;

		isCollided = gp.cChecker.CheckTitle(coll);

		if(isCollided == false && gp.mapManager.isEntityInsidePerimeter(coll))
		{
			pos.x += (dir.x * speed);
			pos.y += (dir.y * speed);
		}
		else if(!(gp.mapManager.isEntityInsidePerimeter(coll)) || isCollided == true)
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
	 * Draws the FlyHead on the provided Graphics2D object.
	 * @param g2 The Graphics2D object on which the player will be drawn.
	 */
	public void Draw(Graphics2D g2)
	{
		g2.drawImage(sprite,pos.x,pos.y,size.x,size.y,null);
		
		g2.drawRect(coll.rec.x, coll.rec.y, coll.rec.width , coll.rec.height);

	}

}