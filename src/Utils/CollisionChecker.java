package Src.Utils;

import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Src.Entity.Alarm;
import Src.Entity.Bomb;
import Src.Entity.BrightHead;
import Src.Entity.Entity;
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
     * Constructs a CollisionChecker object with the specified GamePanel reference and initializes necessary references
     * @param gp the GamePanel reference
    */
	public CollisionChecker(GamePanel gp)
	{
		this.gp = gp;
		this.enemiesManager = gp.enemiesManager;
		this.titleManager = gp.mapManager;
		this.player = gp.player;
	}

    /**
     * Checks for collisions between the specified collider and map titles
     * @param coll The collider to check for collisions
     * @return true if a collision with map titles is detected, false otherwise
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
     * Checks for collisions between the specified collider and bombs placed by the player
     * @param coll The collider to check for collisions
     * @return true if no collision with bombs is detected, false otherwise
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
     * Checks for collisions between the specified collider and bombs placed by the player
     * @param coll the collider to check for collisions
	 * @param object the entity to exclude from the collision check.
     * @return true if no collision with bombs is detected, false otherwise
    */
	public boolean CheckOtherEnemies(Collider coll, Entity object)
	{
		List<?> enemies =  combine(enemiesManager.getListFlyHeads(), enemiesManager.getListBrightHead());

		for (Object enemy : enemies) 
		{
			if (enemy == object)
				return true;
			if (enemy instanceof FlyHead)
			{
				FlyHead flyhead = (FlyHead)enemy;
				if(coll.rec.intersects(flyhead.coll.rec) == true)
					return false;
			}
			else if(enemy instanceof BrightHead)
			{
				BrightHead brightHead = (BrightHead)enemy;
				if(coll.rec.intersects(brightHead.coll.rec) == true)
					return false;
			}
		}
		return true;
	}

    /**
     * Checks for collisions between the player and enemy entities and bomb explosion, updating player/enemies status accordingly
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

	/**
	 * Combines multiple lists into a single list.
	 * @param lists The lists to be combined.
	 * @return A new list containing all the elements from the input lists.
	 * @throws NullPointerException If any of the input lists is {@code null}.
	 */
	public static <Enemy> List<?> combine(final List<?>... lists) 
	{
		return Stream.of(lists).flatMap(List::stream).collect(Collectors.toList());
	}
}
