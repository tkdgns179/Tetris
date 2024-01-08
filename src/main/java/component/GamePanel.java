package component;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import tetromino.CellArea;
import tetromino.Tetromino;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    private final static int originalTileSize = 16; // 16 * 16 tile
    private final static int scale = 3;
    public final static int tileSize = originalTileSize * scale; // 48x48 tile

    private final int maxScreenCol = 16;
    private final int maxScreenRow = 12;
    private final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    private final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    int FPS = 10;

    JFrame parent;
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    CellArea cellArea = new CellArea(keyHandler);


    public GamePanel(JFrame parent) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.setBackground(Color.black);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    private void update() {

        cellArea.update();

    }
    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        cellArea.draw(graphics2D);

        graphics2D.dispose();
    }
}
