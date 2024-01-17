package Src.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Src.User.UserController;

/**
 * Class MManageFile
 */
public class ManageFile 
{
	
	private File    myFile;
	private String  filePath;

	/**
	 * Costructor class ManageFile
	 * @param filePath
	*/
	public ManageFile(String filePath)
	{
		this.filePath = filePath;
		myFile = new File(filePath);
	}

	/**
	 * Method to read info from saved file
	 * @param user 
	*/
	public void returnUserValue(UserController user)
	{
		try {
			Scanner myReader = new Scanner(myFile);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if (data.contains("Nickname"))
					user.setUserNickname(data.split(":")[1]);
				if (data.contains("Total"))
					user.setUserLevel(Integer.parseInt(data.split(":")[1]));
				if (data.contains("lost"))
					user.setUsermatchLose(Integer.parseInt(data.split(":")[1]));
				if (data.contains("won"))
					user.setUsermatchWon(Integer.parseInt(data.split(":")[1]));
				if (data.contains("level"))
					user.setUserLevel(Integer.parseInt(data.split(":")[1]));
				if (data.contains("points"))
					user.setUserPoints(Integer.parseInt(data.split(":")[1]));
				if (data.contains("Avatar"))
					user.setUserAvatar(data.split(":")[1]);
			}
			myReader.close();
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
 
	/**
	 * Method to initialize the file 
	 * @param user The user whose statistics are to be written on file
	 * @throws IOException
	 */
	public void initFile(UserController user) throws IOException
	{
		Scanner myObj = new Scanner(System.in);
		System.out.println("Enter nickname");
	
		String nickname = myObj.nextLine();
		user.setUserNickname(nickname);
				
		new File("saves/").mkdirs();
		myFile.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
		try {
			writer.write(
					"|-------------------------------------------------------------------|\r\n" + //
					"|  ____                  _                                          |\r\n" + //
					"| |  _ \\                | |                                         |\r\n" + //
					"| | |_) | ___  _ __ ___ | |__   ___ _ __ _ __ ___   __ _ _ __       |\r\n" + //
					"| |  _ < / _ \\| '_ ` _ \\| '_ \\ / _ \\ '__| '_ ` _ \\ / _` | '_ \\      |\r\n" + //
					"| | |_) | (_) | | | | | | |_) |  __/ |  | | | | | | (_| | | | |     |\r\n" + //
					"| |____/ \\___/|_| |_| |_|_.__/ \\___|_|  |_| |_| |_|\\__,_|_| |_|     |\r\n" + //
					"|                                                                   |\r\n" + //
					"|-------------------------------------------------------------------|\n"
					);
			writer.write("Nickname :" + nickname);
			writer.write("\n");
			writer.write("Total game :0");
			writer.write("\n");
			writer.write("game lost :0");
			writer.write("\n");
			writer.write("games won :0");
			writer.write("\n");
			writer.write("Actual level :0");
			writer.write("\n");
			writer.write("points :0");
			System.out.println("insert avatar number color:\n1 = White\n2 = Black\n3 = Blue\n4 = Orange");
			int color = Integer.parseInt(myObj.nextLine());
			myObj.close();
			String AvatarColor = "Avatar :";
			switch (color) {
				case 1: 
					AvatarColor += "White";
					break;
				case 2:
					AvatarColor += "Black";
					break;
				case 3:
					AvatarColor += "Blue";
					break;
				case 4:
					AvatarColor += "Orange";			
					break;
				default:
					AvatarColor += "White";
					break;
			}
			writer.write("\n");
			writer.write(AvatarColor);
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			writer.close();
		}
		writer.close();
	}

/**
 * Method to updates a file with the user's game statistics.
 * @param user The user whose statistics are to be written to the file
 * @throws IOException If an input or output exception occurred.
 */
	public void updateFile(UserController user) throws IOException
	{
		myFile.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
		try {
			writer.write(
					"|-------------------------------------------------------------------|\r\n" + //
					"|  ____                  _                                          |\r\n" + //
					"| |  _ \\                | |                                         |\r\n" + //
					"| | |_) | ___  _ __ ___ | |__   ___ _ __ _ __ ___   __ _ _ __       |\r\n" + //
					"| |  _ < / _ \\| '_ ` _ \\| '_ \\ / _ \\ '__| '_ ` _ \\ / _` | '_ \\      |\r\n" + //
					"| | |_) | (_) | | | | | | |_) |  __/ |  | | | | | | (_| | | | |     |\r\n" + //
					"| |____/ \\___/|_| |_| |_|_.__/ \\___|_|  |_| |_| |_|\\__,_|_| |_|     |\r\n" + //
					"|                                                                   |\r\n" + //
					"|-------------------------------------------------------------------|\n"
					);
			writer.write("Nickname :" + user.getUserNickname());
			writer.write("\n");
			writer.write("Total game :" + user.getUsermatch());
			writer.write("\n");
			writer.write("game lost :" + user.getUsermatchLose());
			writer.write("\n");
			writer.write("games won :" + user.getUsermatchWon());
			writer.write("\n");
			writer.write("Actual level :" + user.getUserLevel());
			writer.write("\n");
			writer.write("points :" + user.getUserPoints());
			writer.write("\n");
			writer.write("Avatar color :" + user.getUserAvatar());
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			writer.close();
		}
		writer.close();
	}

	/**
	 * Methos to check if there is a saved file
	 * @return 
	 * @throws IOException
	 */
	public boolean haveSave() throws IOException
	{
		if (!myFile.exists())
			return false;
		return true;
	}
}