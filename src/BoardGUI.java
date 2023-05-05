import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class BoardGUI {
    private JPanel boardPanel;
    private int[][] board;
    private int height;
    private int width;
    private BufferedImage wall1, wall2, wall3, wall4, wall5, wall6;
    private BufferedImage smallPoing, bigPoint, cherry;

    BoardGUI(int height, int width) {
        this.height = height;
        this.width = width;

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
            smallPoing = ImageIO.read(new File(pathFood + "smallPoint.png"));
            bigPoint = ImageIO.read(new File(pathFood + "bigPoint.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize board panel
        boardPanel = new JPanel(new GridLayout(height, width));
        boardPanel.setPreferredSize(new Dimension(height * 15, width * 15));
        boardPanel.setBackground(Color.BLACK);
        // Initialize board
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
                else
                    board[i][j] = 0;


                JLabel cell = new JLabel();
                cell.setPreferredSize(new Dimension(15, 15));
                if (board[i][j] == 1)
                    cell.setIcon(new ImageIcon(wall1));
                else if (board[i][j] == 2)
                    cell.setIcon(new ImageIcon(wall2));
                else if (board[i][j] == 3)
                    cell.setIcon(new ImageIcon(wall3));
                else if (board[i][j] == 4)
                    cell.setIcon(new ImageIcon(wall4));
                else if (board[i][j] == 5)
                    cell.setIcon(new ImageIcon(wall5));
                else if (board[i][j] == 6)
                    cell.setIcon(new ImageIcon(wall6));
                else
                    cell.setIcon(new ImageIcon(smallPoing));
                boardPanel.add(cell);
            }
        }
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public void printBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
