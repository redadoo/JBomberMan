package src;

import java.awt.Color;
import javax.swing.*;

public class Ui 
{
    GameManager gm;
    public JFrame window;

    public Ui(GameManager gm)
    {
        this.gm = gm;

        CrateMainField();

        window.setVisible(true);

    }

    /*
    *   Impostiamo la schermata del gioco 
    */
    public void CrateMainField()
    {
        window = new JFrame();
        window.setSize(1280, 720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK); 
        window.setTitle("Snes Bomberman"); //Titolo in alto a sinistra -> della window
        window.setLocationRelativeTo(null); //Far partire la window dal centro dello schermo
        window.setLayout(null);
        window.setVisible(true);  // si può anche togliere
    }
}
