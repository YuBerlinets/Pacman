import javax.swing.*;

public class TestMap {
    JFrame jFrame;
    Board board;

    TestMap() {
        int size = 40;
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(size*30, size*30);
        board = new Board(size, size);
        jFrame.add(board.getBoardPanel());
        board.printBoard();
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new TestMap();
    }
}
