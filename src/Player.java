package src;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Player extends Entity
{
	private int			life;
	private int			point;
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
		super.setLabel(new JLabel());
	
		File file = new File(imagePath);
		BufferedImage image = ImageIO.read(file);
		super.getLabel().setIcon(new ImageIcon(image.getScaledInstance((int)size.x, (int)size.y,  java.awt.Image.SCALE_SMOOTH)));
		super.getLabel().setLocation((int)this.pos.x, (int)this.pos.y);
		super.getLabel().setLayout(null);
		super.setCollider(pos,12,12);
		this.life = 5;
		this.point = 0;
		this.moveDistance = 5;
	}

	@Override
	public void moveEntity(Vector2 newPos) throws IOException 
	{
		this.pos = newPos;
        super.getLabel().setLocation((int)this.pos.x, (int)this.pos.y);
		super.getCollider().box.pos = newPos;
	}
		
	public int returnPoint() { return this.point; }
	
	public int returnLife() { return this.life; }
		
	public int returnMoveDistance() { return this.moveDistance; }

}