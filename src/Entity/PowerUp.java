package Src.Entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import Src.Manager.ObjectManager.PowerUpType;
import Src.Title.Title;
import Src.lib.Vector2;

/**
 * The PowerUp class represents a power-up entity in the game
*/
public class PowerUp extends Entity 
{

	public Title		myTitle;
	public PowerUpType  myType;
	public Boolean		isVisible;

	/**
	 * Costructor class PowerUp 
	 * @param pos  The position vector for the PowerUp.
	 * @param size The size vector for the PowerUp.
	 * @param num  An integer representing the type of PowerUp.
	 * @throws IOException If there is an issue reading the image resources during initialization.
	 */
	public PowerUp(Vector2 pos, Vector2 size, int num) throws IOException
	{
		super(pos, size);

		this.myTitle = null;
		this.myType = PowerUpType.values()[num];
		this.isVisible = false;
		initPowerUp();
	}

	/**
	 * Initializes the PowerUp object by loading its sprites based on its type.
	 * @throws IOException if there is an issue reading the image resources.
	 */
	public void initPowerUp() throws IOException 
	{
		if (this.myType == PowerUpType.LifeUp) {
			// Load life-up powerUp sprites
			spriteList.add(ImageIO.read(getClass().getResourceAsStream("/Resource/PowerUp/sprite_4.png")));
			spriteList.add(ImageIO.read(getClass().getResourceAsStream("/Resource/PowerUp/sprite_5.png")));
		} else if (this.myType == PowerUpType.AddBomb) {
			// Load add-bomb powerUp sprites
			spriteList.add(ImageIO.read(getClass().getResourceAsStream("/Resource/PowerUp/sprite_0.png")));
			spriteList.add(ImageIO.read(getClass().getResourceAsStream("/Resource/PowerUp/sprite_1.png")));
		} else {
			// Load speed powerUp sprites
			spriteList.add(ImageIO.read(getClass().getResourceAsStream("/Resource/PowerUp/sprite_2.png")));
			spriteList.add(ImageIO.read(getClass().getResourceAsStream("/Resource/PowerUp/sprite_3.png")));
		}

		// Set the current sprite to the first one in the list
		sprite = spriteList.get(spriteIndex);
	}

	/**
	 * Method that update the powerup
	*/
	public void Update()
	{
		if(frameCount % 5 == 0)
		{
			spriteIndex++;
			if (spriteIndex == 2)
				spriteIndex = 0;
		}
		frameCount++;
		sprite = spriteList.get(spriteIndex);
	}

	/**
	 * Method to draws the powerUp on the provided Graphics2D object.
	 * @param g2 The Graphics2D object on which the player will be drawn.
	 */
	public void Draw(Graphics2D g2){ g2.drawImage(sprite, pos.x, pos.y, size.x, size.y, null); }

	/**
	 * Sets the Title associated with the current object
	 * @param title The Title to be set.
	 */
	public void setTitle(Title title) { myTitle = title; }
	
}
