import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Play extends JPanel {
    private int boardSize;
    private JPanel boardSizeAsking;
    private JPanel mainGame;
    private JButton submitSize;
    private JTextField boardSizeInput;
    private Pacman pacman;
    private Ghost ghost1, ghost2, ghost3;
    private Image pacmanTest;

    public Play() {
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

        boardSizeInput = new JTextField();
        boardSizeInput.setBackground(Color.BLACK);
        boardSizeInput.setForeground(Color.WHITE);
        boardSizeInput.setFont(new Font("sarif", Font.BOLD, 16));
        boardSizeInput.setBorder(new LineBorder(Color.WHITE, 1));
        boardSizeInput.setPreferredSize(new Dimension(100, 30));
        boardSizeInput.addActionListener(event -> {
            boardSize = Integer.parseInt(boardSizeInput.getText());
            System.out.println(boardSize);
        });

        submitSize = new MenuButton("Submit");
        submitSize.addActionListener(event -> {
            if (event.getSource() == submitSize) {
                boardSize = Integer.parseInt(boardSizeInput.getText());
                System.out.println(boardSize);
                cardLayout.show(this, "mainGame");
            }
        });

        mainGame = new JPanel();
        mainGame.setLayout(new BorderLayout());
        JButton backButton = new MenuButton("Back to Menu");
        backButton.setPreferredSize(new Dimension(200, 50)); // Set preferred size of the button
        backButton.addActionListener(event -> {
            CardLayout cardLayoutGamePanel = (CardLayout) getParent().getLayout();
            cardLayoutGamePanel.show(getParent(), "menuPanel");
        });
        mainGame.add(backButton,BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        boardSizeAsking.add(boardSizeTextLabel, gbc);

        gbc.gridy = 1;
        boardSizeAsking.add(boardSizeInput, gbc);

        gbc.gridy = 2;
        boardSizeAsking.add(submitSize, gbc);

        boardSizeAsking.setBackground(Color.BLACK);

        mainGame.setBackground(Color.BLACK);

        this.add(boardSizeAsking);
        this.add(mainGame, "mainGame");
    }
}
