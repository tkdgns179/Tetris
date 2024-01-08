package tetromino.shape;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.function.BiFunction;
import javax.imageio.ImageIO;

public class LShape implements Shape {

    BufferedImage L;
    private int row = 3;
    private int col = 2;

    int cells[][] = {
        {1, 0},
        {1, 0},
        {1, 1}
    };

    public LShape() {
        initialize();
    }

    private void initialize() {
        try {
            L = ImageIO.read(getClass().getResourceAsStream("/L.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    @Override
    public BufferedImage getImage() {
        return L;
    }

    @Override
    public int getCol() {
        return col;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int[][] getCells() {
        return cells;
    }
}
