package Src.lib;

import java.awt.Rectangle;

public class Collider 
{
	public Vector2	pos = new Vector2();
	public Rectangle rec = new Rectangle();
	
	/*
	 * Costruttore per un collider a forma di quadrato conoscendo posizione e grandezze
	 */
	public Collider(Vector2 pos, int width, int height)
	{
		this.pos = pos;
		this.rec.x = pos.x;
		this.rec.y = pos.y;
		this.rec.width = width;
		this.rec.height = height;
	}

	public Collider(Vector2 pos, int width, int height,int x,int y)
	{
		this.pos = pos;
		this.rec.x = x;
		this.rec.y = y;
		this.rec.width = width;
		this.rec.height = height;
	}

	public void setPos(Vector2 pos)
	{
		this.pos = pos;
		this.rec.x = pos.x;
		this.rec.y = pos.y;		
	}
}
