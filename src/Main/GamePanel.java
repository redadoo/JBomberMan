package Src.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JPanel;

import Src.Entity.Player;
import Src.Manager.EnemiesManager;
import Src.Manager.TitleManager;

/**
 * GamePanel class that handles the game loop, player input, and rendering. This class also manages the game's title map,
 * player entity, and collision checking.
 */
public class GamePanel extends JPanel implements Runnable
{

	private int                 FPS = 60;
	private Thread              gameThread;
	private KeyHandler          keyh = new KeyHandler();
	public 	TitleManager        mapManager = new TitleManager(this);
	public 	CollisionChecker	cChecker = new CollisionChecker(this);
	private Player              player = new Player(this, keyh,mapManager);
	private EnemiesManager		enemiesManager = new EnemiesManager(this,mapManager);
	public long 				lastTime;
	public long 				currentTime;
	public double				elapsedTime = 0;  // Aggiunta della variabile per il tempo trascorso in secondi

    /**
     * Costructor class GamePanel
     * Initializes the panel properties, sets up the game's dimensions, background color,
     * and key listener for player input.
     */
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(512,480));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyh);
		this.setFocusable(true);
	}

    /**
     * Starts the game thread for the game loop
     */
	public void StartGameThread()
	{
		gameThread = new Thread(this);
		gameThread.start();
	}

    /**
     * The main game loop.
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

				//repaint call paintComponent on each iteration 
				repaint();
				delta--;
			}
		}
	}

    /**
     * Updates the game logic.
     * Calls the update method for the player entity.
     * @throws IOException
     */
	public void Update() throws IOException
	{
		player.Update();

		enemiesManager.Update();
	}

    /**
     * Overrides the paintComponent method to render the game components
     * @param g The Graphics object used for rendering.
     */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;
	
		mapManager.Draw(g2);
		
		player.Draw(g2);

		enemiesManager.Draw(g2);
		
		g2.dispose();
	}
}
