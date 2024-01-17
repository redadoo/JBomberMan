package Src.lib;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Class Sprite to manage the sprite object
*/
public class Sprite 
{

	private int spriteIndex;
	public BufferedImage startSprite;
	public ArrayList<BufferedImage> spriteList;

	/**
	 * Costructor class Sprite
	 * @param spriteList the list of sprite of varoius objects
	 */
	public Sprite(ArrayList<BufferedImage> spriteList) 
	{
		this.spriteList = spriteList;
		this.startSprite = spriteList.get(0);
	}

	/**
	 * Method to change the sprite to the next one
	 */
	public void nextSprite()
	{
		if (spriteIndex + 1 < spriteList.size())
			startSprite = spriteList.get(++spriteIndex);
		else
			spriteIndex = 0;
	}
}
