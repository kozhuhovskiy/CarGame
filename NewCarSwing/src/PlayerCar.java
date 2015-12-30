import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Kirill on 30.12.2015.
 */
public class PlayerCar {

    public static final int MAX_V = 80;
    public static final int MAX_TOP = 25;
    public static final int MAX_BOTTOM = 420;



    Image img_c = new ImageIcon(getClass().getClassLoader().getResource("res/0000012.png")).getImage();
    Image img_l = new ImageIcon(getClass().getClassLoader().getResource("res/000001.png")).getImage();
    Image img_r = new ImageIcon(getClass().getClassLoader().getResource("res/0000013.png")).getImage();

    Image img = img_c;
    public Rectangle getRect(){
        return new Rectangle(x,y, 160,60);
    }

    int v = 0;
    int dv = 0;
    int s = 0;

    int x = 30;
    int y = 95;
    int dy = 0;

    int layer1 = 0;
    int layer2 = 1200;




    public void move() {
        s += v;
        v+= dv;

        if (v <= 0) v = 0;
        if (v >= MAX_V) v = MAX_V;

        y -= dy;
        if (y<= MAX_TOP) y = MAX_TOP;
        if (y>=MAX_BOTTOM) y = MAX_BOTTOM;


        if (layer2 - v <= 0) {
            layer1 = 0;
            layer2 = 1200;
        } else {
            layer1 -= v;
            layer2 -= v;


        }
    }

    public void keyPressed(KeyEvent e){
        int  key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT){
            dv = 3;

        }

        if (key == KeyEvent.VK_LEFT){
            dv = -3;

        }

        if (key == KeyEvent.VK_UP){

            dy = 7;
            img = img_l;

        }

        if (key == KeyEvent.VK_DOWN){
            dy = -7;
            img = img_r;
        }


    }
    public void keyReleased(KeyEvent e){
        int  key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT ){
            dv = 0;

        }

        if (key == KeyEvent.VK_UP  || key == KeyEvent.VK_DOWN){
            dy = 0;
            img = img_c;

        }


    }
}
