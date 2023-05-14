import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Pacman {
    private ImageIcon pacRIGHT, pacLEFT, pacUP, pacDOWN, pacDEF;
    private ImageIcon currentPac;
    private int x;
    private int y;
    private Board board;
    private PacmanMovement pacmanMovement;
    private Map<ImageIcon, ImageIcon> pacImages;
    private int direction;
    private int speed;
    private boolean stuck;
    private boolean alive;

    private final int PACMAN = 7, SMALL_POINT = 9, WALL1 = 1, WALL2 = 2, WALL3 = 3, WALL4 = 4, WALL5 = 5,
            WALL6 = 6, WALL13 = 13,GHOST_DOOR = 12;

    public Pacman(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.direction = 0;
        this.speed = 5;
        this.stuck = false;
        this.alive = true;
        initImages();

        //store images in hashmap to animate eating
        pacImages = new HashMap<>();
        pacImages.put(pacUP, pacUP);
        pacImages.put(pacDOWN, pacDOWN);
        pacImages.put(pacLEFT, pacLEFT);
        pacImages.put(pacRIGHT, pacRIGHT);

        this.pacmanMovement = PacmanMovement.NONE;
        this.currentPac = pacRIGHT;


        Thread threadPacMoving = new Thread(() -> {
            while (this.isAlive()) {
                move();
                this.board.getBoardPanel().repaint();
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    System.out.println("threadPacMoving was interrupted");
                }
            }
        });
        threadPacMoving.start();
        getPacAnim();

        if (!this.isAlive()) {
            threadPacMoving.interrupt();
        }
    }

    private void initImages() {
        pacDEF = new ImageIcon("resources/pacman/pacmanDEF.png");
        pacRIGHT = new ImageIcon("resources/pacman/pacmanRIGHT.png");
        pacLEFT = new ImageIcon("resources/pacman/pacmanLEFT.png");
        pacUP = new ImageIcon("resources/pacman/pacmanUP.png");
        pacDOWN = new ImageIcon("resources/pacman/pacmanDOWN.png");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public synchronized void getPacAnim() {
        long eatingSpeed = 200;
        Thread threadPacmanAnimation = new Thread(() -> {
            while (this.isAlive()) {
                if (!this.isStuck() && currentPac != pacDEF) {
                    ImageIcon tmp;
                    tmp = currentPac;
                    currentPac = pacDEF;
                    board.getBoardTable().repaint();
                    try {
                        Thread.sleep(eatingSpeed);
                    } catch (InterruptedException e) {
                        System.out.println("Thread was interrupted");
                    }
                    currentPac = tmp;
                    board.getBoardTable().repaint();
                    try {
                        Thread.sleep(eatingSpeed);
                    } catch (InterruptedException e) {
                        System.out.println("Thread was interrupted");
                    }
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Thread was interrupted");
                }
            }
        });
        threadPacmanAnimation.start();
    }


    public void move() {
        switch (pacmanMovement) {
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
            case UP -> moveUp();
            case DOWN -> moveDown();
        }
    }


    private boolean isValidMove(int newY, int newX) {
        int[][] boardArray = board.getBoard();
        return newY >= 0 && newY < boardArray.length &&
                newX >= 0 && newX < boardArray[newY].length &&
                boardArray[newY][newX] != WALL1 &&
                boardArray[newY][newX] != WALL2 &&
                boardArray[newY][newX] != WALL3 &&
                boardArray[newY][newX] != WALL4 &&
                boardArray[newY][newX] != WALL5 &&
                boardArray[newY][newX] != WALL6 &&
                boardArray[newY][newX] != GHOST_DOOR &&
                boardArray[newY][newX] != WALL13;
    }

    private synchronized void updatePosition(int newY, int newX) {
        board.setValueAt(0, y, x);
        if (board.getBoard()[newY][newX] == SMALL_POINT) {
            board.setCountSmallPoints(board.getCountSmallPoints() - 1);
            board.setScore(board.getScore() + 10);
        }
        currentPac = (newY < y) ? pacUP : (newY > y) ? pacDOWN : (newX < x) ? pacLEFT : pacRIGHT;
        y = newY;
        x = newX;
        board.setValueAt(PACMAN, y, x);
    }

//    private synchronized void updatePosition(int newY, int newX) {
//        if (newY == y && newX == x) {
//            return;
//        }
//
//        int oldY = y;
//        int oldX = x;
//        y = newY;
//        x = newX;
//
//        if (board.getBoard()[newY][newX] == SMALL_POINT) {
//            board.setCountSmallPoints(board.getCountSmallPoints() - 1);
//            board.setScore(board.getScore() + 10);
//        }
//
//        new Thread(() -> {
//            // Calculate the pixel coordinates of the old cell and the new cell
//            int oldPixelX = oldX * board.getCellWidth();
//            int oldPixelY = oldY * board.getCellHeight();
//            int newPixelX = newX * board.getCellWidth();
//            int newPixelY = newY * board.getCellHeight();
//            for (int i = 0; i < board.getCellWidth(); i += 5) {
//                int dx = (newPixelX - oldPixelX) * i / board.getCellWidth();
//                int dy = (newPixelY - oldPixelY) * i / board.getCellHeight();
//                board.getBoardPanel().repaint(oldPixelX, oldPixelY, board.getCellWidth(), board.getCellHeight());
//                board.getBoardPanel().repaint(newPixelX, newPixelY, board.getCellWidth(), board.getCellHeight());
//                this.setX(oldPixelX + dx);
//                this.setY(oldPixelY + dy);
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.println(2);
//            board.setValueAt(0, oldY, oldX);
//            board.setValueAt(PACMAN, newY, newX);
//        }).start();
//    }


    public void moveLeft() {
        int newX = x - 1;
        if (isValidMove(y, newX)) {
            stuck = false;
            pacmanMovement = pacmanMovement.LEFT;
            //currentPac = pacLEFT;
            updatePosition(y, newX);
        } else
            stuck = true;
    }

    public void moveRight() {
        int newX = x + 1;
        if (isValidMove(y, newX)) {
            stuck = false;
            pacmanMovement = pacmanMovement.RIGHT;
            //currentPac = pacRIGHT;
            updatePosition(y, newX);
        } else
            stuck = true;
    }

    public void moveUp() {
        int newY = y - 1;
        if (isValidMove(newY, x)) {
            stuck = false;
            pacmanMovement = pacmanMovement.UP;
            //currentPac = pacUP;
            updatePosition(newY, x);
        } else
            stuck = true;
    }

    public void moveDown() {
        int newY = y + 1;
        if (isValidMove(newY, x)) {
            stuck = false;
            pacmanMovement = pacmanMovement.DOWN;
            //currentPac = pacDOWN;
            updatePosition(newY, x);
        }
        else
            stuck = true;
    }


    public void printPoints() {
        System.out.println(board.getCountSmallPoints());
    }

    public void death() {
        this.alive = false;
    }

    public ImageIcon getPacRIGHT() {
        return pacRIGHT;
    }

    public Board getBoard() {
        return board;
    }

    public int getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isStuck() {
        return stuck;
    }

    public boolean isAlive() {
        return alive;
    }

    public ImageIcon getCurrentPac() {
        return currentPac;
    }

    public void setPacmanMovement(PacmanMovement pacmanMovement) {
        this.pacmanMovement = pacmanMovement;
    }

    public PacmanMovement getPacmanMovement() {
        return pacmanMovement;
    }

    public Map<ImageIcon, ImageIcon> getPacImages() {
        return pacImages;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
