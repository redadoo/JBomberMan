package Src.Utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;

import Src.Main.GamePanel;
import Src.lib.Vector2;

public class HUD
{
	private GamePanel					gp;
	private BufferedImage				hud;
	private int							offset;
	private ArrayList<BufferedImage>	numbers;
	private int							lenghtInt;
	private BufferedImage				gameOverHud;
	private BufferedImage				indicator;
	private ArrayList<Vector2> 			listposIndicator;
	private Vector2 					posIndicator;
	private BufferedImage				gameWonHud;

	public HUD(GamePanel gp)
	{
		this.gp = gp;
		numbers = new ArrayList<BufferedImage>();
		offset = 170;
		listposIndicator = new ArrayList<Vector2>();
		listposIndicator.add(new Vector2(140,230));
		listposIndicator.add(new Vector2(290,230));
		posIndicator = listposIndicator.get(0);
		try {

			gameOverHud = ImageIO.read(getClass().getResourceAsStream("/Resource/Screen/GameOver.png"));
			indicator = ImageIO.read(getClass().getResourceAsStream("/Resource/Screen/indicator.png"));

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

	
	public void UpdateGameOver()
	{
		if(gp.keyh.rightPressed == true)
			posIndicator = listposIndicator.get(1);
		if(gp.keyh.leftPressed == true)
			posIndicator = listposIndicator.get(0);
			
		if (gp.keyh.enter == true && posIndicator == listposIndicator.get(0))
			gp.gameManager.Reset();
		else if(gp.keyh.enter == true && posIndicator == listposIndicator.get(1))
			System.exit(0);
	}

	public void DrawGameOver(Graphics2D g2)
	{
		g2.drawImage(gameOverHud,0,0,520,470,null);

		g2.drawImage(indicator,posIndicator.x,posIndicator.y,15,25,null);
	}
}
