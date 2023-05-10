//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.KeyEvent;
//
//public class Pacman extends JLabel {
//    private ImageIcon pacRIGHT, pacLEFT, pacUP, pacDOWN;
//    private int x;
//    private int y;
//    private Point position;
//    private Board board;
//    private int direction;
//    private int speed;
//    private boolean stuck;
//    private boolean alive;
//
//    public Pacman(int x, int y, Board board) {
//        this.x = x;
//        this.y = y;
//        this.board = board;
//        this.speed = 5;
//        this.stuck = false;
//        this.alive = true;
//        this.position = new Point(x, y);
//        initImages();
//    }
//
//    Pacman() {
//        this.x = 1;
//        this.y = 1;
//        this.speed = 5;
//        this.stuck = false;
//        this.alive = true;
//        this.position = new Point(x, y);
//        initImages();
//    }
//
//    public void moveLeft() {
//        if (x > 0 && board.getBoard()[y][x - 1] != 1) {
//            x--;
//        }
//    }
//
//    public void moveRight() {
//        if (x < board.getBoard()[y].length - 1 && board.getBoard()[y][x + 1] != 1) {
//            x++;
//        }
//    }
//
//    public void moveUp() {
//        if (y > 0 && board.getBoard()[y - 1][x] != 1) {
//            y--;
//        }
//    }
//
//    public void moveDown() {
//        if (y < board.getBoard().length - 1 && board.getBoard()[y + 1][x] != 1) {
//            y++;
//        }
//    }
//
////    public void moveLeft() {
////        if (x > 0 && newBoard.getBoard()[y][x - 1] != 1) {
////            x--;
////            newBoard.setValueAt(pacLEFT, y, x - 1);
////        }
////    }
////
////    public void moveRight() {
////        if (x < newBoard.getColumnCount() - 1 && newBoard.getBoard()[y][x + 1] != 1) {
////            x++;
////            newBoard.setValueAt(pacRIGHT, y, x + 1);
////        }
////    }
////
////    public void moveUp() {
////        if (y > 0 && newBoard.getBoard()[y - 1][x] != 1) {
////            y--;
////            newBoard.setValueAt(pacUP, y + 1, x);
////        }
////    }
////
////    public void moveDown() {
////        if ((y < (newBoard.getRowCount() - 1)) && newBoard.getBoard()[y + 1][x] != 1) {
////            y++;
////            newBoard.setValueAt(pacDOWN, y - 1, x);
////        }
////    }
//
//
//    public void move() {
//        switch (direction) {
//            case KeyEvent.VK_UP: {
//                y++;
//                break;
//            }
//            case KeyEvent.VK_DOWN: {
//                y += speed;
//                break;
//            }
//            case KeyEvent.VK_LEFT: {
//                x -= speed;
//                break;
//            }
//            case KeyEvent.VK_RIGHT: {
//                x += speed;
//                break;
//            }
//        }
//        System.out.println(this.getX() + " " + this.getY());
//    }
//
//    public void initImages() {
//        this.pacRIGHT = new ImageIcon("resources/pacman/pacmanRIGHT.png");
//        this.pacLEFT = new ImageIcon("resources/pacman/pacmanLEFT.png");
//        this.pacLEFT = new ImageIcon("resources/pacman/pacmanLEFT.png");
//        this.pacUP = new ImageIcon("resources/pacman/pacmanUP.png");
//        this.pacDOWN = new ImageIcon("resources/pacman/pacmanDOWN.png");
//    }
//
//    public void pacmanDeath() {
//        this.alive = false;
//    }
//
//    public ImageIcon getPacRIGHT() {
//        return pacRIGHT;
//    }
//
//    public ImageIcon getPacLEFT() {
//        return pacLEFT;
//    }
//
//    public ImageIcon getPacUP() {
//        return pacUP;
//    }
//
//    public ImageIcon getPacDOWN() {
//        return pacDOWN;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//
//    public boolean isStuck() {
//        return stuck;
//    }
//
//    public boolean isAlive() {
//        return alive;
//    }
//}

import javax.swing.*;

