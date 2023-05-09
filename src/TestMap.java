import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class TestMap {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(720, 720);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.BLACK);
        NewBoard newBoard = new NewBoard(22,22);

        jFrame.add(newBoard.getBoardPanel());
        jFrame.pack();
        jFrame.setVisible(true);

    }
}
