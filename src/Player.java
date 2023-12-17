package src;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import src.lib.Collider;
import src.lib.Vector2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;


public class Player extends Entity
{
	private int				life;
	private int				point;
	public	Vector<Bomb>	bombs;  // container for player's bomb
	private int				numBomb;
	private int				numBombMax;
	private int				moveDistance;

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
		this.numBomb = 1;
		this.numBombMax = 1;		
		
		bombs = new Vector<Bomb>();
		for(int i = 0; i < numBombMax; i++)
			bombs.add(new Bomb());
	}

	@Override
	public void moveEntity(Vector2 newPos) throws IOException 
	{
		this.pos = newPos;
        super.getLabel().setLocation((int)this.pos.x, (int)this.pos.y);
		super.getCollider().pos = newPos;
	}
		
	public int returnPoint() { return this.point; }
	
	public int returnLife() { return this.life; }
		
	public int returnMoveDistance() { return this.moveDistance; }

	public void setNumbBomb(int x) { this.numBomb = x;}

	public int	getNumbBomb() { return this.numBomb;}

	public void setNumbBombMax(int x) { this.numBombMax = x;}

	public int	getNumbBombMax() { return this.numBombMax;}

	public boolean PlaceBomb(GamePanel panel) throws IOException
	{
		for (Bomb bomb : bombs) 
		{
			if (bomb.ReturnStatus() == true)
			{
				bomb.SetPanel(panel);
				bomb.SetStatus(false);
				bomb.placeBomb(new Vector2(super.getPos().x + 2, super.getPos().y + 8), panel);
				return true;
			}
		}
		return false;
	}
}