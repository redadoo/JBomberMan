package Src.Entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Src.Main.GamePanel;
import Src.lib.Vector2;

/**
 * Class to manage class Bomb 
 */
public class Bomb extends Entity 
{
	public  GamePanel   gp;
	private boolean     Isavailable;
	public  int         TimerExplosion = 0;
	
	/** 
	 * Costructor class Bomb
	 * @throws IOException
	 */
	public Bomb(GamePanel gp, Vector2 pos) throws IOException
	{        
		super(pos, new Vector2(25,25));
		spriteVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombSprite/BombSprite0.png")));
 		spriteVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombSprite/BombSprite1.png")));
 		spriteVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombSprite/BombSprite2.png")));
		sprite = spriteVector.elementAt(0);
	}

	/**
	* Function to place the bomb
	* @param pos the position to place the bomb
	* @param panel the panel where place the bomb
	* @throws IOException
	*/
/* 	public void placeBomb(Vector2 pos, GamePanel panel) throws IOException
	{
		
	} */

	/**
	 * Handle the explosion
	 * @throws IOException
	 */
	public void Explosion()
	{
/* 		super.setSize(new Vector2(25,25));
		gamePanel.panel.remove(getLabel());
		Isavailable = true;
		TimerExplosion = 0;
		Index = 0; */
	}

	/**
	 *  Set the status of bomb
	 * @param status to set the visibility of bomb
	 */
	public void SetStatus(Boolean status) {  Isavailable = status; }

	/**
	 *  Get the status of bomb
	 * @return the status of the bomb
	 */
	public Boolean ReturnStatus() { return Isavailable; }

	/**
	 * Function to change the size of the bomb's sprite
	 * @throws IOException
	 */

	@Override
	public void nextSprite()
	{
/* 		super.setSize(new Vector2(getSize().x + Index, getSize().y + Index));
		
		if (super.Index > super.getSpriteArray().length - 1)
		{
			TimerExplosion++;
			super.setSize(new Vector2(25,25));
			Index = 0;
		}
		changeSpirte(super.getSpriteArray()[Index]);
		if (TimerExplosion == 2)
		{
			Explosion();
		}
		Index++; */
	}
}
