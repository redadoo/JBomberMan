package Src.lib;

/**
 * Class to manage double object
 */
public class Vector2
{              
	public int x;
	public int y;
	
	public static Vector2 zero = new Vector2();

	/**
	 * Costructor class Vector2
	 */
	public Vector2() 
	{
		this.x = 0;
		this.y = 0;
	}
	  
	/**
	 * Costructor class Vector2
	 * @param x
	 * @param y
	 */
	public Vector2(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Method to compare two vectors
	 * @param other
	 * @return
	 */
	public boolean equals(Vector2 other) 
	{
		return (this.x == other.x && this.y == other.y);
	}

	/**
	 * Method to return difference
	 * @param v1 first vector
	 * @param v2 second vector
	 * @return the diffrence
	 */
	public Vector2 dir(Vector2 v1, Vector2 v2) 
	{
		Vector2 dir = new Vector2();

		dir.x = v1.x - v2.x;
		dir.y = v1.y - v2.y;

		return (dir);
	}

	/**
	 * Testing Method
	 */
	public void PrintPos()
	{
		System.out.print(" x :   ");
		System.out.print(String.valueOf(this.x));
		System.out.print(" y :   ");
		System.out.println(String.valueOf(this.y));
	}
}