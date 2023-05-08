////import java.awt.*;
////import java.awt.event.*;
////import javax.swing.*;
////
////public class Test extends JFrame implements KeyListener {
////
////    private int x = 0;
////    private int y = 0;
////    private int dx = 5;
////    private int dy = 5;
////    private Pacman pacman;
////
////    public Test() {
////        pacman = new Pacman();
////        setSize(400, 400);
////        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        setVisible(true);
//////        addKeyListener(this);
////        this.add(pacman);
////        pacman.addKeyListener(pacman);
////        Thread thread = new Thread(new Runnable() {
////            @Override
////            public void run() {
////                while (true) {
////                    try {
////                        Thread.sleep(50);
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
////                    move();
////                    repaint();
////                }
////            }
////        });
////        thread.start();
////    }
////
////    private void move() {
////        x += dx;
////        y += dy;
////        if (x > getWidth()) {
////            x = -50;
////        }
////    }
////
////    public void paint(Graphics g) {
////        super.paint(g);
////        g.setColor(Color.YELLOW);
////        g.fillArc(x, y, 50, 50, 30, 300);
////    }
////
////    public static void main(String[] args) {
////        new Test();
////    }
////
////    @Override
////    public void keyPressed(KeyEvent e) {
////        int key = e.getKeyCode();
////        if (key == KeyEvent.VK_LEFT) {
////            dx = -5;
////            dy = 0;
////        } else if (key == KeyEvent.VK_RIGHT) {
////            dx = 5;
////            dy = 0;
////        } else if (key == KeyEvent.VK_UP) {
////            dx = 0;
////            dy = -5;
////        } else if (key == KeyEvent.VK_DOWN) {
////            dx = 0;
////            dy = 5;
////        }
////    }
////
////    @Override
////    public void keyTyped(KeyEvent e) {}
////
////    @Override
////    public void keyReleased(KeyEvent e) {}
////}
//
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
//
import javax.swing.*;
import java.awt.*;

public class Test extends JFrame {
    private JPanel mainGame;
    private JLabel currentScore;
    private JPanel bottomPanel;
    private JPanel livesPanel;
    private JPanel foodPanel;
    private int score;
    private Pacman pacman;
    private Board board;
    private Ghost ghost1, ghost2, ghost3;
    private Image pacmanTest;

    Test() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //main game appearance
        mainGame = new JPanel();
        mainGame.setLayout(new BorderLayout());
        currentScore = new JLabel("Score: " + score);
        currentScore.setFont(new Font("SansSerif", Font.BOLD, 22));
        currentScore.setForeground(Color.YELLOW);

        //adding bottom panel
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));

        //panel for button to comeback to menu
        JPanel buttonBottomPanel = new JPanel();
        buttonBottomPanel.setLayout(new GridBagLayout());
        buttonBottomPanel.setBackground(Color.BLACK);
        buttonBottomPanel.setPreferredSize(new Dimension(720, 45));

        //button for returning to the menu
        JButton backButton = new MenuButton("Back to Menu");
        backButton.setPreferredSize(new Dimension(180, 35));
        backButton.addActionListener(event -> {
            CardLayout cardLayoutGamePanel = (CardLayout) getParent().getLayout();
            cardLayoutGamePanel.show(getParent(), "menuPanel");

        });
        buttonBottomPanel.add(backButton);

        //adding lives panel
        livesPanel = new JPanel();
        livesPanel.setLayout(new BoxLayout(livesPanel, BoxLayout.X_AXIS));
        livesPanel.setBackground(Color.BLACK);
        JLabel pacmanLive1 = new JLabel(new ImageIcon("resources/pacmanLife.png"));
        JLabel pacmanLive2 = new JLabel(new ImageIcon("resources/pacmanLife.png"));
        JLabel pacmanLive3 = new JLabel(new ImageIcon("resources/pacmanLife.png"));
        livesPanel.add(Box.createHorizontalStrut(10));
        livesPanel.add(pacmanLive1);
        livesPanel.add(Box.createHorizontalStrut(10));
        livesPanel.add(pacmanLive2);
        livesPanel.add(Box.createHorizontalStrut(10));
        livesPanel.add(pacmanLive3);
        foodPanel = new JPanel();
        foodPanel.setBackground(Color.BLACK);
        foodPanel.setLayout(new GridLayout(1, 1));
        JLabel cherry = new JLabel(new ImageIcon("resources/food/cherry.png"));
        foodPanel.add(cherry);

        bottomPanel.add(livesPanel);
        bottomPanel.add(foodPanel);
        bottomPanel.add(buttonBottomPanel);

        mainGame.add(bottomPanel, BorderLayout.SOUTH);
        mainGame.add(currentScore, BorderLayout.NORTH);

        mainGame.setBackground(Color.BLACK);
        this.add(mainGame);
    }

    public static void main(String[] args) {
        new Test();
    }
}

