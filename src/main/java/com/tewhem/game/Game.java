package com.tewhem.game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements KeyListener, ActionListener {

    Timer timer = new Timer(10, this);
    private long timeInGame = 0;
    private int firedBullet = 0;
    private BufferedImage spaceShipImage;
    private HashSet<Bullet> bulletList = new HashSet<>();
    private int bulletDirectionY = 5;
    private int ballX = 0;
    private int ballDirectionX = 2;
    private int spaceShipX = 0;
    private int spaceShipDirectionX = 20;
    private BufferedImage bulletImage;

    public Game() {

        try {
            spaceShipImage = ImageIO
                    .read(new FileImageInputStream(new File("SpaceShip.png")));
            bulletImage = ImageIO
                    .read(new FileImageInputStream(new File("Bullet.png")));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        setBackground(Color.BLACK);

        timer.start();

        timeInGame = Calendar.getInstance().getTime().getTime();

    }

    public boolean checkGameIsOver() {
        for (Bullet bullet : bulletList) {

            if (new Rectangle(bullet.getX(), bullet.getY(), 10, 20)
                    .intersects(new Rectangle(ballX, 0, 20, 20))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.red);

        g.fillOval(ballX, 0, 20, 20);

        g.drawImage(spaceShipImage, spaceShipX, 490,
                spaceShipImage.getWidth() / 10, spaceShipImage.getHeight() / 10,
                this);

        for (Iterator<Bullet> iterator = bulletList.iterator();
                iterator.hasNext();) {
            if (iterator.next().getY() <= 0) {
                iterator.remove();
            }
        }

        g.setColor(Color.orange);
        for (Bullet bullet : bulletList) {

            g.drawImage(bulletImage, bullet.getX(), bullet.getY(),
                    bulletImage.getWidth() / 20, bulletImage.getHeight() / 20,
                    this);
            g.drawImage(bulletImage, bullet.getX(), bullet.getY(),
                    bulletImage.getWidth() / 20, bulletImage.getHeight() / 20,
                    this);

        }

        if (checkGameIsOver()) {

            timer.stop();
            long endTime = Calendar.getInstance().getTime().getTime();

            String message = "You win....\n" + "Your time in game: "
                    + (endTime - timeInGame) / 1000. + "\n"
                    + "Fired bullet numbers: " + firedBullet;

            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Bullet bullet : bulletList) {

            bullet.setY(bullet.getY() - bulletDirectionY);
        }

        ballX += ballDirectionX;

        if (ballX >= 750) {

            ballDirectionX *= -1;
        }
        if (ballX <= 0) {

            ballDirectionX *= -1;
        }

        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();

        if (c == KeyEvent.VK_LEFT) {

            if (spaceShipX <= 0) {
                spaceShipX = 0;
            } else {
                spaceShipX -= spaceShipDirectionX;
            }

        } else if (c == KeyEvent.VK_RIGHT) {

            if (spaceShipX >= 712) {
                spaceShipX = 712;

            } else {
                spaceShipX += spaceShipDirectionX;
            }

        } else if (c == KeyEvent.VK_SPACE) {

            bulletList.add(new Bullet(spaceShipX + 6, 490));
            bulletList.add(new Bullet(spaceShipX + 47, 490));

            firedBullet += 2;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
