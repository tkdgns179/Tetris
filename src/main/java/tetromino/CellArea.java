package tetromino;

import component.GamePanel;
import component.KeyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import tetromino.shape.Shape;

public class CellArea {

    private final int x = 20, y = 12;
    private List<List<Integer>> gameArea = new ArrayList<>();
    private BufferedImage blank;
    private KeyHandler keyHandler;
    private Tetromino tetromino;
    
    public CellArea(KeyHandler keyHandler) {
        initalize();
        this.keyHandler = keyHandler;
        this.tetromino = new Tetromino(keyHandler);

    }

    private void initalize() {

        for (int i = 0; i < x; i++) {
            gameArea.add(new ArrayList<>());
            for (int j = 0; j < y; j++) {
                gameArea.get(i).add(0);
            }
        }

        try {
            blank = ImageIO.read(getClass().getResourceAsStream("/blank.png"));
        } catch (IOException e) {

        }


    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage shapeImage = tetromino.getShape().getImage();
        int blockSize = GamePanel.tileSize/2;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (gameArea.get(i).get(j) == 0) {
                    graphics2D.drawImage(blank, j*blockSize + 100, i*blockSize + 50, blockSize, blockSize, null);
                } else {
                    graphics2D.drawImage(shapeImage, j*blockSize + 100, i*blockSize + 50, blockSize, blockSize, null);
                }
            }
        }

        tetromino.hasCollision(gameArea, x, y);
        tetromino.draw(graphics2D);
    }

    public void findFullLine(List<List<Integer>> gameArea) {
        for (int i = y - 1; i >= 0; i++) {
            boolean isFull = gameArea.get(y).stream().allMatch(integer -> integer == 1);
            if (isFull) {
                gameArea.remove(y);
                // 점수로직
            }
        }
    }

    public void update() {
        tetromino.move();
    }

}
