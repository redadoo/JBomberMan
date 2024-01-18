package Src.Manager;

import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Scanner;

import Src.Entity.Player;
import Src.Main.GamePanel;
import Src.User.User;
import Src.User.UserController;
import Src.User.UserView;
import Src.Utils.CollisionChecker;
import Src.Utils.HUD;
import Src.Utils.KeyHandler;
import Src.Utils.ManageFile;

/**
 * Class GameManger that manages the game status and its saves
 */
public class GameManager 
{

	private GamePanel   	gp;
	private KeyHandler  	keyH;
	private User			user;
	private UserView 		userView;
	private ManageFile		manageFile;
	private GameState   	myGamestate;
	private UserController	userController;

	/***
	 * States of game
	*/
	private enum GameState
	{
		Menu,
		Game,
		Finish
	}

	/**
	 * Costructor class GameManager
	 * @param gp the GamePanel
	*/
	public GameManager(GamePanel gp)
	{
		this.gp = gp;
		this.keyH = gp.keyh;
		
		user = new User("");
		userView = new UserView();
		userController = new UserController(user,userView);
		
		manageFile = new ManageFile("saves/save");

		myGamestate = GameState.Menu;

		initData();
	}

	/**
	 * Method to check if the status of game is 'Menu'
	 * @return 
	*/
	public Boolean isOnMenu(){return myGamestate == GameState.Menu;}

	/**
	 * Method to check if the status of game is 'Game'
	 * @return 
	*/
	public Boolean isOnGame(){return myGamestate == GameState.Game;}

	/**
	 * Method to check if the status of game is 'Finish'
	 * @return 
	*/
	public Boolean isOnFinish(){return myGamestate == GameState.Finish;}

	/**
	 * Method to check if the player want continue to play
	*/
	public void death()
	{
		myGamestate = GameState.Finish;
	}

	/**
	 * Method to check if the player want continue to play
	*/
	public void win()
	{
		myGamestate = GameState.Finish;
		
		Scanner myObj = new Scanner(System.in);
		if (userController.getUserLevel() == 1)
		{
			System.out.println("Do you want go to the next level?");
			System.out.println("yes | no");
			String continueChoice = myObj.nextLine();
			if (continueChoice.contains("yes") || continueChoice.contains("y"))
				nextLevel();
			else if(continueChoice.contains("no") || continueChoice.contains("n"))
				System.exit(0);	
		}
		else if(userController.getUserLevel() == 2)
		{
			System.out.println("Do you want go to the level 1?");
			System.out.println("yes | no");
			String continueChoice = myObj.nextLine();
			if (continueChoice.contains("yes") || continueChoice.contains("y"))
				Reset();
			else if(continueChoice.contains("no") || continueChoice.contains("n"))
				System.exit(0);	
		}
	}

	/**
	 * Method to initialize data
	*/
	public void initData()
	{
		try {

			if (manageFile.haveSave() == false)
				manageFile.initFile(userController);
		
			manageFile.returnUserValue(userController);

			userController.updateView();
			myGamestate = GameState.Game;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to set the avatar of player 
	*/
	public void setAvatar(Player player)
	{
		player.Avatar = user.getAvatar();
	}

	/**
	 * Method to update the user data
	*/
	public void updateUserData(Player player) throws IOException
	{	
		updatePoints(player);
	}
	
	/**
	 * Method to update points of player
	*/
	public void updatePoints(Player player) throws IOException
	{
		if (userController.getUserPoints() != player.getPoint())
		{
			userController.setUserPoints(player.getPoint()); 
			manageFile.updateFile(userController);			
			userController.updateView();
		}
	}

	/**
	 * Method to update the number of game lost
	*/
	public void updateGameLose() throws IOException
	{
		userController.setUsermatchLose(userController.getUsermatchLose() + 1);
		manageFile.updateFile(userController);			
		userController.updateView();
	}

	/**
	 * Method to reset the game
	 */
	public void Reset()
	{
		myGamestate = GameState.Finish;

		gp.mapManager = new TitleManager(gp, "/Resource/Maps/map_0");
		gp.player = new Player(gp);
		gp.enemiesManager = new EnemiesManager(gp);
		gp.cChecker = new CollisionChecker(gp);
		gp.hud = new HUD(gp);
		gp.ObjectManager = new ObjectManager(gp);

		myGamestate = GameState.Game;
	}

	/**
	 * Method for Change level
	 */
	public void nextLevel()
	{
		myGamestate = GameState.Finish;

		if(userController.getUserLevel() == 2)
			gp.mapManager = new TitleManager(gp, "/Resource/Maps/map_0");
		else if(userController.getUserLevel() == 1)
			gp.mapManager = new TitleManager(gp, "/Resource/Maps/map_1");
		gp.player = new Player(gp);
		gp.enemiesManager = new EnemiesManager(gp);
		gp.cChecker = new CollisionChecker(gp);
		gp.hud = new HUD(gp);
		gp.ObjectManager = new ObjectManager(gp);

		myGamestate = GameState.Game;
		userController.updateView();
	}
}