package game.util;

import java.util.Arrays;
import java.util.List;

public class RemoveShape {
    private final Shape shape;
    private int step;
    private final int lastStep;

    public RemoveShape(Shape shape,int startStep){
        this.shape = shape;
        this.lastStep = shape.getLastStep();
        step = startStep;
    }

    public List<PairInt> removeCoordinates(){
        if(step > lastStep)return null;
        return Arrays.asList(shape.getWithStep(step++));
    }
}
