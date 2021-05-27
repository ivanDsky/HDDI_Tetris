package game.data.shapes;

import game.util.PairInt;
import game.util.Shape;

public class HorizontalLineShape extends Shape {
    public HorizontalLineShape(PairInt center, int from, int to) {
        super(center);
        coordinates = new PairInt[to - from + 1];
        for(int i = from;i <= to; ++i){
            coordinates[i - from] = new PairInt(i,0);
        }
    }

    public HorizontalLineShape(int from,int to) {
        this(new PairInt(0,0),from,to);
    }
}
