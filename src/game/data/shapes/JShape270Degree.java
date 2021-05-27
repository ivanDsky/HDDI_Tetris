package game.data.shapes;

import game.util.PairInt;
import game.util.Shape;

public class JShape270Degree extends Shape {
    {
        coordinates = new PairInt[]{
                new PairInt(-1, 0),
                new PairInt(0, 0),
                new PairInt(1, 0),
                new PairInt(1, 1)
        };
    }

    public JShape270Degree(PairInt center) {
        super(center);
    }

    public JShape270Degree() {
        super();
    }
}
