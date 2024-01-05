package Src.Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import Src.Main.GamePanel;
import Src.lib.Vector2;

public class BombManager 
{
	private GamePanel       gp;
	private ArrayList<Bomb> bombs;
	private int             bombNumebr;
	private int             cooldown;
	private int             range;

	public BombManager(GamePanel gp)
	{
		this.gp = gp;
		bombs = new ArrayList<Bomb>();
		cooldown = 2;
		range = 1;
		bombNumebr = 1;
	}
	
	public void PlaceBomb(Rectangle rec) throws IOException
	{
		bombs.add(new Bomb(gp, new Vector2()));
		var x = gp.mapManager.GetTitleFromPos(rec);
		if(x != null)
		{
			bombs.get(bombs.size() - 1).pos = x.pos;
			System.out.println(x.pos.x);
		}
	}

	public void Update()
	{

	}

	public void Draw(Graphics2D g2)
	{
		if(bombs.size() > 0)
		{
			for (Bomb bomb : bombs) {
				g2.drawImage(bomb.sprite, bomb.pos.x, bomb.pos.y,28,28,null);                
			}
		}
	}
}
