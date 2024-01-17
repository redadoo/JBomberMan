package Src.User;

public class UserController {
	
	private User model;
	private UserView view;

	public UserController(User model, UserView view) {
		this.model = model;
		this.view = view;
	}

	public void setUserNickname(String name) {
		model.setNickname(name);
	}

	public String getUserNickname() {
		return model.getNickname();
	}

	public void setUserAvatar(String avatarColor) {
		model.setAvatar(avatarColor);
	}

	public String getUserAvatar() {
		return model.getAvatar();
	}

	public void setUsermatchWon(int matchWon) {
		model.setMatchWon(matchWon);
	}

	public int getUsermatchWon() {
		return model.getWinMatch();
	}

	public void setUsermatchLose(int matchLose) {
		model.setMatchLost(matchLose);
	}

	public int getUsermatchLose() {
		return model.getLoseMatch();
	}

	public void setUsermatch(int match) {
		model.setMatch(match);
	}

	public int getUsermatch() {
		return model.getMatch();
	}

	public void setUserLevel (int level) {
		model.setLevel(level);
	}

	public int getUserLevel() {
		return model.getMatch();
	}

	public void setUserPoints (int points) {
		model.setPoints(points);
	}

	public int getUserPoints() {
		return model.getPoints();
	}

	public void updateView() {
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
