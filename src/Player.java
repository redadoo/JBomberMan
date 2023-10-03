package src;


public class Player extends Entity
{
    private int     life;
    private int     point;
    private String  imagePath;
    private Vector2 pos;
    private Vector2 size;

    public Player( String imagePath, Vector2 pos, Vector2 size)
    {
        super(imagePath, pos, size); //Si passano i parametri alla classe padre
    }

/*     @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    } */

/*     @Override
    public void draw(Graphics2D g) {
        // TODO Auto-generated method stub
        g.drawImage(this., this.x, this.y, null);

        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    } */
}