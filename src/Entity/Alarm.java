package Src.Entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import Src.lib.Vector2;


/**
 * Class for the Alarms
 */
public class Alarm extends Entity
{

	private int		frameCount;

	/**
	 * Costructor class Alarm
	 * @param SpritePath the path of all sprites
	 * @param pos the pos to set the entity
	 * @param size the size for the entity
	 */
	public Alarm (Vector2 pos, Vector2 size)
	{
		super(pos,size);
		initAlarm();
	}

	public void initAlarm()
	{
		try {
			spriteVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Alarms/sprite_0.png")));
			spriteVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Alarms/sprite_1.png")));
			spriteVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Alarms/sprite_2.png")));
			spriteVector.add(ImageIO.read(getClass().getResourceAsStream("/Resource/Alarms/sprite_3.png")));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sprite = spriteVector.get(spriteIndex);
	}

	public void Update()
	{
		if(frameCount % 5 == 0)
		{
			spriteIndex++;
			if (spriteIndex == 4)
				spriteIndex = 0;
			sprite = spriteVector.get(spriteIndex);
		}

		frameCount++;
	}

	/**
	 * Draws the alarm on the provided Graphics2D object.
	 * @param g2 The Graphics2D object on which the player will be drawn.
	 */
	public void Draw(Graphics2D g2)
	{
		g2.drawImage(sprite,pos.x,pos.y,size.x,size.y,null);
	}
	
}