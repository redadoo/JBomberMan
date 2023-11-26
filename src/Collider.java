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

		boolean coll1 = false;
		boolean coll2 = false;
		boolean coll3 = false;
		boolean coll4 = false;
		boolean coll5 = false;


		boxCollider a = colliderA.returnBoxCollider();
		boxCollider b = colliderB.returnBoxCollider();
		
		/* -x +y */
		aBottomLeft = new Vector2(a.pos.x - (a.xSize / 2),a.pos.y + (a.ySize / 2));
		bBottomLeft = new Vector2(b.pos.x - (b.xSize / 2), b.pos.y + (b.ySize / 2));
		/* +x -y */
		aTopRight = new Vector2(a.pos.x + (a.xSize / 2),a.pos.y - (a.ySize / 2));
		bTopRight = new Vector2(a.pos.x + (a.xSize / 2),a.pos.y - (a.ySize / 2));

/* 		System.err.println("player info : ");
		Print(aBottomLeft);
		Print(aTopRight);
		System.err.println("collider  info : ");
		Print(bBottomLeft);
		Print(bTopRight);
		//Casi in cui non collidono
		if (aBottomLeft.x == bBottomLeft.x && aTopRight.x == bTopRight.x)
		{
			coll5 = true;
		}
		if (aBottomLeft.y <= bTopRight.y)
		{
			coll1 = true;
		}
		if (bBottomLeft.y <= aTopRight.y)
		{
			coll2 = true;
		}
		// x
		if (aBottomLeft.x >= bBottomLeft.x && aTopRight.x >= bBottomLeft.x)
		{
			coll3 = true;
		}
		if (aBottomLeft.x <= bTopRight.x && aTopRight.x <= bTopRight.x)
		{
			coll4 = true;
		}
		return (coll5 && coll4 && coll3 && coll2 && coll1); */
		if (a.pos.x + a.xSize >= b.pos.x && a.pos.x <= b.pos.x + b.xSize && a.pos.y + a.ySize >= b.pos.y && a.pos.y <= b.pos.y + b.ySize)
    		return true;
		return false;
	}
	
	private static void Print(Vector2 pos) {
		System.out.println(" x :   ");
		System.out.println(String.valueOf(pos.x));
		System.out.println(" y :   ");
		System.out.println(String.valueOf(pos.y));
	}
}
