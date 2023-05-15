import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    private BufferedImage wall1, wall2, wall3, wall4, wall5, wall6, wall13, ghostDoor;
    private BufferedImage smallPoint, bigPoint, cherry, banana, orange, apple, blueberry;
    private BoardGenerator boardGenerator;
    private Ghost redGhost;
    private Ghost yellowGhost;
    private Ghost blueGhost;

    Board(int heightInput, int widthInput) {
        this.height = heightInput;
        this.width = widthInput;
        this.countSmallPoints = 0;
        this.score = 0;
        boardGenerator = new BoardGenerator();
        board = boardGenerator.generateBoard(height, width);


        //set size of cell
        if (height > 50 || width > 50) {
            cellWidth = 20;
            cellHeight = 20;
        } else {
            cellWidth = 30;
            cellHeight = 30;
        }


        //loading pics
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

        printBoard();

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
        boardPanel.addKeyListener(new PacmanKeyListener());

        pacman = new Pacman(1, 1, this);
        redGhost = new Ghost("red", board.length-5, board[1].length-5, this);
        yellowGhost = new Ghost("yellow", board.length-5 , 5 , this);
        blueGhost = new Ghost("blue", 6, 2, this);

        //setting position for pacman
        board[pacman.getY()][pacman.getX()] = 7;

        //setting position for ghosts
        board[redGhost.getY()][redGhost.getX()] = 15;
        board[yellowGhost.getY()][yellowGhost.getX()] = 16;
        board[blueGhost.getY()][blueGhost.getX()] = 17;

        countSmallPoints = countSmallPoint(board);
        System.out.println("Numbers of points ot eat: " + countSmallPoints);
        win();
        boardPanel.setFocusable(true);
    }

    public int[][] getBoard() {
        return board;
    }

    private int countSmallPoint(int[][] arr) {
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (arr[i][j] == 9)
                    count++;
            }
        }
        return count;
    }

    private class PacmanKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    pacman.setPacmanMovement(PacmanMovement.LEFT);
                    System.out.println("Left " + e.getKeyCode());
                    break;
                case KeyEvent.VK_RIGHT:
                    pacman.setPacmanMovement(PacmanMovement.RIGHT);
                    System.out.println("right " + e.getKeyCode());
                    break;
                case KeyEvent.VK_UP:
                    pacman.setPacmanMovement(PacmanMovement.UP);
                    System.out.println("up " + e.getKeyCode());
                    break;
                case KeyEvent.VK_DOWN:
                    pacman.setPacmanMovement(PacmanMovement.DOWN);
                    System.out.println("down " + e.getKeyCode());
                    break;
            }
            //boardTable.repaint();
            System.out.println(pacman.getCurrentPac());
        }
    }

    public void win() {
        new Thread(() -> {
            while (pacman.isAlive()) {
                if (countSmallPoints == 0) {
                    SavingScore savingScore = new SavingScore(this);
                    pacman.death();
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

    public Ghost getRedGhost() {
        return redGhost;
    }

    public BufferedImage getWall1() {
        return wall1;
    }

    public BufferedImage getWall2() {
        return wall2;
    }

    public BufferedImage getWall3() {
        return wall3;
    }

    public BufferedImage getWall4() {
        return wall4;
    }

    public BufferedImage getWall5() {
        return wall5;
    }

    public BufferedImage getWall6() {
        return wall6;
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
}
