package game;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.data.*;
import game.data.spells.FreezeGame;
import game.data.spells.SkipFigure;
import game.data.spells.SwapFigures;
import game.util.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
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
    @FXML
    private Label score;
    @FXML
    private Label bestScore;
    private Field field;
    private Level level;

    private boolean isRotateReloaded = true;
    private boolean isMoveDownReloaded = true;
    private boolean isDownPressed = false;

    public void addKeys(Scene scene){
        scene.getWindow().requestFocus();
        scene.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> {
            if(field.state.get() != GameState.PLAY.ordinal())return;
            if(keyEvent.getCode() == KeyCode.DOWN){
                isMoveDownReloaded = true;
                isDownPressed = false;
            }
            if (keyEvent.getCode() == KeyCode.UP) {
                isRotateReloaded = true;
            }
        });
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if(field.state.get() != GameState.PLAY.ordinal())return;
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
        level= loadLevel.getLevel();
        level.highScore = 18000;
        field = new Field(loadLevel);

        field.score.addListener((observableValue, number, t1) -> {
            int scoreInt = field.score.get();
            if(scoreInt > level.highScore)level.highScore = scoreInt;
            score.setText(Integer.toString(scoreInt));
            bestScore.setText(Integer.toString(level.highScore));
        });

        changeFigureButton.setOnAction(actionEvent -> (new SwapFigures(field)).apply());
        skipFigureButton.setOnAction(actionEvent -> (new SkipFigure(field)).apply());
        freezeFigureButton.setOnAction(actionEvent -> (new FreezeGame(field)).apply());

        startGame();
    }

    private void saveHighScore(){
        try {
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(new File("temp.json"),level);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Timer timer;

    private void startGame() {
        timer = new Timer(field.gamePause) {
            @Override
            public void handle() {
                if(field.state.get() != GameState.PLAY.ordinal())return;
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
                saveHighScore();
            }else{
                field.state.set(GameState.REMOVE.ordinal());
                List<Integer> full = field.fullHorizontals();
                for(Integer i : full){
                    field.removeHorizontalLine(i);
                }
                if(field.toRemove(Duration.ZERO))field.removeCommit();
            }
        }
    }

    public void drawField() {
        gamePane.getRowConstraints().clear();
        gamePane.getColumnConstraints().clear();
        gamePane.getChildren().clear();

        for (int i = 0; i < field.width; ++i) {
            for (int j = 0; j < field.height; ++j) {
                field.getBlocks()[i][j].setXY(i,j);
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
