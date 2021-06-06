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
        Shape shape = new VerticalLineShape(getXY(),0,field.height - 1);
        field.removeShape(shape,1);
        return this;
    }

    @Override
    public Node getNode() {
        return texture;
    }
}
