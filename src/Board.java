import java.util.Random;

public class Board {
    private int[][] board;
    private int height;
    private int width;
    private Random random;

    Board(int height, int width) {
        this.board = new int[height][width];
        this.height = height;
        this.width = width;
        this.random = new Random();
    }

    public int[][] initBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || j == 0 || i == height - 1 || j == width - 1) {
                    board[i][j] = 1; // Create walls around the edges of the board
                } else if (random.nextDouble() < 0.2) {
                    board[i][j] = 1; // Create walls randomly
                } else {
                    board[i][j] = 0; // Create empty spaces for Pac-Man and the ghosts to move
                }
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] != 1)
                    board[i][j] = 0;
            }
        }
        return board;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void printBoard(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
