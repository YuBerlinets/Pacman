import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Pacman {
    private ImageIcon pacRIGHT, pacLEFT, pacUP, pacDOWN, pacDEF;
    private ImageIcon currentPac;
    private int x;
    private int y;
    private Board board;
    private PacmanMovement pacmanMovement;
    private int direction;
    private int speed;
    private boolean stuck;
    private boolean alive;

    public Pacman(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.direction = 0;
        this.speed = 5;
        this.stuck = false;
        this.alive = true;
        initImages();
        this.currentPac = pacRIGHT;

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


    //    public void getPacAnim() throws InterruptedException {
//        long eatingSpeed = 200;
//        while (isAlive()) {
//            if (this.getCurrentPac() == pacUP) {
//                Thread.sleep(eatingSpeed);
//                currentPac = pacDEF;
//                board.getBoardTable().repaint();
//                Thread.sleep(eatingSpeed);
//                currentPac = pacUP;
//                board.getBoardTable().repaint();
//            }
//            if (this.getCurrentPac() == pacDOWN) {
//                Thread.sleep(eatingSpeed);
//                currentPac = pacDEF;
//                board.getBoardTable().repaint();
//                Thread.sleep(eatingSpeed);
//                currentPac = pacDOWN;
//                board.getBoardTable().repaint();
//            }
//            if (this.getCurrentPac() == pacRIGHT) {
//                Thread.sleep(eatingSpeed);
//                currentPac = pacDEF;
//                board.getBoardTable().repaint();
//                Thread.sleep(eatingSpeed);
//                currentPac = pacRIGHT;
//                board.getBoardTable().repaint();
//            }
//            if (this.getCurrentPac() == pacLEFT) {
//                Thread.sleep(eatingSpeed);
//                currentPac = pacDEF;
//                board.getBoardTable().repaint();
//                Thread.sleep(eatingSpeed);
//                currentPac = pacLEFT;
//                board.getBoardTable().repaint();
//            }
//        }
//    }
    public synchronized void getPacAnim() throws InterruptedException {
        long eatingSpeed = 200;
        Map<ImageIcon, ImageIcon> pacImages = new HashMap<>();
        pacImages.put(pacUP, pacUP);
        pacImages.put(pacDOWN, pacDOWN);
        pacImages.put(pacLEFT, pacLEFT);
        pacImages.put(pacRIGHT, pacRIGHT);

        while (isAlive()) {
            ImageIcon tmp = currentPac;
            currentPac = pacDEF;
            board.getBoardTable().repaint();
            Thread.sleep(eatingSpeed);
            currentPac = pacImages.get(tmp);
            board.getBoardTable().repaint();
            Thread.sleep(eatingSpeed);
        }
    }


    public void move() {
        while (isAlive()) {
            if (direction == KeyEvent.VK_LEFT)
                moveLeft();
            if (direction == KeyEvent.VK_RIGHT)
                moveRight();
            if (direction == KeyEvent.VK_UP)
                moveUp();
            if (direction == KeyEvent.VK_DOWN)
                moveDown();
        }
    }

    public void moveLeft() {
        currentPac = pacLEFT;
        if (x > 0 && ((board.getBoard()[y][x - 1] != 1) && (board.getBoard()[y][x - 1] != 2) && (board.getBoard()[y][x - 1] != 3)
                && (board.getBoard()[y][x - 1] != 4) && (board.getBoard()[y][x - 1] != 5) && (board.getBoard()[y][x - 1] != 6))) {
            printPoints();
            board.setValueAt(0, y, x);
            if (board.getBoard()[y][x - 1] == 9) {
                board.setCountSmallPoints(board.getCountSmallPoints() - 1);
                board.setScore(board.getScore() + 10);
            }
            x--;
            board.setValueAt(7, y, x);
        }
    }

    public void moveRight() {
        currentPac = pacRIGHT;
        if (x < board.getBoard()[y].length - 1 && ((board.getBoard()[y][x + 1] != 1) && (board.getBoard()[y][x + 1] != 2)
                && (board.getBoard()[y][x + 1] != 3) && (board.getBoard()[y][x + 1] != 4) && (board.getBoard()[y][x + 1] != 5)
                && (board.getBoard()[y][x + 1] != 6))) {
            printPoints();
            board.setValueAt(0, y, x);
            if (board.getBoard()[y][x + 1] == 9) {
                board.setCountSmallPoints(board.getCountSmallPoints() - 1);
                board.setScore(board.getScore() + 10);
            }
            x++;
            board.setValueAt(7, y, x);
        }
    }

    public void moveUp() {
        currentPac = pacUP;
        if (y > 0 && ((board.getBoard()[y - 1][x] != 2) && (board.getBoard()[y - 1][x] != 1) && (board.getBoard()[y - 1][x] != 3)
                && (board.getBoard()[y - 1][x] != 4) && (board.getBoard()[y - 1][x] != 5) && (board.getBoard()[y - 1][x] != 6))) {
            printPoints();
            board.setValueAt(0, y, x);
            if (board.getBoard()[y - 1][x] == 9) {
                board.setCountSmallPoints(board.getCountSmallPoints() - 1);
                board.setScore(board.getScore() + 10);
            }
            y--;
            board.setValueAt(7, y, x);
        }
    }

    public void moveDown() {
        currentPac = pacDOWN;
        if (y < board.getBoard().length - 1 && ((board.getBoard()[y + 1][x] != 2) && (board.getBoard()[y + 1][x] != 1)
                && (board.getBoard()[y + 1][x] != 4) && (board.getBoard()[y + 1][x] != 3) && (board.getBoard()[y + 1][x] != 5)
                && (board.getBoard()[y + 1][x] != 6))) {
            printPoints();
            board.setValueAt(0, y, x);
            if (board.getBoard()[y + 1][x] == 9) {
                board.setCountSmallPoints(board.getCountSmallPoints() - 1);
                board.setScore(board.getScore() + 10);
            }
            y++;
            board.setValueAt(7, y, x);
        }
    }

    public void printPoints() {
        System.out.println(board.getCountSmallPoints());
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
}
