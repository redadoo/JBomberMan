package Src.Title;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Src.Main.GamePanel;
import Src.lib.Vector2;

/**
 * The TitleManager class manages the game map and titles.
 * It handles loading map data, drawing the map, and providing player position
 * information.
 */
public class TitleManager {

	/** Reference to the GamePanel instance. */
	public GamePanel gp;

	/** List of Title objects representing different map titles. */
	public ArrayList<Title> titles;

	/** 2D array representing the game map based on map titles. */
	public Title[][] mapTitleNum;

	/** Starting position for map tile rendering. */
	private Vector2 StartPos = new Vector2(48, 118);

	public static enum TitleType {
		Grass,
		Obstacle,
		Player,
		Alarm,
		FlyHead,
		NewEnemy
	}

	/**
	 * Constructor for the TitleManager class.
	 * Initializes the GamePanel reference, loads title images, and sets up the
	 * initial map.
	 *
	 * @param gp The GamePanel instance associated with the TitleManager.
	 */
	public TitleManager(GamePanel gp) {
		this.gp = gp;
		titles = new ArrayList<Title>();
		getTitleImage();
		mapTitleNum = new Title[13][11];
		LoadMap("/Resource/Maps/map_0");
	}

	/**
	 * Loads images for different map titles and initializes the 'titles' list.
	 * Assumes specific titles and their corresponding images.
	 */
	public void getTitleImage() {
		try {
			titles.add(new Title());
			titles.get(0).sprite = ImageIO.read(getClass().getResourceAsStream("/Resource/MapTitles/perimeter.png"));
			titles.get(0).size.x = 512;
			titles.get(0).size.y = 412;

			titles.add(new Title());
			titles.get(1).sprite = ImageIO.read(getClass().getResourceAsStream("/Resource/MapTitles/grass_title.png"));
			titles.get(1).size.x = 32;
			titles.get(1).size.y = 32;

			titles.add(new Title());
			titles.get(2).sprite = ImageIO.read(getClass().getResourceAsStream("/Resource/MapTitles/obstacle.png"));
			titles.get(2).size.x = 32;
			titles.get(2).size.y = 32;
			titles.get(2).collision = true;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads a game map from a specified file path and populates the 'mapTitleNum'
	 * array.
	 * Assumes a specific format in the file, where numbers represent different map
	 * titles.
	 * 
	 * @param filePath The file path of the map data to be loaded.
	 */
	public void LoadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (col < 13 && row < 11) {
				String line = br.readLine();
				while (col < 13) {
					String numbers[] = line.split(" ");

					int num = Integer.parseInt(numbers[col]);

					mapTitleNum[col][row] = new Title(new Vector2(StartPos.x + col * 32, StartPos.y + row * 32),
							new Vector2(32, 32));

					if (num == 0 || num >= 2)
						mapTitleNum[col][row].sprite = ImageIO
								.read(getClass().getResourceAsStream("/Resource/MapTitles/grass_title.png"));

					else if (num == 1) {
						mapTitleNum[col][row].sprite = ImageIO
								.read(getClass().getResourceAsStream("/Resource/MapTitles/obstacle.png"));
						mapTitleNum[col][row].collision = true;
					}
					mapTitleNum[col][row].mapTitle = num;
					mapTitleNum[col][row].titleType = TitleType.values()[num];
					col++;
				}
				if (col == 13) {
					col = 0;
					row++;
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Draws the game map and its titles on the provided Graphics2D object.
	 * 
	 * @param g2 The Graphics2D object on which the game map and titles will be
	 *           drawn.
	 */
	public void Draw(Graphics2D g2) {
		// Draw the background tile (assuming it's the first tile in the 'titles' list).
		g2.drawImage(titles.get(0).sprite, 0, 85, 512, 412, null);

		// Draw individual map tiles based on their positions in the 'mapTitleNum'
		// matrix.
		for (int i = 0; i < mapTitleNum.length; i++) {
			for (int j = 0; j < mapTitleNum[i].length; j++) {
				g2.drawImage(mapTitleNum[i][j].sprite, StartPos.x + i * 32, StartPos.y + j * 32, 32, 32, null);
				if (mapTitleNum[i][j].mapTitle == 1)
					g2.drawRect(mapTitleNum[i][j].coll.rec.x, mapTitleNum[i][j].coll.rec.y,
							mapTitleNum[i][j].coll.rec.width, mapTitleNum[i][j].coll.rec.height);
			}
		}
	}

	public Vector2 getTitleIndex(Title title) {
		for (int i = 0; i < mapTitleNum.length; i++) {
			for (int j = 0; j < mapTitleNum[i].length; j++) {
				if (mapTitleNum[i][j] == title) {
					return (new Vector2(i, j));
				}
			}
		}
		return null;
	}

	/**
	 * Returns the position (Vector2) of the player on the game map.
	 * Searches for the player's position by iterating through the map tiles.
	 * Assumes that the player is represented by the map title value 2.
	 * 
	 * @return The Vector2 representing the player's position. If not found, returns
	 *         a default Vector2.
	 */
	public Vector2 ReturnPlayerPos() {
		for (int i = 0; i < mapTitleNum.length; i++) {
			for (int j = 0; j < mapTitleNum[i].length; j++) {
				if (mapTitleNum[i][j].mapTitle == 2) {
					return mapTitleNum[i][j].pos;
				}
			}
		}
		// Return default Vector2 if player position is not found.
		return new Vector2();
	}

	public Title GetTitleFromRec(Rectangle rec) {
		for (int i = 0; i < mapTitleNum.length; i++) {
			for (int j = 0; j < mapTitleNum[i].length; j++) {
				if (mapTitleNum[i][j].mapTitle == 0 && mapTitleNum[i][j].coll.rec.intersects(rec)) {
					return mapTitleNum[i][j];
				}
			}
		}
		return mapTitleNum[0][0];
	}

	public Title GetTitleFromPos(Vector2 pos) {
		for (int i = 0; i < mapTitleNum.length; i++) {
			for (int j = 0; j < mapTitleNum[i].length; j++) {
				if (mapTitleNum[i][j].mapTitle == 0 && mapTitleNum[i][j].coll.pos == pos) {
					return mapTitleNum[i][j];
				}
			}
		}
		return mapTitleNum[0][0];
	}

	public ArrayList<Vector2> returnEnemyPos(TitleType indexEnemy) {
		ArrayList<Vector2> enemiesPos = new ArrayList<Vector2>();

		for (int i = 0; i < mapTitleNum.length; i++) {
			for (int j = 0; j < mapTitleNum[i].length; j++) {
				if (mapTitleNum[i][j].titleType == indexEnemy) {
					enemiesPos.add(mapTitleNum[i][j].pos);
				}
			}
		}

		return enemiesPos;
	}

	public ArrayList<Vector2> getRangedTitle(Vector2 pos) {

		ArrayList<Vector2> rangeTitles = new ArrayList<Vector2>();

		Title title = GetTitleFromPos(pos);

		for (int i = 0; i < mapTitleNum.length; i++) {
			for (int j = 0; j < mapTitleNum[i].length; j++) {

				if(mapTitleNum[i][j].titleType != TitleType.Obstacle)
				{
					Vector2 indexs = getTitleIndex(title);
					if (i == indexs.x + 1 && j == indexs.y && !rangeTitles.contains(new Vector2(i, j)))
						rangeTitles.add(new Vector2(i, j));
					if (i == indexs.x - 1 && j == indexs.y && !rangeTitles.contains(new Vector2(i, j)))
						rangeTitles.add(new Vector2(i, j));
					if (i == indexs.x && j == indexs.y + 1 && !rangeTitles.contains(new Vector2(i, j)))
						rangeTitles.add(new Vector2(i, j));
					if (i == indexs.x && j == indexs.y - 1 && !rangeTitles.contains(new Vector2(i, j)))
						rangeTitles.add(new Vector2(i, j));
				}
			}
		}
		return rangeTitles;
	}

	public Vector2 returnPosFromIndex(Vector2 index)
	{
		for (int i = 0; i < mapTitleNum.length; i++) 
		{
			for (int j = 0; j < mapTitleNum[i].length; j++) 
			{
				if (i == index.x && j == index.y) 
				{
					return mapTitleNum[i][j].pos;
				}
			}
		}
		return null;
	}
}
