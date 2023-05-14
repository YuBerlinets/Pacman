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
    private BufferedImage smallPoint, bigPoint, cherry;
    private BoardGenerator boardGenerator;
    private Ghost redGhost;

    Board(int height, int width) {
        this.height = height;
        this.width = width;
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
//        redGhost = new Ghost("red", 10, 10, this);
        //setting position for pacman
        board[pacman.getY()][pacman.getX()] = 7;
        //setting position for redGhost
//        board[10][10] = 15;
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
//                    this.boardPanel.getParent()
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
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
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
//            case 15:
//                return redGhost.getCurrentGhost();
            default:
                return null;
        }
    }

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
}
