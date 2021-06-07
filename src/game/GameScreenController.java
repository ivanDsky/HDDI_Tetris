package game;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.data.*;
import game.data.spells.FreezeGame;
import game.data.spells.SkipFigure;
import game.data.spells.SwapFigures;
import game.util.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;
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
    private AnchorPane rootPane;
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
    @FXML
    private Label levelNumber;
    @FXML
    private Label cubes;
    @FXML
    private Button pauseButton;


    @FXML
    private AnchorPane pauseMenu;

    @FXML
    private Button playButton;
    @FXML
    private Button menuButton;
    @FXML
    private Button resetButton;
    @FXML
    private Label pauseLabel;

    @FXML
    private Label countSkip;
    @FXML
    private Label countSwap;
    @FXML
    private Label countFreeze;

    private Field field;
    private Level level;

    private boolean isRotateReloaded = true;
    private boolean isMoveDownReloaded = true;
    private boolean isDownPressed = false;

    public MediaPlayer mediaPlayer;

    public void addKeys() {
        rootPane.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> {
            if (field.state.get() != GameState.PLAY.ordinal()) return;
            if (keyEvent.getCode() == KeyCode.DOWN) {
                isMoveDownReloaded = true;
                isDownPressed = false;
            }
            if (keyEvent.getCode() == KeyCode.UP) {
                isRotateReloaded = true;
            }
        });
        rootPane.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (field.state.get() != GameState.PLAY.ordinal()) return;
            boolean draw = false;
            if (keyEvent.getCode() == KeyCode.UP && isRotateReloaded) {
                draw = field.rotate(Rotation.RIGHT);
                isRotateReloaded = false;
            }
            if (keyEvent.getCode() == KeyCode.DOWN && isMoveDownReloaded) {
                isDownPressed = true;
                draw = field.move(Move.DOWN);
            }
            if (keyEvent.getCode() == KeyCode.LEFT) draw = field.move(Move.LEFT);
            if (keyEvent.getCode() == KeyCode.RIGHT) draw = field.move(Move.RIGHT);
            if (draw) drawField();
        });
    }


    private Spell swap, skip, freeze;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Media media = new Media(getClass().getResource("res/back.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(.1);
        mediaPlayer.setOnEndOfMedia(() -> {mediaPlayer.seek(Duration.ZERO);});
        mediaPlayer.play();
        LoadLevel loadLevel = new FileLoadLevel(Main.levelPath);
        level = loadLevel.getLevel();
        field = new Field(loadLevel);

        field.score.addListener((observableValue, number, t1) -> {
            int scoreInt = t1.intValue();
            if (scoreInt > level.highScore) level.highScore = scoreInt;
            score.setText(Integer.toString(scoreInt));
            bestScore.setText(Integer.toString(level.highScore));
            if(number.intValue() / 10000 < t1.intValue() / 10000){
                field.gamePause *= 0.9;
            }
        });
        field.score.set(0);
        field.blocksDeleted.addListener((observableValue, number, t1) -> {
            cubes.setText(String.format("%d/%d", field.blocksDeleted.get(), field.blocksToDelete));
            if (field.blocksToDelete != 0 && field.isGameWon()) {
                pauseMenu.setVisible(true);
                mediaPlayer.stop();
                pauseLabel.setText("You win!!!");
                playButton.setManaged(false);
                playButton.setVisible(false);
                field.state.set(GameState.END.ordinal());
                AudioClip clip = new AudioClip(getClass().getResource("res/win.wav").toExternalForm());
                clip.setVolume(.3);
                clip.play();
                saveHighScore();
            }
        });
        field.blocksDeleted.set(0);

        levelNumber.setText(getWithInf(level.number));
        swap = new SwapFigures(field, level.swapSpells);
        skip = new SkipFigure(field, level.skipSpells);
        freeze = new FreezeGame(field, level.freezeSpells);


        countSwap.setText(getWithInf(swap.leftSpells));
        countSkip.setText(getWithInf(skip.leftSpells));
        countFreeze.setText(getWithInf(freeze.leftSpells));

        changeFigureButton.setOnAction(actionEvent -> {
            if (field.state.get() == GameState.PLAY.ordinal()) {
                swap.apply();
                countSwap.setText(getWithInf(swap.leftSpells));
                drawField();
                drawNextFigure();
            }
        });
        skipFigureButton.setOnAction(actionEvent -> {
            if (field.state.get() == GameState.PLAY.ordinal()) {
                skip.apply();
                countSkip.setText(getWithInf(skip.leftSpells));
                drawField();
                drawNextFigure();
            }
        });
        freezeFigureButton.setOnAction(actionEvent -> {
            if (field.state.get() == GameState.PLAY.ordinal()) {
                freeze.apply();
                countFreeze.setText(getWithInf(freeze.leftSpells));
            }
        });
        pauseButton.setOnAction(actionEvent -> {
            pauseLabel.setText("Pause");
            pauseMenu.setVisible(true);
            mediaPlayer.stop();
            playButton.setManaged(true);
            playButton.setVisible(true);
            field.state.set(GameState.PAUSE.ordinal());
        });

        menuButton.setOnAction(actionEvent -> {
            Stage primaryStage = (Stage) changeFigureButton.getScene().getWindow();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("menuScreen.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            primaryStage.setResizable(false);
            primaryStage.setTitle("Menu");
            primaryStage.setScene(new Scene(root, 720, 480));
            primaryStage.show();

            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            double centerY = bounds.getMinY() + (bounds.getHeight() - 480) / 2;
            primaryStage.setY(centerY);
            double centerX = bounds.getMinX() + (bounds.getWidth() - 720) / 2;
            primaryStage.setX(centerX);
        });

        playButton.setOnAction(actionEvent -> {
            pauseMenu.setVisible(false);
            mediaPlayer.play();
            field.state.set(GameState.PLAY.ordinal());
        });

        resetButton.setOnAction(actionEvent -> {
            reload();
        });

        addKeys();
        startGame();
    }

    public void reload(){
        Scene scene = playButton.getScene();
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

    private void saveHighScore() {
        try {
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(new File(Main.levelPath), level);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getWithInf(int x) {
        if (x == -1) return "∞";
        return Integer.toString(x);
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
        if (field.state.get() != GameState.PLAY.ordinal()) return;
        drawField();
        drawNextFigure();
        if (!field.move(Move.DOWN)) {
            if (isDownPressed) isMoveDownReloaded = false;
            if (!field.endMove()) {
                playButton.setManaged(false);
                playButton.setVisible(false);
                mediaPlayer.stop();
                if (level.number == -1) {
                    pauseLabel.setText("Your score " + field.score.get());
                    saveHighScore();
                    AudioClip clip = new AudioClip(getClass().getResource("res/win2.wav").toExternalForm());
                    clip.setVolume(.3);
                    clip.play();
                } else {
                    pauseLabel.setText("You lose...");
                    AudioClip clip = new AudioClip(getClass().getResource("res/lose.wav").toExternalForm());
                    clip.setVolume(.3);
                    clip.play();
                }
                pauseMenu.setVisible(true);
                field.state.set(GameState.END.ordinal());
                timer.stop();
            } else {
                field.state.set(GameState.REMOVE.ordinal());
                List<Integer> full = field.fullHorizontals();
                for (Integer i : full) {
                    field.removeHorizontalLine(i);
                }
                if (field.toRemove(Duration.ZERO)) field.removeCommit();
            }
        }
    }

    public void drawField() {
        gamePane.getRowConstraints().clear();
        gamePane.getColumnConstraints().clear();
        gamePane.getChildren().clear();

        for (int i = 0; i < field.width; ++i) {
            for (int j = 0; j < field.height; ++j) {
                field.getBlocks()[i][j].setXY(i, j);
                setBlock(field.getBlocks()[i][j], gamePane);
            }
        }
        drawFigure();
    }

    private void drawFigure() {
        for (Block block : field.getCurrentFigure().getBlocks()) {
            setBlock(block, gamePane);
        }
    }

    private void drawNextFigure() {
        nextFigure.getChildren().clear();
        nextFigure.getColumnConstraints().clear();
        nextFigure.getRowConstraints().clear();
        PairInt saveCenter = field.getNextFigure().getCenter();
        field.getNextFigure().setCenter(new PairInt(3, 3));
        for (Block block : field.getNextFigure().getBlocks()) {
            setBlock(block, nextFigure);
        }

        field.getNextFigure().setCenter(saveCenter);
    }

    private void setBlock(Block current, GridPane pane) {
        if (current.getY() < 0) return;
        pane.add(current.getNode(), current.getX(), current.getY());
    }
}
