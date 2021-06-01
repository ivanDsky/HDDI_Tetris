package game.data;

import game.data.blocks.BlockedBlock;
import game.data.blocks.EmptyBlock;
import game.data.shapes.HorizontalLineShape;
import game.util.PairInt;
import game.util.Shape;
import game.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Field {
    public static final int FIELD_WIDTH = 10;
    public static final int FIELD_HEIGHT = 14;
    private final Block[][] gameField;
    private Figure current;
    private Figure next;
    public int gamePause = 1200;

    public Field(){
        current = getRandomFigure();
        next = getRandomFigure();
        gameField = new Block[FIELD_WIDTH][FIELD_HEIGHT];
        for(int i = 0;i < FIELD_WIDTH; ++i){
            for(int j = 0;j < FIELD_HEIGHT; ++j){
                gameField[i][j] = new EmptyBlock(i,j);
            }
        }
        gameField[0][13] = new BlockedBlock(0,13);
        gameField[1][13] = new BlockedBlock(1,13);
        gameField[8][13] = new BlockedBlock(8,13);
        gameField[9][13] = new BlockedBlock(9,13);
    }

    public Block[][] getBlocks(){
        return gameField;
    }

    public boolean isPlacedGood(Figure figure){
        return inBounds(figure) && !intersects(figure);
    }

    public boolean inBounds(Figure figure){
        return figure.checkHorizontal(0,FIELD_WIDTH - 1) && figure.checkVertical(-100,FIELD_HEIGHT - 1);
    }

    public boolean intersects(Figure figure){
        for(PairInt coordinate : figure.getCoordinates()){
            if(!isCoordinateOnField(coordinate))continue;
            if(!isEmpty(coordinate))return true;
        }
        return false;
    }

    public boolean isEmpty(PairInt xy){
        return isEmpty(xy.getX(),xy.getY());
    }

    public boolean isEmpty(int x,int y){
        return gameField[x][y] instanceof EmptyBlock;
    }

    /**
     * Try to perform move, if possible performs and returns true, otherwise return false
     */
    public boolean move(Move direction){
        Move opposite = Util.opposite(direction);

        current.move(direction);
        if(isPlacedGood(current))return true;

        current.move(opposite);
        return false;
    }

    /**
     * Try to perform rotation, if possible performs and returns true, otherwise return false
     */
    public boolean rotate(Rotation rotation){
        Rotation opposite = Util.opposite(rotation);

        current.rotate(rotation);
        if(isPlacedGood(current))return true;

        current.rotate(opposite);
        return false;
    }

    public boolean endMove(){
        for(Block block : current.getBlocks()){
            if(block.getY() < 0)return false;
            gameField[block.getX()][block.getY()] = block;
        }
        current = next;
        next = getRandomFigure();
        return true;
    }

    public void removeHorizontalLine(int y){
        removeShape(new HorizontalLineShape(y,0,FIELD_WIDTH - 1));
    }

    public void removeHorizontalLine(int y,int step){
        removeShapeAnim(new HorizontalLineShape(y,0,FIELD_WIDTH - 1),step);
    }

    public void removeShape(Shape shape){
        for(PairInt coordinate : shape.getCoordinates()){
            if(!isCoordinateOnField(coordinate))continue;
            removeBlock(coordinate);
        }
    }

    public void removeShapeAnim(Shape shape,int step){
        for(PairInt coordinate : shape.getWithStep(step)){
            if(!isCoordinateOnField(coordinate))continue;
            removeBlock(coordinate);
        }
    }

    public void removeBlock(PairInt coordinate){
        gameField[coordinate.getX()][coordinate.getY()] = gameField[coordinate.getX()][coordinate.getY()].removeBlock(this);
    }

    public boolean isShapeFull(Shape shape){
        for(PairInt coordinate : shape.getCoordinates()){
            if(!isCoordinateOnField(coordinate))continue;
            if(isEmpty(coordinate))return false;
        }
        return true;
    }

    public boolean isHorizontalFull(int y){
        return isShapeFull(new HorizontalLineShape(y,0,FIELD_WIDTH - 1));
    }

    public boolean isCoordinateOnField(PairInt coordinate){
        return 0 <= coordinate.getX() && coordinate.getX() < FIELD_WIDTH
                && 0 <= coordinate.getY() && coordinate.getY() < FIELD_HEIGHT;
    }

    public boolean pushVerticalDown(int x,int fy){
        for(int y = fy; y >= 0; --y){
            if(gameField[x][y + 1] == null){
                gameField[x][y + 1] = gameField[x][y];
                gameField[x][y + 1].setXY(x,y + 1);
                gameField[x][y] = null;
            }
        }
        gameField[x][0] = new EmptyBlock(x,0);
        return true;
    }

    public void push(int y){
        for(int i = 0;i < FIELD_WIDTH; ++i){
            pushVerticalDown(i,y);
        }
    }

    public List<Integer> fullHorizontals(){
        List<Integer> full = new ArrayList<>();
        for(int i = 0;i < FIELD_HEIGHT; ++i){
            if(isHorizontalFull(i))
                full.add(i);
        }
        return full;
    }

    public Figure getRandomFigure(){
        Figure ret = Util.getRandomFigure();
        ret.setCenter(new PairInt(Util.getRandomNumber(2,FIELD_WIDTH - 3),-2));
        return ret;
    }

    public Figure getCurrentFigure() {
        return current;
    }

    public void setCurrentFigure(Figure newCurrent) {
        current = newCurrent;
    }

    public Figure getNextFigure() {
        return next;
    }
}

