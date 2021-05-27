package game.data;

import game.util.PairInt;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class Block extends PairInt {
    protected Image texture;
    protected Rectangle temp = new Rectangle(100,100);

    public Block(int x, int y) {
        super(x, y);
    }

    public void setXY(PairInt xy) {
        setXY(xy.getX(), xy.getY());
    }

    public PairInt getXY() {
        return this;
    }

    public Rectangle getRect(){
        return temp;
    }
}
