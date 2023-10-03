package src;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

class MyImagePanel extends JPanel{ 
    BufferedImage image;
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(image != null){
            g.drawImage(image, 0, 0, this);
        }
    }
}