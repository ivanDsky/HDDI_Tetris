package game.data.figures;

import game.data.Block;
import game.data.Figure;
import game.data.blocks.TempBlock;
import game.data.shapes.SShapeHorizontal;
import game.data.shapes.SShapeVertical;
import game.util.PairInt;
import game.util.Shape;

public class SFigure extends Figure {
    protected SFigure(PairInt center, Shape[] rotateArray, Block[] blocks) {
        super(center, rotateArray, blocks);
    }

    public SFigure(PairInt center) {
        this(center,
                new Shape[]{
                        new SShapeHorizontal(),
                        new SShapeVertical(),
                        new SShapeHorizontal(),
                        new SShapeVertical()
                },
                new Block[]{
                        new TempBlock(0, 0),
                        new TempBlock(0, 0),
                        new TempBlock(0, 0),
                        new TempBlock(0, 0)
                });
    }
}
