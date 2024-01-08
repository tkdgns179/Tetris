package tetromino.shape;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class IShape implements Shape {

    BufferedImage I;
    private int row = 4;
    private int col = 1;

    int cells[][] = {
        {1},
        {1},
        {1},
        {1}
    };

    public IShape() {
        initialize();
    }
    private void initialize() {
        try {
            I = ImageIO.read(getClass().getResourceAsStream("/I.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    public BufferedImage getImage() {
        return I;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int[][] getCells() {
        return cells;
    }


}
