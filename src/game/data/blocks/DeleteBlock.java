package game.data.blocks;

import game.data.Block;
import game.data.Field;
import javafx.scene.paint.Color;

public class DeleteBlock extends ColorBlock {
    public DeleteBlock(int x, int y) {
        super(x, y, Color.RED);
    }

    @Override
    public Block removeBlock(Field field) {
        return super.removeBlock(field);
    }
}
