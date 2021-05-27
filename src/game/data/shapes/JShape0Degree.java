package game.data.shapes;

import game.util.PairInt;
import game.util.Shape;

public class JShape0Degree extends Shape {
    {
        coordinates = new PairInt[]{
                new PairInt(0, -1),
                new PairInt(0, 0),
                new PairInt(0, 1),
                new PairInt(-1, 1)
        };
    }

    public JShape0Degree(PairInt center) {
        super(center);
    }

    public JShape0Degree() {
        super();
    }
}
