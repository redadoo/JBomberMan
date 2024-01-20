package Src.Entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import Src.Main.GamePanel;
import Src.Title.Title;
import Src.lib.Sprite;
import Src.lib.Vector2;

/**
 * Class to manage class Bomb 
*/
public class Bomb extends Entity 
{
	public  GamePanel   			gp;
	public	double					timerExplosion;
	public	BombState				myState;
	public 	Map<Sprite, Vector2>	myExplosionSprite;
	public 	Boolean					isPlayerHover;

	/**
	 * The various states of bomb
	 */
	public static enum BombState
	{
		Available,
		Placed,
		Exploded	
	}

	/** 
	 * Costructor class Bomb
	 * @param gp the GamePanel reference
	 * @param pos the pos to set the entity
	 * @throws IOException
	 */
	public Bomb(GamePanel gp, Vector2 pos) throws IOException
	{        
		super(pos, new Vector2(25,25));
		this.gp = gp;
		spriteList.add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombSprite/BombSprite0.png")));
 		spriteList.add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombSprite/BombSprite1.png")));
 		spriteList.add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombSprite/BombSprite2.png")));

		sprite = spriteList.get(0);
		myExplosionSprite = new HashMap<Sprite, Vector2>();
		myState = BombState.Available;
		timerExplosion = 0;
		isPlayerHover = true;
	}

	/**
	 * This method initializes the explosion animation at specific positions based on the given range of titles
	 * @param rangeTitle the range of titles where the explosion should occur
	 * @param explosionSpriteList the list of sprites used for the explosion animation
	 * @param myTitlePos the position of the title that is causing the explosion
	*/
	public void InitExplosion(ArrayList<Vector2> rangeTitle, ArrayList<Sprite> explosionSpriteList, Vector2 myTitlePos)
	{
		myExplosionSprite.put(explosionSpriteList.get(0),new Vector2(pos.x,pos.y - 5));
		//Place the explosions
		for (Vector2 titlePos :  rangeTitle) 
		{
			// Down
			if (titlePos.y == myTitlePos.y + 1)
				myExplosionSprite.put(explosionSpriteList.get(3), new Vector2(pos.x - 1, pos.y + 20));
			// Up
			if (titlePos.y == myTitlePos.y - 1)
				myExplosionSprite.put(explosionSpriteList.get(4),  new Vector2(pos.x - 1, pos.y - 30));
			// Left
			if (titlePos.x == myTitlePos.x - 1)
				myExplosionSprite.put(explosionSpriteList.get(2), new Vector2(pos.x - 30,pos.y - 5));
			// Right
			if (titlePos.x == myTitlePos.x + 1)
				myExplosionSprite.put(explosionSpriteList.get(1), new Vector2(pos.x + 30, pos.y - 5));
		}
	}

	/**
	 * Methond to change sprite of the bomb
	 */
	@Override
    public void nextSprite()
    {
		if(spriteIndex + 1 < spriteList.size())
		{
			int multiply;
			multiply = spriteIndex * 2;
			if(multiply == 0)
				multiply = 2;
			size = new Vector2(size.x + multiply,size.y + multiply);
			setSprite(spriteList.get(++spriteIndex));
		}
		else
		{
			size = new Vector2(25,25);
			spriteIndex = 0;
		}
	}
	
	/**
	 * Gets the title based on the position and size.
	 * @return the Title object associated with the position and size
	*/
	public Title getTitle() { return gp.mapManager.GetTitleFromPos(coll.pos,size); }

}
