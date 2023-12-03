package src;


/*
 *  Classe per la gestione del nemico del primo livello
 */
public class FlyHead extends Entity
{
    int pointDrop;

    public FlyHead(Vector2 pos, Vector2 size) 
    {
        super(pos, size);
        this.pointDrop = 100;
    }

    @Override
    public void moveEntity(Vector2 pos) 
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveEntity'");

        
    }
    
}