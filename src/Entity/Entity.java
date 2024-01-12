package Src.Entity;

import java.awt.image.BufferedImage;
import java.util.Vector;

import Src.lib.Collider;
import Src.lib.Vector2;

/**
 * Class to manage the generic type of Entity
 */
public class Entity 
{
	
	public Vector2					dir;
	public Vector2					pos;
	public Vector2					size;
	public Collider					coll;
	public int						speed;
	public BufferedImage			sprite;
	public int						frameCount;
	public int						spriteIndex;
	public Vector<BufferedImage>	spriteVector;
	public boolean					isCollided = false;

	/**
	* Costructor class Entity 
	* @param pos the pos to set the entity
	* @param size the size for the entity
	* @param sprite the sprite
	*/
	public Entity(Vector2 pos, Vector2 size, BufferedImage sprite)
	{
		this.pos = pos;
		this.size = size;
		this.sprite = sprite;
		this.dir = new Vector2();
		this.coll = new Collider(pos, size.x, size.y);
		this.spriteIndex = 0;
		this.frameCount = 0;
		this.speed = 1;
		spriteVector = new Vector<BufferedImage>();
	}

	/**
	 * Costructor class Entity
	 * @param pos
	 * @param size
	 */
	public Entity(Vector2 pos, Vector2 size)
	{
		this.pos = pos;
		this.size = size;
		this.dir = new Vector2();
		this.coll = new Collider(pos, size.x, size.y);
		this.spriteIndex = 0;
		this.speed = 1;
		this.frameCount = 0;
		spriteVector = new Vector<BufferedImage>();
	}

	/**
	 * Function to set the sprite
	 * @param sprite
	 */
	public void setSprite(BufferedImage sprite)
	{
		this.sprite = sprite;
	}

	/**
	 * Function to change the sprites
	 */
	public void nextSprite()
	{
		if(spriteIndex + 1 < spriteVector.size())
		{
			setSprite(spriteVector.elementAt(++spriteIndex));
		}
		else
			spriteIndex = 0;
	}

	
}
