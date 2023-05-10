import javax.swing.*;
import java.awt.event.KeyEvent;

public class Pacman extends JLabel {
    private ImageIcon pacRIGHT, pacLEFT, pacUP, pacDOWN;
    private int x;
    private int y;
    private Board board;
    private int direction;
    private int speed;
    private boolean stuck;
    private boolean alive;

    public Pacman(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.speed = 5;
        this.stuck = false;
        this.alive = true;
        initImages();
    }

    public void moveLeft() {
        if (x > 0 && board.getBoard()[y][x - 1] != 1) {
            x--;
        }
    }

    public void moveRight() {
        if (x < board.getBoard()[y].length - 1 && board.getBoard()[y][x + 1] != 1) {
            x++;
        }
    }

    public void moveUp() {
        if (y > 0 && board.getBoard()[y - 1][x] != 1) {
            y--;
        }
    }

    public void moveDown() {
        if (y < board.getBoard().length - 1 && board.getBoard()[y + 1][x] != 1) {
            y++;
        }
    }

//    public void moveLeft() {
//        if (x > 0 && newBoard.getBoard()[y][x - 1] != 1) {
//            x--;
//            newBoard.setValueAt(pacLEFT, y, x - 1);
//        }
//    }
//
//    public void moveRight() {
//        if (x < newBoard.getColumnCount() - 1 && newBoard.getBoard()[y][x + 1] != 1) {
//            x++;
//            newBoard.setValueAt(pacRIGHT, y, x + 1);
//        }
//    }
//
//    public void moveUp() {
//        if (y > 0 && newBoard.getBoard()[y - 1][x] != 1) {
//            y--;
//            newBoard.setValueAt(pacUP, y + 1, x);
//        }
//    }
//
//    public void moveDown() {
//        if ((y < (newBoard.getRowCount() - 1)) && newBoard.getBoard()[y + 1][x] != 1) {
//            y++;
//            newBoard.setValueAt(pacDOWN, y - 1, x);
//        }
//    }

    public void move() {
        switch (direction) {
            case KeyEvent.VK_UP: {
                y -= speed;
                break;
            }
            case KeyEvent.VK_DOWN: {
                y += speed;
                break;
            }
            case KeyEvent.VK_LEFT: {
                x -= speed;
                break;
            }
            case KeyEvent.VK_RIGHT: {
                x += speed;
                break;
            }
        }
    }

    public void initImages() {
        this.pacRIGHT = new ImageIcon("resources/pacman/pacmanRIGHT.png");
        this.pacLEFT = new ImageIcon("resources/pacman/pacmanLEFT.png");
        this.pacLEFT = new ImageIcon("resources/pacman/pacmanLEFT.png");
        this.pacUP = new ImageIcon("resources/pacman/pacmanUP.png");
        this.pacDOWN = new ImageIcon("resources/pacman/pacmanDOWN.png");
    }

    public void pacmanDeath() {
        this.alive = false;
    }

    public ImageIcon getPacRIGHT() {
        return pacRIGHT;
    }

    public ImageIcon getPacLEFT() {
        return pacLEFT;
    }

    public ImageIcon getPacUP() {
        return pacUP;
    }

    public ImageIcon getPacDOWN() {
        return pacDOWN;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public boolean isStuck() {
        return stuck;
    }

    public boolean isAlive() {
        return alive;
    }
}