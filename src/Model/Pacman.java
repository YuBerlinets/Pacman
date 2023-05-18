package Model;

import Controller.Board;
import Model.PacmanMovement;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Pacman {
    private ImageIcon pacRIGHT, pacLEFT, pacUP, pacDOWN, pacDEF;
    private volatile ImageIcon currentPac;
    private int x;
    private int y;
    private Board board;
    private PacmanMovement pacmanMovement;
    private Map<ImageIcon, ImageIcon> pacImages;
    private int lives;
    private boolean stuck;
    private int addingScore;
    private boolean alive;
    private boolean invulnerable;
    private long speed = 400;

    private final int PACMAN = 7, SMALL_POINT = 9, WALL1 = 1, WALL2 = 2, WALL3 = 3, WALL4 = 4, WALL5 = 5,
            WALL6 = 6, WALL13 = 13, GHOST_DOOR = 12, CHERRY = 21, BANANA = 22, ORANGE = 23, APPLE = 24, BLUEBERRY = 25;

    public Pacman(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.stuck = false;
        this.alive = true;
        this.lives = 3;
        this.addingScore = 10;
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
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    System.out.println("threadPacMoving was interrupted");
                }
            }
        });
        threadPacMoving.start();
        getPacAnim();

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


    public void speedBoost() {
        this.setSpeed(250);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Thread sleep was interrupted");
        }
        this.setSpeed(400);
    }

    public void plusLife() {
        setLives(getLives() + 1);
    }

    public  void invulnerable() {
        invulnerable = true;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Thread sleep was interrupted");
        }
        invulnerable = false;
    }

    public void pointsBoost() {
        addingScore = 20;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Thread sleep was interrupted");
        }
        addingScore = 10;
    }

    public synchronized void getPacAnim() {
        final long animationSpeed = 200;
        Thread threadPacmanAnimation = new Thread(() -> {
            while (this.isAlive()) {
                ImageIcon currentImage = getCurrentPacImage();
                if (currentImage != null) {
                    setPacImage(pacDEF);
                    board.getBoardTable().repaint();
                    try {
                        Thread.sleep(animationSpeed);
                    } catch (InterruptedException e) {
                        System.out.println("Thread was interrupted");
                    }
                    setPacImage(currentImage);
                    board.getBoardTable().repaint();

                    try {
                        Thread.sleep(animationSpeed);
                    } catch (InterruptedException e) {
                        System.out.println("Thread was interrupted");
                    }
                }
            }
        });
        threadPacmanAnimation.start();
    }

    private synchronized ImageIcon getCurrentPacImage() {
        return currentPac;
    }

    private synchronized void setPacImage(ImageIcon newImage) {
        currentPac = newImage;
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
            board.setScore(board.getScore() + addingScore);
        }
//        System.out.println(board.getCountSmallPoints());
        currentPac = (newY < y) ? pacUP : (newY > y) ? pacDOWN : (newX < x) ? pacLEFT : pacRIGHT;
        y = newY;
        x = newX;
        board.setValueAt(PACMAN, y, x);
    }

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
        } else
            stuck = true;
    }

    public void minusLife() {
        lives -= 1;
    }

    public int getLives() {
        return lives;
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

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public boolean isInvulnerable() {
        return invulnerable;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
