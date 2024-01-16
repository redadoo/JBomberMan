package Src.Manager;

import java.io.IOException;

import Src.Entity.Player;
import Src.Main.GamePanel;
import Src.User.User;
import Src.Utils.ManageFile;

/**
 * GameManger
 */
public class GameManager 
{

    GamePanel   gp;
    User		user = new User("");
    ManageFile	manageFile = new ManageFile("saves/save");

    public GameManager(GamePanel gp)
    {
        this.gp = gp;
        initData();
 
    }

    public void death()
    {

    }

    public void win()
    {
        
    }

    public void initData()
    {
        try {
            if (manageFile.haveSave() == false)
                manageFile.initFile(user);
            manageFile.returnUserValue(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void transferData(Player player)
    {
        player.Avatar = user.getAvatar();
    }
}