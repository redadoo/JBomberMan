package Src.lib;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sprite {

	private int spriteIndex;
	public BufferedImage startSprite;
	public ArrayList<BufferedImage> spriteList;

	public Sprite(ArrayList<BufferedImage> spriteList) {
		this.spriteList = spriteList;
		this.startSprite = spriteList.get(0);
	}

	public void nextSprite()
	{
		if (spriteIndex + 1 < spriteList.size())
			startSprite = spriteList.get(++spriteIndex);
		else
			spriteIndex = 0;
	}
}
