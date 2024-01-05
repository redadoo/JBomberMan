package Src.Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
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
 *  Class to manage the first enemy
*/
public class FlyHead extends Entity
{
	private Vector2     dir;
	private int         pointDrop;
	public	boolean		isCollided;

	private	Vector<BufferedImage>		FrontVector; // Vector for front-facing Enemy sprites.
	private	Vector<BufferedImage>		BackVector;// Vector for back-facing Enemy sprites.
	
	/**
	 * Costructor class FlyHead
	*/
	public FlyHead(Vector2 pos, Vector2 size) throws IOException
	{
		
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
	 * To change the direction of enemy
	 * @throws IOException
	 */
/* 	public void changeDir() throws IOException
	{
		if (dir.y == -1)
		{
			changeSpirte(FrontVector);
			dir.y = 1;
		}
		else 
		{
			changeSpirte(frontSpriteArray[0]);
			dir.y = -1;
		}
	} */
 
	/**
	 * Routine of enemy
	 * @throws IOException
	*/
/*	public void update() throws IOException
	{
		if (dir.y == -1)
			setSprite(FrontVector);
		else 
			setSprite(BackVector);
		NextSprite();
	} 
*/

	/**
	 * Loads Enemy sprite images for different directions (front and back).
	 */
	public void getflyHeadImage()
	{
		try {

			FrontVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/FrontSprite/FlyHead_0.png")));
			FrontVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/FrontSprite/FlyHead_1.png")));
			FrontVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/FrontSprite/FlyHead_2.png")));
			FrontVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/FrontSprite/FlyHead_3.png")));

			BackVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/BackSprite/FlyHead_4.png")));
			BackVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/BackSprite/FlyHead_5.png")));
			BackVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/BackSprite/FlyHead_6.png")));
			BackVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Player/BackSprite/FlyHead_7.png")));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSprite(BufferedImage sprite)
	{
		this.sprite = sprite;
	}
	
	/**
	 * Draws the player on the provided Graphics2D object.
	 * @param g2 The Graphics2D object on which the player will be drawn.
	 */
	public void Draw(Graphics2D g2)
	{
		g2.drawImage(sprite,pos.x,pos.y,size.x,size.y,null);
		
		g2.drawRect(coll.rec.x, coll.rec.y, coll.rec.width , coll.rec.height);

	}

}