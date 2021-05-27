package game.data.shapes;

import game.util.PairInt;
import game.util.Shape;

public class TShape90Degree extends Shape {
    {
        coordinates = new PairInt[]{
                new PairInt(1, 0),
                new PairInt(0, -1),
                new PairInt(0, 0),
                new PairInt(0, 1)
        };
    }

    public TShape90Degree(PairInt center) {
        super(center);
    }

    public TShape90Degree() {
        super();
    }
}
