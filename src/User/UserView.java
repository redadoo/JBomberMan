package Src.User;

/**
 * Class UserView
 */
public class UserView 
{
	/**
	 * This method prints the details of the user to the console
	 * @param nickname The nickname of the user
	 * @param match The total number of matches played by the user
	 * @param matchLose The number of matches lost by the user
	 * @param matchWon The number of matches won by the user
	 * @param level The level of the user
	 * @param points The points scored by the user
	 * @param avatarColor The color of the user's avatar
	 */
	public void printUserDetails(String nickname, int match,  int matchLose, int matchWon, int level, int points, String avatarColor )
	{
		System.out.print("\033[H\033[2J");  
		System.out.flush(); 
		System.out.print("|-------------------------------------------------------------------|\r\n" + //
		"|  ____                  _                                          |\r\n" + //
		"| |  _ \\                | |                                         |\r\n" + //
		"| | |_) | ___  _ __ ___ | |__   ___ _ __ _ __ ___   __ _ _ __       |\r\n" + //
		"| |  _ < / _ \\| '_ ` _ \\| '_ \\ / _ \\ '__| '_ ` _ \\ / _` | '_ \\      |\r\n" + //
		"| | |_) | (_) | | | | | | |_) |  __/ |  | | | | | | (_| | | | |     |\r\n" + //
		"| |____/ \\___/|_| |_| |_|_.__/ \\___|_|  |_| |_| |_|\\__,_|_| |_|     |\r\n" + //
		"|                                                                   |\r\n" + //
		"|-------------------------------------------------------------------|\n"
		);

		System.out.print("Nickname :" + nickname);
		System.out.print("\n");
		System.out.print("Total game : " + (matchLose + match));
		System.out.print("\n");
		System.out.print("game lost : " + matchLose);
		System.out.print("\n");
		System.out.print("games won : " + matchWon);
		System.out.print("\n");
		System.out.print("Actual level : " + level);
		System.out.print("\n");
		System.out.print("points : " + points);
		System.out.print("\n");
		System.out.print("Avatar Color : " + avatarColor);
		System.out.print("\n");
	}
	}
