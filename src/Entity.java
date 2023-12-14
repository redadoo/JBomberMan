package src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/* 
 * Classe che verrà estesa dai personaggi
*/
public abstract class Entity 
{
	public  Vector2         pos;
	private Vector2         size;
	private JLabel	        jlabel;
	private int             Index = 0;
	private Collider		collider;
	private BufferedImage	StartSprite;
	private String          []SpriteArray;

	public Entity(Vector2 pos, Vector2 size, String SpritePath) throws IOException 
	{
		this.pos = pos;
		this.size = size;
		this.jlabel = new JLabel();

		File file = new File(SpritePath);
		this.StartSprite = ImageIO.read(file);
		this.jlabel.setIcon(new ImageIcon(this.StartSprite.getScaledInstance((int)this.size.x, (int)size.y,  java.awt.Image.SCALE_SMOOTH)));
		this.jlabel.setLocation((int)this.pos.x, (int)this.pos.y);
		this.jlabel.setLayout(null);
	}

	public void	setLabel(JLabel jl) { this.jlabel = jl; }

	public void InitSpriteArray(String []spriteAr) { SpriteArray = spriteAr; }

	// return Size
	public Vector2 getSize() {return this.size;}

	// return pos
	public Vector2 getPos() {return this.pos;}
 
	// return label
	public JLabel getLabel() { return this.jlabel; }
	
	// set pos
	public void setPos(Vector2 v) { this.pos = v; }
	
	// set size
	public void setSize(Vector2 v) { this.size = v; }
	
	public Collider getCollider() { return this.collider;}

	public void setCollider(Vector2 pos, float xWidth, float yHeight)
	{
		this.collider = new Collider(pos, xWidth, yHeight);
	}

	// change sprite
	public void changeSpirte(String imagePath) throws IOException
	{
		File file = new File(imagePath);
		BufferedImage image = ImageIO.read(file);
		this.jlabel.setIcon(new ImageIcon(image.getScaledInstance((int)this.size.x, (int)size.y,  java.awt.Image.SCALE_SMOOTH)));
	}
	
	public void moveEntity(Vector2 pos) throws IOException
	{
		this.pos = pos;
		this.jlabel.setLocation((int)this.pos.x, (int)this.pos.y);
	}

	public void NextSprite() throws IOException
	{
		if (Index > SpriteArray.length - 1) Index = 0;
		changeSpirte(SpriteArray[Index]);
		Index++;
	}

	//move entity
}
