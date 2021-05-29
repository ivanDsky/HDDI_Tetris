package game;

import game.data.*;
import game.data.figures.IFigure;
import game.data.figures.TFigure;
import game.util.PairInt;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class Controller {
    @FXML
    private GridPane gamePane;
    private Figure figure;
    private Field field;

    public void initialize() throws InterruptedException {
        field = new Field();

        field.setCurrentFigure(new IFigure(new PairInt(1,1)));
        field.endMove();
        field.setCurrentFigure(new IFigure(new PairInt(1,4)));
        field.rotate(Rotation.RIGHT);
        field.move(Move.UP);
        field.move(Move.RIGHT);
        field.endMove();
        field.setCurrentFigure(new TFigure(new PairInt(4,4)));
        field.endMove();
        field.removeHorizontalLine(1);
        drawField();
    }

    public void drawField(){
        for(int i = 0;i < Field.FIELD_WIDTH; ++i){
            for(int j = 0;j < Field.FIELD_HEIGHT; ++j){
                Block current = field.getBlocks()[i][j];
                gamePane.add(current.getNode(), current.getX(), current.getY());
            }
        }
        gamePane.setPrefSize(800, 600); // Default width and height
        gamePane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
    }
}
