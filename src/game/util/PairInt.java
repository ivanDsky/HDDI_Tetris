package game.util;

public class PairInt {
    private int x,y;

    public PairInt(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXY(int x,int y){
        setX(x);setY(y);
    }

    public void addXY(PairInt add){
        this.setXY(x + add.x,y + add.y);
    }

    public static PairInt add(PairInt a,PairInt b){
        return new PairInt(a.x + b.x,a.y + b.y);
    }
}
