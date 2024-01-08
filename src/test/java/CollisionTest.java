import component.KeyHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import tetromino.Tetromino;
import tetromino.shape.LShape;

public class CollisionTest {

    public static void main(String[] args) {

        List<List<Integer>> cells = new ArrayList<>();

        cells.add(Arrays.asList(0, 0, 0, 0, 0));
        cells.add(Arrays.asList(0, 1, 0, 0, 0));
        cells.add(Arrays.asList(0, 0, 0, 0, 0));
        cells.add(Arrays.asList(0, 0, 0, 0, 0));
        cells.add(Arrays.asList(0, 1, 0, 0, 0));
        cells.add(Arrays.asList(0, 1, 0, 0, 0));
        cells.add(Arrays.asList(1, 1, 1, 1, 1));

        Tetromino tetromino = new Tetromino(new KeyHandler());
        tetromino.setX(1);
        tetromino.setY(0);
        tetromino.setShape(new LShape());
        long before = System.currentTimeMillis();
        tetromino.hasCollision(cells, 7, 5);
        long after = System.currentTimeMillis();

        System.out.printf("충돌여부 좌 = %b, 우 = %b, 하 = %b \n", tetromino.isLeftCollision(),
            tetromino.isRightCollision(), tetromino.isDownCollision());

        System.out.println("소요시간 : " + (after - before));
    }

}
