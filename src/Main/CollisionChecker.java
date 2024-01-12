package Src.Main;

import Src.lib.Collider;

/**
 * Class CollisionChecker to check if two entity/Collider make contact
 */
public class CollisionChecker 
{
	
	GamePanel gp;

	/**
	 * Costructor class CollisionChecker
	 * @param gp
	 */
	public CollisionChecker(GamePanel gp)
	{
		this.gp = gp;
	}

	/**
	 * Class CheckTitle to check if two title make an intersect
	 * @param coll
	 * @return
	 */
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
	 * Class CheckTitleForEnenmy to check if two enemies make contact
	 * @param coll
	 * @return
	 */
	public Boolean CheckTitleForEnenmy(Collider coll)
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
}
