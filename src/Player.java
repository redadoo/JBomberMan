package src;

import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class Player 
{
	private Collider	collider;
	private int			life;
	private int			point;
	private Vector2		pos;
	private Vector2		size;
	private String		imagePath;
	private JLabel		sprite;
	private int			moveDistance;


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
	public Player( String imagePath, Vector2 pos, Vector2 size)
	{
		this.pos = pos;
		
		this.size = size;
		
        //inizializiamo lo sprite
        this.sprite = new JLabel();
		
		// ? 
		ImageIcon playerIcon = new ImageIcon(imagePath);
		
		// Ottengo l'immagine dello sprite scalata
		java.awt.Image newimg = playerIcon.getImage().getScaledInstance((int)size.x, (int)size.y,  java.awt.Image.SCALE_SMOOTH);
		 
		// Riportiamo a ImageIcon
		ImageIcon newImageIcon = new ImageIcon(newimg);
		
		// Imposta la posizione
        this.sprite.setIcon(newImageIcon);

        this.sprite.setLocation((int)this.pos.x, (int)this.pos.y);
        this.sprite.setSize((int)size.x, (int)size.y);

        // Vita iniziale del player 
		this.life = 5;

        //Punti iniziali del player
		this.point = 0;

		//Lunghezza passo del player
		this.moveDistance = 10;
		this.collider = new Collider(this.pos,4,4);
	}

	public void changeSpirte(String imagePath) 
	{
		ImageIcon playerIcon = new ImageIcon(imagePath);
		
		// Ottengo l'immagine dello sprite scalata
		java.awt.Image newimg = playerIcon.getImage().getScaledInstance((int)size.x, (int)size.y,  java.awt.Image.SCALE_SMOOTH);
		 
		// Riportiamo a ImageIcon
		ImageIcon newImageIcon = new ImageIcon(newimg);
		
		// Imposta la posizione
        this.sprite.setSize((int)this.size.x, (int)this.size.y);
        this.sprite.setIcon(newImageIcon);
	}

	public Vector2 getPos() { return this.pos; }

	public int returnPoint() { return this.point; }

	public int returnLife() { return this.life; }

	public JLabel returnLabel() { return this.sprite; }

	public void moveEntity(Vector2 newPos) 
	{

/* 		if (newPos != this.getPos())
			PrintPos(this.getPos()); */

		if (newPos.x > this.getPos().x)
			this.changeSpirte("src/Resource/PlayerSprite/PlayerRight.png");
		if (newPos.x < this.getPos().x)
			this.changeSpirte("src/Resource/PlayerSprite/PlayerLeft.png");
		if (newPos.y < this.getPos().y)
			this.changeSpirte("src/Resource/PlayerSprite/PlayerBack.png");
		if (newPos.y > this.getPos().y)
			this.changeSpirte("src/Resource/PlayerSprite/PlayerFront.png");

 		this.pos = newPos;
        this.sprite.setLocation((int)this.pos.x, (int)this.pos.y);
		this.collider.box.pos = newPos;
		/* this.sprite.setSize((int)this.size.x, (int)this.size.y); */
	}

	public int returnMoveDistance()
	{
		return this.moveDistance;
	}

	public Vector2 getSize()
	{
		Vector2 size = new Vector2();

		size.x = this.collider.box.xSize;
		size.y = this.collider.box.ySize;

		return size;
	}

	public Collider getCollider()
	{
		return this.collider;
	}


}