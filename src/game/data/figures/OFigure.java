package game.data.figures;

import game.data.Block;
import game.data.Figure;
import game.data.blocks.ColorBlock;
import game.data.shapes.OShape;
import game.util.PairInt;
import game.util.Shape;
import javafx.scene.paint.Color;

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
                        new ColorBlock(0, 0, Color.web("08E8DE")),
                        new ColorBlock(0, 0, Color.web("08E8DE")),
                        new ColorBlock(0, 0, Color.web("08E8DE")),
                        new ColorBlock(0, 0, Color.web("08E8DE"))
                });

    }
}
