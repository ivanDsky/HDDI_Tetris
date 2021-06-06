package game.data.shapes;

import game.util.PairInt;
import game.util.Shape;

public class ExplosionShape extends Shape {
    private final int radius;
    public ExplosionShape(PairInt center,int radius) {
        super(center);
        this.radius = radius;
        int temp = 2 * radius + 1;
        coordinates = new PairInt[(radius + 1) * (radius + 1) * 2 - temp];
        int id = 0;
        for(int i = -radius;i <= radius; ++i){
            int abs = Math.abs(i);
            int l = -radius + abs,r = radius - abs;
            for(int j = l; j <= r; ++j){
                coordinates[id++] = new PairInt(i,j);
            }
        }
    }

    @Override
    public int getLastStep() {
        return radius;
    }

    @Override
    public PairInt[] getWithStep(int step) {
        if(step == 0)return new PairInt[]{center};
        PairInt[] ret = new PairInt[(1 << (step + 1))];
        int id = 0;
        ret[id++] = PairInt.add(center,new PairInt(-step,0));
        for(int i = -step + 1; i < step; ++i){
            int abs = step - Math.abs(i);
            ret[id++] = PairInt.add(center,new PairInt(i,-abs));
            ret[id++] = PairInt.add(center,new PairInt(i,abs));
        }
        ret[id] = PairInt.add(center,new PairInt(-step,0));
        return ret;
    }
}
