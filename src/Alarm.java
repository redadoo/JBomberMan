package src;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 *  Classe per gli oggetti "allarmi"
 */
public class Alarm extends Entity
{
	/*
	 * Costruttore della classe Alarm
	 */
	public Alarm (String imagePath, Vector2 pos, Vector2 size) throws IOException
	{
		super(pos, size,imagePath);
		//inizializiamo lo sprite
		super.setLabel(new JLabel());
		File file = new File(imagePath);
		BufferedImage image = ImageIO.read(file);

		// Imposta la posizione
		super.getLabel().setIcon(new ImageIcon(image.getScaledInstance((int)size.x, (int)size.y,  java.awt.Image.SCALE_SMOOTH)));
		super.getLabel().setLocation((int)this.pos.x, (int)this.pos.y);
		/* this.sprite.setSize((int)size.x, (int)size.y); */
		super.getLabel().setLayout(null);
	}
	
}