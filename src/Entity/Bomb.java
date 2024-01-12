package Src.Entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import Src.Main.GamePanel;
import Src.lib.Vector2;

/**
 * Class to manage class Bomb 
 */
public class Bomb extends Entity 
{
	public  GamePanel   						gp;
	public	double								timerExplosion;
	public	BombState							myState;
	public 	Map<BufferedImage, Vector2>			myExplosionSprite;

	// The various state of bomb
	public static enum BombState
	{
		Available,
		NotAvailable,
		Exploded	
	}

	/** 
	 * Costructor class Bomb
	 * @throws IOException
	 */
	public Bomb(GamePanel gp, Vector2 pos) throws IOException
	{        
		super(pos, new Vector2(25,25));
		this.gp = gp;
		spriteVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombSprite/BombSprite0.png")));
 		spriteVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombSprite/BombSprite1.png")));
 		spriteVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombSprite/BombSprite2.png")));

		sprite = spriteVector.elementAt(0);
		myExplosionSprite = new HashMap<BufferedImage,Vector2>();
		myState = BombState.Available;
		timerExplosion = 0;
	}

	/**
	 * Handle the explosion
	 * @throws IOException
	 */
	public void InitExplosion(ArrayList<Vector2> rangeTitle, ArrayList<BufferedImage> explosionSpriteList, Vector2 myTitlePos)
	{
		myExplosionSprite.put(explosionSpriteList.get(0), this.pos);

		myTitlePos.PrintPos();

		for (Vector2 titlePos :  rangeTitle) {

			Vector2 newTitlePos = gp.mapManager.returnPosFromIndex(titlePos);
			// Down
			if (titlePos.y == myTitlePos.y - 1)
				myExplosionSprite.put(explosionSpriteList.get(2), new Vector2(newTitlePos.x - 1,newTitlePos.y + 5));
			// Up
			if (titlePos.y == myTitlePos.y + 1)
				myExplosionSprite.put(explosionSpriteList.get(1), gp.mapManager.returnPosFromIndex(titlePos));
			// Right
			if (titlePos.x == myTitlePos.x - 1)
				myExplosionSprite.put(explosionSpriteList.get(3), gp.mapManager.returnPosFromIndex(titlePos));
			// Left
			if (titlePos.x == myTitlePos.x + 1)
				myExplosionSprite.put(explosionSpriteList.get(4), gp.mapManager.returnPosFromIndex(titlePos));
		}
	}

	/**
	 * Function to change sprite of the bomb
	 */
	@Override
    public void nextSprite()
    {
		if(spriteIndex + 1 < spriteVector.size())
		{
			int multiply;
			multiply = spriteIndex * 2;
			if(multiply == 0)
				multiply = 2;
			size = new Vector2(size.x + multiply,size.y + multiply);
			setSprite(spriteVector.elementAt(++spriteIndex));
		}
		else
		{
			size = new Vector2(25,25);
			spriteIndex = 0;
		}
	}

}
