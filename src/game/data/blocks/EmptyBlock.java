package game.data.blocks;

import game.data.Block;
import game.util.PairInt;
import javafx.animation.KeyFrame;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class EmptyBlock extends Block {
    public EmptyBlock(int x, int y) {
        super(x, y);
        rectangle.setFill(Color.TRANSPARENT);
    }

    public EmptyBlock(PairInt xy) {
        this(xy.getX(),xy.getY());
    }

    @Override
    public KeyFrame animation() {
        return null;
    }

    @Override
    public Node getNode() {
        return rectangle;
    }
}
