import component.GamePanel;
import component.StartPanel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;

public class Main {


    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("TETRIS");

        StartPanel startPanel = new StartPanel(window);
        window.add(startPanel);
        window.pack();


        window.setLocationRelativeTo(null);
        window.setVisible(true);


    }

}
