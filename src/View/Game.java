package View;

import Controller.Board;
import Controller.SavingScore;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    private JPanel mainGame;
    private JLabel currentScore;
    private JLabel currentTime;
    private JPanel bottomPanel;
    private JPanel livesPanel;
    private JPanel topPanel;
    private JPanel foodPanel;
    private int score;
    private int time;
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

        topPanel = new JPanel(new BorderLayout());

        topPanel.setBackground(Color.BLACK);

        currentScore = new JLabel("Score: " + score);
        currentScore.setFont(new Font("SansSerif", Font.BOLD, 22));
        currentScore.setForeground(Color.YELLOW);

        time = 120;
        currentTime = new JLabel("Time: " + time);
        currentTime.setFont(new Font("SansSerif", Font.BOLD, 22));
        currentTime.setForeground(Color.YELLOW);

        //initialising board
        board = new Board(height, width, this);
        topPanel.add(currentScore, BorderLayout.WEST);
        topPanel.add(currentTime,BorderLayout.EAST);
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

        //updating time
        Thread timeThread = new Thread(()->{
            while (board.getPacman().isAlive()) {
                try {
                    currentTime.setText("Time: " + time);
                    time--;
                    if(time == 0){
                        SavingScore savingScore = new SavingScore(board);
                        board.getPacman().death();
                        break;
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("TimeThread was interrupted");
                }
            }
        });
        timeThread.start();



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
        mainGame.add(topPanel, BorderLayout.NORTH);

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

