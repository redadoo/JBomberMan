package src;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import src.lib.Collider;
import src.lib.Vector2;

import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * Class to manage the Map
 */
public class Map {

	private Vector2			pos;
	private Vector2			size;
	private JLabel			label;
	private String			imagePath;
	private	BufferedImage	buffImage;
	public	Collider[]		mapCollider = {	
										// 1° riga
											new Collider(new Vector2(82 ,-78), 30,2),
											new Collider(new Vector2(145,-78), 30,2),
											new Collider(new Vector2(208,-78), 32,2),
											new Collider(new Vector2(275,-78), 27,2),
 											new Collider(new Vector2(338,-78), 27,2),
											new Collider(new Vector2(402,-78), 30,2),
										// 2° riga
										/* 
											new Collider(new Vector2(82 ,-39), 30,2),
											new Collider(new Vector2(145,-39), 30,2),
											new Collider(new Vector2(208,-39), 32,2),
											new Collider(new Vector2(275,-39), 27,2),
 											new Collider(new Vector2(338,-39), 27,2),
											new Collider(new Vector2(402,-39), 30,2),
										 */
										// 3° riga
										/*
											new Collider(new Vector2(82 ,-12), 30,2),
											new Collider(new Vector2(145,-12), 30,2),
											new Collider(new Vector2(208,-12), 32,2),
											new Collider(new Vector2(275,-12), 27,2),
 											new Collider(new Vector2(338,-12), 27,2),
											new Collider(new Vector2(402,-12), 30,2),										
										*/
										// 4° riga
										/*
											new Collider(new Vector2(82 ,-12), 30,2),
											new Collider(new Vector2(145,-12), 30,2),
											new Collider(new Vector2(208,-12), 32,2),
											new Collider(new Vector2(275,-12), 27,2),
 											new Collider(new Vector2(338,-12), 27,2),
											new Collider(new Vector2(402,-12), 30,2),										
										*/

										};

	/*
	 * Costructor class Map
	 */
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

		buffImage =  resizedImage;
		this.label.setIcon(new ImageIcon(resizedImage));

        this.label.setSize((int)size.x, (int)size.y);
        this.label.setLocation((int)this.pos.x, (int)this.pos.y);
		this.label.setLayout(new FlowLayout()); 
/* 		this.label. */
    }

	/*
	 * Get the position of the Map
	 */
    public Vector2 getPos() { return this.pos; }

	/*
	 *  Get the label
	 */
	public JLabel returnLabel() { return this.label; }

	/*
	 * Chekc if we use it
	 */
	public void moveEntity(Vector2 newPos) 
	{
 		this.pos = newPos;
        this.label.setLocation((int)this.pos.x, (int)this.pos.y);
	}

	/*
	 * Get collider of the Map
	 */
	public Collider[] returnCollider()
	{
		return mapCollider;
	}	
}
