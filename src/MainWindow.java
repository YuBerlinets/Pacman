import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private JPanel menu;
    MainWindow(JPanel menu) {
        this.menu = menu;
        this.setTitle("Pacman");
        this.setIconImage(new ImageIcon("resources/pacman_logo.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(720, 720);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

//        this.getContentPane().add(this.menu, BorderLayout.CENTER);
        this.add(this.menu);

        this.setVisible(true);
    }
}
