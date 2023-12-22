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
											new Collider(new Vector2(208 ,-120), 30,2),

											new Collider(new Vector2(82 ,-78), 30,2),
											new Collider(new Vector2(145,-78), 30,2),
											new Collider(new Vector2(208,-78), 32,2),
											new Collider(new Vector2(275,-78), 27,2),
 											new Collider(new Vector2(338,-78), 27,2),
											new Collider(new Vector2(402,-78), 30,2),
										// 2° riga
										
											new Collider(new Vector2(82 ,-24), 30,2),
											new Collider(new Vector2(145,-24), 30,2),
											new Collider(new Vector2(208,-24), 32,2),
											new Collider(new Vector2(275,-24), 27,2),
 											new Collider(new Vector2(338,-24), 27,2),
											new Collider(new Vector2(402,-24), 30,2),

										// 2/3° riga

											new Collider(new Vector2(402,-6), 30,2),

										
										// 3° riga
										
											new Collider(new Vector2(82 ,29), 30,2),
											new Collider(new Vector2(145,29), 30,2),
											new Collider(new Vector2(208,29), 32,2),
											new Collider(new Vector2(275,29), 27,2),
 											new Collider(new Vector2(338,29), 27,2),
											new Collider(new Vector2(305,29), 27,2),
											new Collider(new Vector2(402,29), 30,2),	
											
										// 3/2° riga

											new Collider(new Vector2(275,48), 27,2),
 											new Collider(new Vector2(338,48), 27,2),
										
										// 4° riga
										
											new Collider(new Vector2(82 ,83), 30,2),
											new Collider(new Vector2(145,83), 30,2),
											new Collider(new Vector2(208,83), 32,2),
											new Collider(new Vector2(275,83), 27,2),
 											new Collider(new Vector2(338,83), 27,2),
											new Collider(new Vector2(402,83), 30,2),	

										// 4/5° riga

											new Collider(new Vector2(434,114), 30,2),
											
										// 5° riga
										
											new Collider(new Vector2(82 ,140), 30,2),
											new Collider(new Vector2(145,140), 30,2),
											new Collider(new Vector2(208,140), 32,2),
											new Collider(new Vector2(275,140), 27,2),
 											new Collider(new Vector2(338,140), 27,2),
											new Collider(new Vector2(402,140), 30,2),

										// 6° riga
										
											new Collider(new Vector2(145,160), 30,2),
											new Collider(new Vector2(208,160), 32,2),
											new Collider(new Vector2(275,160), 27,2),

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
