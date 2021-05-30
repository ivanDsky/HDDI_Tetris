package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuScreenController {
    @FXML
    Button playButton;

    public void initialize(){
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("gameScreen.fxml"));

                    Stage primaryStage = (Stage) playButton.getScene().getWindow();
                    primaryStage.setTitle("Tutris 2");
                    primaryStage.setScene(new Scene(root, 600, 400));
                    primaryStage.show();
                }catch (Exception ignored){}


                System.out.println("debug:: playClick");
            }
        });
    }
}
