package game.data.shapes;

import game.util.PairInt;
import game.util.Shape;

public class SmallVertical extends Shape {
    {
        coordinates = new PairInt[]{
                new PairInt(0, 0),
                new PairInt(0, -1)
        };
    }

    public SmallVertical(PairInt center) {
        super(center);
    }

    public SmallVertical() {
        super();

    }
}
