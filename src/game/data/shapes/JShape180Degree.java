package game.data.shapes;

import game.util.PairInt;
import game.util.Shape;

public class JShape180Degree extends Shape {
    {
        coordinates = new PairInt[]{
                new PairInt(1, -1),
                new PairInt(0, -1),
                new PairInt(0, 0),
                new PairInt(0, 1)
        };
    }

    public JShape180Degree(PairInt center) {
        super(center);
    }

    public JShape180Degree() {
        super();
    }
}
