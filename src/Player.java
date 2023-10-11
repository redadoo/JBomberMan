package src;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player 
{
	private int			life;
	private int			point;
	private Vector2		pos;
	private Vector2		size;
	private String		imagePath;
	private JLabel		sprite;

    /*
     *  Costruttore della classe Player
     */
	public Player( String imagePath, Vector2 pos, Vector2 size)
	{
		//Si passano i parametri alla classe padre
		this.pos = pos;
		
		this.size = size;
		
        //inizializiamo lo sprite
        this.sprite = new JLabel();
		
		ImageIcon sizeSprite = new ImageIcon(imagePath);
		
		java.awt.Image image = sizeSprite.getImage();
		java.awt.Image newimg = image.getScaledInstance(size.x, size.y,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
		ImageIcon newImageIcon = new ImageIcon(newimg);
		
        this.sprite.setIcon(newImageIcon);

        this.sprite.setLocation(this.pos.x,this.pos.y);
        this.sprite.setSize(size.x, size.y);

        // Vita iniziale del player 
		this.life = 5;

        //Punti iniziali del player
		this.point = 0;
	}

	public Vector2 getPos() { return this.pos; }

	public int returnPoint() { return this.point; }

	public int returnLife() { return this.life; }

	public JLabel returnLabel() { return this.sprite; }

	public void moveEntity(Vector2 newPos) 
	{
 		this.pos = newPos;
        this.sprite.setLocation(this.pos.x, this.pos.y);
	}
}