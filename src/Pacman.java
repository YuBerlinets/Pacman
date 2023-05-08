import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pacman extends JLabel implements KeyListener {
    private ImageIcon pacRIGHT, pacLEFT, pacUP, pacDOWN;
    private int x;
    private int y;
    private int direction;
    private int speed;
    private boolean stuck;
    private boolean alive;

    public Pacman() {
        this.x = 1;
        this.y = 1;
        this.speed = 5;
        this.stuck = false;
        this.alive = true;
        initImages();
    }

    public void move() {
        switch (direction) {
            case KeyEvent.VK_UP: {
                y -= speed;
                break;
            }
            case KeyEvent.VK_DOWN: {
                y += speed;
                break;
            }
            case KeyEvent.VK_LEFT: {
                x -= speed;
                break;
            }
            case KeyEvent.VK_RIGHT: {
                x += speed;
                break;
            }
        }
    }

    public void initImages() {
        this.pacRIGHT = new ImageIcon("resources/pacman/pacmanRIGHT.png");
        this.pacLEFT = new ImageIcon("resources/pacman/pacmanLEFT.png");
        this.pacLEFT = new ImageIcon("resources/pacman/pacmanLEFT.png");
        this.pacUP = new ImageIcon("resources/pacman/pacmanUP.png");
        this.pacDOWN = new ImageIcon("resources/pacman/pacmanDOWN.png");
    }

    public void pacmanDeath() {
        this.alive = false;
    }

    public ImageIcon getPacRIGHT() {
        return pacRIGHT;
    }

    public ImageIcon getPacLEFT() {
        return pacLEFT;
    }

    public ImageIcon getPacUP() {
        return pacUP;
    }

    public ImageIcon getPacDOWN() {
        return pacDOWN;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }


    public boolean isStuck() {
        return stuck;
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:{
                direction = KeyEvent.VK_UP;
                System.out.println(1);
                break;
            }
            case KeyEvent.VK_DOWN:
                direction = KeyEvent.VK_DOWN;
                System.out.println(2);
                break;
            case KeyEvent.VK_LEFT:
                direction = KeyEvent.VK_LEFT;
                System.out.println(3);
                break;
            case KeyEvent.VK_RIGHT:
                direction = KeyEvent.VK_RIGHT;
                System.out.println(4);
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}