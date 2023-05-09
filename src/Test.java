import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

class Test extends JFrame implements KeyListener {

    JLabel pacmanLabel;
    ImageIcon pacUP, pacDOWN, pacLEFT, pacRIGHT;
    int pacmanDirection;
    boolean alive;
    Thread thread;
    int speed;


    Test() {
        //default settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(null);
        this.addKeyListener(this);
        this.setLocationRelativeTo(null);
        renderImages();
        speed = 6;
        pacmanDirection = KeyEvent.VK_RIGHT;
        alive = true;

        pacmanLabel = new JLabel();
        pacmanLabel.setBounds(0, 0, 20, 20);
        pacmanLabel.setIcon(pacRIGHT);

        this.getContentPane().setBackground(Color.black);
        this.add(pacmanLabel);
        this.setVisible(true);

        thread = new Thread(() -> {
            while (alive) {
                try {
                    movePacman();
                    Thread.sleep(70);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void renderImages() {
        pacRIGHT = new ImageIcon("resources/pacman/pacmanRIGHT.png");
        pacLEFT = new ImageIcon("resources/pacman/pacmanLEFT.png");
        pacUP = new ImageIcon("resources/pacman/pacmanUP.png");
        pacDOWN = new ImageIcon("resources/pacman/pacmanDOWN.png");
    }

    private void movePacman() {
        int x = pacmanLabel.getX();
        int y = pacmanLabel.getY();

        switch (pacmanDirection) {
            case KeyEvent.VK_UP:
                y -= speed;
                pacmanLabel.setIcon(pacUP);
                break;
            case KeyEvent.VK_DOWN:
                y += speed;
                pacmanLabel.setIcon(pacDOWN);
                break;
            case KeyEvent.VK_LEFT:
                x -= speed;
                pacmanLabel.setIcon(pacLEFT);
                break;
            case KeyEvent.VK_RIGHT:
                x += speed;
                pacmanLabel.setIcon(pacRIGHT);
                break;
        }

        pacmanLabel.setLocation(x, y);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                pacmanDirection = keyCode;
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public static void main(String[] args) {
        new Test();
    }
}
