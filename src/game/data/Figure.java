package game.data;

import game.util.PairInt;
import game.util.Shape;

public abstract class Figure {
    protected Block[] blocks;

    protected Shape[] rotateArray;
    protected int rotateIndex;

    protected PairInt center;

    public void rotate(Rotation rotation){
        if(rotation == Rotation.RIGHT)rotateIndex+=1;
        if(rotation == Rotation.DOUBLE)rotateIndex+=2;
        if(rotation == Rotation.LEFT)rotateIndex+=3;
        rotateIndex %= 4;
        updateCenter();
    }

    public void move(Move direction){
        if(direction == Move.UP)center.addXY(new PairInt(0,-1));
        if(direction == Move.DOWN)center.addXY(new PairInt(0,1));
        if(direction == Move.LEFT)center.addXY(new PairInt(-1,0));
        if(direction == Move.RIGHT)center.addXY(new PairInt(1,0));
        updateCenter();
    }

    public boolean checkHorizontal(int left,int right){
        for(PairInt coordinate : getCoordinates()){
            if(left <= coordinate.getX() && coordinate.getX() <= right)continue;
            return false;
        }
        return true;
    }

    public boolean checkVertical(int top,int bottom){
        for(PairInt coordinate : getCoordinates()){
            if(top <= coordinate.getY() && coordinate.getY() <= bottom)continue;
            return false;
        }
        return true;
    }

    public PairInt[] getCoordinates(){
        return rotateArray[rotateIndex].getCoordinates();
    }

    private void updateCenter(){
        rotateArray[rotateIndex].setCenter(center);
    }

    public Block[] getBlocks(){
        PairInt[] coordinates = getCoordinates();
        for(int i = 0;i < coordinates.length; ++i){
            blocks[i].setXY(coordinates[i]);
        }
        return blocks;
    }

}



