package src;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Player extends Entity
{
	private Collider	collider;
	private int			life;
	private int			point;
	private Vector2		pos;
	private Vector2		size;
	private String		imagePath;
	private int			moveDistance;


	//Funzione di testing
	static public void Print(String x)
	{
		System.out.print(x);
	}

	static public void PrintPos(Vector2 pos)
	{
		Print(" x :   ");
		Print(String.valueOf(pos.x));
		Print(" y :   ");
		Print(String.valueOf(pos.y));
		Print("\n");
	}

    /*
     *  Costruttore della classe Player
     */
	public Player( String imagePath, Vector2 pos, Vector2 size) throws IOException 
	{
		super(pos, size, imagePath);
		this.pos = pos;
		
		this.size = size;
		
		super.setLabel(new JLabel());
		
	
		File file = new File(imagePath);
		BufferedImage image = ImageIO.read(file);

		super.getLabel().setIcon(new ImageIcon(image.getScaledInstance((int)size.x, (int)size.y,  java.awt.Image.SCALE_SMOOTH)));

        super.getLabel().setLocation((int)this.pos.x, (int)this.pos.y);
		super.getLabel().setLayout(null);

		this.life = 5;
		this.point = 0;

		this.moveDistance = 5;
		this.collider = new Collider(this.pos,2,2);
		this.moveEntity(this.getPos());	
	}

	@Override
	public void moveEntity(Vector2 newPos) throws IOException 
	{
		if (newPos.x > this.getPos().x) this.changeSpirte("src/Resource/PlayerSprite/PlayerRight.png");
		if (newPos.x < this.getPos().x) this.changeSpirte("src/Resource/PlayerSprite/PlayerLeft.png");
		if (newPos.y < this.getPos().y) this.changeSpirte("src/Resource/PlayerSprite/PlayerBack.png");
		if (newPos.y > this.getPos().y) this.changeSpirte("src/Resource/PlayerSprite/PlayerFront.png");

 		this.pos = newPos;
        super.getLabel().setLocation((int)this.pos.x, (int)this.pos.y);
		this.collider.box.pos = newPos;
	}

	//Ritorno la taglia del collider del player
	public Vector2 getSize()
	{
		Vector2 size = new Vector2();

		size.x = this.collider.box.xSize;
		size.y = this.collider.box.ySize;

		return this.size;
	}
	
	public Vector2 getPos() { return this.pos; }
	
	public int returnPoint() { return this.point; }
	
	public int returnLife() { return this.life; }
		
	public int returnMoveDistance() { return this.moveDistance; }

	public Collider getCollider() { return this.collider; }


}