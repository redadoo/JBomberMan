package src;

/*
 * Classe per i collider a forma di quadrato
 */
class boxCollider
{
	Vector2 pos;
	float	xSize;
	float	ySize;
}

/*
 * Classe per i collider a forma di cerchio
 */
class circleCollider
{
	Vector2 pos;
	float	radius;
}

public class Collider 
{
	boxCollider		box;
	circleCollider	circle;
	
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

	/*
	 * Costruttore per un collider a forma di cerchio
	 */
	public Collider(Vector2 pos, float radius)
	{
		circle = new circleCollider();
		this.circle.pos = pos;
		this.circle.radius = radius;
	}

	public boxCollider returnBoxCollider()
	{
		return this.box;
	}


	static public void PrintPos(Vector2 pos)
	{
		System.out.println(" x :   ");
		System.out.println(String.valueOf(pos.x));
		System.out.println(" y :   ");
		System.out.println(String.valueOf(pos.y));
	}

	//controllo che due collider non si sovrappongano
	public static Boolean checkCollideBoxes(Collider colliderA, Collider colliderB)
	{
		//Se a è dentro b
		Vector2 bTopRight;
		Vector2 aTopRight;
		Vector2 aBottomLeft;
		Vector2 bBottomLeft;

		boxCollider a = colliderA.returnBoxCollider();
		boxCollider b = colliderB.returnBoxCollider();
		
		/* -x +y */
		aBottomLeft = new Vector2(a.pos.x - (a.xSize / 2),a.pos.y + (a.ySize / 2));
		bBottomLeft = new Vector2(b.pos.x - (b.xSize / 2), b.pos.y + (b.ySize / 2));
		/* +x -y */
		aTopRight = new Vector2(a.pos.x + (a.xSize / 2),a.pos.y - (a.ySize / 2));
		bTopRight = new Vector2(a.pos.x + (a.xSize / 2),a.pos.y - (a.ySize / 2));

		boolean widthIsPositive = Math.min(aTopRight.x, bTopRight.x) > Math.max(aBottomLeft.x, bBottomLeft.x);
        boolean heightIsPositive = Math.min(bTopRight.y, aTopRight.y) > Math.max(aBottomLeft.y, bBottomLeft.y);
        return ( widthIsPositive && heightIsPositive);
	}
}
