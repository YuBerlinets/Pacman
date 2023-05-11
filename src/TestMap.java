import java.awt.*;
import javax.swing.*;

public class TestMap {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            JFrame jFrame = new JFrame();
            jFrame.setSize(720, 720);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setLocationRelativeTo(null);

            JPanel jPanel = new JPanel();
            jPanel.setBackground(Color.BLACK);
            Board board = new Board(22, 22);


            jPanel.add(board.getBoardPanel());
//            Thread thread = new Thread(()->{
//                try {
//                    board.getPacman().getPacAnim();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//            thread.start();
            jFrame.add(jPanel);
            jFrame.pack();
            jFrame.setVisible(true);
        });
    }
}
