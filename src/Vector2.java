package src;

/**
 *  Classe per la gestione della posizione del personaggio
 */
public class Vector2
{              
    // Members
    public float x;
    public float y;
       
    // Constructors
    public Vector2() 
    {
        this.x = 0.0f;
        this.y = 0.0f;
    }
       
    public Vector2(float x, float y) 
    {
        this.x = x;
        this.y = y;
    }
       
    // Compare two vectors
    public boolean equals(Vector2 other) 
    {
        return (this.x == other.x && this.y == other.y);
    }

    public Vector2 dir(Vector2 v1, Vector2 v2) 
    {
        Vector2 dir = new Vector2();

        dir.x = v1.x - v2.x;
        dir.y = v1.y - v2.y;

        return (dir);
    }
}