package src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ManageFile {
	
	private File    myFile;
	private String  filePath;

	/**
	 * 
	 * @param filePath
	 */
	ManageFile(String filePath)
	{
		this.filePath = filePath;
		myFile = new File(filePath);
	}

	/**
	 * read info from save file
	 * @param user 
	*/
	public void returnUserValue(User user)
	{
		try {
			Scanner myReader = new Scanner(myFile);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if (data.contains("Nickname"))
					user.setNickname(data.split(":")[1]);
				if (data.contains("Total"))
					user.setMatch(Integer.parseInt(data.split(":")[1]));
				if (data.contains("lost"))
					user.setMatchLost(Integer.parseInt(data.split(":")[1]));
				if (data.contains("won"))
					user.setMatchWon(Integer.parseInt(data.split(":")[1]));
				if (data.contains("level"))
					user.setLevel(Integer.parseInt(data.split(":")[1]));
				if (data.contains("Avatr"))
					user.setAvatar(data.split(":")[1]);
			}
			myReader.close();
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
 
	/**
	 * 
	 * @param user
	 * @throws IOException
	 */
	public User initFile(User user) throws IOException
	{
		Scanner myObj = new Scanner(System.in);
		System.out.println("Enter nickname");
	
		String nickname = myObj.nextLine();
		user = new User(nickname);
		
		myObj.close();
		
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
			writer.write("Avatar :White");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			writer.close();
		}
		writer.close();
		return user;
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public boolean haveSave() throws IOException
	{
		if (new File("saves/").isDirectory() && myFile.exists())
			return true;
		return false;
	}
}
