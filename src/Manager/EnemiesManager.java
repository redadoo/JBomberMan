package Src.Manager;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Src.Entity.BrightHead;
import Src.Entity.Entity;
import Src.Entity.FlyHead;
import Src.Main.GamePanel;
import Src.Manager.TitleManager.TitleType;
import Src.lib.Vector2;

/**
 * Class to manage the enemies
*/
public class EnemiesManager implements Observer 
{
	
	private GamePanel               gp;
	private TitleManager            mapManager;
	private ArrayList<FlyHead>      listFlyHeads;
	private ArrayList<BrightHead>   listBrightHead;

	/**
	 * Costructor class EnemiesManager
	 * @param gp the game panle where works
	*/
	public EnemiesManager(GamePanel gp)
	{
		this.gp = gp;
		this.mapManager = gp.mapManager;
		listFlyHeads = new ArrayList<FlyHead>();
		listBrightHead = new ArrayList<BrightHead>();
		initEnemy();
	}

	/**
	 * Method to init the enemy
	*/
	private void initEnemy()
	{
		for (Vector2 posFlyhead : mapManager.returnTitlePos(TitleType.FlyHead)) {
			try {
				listFlyHeads.add(new FlyHead(gp,posFlyhead, new Vector2(32,42)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for (Vector2 posBrightHead : mapManager.returnTitlePos(TitleType.BrightHead)) {
			try {
				listBrightHead.add(new BrightHead(gp,posBrightHead, new Vector2(30,30)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method to get the list of enemies FlyHead
	 * @return list og FlyHead
	 */
	public ArrayList<FlyHead> getListFlyHeads(){ return listFlyHeads; }


	/**
	 * Method to get the list of enemies BrightHead
	 * @return list og BrightHead
	 */
	public ArrayList<BrightHead> getListBrightHead(){ return listBrightHead; }
	
	/**
	 * Method to update the enemies during the game
	*/
	public void Update()
	{
		for (FlyHead flyHead : listFlyHeads) {
			flyHead.Update();
		}
		for (BrightHead brightHead : listBrightHead) {
			brightHead.Update();
		}
	}

	/**
	 * Method to draw the anemys sprites
	 * @param g2 the Graphics2D object that provides the drawing methods
	 */
	public void Draw(Graphics2D g2)
	{
		for (FlyHead flyHead : listFlyHeads) {
			flyHead.Draw(g2);
		}
		for (BrightHead brightHead : listBrightHead) {
			brightHead.Draw(g2);
		}
	}

	/**
	 * Method to updates the state of the game when a FlyHead object changes its state
	 * @param o The observable object that this observer is registered to
	 * @param arg The argument passed to the `notifyObservers` method
	 */
	@Override
	public void update(Observable o, Object arg) 
	{
		if(arg instanceof FlyHead)
		{
			for (FlyHead flyHead : listFlyHeads) {
				if ((FlyHead)arg == flyHead)
				{
					gp.player.addPoint(flyHead.getPointDrop());
					listFlyHeads.remove(flyHead);
					return ;
				} 
			}
		}
		else if(arg instanceof BrightHead)
		{
			for (BrightHead brightHead : listBrightHead) {
				if ((BrightHead)arg == brightHead)
				{
					if (brightHead.life == 1)
					{
						gp.player.addPoint(brightHead.getPointDrop());
						listBrightHead.remove(brightHead);
						return ;
					}
					else
					{
						brightHead.life--;
					}
				} 
			}
		}
	}

}
