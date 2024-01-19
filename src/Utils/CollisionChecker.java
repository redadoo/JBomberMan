package Src.Utils;

import java.util.Observable;

import Src.Entity.Alarm;
import Src.Entity.Bomb;
import Src.Entity.BrightHead;
import Src.Entity.FlyHead;
import Src.Entity.Player;
import Src.Main.GamePanel;
import Src.Manager.EnemiesManager;
import Src.Manager.TitleManager;
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
     * Constructs a CollisionChecker object with the specified GamePanel reference and initializes necessary references.
     *
     * @param gp The GamePanel reference.
     */
	public CollisionChecker(GamePanel gp)
	{
		this.gp = gp;
		this.enemiesManager = gp.enemiesManager;
		this.titleManager = gp.mapManager;
		this.player = gp.player;
	}

    /**
     * Checks for collisions between the specified collider and map titles.
     *
     * @param coll The collider to check for collisions.
     * @return true if a collision with map titles is detected, false otherwise.
     */
	public Boolean CheckTitle(Collider coll)
	{
		if (gp.mapManager == null)
			return false;
		
		for (int i = 0; i < gp.mapManager.mapTitleNum.length; i++) 
		{
			for (int j = 0; j < gp.mapManager.mapTitleNum[i].length; j++) 
			{
				if(gp.mapManager.mapTitleNum[i][j].collision == true)
				{
					if(coll.rec.intersects(gp.mapManager.mapTitleNum[i][j].coll.rec) == true)
						return true;
				}
			}
		}

		return false;
	}

    /**
     * Checks for collisions between the specified collider and bombs placed by the player.
     *
     * @param coll The collider to check for collisions.
     * @return true if no collision with bombs is detected, false otherwise.
     */
	public boolean CheckBomb(Collider coll)
	{
		for (Bomb bomb : player.bombManager.getBombList()) 
		{
			if (bomb.myState == Bomb.BombState.Placed)
			{
				if(coll.rec.intersects(bomb.getTitle().coll.rec) == true)
					return false;
			}
		}
		return true;
	}

    /**
     * Checks for collisions between the player and enemy entities and bomb explosion, updating player/enemies status accordingly.
     */
	public void Update()
	{
		for (FlyHead flyHead : enemiesManager.getListFlyHeads()) 
		{
			if(player.coll.rec.intersects(flyHead.coll.rec) == true)
			{
				player.update(this, true);
			}
		}

		for (BrightHead brightHead : enemiesManager.getListBrightHead()) 
		{
			if(player.coll.rec.intersects(brightHead.coll.rec) == true)
			{
				player.update(this, true);
			}
		}

		for (Bomb bomb : player.bombManager.getBombList()) {
			
			if (bomb.myState == Bomb.BombState.Exploded)
			{
				if (titleManager.GetTitleFromPos(bomb.pos, bomb.size).coll.rec.intersects(player.coll.rec) == true)
				{
					player.update(this, true);
				}

				for (Vector2 titlePos : titleManager.getRangedTitlePos(bomb.pos, bomb.size)) 
				{
					if (titlePos.equals(player.getTitle().matrixPos))
					{
						player.update(this, true);
					}

					for (FlyHead flyHead : enemiesManager.getListFlyHeads()) 
					{
						if(titlePos.equals(flyHead.getTitle().matrixPos) == true)
						{
							enemiesManager.update(this, flyHead);
							break ;
						}
					}

					for (BrightHead brightHead : enemiesManager.getListBrightHead()) 
					{
						if(titlePos.equals(brightHead.getTitle().matrixPos) == true)
						{
							enemiesManager.update(this, brightHead);
							break ;
						}
					}
					
					for (Alarm alarm : gp.ObjectManager.getAlarmList()) 
					{
						if(titlePos.equals(alarm.getTitle().matrixPos))
						{
							gp.ObjectManager.update(this, alarm);
							break ;
						}
					}
			
				}
			}
		}

	}
}
