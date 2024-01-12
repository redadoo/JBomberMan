package Src.Title;

import java.awt.image.BufferedImage;

import Src.Title.TitleManager.TitleType;
import Src.lib.Collider;
import Src.lib.Vector2;

/**
 * The Title class represents a map title with properties such as position, size, collision status, and sprite.
 * It is used to define tiles on the game map.
 */
public class Title {

    /** Integer representing the map title. */
	public int				mapTitle;

	/** Vector2 representing the position of the title. */
	public Vector2			pos;

    /** Vector2 representing the size of the title. */
	public Vector2			size;

	/** Boolean indicating whether the title has collision. */
	public boolean			collision;

    /** BufferedImage representing the sprite image of the title. */
	public BufferedImage	sprite;

	/** Collider representing the Collider area of the title. */
	public Collider			coll;

	public TitleType		titleType;
   	/**
     * Default constructor for the Title class.
     * Initializes position and size vectors.
     */
	public Title(Vector2 pos, Vector2 size)
	{
		this.pos = pos;
		this.size = size;
		coll = new Collider(pos, size.x, size.y);
	}

	public Title()
	{
		this.pos = new Vector2();
		this.size = new Vector2();
	}
}
