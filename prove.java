import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class prove extends JPanel implements ActionListener, KeyListener 
{
    private int characterX = 50;
    private int characterY = 50;
    private BufferedImage characterImage;

    public prove() {
        setPreferredSize(new Dimension(800, 600));
        addKeyListener(this);
        setFocusable(true);

        // Carica l'immagine del personaggio da un file
        try {
            characterImage = ImageIO.read(new File("sp.png")); // Sostituisci con il tuo percorso all'immagine
        } catch (IOException e) {
            e.printStackTrace();
        }

        Timer timer = new Timer(16, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Aggiorna il movimento del personaggio in base alla logica del gioco
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Disegna l'immagine del personaggio nella posizione corrente
        g.drawImage(characterImage, characterX, characterY, this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        // Aggiorna la posizione del personaggio in base all'input utente
        if (key == KeyEvent.VK_LEFT) {
            characterX -= 5;
        } else if (key == KeyEvent.VK_RIGHT) {
            characterX += 5;
        } else if (key == KeyEvent.VK_UP) {
            characterY -= 5;
        } else if (key == KeyEvent.VK_DOWN) {
            characterY += 5;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Character Movement With Images");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new prove());
        frame.pack();
        frame.setVisible(true);
    }
}
