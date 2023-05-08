//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//
//public class Test extends JFrame implements KeyListener {
//
//    private int x = 0;
//    private int y = 0;
//    private int dx = 5;
//    private int dy = 5;
//    private Pacman pacman;
//
//    public Test() {
//        pacman = new Pacman();
//        setSize(400, 400);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
////        addKeyListener(this);
//        this.add(pacman);
//        pacman.addKeyListener(pacman);
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(50);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    move();
//                    repaint();
//                }
//            }
//        });
//        thread.start();
//    }
//
//    private void move() {
//        x += dx;
//        y += dy;
//        if (x > getWidth()) {
//            x = -50;
//        }
//    }
//
//    public void paint(Graphics g) {
//        super.paint(g);
//        g.setColor(Color.YELLOW);
//        g.fillArc(x, y, 50, 50, 30, 300);
//    }
//
//    public static void main(String[] args) {
//        new Test();
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        int key = e.getKeyCode();
//        if (key == KeyEvent.VK_LEFT) {
//            dx = -5;
//            dy = 0;
//        } else if (key == KeyEvent.VK_RIGHT) {
//            dx = 5;
//            dy = 0;
//        } else if (key == KeyEvent.VK_UP) {
//            dx = 0;
//            dy = -5;
//        } else if (key == KeyEvent.VK_DOWN) {
//            dx = 0;
//            dy = 5;
//        }
//    }
//
//    @Override
//    public void keyTyped(KeyEvent e) {}
//
//    @Override
//    public void keyReleased(KeyEvent e) {}
//}

import javax.swing.*;

//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//
//public class Test extends JPanel implements KeyListener {
//    private int pacmanMouthAngle = 45;
//    private boolean mouthClosing = false;
//    private int pacmanX = 10;
//    private int pacmanY = 10;
//    private int pacmanSpeed = 7;
//    private int pacmanDirection = KeyEvent.VK_RIGHT;
//
//    public Test() {
//        setBackground(Color.BLACK);
//        ImageIcon icon = new ImageIcon("resources/pacman/pacRIGHT.png");
//
//        addKeyListener(this);
//        setFocusable(true);
//    }
//
//    public void move(){
//
//    }
//
//    public void paintComponent(Graphics g1) {
//
//        super.paintComponent(g1);
//        // Draw Pacman
//        g1.setColor(Color.yellow);
//        g1.fillArc(pacmanX, pacmanY, 50, 50, pacmanMouthAngle, 360 - 2 * pacmanMouthAngle);
//
//        // Update Pacman's mouth angle
//        if (mouthClosing) {
//            pacmanMouthAngle -= 5;
//            if (pacmanMouthAngle <= 0) {
//                mouthClosing = false;
//            }
//        } else {
//            pacmanMouthAngle += 5;
//            if (pacmanMouthAngle >= 45) {
//                mouthClosing = true;
//            }
//        }
//
//        // Update Pacman's position
//        switch (pacmanDirection) {
//            case KeyEvent.VK_UP: {
//                pacmanY -= pacmanSpeed;
//                System.out.println("1");
//                break;
//            }
//            case KeyEvent.VK_DOWN: {
//                pacmanY += pacmanSpeed;
//                break;
//            }
//            case KeyEvent.VK_LEFT: {
//                pacmanX -= pacmanSpeed;
//                break;
//            }
//            case KeyEvent.VK_RIGHT: {
//                pacmanX += pacmanSpeed;
//                break;
//            }
//        }
//
//        // Wrap Pacman's position around the screen if necessary
//        if (pacmanX < -50) {
//            pacmanX = getWidth();
//        } else if (pacmanX > getWidth()) {
//            pacmanX = -50;
//        }
//        if (pacmanY < -50) {
//            pacmanY = getHeight();
//        } else if (pacmanY > getHeight()) {
//            pacmanY = -50;
//        }
//
//        try {
//            Thread.sleep(50);
//        } catch (InterruptedException ex) {
//            // do nothing
//        }
//        repaint();
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        int keyCode = e.getKeyCode();
//        switch (keyCode) {
//            case KeyEvent.VK_UP:
//                pacmanDirection = KeyEvent.VK_UP;
//                break;
//            case KeyEvent.VK_DOWN:
//                pacmanDirection = KeyEvent.VK_DOWN;
//                break;
//            case KeyEvent.VK_LEFT:
//                pacmanDirection = KeyEvent.VK_LEFT;
//                break;
//            case KeyEvent.VK_RIGHT:
//                pacmanDirection = KeyEvent.VK_RIGHT;
//                break;
//        }
//
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//    }
//
//    @Override
//    public void keyTyped(KeyEvent e) {
//    }
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Pacman Animation");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500, 500);
//        Test panel = new Test();
//        frame.add(panel);
//        frame.setVisible(true);
//    }
//}
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class Test {

    public static void main(String[] args) {

        new MyFrame();
    }
}

class MyFrame extends JFrame implements KeyListener {

    JLabel label;
    ImageIcon icon;
    int pacmanDirection;
    boolean alive;
    Thread thread;


    MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(null);
        this.addKeyListener(this);
        this.setLocationRelativeTo(null);

        pacmanDirection = KeyEvent.VK_RIGHT;
        icon = new ImageIcon("resources/pacman/pacmanRIGHT.png");
        alive = true;

        label = new JLabel();
        label.setBounds(0, 0, 20, 20);
        label.setIcon(icon);

        this.getContentPane().setBackground(Color.black);
        this.add(label);
        this.setVisible(true);

        thread = new Thread(() -> {

            while (alive) {
                movePacman();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        thread.start();
    }

    private void movePacman() {
        int x = label.getX();
        int y = label.getY();

        switch (pacmanDirection) {
            case KeyEvent.VK_UP:
                y -= 5;
                break;
            case KeyEvent.VK_DOWN:
                y += 5;
                break;
            case KeyEvent.VK_LEFT:
                x -= 5;
                break;
            case KeyEvent.VK_RIGHT:
                x += 5;
                break;
        }

        label.setLocation(x, y);
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
}
