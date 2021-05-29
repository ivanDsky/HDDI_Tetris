package game.data.blocks;

import game.data.Block;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class ColorBlock extends Block {
    public ColorBlock(int x, int y, Color color) {
        super(x, y);
        rectangle.setFill(color);
    }

    public ColorBlock(int x, int y) {
        super(x, y);
        rectangle.setFill(Color.LIGHTBLUE);
    }

    @Override
    public Node getNode() {
        return rectangle;
    }
}
