import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SavingScore extends JFrame {
    private Statistics statistics;
    private JTextField textField;
    private String textName;

    SavingScore(Board board) {
        this.statistics = new Statistics();
        setSize(300, 300);
        setBackground(Color.BLACK);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();

        panel.setBackground(Color.BLACK);
        textField = new JTextField(16);
        textField.setForeground(Color.WHITE);
        textField.setBackground(Color.BLACK);
        textField.setFont(new Font("sarif", Font.BOLD, 16));
        textField.setBorder(new LineBorder(Color.WHITE, 1));
        textField.setPreferredSize(new Dimension(150, 35));

        JLabel textScore = new JLabel();
        textScore.setText("Your score: " + board.getScore());
        textScore.setFont(new Font("sarif", Font.PLAIN, 24));
        textScore.setForeground(Color.WHITE);
        panel.add(textScore);
        JLabel text = new JLabel();
        text.setText("Input your name:");
        text.setFont(new Font("sarif", Font.PLAIN, 24));
        text.setForeground(Color.WHITE);
        panel.add(text);
        panel.add(textField);

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 40));
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(true);
        submitButton.setFont(new Font("sarif", Font.PLAIN, 16));
        submitButton.addActionListener(e -> {
            textName = textField.getText();
            System.out.println(statistics.getStats());
            PlayerScore playerScore = new PlayerScore(textName, board.getScore());
            for (int i = 0; i < statistics.getStats().getModel().getSize(); i++) {
                System.out.println(statistics.getStats().getModel().getElementAt(i));
            }
            statistics.addPersonScore(playerScore);
            statistics.getStats().repaint();
            for (int i = 0; i < statistics.getStats().getModel().getSize(); i++) {
                System.out.println(statistics.getStats().getModel().getElementAt(i));
            }
            dispose();
        });

        panel.add(submitButton);
        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JTextField getTextField() {
        return textField;
    }

    public String getTextName() {
        return textName;
    }

    public Statistics getStatistics() {
        return statistics;
    }

//    private void saveDataToFile(PlayerScore playerScore) {
//        try {
//            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(pathToFile, true));
//            out.writeObject(playerScore);
//            out.close();
//        } catch (IOException ex) {
//            System.out.println("Failed to save score data");
//        }
//    }


}
