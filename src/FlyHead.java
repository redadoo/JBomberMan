package src;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import src.lib.Vector2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/*
 *  Class to manage the first enemy
*/
public class FlyHead extends Entity
{
	private Vector2     dir;
	private int         pointDrop;
	public	boolean		isCollided;
	/*
	 * Costructor class FlyHead
	*/
	public FlyHead(String imagePath, Vector2 pos, Vector2 size) throws IOException
	{
		super(pos, size,imagePath);
		dir = new Vector2();
		isCollided = false;
		this.pointDrop = 100;
	
		File file = new File(imagePath);
		BufferedImage image = ImageIO.read(file);

		super.setCollider(pos,12,12);

		// Set position
		super.getLabel().setIcon(new ImageIcon(image.getScaledInstance((int)size.x, (int)size.y,  java.awt.Image.SCALE_SMOOTH)));    
		
		super.getLabel().setLocation((int)this.pos.x, (int)this.pos.y);
		/* this.sprite.setSize((int)size.x, (int)size.y); */
		super.getLabel().setLayout(null);

		int randomNumber = new Random().nextInt(2) + 1;
		if (randomNumber == 2) randomNumber = -1;
		dir.y = randomNumber;
		dir.x = 0;
	}

	/*
	 *  Remain enemy's life -> ?? 
	*/
	public int getPointDrop() { return pointDrop; }

	public Vector2 getDir() { return dir; }

	public void changeDir()
	{
		if (dir.y == -1)
			dir.y = 1;
		else 
			dir.y = -1;
	}

	public void EnemyRoutine() throws IOException
	{

	}
	
}