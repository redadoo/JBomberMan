package src;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;

public class CreateImage extends JFrame
{
    JLabel DisplayField;
    ImageIcon image;


    public CreateImage(JFrame frame, String name)
    {
        try
        {
            image = new ImageIcon(getClass().getResource(name));
            DisplayField = new JLabel(image);
            
/*             frame.add(DisplayField);
            frame.pack();
            frame.setVisible(true); */
            System.out.println(name);
        } 
        catch(Exception e)
        { 
            System.out.println(e); 
        }
    }
    
}
