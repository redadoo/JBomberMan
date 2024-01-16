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
     * @param gp
     * @param mapManager
     */
    public EnemiesManager(GamePanel gp,TitleManager mapManager)
    {
        this.gp = gp;
        this.mapManager = mapManager;
        listFlyHeads = new ArrayList<FlyHead>();
        enemy =  new ArrayList<Entity>();
        initEnemy();
    }

    /**
     * Function to init the enemy
     */
    private void initEnemy()
    {
        for (Vector2 posFlyhead : mapManager.returnEnemyPos(TitleType.FlyHead)) {
            try {
                listFlyHeads.add(new FlyHead(posFlyhead, new Vector2(32,42)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<FlyHead> GetListFlyHeads(){ return listFlyHeads; }

    /**
     * Function to update the enemies during the game
     */
    public void Update()
    {
        for (FlyHead flyHead : listFlyHeads) {
            flyHead.Update(gp);
        }
    }

    /**
     * Function to draw the anemys sprites
     * @param g2
     */
    public void Draw(Graphics2D g2)
    {
        for (FlyHead flyHead : listFlyHeads) {
            flyHead.Draw(g2);
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        for (FlyHead flyHead : listFlyHeads) {
            if ((FlyHead)arg == flyHead)
            {
                listFlyHeads.remove(flyHead);
                return ;
            } 
        }
    }
}
