package src.Utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observer;

import javax.imageio.ImageIO;

import src.Main.GamePanel;
import src.lib.Vector2;

/**
 * The HUD (Heads-Up Display) class manages the graphical elements displayed on the screen during gameplay
 * and game over state. It includes the player's life, points, and game over screen with indicators.
*/
public class HUD
{
	private int							i;
	private GamePanel					gp;
	private BufferedImage				hud;
	private int							offset;
	private int							frameCount;
	private int							spriteIndex;
	private ArrayList<BufferedImage>	numbers;
	private ArrayList<BufferedImage>	clockSprite;
	private BufferedImage				gameOverHud;
	private BufferedImage				indicator;
	private ArrayList<Vector2> 			listposIndicator;
	private Vector2 					posIndicator;

	/**
     * Constructs a HUD object with the specified GamePanel reference and initializes graphical elements.
     * @param gp The GamePanel reference
    */
	public HUD(GamePanel gp)
	{
		this.gp = gp;
		numbers = new ArrayList<BufferedImage>();
		clockSprite = new ArrayList<BufferedImage>();
		offset = 170;
		listposIndicator = new ArrayList<Vector2>();
		listposIndicator.add(new Vector2(140,230));
		listposIndicator.add(new Vector2(290,230));
		posIndicator = listposIndicator.get(0);
		try {

			for(i = 0; i < 8; i++)
				clockSprite.add(ImageIO.read(getClass().getResourceAsStream("/Resource/ClockSprite/sprite_"+ i +".png")));

			gameOverHud = ImageIO.read(getClass().getResourceAsStream("/Resource/Screen/GameOver.png"));
			indicator = ImageIO.read(getClass().getResourceAsStream("/Resource/Screen/indicator.png"));
			
			for(i = 0; i < 10; i++)
				numbers.add(ImageIO.read(getClass().getResourceAsStream("/Resource/HUD/Numbers/_sprite_0"+ i + ".png")));

			hud = ImageIO.read(getClass().getResourceAsStream("/Resource/HUD/HUD.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * Draws HUD elements on the provided Graphics2D object, including player life and points
     * @param g2 The Graphics2D object on which the HUD will be drawn
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

		if (frameCount % 70 == 0)
		{
			spriteIndex++;
			if (spriteIndex > 7)
				spriteIndex = 0;
		}
			
		g2.drawImage(clockSprite.get(spriteIndex),246,17,30,40,null);
		frameCount++;
	}

	/**
     * Updates the game over screen based on user input for restarting or exiting the game.
    */
	public void UpdateHud()
	{
		if(gp.keyh.rightPressed == true)
			posIndicator = listposIndicator.get(1);
		if(gp.keyh.leftPressed == true)
			posIndicator = listposIndicator.get(0);
			
		if (gp.keyh.enter == true && posIndicator == listposIndicator.get(0))
		{
			if (gp.gameManager.isOnChangeLevel() == true)
				gp.gameManager.nextLevel();
			else
				gp.gameManager.reset();
		}
		else if(gp.keyh.enter == true && posIndicator == listposIndicator.get(1))
			gp.gameManager.closeGame();
	}

	/**
     * Draws the game over screen with indicators based on the selected option
     * @param g2 The Graphics2D object on which the game over screen will be drawn
    */
	public void DrawChangeLevel(Graphics2D g2)
	{
		g2.drawImage(gameOverHud,0,0,520,470,null);

		g2.drawImage(indicator,posIndicator.x,posIndicator.y,15,25,null);
	}
}
