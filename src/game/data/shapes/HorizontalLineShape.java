package game.data.shapes;

import game.util.PairInt;
import game.util.Shape;

public class HorizontalLineShape extends Shape {
    private final int from,to;
    public HorizontalLineShape(int y, int from, int to) {
        this(new PairInt(from + (to - from) >> 1,y),from,to);
    }

    public HorizontalLineShape(PairInt center, int from, int to) {
        super(center);
        this.from = from;
        this.to = to;
        coordinates = new PairInt[to - from + 1];
        for(int i = from;i <= to; ++i){
            coordinates[i - from] = new PairInt(i - center.getX(),0);
        }
    }

    @Override
    public int getLastStep() {
        return Math.max(center.getX() - from,to - center.getX());
    }

    public HorizontalLineShape(int from, int to) {
        this(0,from,to);
    }

    public void setY(int y){
        setCenter(new PairInt(center.getX(),y));
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
