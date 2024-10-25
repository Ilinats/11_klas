package nestho;

public abstract class Candy {
    protected int type;
    protected Game game;

    public Candy(Game game) {
        this.game = game;
    }


    public abstract void display();

    public abstract void onSwap(Coordinate coordinate);
}
