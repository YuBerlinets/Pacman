import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Play extends JPanel {
    private int boardSizeX;
    private int boardSizeY;
    private JPanel boardSizeAsking;
    private JButton submitSize;
    private JButton backButton;
    private JTextField boardSizeInputX, boardSizeInputY;

    public Play() {
        CardLayout cardLayout = new CardLayout();
        this.setVisible(true);
        this.setSize(720, 720);
        this.setBackground(Color.BLACK);
        this.setLayout(cardLayout);

        boardSizeAsking = new JPanel(new GridBagLayout());

        JLabel boardSizeTextLabel = new JLabel("Enter size of the board:");
        boardSizeTextLabel.setFont(new Font("serif", Font.PLAIN, 22));
        boardSizeTextLabel.setForeground(Color.WHITE);

        boardSizeInputX = new JTextField();
        boardSizeInputX.setBackground(Color.BLACK);
        boardSizeInputX.setForeground(Color.WHITE);
        boardSizeInputX.setFont(new Font("sarif", Font.BOLD, 16));
        boardSizeInputX.setBorder(new LineBorder(Color.WHITE, 1));
        boardSizeInputX.setPreferredSize(new Dimension(150, 40));
        boardSizeInputX.addActionListener(event -> {
            boardSizeX = Integer.parseInt(boardSizeInputX.getText());
        });

        boardSizeInputY = new JTextField();
        boardSizeInputY.setBackground(Color.BLACK);
        boardSizeInputY.setForeground(Color.WHITE);
        boardSizeInputY.setFont(new Font("sarif", Font.BOLD, 16));
        boardSizeInputY.setBorder(new LineBorder(Color.WHITE, 1));
        boardSizeInputY.setPreferredSize(new Dimension(150, 40));
        boardSizeInputY.addActionListener(event -> {
            boardSizeY = Integer.parseInt(boardSizeInputY.getText());
        });


        submitSize = new MenuButton("Submit");
        submitSize.addActionListener(event -> {
            if (event.getSource() == submitSize) {
                if (!isValid(boardSizeInputX.getText()) || !isValid(boardSizeInputY.getText())) {
                    submitSize.setText("Enter digits!!");
                } else {
                    boardSizeX = Integer.parseInt(boardSizeInputX.getText());
                    boardSizeY = Integer.parseInt(boardSizeInputY.getText());
                    new Game(boardSizeX, boardSizeY);
                    boardSizeInputX.setText("");
                    boardSizeInputY.setText("");
                    submitSize.setText("Submit");
                }
            }
        });
        backButton = new MenuButton("Back to Menu");
        backButton.addActionListener(event -> {
            if (event.getSource() == backButton) {
                CardLayout cardLayoutCreatingGamePanel = (CardLayout) getParent().getLayout();
                cardLayoutCreatingGamePanel.show(getParent(), "menuPanel");
            }
        });

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

        gridBagConstraints.gridy = 4;
        boardSizeAsking.add(backButton, gridBagConstraints);

        boardSizeAsking.setBackground(Color.BLACK);
        this.add(boardSizeAsking);
    }

    private boolean isValid(String size) {
        if (size.isEmpty())
            return false;

        int check;
        try {
            check = Integer.parseInt(size);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("String can't be entered");
        }
        return false;
    }

}
