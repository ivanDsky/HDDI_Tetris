package game.data.blocks;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class BlockedBlock extends ColorBlock{
    private ImageView blockedLevel = new ImageView("game/res/chain_for_block_64x64.png");
    private final StackPane stackPane;

    public BlockedBlock(int x, int y) {
        super(x, y, Color.RED);
        blockedLevel.setFitWidth(texture.getFitWidth());
        blockedLevel.setFitHeight(texture.getFitHeight());
        stackPane = new StackPane(texture,blockedLevel);

        stackPane.setOnMouseClicked(mouseEvent -> {
            stackPane.getChildren().remove(blockedLevel);
        });
    }

    @Override
    public Node getNode() {
        return stackPane;
    }
}
