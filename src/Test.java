import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Test {
    private static final int EMPTY_SPACE = 9;
    private static final int VERTICAL_WALL = 1;
    private static final int HORIZONTAL_WALL = 2;
    private static final int LEFT_TOP_CORNER = 3;
    private static final int RIGHT_TOP_CORNER = 4;
    private static final int LEFT_BOTTOM_CORNER = 5;
    private static final int RIGHT_BOTTOM_CORNER = 6;

    private int height;
    private int width;
    private int[][] maze;
    private Random random;

    public Test(int height, int width) {
        this.height = height;
        this.width = width;
        this.maze = new int[height][width];
        this.random = new Random();
    }

    public void generateMaze() {
        // Initialize maze with all walls and corners
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int cell = EMPTY_SPACE;
                if (i == 0) {
                    cell |= HORIZONTAL_WALL | LEFT_TOP_CORNER | RIGHT_TOP_CORNER;
                }
                if (j == 0) {
                    cell |= VERTICAL_WALL | LEFT_TOP_CORNER | LEFT_BOTTOM_CORNER;
                }
                if (i == height - 1) {
                    cell |= HORIZONTAL_WALL | LEFT_BOTTOM_CORNER | RIGHT_BOTTOM_CORNER;
                }
                if (j == width - 1) {
                    cell |= VERTICAL_WALL | RIGHT_TOP_CORNER | RIGHT_BOTTOM_CORNER;
                }
                maze[i][j] = cell;
            }
        }

        // Generate maze using DFS algorithm
        int startRow = random.nextInt(height);
        int startCol = random.nextInt(width);
        depthFirstSearch(startRow, startCol);

        // Add walls to maze
        int numWalls = (int) Math.floor((height * width) / 4);
        List<Integer> wallLocations = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (maze[i][j] == EMPTY_SPACE) {
                    wallLocations.add(i * width + j);
                }
            }
        }
        Collections.shuffle(wallLocations, random);
        for (int i = 0; i < numWalls && i < wallLocations.size(); i++) {
            int location = wallLocations.get(i);
            int row = location / width;
            int col = location % width;
            int direction = random.nextInt(2) + 1; // 1 for vertical, 2 for horizontal
            addWall(row, col, direction);
        }
    }

    private void depthFirstSearch(int row, int col) {
        int[] directionRow = {-1, 0, 1, 0};
        int[] directionCol = {0, 1, 0, -1};
        List<Integer> directions = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            directions.add(i);
        }
        Collections.shuffle(directions, random);
        for (int i = 0; i < 4; i++) {
            int index = directions.get(i);
            int newRow = row + directionRow[index];
            int newCol = col + directionCol[index];
            if (newRow < 0 || newRow >= height || newCol <
                    0 || newCol >= width || (maze[newRow][newCol] != EMPTY_SPACE)) {
                continue;
            }
            maze[row][col] |= (1 << index); // Remove wall between current cell and neighbor
            maze[newRow][newCol] |= (1 << ((index + 2) % 4)); // Remove wall between neighbor and current cell
            depthFirstSearch(newRow, newCol);
        }
    }

    public void addWall(int row, int col, int direction) {
        if (row < 0 || row >= height || col < 0 || col >= width) {
            throw new IllegalArgumentException("Invalid row or column value");
        }
        if (direction != VERTICAL_WALL && direction != HORIZONTAL_WALL) {
            throw new IllegalArgumentException("Invalid direction value");
        }
        if (direction == VERTICAL_WALL && col == 0) {
            throw new IllegalArgumentException("Cannot add vertical wall at column 0");
        }
        if (direction == HORIZONTAL_WALL && row == 0) {
            throw new IllegalArgumentException("Cannot add horizontal wall at row 0");
        }
        if (direction == VERTICAL_WALL && (maze[row][col] & VERTICAL_WALL) != 0) {
            throw new IllegalArgumentException("Vertical wall already exists at specified location");
        }
        if (direction == HORIZONTAL_WALL && (maze[row][col] & HORIZONTAL_WALL) != 0) {
            throw new IllegalArgumentException("Horizontal wall already exists at specified location");
        }
        if (direction == VERTICAL_WALL) {
            maze[row][col] |= VERTICAL_WALL;
            maze[row][col - 1] |= VERTICAL_WALL;
        } else {
            maze[row][col] |= HORIZONTAL_WALL;
            maze[row - 1][col] |= HORIZONTAL_WALL;
        }
    }

    public void printMaze() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }
    public int[][] getMaze() {
        return maze;
    }

    public static void main(String[] args) {
        Test mazeGenerator = new Test(20, 20);
        mazeGenerator.generateMaze();
        mazeGenerator.printMaze();
    }
}