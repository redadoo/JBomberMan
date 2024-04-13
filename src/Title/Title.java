package src.Title;

import java.awt.image.BufferedImage;

import src.Manager.TitleManager.TitleType;
import src.lib.Collider;
import src.lib.Vector2;

/**
 * Class Title that represents a map title with properties such as position, size, collision status, and sprite
 * It is used to define tiles on the game map
 */
public class Title 
{

    // Integer representing the map title. 
	public int				mapTitle;

	// Vector2 representing the position of the title.
	public Vector2			pos;

	// Vector2 representing the position of the title.
	public Vector2			matrixPos;

    // Vector2 representing the size of the title. 
	public Vector2			size;

	// Boolean indicating whether the title has collision.
	public boolean			collision;

    // BufferedImage representing the sprite image of the title.
	public BufferedImage	sprite;

	//Collider representing the Collider area of the title.
	public Collider			coll;

	public TitleType		titleType;

	/**
	 * Costructor class Title that initializes position and size vectors.
	 * @param pos the position to place the title
	 * @param size the size of title
	 */
	public Title(Vector2 pos, Vector2 size)
	{
		this.pos = pos/* new Vector2(pos.x + (size.x / 2), pos.y + (size.y / 2)) */;
		this.size = size;
		coll = new Collider(pos, size.x, size.y);
	}

	/**
	 * Costructor class Title
	 */
	public Title()
	{
		this.pos = new Vector2();
		this.size = new Vector2();
	}
}
