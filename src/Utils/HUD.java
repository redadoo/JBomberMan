package Src.Utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;

import Src.Main.GamePanel;

public class HUD
{
	private GamePanel					gp;
	private BufferedImage				hud;
	private int							offset;
	private ArrayList<BufferedImage>	numbers;
	private int							lenghtInt;

	public HUD(GamePanel gp)
	{
		this.gp = gp;
		numbers = new ArrayList<BufferedImage>();
		offset = 170;
		try {

			numbers.add(ImageIO.read(getClass().getResourceAsStream("/Resource/HUD/Numbers/_sprite_00.png")));
			numbers.add(ImageIO.read(getClass().getResourceAsStream("/Resource/HUD/Numbers/_sprite_01.png")));
			numbers.add(ImageIO.read(getClass().getResourceAsStream("/Resource/HUD/Numbers/_sprite_02.png")));
			numbers.add(ImageIO.read(getClass().getResourceAsStream("/Resource/HUD/Numbers/_sprite_03.png")));
			numbers.add(ImageIO.read(getClass().getResourceAsStream("/Resource/HUD/Numbers/_sprite_04.png")));
			numbers.add(ImageIO.read(getClass().getResourceAsStream("/Resource/HUD/Numbers/_sprite_05.png")));
			numbers.add(ImageIO.read(getClass().getResourceAsStream("/Resource/HUD/Numbers/_sprite_06.png")));
			numbers.add(ImageIO.read(getClass().getResourceAsStream("/Resource/HUD/Numbers/_sprite_07.png")));
			numbers.add(ImageIO.read(getClass().getResourceAsStream("/Resource/HUD/Numbers/_sprite_08.png")));
			numbers.add(ImageIO.read(getClass().getResourceAsStream("/Resource/HUD/Numbers/_sprite_09.png")));

			hud = ImageIO.read(getClass().getResourceAsStream("/Resource/HUD/HUD.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void Update()
	{

	}

	/**
	 * Draws the player on the provided Graphics2D object.
	 * @param g2 The Graphics2D object on which the player will be drawn.
	 */
	public void Draw(Graphics2D g2)
	{
		int index = gp.player.life;
		// Draw hud
		g2.drawImage(hud,0,0,520,60,null);

		// Draw player life
		g2.drawImage(numbers.get(index),50,15,15,30,null);
	
		//Draw point

		if (gp.player.getPoint() > 0)
		{
			String pointString = String.valueOf(gp.player.getPoint());
			for (int i = 0; i < pointString.length(); i++) 
			{	
				char c = pointString.charAt(i);
				int indexPoint = Integer.parseInt(String.valueOf(c)); // Rapresent the point
				g2.drawImage(numbers.get(indexPoint),offset + i * 20,17,17,28,null);
			}
		}
	}
}
