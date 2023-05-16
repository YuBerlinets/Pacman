import java.awt.*;
import javax.swing.*;

public class TestMap {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(720, 720);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.BLACK);
//        Controller.Board board = new Controller.Board(22, 22,jFrame);

//        jPanel.add(board.getBoardPanel());

        jFrame.setBackground(Color.BLACK);
        jFrame.add(jPanel);
        jFrame.pack();

        jFrame.setVisible(true);
    }
}
