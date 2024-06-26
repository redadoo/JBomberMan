package src.Manager;

import java.io.IOException;

import src.Entity.Player;
import src.Main.GamePanel;
import src.User.User;
import src.User.UserController;
import src.User.UserView;
import src.Utils.CollisionChecker;
import src.Utils.HUD;
import src.Utils.ManageFile;

/**
 * Class GameManger that manages the game status and its saves
 */
public class GameManager 
{

	private GamePanel		gp;             // Reference to the GamePanel
    private User 			user;           // User object representing the player
    private UserView 		userView;       // UserView object for displaying user information
    private ManageFile 		manageFile;     // ManageFile object for managing file operations
    private GameState 		myGamestate;    // Enum representing different game states
    public 	UserController 	userController; // UserController for handling user interactions

	/***
	 * States of game
	*/
	private enum GameState
	{
		Menu,
		Game,
		OnChangeLevel,
		Finish
	}

	/**
	 * Costructor class GameManager
     * @param gp the GamePanel reference	
	*/
	public GameManager(GamePanel gp)
	{
		this.gp = gp;
		
		user = new User("");
		userView = new UserView();
		userController = new UserController(user,userView);
		
		manageFile = new ManageFile("saves/save");

		myGamestate = GameState.Menu;

		initData();
	}

	/**
	 * Method to check if the status of game is 'Menu'
	 * @return game status
	*/
	public Boolean isOnChangeLevel(){return myGamestate == GameState.OnChangeLevel;}
	
	/**
	 * Method to check if the status of game is 'Menu'
	 * @return game status
	*/
	public Boolean isOnMenu(){return myGamestate == GameState.Menu;}

	/**
	 * Method to check if the status of game is 'Game'
	 * @return game status
	*/
	public Boolean isOnGame(){return myGamestate == GameState.Game;}

	/**
	 * Method to check if the status of game is 'Finish'
	 * @return game status
	*/
	public Boolean isOnFinish(){return myGamestate == GameState.Finish;}

	/**
	 * Method to update the game lost
	*/
	public void death()
	{
		userController.setUsermatchLose(userController.getUsermatchLose() + 1);
		try {
			manageFile.updateFile(userController);
		} catch (IOException e) {
			e.printStackTrace();
		}
		myGamestate = GameState.Finish;
	}

	/**
	 * Method to update the game wone
	*/
	public void win()
	{
		userController.setUsermatchWon(userController.getUsermatchWon() + 1);
		try {
			manageFile.updateFile(userController);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (userController.getUserLevel() == 1)
			myGamestate = GameState.OnChangeLevel;
		else 
			myGamestate = GameState.Finish;
		userController.updateView();
	}

	/**
	 * Method to initialize data
	*/
	public void initData()
	{
		try {
			myGamestate = GameState.Menu;
			
			if (manageFile.haveSave() == false)
			{
				manageFile.initFile(userController);

			}
			else
			{
				manageFile.returnUserValue(userController);
				manageFile.setAvatarColor(userController);
				manageFile.updateFile(userController);
			}
			userController.updateView();
			myGamestate = GameState.Game;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to set the avatar of player 
	*/
	public void setAvatar(Player player){ player.Avatar = user.getAvatar(); }

	/**
	 * Method to update the user data
	 * @throws IOException
	*/
	public void updateUserData(Player player) throws IOException
	{ updatePoints(player); }
	
	/**
	 * Method to update points of player
	 * @throws IOException
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
	 * @throws IOException
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
	public void reset()
	{
		myGamestate = GameState.Finish;
		try {
			manageFile.updateFile(userController);
		} catch (IOException e) {
			e.printStackTrace();
		}
		gp.mapManager = new TitleManager(gp, "/Resource/Maps/map_0");
		gp.player = new Player(gp);
		gp.enemiesManager = new EnemiesManager(gp);
		gp.cChecker = new CollisionChecker(gp);
		gp.hud = new HUD(gp);
		gp.ObjectManager = new ObjectManager(gp);
		userController.setUsermatch(userController.getUsermatch() + 1);
		userController.setUserLevel(1);
		myGamestate = GameState.Game;
		userController.updateView();
	}

	/**
	 * Method for Change level
	 */
	public void nextLevel()
	{
		myGamestate = GameState.Finish;
		userController.setUserLevel(userController.getUserLevel() + 1);
		userController.setUserLevel(2);
		gp.mapManager = new TitleManager(gp, "/Resource/Maps/map_1");
		gp.enemiesManager = new EnemiesManager(gp);
		gp.cChecker = new CollisionChecker(gp);
		gp.ObjectManager = new ObjectManager(gp);
		gp.player.resetPos();
		myGamestate = GameState.Game;
		userController.updateView();
	}

	/**
	 * Method to close game
	*/
	public void closeGame()
	{
		try {
			manageFile.updateFile(userController);
		} catch (IOException e) {
			e.printStackTrace();
		}			
		userController.updateView();
		System.exit(0);
	}
}