package game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class LevelScreenController {
    @FXML
    Button level1Button;
    @FXML
    Button level2Button;
    @FXML
    Button level3Button;
    @FXML
    Button level4Button;
    @FXML
    Button level5Button;
    @FXML
    Button infiniteLevelButton;
    @FXML
    Button backButton;

    public void initialize(){
        level1Button.setOnAction(actionEvent -> loadScreen("1"));
        level2Button.setOnAction(actionEvent -> loadScreen("2"));
        level3Button.setOnAction(actionEvent -> loadScreen("3"));
        level4Button.setOnAction(actionEvent -> loadScreen("4"));
        level5Button.setOnAction(actionEvent -> loadScreen("5"));
        infiniteLevelButton.setOnAction(actionEvent -> loadScreen("Infinite"));
        backButton.setOnAction(actionEvent -> {
            Stage primaryStage = (Stage) level1Button.getScene().getWindow();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("menuScreen.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            primaryStage.setResizable(false);
            primaryStage.setTitle("Menu");
            primaryStage.setScene(new Scene(root,720,480));
            primaryStage.show();
        });

    }

    private void loadScreen(String level){
        Main.levelPath = "src/levels/level" + level + ".json";
        Scene scene = level1Button.getScene();
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource("gameScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage primaryStage = (Stage) scene.getWindow();
        primaryStage.setTitle("Game");
        primaryStage.setResizable(false);
        scene = new Scene(root,700,820);
        primaryStage.setScene(scene);
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - 410);
        primaryStage.setY(0);
    }
}
