package game.data.blocks;

import game.data.Block;
import game.util.PairInt;
import javafx.animation.KeyFrame;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class EmptyBlock extends Block {
    public EmptyBlock(int x, int y) {
        super(x, y);
        texture = new ImageView("game/res/gameFieldSquare.png");
        texture.setFitWidth(36);
        texture.setFitHeight(36);
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
        return texture;
    }
}
