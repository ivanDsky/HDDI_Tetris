package game.data.figures;

import game.data.Block;
import game.data.Figure;
import game.data.blocks.ColorBlock;
import game.data.shapes.SmallHorizontal;
import game.data.shapes.SmallVertical;
import game.util.PairInt;
import game.util.Shape;
import javafx.scene.paint.Color;

public class BonusBigFigure extends Figure {
    protected BonusBigFigure(PairInt center, Shape[] rotateArray, Block[] blocks) {
        super(center, rotateArray, blocks);
    }

    public BonusBigFigure(PairInt center) {
        this(center,
                new Shape[]{
                        new SmallHorizontal(),
                        new SmallVertical(),
                        new SmallHorizontal(),
                        new SmallVertical()
                },
                new Block[]{
                        new ColorBlock(0, 0, Color.YELLOW),
                        new ColorBlock(0, 0, Color.YELLOW),
                });

    }
}
