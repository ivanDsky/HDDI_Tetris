package game.data.figures;

import game.data.Block;
import game.data.Figure;
import game.data.blocks.ColorBlock;
import game.data.shapes.PointShape;
import game.util.PairInt;
import game.util.Shape;
import javafx.scene.paint.Color;

public class BonusFigure extends Figure {
    protected BonusFigure(PairInt center, Shape[] rotateArray, Block[] blocks) {
        super(center, rotateArray, blocks);
    }

    public BonusFigure(PairInt center) {
        this(center,
                new Shape[]{
                        new PointShape(),
                        new PointShape(),
                        new PointShape(),
                        new PointShape()
                },
                new Block[]{
                        new ColorBlock(0, 0, Color.YELLOW),
                });

    }
}
