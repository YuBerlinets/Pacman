import javax.swing.*;
import java.awt.*;

public class Pacman extends JLabel {
    private Image appearance;
    public Pacman(){
        appearance = new ImageIcon("resources/pacman.png").getImage();
//        JLabel pacmanLabel = new JLabel(new ImageIcon("resources/pacman.png"));

    }
}
