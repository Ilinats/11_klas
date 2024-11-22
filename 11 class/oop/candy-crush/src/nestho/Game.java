package nestho;

public class Game {
    Candy[][] board;
    private int score;
    private int size;

    public Game(int size) {
        this.size = size;
        this.board = new Candy[size][size];
        this.score = 0;
        fillBoard();
    }

    private final int MAX_BOMBS = 5;
    private final double BOMB_CHANCE = 0.05;

    private void fillBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                createcandy(i, j);
            }
        }
    }

    void createcandy(int i, int j) {
        if (Math.random() < BOMB_CHANCE) {
            board[i][j] = new BombCandy(this);
        }
        else {
            board[i][j] = new RegularCandy(this);
        }
    }

    public Candy getCandy(Coordinate coordinate) {
        return board[coordinate.x()][coordinate.y()];
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j].display();
            }
            System.out.println();
        }
    }

    public void swap(Coordinate coordinate, Direction direction) {
        Coordinate n = coordinate.neighbour(direction);
        if(!n.isInBoard(size)) throw new RuntimeException("invalid swap");

        Candy temp = getCandy(n);
        board[n.x()][n.y()] = getCandy(coordinate);
        board[coordinate.x()][coordinate.y()] = temp;

        temp.onSwap(coordinate);
        getCandy(n).onSwap(coordinate);
    }

    public int getSize() {
        return size;
    }
}
