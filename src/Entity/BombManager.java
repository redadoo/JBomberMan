package Src.Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;

import Src.Entity.Bomb.BombState;
import Src.Main.GamePanel;
import Src.Title.Title;
import Src.lib.Vector2;

public class BombManager 
{
	private GamePanel       			gp;
	private ArrayList<Bomb>				bombsList;
	private int             			frameCount;
	public	ArrayList<BufferedImage>	explosionSpriteList;

	public BombManager(GamePanel gp)
	{
		this.gp = gp;
		frameCount = 0;
		bombsList = new ArrayList<Bomb>();
		explosionSpriteList = new ArrayList<BufferedImage>();
		try {
			explosionSpriteList.add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/ex00.png")));
			explosionSpriteList.add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/ex03.png")));
			explosionSpriteList.add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/ex06.png")));
			explosionSpriteList.add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/ex09.png")));
			explosionSpriteList.add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/ex12.png")));

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			bombsList.add(new Bomb(gp, new Vector2()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * To place the bomb in the right way
	 * @param rec to manage the info (pos, state) 
	 */
	public void PlaceBomb(Rectangle rec) throws IOException
	{
		for (Bomb bomb : bombsList) {
			if (bomb.myState == BombState.Available)
			{
				Title title = gp.mapManager.GetTitleFromRec(rec);
				if (title != null)
					bomb.pos = title.pos;
				bomb.myState = BombState.NotAvailable;
			}
		}
	}

	/**
	 * Manage the behavior of bombs
	 */
	public void Update()
	{
		for (Bomb bomb : bombsList) {

			if (bomb.myState ==  BombState.Exploded)
			{
				if(bomb.timerExplosion == 0)
					bomb.timerExplosion = gp.elapsedTime;

				if(frameCount % 22 == 0)
					bomb.nextSprite();

				if(gp.elapsedTime >= bomb.timerExplosion + 1)
				{
					bomb.timerExplosion = 0;
					bomb.myState = BombState.Available;
					bomb.myExplosionSprite.clear();
				}
			}
			if (bomb.myState ==  BombState.NotAvailable) // Placed
			{
				if(bomb.timerExplosion == 0)
					bomb.timerExplosion = gp.elapsedTime;
				
				// Change the sprites after a period
				if(frameCount % 22 == 0)
					bomb.nextSprite();

				// Bomb explosion
				if(gp.elapsedTime >= bomb.timerExplosion + 2.5)
				{
					bomb.InitExplosion(gp.mapManager.getRangedTitle(bomb.pos),explosionSpriteList, gp.mapManager.getTitleIndex(gp.mapManager.GetTitleFromPos(bomb.pos)));
					bomb.timerExplosion = 0;
					bomb.myState = BombState.Exploded;
				}
			}

		}
		frameCount++;
	}

	/**
	 * Draw bombs if are available
	 * @param g2 the variable tho handle the graphic
	 */
	public void Draw(Graphics2D g2)
	{
		if(bombsList.size() > 0)
		{
			for (Bomb bomb : bombsList) {
				if (bomb.myState == BombState.NotAvailable)
					g2.drawImage(bomb.sprite, bomb.pos.x + 1, bomb.pos.y,bomb.size.x,bomb.size.y,null);
				// Bomb exploded
				if (bomb.myState == BombState.Exploded)
				{
					for (Map.Entry<BufferedImage,Vector2> entry : bomb.myExplosionSprite.entrySet())  
					{
						g2.drawImage(entry.getKey(), entry.getValue().x + 3, entry.getValue().y,bomb.size.x * 2,bomb.size.y * 2,null);
					}
				}
			}
		}
	}
}
