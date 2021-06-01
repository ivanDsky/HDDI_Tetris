package game.data.blocks;

import game.data.Block;
import game.data.Field;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class BlockedBlock extends ColorBlock{
    private final ImageView blockedLevel = new ImageView("game/res/chain_for_block_64x64.png");
    private final StackPane stackPane;

    public BlockedBlock(int x, int y) {
        super(x, y, Color.RED);
        blockedLevel.setFitWidth(texture.getFitWidth());
        blockedLevel.setFitHeight(texture.getFitHeight());
        isBlocked = true;
        stackPane = new StackPane(texture,blockedLevel);
    }

    private boolean isBlocked;

    @Override
    public Block removeBlock(Field field) {
        if(isBlocked){
            isBlocked = false;
            stackPane.getChildren().remove(blockedLevel);
            return this;
        }else{
            return null;
        }
    }

    @Override
    public Node getNode() {
        return stackPane;
    }
}
