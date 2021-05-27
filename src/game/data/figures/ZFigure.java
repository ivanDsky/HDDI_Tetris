package game.data.figures;

import game.data.Block;
import game.data.Figure;
import game.data.blocks.TempBlock;
import game.data.shapes.ZShapeHorizontal;
import game.data.shapes.ZShapeVertical;
import game.util.PairInt;
import game.util.Shape;

public class ZFigure extends Figure {

    protected ZFigure(PairInt center, Shape[] rotateArray, Block[] blocks) {
        super(center, rotateArray, blocks);
    }

    public ZFigure(PairInt center) {
        this(center,
            new Shape[]{
                new ZShapeHorizontal(),
                new ZShapeVertical(),
                new ZShapeHorizontal(),
                new ZShapeVertical()
            },
            new Block[]{
                new TempBlock(0, 0),
                new TempBlock(0, 0),
                new TempBlock(0, 0),
                new TempBlock(0, 0)
            });
    }
}
