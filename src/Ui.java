package src;

import java.awt.Color;

import javax.swing.*;


public class Ui 
{
    GameManager gm;
    JFrame window;

    public Ui(GameManager gm)
    {
        this.gm = gm;

        CrateMainField();

        window.setVisible(true);

    }

    
    public void CrateMainField()
    {
        window = new JFrame();
        window.setSize(1280, 720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);
    }
}
