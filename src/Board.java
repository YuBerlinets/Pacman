import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Board extends AbstractTableModel {
    private JPanel boardPanel;
    private int[][] board;
    private int height;
    private int width;
    private JTable boardTable;
    private Pacman pacman;
    private BufferedImage wall1, wall2, wall3, wall4, wall5, wall6;
    private BufferedImage smallPoint, bigPoint, cherry;

    Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.pacman = new Pacman(1, 1, this);


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
            smallPoint = ImageIO.read(new File(pathFood + "smallPoint.png"));
            bigPoint = ImageIO.read(new File(pathFood + "bigPoint.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // initialize board
        board = new int[height][width];
        Random random = new Random();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 && j == 0)
                    board[i][j] = 3;
                else if (i == 1 && j == 1)
                    board[i][j] = 7;
                else if (i == 0 && j == width - 1)
                    board[i][j] = 4;
                else if ((i == height - 1 && j == 0))
                    board[i][j] = 5;
                else if (i == height - 1 && j == width - 1)
                    board[i][j] = 6;
                else if (i == 0 || i == height - 1)
                    board[i][j] = 2;
                else if (j == 0 || j == width - 1)
                    board[i][j] = 1;
                else if (random.nextDouble() < 0.2)
                    board[i][j] = 1;
                else
                    board[i][j] = 0;

            }
        }
        boardTable = new JTable(this);
        boardTable.setRowHeight(30);
        boardTable.setBackground(Color.BLACK);
        boardTable.setRowHeight(30);
        boardTable.setShowGrid(false);
        boardTable.setCellSelectionEnabled(false);
        boardTable.setColumnSelectionAllowed(false);
        boardTable.setRowSelectionAllowed(false);
        for (int i = 0; i < width; i++) {
            boardTable.getColumnModel().getColumn(i).setPreferredWidth(30);
        }

        boardPanel = new JPanel();
        boardPanel.setBackground(Color.BLACK);

        boardPanel.add(getBoardTable());
        boardPanel.addKeyListener(new PacmanKeyListener());
        boardPanel.setFocusable(true);

    }

    public int[][] getBoard() {
        return board;
    }

    private class PacmanKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    pacman.moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    pacman.moveRight();
                    break;
                case KeyEvent.VK_UP:
                    pacman.moveUp();
                    break;
                case KeyEvent.VK_DOWN:
                    pacman.moveDown();
                    break;
            }
            boardTable.repaint();
            System.out.println(pacman.getX() + " " + pacman.getY());
        }
    }

    public void printBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }


    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public JTable getBoardTable() {
        return boardTable;
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
                return pacman.getPacRIGHT();
            case 0:
                return new ImageIcon(smallPoint);
            default:
                return null;
        }
    }

}
