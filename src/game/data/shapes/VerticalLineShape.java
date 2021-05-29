package game.data.shapes;

import game.util.PairInt;
import game.util.Shape;

public class VerticalLineShape extends Shape {
    public VerticalLineShape(int x, int from, int to) {
        super(new PairInt(x,0));
        coordinates = new PairInt[to - from + 1];
        for(int i = from;i <= to; ++i){
            coordinates[i - from] = new PairInt(0,i);
        }
    }

    public VerticalLineShape(int from,int to) {
        this(0,from,to);
    }

    @Override
    public PairInt[] getWithStep(int step) {
        if(step == 0)return new PairInt[]{center};
        return new PairInt[]{
                PairInt.add(center,new PairInt(0,-step)),
                PairInt.add(center,new PairInt(0,step))
        };
    }
}
