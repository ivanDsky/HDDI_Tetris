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

public class MenuScreenController {
    @FXML
    Button playButton;
    @FXML
    Button levelsButton;
    @FXML
    Button quitButton;

    public void initialize(){
        Scene scene = playButton.getScene();
        playButton.setOnAction(actionEvent -> {
            Main.levelPath = "src/levels/levelInfinite.json";
            loadScreen("gameScreen.fxml",700,820);

        });
        levelsButton.setOnAction(actionEvent -> loadScreen("levelsScreen.fxml",720,480));
        quitButton.setOnAction(actionEvent -> ((Stage) playButton.getScene().getWindow()).close());
    }

    public void loadScreen(String name,int width,int height){
        Scene scene = playButton.getScene();
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource(name));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage primaryStage = (Stage) scene.getWindow();
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        if(name.equals("gameScreen.fxml")) {
            primaryStage.setTitle("Game");
            primaryStage.setY(0);
        }else {
            primaryStage.setTitle("Levels");
            double centerY = bounds.getMinY() + (bounds.getHeight() - height) / 2;
            primaryStage.setY(centerY);
        }
        primaryStage.setResizable(false);
        scene = new Scene(root,width,height);
        primaryStage.setScene(scene);
        double centerX = bounds.getMinX() + (bounds.getWidth() - width) / 2;
        primaryStage.setX(centerX);
    }
}
