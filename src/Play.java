import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Play extends JPanel {
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

    public Play() {
        score = 0;
        CardLayout cardLayout = new CardLayout();
        this.setVisible(true);
        this.setSize(720, 720);
        this.setBackground(Color.BLACK);
        this.setLayout(cardLayout);

        pacman = new Pacman();
        boardSizeAsking = new JPanel(new GridBagLayout());

        JLabel boardSizeTextLabel = new JLabel("Enter size of the board:");
        boardSizeTextLabel.setFont(new Font("serif", Font.PLAIN, 22));
        boardSizeTextLabel.setForeground(Color.WHITE);

        boardSizeInputX = new JTextField();
        boardSizeInputX.setBackground(Color.BLACK);
        boardSizeInputX.setForeground(Color.WHITE);
        boardSizeInputX.setFont(new Font("sarif", Font.BOLD, 16));
        boardSizeInputX.setBorder(new LineBorder(Color.WHITE, 1));
        boardSizeInputX.setPreferredSize(new Dimension(100, 30));
        boardSizeInputX.addActionListener(event -> {
            boardSizeX = Integer.parseInt(boardSizeInputX.getText());
        });

        boardSizeInputY = new JTextField();
        boardSizeInputY.setBackground(Color.BLACK);
        boardSizeInputY.setForeground(Color.WHITE);
        boardSizeInputY.setFont(new Font("sarif", Font.BOLD, 16));
        boardSizeInputY.setBorder(new LineBorder(Color.WHITE, 1));
        boardSizeInputY.setPreferredSize(new Dimension(100, 30));
        boardSizeInputY.addActionListener(event -> {
            boardSizeY = Integer.parseInt(boardSizeInputY.getText());
        });


        mainGame = new JPanel();
        submitSize = new MenuButton("Submit");
        submitSize.addActionListener(event -> {
            if (event.getSource() == submitSize) {
                boardSizeX = Integer.parseInt(boardSizeInputX.getText());
                boardSizeY = Integer.parseInt(boardSizeInputY.getText());
                board = new Board(boardSizeX, boardSizeY);
                mainGame.add(board.getBoardPanel());
                cardLayout.show(this, "mainGame");
            }
        });


        //main game appearance
        mainGame.setLayout(new BorderLayout());
        currentScore = new JLabel("Score: " + score);
        currentScore.setFont(new Font("SansSerif", Font.BOLD, 22));
        currentScore.setForeground(Color.YELLOW);

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
            CardLayout cardLayoutGamePanel = (CardLayout) getParent().getLayout();
            cardLayoutGamePanel.show(getParent(), "menuPanel");

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
        mainGame.add(currentScore, BorderLayout.NORTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 0, 10, 0);
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        boardSizeAsking.add(boardSizeTextLabel, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        boardSizeAsking.add(boardSizeInputX, gridBagConstraints);

        gridBagConstraints.gridy = 2;
        boardSizeAsking.add(boardSizeInputY, gridBagConstraints);

        gridBagConstraints.gridy = 3;
        boardSizeAsking.add(submitSize, gridBagConstraints);

        boardSizeAsking.setBackground(Color.BLACK);
        mainGame.setBackground(Color.BLACK);
        this.add(boardSizeAsking);
        this.add(mainGame, "mainGame");
    }

}
