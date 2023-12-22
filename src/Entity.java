package src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import src.lib.Collider;
import src.lib.Vector2;

/* 
 * Class Entity that will be extended by the characters
*/
public abstract class Entity 
{
	public  Vector2         pos;
	private Vector2         size;
	private JLabel	        jlabel;
	public	int             Index = 0;
	private Collider		collider;
	private BufferedImage	StartSprite;
	private String			ImagePath;
	private String          []SpriteArray;
	private String          []effectArray;

	/*
	 * Entity costructor
	 */
	public Entity(Vector2 pos, Vector2 size, String SpritePath) throws IOException 
	{
		this.pos = pos;
		this.size = size;
		this.jlabel = new JLabel();
		this.ImagePath = SpritePath;
		
		File file = new File(SpritePath);
		this.StartSprite = ImageIO.read(file);
		this.jlabel.setIcon(new ImageIcon(this.StartSprite.getScaledInstance((int)this.size.x, (int)size.y,  java.awt.Image.SCALE_SMOOTH)));
		this.jlabel.setLocation((int)this.pos.x, (int)this.pos.y);
		this.jlabel.setLayout(null);
	}

	/*
	 *  Get image path
	 */
	public String GetImagePath () {return this.ImagePath; }

	/*
	 *  Set laber of the Entity
	 */
	public void	setLabel(JLabel jl) { this.jlabel = jl; }

	/*
	 *  Init the effect of the Entity
	 */
	public void InitEffectArray(String []effect) { effectArray = effect; }

	/*
	 *  ?
	 */
	public void InitSpriteArray(String []sprite) { SpriteArray = sprite; }

	/*
	 *  Obtain all sprite of Entity
	 */
	public String [] getSpriteArray() {return SpriteArray;}

	/*
	 *  Get the effect of Entity
	 */
	public String [] getEffectArray() {return effectArray;}
	
	/*
	 *  Get the size of Entity
	 */
	public Vector2 getSize() {return this.size;}

	/*
	 *  Get the position of Entity
	 */
	public Vector2 getPos() {return this.pos;}
 
	/*
	 *  Get the label of Entity
	 */
	public JLabel getLabel() { return this.jlabel; }
	
	/*
	 *  Set the position of Entity
	 */
	public void setPos(Vector2 v) { this.pos = v; }
	
	/*
	 *  Set the size of Entity
	 */
	public void setSize(Vector2 v) { this.size = v; }
	
	/*
	 *  Get the collider of Entity
	 */
	public Collider getCollider() { return this.collider;}

	/*
	 *  Get the image of Entity
	 */
	public BufferedImage GetImage() {return this.StartSprite;}

	/*
	 *  Set the Collider of Entity
	 */
	public void setCollider(Vector2 pos, float xWidth, float yHeight)
	{
		this.collider = new Collider(pos, xWidth, yHeight);
	}

	/*
	 *  Change sprite of Entity
	 */
	public void changeSpirte(String imagePath) throws IOException
	{
		File file = new File(imagePath);
		BufferedImage image = ImageIO.read(file);
		if (this.jlabel != null)
			this.jlabel.setIcon(new ImageIcon(image.getScaledInstance((int)this.size.x, (int)size.y,  java.awt.Image.SCALE_SMOOTH)));
	}
	
	/*
	 *  Move the Entity
	 */
	public void moveEntity(Vector2 pos) throws IOException
	{
		this.pos = pos;
		this.jlabel.setLocation((int)this.pos.x, (int)this.pos.y);
	}

	/*
	 *  Obtain the next sprite of the Entity
	 */
	public void NextSprite() throws IOException
	{
		if (Index > SpriteArray.length - 1) Index = 0;
		changeSpirte(SpriteArray[Index]);
		Index++;
	}

	/*
	 *  Obtain the next effect of the Entity
	 */
	public void NextEffect() throws IOException
	{
		if (Index > effectArray.length - 1) Index = 0;
		changeSpirte(SpriteArray[Index]);
		Index++;
	}

}
