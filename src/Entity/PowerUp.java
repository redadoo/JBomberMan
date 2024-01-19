package Src.Entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import Src.Manager.ObjectManager.PowerUpType;
import Src.Title.Title;
import Src.lib.Vector2;

public class PowerUp extends Entity {

	public Title		myTitle;
	public PowerUpType  myType;
	public Boolean		isVisible;

	public PowerUp(Vector2 pos, Vector2 size, int num) throws IOException
	{
		super(pos, size);

		this.myTitle = null;
		this.myType = PowerUpType.values()[num];
		this.isVisible = false;
		initPowerUp();
	}

	public void initPowerUp() throws IOException
	{
		if (this.myType == PowerUpType.LifeUp)
		{
			spriteList.add(ImageIO.read(getClass().getResourceAsStream("/Resource/PowerUp/sprite_4.png")));
			spriteList.add(ImageIO.read(getClass().getResourceAsStream("/Resource/PowerUp/sprite_5.png")));
		}
		else if (this.myType == PowerUpType.AddBomb)
		{
			spriteList.add(ImageIO.read(getClass().getResourceAsStream("/Resource/PowerUp/sprite_0.png")));
			spriteList.add(ImageIO.read(getClass().getResourceAsStream("/Resource/PowerUp/sprite_1.png")));
		}
		else 
		{
			spriteList.add(ImageIO.read(getClass().getResourceAsStream("/Resource/PowerUp/sprite_2.png")));
			spriteList.add(ImageIO.read(getClass().getResourceAsStream("/Resource/PowerUp/sprite_3.png")));
		}

		sprite = spriteList.get(spriteIndex);
	}

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

	public void Draw(Graphics2D g2)
	{
		g2.drawImage(sprite, pos.x, pos.y, size.x, size.y, null);
	}

	public void setTitle(Title title) { myTitle = title; }
	
}
