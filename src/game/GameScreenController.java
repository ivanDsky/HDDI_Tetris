package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameScreenController {
    @FXML
    private Button button1;

    public void initialize(){

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("debug:: clicked");
            }
        });
    }

}
