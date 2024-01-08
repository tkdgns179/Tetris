package tetromino.shape;

import java.util.Random;

public class RandomShape {

    private final static Shape I = new IShape();
    private final static Shape L = new LShape();

    public static Shape getRandomShape() {

        Random random = new Random();
        int selectNum = random.nextInt(2) + 1;
        switch (selectNum) {
            case 1:
                return I;
            case 2:
                return L;
        }

        return I;
    }


}
