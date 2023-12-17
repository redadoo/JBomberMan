package src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import src.lib.Vector2;

/*
 * Class to manage class Bomb 
 */
public class Bomb extends Entity 
{
    public  GamePanel   panel;
    private boolean     Isavailable;
    public  int         TimerExplosion = 0;
    
    //Costructor class Bomb
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

    //Function to place the bomb
    public void placeBomb(Vector2 pos, GamePanel panel) throws IOException
    {
        panel.addToPanel(super.getLabel());
        super.getLabel().setVisible(true);
        super.setPos(pos);
		super.getLabel().setLocation((int)this.pos.x, (int)this.pos.y);
        super.moveEntity(pos);
/*         System.out.println("bomba!!!!!!");
 */    
    }

    public void SetPanel(GamePanel panel) {this.panel = panel;}

    public void Explosion()
    {
        super.setSize(new Vector2(25,25));
        panel.panel.remove(getLabel());
        Isavailable = true;
        TimerExplosion = 0;
        Index = 0;
    }

    public void     SetStatus(Boolean x) {  Isavailable = x; }
    public Boolean  ReturnStatus() { return Isavailable; }

    //Function to change the size of the bomb's sprite
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
