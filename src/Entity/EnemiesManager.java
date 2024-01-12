package Src.Entity;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

import Src.Main.GamePanel;
import Src.Title.TitleManager;
import Src.Title.TitleManager.TitleType;
import Src.lib.Vector2;

public class EnemiesManager {
    
    private GamePanel               gp;
    private TitleManager            mapManager;
    private ArrayList<FlyHead>      listFlyHeads;
    private ArrayList<Entity>       enemy;

    public EnemiesManager(GamePanel gp,TitleManager mapManager)
    {
        this.gp = gp;
        this.mapManager = mapManager;
        listFlyHeads = new ArrayList<FlyHead>();
        enemy =  new ArrayList<Entity>();
        initEnemy();
    }

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


    public void Update()
    {
        for (FlyHead flyHead : listFlyHeads) {
            flyHead.Update(gp);
        }
    }

    public void Draw(Graphics2D g2)
    {
        for (FlyHead flyHead : listFlyHeads) {
            flyHead.Draw(g2);
        }
    }
}
