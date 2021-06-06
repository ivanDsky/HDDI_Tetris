package game.data;

import game.data.blocks.EmptyBlock;
import game.data.shapes.HorizontalLineShape;
import game.util.LoadLevel;
import game.util.PairInt;
import game.util.Shape;
import game.util.Util;
import javafx.animation.Timeline;

import java.util.ArrayList;
import java.util.List;

public class Field {
    public int width = 10;
    public  int height = 14;
    private final Block[][] gameField;
    private Figure current;
    private Figure next;
    public int gamePause = 1200;
    public int blocksToDelete;
    public int blocksToDeleteLeft;

    public Field(LoadLevel loadLevel) {
        next = getRandomFigure();
        skipMove();
        gameField = loadLevel.loadBlocks();
        width = loadLevel.getWidth();
        height = loadLevel.getHeight();
        gamePause = loadLevel.getSpeed();
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

    public boolean isGameWon(){
        return blocksToDeleteLeft <= 0;
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

    private boolean[][] used = new boolean[width][height];

    public void removeHorizontalLine(int y) {
        removeShape(new HorizontalLineShape(y, 0, width - 1));
    }

    public void removeHorizontalLine(int y, int step) {
        removeShapeAnim(new HorizontalLineShape(y, 0, width - 1), step);
    }

    public void removeShape(Shape shape) {
        for (PairInt coordinate : shape.getCoordinates()) {
            if (!isCoordinateOnField(coordinate)) continue;
            removeBlock(coordinate);
        }
    }

    public void removeShapeAnim(Shape shape, int step) {
        for (PairInt coordinate : shape.getWithStep(step)) {
            if (!isCoordinateOnField(coordinate)) continue;
            removeBlock(coordinate);
        }
    }

    public Timeline timeline;

    public boolean startAnimation() {
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                if (used[i][j]) {
                    if (getBlocks()[i][j] instanceof EmptyBlock) continue;
                    timeline = gameField[i][j].animation();
                    timeline.play();
                }
            }
        }
        return timeline != null;
    }

    public void removeCommit() {
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                if (used[i][j]) {
                    used[i][j] = false;
                    pushVerticalDown(i, j - 1);
                }
            }
        }
        //timeline.play();
        used = new boolean[width][height];
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

    public boolean pushVerticalDown(int x, int fy) {
        if (fy >= height) return false;
        for (int y = fy; y >= 0; --y) {
            gameField[x][y + 1] = gameField[x][y];
            gameField[x][y + 1].setXY(x, y + 1);

        }
        gameField[x][0] = new EmptyBlock(x, 0);
        return true;
    }

    public void push(int y) {
        for (int i = 0; i < width; ++i) {
            pushVerticalDown(i, y);
        }
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
        return Util.getRandomFigure();
    }

    public Figure getCurrentFigure() {
        return current;
    }

    public void setCurrentFigure(Figure newCurrent) {
        current = newCurrent;
    }

    public void setNextFigure(Figure newNext){
        next = newNext;
    }

    public Figure getNextFigure() {
        return next;
    }
}

