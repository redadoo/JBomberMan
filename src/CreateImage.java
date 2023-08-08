package src;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CreateImage extends JFrame
{
    JLabel DispleyField;
    ImageIcon image;


    public CreateImage(JFrame frame)
    {
        try
        {
            image = new ImageIcon(getClass().getResource("download.jpg"));
            DispleyField = new JLabel(image);
            DispleyField.setSize(100, 100);
            frame.add(DispleyField);
        } 
        catch(Exception e){ System.out.println(e); } //Ah Coglione
    }
    
}
