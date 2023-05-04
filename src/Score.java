//import javax.swing.*;
//import java.awt.*;
//
//public class Score extends JPanel {
//    Score(){
//        Statistics statistics = new Statistics();
//        this.setVisible(true);
//        this.setSize(200,200);
//        JLabel scoreTitle = new JLabel("Score:");
//        scoreTitle.setFont(new Font("sarif",Font.BOLD, 32));
//        scoreTitle.setForeground(Color.WHITE);
//        this.add(scoreTitle);
//        this.add(statistics.getStatistics());
//        this.setBackground(Color.BLACK);
//        this.setLayout(new GridBagLayout());
//    }
//}
//import javax.swing.*;
//import java.awt.*;
//
//public class Score extends JPanel {
//    Score(){
//        Statistics statistics = new Statistics();
//        this.setVisible(true);
//        this.setSize(200,200);
//        this.setLayout(new BorderLayout());
//
//        // Add title label to the top
//        JLabel scoreTitle = new JLabel("Score:");
//        scoreTitle.setFont(new Font("sarif", Font.BOLD, 32));
//        scoreTitle.setForeground(Color.WHITE);
//        JPanel titlePanel = new JPanel();
//        titlePanel.setBackground(Color.BLACK);
//        titlePanel.add(scoreTitle);
//        this.add(titlePanel, BorderLayout.NORTH);
//
//        // Add statistics to the center
//        JPanel statisticsPanel = new JPanel();
//        statisticsPanel.setBackground(Color.BLACK);
//        statisticsPanel.add(statistics.getStatistics());
//        this.add(statisticsPanel, BorderLayout.CENTER);
//
//        this.setBackground(Color.BLACK);
//    }
//}
import javax.swing.*;
import java.awt.*;

public class Score extends JPanel {
    Score(){
        Statistics statistics = new Statistics();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        JLabel scoreTitle = new JLabel("Score:");
        scoreTitle.setFont(new Font("sarif", Font.BOLD, 32));
        scoreTitle.setForeground(Color.WHITE);
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);
        titlePanel.add(scoreTitle);
        this.add(titlePanel, BorderLayout.NORTH);

        JPanel statisticsPanel = new JPanel();
        statisticsPanel.setBackground(Color.BLACK);
        statisticsPanel.add(statistics.getStatistics());
        this.add(statisticsPanel, BorderLayout.CENTER);

        JButton backButton = new MenuButton("Back to Menu");
        backButton.setPreferredSize(new Dimension(200, 50)); // Set preferred size of the button
        backButton.addActionListener(event -> {
            CardLayout cardLayout = (CardLayout) getParent().getLayout();
            cardLayout.show(getParent(), "menuPanel");
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(backButton);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
}
