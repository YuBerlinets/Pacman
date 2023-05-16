package View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
public class MenuButton extends JButton {
    MenuButton(String text){
        this.setText(text);
        this.setPreferredSize(new Dimension(200,50));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setFocusPainted(false);
        this.setFont(new Font("sarif", Font.PLAIN, 22));
    }
}
