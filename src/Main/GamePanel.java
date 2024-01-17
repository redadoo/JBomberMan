package Src.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JPanel;

import Src.Entity.Player;
import Src.Manager.EnemiesManager;
import Src.Manager.GameManager;
import Src.Manager.ObjectManager;
import Src.Manager.TitleManager;
import Src.Utils.CollisionChecker;
import Src.Utils.HUD;
import Src.Utils.KeyHandler;

/**
 * GamePanel class that handles the game loop, player input, and rendering. This class also manages the game's title map,
 * player entity, and collision checking.
 */
public class GamePanel extends JPanel implements Runnable
{

	public	HUD					hud;
	private int                 FPS;
	public	KeyHandler          keyh;
	public	Player              player;
	public	long 				lastTime;
	public 	CollisionChecker	cChecker;
	private Thread              gameThread;
	public 	TitleManager        mapManager;
	public	GameManager			gameManager;
	public	long 				currentTime;
	public	double				elapsedTime;
	public	EnemiesManager		enemiesManager;
	public	ObjectManager		ObjectManager;

    /**
     * Costructor class GamePanel
     * Initializes the panel properties, sets up the game's dimensions, background color,
     * and key listener for player input.
    */
	public GamePanel()
	{
		FPS = 60;
		elapsedTime = 0;

		keyh = new KeyHandler();
		mapManager = new TitleManager(this, "/Resource/Maps/map_0");
		gameManager = new GameManager(this);
		player = new Player(this);
		enemiesManager = new EnemiesManager(this);
		cChecker = new CollisionChecker(this);
		hud = new HUD(this);
		ObjectManager = new ObjectManager(this);

		this.setPreferredSize(new Dimension(512, 470));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyh);
		this.setFocusable(true);
	}

    /**
     * Method that starts the game thread for the game loop
    */
	public void StartGameThread()
	{
		gameThread = new Thread(this);
		gameThread.start();
	}

    /**
     * Method that start the main game loop.
     * Updates and repaints the game at a specified FPS.
    */
	@Override
	public void run() 
	{
		double drawInterval = 1000000000/FPS; //To choose the frame rate
		double delta = 0;
		lastTime = System.nanoTime();

		while (gameThread != null) 
		{
			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if(delta >= 1)
			{
				try {
					Update();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				elapsedTime += delta / FPS;

				repaint();

				delta--;
			}
		}
	}

    /**
     * Method that updates the game logic.
     * Calls the update method for the player entity.
     * @throws IOException
     */
	public void Update() throws IOException
	{
		if(gameManager.isOnFinish())
			gameManager.updateFinishGame();
		
		else if(gameManager.isOnGame())
		{
			cChecker.Update();
			player.Update();
			enemiesManager.Update();	
			ObjectManager.Update();
		}
	}

    /**
     * Method that overrides the paintComponent method to render the game components
     * @param g The Graphics object used for rendering
    */
   	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;

		if(gameManager.isOnFinish())
			gameManager.drawFinishGame();

		else if(gameManager.isOnGame())
		{
			hud.Draw(g2);
			mapManager.Draw(g2);
			ObjectManager.Draw(g2);
			player.Draw(g2);
			enemiesManager.Draw(g2);
		}
			
		g2.dispose();
	}

}
