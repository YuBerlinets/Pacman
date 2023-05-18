package Model;

import Controller.Board;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Ghost {
    private ImageIcon currentGhost;
    private ImageIcon ghostIcon1, ghostIcon2;
    private ImageIcon ghostVul1, ghostVul2;
    private String ghostPath = "resources/ghosts/";
    private Board board;
    private int[] upgrades = {21, 22, 23, 24, 25, 26};
    private final int UPGRADE_PROBABILITY = 25; // 25% probability of spawning upgrade
    private int x;
    private int y;
    private long timeElapsed = 0;
    private double boostProbability = 0.25;
    private boolean isRunning;
    private int[][] originalBoard;
    private int ghostNumber;
    private final int PACMAN = 7, SMALL_POINT = 9, WALL1 = 1, WALL2 = 2, WALL3 = 3, WALL4 = 4, WALL5 = 5, WALL6 = 6, WALL13 = 13,
            CHERRY = 21, BANANA = 22, ORANGE = 23, APPLE = 24, BLUEBERRY = 25;

    public Ghost(String color, int y, int x, Board board) {
        this.x = x;
        this.y = y;
        this.isRunning = true;
        this.board = board;
        this.originalBoard = Arrays.copyOf(board.getBoard(), board.getBoard().length);
        ghostVul1 = new ImageIcon(ghostPath + "vulGhost1.png");
        ghostVul2 = new ImageIcon(ghostPath + "vulGhost2.png");
        if (color.equals("red")) {
            this.ghostIcon1 = new ImageIcon(ghostPath + "redGhost1.png");
            this.ghostIcon2 = new ImageIcon(ghostPath + "redGhost2.png");
            this.ghostNumber = 15;
        } else if (color.equals("blue")) {
            this.ghostIcon1 = new ImageIcon(ghostPath + "blueGhost1.png");
            this.ghostIcon2 = new ImageIcon(ghostPath + "blueGhost2.png");
            this.ghostNumber = 17;
        } else {
            this.ghostIcon1 = new ImageIcon(ghostPath + "yellowGhost1.png");
            this.ghostIcon2 = new ImageIcon(ghostPath + "yellowGhost2.png");
            this.ghostNumber = 16;
        }
        originalBoard[y][x] = ghostNumber;

        currentGhost = ghostIcon1;
        changingAppearance();
        move();
    }
    //thread that swaping image of ghost
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

                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    System.out.println("Thread was interrupted");
                }
            }
        }).start();
    }

    public void spawnBoost() {
        Random random = new Random();
        Thread boostThread = new Thread(() -> {
            while (isRunning) {
                timeElapsed += 400;

                if (timeElapsed >= 5000) {
                    timeElapsed = 0;

                    if (random.nextDouble() <= boostProbability) {
                        int upgradeX = 0;
                        int upgradeY = 0;
                        int upgrade = upgrades[random.nextInt(upgrades.length)];
                        do {
                            upgradeX = random.nextInt(board.getBoard()[0].length);
                            upgradeY = random.nextInt(board.getBoard().length);
                        } while (!isValidUpgradeCell(upgradeY, upgradeX));
                        board.addBoost(upgrade,upgradeY,upgradeX);
                        board.setValueAt(upgrade, upgradeY, upgradeX);
                    }
                }
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    System.out.println("Thread was interrupted");
                }
            }
        });
        boostThread.start();
    }

    private boolean isValidUpgradeCell(int y, int x) {
        int cellValue = board.getBoard()[y][x];
        return cellValue != WALL1 && cellValue != WALL2 && cellValue != WALL3 &&
                cellValue != WALL4 && cellValue != WALL5 && cellValue != WALL6 &&
                cellValue != WALL13 && cellValue != PACMAN && cellValue != 15 && cellValue != 16 &&
                cellValue != 17 && cellValue != CHERRY && cellValue != BANANA && cellValue != ORANGE &&
                cellValue != APPLE && cellValue != BLUEBERRY;
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


    private void updatePosition(int newY, int newX, int[][] originalBoard) {
        if (newY == y && newX == x) {
            return;
        }
        int prevValueTable = originalBoard[newY][newX];
//        originalBoard[newY][newX] = ghostNumber;
        int prevValueGhost = originalBoard[y][x];
        originalBoard[y][x] = prevValueTable;
        y = newY;
        x = newX;
        board.setValueAt(ghostNumber, newY, newX);
        board.setValueAt(prevValueGhost, y, x);
    }

    public void stop() {
        isRunning = false;
    }

    public ImageIcon getCurrentGhost() {
        return currentGhost;
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


