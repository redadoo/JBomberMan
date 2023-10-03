/* import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Dimension;

public class GameManager {
    private Ui ui;

    public GameManager() {
        JFrame frame = new JFrame("Game Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));

        JPanel panel = new JPanel();
        frame.add(panel);

        // Carica l'immagine dello sprite del giocatore
        ImageIcon playerIcon = new ImageIcon("sprite_4.png");
        JLabel playerLabel = new JLabel(playerIcon);

        // Imposta la posizione del giocatore
        playerLabel.setBounds(100, 100, playerIcon.getIconWidth(), playerIcon.getIconHeight());

        // Aggiungi il giocatore al pannello
        panel.add(playerLabel);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameManager GM = new GameManager();
        });
    }
}
 */