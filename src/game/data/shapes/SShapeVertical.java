package game.data.shapes;

import game.util.PairInt;
import game.util.Shape;

public class SShapeVertical extends Shape {
    {
        coordinates = new PairInt[]{
                new PairInt(-1, -1),
                new PairInt(-1, 0),
                new PairInt(0, 0),
                new PairInt(0, 1)
        };
    }

    public SShapeVertical(PairInt center) {
        super(center);
    }

    public SShapeVertical() {
        super();
    }

}
