package src;

public class User
{

    String nickname;
    int match; 
    int winMatch;
    int loseMatch;
    int level;

    public User(String nickname)
    {
        this.nickname = nickname;
        this.match = 0;
        this.loseMatch = 0;
        this.winMatch = 0;
        this.level = 1;
    }

    /*
     * Metodi Getter e Setter
     */
    public String getNickname() { return this.nickname; }

    public int getMatch() { return this.match; }

    public int getWinMatch() { return this.winMatch; }

    public int getLoseMatch() { return this.loseMatch; }

    public int getLevel() { return this.level; }

    public int setLevel(int newLevel) 
    { 
        this.level = newLevel; 
        return this.level;
    }



}