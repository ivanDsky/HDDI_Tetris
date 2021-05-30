package game.data.blocks;

import game.data.Block;
import javafx.scene.Node;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ColorBlock extends Block {
    private HBox hBox;
    public ColorBlock(int x, int y, Color color) {
        super(x, y);
        texture = new ImageView("game/res/back_block.png");
        texture.setFitWidth(47);
        texture.setFitHeight(47);

        ColorAdjust adjust = new ColorAdjust();
        adjust.setSaturation(-1);

        Blend blend = new Blend(
                BlendMode.MULTIPLY,
                adjust,
                new ColorInput(
                        0,0,texture.getFitWidth(),texture.getFitHeight(),color
                )
        );

        texture.effectProperty().set(blend);
        hBox = new HBox();
        String style_inner = "-fx-border-color: red;"
                + "-fx-border-width: 2;";
        hBox.setStyle(style_inner);
        hBox.getChildren().add(texture);
    }

    public ColorBlock(int x, int y) {
        this(x, y, Color.LIGHTBLUE);
    }

    @Override
    public Node getNode() {
        return hBox;
    }
}
