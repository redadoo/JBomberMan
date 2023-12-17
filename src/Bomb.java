package src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import src.lib.Vector2;

public class Bomb extends Entity {
    
    public Bomb(String imagePath, Vector2 size) throws IOException
    {
        super(new Vector2(-100 , -100), size, imagePath);
        Vector2 pos = new Vector2(-100 , -100);
		super.setLabel(new JLabel());
        
        File file = new File(super.GetImagePath());
		BufferedImage image = ImageIO.read(file);
        
        super.getLabel().setIcon(new ImageIcon(image.getScaledInstance((int)super.getSize().x, (int)super.getSize().y,  java.awt.Image.SCALE_SMOOTH)));
		super.getLabel().setLocation((int)pos.x, (int)pos.y);
		super.getLabel().setLayout(null);
    }

    public void placeBomb(Vector2 pos) throws IOException
    {
        super.getLabel().setVisible(true);
        super.setPos(pos);
		super.getLabel().setLocation((int)this.pos.x, (int)this.pos.y);
        super.moveEntity(pos);
        System.out.println("bomba!!!!!!");
    }
}
