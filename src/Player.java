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

/*
 * Class to manage class Player
 */
public class Player extends Entity
{
	private int				life;
	private int				point;
	public	Vector<Bomb>	bombs;  // container for player's bomb
	public	String[]		RightArray; 
	public	String[]		LeftArray; 
	public	String[]		FrontArray; 
	public	String[]		BackArray; 
	private int				numBomb;
	private int				numBombMax;
	private int				moveDistance;

	//Funzioni di testing
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
	*  Costructor class Player
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
		this.moveDistance = 3;
		this.numBomb = 1;
		this.numBombMax = 1;		
		
		bombs = new Vector<Bomb>();
		for(int i = 0; i < numBombMax; i++)
			bombs.add(new Bomb());
		
		RightArray = new String[]{	"src/Resource/Player/RightSprite/PlayerRight_0.png",
									"src/Resource/Player/RightSprite/PlayerRight_1.png",
									"src/Resource/Player/RightSprite/PlayerRight_2.png",
									"src/Resource/Player/RightSprite/PlayerRight_1.png"};
		
		LeftArray = new String[]{	"src/Resource/Player/LeftSprite/PlayerLeft_0.png",
									"src/Resource/Player/LeftSprite/PlayerLeft_1.png",
									"src/Resource/Player/LeftSprite/PlayerLeft_2.png",
									"src/Resource/Player/LeftSprite/PlayerLeft_1.png",};
		
		FrontArray = new String[]{	"src/Resource/Player/FrontSprite/PlayerFront_0.png",
									"src/Resource/Player/FrontSprite/PlayerFront_1.png",
									"src/Resource/Player/FrontSprite/PlayerFront_2.png",
									"src/Resource/Player/FrontSprite/PlayerFront_1.png"};
									
		BackArray = new String[]{	"src/Resource/Player/BackSprite/PlayerBack_0.png",
									"src/Resource/Player/BackSprite/PlayerBack_1.png",
									"src/Resource/Player/BackSprite/PlayerBack_2.png",
									"src/Resource/Player/BackSprite/PlayerBack_1.png"};
	}

	/*
	 *  Move the Entity
	*/
	@Override
	public void moveEntity(Vector2 newPos) throws IOException 
	{
/* 		if (this.pos != newPos)
			newPos.PrintPos(); */
		this.pos = newPos;
        super.getLabel().setLocation((int)this.pos.x, (int)this.pos.y);
		super.getCollider().pos = newPos;
	}

	
	public void walkSprite(int dir, int index) throws IOException
	{
		if (index == 4) index = 0;
		if (dir == 0)
		{
			changeSpirte(RightArray[index]);
		}
		else if (dir == 1)
		{	
			changeSpirte(BackArray[index]);
		}
		else if (dir == 2)
		{
			changeSpirte(LeftArray[index]);
		}
		else if (dir == 3)
		{
			changeSpirte(FrontArray[index]);
		}
	}
	
	/*
	 *  Get the point of Player
	 */
	public int returnPoint() { return this.point; }
	
	/*
	 *  Get the life of Player
	 */
	public int returnLife() { return this.life; }
	
	/*
	 *  Get the distance of Player's moviment
	 */
	public int returnMoveDistance() { return this.moveDistance; }

	/*
	 *  Set the number of bomb
	 */
	public void setNumbBomb(int x) { this.numBomb = x;}

	/*
	 *  Get the number of bomb
	 */
	public int	getNumbBomb() { return this.numBomb;}

	/*
	 *  Set the max number of bomb
	 */
	public void setNumbBombMax(int x) { this.numBombMax = x;}

	/*
	 *  Get the max number of bomb
	 */
	public int	getNumbBombMax() { return this.numBombMax;}

	/*
	 *  Place the bomb
	 */
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