package game.data.figures;

import game.data.Block;
import game.data.Figure;
import game.data.blocks.ColorBlock;
import game.data.shapes.SShapeHorizontal;
import game.data.shapes.SShapeVertical;
import game.util.PairInt;
import game.util.Shape;
import javafx.scene.paint.Color;

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
                        new ColorBlock(0, 0, Color.web("3CAEA3")),
                        new ColorBlock(0, 0, Color.web("3CAEA3")),
                        new ColorBlock(0, 0, Color.web("3CAEA3")),
                        new ColorBlock(0, 0, Color.web("3CAEA3"))
                });
    }
}
