import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    private int boardSizeX;
    private int boardSizeY;
    private JPanel boardSizeAsking;
    private JPanel mainGame;
    private JButton submitSize;
    private JTextField boardSizeInputX, boardSizeInputY;
    private JLabel currentScore;
    private JPanel bottomPanel;
    private JPanel livesPanel;
    private JPanel foodPanel;
    private int score;
    private Pacman pacman;
    private Board board;
    private Ghost ghost1, ghost2, ghost3;
    private Image pacmanTest;
    Game(){

    }
}
