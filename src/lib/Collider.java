package Src.lib;

import java.awt.Rectangle;

/**
 * Class to create the Collider
 */
public class Collider 
{
	public Vector2	pos = new Vector2();
	public Rectangle rec = new Rectangle();
	
	/**
	 * Costructor class Collider
	 * @param pos
	 * @param width
	 * @param height
	 */
	public Collider(Vector2 pos, int width, int height)
	{
		this.pos = pos;
		this.rec.x = pos.x;
		this.rec.y = pos.y;
		this.rec.width = width;
		this.rec.height = height;
	}

	/**
	 * Costructor class Collider
	 * @param pos
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 */
		public Collider(Vector2 pos, int width, int height,int x,int y)
	{
		this.pos = pos;
		this.rec.x = x;
		this.rec.y = y;
		this.rec.width = width;
		this.rec.height = height;
	}

	/**
	 * Function to set position of collider
	 * @param pos
	 */
	public void setPos(Vector2 pos)
	{
		this.pos = pos;
		this.rec.x = pos.x;
		this.rec.y = pos.y;		
	}
}
