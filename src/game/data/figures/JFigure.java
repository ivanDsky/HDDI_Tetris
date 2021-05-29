package game.data.figures;

import game.data.Block;
import game.data.Figure;
import game.data.blocks.ColorBlock;
import game.data.shapes.JShape0Degree;
import game.data.shapes.JShape180Degree;
import game.data.shapes.JShape270Degree;
import game.data.shapes.JShape90Degree;
import game.util.PairInt;
import game.util.Shape;

public class JFigure extends Figure {
    protected JFigure(PairInt center, Shape[] rotateArray, Block[] blocks) {
        super(center, rotateArray, blocks);
    }

    public JFigure(PairInt center) {
        this(center,
                new Shape[]{
                        new JShape0Degree(),
                        new JShape90Degree(),
                        new JShape180Degree(),
                        new JShape270Degree(),
                },
                new Block[]{
                        new ColorBlock(0, 0),
                        new ColorBlock(0, 0),
                        new ColorBlock(0, 0),
                        new ColorBlock(0, 0)
                });

    }
}
