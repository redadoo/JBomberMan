package Src.Manager;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Src.Entity.Alarm;
import Src.Main.GamePanel;
import Src.Manager.TitleManager.TitleType;
import Src.lib.Vector2;

/**
 * Class ObjectManager to manage the various aboject on the map
 */
public class ObjectManager implements Observer 
{
	
	private GamePanel           gp;
	private ArrayList<Alarm>    listAlarm;
	private Alarm				tunnel;

	/**
	 * Costructor class ObjectManager
	 * @param gp The GamePanel instance associated with the ObjectManager
	 */
	public ObjectManager(GamePanel gp)
	{
		this.gp = gp;
		initObject();
	}

	/**
	 * Method to init all abojects
	*/
	public void initObject()
	{
		listAlarm = new ArrayList<Alarm>();
		tunnel = null;
		for (Vector2 posAlarm : gp.mapManager.returnTitlePos(TitleType.Alarm)) 
		{
			if (gp.mapManager.returnTitlePos(TitleType.Tunnel).contains(posAlarm))
				listAlarm.add(new Alarm(gp,posAlarm,new Vector2(33,35),true));
			else
				listAlarm.add(new Alarm(gp,posAlarm,new Vector2(33,35),false));
		}
	}

	/**
	 * Method to update alla the alarms during the game
	*/
	public void Update()
	{
		for (Alarm alarm : listAlarm)
			alarm.Update();
		if (tunnel != null && isLevelFinish())
			gp.gameManager.win();
	}

	/**
	 * Method to draw all the alarms
	 * @param g2 The Graphics2D object on which the game map and titles will be drawn
	*/
	public void Draw(Graphics2D g2)
	{
		for (Alarm alarm : listAlarm)
			alarm.Draw(g2);
		if (tunnel != null)
			tunnel.Draw(g2);
	}

	/**
	 * Method to get the list of alarms
	 * @return the list of alarms
	*/
	public ArrayList<Alarm> getAlarmList(){ return listAlarm; }

	/**
	 * Method to sets the collision property of the title at the alarm's position to false
	 * @param alarm The alarm object whose position is used to find the corresponding title
	 */
	public void setCollideFormAlarms( Alarm alarm)
	{
		for (Vector2 posAlarm : gp.mapManager.returnTitlePos(TitleType.Alarm)) 
		{
			if (posAlarm == alarm.pos)
				gp.mapManager.GetTitleFromPos(alarm.pos,alarm.size).collision = false;
		}
	}

    /**
     * Methos that updates the state of the game when an Alarm object changes its state
     * @param o The observable object that this observer is registered to
     * @param arg The argument passed to the `notifyObservers` method
     */
	@Override
	public void update(Observable o, Object arg) 
    {
		for (Alarm alarm : listAlarm) {
			if ((Alarm)arg == alarm)
			{
				if (!alarm.getIsTunnel())
				{
					setCollideFormAlarms(alarm);
					listAlarm.remove(alarm);
				}
				else
				{
					setCollideFormAlarms(alarm);
					listAlarm.remove(alarm);
					tunnel = alarm;
					alarm.setSprite(alarm.spriteVector.get(4));
				}
				return ;
			} 
		}
	}

	public boolean isLevelFinish()
	{
		Vector2 playerTitlePos = gp.mapManager.GetTitleFromRec(gp.player.coll.rec).matrixPos;
		Vector2 tunnelTitlePos = gp.mapManager.GetTitleFromRec(tunnel.coll.rec).matrixPos;
		
		if(gp.enemiesManager.GetListFlyHeads().size() == 0 && playerTitlePos.equals(tunnelTitlePos))
			return true;
		return false;
	}
}
