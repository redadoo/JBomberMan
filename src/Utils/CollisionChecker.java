package Src.Utils;

import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import Src.Entity.Bomb;
import Src.Entity.FlyHead;
import Src.Entity.Player;
import Src.Main.GamePanel;
import Src.Manager.EnemiesManager;
import Src.Manager.TitleManager;
import Src.Title.Title;
import Src.lib.Collider;
import Src.lib.Vector2;

/**
 * Class CollisionChecker to check if two entity/Collider make contact
 */
public class CollisionChecker extends Observable
{
	private GamePanel		gp;
	private Player			player;
	private	EnemiesManager	enemiesManager;
	private TitleManager	titleManager;
	/**
	 * Costructor class CollisionChecker
	 * @param gp
	 */
	public CollisionChecker(GamePanel gp)
	{
		this.gp = gp;
		this.enemiesManager = gp.enemiesManager;
		this.titleManager = gp.mapManager;
		this.player = gp.player;
	}

	public Boolean CheckTitle(Collider coll)
	{
		for (int i = 0; i < gp.mapManager.mapTitleNum.length; i++) 
		{
			for (int j = 0; j < gp.mapManager.mapTitleNum[i].length; j++) 
			{
				if(gp.mapManager.mapTitleNum[i][j].collision == true)
				{
					if(coll.rec.intersects(gp.mapManager.mapTitleNum[i][j].coll.rec) == true)
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Check if player got damage
	*/
	public void Update()
	{
		for (FlyHead flyHead : enemiesManager.GetListFlyHeads()) 
		{
			if(player.coll.rec.intersects(flyHead.coll.rec) == true)
			{
				player.update(this, true);
			}
		}

		for (Bomb bomb : player.bombManager.getBombList()) {
			
			if (bomb.myState == Bomb.BombState.Exploded)
			{
				if (titleManager.GetTitleFromPos(bomb.pos).coll.rec.intersects(player.coll.rec) == true)
				{
					player.update(this, true);
				}
				for (Vector2 titlePos : titleManager.getRangedTitlePos(bomb.pos)) 
				{
					if (titleManager.GetTitleFromPos(titlePos).coll.rec.intersects(player.coll.rec) == true)
					{
						player.update(this, true);
					}

					for (FlyHead flyHead : enemiesManager.GetListFlyHeads()) 
					{
						if(titleManager.GetTitleFromPos(titlePos).coll.rec.intersects(flyHead.coll.rec) == true)
						{
							enemiesManager.update(this, flyHead);
							break ;
						}
					}
			
				}
			}
		}

	}
}
