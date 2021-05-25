package game.util;

public class Shape {
    protected PairInt center;
    protected PairInt[] coordinates;

    public Shape(PairInt[] coordinates, PairInt center){
        this.coordinates = coordinates;
        this.center = center;
    }

    public Shape(PairInt[] coordinates){
        this(coordinates,new PairInt(0,0));
    }

    public PairInt[] getCoordinates(){
        PairInt[] ret = new PairInt[coordinates.length];
        for(int i = 0;i < ret.length; ++i){
            ret[i] = PairInt.add(coordinates[i],center);
        }
        return ret;
    }

    public PairInt getCenter() {
        return center;
    }

    public void setCenter(PairInt center) {
        this.center = center;
    }
}
