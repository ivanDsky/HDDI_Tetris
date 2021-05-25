package game.data;

import game.util.PairInt;
import javafx.scene.image.Image;

public abstract class Block {
    protected Image texture;
    protected PairInt xy;

    public int getX() {
        return xy.getX();
    }

    public void setX(int x) {
        this.xy.setX(x);
    }

    public int getY() {
        return xy.getY();
    }

    public void setY(int y) {
        this.xy.setY(y);
    }

    public void setXY(PairInt pair) {
        xy = pair;
    }

    public void setXY(int x, int y) {
        setX(x);
        setY(y);
    }

    public PairInt getXY() {
        return xy;
    }
}
