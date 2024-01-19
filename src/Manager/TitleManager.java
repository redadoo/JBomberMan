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
	 * The different type of title 
	*/
	public static enum TitleType 
	{
		Grass,
		Obstacle,
		Player,
		Alarm,
		FlyHead,
		BrightHead,
		Tunnel
	}

	/**
	 * Constructor class TitleManager that initializes the GamePanel reference,
	 * loads title images, and sets up the initial map
	 * @param gp The GamePanel instance associated with the TitleManager
	 */
	public TitleManager(GamePanel gp, String filePath) 
	{
		this.gp = gp;
		titles = new ArrayList<Title>();
		getTitleImage();
		mapTitleNum = new Title[13][11];
		LoadMap(filePath);
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
	 * Loads a game map from a specified file path and populates the 'mapTitleNum'array
	 * @param filePath The file path of the map data to be loaded.
	*/
	public void LoadMap(String filePath) 
	{
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

					mapTitleNum[col][row] = new Title(new Vector2(StartPos.x + col * 32, StartPos.y + row * 32), new Vector2(32, 32));
					
					if (num == 0 || num >= 2)
						mapTitleNum[col][row].sprite = ImageIO.read(getClass().getResourceAsStream("/Resource/MapTitles/grass_title.png"));
					if (num == 1) 
						mapTitleNum[col][row].sprite = ImageIO.read(getClass().getResourceAsStream("/Resource/MapTitles/obstacle.png"));
					if (num == 1 || num == 3 || num == 6)
						mapTitleNum[col][row].collision = true;

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
	 * Method to draws the game map and its titles on the provided Graphics2D object
	 * @param g2 The Graphics2D object on which the game map and titles will be drawn
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
			}
		}
	}

	/**
	 * Method to returns the position (Vector2) of the player on the game map.
	 * @return The Vector2 representing the player's position. If not found, returns a default Vector2.
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

	/**
	 * Method that retrieves a Title object based on an intersection with a specified rectangle.
	 * @param rec The rectangle to check for intersection.
	 * @return A Title object representing the title on the map that intersects with the rectangle.
	*/
	public Title GetTitleFromRec(Rectangle rec) 
	{
		for (int i = 0; i < mapTitleNum.length; i++) 
		{
			for (int j = 0; j < mapTitleNum[i].length; j++) 
			{
				if (mapTitleNum[i][j].coll.rec.intersects(rec)) 
					return mapTitleNum[i][j];
			}
		}
		return mapTitleNum[0][0];
	}

	/**
	 * Method that retrieves a Title object based on a specified position.
	 * @param pos The position for which to retrieve the corresponding Title object.
	 * @return A Title object representing the title on the map at the specified position, or null if not found.
	*/
	public Title GetTitleFromPos(Vector2 pos, Vector2 size) 
	{
		pos = new Vector2(pos.x + (size.x) / 2,pos.y +(size.y / 2));

		for (int i = 0; i < mapTitleNum.length; i++)
		{
			for (int j = 0; j < mapTitleNum[i].length; j++)
			{
				if (mapTitleNum[i][j].coll.rec.contains(pos.x, pos.y))
				{
					return mapTitleNum[i][j];
				}
			}
		}
		return null;
	}

	/**
	 * Method to returns the positions of specific titles in a map.
	 * @param indexEnemy The type of title to find in the map.
	 * @return ArrayList<Vector2> A list of positions for the specified title type.
	*/
	public ArrayList<Vector2> returnTitlePos(TitleType titleType) 
	{
		ArrayList<Vector2> titlePosArray = new ArrayList<Vector2>();

		for (int i = 0; i < mapTitleNum.length; i++) 
		{
			for (int j = 0; j < mapTitleNum[i].length; j++) 
			{
				if (titleType == TitleType.Alarm)
				{
					if (mapTitleNum[i][j].titleType == titleType || mapTitleNum[i][j].titleType == TitleType.Tunnel) 
						titlePosArray.add(mapTitleNum[i][j].pos);
				}
				else 
				{
					if (mapTitleNum[i][j].titleType == titleType)
						titlePosArray.add(mapTitleNum[i][j].pos);
				}
			}
		}

		return titlePosArray;
	}

	/**
	 * Method to returns the indices of titles within a one-tile range of a specified position, excluding obstacles.
	 * @param pos The position from which to find nearby titles.
	 * @return ArrayList<Vector2> A list of indices for the titles within range.
	 */
	public ArrayList<Vector2> getRangedTitleIndex(Vector2 pos, Vector2 size) 
	{
		ArrayList<Vector2> rangeTitles = new ArrayList<Vector2>();

		Title title = GetTitleFromPos(pos,size);

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

	/**
	 * Method to returns the position of a title given its index in the map.
	 * @param index The index of the title in the map.
	 * @return Vector2 The position of the title. If the title is not found, it returns null.
	 */
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

	/**
	 * Method to checks if an entity, represented by a collider, is inside a specified perimeter.
	 * @param coll The collider representing the entity.
	 * @return boolean Returns true if the entity is inside the perimeter, false otherwise.
	*/
	public boolean isEntityInsidePerimeter(Collider coll)
	{
		if(coll.pos.y > 85 && coll.pos.y <  420 && coll.pos.x > 45 && coll.pos.x < 435)
			return true;
		return false;
	}

	/**
	 * Method to returns the positions of titles within a one-tile range of a specified position, excluding obstacles.
	 * @param pos The position from which to find nearby titles.
	 * @return ArrayList<Vector2> A list of positions for the titles within range.
	*/
	public ArrayList<Vector2> getRangedTitlePos(Vector2 pos,Vector2 size) 
	{
		ArrayList<Vector2> rangeTitles = new ArrayList<Vector2>();

		Title title = GetTitleFromPos(pos,size);

		for (int i = 0; i < mapTitleNum.length; i++) 
		{
			for (int j = 0; j < mapTitleNum[i].length; j++) 
			{
				if (mapTitleNum[i][j].titleType != TitleType.Obstacle) 
				{
					Vector2 indexs = title.matrixPos;
					if (i == indexs.x + 1 && j == indexs.y && !rangeTitles.contains(mapTitleNum[i][j].pos))
						rangeTitles.add(mapTitleNum[i][j].matrixPos);
					if (i == indexs.x - 1 && j == indexs.y && !rangeTitles.contains(mapTitleNum[i][j].pos))
						rangeTitles.add(mapTitleNum[i][j].matrixPos);
					if (i == indexs.x && j == indexs.y + 1 && !rangeTitles.contains(mapTitleNum[i][j].pos))
						rangeTitles.add(mapTitleNum[i][j].matrixPos);
					if (i == indexs.x && j == indexs.y - 1 && !rangeTitles.contains(mapTitleNum[i][j].pos))
						rangeTitles.add(mapTitleNum[i][j].matrixPos);
				}
			}
		}
		return rangeTitles;
	}
}
