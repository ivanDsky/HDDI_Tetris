package game.data.figures;

import game.data.Block;
import game.data.Figure;
import game.data.blocks.TempBlock;
import game.data.shapes.IShapeHorizontal;
import game.data.shapes.IShapeVertical;
import game.util.PairInt;
import game.util.Shape;

public class IFigure extends Figure {
    protected IFigure(PairInt center, Shape[] rotateArray, Block[] blocks) {
        super(center, rotateArray, blocks);
    }

    public IFigure(PairInt center) {
        this(center,
                new Shape[]{
                        new IShapeHorizontal(),
                        new IShapeVertical(),
                        new IShapeHorizontal(),
                        new IShapeVertical()
                },
                new Block[]{
                        new TempBlock(0, 0),
                        new TempBlock(0, 0),
                        new TempBlock(0, 0),
                        new TempBlock(0, 0)
                });

    }
}
