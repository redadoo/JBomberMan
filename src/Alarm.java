package src;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 *  Classe per gli oggetti "allarmi"
 */
public class Alarm extends Entity
{
    private JLabel		sprite;
    private Vector2		pos;
	private Vector2		size;
    private String		imagePath;


    /*
     * Costruttore della classe Alarm
     */
    public Alarm (String imagePath, Vector2 pos, Vector2 size) throws IOException
    {
        super(pos, size);
        //inizializiamo lo sprite
        this.sprite = new JLabel();

        this.pos = pos;
		
		this.size = size;

 
        
        File file = new File(imagePath);
        BufferedImage image = ImageIO.read(file);
        System.out.println("Ciao");
        // Imposta la posizione
        this.sprite.setIcon(new ImageIcon(image.getScaledInstance((int)size.x, (int)size.y,  java.awt.Image.SCALE_SMOOTH)));
    
        this.sprite.setLocation((int)this.pos.x, (int)this.pos.y);
        /* this.sprite.setSize((int)size.x, (int)size.y); */
        this.sprite.setLayout(null);


    }

    public void changeSpirte(String imagePath) 
	{
    }

    public Vector2 getPos() { return this.pos; }

	public JLabel returnLabel() { return this.sprite; }




    @Override
    public void moveEntity(Vector2 pos) 
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveEntity'");

        
    }
    
}