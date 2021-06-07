package game.data;

import game.Main;
import game.data.blocks.*;
import game.data.shapes.HorizontalLineShape;
import game.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Field {
    public int width = 10;
    public int height = 14;
    private Block[][] gameField;
    private Figure current;
    private Figure next;
    public int gamePause = 1200;
    public int blocksToDelete;
    public SimpleIntegerProperty score;
    public SimpleIntegerProperty blocksDeleted;

    public IntegerProperty state = new SimpleIntegerProperty(GameState.PLAY.ordinal());

    public Field(LoadLevel loadLevel) {
        next = getRandomFigure();
        skipMove();
        Level level = loadLevel.getLevel();
        gameField = loadLevel.loadBlocks();
        width = level.width;
        height = level.height;
        used = new boolean[width][height];
        blocked = new boolean[width][height];
        for(int i = 0;i < width; ++i){
            for(int j = 0;j < height; ++j){
                if(Character.toLowerCase(level.field[j][i]) == 'x')blocksToDelete++;
            }
        }

        gamePause = level.speed;
        score = new SimpleIntegerProperty(-1);
        blocksDeleted = new SimpleIntegerProperty(-1);
        timeline = new Timeline();
    }

    public Block[][] getBlocks() {
        return gameField;
    }

    public boolean isPlacedGood(Figure figure) {
        return inBounds(figure) && !intersects(figure);
    }

    public boolean inBounds(Figure figure) {
        return figure.checkHorizontal(0, width - 1) && figure.checkVertical(-100, height - 1);
    }

    public boolean intersects(Figure figure) {
        for (PairInt coordinate : figure.getCoordinates()) {
            if (!isCoordinateOnField(coordinate)) continue;
            if (!isEmpty(coordinate)) return true;
        }
        return false;
    }

    public boolean isEmpty(PairInt xy) {
        return isEmpty(xy.getX(), xy.getY());
    }

    public boolean isEmpty(int x, int y) {
        return gameField[x][y] instanceof EmptyBlock;
    }

    /**
     * Try to perform move, if possible performs and returns true, otherwise return false
     */
    public boolean move(Move direction) {
        Move opposite = Util.opposite(direction);

        current.move(direction);
        if (isPlacedGood(current)) return true;

        current.move(opposite);
        return false;
    }

    /**
     * Try to perform rotation, if possible performs and returns true, otherwise return false
     */
    public boolean rotate(Rotation rotation) {
        Rotation opposite = Util.opposite(rotation);

        current.rotate(rotation);
        if (isPlacedGood(current)) return true;

        current.rotate(opposite);
        return false;
    }

    public boolean isGameWon() {
        return blocksDeleted.get() == blocksToDelete;
    }

    public boolean endMove() {
        for (Block block : current.getBlocks()) {
            if (block.getY() < 0) return false;
            gameField[block.getX()][block.getY()] = block;
        }
        current = next;
        current.setCenter(new PairInt(Util.getRandomNumber(2, width - 3), -2));
        next = getRandomFigure();
        return true;
    }

    public void skipMove() {
        current = next;
        current.setCenter(new PairInt(Util.getRandomNumber(2, width - 3), -2));
        next = getRandomFigure();
    }

    private boolean[][] used;
    public boolean[][] blocked;

    private final Set<RemoveShape> toRemove = new HashSet<>();

    public void removeHorizontalLine(int y) {
        removeShape(new HorizontalLineShape(y, 0, width - 1), 0);
    }

    public void removeShape(Shape shape, int startStep) {
        toRemove.add(new RemoveShape(shape, startStep));
    }

    Set<PairInt> set = new HashSet<>();

    public boolean toRemove(Duration lastDuration) {
        set.clear();
        List<RemoveShape> rlst = new ArrayList<>();
        for (RemoveShape shape : toRemove) {
            List<PairInt> lst = shape.removeCoordinates();
            if (lst == null) rlst.add(shape);
            else set.addAll(lst);
        }
        rlst.forEach(toRemove::remove);
        KeyFrame last = null;
        for (PairInt coordinate : set) {
            if (!isCoordinateOnField(coordinate)) continue;
            if (used[coordinate.getX()][coordinate.getY()]) continue;
            Block block = getBlocks()[coordinate.getX()][coordinate.getY()];
            KeyFrame temp = block.animation();
            if (temp == null) continue;
            KeyFrame finalTemp = temp;
            temp = new KeyFrame(lastDuration.add(temp.getTime().divide(2)), actionEvent -> {
                finalTemp.getOnFinished().handle(actionEvent);
                score.set(score.get() + 300);
                if(block instanceof DeleteBlock)blocksDeleted.set(blocksDeleted.get() + 1);
                AudioClip clip = new AudioClip(Main.class.getResource("res/exp.wav").toExternalForm());
                clip.setVolume(.3);
                clip.play();
            });
            timeline.getKeyFrames().add(temp);
            last = temp;
        }
        if (last != null) {
            for (PairInt coordinate : set) {
                if (!isCoordinateOnField(coordinate)) continue;
                if (used[coordinate.getX()][coordinate.getY()]) continue;
                removeBlock(coordinate);
            }

            if (set.size() > 0) toRemove(last.getTime());
        }
        return true;
    }

    public Timeline timeline;

    public void removeCommit() {
        timeline.getKeyFrames().add(new KeyFrame(timeline.getTotalDuration().add(new Duration(400))));
        timeline.setOnFinished(actionEvent -> {
            Block[][] blocks = new Block[width][height];
            for (int i = 0; i < width; ++i) {
                for (int j = 0; j < height; ++j) {
                    if(used[i][j]){
                        if(blocked[i][j])blocks[i][j] = getBlocks()[i][j];
                        continue;
                    }
                    int down = getDeletedBlock(i,j);
                    int nj = j + down;
                    if(nj >= height)continue;
                    blocks[i][nj] = getBlocks()[i][j];

                }
            }
            for (int i = 0; i < width; ++i) {
                for (int j = 0; j < height; ++j) {
                   if(blocks[i][j] == null)blocks[i][j] = new EmptyBlock(i,j);
                }
            }
            gameField = blocks;
            if(GameState.REMOVE.ordinal() == state.get())state.set(GameState.PLAY.ordinal());
            timeline.getKeyFrames().clear();
            used = new boolean[width][height];
            blocked = new boolean[width][height];
        });
        timeline.play();
    }

    public int getDeletedBlock(int x,int y){
        int cnt = 0;
        for(int j = y;j < height; ++j){
            if(blocked[x][j])return cnt;
            if(used[x][j])cnt++;
        }
        return cnt;
    }

    public void removeBlock(PairInt coordinate) {
        if (used[coordinate.getX()][coordinate.getY()]) return;
        used[coordinate.getX()][coordinate.getY()] = true;

        gameField[coordinate.getX()][coordinate.getY()] = gameField[coordinate.getX()][coordinate.getY()].removeBlock(this);
    }

    public boolean isShapeFull(Shape shape) {
        for (PairInt coordinate : shape.getCoordinates()) {
            if (!isCoordinateOnField(coordinate)) continue;
            if (isEmpty(coordinate)) return false;
        }
        return true;
    }

    public boolean isHorizontalFull(int y) {
        return isShapeFull(new HorizontalLineShape(y, 0, width - 1));
    }

    public boolean isCoordinateOnField(PairInt coordinate) {
        return 0 <= coordinate.getX() && coordinate.getX() < width
                && 0 <= coordinate.getY() && coordinate.getY() < height;
    }

    public List<Integer> fullHorizontals() {
        List<Integer> full = new ArrayList<>();
        for (int i = 0; i < height; ++i) {
            if (isHorizontalFull(i))
                full.add(i);
        }
        return full;
    }

    public Figure getRandomFigure() {
        Figure random = Util.getRandomFigure();
        if(random.getBlocks().length >= 4)return random;
        for(int i = 0;i < random.getBlocks().length; ++i){
            Block block = random.getBlocks()[i];
            int percent = Util.getRandomNumber(0,10000);
            if(percent < 200)block = new RocketBlock(block.getXY());else
            if(percent < 500)block = new DynamiteBlock(block.getXY());else
            if(percent < 1500)block = new BombBlock(block.getXY());
            random.setBlock(block,i);
        }
        return random;
    }

    public Figure getCurrentFigure() {
        return current;
    }

    public void setCurrentFigure(Figure newCurrent) {
        current = newCurrent;
    }

    public void setNextFigure(Figure newNext) {
        next = newNext;
    }

    public Figure getNextFigure() {
        return next;
    }
}

