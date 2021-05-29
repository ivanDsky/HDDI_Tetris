package game.data.blocks;

import game.data.Block;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class EmptyBlock extends Block {
    public EmptyBlock(int x, int y) {
        super(x, y);
        rectangle.setFill(Color.TRANSPARENT);
    }

    @Override
    public Node getNode() {
        return rectangle;
    }
}
