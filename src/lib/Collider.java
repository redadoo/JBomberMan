package src.lib;

public class Collider 
{
	public Vector2	pos = new Vector2();
	public float	xSize = 0;
	public float	ySize = 0;
	
	/*
	 * Costruttore per un collider a forma di quadrato conoscendo posizione e grandezze
	 */
	public Collider(Vector2 pos, float xSize, float ySize)
	{
		this.pos = pos;
		this.xSize = xSize;
		this.ySize = ySize;
	}
}
