package src;

public class GameManager 
{  
    Ui ui = new Ui(this);
    public static void main(String[] args) 
    {  
        new GameManager();
    }

    public GameManager()
    {
        CreateImage i = new CreateImage(ui.window);
    }
}  
