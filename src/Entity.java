package src;

import javax.swing.JLabel;

/* 
 * Classe che verrà estesa dei personaggi
*/
public abstract class Entity 
{
    public Vector2          pos; // Posizione dell'entità
    private Vector2         size;
    private String          imagePath; // Percorso dello sprite
    private JLabel          sprite;

    public Entity(Vector2 pos, Vector2 size) 
    {
        this.pos = pos;
        this.size = size;
    }

    // Ottieni la posizione x dell'entità
    public Vector2 getPos() {return this.pos;}

    public JLabel getLabel() { return this.sprite; }

    // Imposta la posizione x dell'entità
    public void setPos(Vector2 v) { this.pos = v; }

    public void setSize(Vector2 v) { this.size = v; }

    public String getImagePath() { return this.imagePath; }

    /*
     * Metodo che setta la nuova posiszione del player
     */
    public abstract void moveEntity(Vector2 pos);
}
