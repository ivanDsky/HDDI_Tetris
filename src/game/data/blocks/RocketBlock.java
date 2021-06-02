package game.data.blocks;

import game.data.Block;
import game.data.Field;
import game.data.shapes.VerticalLineShape;
import game.util.PairInt;
import game.util.Shape;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class RocketBlock extends Block {
    public RocketBlock(int x, int y) {
        super(x, y);
        texture = new ImageView("game/res/back_block_rocket.png");
        texture.setFitWidth(47);
        texture.setFitHeight(47);
    }

    public RocketBlock(PairInt xy) {
        this(xy.getX(),xy.getY());
    }

    @Override
    public Block removeBlock(Field field) {
        Shape shape = new VerticalLineShape(getX(),0,Field.FIELD_HEIGHT - 1);
        field.removeShape(shape);
        return null;
    }

    @Override
    public Node getNode() {
        return texture;
    }
}
