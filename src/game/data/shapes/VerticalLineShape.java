package game.data.shapes;

import game.util.PairInt;
import game.util.Shape;

public class VerticalLineShape extends Shape {
    private final int from,to;
    public VerticalLineShape(int x, int from, int to) {
        this(new PairInt(x,from + (to - from) >> 1),from,to);
    }

    public VerticalLineShape(PairInt center, int from, int to) {
        super(center);
        this.from = from;
        this.to = to;

        coordinates = new PairInt[to - from + 1];
        for(int i = from;i <= to; ++i){
            coordinates[i - from] = new PairInt(0,i - center.getY());
        }
    }

    @Override
    public int getLastStep() {
        return Math.max(center.getY() - from,to - center.getY());
    }

    public VerticalLineShape(int from, int to) {
        this(0,from,to);
    }

    public void setY(int y){
        setCenter(new PairInt(center.getX(),y));
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
