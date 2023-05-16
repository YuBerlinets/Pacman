package View;

import Controller.Board;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    private JPanel mainGame;
    private JLabel currentScore;
    private JPanel bottomPanel;
    private JPanel livesPanel;
    private JPanel foodPanel;
    private int score;
    private Board board;


    public Game(int height, int width, JFrame firstLaunch) {
        this.setTitle("Pacman");
        this.setIconImage(new ImageIcon("resources/pacman_logo.png").getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        if (height > 50 || width > 50) {
            this.setSize(width * 25, height * 25);
        } else if (height < 18 || width < 18)
            this.setSize(width * 45, height * 40);
        else
            this.setSize(width * 35, height * 35);


        //main game appearance
        mainGame = new JPanel();
        mainGame.setLayout(new BorderLayout());
        currentScore = new JLabel("Score: " + score);
        currentScore.setFont(new Font("SansSerif", Font.BOLD, 22));
        currentScore.setForeground(Color.YELLOW);

        board = new Board(height, width, this);

        //updating score
        Thread scoreThread = new Thread(() -> {
            while (board.getPacman().isAlive()) {
                try {
                    currentScore.setText("Score: " + board.getScore());
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("ScoreThread was interrupted");
                }
            }
        });
        scoreThread.start();

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
            board.getPacman().death();
            board.getRedGhost().stop();
            board.getYellowGhost().stop();
            board.getBlueGhost().stop();
            new Launch();
            this.dispose();
        });
        buttonBottomPanel.add(backButton);

        //adding lives panel
        livesPanel = new JPanel();
        livesPanel.setLayout(new BoxLayout(livesPanel, BoxLayout.X_AXIS));
        livesPanel.setBackground(Color.BLACK);
        for (int i = 0; i < board.getPacman().getLives(); i++) {
            livesPanel.add(Box.createHorizontalStrut(10));
            JLabel pacmanLive = new JLabel((new ImageIcon("resources/pacmanLife.png")));
            livesPanel.add(pacmanLive);
        }

//        JLabel pacmanLive1 = new JLabel(new ImageIcon("resources/pacmanLife.png"));
//        JLabel pacmanLive2 = new JLabel(new ImageIcon("resources/pacmanLife.png"));
//        JLabel pacmanLive3 = new JLabel(new ImageIcon("resources/pacmanLife.png"));
//        livesPanel.add(Box.createHorizontalStrut(10));
//        livesPanel.add(pacmanLive1);
//        livesPanel.add(Box.createHorizontalStrut(10));
//        livesPanel.add(pacmanLive2);
//        livesPanel.add(Box.createHorizontalStrut(10));
//        livesPanel.add(pacmanLive3);
        foodPanel = new JPanel();
        foodPanel.setBackground(Color.BLACK);
        foodPanel.setLayout(new GridLayout(1, 1));
        JLabel cherry = new JLabel(new ImageIcon("resources/food/cherry.png"));
        foodPanel.add(cherry);

        bottomPanel.add(livesPanel);
        bottomPanel.add(foodPanel);
        bottomPanel.add(buttonBottomPanel);

        mainGame.add(bottomPanel, BorderLayout.SOUTH);
        //mainGame.add(board.getBoardPanel(), BorderLayout.CENTER);
        mainGame.add(board.getBoardPanel(), BorderLayout.CENTER);
        mainGame.add(currentScore, BorderLayout.NORTH);

        mainGame.setBackground(Color.BLACK);
        this.add(mainGame);
        this.setLocationRelativeTo(null);
    }

    public void updateLivesPanel() {
        livesPanel.removeAll();
        for (int i = 0; i < board.getPacman().getLives(); i++) {
            livesPanel.add(Box.createHorizontalStrut(10));
            JLabel pacmanLive = new JLabel((new ImageIcon("resources/pacmanLife.png")));
            livesPanel.add(pacmanLive);
        }
        livesPanel.revalidate();
        livesPanel.repaint();
    }
}

