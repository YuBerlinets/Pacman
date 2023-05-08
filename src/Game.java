import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    private JPanel mainGame;
    private JLabel currentScore;
    private JPanel bottomPanel;
    private JPanel livesPanel;
    private JPanel foodPanel;
    private int score;
    private Pacman pacman;
    private Board board;
    private Ghost ghost1, ghost2, ghost3;
    private Image pacmanTest;


    public Game(int height, int width) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(width*30,height*30);
        this.setLocationRelativeTo(null);
        //main game appearance
        mainGame = new JPanel();
        mainGame.setLayout(new BorderLayout());
        currentScore = new JLabel("Score: " + score);
        currentScore.setFont(new Font("SansSerif", Font.BOLD, 22));
        currentScore.setForeground(Color.YELLOW);

        board = new Board(height,width);

        //adding bottom panel
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));

        //panel for button to comeback to menu
        JPanel buttonBottomPanel = new JPanel();
        buttonBottomPanel.setLayout(new GridBagLayout());
        buttonBottomPanel.setBackground(Color.BLACK);
        buttonBottomPanel.setPreferredSize(new Dimension(720, 45));

        //button for returning to the menu
        JButton backButton = new MenuButton("Back to Menu");
        backButton.setPreferredSize(new Dimension(180, 35));
        backButton.addActionListener(event -> {
            this.dispose();
        });
        buttonBottomPanel.add(backButton);

        //adding lives panel
        livesPanel = new JPanel();
        livesPanel.setLayout(new BoxLayout(livesPanel, BoxLayout.X_AXIS));
        livesPanel.setBackground(Color.BLACK);
        JLabel pacmanLive1 = new JLabel(new ImageIcon("resources/pacmanLife.png"));
        JLabel pacmanLive2 = new JLabel(new ImageIcon("resources/pacmanLife.png"));
        JLabel pacmanLive3 = new JLabel(new ImageIcon("resources/pacmanLife.png"));
        livesPanel.add(Box.createHorizontalStrut(10));
        livesPanel.add(pacmanLive1);
        livesPanel.add(Box.createHorizontalStrut(10));
        livesPanel.add(pacmanLive2);
        livesPanel.add(Box.createHorizontalStrut(10));
        livesPanel.add(pacmanLive3);
        foodPanel = new JPanel();
        foodPanel.setBackground(Color.BLACK);
        foodPanel.setLayout(new GridLayout(1, 1));
        JLabel cherry = new JLabel(new ImageIcon("resources/food/cherry.png"));
        foodPanel.add(cherry);

        bottomPanel.add(livesPanel);
        bottomPanel.add(foodPanel);
        bottomPanel.add(buttonBottomPanel);

        mainGame.add(bottomPanel, BorderLayout.SOUTH);
        mainGame.add(board.getBoardPanel(), BorderLayout.CENTER);
        mainGame.add(currentScore, BorderLayout.NORTH);

        mainGame.setBackground(Color.BLACK);
        this.add(mainGame);
    }
}