//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//
//public class Pacman extends JLabel implements ActionListener {
//    private ImageIcon pacRIGHT, pacLEFT, pacUP, pacDOWN;
//    private Point position;
//    private Board board;
//    private int direction;
//    private int speed;
//    private boolean stuck;
//    private boolean alive;
//
//    public Pacman(int x, int y, Board board) {
//        this.board = board;
//        this.speed = 5;
//        this.stuck = false;
//        this.alive = true;
//        this.position = new Point(x, y);
//        initImages();
//    }
//
//    public void actionPerformed(ActionEvent e) {
//        if (direction == KeyEvent.VK_LEFT) {
//            moveLeft();
//        } else if (direction == KeyEvent.VK_RIGHT) {
//            moveRight();
//        } else if (direction == KeyEvent.VK_UP) {
//            moveUp();
//        } else if (direction == KeyEvent.VK_DOWN) {
//            moveDown();
//        }
//    }
//
//    public void moveLeft() {
//        if (position.x > 0 && board.getBoard()[position.y][position.x - 1] != 1) {
//            position.x--;
//            position.setLocation(position.x, position.y);
//            setIcon(pacLEFT);
//            System.out.println(1);
//        }
//    }
//
//    public void moveRight() {
//        if (position.x < board.getBoard()[position.y].length - 1 && board.getBoard()[position.y][position.x + 1] != 1) {
//            position.x++;
//            position.setLocation(position.x, position.y);
//            setIcon(pacRIGHT);
//            System.out.println(2);
//        }
//    }
//
//    public void moveUp() {
//        if (position.y > 0 && board.getBoard()[position.y - 1][position.x] != 1) {
//            position.y--;
//            position.setLocation(position.x, position.y);
//            setIcon(pacUP);
//        }
//    }
//
//    public void moveDown() {
//        if (position.y < board.getBoard().length - 1 && board.getBoard()[position.y + 1][position.x] != 1) {
//            position.y++;
//            position.setLocation(position.x, position.y);
//            setIcon(pacDOWN);
//        }
//    }
//
//    public void move() {
//        switch (direction) {
//            case KeyEvent.VK_UP: {
//                moveUp();
//                break;
//            }
//            case KeyEvent.VK_DOWN: {
//                moveDown();
//                break;
//            }
//            case KeyEvent.VK_LEFT: {
//                moveLeft();
//                break;
//            }
//            case KeyEvent.VK_RIGHT: {
//                moveRight();
//                break;
//            }
//        }
//        System.out.println(this.getX() + " " + this.getY());
//    }
//
//    public int getDirection() {
//        return direction;
//    }
//
//    public void setDirection(int direction) {
//        this.direction = direction;
//    }
//
//    public void initImages() {
//        this.pacRIGHT = new ImageIcon("resources/pacman/pacmanRIGHT.png");
//        this.pacLEFT = new ImageIcon("resources/pacman/pacmanLEFT.png");
//        this.pacUP = new ImageIcon("resources/pacman/pacmanUP.png");
//        this.pacDOWN = new ImageIcon("resources/pacman/pacmanDOWN.png");
//        setIcon(pacRIGHT);
//    }
//
//    public void pacmanDeath() {
//        this.alive = false;
//    }
//
//    public boolean isStuck() {
//        return stuck;
//    }
//
//    public boolean isAlive() {
//        return alive;
//    }
//
//    public Point getPosition() {
//        return position;
//    }
//
//    public Board getBoard() {
//        return board;
//    }
//
//    public int getSpeed() {
//        return speed;
//    }
//}
public class Pacman {
    private ImageIcon pacRIGHT, pacLEFT, pacUP, pacDOWN;
    private ImageIcon currentPac;
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
        this.direction = 0;
        this.speed = 5;
        this.stuck = false;
        this.alive = true;
        initImages();
        this.currentPac = pacRIGHT;


    }

    private void initImages() {
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

    public void moveLeft() {
        if (x > 0 && board.getBoard()[y][x - 1] != 1) {
            printPoints();
            board.setValueAt(0, y, x);
            if (board.getBoard()[y][x - 1] == 9) {
                board.setCountSmallPoints(board.getCountSmallPoints() - 1);
                board.setScore(board.getScore() + 10);
            }
            x--;
            board.setValueAt(7, y, x);
            currentPac = pacLEFT;
        }
    }

    public void moveRight() {
        if (x < board.getBoard()[y].length - 1 && board.getBoard()[y][x + 1] != 1) {
            printPoints();
            board.setValueAt(0, y, x);
            if (board.getBoard()[y][x + 1] == 9) {
                board.setCountSmallPoints(board.getCountSmallPoints() - 1);
                board.setScore(board.getScore() + 10);
            }
            x++;
            board.setValueAt(7, y, x);
            currentPac = pacRIGHT;
        }
    }

    public void moveUp() {
        if (y > 0 && ((board.getBoard()[y - 1][x] != 2) && (board.getBoard()[y - 1][x] != 1))) {
            printPoints();
            board.setValueAt(0, y, x);
            if (board.getBoard()[y - 1][x] == 9) {
                board.setCountSmallPoints(board.getCountSmallPoints() - 1);
                board.setScore(board.getScore() + 10);
            }
            y--;
            board.setValueAt(7, y, x);
            currentPac = pacUP;
        }
    }

    public void moveDown() {
        if (y < board.getBoard().length - 1 && board.getBoard()[y + 1][x] != 1) {
            printPoints();
            board.setValueAt(0, y, x);
            if (board.getBoard()[y + 1][x] == 9) {
                board.setCountSmallPoints(board.getCountSmallPoints() - 1);
                board.setScore(board.getScore() + 10);
            }
            y++;
            board.setValueAt(7, y, x);
            currentPac = pacDOWN;
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
