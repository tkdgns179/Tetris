package tetromino;

import component.GamePanel;
import component.KeyHandler;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import tetromino.shape.RandomShape;
import tetromino.shape.Shape;

public class Tetromino extends Entity implements Runnable {

    private Thread thread;

    private Shape shape = RandomShape.getRandomShape();
    private KeyHandler keyHandler;
    private boolean leftCollision, rightCollision, downCollision;

    public Tetromino(KeyHandler keyHandler) {
        this.thread = new Thread(this);
        this.keyHandler = keyHandler;

        setDefaultValues();
        getTetrominoImage();
    }

    public void getTetrominoImage() {
        try {
            I = ImageIO.read(getClass().getResourceAsStream("/I.png"));
            J = ImageIO.read(getClass().getResourceAsStream("/J.png"));
            L = ImageIO.read(getClass().getResourceAsStream("/L.png"));
            O = ImageIO.read(getClass().getResourceAsStream("/O.png"));
            S = ImageIO.read(getClass().getResourceAsStream("/S.png"));
            T = ImageIO.read(getClass().getResourceAsStream("/T.png"));
            Z = ImageIO.read(getClass().getResourceAsStream("/Z.png"));
        } catch (IOException e) {

        }
    }

    public void setDefaultValues() {
        x = 0;
        y = 6;
        speed = 1;
        this.thread.start();
    }

    public void hasCollision(List<List<Integer>> cells, int row, int col) {

        int[][] shapeCells = shape.getCells();
        int shapeRow = shape.getRow();
        int shapeCol = shape.getCol();

        boolean leftCollision = false;
        boolean rightCollision = false;
        boolean downCollision = false;

        for (int i = 0; i < shapeRow; i++) {
            for (int j = 0; j < shapeCol; j++) {

                boolean isInCells = (((x + i) > 0) && ((x + i) < (row - 2))) && (((y + j) > 1) && ((y + j) < (col - 2)));
                boolean cellExist = shapeCells[i][j] == 1;
                boolean isLeftCorner = ((x + i) == (row - 1)) && ((y + j) == 0);
                boolean isRightCorner = ((x + i) == (row - 1)) && ((y + j) == (col -1));

                if (cellExist && isInCells) {
//                    System.out.printf("x = %d, col = %d", );
                    leftCollision |= (cells.get(x + i).get(y + j - 1) == 1);
                    rightCollision |= (cells.get(x + i).get(y + j + 1) == 1);
                    downCollision |= (cells.get(x + i + 1).get(y + j) == 1);
                }

                if (cellExist && ((x + i) == (row - 1)) && !isLeftCorner && !isRightCorner) {
                    downCollision |= true;
                    leftCollision |= (cells.get(x + i).get(y + j - 1) == 1);
                    rightCollision |= (cells.get(x + i).get(y + j + 1) == 1);
                }
                if (cellExist && ((y + j) == 0) && !isLeftCorner && !isRightCorner) {
                    leftCollision |= true;
                    rightCollision |= (cells.get(x + i).get(y + j + 1) == 1);
                    downCollision |= (cells.get(x + i + 1).get(y + j) == 1);
                }
                if (cellExist && ((y + j) == (col - 1)) && !isLeftCorner && !isRightCorner) {
                    rightCollision |= true;
                    leftCollision |= (cells.get(x + i).get(y + j - 1) == 1);
                    downCollision |= (cells.get(x + i + 1).get(y + j) == 1);
                }
                if (cellExist && isRightCorner) {
                    leftCollision |= (cells.get(x + i).get(y + j - 1) == 1);
                    rightCollision |= true;
                    downCollision |= true;
                }
                if (cellExist && isLeftCorner) {
                    leftCollision |= true;
                    rightCollision |= (cells.get(x + i).get(y + j + 1) == 1);
                    downCollision |= true;
                }
            }
        }

        this.leftCollision = leftCollision;
        this.rightCollision = rightCollision;
        this.downCollision = downCollision;
    }

    public void move() {
        if (keyHandler.downPressed && !downCollision) {
            x++;
        }
        if (keyHandler.leftPressed && !leftCollision) {
            y--;
        }
        if (keyHandler.rightPressed && !rightCollision) {
            y++;
        }

    }


    @Override
    public void run() {

        while (true) {
            try {
                System.out.printf("down collision = %b, right collision = %b, left collision = %b \n", downCollision, rightCollision, leftCollision);
                if (!downCollision) {
                    x++;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 스레드 종료
            }
        }

    }

    public void draw(Graphics2D graphics2D) {
        Shape shape = getShape();
        int[][] cells = shape.getCells();
        int row = shape.getRow();
        int col = shape.getCol();
        int blockSize = GamePanel.tileSize/2;

        for (int i = 0; i < row; i ++) {
            for (int j = 0; j < col; j++) {
                if (cells[i][j] == 1) {
                    graphics2D.drawImage(shape.getImage(), (y + j)*blockSize + 100, (x + i)*blockSize + 50, blockSize, blockSize, null);
                }

            }
        }
    }


    public Shape getShape() {
        return shape;
    }
    public void setShape(Shape shape) { this.shape = shape; }
    public boolean isLeftCollision() { return leftCollision; }
    public boolean isRightCollision() { return rightCollision; }
    public boolean isDownCollision() { return downCollision; }

}