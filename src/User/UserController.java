package Src.User;

/**
 * Class UserController
*/
public class UserController 
{
	private User model;
	private UserView view;

	/**
	 * Costructor class UserController
	* @param model The User object that this controller will manage
	* @param view The UserView object that this controller will use to display the User
	*/
	public UserController(User model, UserView view) 
	{
		this.model = model;
		this.view = view;
	}

	/**
	 * Method to sets the nickname for the user.
	 * @param name The nickname to set for the user.
	 */
	public void setUserNickname(String name) { model.setNickname(name); }

	/**
	 * Method to retrieves the nickname of the user.
	 * @return The user's nickname.
	 */
	public String getUserNickname() { return model.getNickname(); }

	/**
	 * Method to sets the avatar color for the user.
	 * @param avatarColor The avatar color to set for the user.
	 */
	public void setUserAvatar(String avatarColor) { model.setAvatar(avatarColor); }

	/**
	 * Method to retrieves the avatar color of the user.
	 * @return The user's avatar color.
	 */
	public String getUserAvatar() { return model.getAvatar(); }

	/**
	 * Method to sets the number of matches won by the user.
	 * @param matchWon The number of matches won to set for the user.
	 */
	public void setUsermatchWon(int matchWon) { model.setMatchWon(matchWon); }

	/**
	 * Method to retrieves the number of matches won by the user.
	 * @return The number of matches won by the user.
	 */
	public int getUsermatchWon() { return model.getWinMatch(); }

	/**
	 * Method to sets the number of matches lost by the user.
	 * @param matchLose The number of matches lost to set for the user.
	 */
	public void setUsermatchLose(int matchLose) { model.setMatchLost(matchLose); }

	/**
	 * Method to gets the number of matches lost by the user.
	 */
	public int getUsermatchLose() { return model.getLoseMatch(); }

	/**
	 * Method to sets the total number of matches played by the user.
	 * @param match The total number of matches to set for the user.
	 */
	public void setUsermatch(int match) { model.setMatch(match); }

	/**
	 * Method to retrieves the total number of matches played by the user.
	 * @return The total number of matches played by the user.
	 */
	public int getUsermatch() { return model.getMatch(); }

	/**
	 * Method to sets the level of the user.
	 * @param level The level to set for the user.
	 */
	public void setUserLevel(int level) { model.setLevel(level); }

	/**
	 * Method to retrieves the level of the user.
	 * @return The level of the user.
	 */
	public int getUserLevel() { return model.getLevel(); }

	/**
	 * Method to sets the points earned by the user.
	 * @param points The points to set for the user.
	 */
	public void setUserPoints(int points) { model.setPoints(points); }

	/**
	 * Method to retrieves the points earned by the user
	 * @return The points earned by the user
	 */
	public int getUserPoints() { return model.getPoints(); }
	
	/**
	 * Method that updates the view by printing the user's details
	*/
	public void updateView() 
	{
		view.printUserDetails(
		model.getNickname(), 
		model.getMatch(), 
		model.getLoseMatch(),
		model.getWinMatch(),
		model.getLevel(),
		model.getPoints(),
		model.getAvatar());
	}
}
