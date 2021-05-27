package game.data.figures;

import game.data.Block;
import game.data.Figure;
import game.data.blocks.TempBlock;
import game.data.shapes.LShape0Degree;
import game.data.shapes.LShape180Degree;
import game.data.shapes.LShape270Degree;
import game.data.shapes.LShape90Degree;
import game.util.PairInt;
import game.util.Shape;

public class LFigure extends Figure {
    protected LFigure(PairInt center, Shape[] rotateArray, Block[] blocks) {
        super(center, rotateArray, blocks);
    }

    public LFigure(PairInt center) {
        this(center,
                new Shape[]{
                        new LShape0Degree(),
                        new LShape90Degree(),
                        new LShape180Degree(),
                        new LShape270Degree(),
                },
                new Block[]{
                        new TempBlock(0, 0),
                        new TempBlock(0, 0),
                        new TempBlock(0, 0),
                        new TempBlock(0, 0)
                });

    }
}
