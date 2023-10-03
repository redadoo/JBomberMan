package src;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javafx.geometry.Pos;

/* 
 * Classe che verrà estesa dal player e i nemici
*/
public abstract class Entity 
{
    private Vector2         pos; // Posizione dell'entità
    private Vector2         size;
    private String          imagePath; // Percorso dello sprite
    private BufferedImage   currentSprite;

    public Entity(String imagePath, Vector2 pos, Vector2 size) 
    {
        this.imagePath = imagePath;
        this.pos = pos;
        this.size = size;
        //currentSprite = new BufferedImage(size.x,size.y,);
    }

    // Ottieni la posizione x dell'entità
    public float getX() { return this.pos.x; }

    // Ottieni la posizione y dell'entità
    public float getY() { return this.pos.y; }

    public Image getImage() { return this.currentSprite; }

    // Imposta la posizione x dell'entità
    public void setPos(Vector2 v) { this.pos = v; }

    public void setSize(Vector2 v) { this.size = v; }

    public String getImagePath() { return this.imagePath; }

    // Metodo astratto per l'aggiornamento dell'entità
/*     public abstract void update();
 */
    // Metodo astratto per il disegno dell'entità
/*     public abstract void draw(Graphics2D g);
 */
}
