import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

public class Road extends JPanel implements ActionListener, Runnable {

    Timer mainTimer = new Timer(20, this);

    Image img = new ImageIcon(getClass().getClassLoader().getResource("res/Road.jpg")).getImage();

    PlayerCar p = new PlayerCar();

    Thread enemiesFactory = new Thread(this);
    Thread audioThread = new Thread(new AudioThread());

    java.util.List<Enemy> enemyList = new ArrayList<Enemy>();

    public Road() {
        mainTimer.start();
        enemiesFactory.start();
        audioThread.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    @Override
    public void run() {
        while (true) {
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(2000));
                enemyList.add(new Enemy(1200, random.nextInt(600), random.nextInt(55), this));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        p.move();
        repaint();
        testCollisionWithEnemies();

        testWin();

    }

    private void testWin() {
        if(p.s > 55500 ){
            JOptionPane.showMessageDialog(null, "YOU WIN!!!");
            System.exit(0);

        }

    }

    private void testCollisionWithEnemies() {
        Iterator<Enemy> i = enemyList.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (p.getRect().intersects(e.getRect())) {
                JOptionPane.showMessageDialog(null, "YOU LOSE!!!!");
                System.exit(1);


            }
        }

    }

    private class MyKeyAdapter extends KeyAdapter {


        @Override
        public void keyPressed(KeyEvent e) {
            p.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            p.keyReleased(e);
        }
    }



    public void paint(Graphics g) {
        g = (Graphics2D) g;
        g.drawImage(img, p.layer1, 0, null);
        g.drawImage(img, p.layer2, 0, null);
        g.drawImage(p.img, p.x, p.y, null);

        double v = (200/PlayerCar.MAX_V) * p.v;
        g.setColor(Color.YELLOW);
        Font font = new Font("Arial", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("Speed: " + v + " km/h", 100, 30);



        Iterator<Enemy> i = enemyList.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (e.x >= 2400 || e.x <= -2400) {
                i.remove();
            } else {
                e.move();
                g.drawImage(e.img, e.x, e.y, null);

            }

        }



    }
}
