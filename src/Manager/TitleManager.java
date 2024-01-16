package Src.Manager;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Src.Main.GamePanel;
import Src.Title.Title;
import Src.lib.Collider;
import Src.lib.Vector2;

/**
 * Class TitleManager manages the game map and titles.
 * It handles loading map data, drawing the map, and providing player position
 * information.
 */
public class TitleManager 
{

	// Reference to the GamePanel instance.
	public GamePanel gp;

	// List of Title objects representing different map titles.
	public ArrayList<Title> titles;

	// 2D array representing the game map based on map titles.
	public Title[][] mapTitleNum;

	// Starting position for map tile rendering.
	private Vector2 StartPos = new Vector2(48, 90);

	/**
	 * The differt type of title 
	 */
	public static enum TitleType 
	{
		Grass,
		Obstacle,
		Player,
		Alarm,
		FlyHead,
		NewEnemy,
		Tunnel
	}

	/**
	 * Constructor class TitleManager that initializes the GamePanel reference,
	 * loads title images, and sets up the
	 * initial map.
	 * 
	 * @param gp The GamePanel instance associated with the TitleManager.
	 */
	public TitleManager(GamePanel gp) 
	{
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
	public void getTitleImage() 
	{
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
	 * 
	 * @param filePath The file path of the map data to be loaded.
	 */
	public void LoadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (col < 13 && row < 11) 
			{
				String line = br.readLine();
				while (col < 13) 
				{
					String numbers[] = line.split(" ");

					int num = Integer.parseInt(numbers[col]);

					mapTitleNum[col][row] = new Title(new Vector2(StartPos.x + col * 32, StartPos.y + row * 32),
							new Vector2(32, 32));

					if (num == 0 || num >= 2)
						mapTitleNum[col][row].sprite = ImageIO.read(getClass().getResourceAsStream("/Resource/MapTitles/grass_title.png"));
					if (num == 1) 
					{
						mapTitleNum[col][row].sprite = ImageIO.read(getClass().getResourceAsStream("/Resource/MapTitles/obstacle.png"));
						mapTitleNum[col][row].collision = true;
					}
					if (num == 3)
					{
						mapTitleNum[col][row].collision = true;
					}
					mapTitleNum[col][row].mapTitle = num;
					mapTitleNum[col][row].titleType = TitleType.values()[num];
					mapTitleNum[col][row].matrixPos = new Vector2(col,row);
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
	public void Draw(Graphics2D g2) 
	{
		// Draw the background tile (assuming it's the first tile in the 'titles' list).
		g2.drawImage(titles.get(0).sprite, 0, 60, 512, 412, null);

		// Draw individual map tiles based on their positions in the 'mapTitleNum'
		// matrix.

		
		for (int i = 0; i < mapTitleNum.length; i++) 
		{
			for (int j = 0; j < mapTitleNum[i].length; j++) 
			{
				g2.drawImage(mapTitleNum[i][j].sprite, StartPos.x + i * 32, StartPos.y + j * 32, 32, 32, null);
				g2.drawRect(mapTitleNum[i][j].coll.pos.x, mapTitleNum[i][j].coll.pos.y, mapTitleNum[i][j].coll.rec.width, mapTitleNum[i][j].coll.rec.height);
			}
		}
	}

	/**
	 * Function to returns the position (Vector2) of the player on the game map.
	 * 
	 * @return The Vector2 representing the player's position. If not found, returns
	 *         a default Vector2.
	 */
	public Vector2 ReturnPlayerPos() {
		for (int i = 0; i < mapTitleNum.length; i++) {
			for (int j = 0; j < mapTitleNum[i].length; j++) {
				if (mapTitleNum[i][j].mapTitle == 2)
					return mapTitleNum[i][j].pos;
			}
		}
		// Return default Vector2 if player position is not found.
		return new Vector2();
	}

	public Title GetTitleFromRec(Rectangle rec) 
	{
		for (int i = 0; i < mapTitleNum.length; i++) 
		{
			for (int j = 0; j < mapTitleNum[i].length; j++) 
			{
				if (mapTitleNum[i][j].mapTitle == 0 && mapTitleNum[i][j].coll.rec.intersects(rec)) 
					return mapTitleNum[i][j];
			}
		}
		return mapTitleNum[0][0];
	}

	public Title GetTitleFromPos(Vector2 pos) 
	{
		for (int i = 0; i < mapTitleNum.length; i++)
		{
			for (int j = 0; j < mapTitleNum[i].length; j++)
			{
				if(mapTitleNum[i][j].pos == pos || mapTitleNum[i][j].coll.rec.contains(pos.x, pos.y))
				{
					return mapTitleNum[i][j];
				}
			}
		}
		return null;
	}

	public ArrayList<Vector2> returnTitlePos(TitleType indexEnemy) 
	{
		ArrayList<Vector2> enemiesPos = new ArrayList<Vector2>();

		for (int i = 0; i < mapTitleNum.length; i++) 
		{
			for (int j = 0; j < mapTitleNum[i].length; j++) 
			{
				if (mapTitleNum[i][j].titleType == indexEnemy) 
					enemiesPos.add(mapTitleNum[i][j].pos);
			}
		}

		return enemiesPos;
	}

	public ArrayList<Vector2> getRangedTitleIndex(Vector2 pos) 
	{
		ArrayList<Vector2> rangeTitles = new ArrayList<Vector2>();

		Title title = GetTitleFromPos(pos);

		for (int i = 0; i < mapTitleNum.length; i++) 
		{
			for (int j = 0; j < mapTitleNum[i].length; j++) 
			{

				if (mapTitleNum[i][j].titleType != TitleType.Obstacle) 
				{
					Vector2 indexs = title.matrixPos;
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
					return mapTitleNum[i][j].pos;
			}
		}
		return null;
	}

	public boolean isEntityInsidePerimeter(Collider coll)
	{
		if(coll.pos.y > 90 && coll.pos.y <  420 && coll.pos.x > 45 && coll.pos.x < 435)
			return true;
		return false;
	}

	public ArrayList<Vector2> getRangedTitlePos(Vector2 pos) 
	{
		ArrayList<Vector2> rangeTitles = new ArrayList<Vector2>();

		Title title = GetTitleFromPos(pos);

		for (int i = 0; i < mapTitleNum.length; i++) 
		{
			for (int j = 0; j < mapTitleNum[i].length; j++) 
			{
				if (mapTitleNum[i][j].titleType != TitleType.Obstacle) 
				{
					Vector2 indexs = title.matrixPos;
					if (i == indexs.x + 1 && j == indexs.y && !rangeTitles.contains(mapTitleNum[i][j].pos))
						rangeTitles.add(mapTitleNum[i][j].pos);
					if (i == indexs.x - 1 && j == indexs.y && !rangeTitles.contains(mapTitleNum[i][j].pos))
						rangeTitles.add(mapTitleNum[i][j].pos);
					if (i == indexs.x && j == indexs.y + 1 && !rangeTitles.contains(mapTitleNum[i][j].pos))
						rangeTitles.add(mapTitleNum[i][j].pos);
					if (i == indexs.x && j == indexs.y - 1 && !rangeTitles.contains(mapTitleNum[i][j].pos))
						rangeTitles.add(mapTitleNum[i][j].pos);
				}
			}
		}
		return rangeTitles;
	}
}
