package Controller;

import Model.*;
import View.Game;
import View.Launch;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Board extends AbstractTableModel {
    private JPanel boardPanel;
    private int[][] board;
    private boolean[][] boardCellVisited;
    private int countSmallPoints;
    private int height;
    private int width;
    private JTable boardTable;
    private Pacman pacman;
    private int score;
    private int cellHeight, cellWidth;
    private int[][] boostBoard;
    private BufferedImage wall1, wall2, wall3, wall4, wall5, wall6, wall13, ghostDoor;
    private BufferedImage smallPoint, bigPoint, cherry, banana, orange, apple, blueberry;
    private BoardGenerator boardGenerator;
    private Game gameClass;
    private Ghost redGhost;
    private Ghost yellowGhost;
    private Ghost blueGhost;
    private int CHERRY = 21, BANANA = 22, ORANGE = 23, APPLE = 24, BLUEBERRY = 25;
    public Set<Point> boostLocations = new HashSet<>();

    private int startPositionPacmanX, startPositionPacmanY,
            startPositionGhost1X, startPositionGhost1Y,
            startPositionGhost2X, startPositionGhost2Y,
            startPositionGhost3X, startPositionGhost3Y;

    public Board(int heightInput, int widthInput, Game game) {
        this.height = heightInput;
        this.width = widthInput;
        this.countSmallPoints = 0;
        this.score = 0;
        boardGenerator = new BoardGenerator();
//        board = boardGenerator.generateBoard(height, width);
        PacmanBoardGenerator pacmanBoardGenerator = new PacmanBoardGenerator(height, width);
        board = pacmanBoardGenerator.getBoard();
        this.gameClass = game;
        boostBoard = new int[height][width];
        //set size of cell
        if (height > 70 || width > 70) {
            cellWidth = 15;
            cellHeight = 15;
        } else if (height > 30 || width > 30) {
            cellWidth = 20;
            cellHeight = 20;
        } else {
            cellWidth = 30;
            cellHeight = 30;
        }

        //loading images
        initImages();
        //printing array as map on the console
//        printBoard();

        boardTable = new JTable(this);
        boardTable.setRowHeight(30);
        boardTable.setBackground(Color.BLACK);
        boardTable.setRowHeight(cellHeight);
        boardTable.setShowGrid(false);
        boardTable.setCellSelectionEnabled(false);
        boardTable.setColumnSelectionAllowed(false);
        boardTable.setRowSelectionAllowed(false);
        for (int i = 0; i < this.width; i++) {
            boardTable.getColumnModel().getColumn(i).setPreferredWidth(cellWidth);
        }

        boardPanel = new JPanel();
        boardPanel.setBackground(Color.BLACK);

        boardPanel.add(getBoardTable());
        boardPanel.addKeyListener(new PacmanKeyListener(game));

        //initialise a start positions
        startPositionPacmanX = 1;
        startPositionPacmanY = 1;
        startPositionGhost1Y = board.length - 5;
        startPositionGhost1X = board[1].length - 5;
        startPositionGhost2Y = board.length - 5;
        startPositionGhost2X = 5;
        startPositionGhost3Y = 6;
        startPositionGhost3X = 2;



        pacman = new Pacman(1, 1, this);
        redGhost = new Ghost("red", startPositionGhost1Y, startPositionGhost1X, this);
        blueGhost = new Ghost("blue", startPositionGhost3Y, startPositionGhost3X, this);
        yellowGhost = new Ghost("yellow", 6, 6, this);

        //setting position for pacman
        board[pacman.getY()][pacman.getX()] = 7;

        //setting position for ghosts
//        board[redGhost.getY()][redGhost.getX()] = 15;
//        board[yellowGhost.getY()][yellowGhost.getX()] = 16;
//        board[startPositionGhost3Y][startPositionGhost3X] = 17;
        boardTable.repaint();
        countSmallPoints = countSmallPoint(board);
        System.out.println("Numbers of points ot eat: " + countSmallPoints);
        win();
        collisionChecker();
        boostChecker();
        printBoard();
        redGhost.spawnBoost();
        yellowGhost.spawnBoost();
        boardPanel.setFocusable(true);
    }

    public int[][] getBoard() {
        return board;
    }

    private int countSmallPoint(int[][] arr) {
        int count = 0;
        for (int i = 0; i < height - 1; i++) {
            for (int j = 0; j < width - 1; j++) {
                if (arr[i][j] == 9)
                    count++;
            }
        }
        return count;
    }

    public void addBoost(int boostType, int y, int x) {
        this.boostBoard[y][x] = boostType;
        boostLocations.add(new Point(x, y));
    }

    public void boostChecker() {
        Thread boostThread = new Thread(() -> {
            while (pacman.isAlive()) {
                int boostType = boostBoard[pacman.getY()][pacman.getX()];
                if (boostType == CHERRY) {
                    System.out.println("Speed boost was applied");
                    pacman.speedBoost();
                }
                if (boostType == BANANA) {
                    System.out.println("One more life was added");
                    pacman.plusLife();
                    gameClass.updateLivesPanel();
                }
                if (boostType == BLUEBERRY) {
                    System.out.println("Pacman is invulnerable for 5 sec");
                    pacman.invulnerable();
                }
                if (boostType == ORANGE) {
                    System.out.println("More 25 sec was added to the timer");
                    addTime();
                }
                if (boostType == APPLE) {
                    System.out.println("For 10 sec pacman receives 2x points");
                    pacman.pointsBoost();
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Thread was interrupted");
                }
            }
        });
        boostThread.start();
    }

    public void addTime() {
        gameClass.setTime(gameClass.getTime() + 25);
    }

    public void collisionChecker() {
        Thread collisionCheckerThread = new Thread(() -> {
            while (pacman.isAlive()) {
                if ((pacman.getX() == redGhost.getX() || pacman.getX() == yellowGhost.getX() || pacman.getX() == blueGhost.getX())
                        && (pacman.getY() == redGhost.getY() || pacman.getY() == yellowGhost.getY() || pacman.getY() == blueGhost.getY()) && !pacman.isInvulnerable()) {
                    System.out.println("Collision");
                    pacman.minusLife();
                    gameClass.updateLivesPanel();
                    if (pacman.getLives() == 0) {
                        pacman.death();
                        redGhost.stop();
                        yellowGhost.stop();
                        blueGhost.stop();
                        SavingScore savingScore = new SavingScore(this);
                        break;
                    }

                    board[redGhost.getY()][redGhost.getX()] = 0;
                    board[yellowGhost.getY()][yellowGhost.getX()] = 0;
                    board[blueGhost.getY()][blueGhost.getX()] = 0;
                    board[pacman.getY()][pacman.getX()] = 0;
                    boardTable.repaint();
                    pacman.setY(startPositionPacmanY);
                    pacman.setX(startPositionPacmanX);
                    redGhost.setY(startPositionGhost1Y);
                    redGhost.setX(startPositionGhost1X);
                    yellowGhost.setY(startPositionGhost2Y);
                    yellowGhost.setX(startPositionGhost2X);
                    blueGhost.setY(startPositionGhost3Y);
                    blueGhost.setX(startPositionGhost3X);
                    board[redGhost.getY()][redGhost.getX()] = 15;
                    board[yellowGhost.getY()][yellowGhost.getX()] = 16;
                    board[blueGhost.getY()][blueGhost.getX()] = 17;

                    boardTable.repaint();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Thread collisionCheckerThread was interrupted");
                }
            }
        });
        collisionCheckerThread.start();

    }

    public void win() {
        new Thread(() -> {
            while (pacman.isAlive()) {
                if (countSmallPoints == 0) {
                    SavingScore savingScore = new SavingScore(this);
                    pacman.death();
                    blueGhost.stop();
                    redGhost.stop();
                    yellowGhost.stop();
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Thread was interrupted");
                }
            }
        }).start();
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public void printBoard() {
        System.out.println("Map:\n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getCountSmallPoints() {
        return countSmallPoints;
    }

    public void setCountSmallPoints(int countSmallPoints) {
        this.countSmallPoints = countSmallPoints;
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public JTable getBoardTable() {
        return boardTable;
    }

    public void initImages() {
        String pathWall = "resources/walls/wall";
        String pathFood = "resources/food/";
        try {
            wall1 = ImageIO.read(new File(pathWall + 1 + ".png"));
            wall2 = ImageIO.read(new File(pathWall + 2 + ".png"));
            wall3 = ImageIO.read(new File(pathWall + 3 + ".png"));
            wall4 = ImageIO.read(new File(pathWall + 4 + ".png"));
            wall5 = ImageIO.read(new File(pathWall + 5 + ".png"));
            wall6 = ImageIO.read(new File(pathWall + 6 + ".png"));
            wall13 = ImageIO.read(new File(pathWall + 13 + ".png"));
            ghostDoor = ImageIO.read(new File("resources/walls/ghost_door.png"));
            smallPoint = ImageIO.read(new File(pathFood + "smallPoint.png"));
            bigPoint = ImageIO.read(new File(pathFood + "bigPoint.png"));
            cherry = ImageIO.read(new File(pathFood + "cherry.png"));
            banana = ImageIO.read(new File(pathFood + "banana.png"));
            blueberry = ImageIO.read(new File(pathFood + "blueberry.png"));
            orange = ImageIO.read(new File(pathFood + "orange.png"));
            apple = ImageIO.read(new File(pathFood + "apple.png"));


        } catch (IOException e) {
            System.out.println("Photo haven't been founded");
        }
    }

    public void setValueAt(int value, int rowIndex, int columnIndex) {
        board[rowIndex][columnIndex] = value;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return ImageIcon.class;
    }

    @Override
    public int getRowCount() {
        return height;
    }

    @Override
    public int getColumnCount() {
        return width;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (board[rowIndex][columnIndex]) {
            case 1:
                return new ImageIcon(wall1);
            case 2:
                return new ImageIcon(wall2);
            case 3:
                return new ImageIcon(wall3);
            case 4:
                return new ImageIcon(wall4);
            case 5:
                return new ImageIcon(wall5);
            case 6:
                return new ImageIcon(wall6);
            case 7:
                return pacman.getCurrentPac();
            case 9:
                return new ImageIcon(smallPoint);
            case 12:
                return new ImageIcon(ghostDoor);
            case 13:
                return new ImageIcon(wall13);
            case 15:
                return redGhost.getCurrentGhost();
            case 16:
                return yellowGhost.getCurrentGhost();
            case 17:
                return blueGhost.getCurrentGhost();
            case 21:
                return new ImageIcon(cherry);
            case 22:
                return new ImageIcon((banana));
            case 23:
                return new ImageIcon(orange);
            case 24:
                return new ImageIcon(apple);
            case 25:
                return new ImageIcon(blueberry);
            default:
                return null;
        }
    }//CHERRY = 21, BANANA = 22, ORANGE = 23, APPLE = 24, BLUEBERRY = 25;

    private class PacmanKeyListener extends KeyAdapter {
        JFrame mainFrame;

        PacmanKeyListener(JFrame mainFrame) {
            this.mainFrame = mainFrame;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 77) {
                pacman.getBoard().printBoard();
//                //for debug
//                for (int i = 0; i < boostBoard.length; i++) {
//                    for (int j = 0; j < boostBoard[i].length; j++) {
//                        System.out.print(boostBoard[i][j] + " ");
//                    }
//                    System.out.println();
//                }
            }
            if (e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_Q) {
                System.out.println("CTRL + SHIFT + Q hotkey was pressed");
                getPacman().death();
                getRedGhost().stop();
                getYellowGhost().stop();
                getBlueGhost().stop();
                new Launch();
                mainFrame.dispose();
            } else {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        pacman.setPacmanMovement(PacmanMovement.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        pacman.setPacmanMovement(PacmanMovement.RIGHT);
                        break;
                    case KeyEvent.VK_UP:
                        pacman.setPacmanMovement(PacmanMovement.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        pacman.setPacmanMovement(PacmanMovement.DOWN);
                        break;
                }
            }
        }
    }

    public Ghost getRedGhost() {
        return redGhost;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public Ghost getYellowGhost() {
        return yellowGhost;
    }

    public Ghost getBlueGhost() {
        return blueGhost;
    }

    public Game getGameClass() {
        return gameClass;
    }
}
