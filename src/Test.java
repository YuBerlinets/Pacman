import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Test extends JFrame implements Runnable {
    private Pacman pacman;
    private JLabel pacmanLabel;

    public Test() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        pacman = new Pacman(50, 50, new Board(22, 22));
        pacmanLabel = new JLabel(pacman.getCurrentPac());
        add(pacmanLabel);
        pacmanLabel.setBounds(pacman.getX(), pacman.getY(), 30, 30);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                pacman.setDirection(e.getKeyCode());
            }
        });
        setFocusable(true);
    }

    public static void main(String[] args) {
        Test test = new Test();
        Thread thread = new Thread(test);
        thread.start();
    }

    @Override
    public void run() {
        while (pacman.isAlive()) {
            switch (pacman.getDirection()) {
                case KeyEvent.VK_LEFT:
                    pacman.moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    pacman.moveRight();
                    break;
                case KeyEvent.VK_UP:
                    pacman.moveUp();
                    break;
                case KeyEvent.VK_DOWN:
                    pacman.moveDown();
                    break;
            }
            pacmanLabel.setBounds(pacman.getX(), pacman.getY(), 30, 30);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
