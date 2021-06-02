package game;

import game.data.Block;
import game.data.Field;
import game.data.Move;
import game.data.Rotation;
import game.util.Timer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameScreenController implements Initializable {
    @FXML
    private GridPane gamePane;
    private Field field;

    public void addKeys(Scene scene){
        scene.getWindow().requestFocus();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.UP) field.rotate(Rotation.RIGHT);
            if (keyEvent.getCode() == KeyCode.DOWN) field.move(Move.DOWN);
            if (keyEvent.getCode() == KeyCode.LEFT) field.move(Move.LEFT);
            if (keyEvent.getCode() == KeyCode.RIGHT) field.move(Move.RIGHT);
            drawField();
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        field = new Field();

        startGame();
    }

    private Timer timer;

    private void startGame() {
        timer = new Timer(field.gamePause) {
            @Override
            public void handle() {
                step();
            }
        };
        timer.start();
    }

    private void step() {
        drawField();
        if (!field.move(Move.DOWN)) {
            if (!field.endMove()){
                timer.stop();
            }else{
                List<Integer> full = field.fullHorizontals();
                for(Integer i : full){
                    field.removeHorizontalLine(i);
                }
                field.removeCommit();
            }
        }
    }

    public void drawField() {
        gamePane.getColumnConstraints().clear();
        gamePane.getRowConstraints().clear();
        gamePane.getChildren().clear();

        for (int i = 0; i < Field.FIELD_WIDTH; ++i) {
            for (int j = 0; j < Field.FIELD_HEIGHT; ++j) {
                setBlock(field.getBlocks()[i][j]);
            }
        }
        drawFigure();
    }

    private void drawFigure() {
        for (Block block : field.getCurrentFigure().getBlocks()) {
            setBlock(block);
        }
    }


    private void setBlock(Block current) {
        if (current.getY() < 0) return;
        gamePane.add(current.getNode(), current.getX(), current.getY());
    }
}
