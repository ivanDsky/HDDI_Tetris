package game.data;

import game.util.PairInt;
import javafx.scene.image.Image;

public abstract class Block extends PairInt {
    protected Image texture;

    public Block(int x, int y) {
        super(x, y);
    }

    public void setXY(PairInt xy) {
        setXY(xy.getX(), xy.getY());
    }

    public PairInt getXY() {
        return this;
    }
}
