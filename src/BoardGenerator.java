public class BoardGenerator {
    int[][] board;
    private int height, width;
    private int startX, startY, endY, endX;
    private int WALL1 = 1, WALL2 = 2, WALL3 = 3, WALL4 = 4, WALL5 = 5, WALL6 = 6, SMALL_POINT = 9,
            BIG_POINT = 10, GHOST_DOOR = 12, SIMPLE_BLOCK = 13,GHOST_SPACE = 14;

    public int[][] generateBoard(int heightInput, int widthInput) {
        this.height = heightInput;
        this.width = widthInput/2;
        this.board = new int[height][width];
        startX = width - 3;
        endX = width;
        startY = height / 2 - 2;
        endY = height / 2 + 2;


        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 || j == 0 || i == board.length - 1 || j == board[0].length - 1) {
                    board[i][j] = SMALL_POINT;
                } else {
                    board[i][j] = SMALL_POINT;
                }
            }
        }
        initRecMiddle();
        initUpDownBlocks();
        initBorders();
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }
        System.out.println();
        System.out.println();
        System.out.println();
        int [][] completedBoard = expandArray(board);
        return completedBoard;
    }

    private void initUpDownBlocks() {
        int x = 2;
        int y = 4;
        for (int i = x; i < y; i++) {
            for (int j = x; j < y; j++) {
                board[i][j] = SIMPLE_BLOCK;
            }
        }
        for (int i = height - y; i < height - x; i++) {
            for (int j = x; j < y; j++) {
                board[i][j] = SIMPLE_BLOCK;
            }
        }
    }

    private void initBorders() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 && j == 0)
                    board[i][j] = WALL3;
                else if ((i == height - 1 && j == 0))
                    board[i][j] = WALL5;
                else if (i == 0 || i == height - 1)
                    board[i][j] = WALL2;
                else if (j == 0)
                    board[i][j] = WALL1;
            }
        }

    }

    private void initRecMiddle() {
        // fill rectangle in the middle
        for (int i = startY; i < endY; i++) {
            for (int j = startX; j < endX; j++) {
                if (i == startY && j == startX)
                    board[i][j] = WALL3;
                else if ((i == endY - 1 && j == startX))
                    board[i][j] = WALL5;
                else if ((i == startY && j == endX - 1))
                    board[i][j] = GHOST_DOOR;
                else if (i == startY || i == endY - 1)
                    board[i][j] = WALL2;
                else if (j == startX)
                    board[i][j] = WALL1;
                else
                    board[i][j] = GHOST_SPACE;
            }
        }
    }

    public int[][] expandArray(int[][] arr) {
        int rows = arr.length;
        int cols = arr[0].length;
        int[][] expandedArr = new int[rows][cols * 2];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                expandedArr[i][j] = arr[i][j];
            }
            for (int j = cols; j < cols * 2; j++) {
                expandedArr[i][j] = arr[i][2 * cols - j - 1];
            }
        }

        return expandedArr;
    }

}
