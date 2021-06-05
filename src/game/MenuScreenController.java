package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Objects;

public class MenuScreenController {
    @FXML
    Button playButton;

    public void initialize(){
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gameScreen.fxml")));

                    Stage primaryStage = (Stage) playButton.getScene().getWindow();
                    primaryStage.setTitle("Tutris 2");
                    primaryStage.setScene(new Scene(root, 1000, 1125));
                    primaryStage.show();
                    System.out.println("debug:: playClick123");
                }catch (Exception ignored){}


                System.out.println("debug:: playClick");
            }
        });
    }
}
