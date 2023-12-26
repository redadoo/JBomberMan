package src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import src.lib.Vector2;

/**
 * Class to manage class Bomb 
 */
public class Bomb extends Entity 
{
    public  GamePanel   gamePanel;
    private boolean     Isavailable;
    public  int         TimerExplosion = 0;
    
    /** 
     * Costructor class Bomb
     * @throws IOException
    */
    public Bomb() throws IOException
    {        
        super(new Vector2(-100 , -100), new Vector2(25,25), "src/Resource/BombSprite/BombSprite0.png");
        Vector2 pos = new Vector2(-100 , -100);
		super.setLabel(new JLabel());
        
        File file = new File(super.GetImagePath());
		BufferedImage image = ImageIO.read(file);
        
        
        super.getLabel().setIcon(new ImageIcon(image.getScaledInstance((int)super.getSize().x, (int)super.getSize().y,  java.awt.Image.SCALE_SMOOTH)));
		super.getLabel().setLocation((int)pos.x, (int)pos.y);
		super.getLabel().setLayout(null);

        Isavailable = true;

        super.InitSpriteArray(new String[]{     "src/Resource/BombSprite/BombSprite0.png", 
                                                "src/Resource/BombSprite/BombSprite1.png",
                                                "src/Resource/BombSprite/BombSprite2.png"});
    }

    /**
     * To round the number
     * @param n numbet to round
     * @param d  the tenth to reach
     */
    public static int RoundUpInt(int n, int d) 
    {
        int div = n / d;
        int rest = n % d;

        int res = div * d;

        if (rest < d / 2) 
            return res; 
        else 
            return res + d; 
    }

    /**
    * Function to place the bomb
    * @param pos the position to place the bomb
    * @param panel the panel where place the bomb
    * @throws IOException
    */
    public void placeBomb(Vector2 pos, GamePanel panel) throws IOException
    {
        pos.x = RoundUpInt((int)pos.x, 30);

        panel.addToPanel(super.getLabel());
        super.getLabel().setVisible(true);
        super.setPos(pos);
		super.getLabel().setLocation(((int)this.pos.x), ((int)this.pos.y));
        super.moveEntity(pos);   
    }
    /**
	 * Set the panel
     * @param panel to set the panel
	 */
    public void SetPanel(GamePanel panel) {this.gamePanel = panel;}

	/**
	 * Handle the explosion
     * @throws IOException
	 */
    public void Explosion() throws IOException
    {
        /** super.changeSpirte("src/Resource/BombExplosion/sprite_0.png"); */
        super.setSize(new Vector2(25,25));
        gamePanel.panel.remove(getLabel());
        Isavailable = true;
        TimerExplosion = 0;
        Index = 0;
    }

	/**
	 *  Set the status of bomb
     * @param status to set the visibility of bomb
	 */
    public void SetStatus(Boolean status) {  Isavailable = status; }

    /**
	 *  Get the status of bomb
     * @return the status of the bomb
	 */
    public Boolean ReturnStatus() { return Isavailable; }

    /**
     * Function to change the size of the bomb's sprite
     * @throws IOException
     */
    @Override
    public void NextSprite() throws IOException
    {
        super.setSize(new Vector2(getSize().x + Index, getSize().y + Index));
        
        if (super.Index > super.getSpriteArray().length - 1)
        {
            TimerExplosion++;
            super.setSize(new Vector2(25,25));
            Index = 0;
        }
		changeSpirte(super.getSpriteArray()[Index]);
        if (TimerExplosion == 2)
        {
            Explosion();
        }
		Index++;
    }
}
