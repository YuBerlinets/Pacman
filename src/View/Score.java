package View;

import Model.Statistics;

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

        statisticsPanel.add(statistics.getStats());
        this.add(statisticsPanel, BorderLayout.CENTER);

        JButton backButton = new MenuButton("Back to Menu");
        backButton.setPreferredSize(new Dimension(200, 50));
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
