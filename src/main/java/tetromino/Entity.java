package tetromino;
import java.awt.image.BufferedImage;

abstract public class Entity {

    public int x, y, speed;

    public BufferedImage I, J, L, O, S, T, Z;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
