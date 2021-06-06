package game.data;

import game.util.PairInt;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
public abstract class Block extends PairInt {
    protected ImageView texture;
    protected Rectangle rectangle = new Rectangle(50,50);

    public Block(int x, int y) {
        super(x, y);
        rectangle.setStroke(Color.BLACK);
    }

    public Block(PairInt xy) {
        this(xy.getX(),xy.getY());
    }

    public void setXY(PairInt xy) {
        setXY(xy.getX(), xy.getY());
    }

    public PairInt getXY() {
        return this;
    }

    public Block removeBlock(Field field){
        return this;
    }

    public KeyFrame animation(){
        int time = 200;
        return new KeyFrame(new Duration(time), actionEvent -> {
            Duration duration = new Duration(time);
            ScaleTransition transition = new ScaleTransition(duration,getNode());
            transition.setToX(1.3);
            transition.setToY(1.3);
            transition.setAutoReverse(true);
            transition.setCycleCount(2);
            transition.play();
        });
    }

    public abstract Node getNode();
}
