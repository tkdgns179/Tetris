package tetromino.shape;

import java.awt.image.BufferedImage;

public interface Shape {

    public BufferedImage getImage();

    public int getCol();

    public int getRow();

    public int[][] getCells();


}
