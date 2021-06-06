package game;

import game.data.Block;
import game.data.Field;
import game.data.Move;
import game.data.Rotation;
import game.data.spells.FreezeGame;
import game.data.spells.SkipFigure;
import game.data.spells.SwapFigures;
import game.util.FileLoadLevel;
import game.util.LoadLevel;
import game.util.PairInt;
import game.util.Timer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameScreenController implements Initializable {
    @FXML
    private GridPane gamePane;
    @FXML
    private GridPane nextFigure;
    @FXML
    private Button changeFigureButton;
    @FXML
    private Button freezeFigureButton;
    @FXML
    private Button skipFigureButton;
    private Field field;

    private boolean isRotateReloaded = true;
    private boolean isMoveDownReloaded = true;
    private boolean isDownPressed = false;
    private boolean isPaused = false;

    public void addKeys(Scene scene){
        scene.getWindow().requestFocus();
        scene.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> {
            if(isPaused)return;
            if(keyEvent.getCode() == KeyCode.DOWN){
                isMoveDownReloaded = true;
                isDownPressed = false;
            }
            if (keyEvent.getCode() == KeyCode.UP) {
                isRotateReloaded = true;
            }
        });
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if(isPaused)return;
            boolean draw = false;
            if (keyEvent.getCode() == KeyCode.UP && isRotateReloaded) {
                draw = field.rotate(Rotation.RIGHT);
                isRotateReloaded = false;
            }
            if (keyEvent.getCode() == KeyCode.DOWN && isMoveDownReloaded) {
                isDownPressed = true;
                draw = field.move(Move.DOWN);
            }
            if (keyEvent.getCode() == KeyCode.LEFT)  draw = field.move(Move.LEFT);
            if (keyEvent.getCode() == KeyCode.RIGHT) draw = field.move(Move.RIGHT);
            if(draw)drawField();
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadLevel loadLevel = new FileLoadLevel("temp.json");

        field = new Field(loadLevel);

        changeFigureButton.setOnAction(actionEvent -> (new SwapFigures(field)).apply());
        skipFigureButton.setOnAction(actionEvent -> (new SkipFigure(field)).apply());
        freezeFigureButton.setOnAction(actionEvent -> (new FreezeGame(field)).apply());

        startGame();
    }

    private Timer timer;

    private void startGame() {
        timer = new Timer(field.gamePause) {
            @Override
            public void handle() {
                step();
                timer.setCycleDuration(field.gamePause);
            }
        };
        timer.start();
    }

    private void step() {
        drawNextFigure();
        drawField();
        if (!field.move(Move.DOWN)) {
            if(isDownPressed)isMoveDownReloaded = false;
            if (!field.endMove()){
                timer.stop();
            }else{
                List<Integer> full = field.fullHorizontals();
                for(Integer i : full){
                    field.removeHorizontalLine(i);
                }
                if(field.startAnimation()) {
                    timer.stop();
                    field.timeline.setOnFinished(actionEvent -> {
                        timer.start();
                        field.removeCommit();
                    });
                    field.timeline = null;
                }else{
                    field.removeCommit();
                }
            }
        }
    }

    public void drawField() {
        gamePane.getRowConstraints().clear();
        gamePane.getColumnConstraints().clear();
        gamePane.getChildren().clear();

        for (int i = 0; i < field.width; ++i) {
            for (int j = 0; j < field.height; ++j) {
                setBlock(field.getBlocks()[i][j],gamePane);
            }
        }
        drawFigure();
    }

    private void drawFigure() {
        for (Block block : field.getCurrentFigure().getBlocks()) {
            setBlock(block,gamePane);
        }
    }

    private void drawNextFigure(){
        nextFigure.getChildren().clear();
        PairInt saveCenter = field.getNextFigure().getCenter();
        field.getNextFigure().setCenter(new PairInt(3,3));
        for (Block block : field.getNextFigure().getBlocks()) {
            setBlock(block,nextFigure);
        }
        field.getNextFigure().setCenter(saveCenter);
    }

    private void setBlock(Block current,GridPane pane) {
        if (current.getY() < 0) return;
        pane.add(current.getNode(), current.getX(), current.getY());
    }
}
