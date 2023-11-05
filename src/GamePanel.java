package src;

import javax.swing.JFrame;
import java.awt.Component;
import javax.swing.JPanel;
import java.awt.Color;

public class GamePanel 
{
    Vector2 size;
    String  name;
    JPanel  panel;
    JFrame	screen;
    
    public GamePanel(JFrame	screen, JPanel  panel, Vector2 size, String name)
    {
        this.screen = screen;
        this.panel = panel;
        this.size = size;
        this.name = name;

        setSize(size);
		panel.setBackground(Color.black);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setTitle(name); 					//Titolo in alto a sinistra -> della window
		screen.setLocationRelativeTo(null); 				//Far partire la window dal centro dello schermo
		screen.setVisible(true);
        this.addToScreen(this.returnJPanel());

    }

    void addToPanel(Component comp)
    {
        this.panel.add(comp);
    }
    
    void addToScreen(Component comp)
    {
        this.screen.add(comp);
    }

    void setSize(Vector2 size)
    {
        this.screen.setSize(size.x,size.y);
    }

    JPanel returnJPanel()
    {
        return panel;
    }

    JFrame returnJFrame()
    {
        return screen;
    }
}
