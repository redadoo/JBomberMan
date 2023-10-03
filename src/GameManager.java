package src;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class GameManager
{  
	private Ui ui;
	
	
	public static void main(String[] args) throws IOException 
	{
		JFrame dio = new JFrame();
		Vector2 PlayerPos = new Vector2();
		Vector2 PlayerSize = new Vector2();

 		GameManager GM = new GameManager();
 
/* 		Ui ui = new Ui(GM);
 */
		Player player = new Player("sprite_4.png", PlayerPos, PlayerSize);
		System.out.println(player.getImagePath());


		dio.setSize(1280, 720);
        dio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dio.getContentPane().setBackground(Color.BLACK); 
        dio.setTitle("Snes Bomberman"); //Titolo in alto a sinistra -> della window
        dio.setLocationRelativeTo(null); //Far partire la window dal centro dello schermo
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon("src/sprite_4.png"));
		dio.add(label);
        dio.setVisible(true);
/* 		java.awt.Image image = Toolkit.getDefaultToolkit().getImage("src/sprite_4.png");
 */
/* 		JLabel labelLcl = new JLabel("Ye ana!");

		File f = new File("src/sprite_4.png");
		BufferedImage imgLcl = ImageIO.read(f);
		Image im = SwingFXUtils.toFXImage(imgLcl,null); */

/* 		do 
		{
			if (Keyboard.isKeyPressed(KeyEvent.VK_D)) System.out.println("D is pressed!");
		} while (!Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)); */
	}
	
}  