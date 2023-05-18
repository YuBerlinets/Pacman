package Model;

import java.util.Random;

public class PacmanBoardGenerator {
    private static final int[][] BOX_2X2 = {{13, 13}, {13, 13}};
    private static final int[][] BOX_3X3 = {{13, 13}, {13, 13}, {13, 13}};
    private static final int[][] MIDDLE_BOX = {
            {3, 2, 12, 12, 2, 4},
            {1, 14, 14, 14, 14, 1},
            {1, 14, 14, 14, 14, 1},
            {5, 2, 2, 2, 2, 6}};
    private static final int[][] LETTER_T_TOP_3X3 = {{13, 13, 13},
            {0, 13, 0},
            {0, 13, 0}};
    private static final int[][] LETTER_T_RIGHT_3X3 = {
            {13, 0, 0},
            {13, 13, 13},
            {13, 0, 0}};
    private static final int[][] LETTER_T_LEFT_3X3 = {
            {13, 0, 0},
            {13, 13, 13},
            {13, 0, 0}};
    private static final int[][] VERTICAL_LINE = {
            {0, 13, 0},
            {0, 13, 0},
            {0, 13, 0}};
    private static final int[][] HORIZONTAL_LINE = {
            {0, 0, 0},
            {13, 13, 13},
            {0, 0, 0}};

    private Random random;
    private int height;
    private int width;
    private int[][] board;
    private int WALL1 = 1, WALL2 = 2, WALL3 = 3, WALL4 = 4, WALL5 = 5, WALL6 = 6, SMALL_POINT = 9,
            BIG_POINT = 10, GHOST_DOOR = 12, SIMPLE_BLOCK = 13, GHOST_SPACE = 14;

    private static final int[][][] TEMPLATES = {BOX_2X2, LETTER_T_TOP_3X3, LETTER_T_RIGHT_3X3, BOX_3X3, LETTER_T_LEFT_3X3
            , HORIZONTAL_LINE, VERTICAL_LINE};

    public PacmanBoardGenerator(int heightInput, int widthInput) {
        this.width = widthInput;
        this.height = heightInput;
        board = new int[height][width];
        random = new Random();
        int loops = height + random.nextInt(15);
        System.out.println(loops);
        for (int i = 0; i < loops; i++) {
            int[][] template = TEMPLATES[random.nextInt(TEMPLATES.length)];
            int centerRow = random.nextInt(board.length);
            int centerCol = random.nextInt(board[0].length);
            addTemplate(board, template, centerRow, centerCol);
        }

        int boxCenterRow = board.length / 2;
        int boxCenterCol = board[0].length / 2;
        initBorders();
        validateCells();
        addTemplate(board, MIDDLE_BOX, boxCenterRow, boxCenterCol);
        initSmallPoints();
    }

    private void printBoard() {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    private void validateCells() {
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width - 1; j++) {
                if (board[i][j + 1] == 13 && board[i - 1][j - 1] == 13)
                    board[i][j] = 0;
//                else if(board[i][j + 1] == 13 && board[i + 1][j + 1] == 13)
//                    board[i][j] = 0;
//                else if(board[i-1][j + 1] == 13 && board[i][j + 1] == 13)
//                    board[i][j] = 0;
            }
        }
        board[2][1] = 0;
        board[1][2] = 0;
        board[2][2] = 0;
    }

    private void initSmallPoints() {
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width - 1; j++) {
                if (board[i][j] == 0)
                    board[i][j] = 9;
            }
        }
    }

    private void initBorders() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 && j == 0)
                    board[i][j] = WALL3;
                else if (i == height - 1 && j == 0)
                    board[i][j] = WALL5;
                else if (i == 0 && j == width - 1)
                    board[i][j] = WALL4;
                else if (i == height - 1 && j == width - 1)
                    board[i][j] = WALL6;
                else if (i == 0 || i == height - 1)
                    board[i][j] = WALL2;
                else if (j == 0 || j == width - 1)
                    board[i][j] = WALL1;
            }
        }

    }

    private void addTemplate(int[][] board, int[][] template, int centerRow, int centerCol) {
        int templateHeight = template.length;
        int templateWidth = template[0].length;
        int boardHeight = board.length;
        int boardWidth = board[0].length;

        int startRow = centerRow - templateHeight / 2;
        int startCol = centerCol - templateWidth / 2;

        for (int i = 0; i < templateHeight; i++) {
            for (int j = 0; j < templateWidth; j++) {
                if (startRow + i >= 0 && startRow + i < boardHeight &&
                        startCol + j >= 0 && startCol + j < boardWidth) {
                    board[startRow + i][startCol + j] = template[i][j];
                }
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }
}
