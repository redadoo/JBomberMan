package src;

public class User
{

    String nickname;
    int match; 
    int winMatch;
    int loseMatch;
    int level;

    /*
     * Costructor class User
     */
    public User(String nickname)
    {
        this.nickname = nickname;
        this.match = 0;
        this.loseMatch = 0;
        this.winMatch = 0;
        this.level = 1;
    }

    /*
     * Get nickname of the actual player
     */
    public String getNickname() { return this.nickname; }

    /*
     * Get numer of match
     */
    public int getMatch() { return this.match; }

    /*
     * Get numeber of win match
     */
    public int getWinMatch() { return this.winMatch; }

    /*
     * Get numeber of lose match
     */
    public int getLoseMatch() { return this.loseMatch; }

    /*
     * Get numeber of level
     */
    public int getLevel() { return this.level; }

    /*
     * Set the level
     */
    public int setLevel(int newLevel) 
    { 
        this.level = newLevel; 
        return this.level;
    }
}