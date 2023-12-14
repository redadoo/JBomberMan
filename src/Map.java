package src;

import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Map {

	private Vector2			pos;
	private Vector2			size;
	private JLabel			label;
	private String			imagePath;
	private	BufferedImage	buffImage;
	public	Collider[]		mapCollider = { new Collider(new Vector2(90,115),12,2)
/* 											new Collider(new Vector2(130,120),40,20),
											new Collider(new Vector2(190,120),40,20),
											new Collider(new Vector2(190,100),40,20),
											new Collider(new Vector2(250,120),40,20),
											new Collider(new Vector2(310,120),40,20),
											new Collider(new Vector2(370,120),40,20),
											//2° riga
											new Collider(new Vector2(70,170),40,20), */
/* 											new Collider(new Vector2(130,170),40,20) */
										};
    public Map(String imagePath, Vector2 pos, Vector2 size) throws IOException
    {
        this.pos = pos;
        this.size = size;
        this.imagePath = imagePath;
        this.label = new JLabel();


		File file = new File(imagePath);
		BufferedImage image = ImageIO.read(file);


		BufferedImage resizedImage = new BufferedImage((int)size.x, (int)size.y, BufferedImage.TYPE_INT_ARGB); 
		Graphics2D g = resizedImage.createGraphics();

		g.drawImage(image, 0, 0, (int)size.x,  (int)size.y, null);
		g.dispose();
		// Imposta la posizione

		buffImage =  resizedImage;
		this.label.setIcon(new ImageIcon(resizedImage));

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
