package game.data.blocks;

import game.data.Block;
import game.data.Field;
import game.util.PairInt;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class BlockedBlock extends Block{
    private StackPane stackPane;
    private Block toBlock;

    private BlockedBlock(int x, int y) {
        super(x, y);
    }

    public BlockedBlock(int x, int y, Block toBlock) {
        this(x,y);
        this.toBlock = toBlock;
        ImageView blockedLevel = new ImageView("game/res/chain_for_block_64x64.png");
        blockedLevel.setFitWidth(50);
        blockedLevel.setFitHeight(50);
        stackPane = new StackPane(toBlock.getNode(), blockedLevel);
    }

    @Override
    public Block removeBlock(Field field) {
        return toBlock;
    }

    @Override
    public void setXY(PairInt xy) {
        toBlock.setXY(xy);
    }

    @Override
    public PairInt getXY() {
        return toBlock.getXY();
    }

    @Override
    public Timeline animation() {
        return toBlock.animation();
    }

    @Override
    public Node getNode() {
        return stackPane;
    }
}
