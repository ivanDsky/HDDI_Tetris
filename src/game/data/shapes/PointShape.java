package game.data.shapes;

import game.util.PairInt;
import game.util.Shape;

public class PointShape extends Shape {
    {
        coordinates = new PairInt[]{
                new PairInt(0, 0)
        };
    }

    public PointShape(PairInt center) {
        super(center);
    }

    public PointShape() {
        super();

    }
}
