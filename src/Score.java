import javax.swing.*;
import java.awt.*;

public class Score extends JPanel {
    Score(){
        Statistics statistics = new Statistics();
        this.setVisible(true);
        this.setSize(200,200);
        JLabel scoreTitle = new JLabel("Score:");
        scoreTitle.setFont(new Font("sarif",Font.BOLD, 32));
        scoreTitle.setForeground(Color.WHITE);
        this.add(scoreTitle);
        this.add(statistics.getStatistics());
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(3,1));
    }
}
