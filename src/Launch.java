import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launch {
    private JFrame window;
    private JPanel windowPanel;
    private JPanel menuPanel;
    private JPanel playPanel;
    private JPanel scorePanel;
    private JButton playButton, scoreButton, exitButton, backScoreButton, backPlayButton;

    Launch() {
        window = new JFrame("Pacman");
        CardLayout cardLayout = new CardLayout();
        windowPanel = new JPanel();
        menuPanel = new JPanel();
        scorePanel = new Score();
        playPanel = new Play();
        window.setBackground(Color.BLACK);
        window.setSize(720, 720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setIconImage(new ImageIcon("resources/pacman_logo.png").getImage());
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

        //back button for score and play panel
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
        menuPanel.add(buttons);
        menuPanel.setBackground(Color.BLACK);

        playPanel.add(backPlayButton);

        windowPanel.setLayout(cardLayout);
        windowPanel.add(menuPanel, "menuPanel");
        windowPanel.add(playPanel, "playPanel");
        windowPanel.add(scorePanel, "scorePanel");
        window.add(this.windowPanel, BorderLayout.CENTER);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
