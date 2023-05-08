import javax.swing.*;

public class TestMap {
    JFrame jFrame;
    Board board;

    TestMap() {
        int size = 40;
        jFrame = new JFrame();
        jFrame.setSize(720,720);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        JPanel map = new JPanel();
        NewBoard newBoard = new NewBoard(20,20);
        //map.add(newBoard);

    }

    public static void main(String[] args) {
        new TestMap();
    }
}
