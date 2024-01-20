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
import Src.Sound.AudioManager;
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
	public	Thread				audioThread;
	public	AudioManager		audioManager;
	
    /**
     * Costructor class GamePanel
    */
	public GamePanel()
	{
		FPS = 60;
		elapsedTime = 0;

		audioManager = AudioManager.getInstance();

		keyh = new KeyHandler();
		
		gameManager = new GameManager(this);
			
		mapManager = new TitleManager(this, "/Resource/Maps/map_0");
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
     * Method that start the main game loop
     * Updates and repaints the game at a specified FPS
    */
	@Override
	public void run() 
	{
		audioManager.play("Resource/Music/Super-Bomberman-Level-1-_ost-snes_.wav");

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
     * @throws IOException
    */
	public void Update() throws IOException
	{
		if(gameManager.isOnChangeLevel() || gameManager.isOnFinish())
			hud.UpdateHud();
		
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

		if(gameManager.isOnChangeLevel() || gameManager.isOnFinish())
			hud.DrawChangeLevel(g2);

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
