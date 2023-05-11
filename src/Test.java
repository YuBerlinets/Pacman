//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class Test extends JPanel implements ActionListener {
//
//    private int pacX, pacY;
//    private Image pacImage;
//    private Timer timer;
//
//    public Test() {
//        pacX = 0;
//        pacY = 0;
//        pacImage = new ImageIcon("resources/pacman/pacmanRIGHT.png").getImage();
//        timer = new Timer(10, this);
//        timer.start();
//        setPreferredSize(new Dimension(800, 600));
//    }
//
//    public void actionPerformed(ActionEvent e) {
//        pacX += 1;
//        repaint();
//    }
//
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.drawImage(pacImage, pacX, pacY, null);
//    }
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("PacMan");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(new Test());
//        frame.pack();
//        frame.setVisible(true);
//    }
//}