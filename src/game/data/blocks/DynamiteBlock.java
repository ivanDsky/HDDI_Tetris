package game.data.blocks;

import game.data.Block;
import game.data.Field;
import game.data.shapes.ExplosionShape;
import game.util.PairInt;
import game.util.Shape;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class DynamiteBlock extends Block {
    public DynamiteBlock(int x, int y) {
        super(x, y);
        texture = new ImageView("game/res/back_block_dynamite.png");
        texture.setFitWidth(33);
        texture.setFitHeight(33);
    }

    public DynamiteBlock(PairInt xy) {
        this(xy.getX(),xy.getY());
    }

    @Override
    public Block removeBlock(Field field) {
        Shape shape = new ExplosionShape(getXY(),2);
        field.removeShape(shape,1);
        return this;
    }

    @Override
    public Node getNode() {
        return texture;
    }
}