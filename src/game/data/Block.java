package game.data;

import game.util.PairInt;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Block extends PairInt {
    protected ImageView texture;
    protected Rectangle rectangle = new Rectangle(50,50);

    public Block(int x, int y) {
        super(x, y);
        rectangle.setStroke(Color.BLACK);
    }

    public void setXY(PairInt xy) {
        setXY(xy.getX(), xy.getY());
    }

    public PairInt getXY() {
        return this;
    }

    public abstract Node getNode();
}
