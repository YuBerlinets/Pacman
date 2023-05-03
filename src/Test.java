import java.util.Random;

public class Test {
    private char[][] grid;
    private int width;
    private int height;
    private Random random;

    public Test(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new char[height][width];
        this.random = new Random();
        initializeGrid();
    }

    private void initializeGrid() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (row == 0 || col == 0 || row == height - 1 || col == width - 1) {
                    grid[row][col] = 'W'; // Create walls around the edges of the board
                } else if (random.nextDouble() < 0.2) {
                    grid[row][col] = 'W'; // Create walls randomly
                } else {
                    grid[row][col] = ' '; // Create empty spaces for Pac-Man and the ghosts to move
                }
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] != 'W')
                    grid[i][j] = 'I';
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char getCell(int row, int col) {
        return grid[row][col];
    }

    public void setCell(int row, int col, char value) {
        grid[row][col] = value;
    }

    public void printBoard() {
        // Print the current state of the board to the console
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Test test = new Test(30, 30);
        test.printBoard();
    }
}
