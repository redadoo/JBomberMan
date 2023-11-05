package src;

class boxCollider
{
	Vector2 pos;
	Vector2 size;
}

public class Collider 
{

	boxCollider box;

	public Collider()
	{
		box = new boxCollider();
	}
	//controllo che due collider non si sovrappongano
	static Boolean checkCollideBoxes(boxCollider a, boxCollider b)
	{
		if (a.pos.x + a.size.x > b.pos.x && a.pos.x < b.pos.x + b.size.x)
			return true;
		if (a.pos.y + a.size.y > b.pos.y && a.pos.y < b.pos.y + b.size.y)
			return true;
		return false;
	}
}
