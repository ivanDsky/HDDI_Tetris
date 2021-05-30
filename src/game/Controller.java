package game;

import game.data.Block;
import game.data.Field;
import game.data.Move;
import game.data.Rotation;
import game.data.figures.IFigure;
import game.util.PairInt;
import game.util.Timer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private GridPane gamePane;
    private Field field;

    public void addKeys(Scene scene){
        scene.setOnKeyReleased(keyEvent -> {
            System.out.println("debug::");

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

        //field.setCurrentFigure(new IFigure(new PairInt(1,1)));
        //field.endMove();
        field.setCurrentFigure(new IFigure(new PairInt(1, 4)));
        field.rotate(Rotation.RIGHT);
//        field.move(Move.UP);
//        field.move(Move.RIGHT);
//        field.endMove();
//        field.setCurrentFigure(new TFigure(new PairInt(4,4)));
//        field.endMove();
////        field.removeHorizontalLine(1);
//        drawField();
    }

    private Timer timer;

    private void startGame() {
        timer = new Timer(500) {
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
                for(Integer i : field.fullHorizontals()){
                    field.removeHorizontalLine(i);
                }
            }
        }
    }

    public void drawField() {
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
        try {
            gamePane.add(current.getNode(), current.getX(), current.getY());
        } catch (Exception ignored) {
        }
    }

}
