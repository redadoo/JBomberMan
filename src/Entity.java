package src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import src.lib.Collider;
import src.lib.Vector2;

/** 
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
	public	String			imagePath;
	private String          []SpriteArray;
	private String          []effectArray;

	/**
	 * Entity costructor
	 * @param pos the pos to set the entity
	 * @param size the size for the entity
	 * @param SpritePath the path of all sprites
	 */
	public Entity(Vector2 pos, Vector2 size, String SpritePath) throws IOException 
	{
		this.pos = pos;
		this.size = size;
		this.jlabel = new JLabel();
		this.imagePath = SpritePath;
		
		File file = new File(SpritePath);
		this.StartSprite = ImageIO.read(file);
		this.jlabel.setIcon(new ImageIcon(this.StartSprite.getScaledInstance((int)this.size.x, (int)size.y,  java.awt.Image.SCALE_SMOOTH)));
		this.jlabel.setLocation((int)this.pos.x, (int)this.pos.y);
		this.jlabel.setLayout(null);
	}

	/**
	 *  Get image path
	 * @return the image path 
	 */
	public String GetImagePath () {return this.imagePath; }

	/**
	 * Set laber of the Entity
	 * @param jl the label of the entity
	 */
	public void	setLabel(JLabel jl) { this.jlabel = jl; }

	/**
	 *  Init the effect of the Entity
	 */
	public void InitEffectArray(String []effect) { effectArray = effect; }

	/**
	 * Init all the sprites of entity
	 * @param sprite to init the entity with all the sprites
	 */
	public void InitSpriteArray(String []sprite) { SpriteArray = sprite; }

	/**
	 * Get all sprite of Entity
	 * @return the array that contain all the sprite of entity
	 */
	public String [] getSpriteArray() {return SpriteArray;}

	/**
	 * Get the effect of Entity
	 * @return the effect of the entity (ex: the explosion)
	 */
	public String [] getEffectArray() {return effectArray;}
	
	/**
	 * Get the size of Entity
	 * @return the size of entity
	 */
	public Vector2 getSize() {return this.size;}

	/**
	 * Get the position of Entity
	 * @return the position if entity
	 */
	public Vector2 getPos() {return this.pos;}
 
	/**
	 * Get the label of Entity
	 * @return the label of entity
	 */
	public JLabel getLabel() { return this.jlabel; }
	
	/**
	 * Set the position of Entity
	 * @param pos to position of entity
	 */
	public void setPos(Vector2 pos) { this.pos = pos; }
	
	/**
	 * Set the size of Entity
	 * @param size to set the size of entity
	 */
	public void setSize(Vector2 size) { this.size = size; }
	
	/**
	 * Set all sprite of Entity
	 * @param array to set all the sprite of entity
	 */
	public void setSpriteArray(String[] array) {SpriteArray = array;}


	/**
	 * Get the collider of Entity
	 * @return the collider of entity
	 */
	public Collider getCollider() { return this.collider;}

	/**
	 * Get the image of Entity
	 * @return the initial sprite of entity
	 */
	public BufferedImage GetImage() {return this.StartSprite;}

	/**
	 * Set the Collider of Entity
	 * @param pos the position of collider
	 * @param xWidth the width of entity
	 * @param yHeight the height of entity
	 */
	public void setCollider(Vector2 pos, float xWidth, float yHeight)
	{
		this.collider = new Collider(pos, xWidth, yHeight);
	}

	/**
	 * Change sprite of Entity
	 * @param imagePath the path of sprites
	 * @throws IOException
	 */
	public void changeSpirte(String imagePath) throws IOException
	{
		File file = new File(imagePath);
		BufferedImage image = ImageIO.read(file);
		if (this.jlabel != null)
			this.jlabel.setIcon(new ImageIcon(image.getScaledInstance((int)this.size.x, (int)size.y,  java.awt.Image.SCALE_SMOOTH)));
	}
	
	/**
	 * Move the Entity
	 * @param pos the poistion of entity
	 * @throws IOException
	 */
	public void moveEntity(Vector2 pos) throws IOException
	{
		this.pos = pos;
		this.jlabel.setLocation((int)this.pos.x, (int)this.pos.y);
	}

	/**
	 * Get the next sprite of the Entity
	 * @throws IOException
	 */
	public void NextSprite() throws IOException
	{
		if (Index > SpriteArray.length - 1) Index = 0;
		changeSpirte(SpriteArray[Index]);
		Index++;
	}

	/**
	 * Get the next effect of the Entity
	 * @throws IOException
	 */
	public void NextEffect() throws IOException
	{
		if (Index > effectArray.length - 1) Index = 0;
		changeSpirte(SpriteArray[Index]);
		Index++;
	}

}
