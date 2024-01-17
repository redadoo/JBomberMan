package Src.User;

public class User {

	private int 	level;
	private int 	match;
	private String 	nickname;
	private int 	matchWon;
	private int 	matchLose;
	private int 	points;
	private String 	avatar;

	/**
	 * Costructor class User
	 * @param nickname the nickname of Player
	 */
	public User(String nickname)
	{
		this.nickname = nickname;
		this.match = 0;
		this.matchLose = 0;
		this.matchWon = 0;
		this.level = 1;
	}

	/**
	 * Method to get nickname of the actual player
	 * @return the nickname
	 */
	public String getNickname() { return this.nickname; }

	/**
	 * Method to get number of match
	 * @return the number of match
	 */
	public int getMatch() { return this.match; }

	/**
	 * Method to get numeber of win match
	 * @return the number of match won
	 */
	public int getWinMatch() { return this.matchWon; }

	/**
	 * Method to get numeber of lose match
	 * @return the number of match lost
	 */
	public int getLoseMatch() { return this.matchLose; }

	/**
	 * Method to get numeber of level
	 * @return the level
	 */
	public int getLevel() { return this.level; }

	/**
	 * Method to get numeber of level
	 * @return Name of the avatar
	 */
	public String getAvatar() { return this.avatar; }

	/**
	 * Method to get player point
	 * @return player point
	 */
	public int getPoints() { return this.points; }

	/**
	 * Method to set the level
	 * @param newLevel to be set
	 */
	public void setLevel(int newLevel) { this.level = newLevel; }

	/**
	 * Method to set the nickname
	 * @param newNickname to set the new nickname
	 */
	public void setNickname(String newNickname) {this.nickname = newNickname; }

	/**
	 * Method to set the number of match won
	 * @param MatchWon to set the number of match won
	 */
	public void setMatchWon(int MatchWon) { this.matchWon = MatchWon; }

	/**
	 * Method to set the number of match lost 
	 * @param MatchLose to set the number of match lost
	 */
	public void setMatchLost(int MatchLose) { this.matchLose = MatchLose; }

	/**
	 * Method to set the number of match 
	 * @param Match to set the number of match
	 */
	public void setMatch(int Match) { this.match = Match; }

	/**
	 * Method to set the avatar
	 * @param avatar to set the type of sprite
	 */
	public void setAvatar(String avatar) { this.avatar = avatar; }

	/**
	 * Method to set  player point
	 * @param points to set 
	 */
	public void setPoints(int points) { this.points = points; }
}