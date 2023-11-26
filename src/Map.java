package src;

import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Map {

	private Vector2		pos;
	private Vector2		size;
	private JLabel		label;
	private String		imagePath;
	public	Collider[]	mapCollider = {new Collider(new Vector2(70,120),40,20)};

    public Map(String imagePath, Vector2 pos, Vector2 size)
    {
        this.pos = pos;
        this.size = size;
        this.imagePath = imagePath;
        this.label = new JLabel();

        ImageIcon mapIcon = new ImageIcon(imagePath);
		
		// Ottengo l'immagine dello sprite scalata
		java.awt.Image newimg = mapIcon.getImage().getScaledInstance((int)size.x, (int)size.y,  java.awt.Image.SCALE_SMOOTH);
		 
		// Riportiamo a ImageIcon
		ImageIcon newImageIcon = new ImageIcon(newimg);
		
		// Imposta la posizione
        this.label.setIcon(newImageIcon);

        this.label.setSize((int)size.x, (int)size.y);
        this.label.setLocation((int)this.pos.x, (int)this.pos.y);
/* 		this.label. */
    }

    public Vector2 getPos() { return this.pos; }

	public JLabel returnLabel() { return this.label; }

	public void moveEntity(Vector2 newPos) 
	{
 		this.pos = newPos;
        this.label.setLocation((int)this.pos.x, (int)this.pos.y);
	}

	public Collider[] returnCollider()
	{
		return mapCollider;
	}

}
