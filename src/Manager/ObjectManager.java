package Src.Manager;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Src.Entity.Alarm;
import Src.Entity.FlyHead;
import Src.Main.GamePanel;
import Src.Manager.TitleManager.TitleType;
import Src.lib.Vector2;

public class ObjectManager implements Observer {
    
    private GamePanel           gp;
    private ArrayList<Alarm>    listAlarm;

    public ObjectManager(GamePanel gp)
    {
        this.gp = gp;
        initObject();
    }

    public void initObject()
    {
        listAlarm = new ArrayList<Alarm>();
        for (Vector2 posAlarm : gp.mapManager.returnTitlePos(TitleType.Alarm)) 
        {
            listAlarm.add(new Alarm(posAlarm,new Vector2(33,35)));
        }
    }

    public void Update()
    {
        for (Alarm alarm : listAlarm) {
            alarm.Update();
        }        
    }

    public void Draw(Graphics2D g2)
    {
        for (Alarm alarm : listAlarm) {
            alarm.Draw(g2);
        }
    }

    public ArrayList<Alarm> getAlarmList(){ return listAlarm;}

    public void setCollideFormAlarms(boolean bool, Alarm alarm)
    {
        for (Vector2 posAlarm : gp.mapManager.returnTitlePos(TitleType.Alarm)) 
        {
            if (posAlarm == alarm.pos)
            {
                gp.mapManager.GetTitleFromPos(alarm.pos).collision = false;
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        for (Alarm alarm : listAlarm) {
            if ((Alarm)arg == alarm)
            {
                setCollideFormAlarms(false,alarm);
                listAlarm.remove(alarm);
                return ;
            } 
        }
    }
}
