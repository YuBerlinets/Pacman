import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class NewBoard extends AbstractTableModel {
    private JPanel boardPanel;
    private int[][] board;
    private int height;
    private int width;
    private JTable boardTable;
    private Pacman pacman;
    private BufferedImage wall1, wall2, wall3, wall4, wall5, wall6;
    private BufferedImage smallPoint, bigPoint, cherry;

    NewBoard(int height, int width){
        this.height = height;
        this.width = width;
        this.pacman = new Pacman();

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
                else if (i == 1 && j == 1)
                    board[i][j] = 7;
                else
                    board[i][j] = 0;

            }
        }
        boardTable = new JTable();
    }

    public JTable getTable(){
        return  null;
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
        return board[rowIndex][columnIndex];
    }
}
