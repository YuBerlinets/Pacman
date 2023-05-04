import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launch {
    private JFrame window = new JFrame("Pacman");
    private JPanel windowPanel = new JPanel();
    private JPanel menuPanel = new JPanel();
    private JPanel playPanel;
    private JPanel scorePanel;
    private JButton playButton, scoreButton, exitButton, backScoreButton, backPlayButton;

    Launch() {
        CardLayout cardLayout = new CardLayout();
        scorePanel = new Score();
        playPanel = new Play();
        window.setBackground(Color.BLACK);
        window.setSize(720, 720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setIconImage(new ImageIcon("resources/pacman_logo.png").getImage());
//        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setLayout(new GridBagLayout());
        windowPanel.setLayout(cardLayout);

        //logo and text panel
        JPanel information = new JPanel();
        information.setLayout(new GridBagLayout());
        JLabel logo = new JLabel(new ImageIcon("resources/info.png"));
        information.add(logo);
        information.setBackground(Color.BLACK);
        //buttons panel
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(5, 1));
        buttons.setBackground(Color.BLACK);

        playButton = new MenuButton("Play");
        playButton.addActionListener(event -> {
            if (event.getSource() == playButton) {
                cardLayout.show(windowPanel, "playPanel");
            }
        });
        scoreButton = new MenuButton("Score");
        scoreButton.addActionListener(event -> {
            if (event.getSource() == scoreButton) {
                cardLayout.show(windowPanel, "scorePanel");
            }
        });
        exitButton = new MenuButton("Exit");
        exitButton.addActionListener(event -> {
            Window window = SwingUtilities.getWindowAncestor(exitButton);
            window.dispose();
        });

        //back button for score panel

        backScoreButton = new MenuButton("Back to Menu");
        backScoreButton.addActionListener(event -> {
            cardLayout.show(windowPanel, "menuPanel");

        });
        backPlayButton = new MenuButton("Back to Menu");
        backPlayButton.addActionListener(event -> {
            cardLayout.show(windowPanel, "menuPanel");
        });

        //adding buttons
        buttons.add(playButton);
        buttons.add(Box.createVerticalStrut(10));
        buttons.add(scoreButton);
        buttons.add(Box.createVerticalStrut(10));
        buttons.add(exitButton);

        //adding panels to the frame
        //menuPanel.add(information);
        menuPanel.add(buttons);
        menuPanel.setBackground(Color.BLACK);

        playPanel.add(backPlayButton);
        scorePanel.add(backScoreButton);

        windowPanel.setLayout(cardLayout);
        windowPanel.add(menuPanel, "menuPanel");
        windowPanel.add(playPanel, "playPanel");
        windowPanel.add(scorePanel, "scorePanel");
        window.add(this.windowPanel, BorderLayout.CENTER);
        window.setVisible(true);
    }
}
