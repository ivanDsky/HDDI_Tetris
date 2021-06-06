package game.data.blocks;

import game.data.Block;
import game.data.Field;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class DeleteBlock extends ColorBlock {
    private final StackPane stackPane;
    public DeleteBlock(int x, int y) {
        super(x, y);
        texture = new ImageView("game/res/back_block.png");
        texture.setFitWidth(35);
        texture.setFitHeight(35);
        ImageView blockedLevel = new ImageView("game/res/gameCubes.png");
        blockedLevel.setFitWidth(35);
        blockedLevel.setFitHeight(35);
        stackPane = new StackPane(texture, blockedLevel);
    }

    @Override
    public Block removeBlock(Field field) {
        return this;
    }

    @Override
    public Node getNode() {
        return stackPane;
    }
}
