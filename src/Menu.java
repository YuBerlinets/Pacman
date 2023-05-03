import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel implements ActionListener {
    Menu() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel information = new JPanel();
        information.setLayout(new GridBagLayout());
        JLabel logo = new JLabel(new ImageIcon("resources/info.png"));

        JLabel title = new JLabel("Pacman");
        title.setFont(new Font("serif", Font.BOLD, 52));
        title.setForeground(Color.WHITE);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(5,1));
//        buttons.setLayout(new BoxLayout(buttons,BoxLayout.Y_AXIS));
        JButton playButton = new MenuButton("Play");
        JButton scoreButton = new MenuButton("Score");
        scoreButton.addActionListener(event -> {
            if(event.getSource() == scoreButton){
                Score score = new Score();
            }
        });
        JButton exitButton = new MenuButton("Exit");
        exitButton.addActionListener(event -> {
            Window window = SwingUtilities.getWindowAncestor(exitButton);
            window.dispose();
        });
        information.add(logo);
        buttons.add(playButton);
        buttons.add(Box.createVerticalStrut(10));
        buttons.add(scoreButton);
        buttons.add(Box.createVerticalStrut(10));
        buttons.add(exitButton);
        this.add(information);
        this.add(buttons);
        this.setBackground(Color.BLACK);
        buttons.setBackground(Color.BLACK);
        information.setBackground(Color.BLACK);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
