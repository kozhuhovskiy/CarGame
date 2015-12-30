import javax.swing.*;
import java.awt.*;

/**
 * Created by Kirill on 30.12.2015.
 */
public class Enemy {


    int x;
    int y;
    int v;
    Image img = new ImageIcon(getClass().getClassLoader().getResource("res/300px-LadaNiva.png")).getImage();

    Road road;

    public Rectangle getRect(){
        return new Rectangle(x,y, 100,40);
    }

    public Enemy(int x, int y, int v, Road road) {
        this.x = x;
        this.y = y;
        this.v = v;
        this.road = road;
    }

    public void move(){
        x = x - road.p.v + v;
    }
}


