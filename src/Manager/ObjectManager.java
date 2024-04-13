package src.Manager;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import src.Entity.Alarm;
import src.Entity.PowerUp;
import src.Main.GamePanel;
import src.Manager.TitleManager.TitleType;
import src.lib.Vector2;

/**
 * The ObjectManager class is responsible for managing various objects on the game map
 */
public class ObjectManager implements Observer 
{
	
    private GamePanel 			gp;             // Reference to the GamePanel
    private ArrayList<Alarm> 	alarmList; 		// List of Alarm objects
    private ArrayList<PowerUp>	powerUpList;	// List of PowerUp objects
    private Alarm 				tunnel;         // The tunnel Alarm

	/**
     * Different types of power-ups
    */
	public enum PowerUpType
	{
		LifeUp,
		AddBomb,
		SpeedUp,
	}

    /**
     * Constructs an ObjectManager 
     * @param gp The GamePanel instance associated with the ObjectManager
    */
	public ObjectManager(GamePanel gp)
	{
		this.gp = gp;
		initObject();
	}


    /**
     * Initializes various objects such as alarms and power-ups on the map.
    */
	public void initObject()
	{
		alarmList = new ArrayList<Alarm>();
		powerUpList = new ArrayList<PowerUp>();

		for(int i = 0;i < getRandomNumber(2,4);i++)
		{
			try {
				powerUpList.add(new PowerUp(new Vector2(), new Vector2(32,32),getRandomNumber(0,3)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		tunnel = null;

		for (Vector2 posAlarm : gp.mapManager.returnTitlePos(TitleType.Alarm)) 
		{
			if (gp.mapManager.returnTitlePos(TitleType.Tunnel).contains(posAlarm))
				alarmList.add(new Alarm(gp,posAlarm,new Vector2(33,35),true));
			else
				alarmList.add(new Alarm(gp,posAlarm,new Vector2(33,35),false));
		}

		for (Alarm alarm : alarmList) {

			if (getRandomNumber(0,2) == 1 && !alarm.getIsTunnel())
			{
				for (PowerUp powerUp : powerUpList) {
					if (powerUp.myTitle == null)
					{
						powerUp.myTitle = alarm.getTitle();
						powerUp.pos = alarm.pos;
						break ;
					}
				}
			}
		}

	}

    /**
     * Updates the state of alarms and power-ups during the game.
    */
	public void Update()
	{
		for (Alarm alarm : alarmList)
			alarm.Update();
		if (tunnel != null && isLevelFinish())
			gp.gameManager.win();
		for (PowerUp powerUp : powerUpList) 
		{
			if (powerUp.isVisible == true)
				powerUp.Update();
			if (powerUp.myTitle == gp.player.getTitle())
			{
				switch (powerUp.myType) {
					case AddBomb:
						gp.player.addBomb();
						break;

					case LifeUp:
						gp.player.addLife();
						break ;

					case SpeedUp:
						gp.player.addSpeed();
						break;
				}
				powerUpList.remove(powerUp);
				break ;
			}
		}
	}

    /**
     * Draws alarms, the tunnel, and power-ups on the provided Graphics2D object
     * @param g2 The Graphics2D object on which the objects will be drawn
    */
	public void Draw(Graphics2D g2)
	{
		for (Alarm alarm : alarmList)
			alarm.Draw(g2);
		if (tunnel != null)
			tunnel.Draw(g2);
		for (PowerUp powerUp : powerUpList) 
		{
			if (powerUp.isVisible == true)
				powerUp.Draw(g2);
		}
	}

	/**
	 * Method to get the list of alarms
	 * @return the list of alarms
	*/
	public ArrayList<Alarm> getAlarmList(){ return alarmList; }

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
		for (Alarm alarm : alarmList) {
			if ((Alarm)arg == alarm)
			{
				for (PowerUp powerUp : powerUpList) 
				{
					if (powerUp.myTitle == alarm.getTitle())
						powerUp.isVisible = true;
				}
				if (!alarm.getIsTunnel())
				{
					setCollideFormAlarms(alarm);
					alarmList.remove(alarm);
				}
				else
				{
					setCollideFormAlarms(alarm);
					alarmList.remove(alarm);
					tunnel = alarm;
					alarm.setSprite(alarm.spriteList.get(4));
				}
				return ;
			} 
		}
	}

	/**
	 * Method that checks if all enemies are dead and if the player is above the tunnel for the next level
	 * @return true if all enemies are dead and if the player is above the tunnel 
	 */
	public boolean isLevelFinish()
	{
		Vector2 playerTitlePos = gp.mapManager.GetTitleFromRec(gp.player.coll.rec).matrixPos;
		Vector2 tunnelTitlePos = gp.mapManager.GetTitleFromRec(tunnel.coll.rec).matrixPos;
		
		if(gp.enemiesManager.getListFlyHeads().size() == 0 &&  gp.enemiesManager.getListBrightHead().size() == 0 && playerTitlePos.equals(tunnelTitlePos))
			return true;
		return false;
	}

	/**
	 * Method to generates a random integer within the specified range
	 * @param min The inclusive lower bound of the range
	 * @param max The exclusive upper bound of the range
	 * @return A random integer within the specified range
	 */
	public int getRandomNumber(int min, int max) { return (int) ((Math.random() * (max - min)) + min); }
}
