package game.data.shapes;

import game.util.PairInt;
import game.util.Shape;

public class IShapeVertical extends Shape {
    {
        coordinates = new PairInt[]{
                new PairInt(0,-1),
                new PairInt(0,0),
                new PairInt(0,1),
                new PairInt(0,2)
        };
    }

    public IShapeVertical(PairInt center) {
        super(center);
    }

    public IShapeVertical() {
        super();
    }
}
