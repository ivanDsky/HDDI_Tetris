package game.data.figures;

import game.data.Block;
import game.data.Figure;
import game.data.blocks.ColorBlock;
import game.data.shapes.ZShapeHorizontal;
import game.data.shapes.ZShapeVertical;
import game.util.PairInt;
import game.util.Shape;
import javafx.scene.paint.Color;

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
                    new ColorBlock(0, 0, Color.web("CD00CD")),
                    new ColorBlock(0, 0, Color.web("CD00CD")),
                    new ColorBlock(0, 0, Color.web("CD00CD")),
                    new ColorBlock(0, 0, Color.web("CD00CD"))
            });
    }
}
