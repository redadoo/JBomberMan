package src;

import java.util.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Classe per la gestione della grafica
 * Tempo che scorre, punti vita e  score
 */
public class HUD 
{
    private Vector<BufferedImage> numberImage;
    
    public HUD() throws IOException
    {
        File file = new File("src/Resource/NumberSprite/sprite_00.jng");

		BufferedImage image = ImageIO.read(file);
        numberImage.add(image);
    }

}
