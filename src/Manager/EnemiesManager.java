package Src.Manager;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

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
    private ArrayList<Entity>       enemy;

    /**
     * Costructor class EnemiesManager
     * @param gp the game panle where works
    */
    public EnemiesManager(GamePanel gp)
    {
        this.gp = gp;
        this.mapManager = gp.mapManager;
        listFlyHeads = new ArrayList<FlyHead>();
        enemy =  new ArrayList<Entity>();
        initEnemy();
    }

    /**
     * Method to init the enemy
    */
    private void initEnemy()
    {
        for (Vector2 posFlyhead : mapManager.returnTitlePos(TitleType.FlyHead)) {
            try {
                listFlyHeads.add(new FlyHead(posFlyhead, new Vector2(32,42)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to get the list of enemies FlyHead
     * @return list og FlyHead
     */
    public ArrayList<FlyHead> GetListFlyHeads(){ return listFlyHeads; }

    /**
     * Method to update the enemies during the game
    */
    public void Update()
    {
        for (FlyHead flyHead : listFlyHeads) {
            flyHead.Update(gp);
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
    }

    /**
     * Method to updates the state of the game when a FlyHead object changes its state
     * @param o The observable object that this observer is registered to
     * @param arg The argument passed to the `notifyObservers` method
     */
    @Override
    public void update(Observable o, Object arg) 
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
}
