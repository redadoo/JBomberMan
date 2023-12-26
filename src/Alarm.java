package src;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import src.lib.Vector2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Class for the Alarms
 */
public class Alarm extends Entity
{
	/**
	 * Costructor class Alarm
	 * @param SpritePath the path of all sprites
	 * @param pos the pos to set the entity
	 * @param size the size for the entity
	 */
	public Alarm (String imagePath, Vector2 pos, Vector2 size) throws IOException
	{
		super(pos, size,imagePath);
		// Init the sprite
		super.setLabel(new JLabel());
		File file = new File(imagePath);
		BufferedImage image = ImageIO.read(file);

		// Set position
		super.getLabel().setIcon(new ImageIcon(image.getScaledInstance((int)size.x, (int)size.y,  java.awt.Image.SCALE_SMOOTH)));
		super.getLabel().setLocation((int)this.pos.x, (int)this.pos.y);
		/** this.sprite.setSize((int)size.x, (int)size.y); */
		super.getLabel().setLayout(null);
		Vector2 n = new Vector2(pos.x + 4,pos.y + 5);
		super.setCollider(n, 24, 10);
	}
	
}