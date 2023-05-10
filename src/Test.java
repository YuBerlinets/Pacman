//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.KeyEvent;
//
//public class Test extends JFrame implements Runnable {
//    private Pacman pacman;
//
//    public Test() {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(720, 720);
//        setLocationRelativeTo(null);
//        setVisible(true);
//        setLayout(null);
//        Board board = new Board(22,22);
//        pacman = new Pacman(0, 0, board);
//        setBackground(Color.BLACK);
//
//        JTable jTable = new JTable(board);
//        JPanel jPanel = new JPanel();
//
//        jPanel.add(pacman);
//        jPanel.setBackground(Color.BLACK);
//        jPanel.add(jTable);
//        add(jPanel);
//        add(pacman.getBoard().getBoardPanel());
//        pacman.setBounds(pacman.getX(), pacman.getY(), 30, 30);
//        addKeyListener(new java.awt.event.KeyAdapter() {
//            public void keyPressed(KeyEvent e) {
//                pacman.setDirection(e.getKeyCode());
//            }
//        });
//        setFocusable(true);
//    }
//
//    public static void main(String[] args) {
//        Test test = new Test();
//        Thread thread = new Thread(test);
//        thread.run();
//    }
//
//    @Override
//    public void run() {
//        while (pacman.isAlive()) {
//            switch (pacman.getDirection()) {
//                case KeyEvent.VK_LEFT:
//                    pacman.moveLeft();
//                    break;
//                case KeyEvent.VK_RIGHT:
//                    pacman.moveRight();
//                    break;
//                case KeyEvent.VK_UP:
//                    pacman.moveUp();
//                    break;
//                case KeyEvent.VK_DOWN:
//                    pacman.moveDown();
//                    break;
//            }
//            pacman.setBounds(pacman.getX(), pacman.getY(), 30, 30);
//            System.out.println(pacman.getPosition());
//            try {
//                Thread.sleep(70);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
//
