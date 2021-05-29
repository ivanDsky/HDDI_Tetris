package game.data.shapes;

import game.util.PairInt;
import game.util.Shape;

public class HorizontalLineShape extends Shape {
    public HorizontalLineShape(int y, int from, int to) {
        super(new PairInt(0,y));
        coordinates = new PairInt[to - from + 1];
        for(int i = from;i <= to; ++i){
            coordinates[i - from] = new PairInt(i,0);
        }
    }

    public HorizontalLineShape(int from,int to) {
        this(0,from,to);
    }

    public void setY(int y){
        setCenter(new PairInt(0,y));
    }

    @Override
    public PairInt[] getWithStep(int step) {
        if(step == 0)return new PairInt[]{center};
        return new PairInt[]{
                PairInt.add(center,new PairInt(-step,0)),
                PairInt.add(center,new PairInt(step,0))
        };
    }
}
