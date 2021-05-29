package game.data.figures;

import game.data.Block;
import game.data.Figure;
import game.data.blocks.ColorBlock;
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
                        new ColorBlock(0, 0),
                        new ColorBlock(0, 0),
                        new ColorBlock(0, 0),
                        new ColorBlock(0, 0)
                });

    }
}
