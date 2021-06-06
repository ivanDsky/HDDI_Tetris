package game.data.shapes;

import game.util.PairInt;
import game.util.Shape;

public class SmallHorizontal extends Shape {
    {
        coordinates = new PairInt[]{
                new PairInt(0, 0),
                new PairInt(-1, 0)
        };
    }

    public SmallHorizontal(PairInt center) {
        super(center);
    }

    public SmallHorizontal() {
        super();

    }
}
