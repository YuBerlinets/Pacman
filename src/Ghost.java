import javax.swing.*;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;

public class Ghost {
    private ImageIcon currentGhost;
    private ImageIcon ghostIcon1, ghostIcon2;
    private ImageIcon ghostVul1, ghostVul2;
    private String ghostPath = "resources/ghosts/";
    private Board board;
    private int[] upgrades = {21, 22, 23, 24, 25, 26};

    private int x;
    private int y;
    private boolean isRunning;
    private int[][] originalBoard;
    private final int PACMAN = 7, SMALL_POINT = 9, WALL1 = 1, WALL2 = 2, WALL3 = 3, WALL4 = 4, WALL5 = 5, WALL6 = 6, WALL13 = 13,
            CHERRY = 21, BANANA = 22, ORANGE = 23, APPLE = 24, BLUEBERRY = 25;

    public Ghost(String color, int y, int x, Board board) {
        this.x = x;
        this.y = y;
        this.isRunning = true;
        this.board = board;
        this.originalBoard = Arrays.copyOf(board.getBoard(), board.getBoard().length);
        originalBoard[x][y] = 14;
        ghostVul1 = new ImageIcon(ghostPath + "vulGhost1.png");
        ghostVul2 = new ImageIcon(ghostPath + "vulGhost2.png");
        if (color.equals("red")) {
            this.ghostIcon1 = new ImageIcon(ghostPath + "redGhost1.png");
            this.ghostIcon2 = new ImageIcon(ghostPath + "redGhost2.png");
        } else if (color.equals("blue")) {
            this.ghostIcon1 = new ImageIcon(ghostPath + "blueGhost1.png");
            this.ghostIcon2 = new ImageIcon(ghostPath + "blueGhost2.png");
        } else {
            this.ghostIcon1 = new ImageIcon(ghostPath + "yellowGhost1.png");
            this.ghostIcon2 = new ImageIcon(ghostPath + "yellowGhost2.png");
        }

        currentGhost = ghostIcon1;
        changingAppearance();
        move();
    }

    public void changingAppearance() {
        new Thread(() -> {
            while (isRunning) {
                currentGhost = ghostIcon1;
                board.getBoardTable().repaint();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("Thread was interrupted");
                }
                currentGhost = ghostIcon2;
                board.getBoardTable().repaint();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("Thread was interrupted");
                }
            }
        }).start();
    }

    public synchronized void move() {
        Random random = new Random();
        new Thread(() -> {
            while (isRunning) {
                int newX = x;
                int newY = y;

                int direction = random.nextInt(4);
                switch (direction) {
                    case 0:
                        newX = x + 1;
                        break;
                    case 1:
                        newX = x - 1;
                        break;
                    case 2:
                        newY = y + 1;
                        break;
                    case 3:
                        newY = y - 1;
                        break;
                }

                if (isValidMove(newY, newX)) {
                    updatePosition(newY, newX, originalBoard);
                    board.getBoardTable().repaint();
                }
//                System.out.println(getX() + " " + getY());

                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    System.out.println("Thread was interrupted");
                }
            }
        }).start();
    }

    private boolean isValidMove(int newY, int newX) {
        return newY >= 0 && newY < board.getBoard().length &&
                newX >= 0 && newX < board.getBoard()[newY].length &&
                board.getBoard()[newY][newX] != WALL1 &&
                board.getBoard()[newY][newX] != WALL2 &&
                board.getBoard()[newY][newX] != WALL3 &&
                board.getBoard()[newY][newX] != WALL4 &&
                board.getBoard()[newY][newX] != WALL5 &&
                board.getBoard()[newY][newX] != WALL6 &&
                board.getBoard()[newY][newX] != WALL13;
    }

    //    private void updatePosition(int newY, int newX, int[][] originalBoard) {
//        if (newY == y && newX == x) {
//            return;
//        }
//        int oldY = y;
//        int oldX = x;
//        int prevValueTable = originalBoard[y][x];
//        y = newY;
//        x = newX;
//        board.setValueAt(15, newY, newX);
//        board.setValueAt(prevValueTable, oldY, oldX);
//    }


    private void updatePosition(int newY, int newX, int[][] originalBoard) {
        if (newY == y && newX == x) {
            return;
        }
        int prevValueTable = originalBoard[newY][newX];
        originalBoard[newY][newX] = 15;
        int prevValueGhost = originalBoard[y][x];
        originalBoard[y][x] = prevValueTable;
        y = newY;
        x = newX;
        board.setValueAt(15, newY, newX);

            board.setValueAt(prevValueGhost, y, x);
    }

    private boolean shouldSpawnUpgrade() {
        return Math.random() < 0.25;
    }

    private void spawnUpgrade(int y, int x) {
        int upgradeType = getRandomUpgradeType();

        board.setValueAt(upgradeType, y, x);
    }

    private int getRandomUpgradeType() {
        int randomIndex = (int) (Math.random() * upgrades.length);
        return upgrades[randomIndex];
    }


    public void stop() {
        isRunning = false;
    }

    public ImageIcon getCurrentGhost() {
        return currentGhost;
    }

    public ImageIcon getGhostIcon1() {
        return ghostIcon1;
    }

    public ImageIcon getGhostIcon2() {
        return ghostIcon2;
    }

    public ImageIcon getGhostVul1() {
        return ghostVul1;
    }

    public ImageIcon getGhostVul2() {
        return ghostVul2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}


