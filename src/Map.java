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
	public	Collider[]		mapCollider = {new Collider(new Vector2(70,120),40,20)};

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

	public boolean checkPos(Vector2 pos)
	{
		int rgb = buffImage.getRGB((int)pos.x, (int)pos.y);

		int red = (rgb & 0xff0000) >> 16;
		int green = (rgb & 0xff00) >> 8;
		int blue = rgb & 0xff;

		int alpha = (rgb & 0xff000000) >>> 24;

		System.out.println(red + " " + green + " " + blue);

		return true;
	}

}
