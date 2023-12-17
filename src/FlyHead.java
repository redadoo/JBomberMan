package src;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import src.lib.Vector2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 *  Classe per la gestione del nemico del primo livello
 */
public class FlyHead extends Entity
{
    private int             pointDrop;
    private JLabel		sprite;
    private Vector2		pos;
	private Vector2		size;
    



    /*
     * Costruttore della classe FlyHead
     */
    public FlyHead(String imagePath, Vector2 pos, Vector2 size) throws IOException
    {
        super(pos, size,imagePath);
        this.pointDrop = 100;
    
        //inizializiamo lo sprite
        this.sprite = new JLabel();

        File file = new File(imagePath);
        BufferedImage image = ImageIO.read(file);

        // Imposta la posizione
        this.sprite.setIcon(new ImageIcon(image.getScaledInstance((int)size.x, (int)size.y,  java.awt.Image.SCALE_SMOOTH)));
    
        this.sprite.setLocation((int)this.pos.x, (int)this.pos.y);
        /* this.sprite.setSize((int)size.x, (int)size.y); */
        this.sprite.setLayout(null);

    }

    public int getPointDrop() { return pointDrop; }
    
}