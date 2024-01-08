package component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import tetromino.CellArea;

public class StartPanel extends JPanel {

    final int originalTileSize = 16; // 16 * 16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 tile

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    JPanel current = this;
    JFrame parent;

    KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_X) {
                System.out.println("clicked X");
                if (parent instanceof JFrame) {
                    parent.remove(current);
                    GamePanel gamePanel = new GamePanel(parent);
                    parent.add(gamePanel);
                    parent.pack();
                    gamePanel.startGameThread();
                }
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };

    public StartPanel(JFrame parent) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyListener);
        this.setFocusable(true);
        this.parent = parent;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/background.png"));
            g.drawImage(image, 0, 0, screenWidth, screenHeight, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


