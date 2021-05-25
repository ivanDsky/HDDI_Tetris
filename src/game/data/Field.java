package game.data;

import game.util.PairInt;
import game.util.Util;

public class Field {
    private static final int FIELD_WIDTH = 10;
    private static final int FIELD_HEIGHT = 25;
    private final Block[][] gameField;
    private Figure current;
    private Figure next;
    private int gamePause = 10;

    public Field(){
        gameField = new Block[FIELD_WIDTH][FIELD_HEIGHT];
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
            if(!isEmpty(coordinate))return true;
        }
        return false;
    }

    public boolean isEmpty(PairInt xy){
        return isEmpty(xy.getX(),xy.getY());
    }

    public boolean isEmpty(int x,int y){
        return gameField[x][y] == null;
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

    public void endMove(){
        for(Block block : current.getBlocks()){
            gameField[block.getX()][block.getY()] = block;
        }
        current = next;
        next = getRandomFigure();
    }

    //TODO
    public Figure getRandomFigure(){
        return current;
    }
}
