package Src.Manager;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;

import Src.Entity.Bomb;
import Src.Entity.Bomb.BombState;
import Src.Main.GamePanel;
import Src.Title.Title;
import Src.lib.Sprite;
import Src.lib.Vector2;

/**
 * Class to manage the class bomb
 */
public class BombManager 
{
	private GamePanel       		gp;
	private ArrayList<Bomb>			bombsList;
	private int             		frameCount;
	public	ArrayList<Sprite>		explosionSpriteList;

	/**
	 * Costructor class BombManager
	 * @param gp
	*/
	public BombManager(GamePanel gp)
	{
		this.gp = gp;
		frameCount = 0;
		bombsList = new ArrayList<Bomb>();
		explosionSpriteList = new ArrayList<Sprite>();

		try {
			explosionSpriteList.add(new Sprite(new ArrayList<BufferedImage>()
			{
				{
				add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/two/sprite_0.png")));
				add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/third/sprite_0.png")));
				add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/four/sprite_0.png")));
				add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/five/sprite_0.png")));
				}
			}));

			explosionSpriteList.add(new Sprite(new ArrayList<BufferedImage>(){{
				add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/two/sprite_1.png")));
				add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/third/sprite_1.png")));
				add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/four/sprite_1.png")));
				add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/five/sprite_1.png")));
			}}));

			explosionSpriteList.add(new Sprite(new ArrayList<BufferedImage>(){{
				add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/two/sprite_2.png")));
				add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/third/sprite_2.png")));
				add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/four/sprite_2.png")));
				add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/five/sprite_2.png")));
			}}));

			explosionSpriteList.add(new Sprite(new ArrayList<BufferedImage>(){{
				add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/two/sprite_3.png")));
				add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/third/sprite_3.png")));
				add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/four/sprite_3.png")));
				add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/five/sprite_3.png")));
			}}));

			explosionSpriteList.add(new Sprite(new ArrayList<BufferedImage>(){{
				add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/two/sprite_4.png")));
				add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/third/sprite_4.png")));
				add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/four/sprite_4.png")));
				add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/five/sprite_4.png")));
			}}));

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
					bomb.InitExplosion(gp.mapManager.getRangedTitleIndex(bomb.pos), explosionSpriteList,gp.mapManager.GetTitleFromPos(bomb.pos).matrixPos);
					bomb.timerExplosion = 0;
					bomb.myState = BombState.Exploded;
				}
			}
			if (bomb.myState == BombState.Exploded)
			{
				for (Map.Entry<Sprite,Vector2> entry : bomb.myExplosionSprite.entrySet())  
				{
					if(frameCount % 10 == 0)
						entry.getKey().nextSprite();
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
		if(bombsList.size() <= 0)
			return ;

		for (Bomb bomb : bombsList) {
			if (bomb.myState == BombState.NotAvailable)
				g2.drawImage(bomb.sprite, bomb.pos.x + 1, bomb.pos.y,bomb.size.x,bomb.size.y,null);
			// Bomb exploded
			if (bomb.myState == BombState.Exploded)
			{
				for (Map.Entry<Sprite,Vector2> entry : bomb.myExplosionSprite.entrySet())  
				{
					g2.drawImage(entry.getKey().startSprite, entry.getValue().x + 1, entry.getValue().y + 5, 30, 30, null);
				}
			}
		}
	}

	public ArrayList<Bomb> getBombList() { return bombsList; }

}
