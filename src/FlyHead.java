package src;

import javax.swing.ImageIcon;

import src.lib.Vector2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 *  Class to manage the first enemy
*/
public class FlyHead extends Entity
{
	private Vector2     dir;
	private int         pointDrop;
	public	boolean		isCollided;
	private String[]	frontSpriteArray;
	private String[]	backSpriteArray;
	
	/**
	 * Costructor class FlyHead
	*/
	public FlyHead(Vector2 pos, Vector2 size) throws IOException
	{
		super(pos, size, "src/Resource/FlyHead/FlyHead_0.png");

		dir = new Vector2();

		// To select the random direction at the begin of game
		int randomNumber = new Random().nextInt(2) + 1;
		if (randomNumber == 2) randomNumber = -1;
		dir.y = randomNumber;
		dir.x = 0;
		
		isCollided = false;
		this.pointDrop = 100;
	
		frontSpriteArray = new String[]{
			"src/Resource/FlyHead/FlyHead_0.png",
			"src/Resource/FlyHead/FlyHead_1.png",
			"src/Resource/FlyHead/FlyHead_2.png",
			"src/Resource/FlyHead/FlyHead_3.png"
		};
		
		backSpriteArray = new String[]{
			"src/Resource/FlyHead/FlyHead_4.png",
			"src/Resource/FlyHead/FlyHead_5.png",
			"src/Resource/FlyHead/FlyHead_6.png",
			"src/Resource/FlyHead/FlyHead_7.png"
		};

		// down
		if (dir.y == -1)
			imagePath = frontSpriteArray[0];
		else 
			imagePath = backSpriteArray[0];	

		File file = new File(imagePath);
		BufferedImage image = ImageIO.read(file);
	
		super.setCollider(pos,12,12);
	
		// Set position
		super.getLabel().setIcon(new ImageIcon(image.getScaledInstance((int)size.x, (int)size.y,  java.awt.Image.SCALE_SMOOTH)));    

		super.getLabel().setLocation((int)this.pos.x, (int)this.pos.y);
		super.getLabel().setLayout(null);
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
	public void changeDir() throws IOException
	{
		if (dir.y == -1)
		{
			changeSpirte(backSpriteArray[0]);
			dir.y = 1;
		}
		else 
		{
			changeSpirte(frontSpriteArray[0]);
			dir.y = -1;
		}
	}

	/**
	 * Routine of enemy
	 * @throws IOException
	*/
	public void EnemyRoutine() throws IOException
	{
		if (dir.y == -1)
			setSpriteArray(frontSpriteArray);
		else 
			setSpriteArray(backSpriteArray);
		NextSprite();
	}
	
}