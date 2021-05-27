package game.data.shapes;

import game.util.PairInt;
import game.util.Shape;

public class TShape0Degree extends Shape {
    {
        coordinates = new PairInt[]{
                new PairInt(1, 0),
                new PairInt(0, 0),
                new PairInt(-1, 0),
                new PairInt(0, -1)
        };
    }

    public TShape0Degree(PairInt center) {
        super(center);
    }

    public TShape0Degree() {
        super();
    }
}
