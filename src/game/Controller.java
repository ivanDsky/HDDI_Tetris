package game;

import game.data.Block;
import game.data.Figure;
import game.data.Rotation;
import game.data.figures.IFigure;
import game.util.PairInt;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controller {
    @FXML
    private GridPane gamePane;
    private Figure figure;

    public void initialize() throws InterruptedException {
        for(int i = 0;i < 10; ++i){
            for(int j = 0;j < 25; ++j){
                Rectangle rec = new Rectangle(100,100);
                rec.setFill(Color.WHITE);
                gamePane.add(rec,i,j);
            }
        }

        figure = new IFigure(new PairInt(1,1));
        figure.rotate(Rotation.LEFT);
        applyFigure(figure);
    }

    public void applyFigure(Figure figure){
        for(Block block : figure.getBlocks()){
            gamePane.add(block.getRect(),block.getX(),block.getY());
        }
    }
}
