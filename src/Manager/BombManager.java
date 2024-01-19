package Src.Manager;

import java.awt.Graphics2D;
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
	private int						i;
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
		initBombManager();
	}
	
	/**
	 * Initializes the Bomb Manager by setting up the necessary data structures and loading explosion sprites.
	 * This method creates an initial bomb and adds it to the bombsList.
	 */
	public void initBombManager()
	{
		// Initialize frame count to 0
		frameCount = 0;

		// Initialize lists to store bombs and explosion sprites
		bombsList = new ArrayList<Bomb>();
		explosionSpriteList = new ArrayList<Sprite>();

		// Load explosion sprites into the explosionSpriteList
		try {
			// Load sprites for each frame of the explosion animation
			for (i = 0; i < 5; i++) {
				explosionSpriteList.add(new Sprite(new ArrayList<BufferedImage>() {{
					add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/two/sprite_" + i + ".png")));
					add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/third/sprite_" + i + ".png")));
					add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/four/sprite_" + i + ".png")));
					add(ImageIO.read(getClass().getResourceAsStream("/Resource/BombExplosion/five/sprite_" + i + ".png")));
				}}));
			}
		} catch (IOException e) {
			e.printStackTrace(); // Handle IOException by printing the stack trace
		}

		// Create an initial Bomb object and add it to the bombsList
		try {
			bombsList.add(new Bomb(gp, new Vector2()));
			bombsList.add(new Bomb(gp, new Vector2()));
		} catch (IOException e) {
			e.printStackTrace(); // Handle IOException by printing the stack trace
		}
	}

	/**
	 * To place the bomb in the right way
	 * @param rec to manage the info (pos, state) 
	 */
	public void PlaceBomb(Title title) throws IOException
	{
		for (Bomb bomb : bombsList) {
			if (bomb.myState == BombState.Available && isTitleFree(title))
			{
				System.out.println("love");
				if(title.matrixPos.equals(new Vector2(0, 0)))
					bomb.setPos(new Vector2(48, 90));
				else
					bomb.setPos(title.pos);
				bomb.myState = BombState.Placed;
				return ;
			}
		}
	}

	/**
	 * Manage the behavior of bombs
	*/
	public void Update()
	{
		for (Bomb bomb : bombsList) 
		{
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

			if (bomb.myState == BombState.Placed) // Placed
			{
				if(bomb.timerExplosion == 0)
				{
					bomb.isPlayerHover = true;
					bomb.timerExplosion = gp.elapsedTime;
				}
				
				// Change the sprites after a period
				if(frameCount % 22 == 0)
					bomb.nextSprite();

				// Bomb explosion
				if(gp.elapsedTime >= bomb.timerExplosion + 2.5)
				{
					bomb.InitExplosion(gp.mapManager.getRangedTitleIndex(bomb.pos,bomb.size), explosionSpriteList,gp.mapManager.GetTitleFromPos(bomb.pos,bomb.size).matrixPos);
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

			if (bomb.myState == BombState.Placed)
			{
				g2.drawImage(bomb.sprite, bomb.pos.x, bomb.pos.y, bomb.size.x, bomb.size.y,null);
				g2.drawRect(bomb.coll.rec.x, bomb.coll.rec.y, bomb.coll.rec.width, bomb.coll.rec.height);
			}

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

	/**
	 * Method to get the list of Bomb
	 * @return the list of Bomb
	*/
	public ArrayList<Bomb> getBombList() { return bombsList; }
	
	/**
	 * Checks if a specific tile is free from placed bombs.
	 * It iterates through the list of bombs and compares their positions with the given tile.
	 *
	 * @param title The title to check for availability.
	 * @return {@code true} if the tile is free; {@code false} otherwise.
	 */
	public Boolean isTitleFree(Title title)
	{
		for (Bomb bomb : bombsList) {
			if (bomb.myState == BombState.Placed && bomb.pos == title.pos)
				return false;
		}
		return true;
	}

	/**
	 * Method to for add a bomb to bombsList
	 */
	public void addBomb() throws IOException{ bombsList.add(new Bomb(gp, new Vector2()));}

}
