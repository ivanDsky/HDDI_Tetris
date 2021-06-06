package game.data;

import game.util.PairInt;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
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

    public Timeline animation(){
        Timeline timeline = new Timeline();
        int time = 400;
        KeyFrame frame = new KeyFrame(new Duration(4 * time),actionEvent -> {
            Duration duration1 = new Duration(time);
            RotateTransition transition1 = new RotateTransition(duration1,getNode());
            transition1.setFromAngle(0);
            transition1.setToAngle(180);

            Duration duration2 = new Duration(time * 2);

            RotateTransition transition2 = new RotateTransition(duration2,getNode());
            //transition2.setByAngle(60);

            Duration duration3 = new Duration(time);

            RotateTransition transition3 = new RotateTransition(duration3,getNode());
            //transition3.setByAngle(-30);

            transition1.setOnFinished(actionEvent1 -> transition2.play());
            transition2.setOnFinished(actionEvent1 -> transition3.play());
            transition1.play();
        });
        timeline.getKeyFrames().add(frame);
        return timeline;
    }

    public abstract Node getNode();
}
