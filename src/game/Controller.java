package game;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class Controller {
    @FXML
    private GridPane gamePane;

    public void initialize() {
        gamePane.add(new Rectangle(100,100),3,3);
        gamePane.add(new Rectangle(100,100),2,2);
        gamePane.add(new Rectangle(100,100),1,1);
    }
}
