package src;

/*
 * Classe per i collider a forma di quadrato
 */
class boxCollider
{
	Vector2 pos = new Vector2();
	float	xSize = 0;
	float	ySize = 0;
}

public class Collider 
{
	boxCollider		box;
	
	/*
	 * Costruttore per un collider a forma di quadrato conoscendo posizione e grandezze
	 */
	public Collider(Vector2 pos, float xSize, float ySize)
	{
		box = new boxCollider();
		this.box.pos = pos;
		this.box.xSize = xSize;
		this.box.ySize = ySize;
	}

	public boxCollider returnBoxCollider()
	{
		return this.box;
	}
}
