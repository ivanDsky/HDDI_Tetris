package game.data.figures;

import game.data.Block;
import game.data.Figure;
import game.data.blocks.TempBlock;
import game.data.shapes.OShape;
import game.util.PairInt;
import game.util.Shape;

public class OFigure extends Figure {
    protected OFigure(PairInt center, Shape[] rotateArray, Block[] blocks) {
        super(center, rotateArray, blocks);
    }

    public OFigure(PairInt center) {
        this(center,
                new Shape[]{
                        new OShape(),
                        new OShape(),
                        new OShape(),
                        new OShape()
                },
                new Block[]{
                        new TempBlock(0, 0),
                        new TempBlock(0, 0),
                        new TempBlock(0, 0),
                        new TempBlock(0, 0)
                });

    }
}
