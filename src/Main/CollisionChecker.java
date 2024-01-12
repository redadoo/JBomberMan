package Src.Main;

import Src.lib.Collider;

public class CollisionChecker {
	
	GamePanel gp;

	public CollisionChecker(GamePanel gp)
	{
		this.gp = gp;
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
