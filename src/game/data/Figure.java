package game.data;

import game.util.PairInt;

public abstract class Figure {
    protected Block[] blocks;

    protected PairInt[][] rotateArray;
    protected int rotateIndex;

    protected PairInt center;

    public void rotate(Rotation rotation){
        if(rotation == Rotation.RIGHT)rotateIndex+=1;
        if(rotation == Rotation.DOUBLE)rotateIndex+=2;
        if(rotation == Rotation.LEFT)rotateIndex+=3;
        rotateIndex %= 4;
    }

    public void move(Move direction){
        if(direction == Move.UP)center.addXY(new PairInt(0,-1));
        if(direction == Move.DOWN)center.addXY(new PairInt(0,1));
        if(direction == Move.LEFT)center.addXY(new PairInt(-1,0));
        if(direction == Move.RIGHT)center.addXY(new PairInt(1,0));
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
        PairInt[] ret = new PairInt[blocks.length];
        for(int i = 0;i < ret.length; ++i){
            ret[i] = PairInt.add(center,rotateArray[rotateIndex][i]);
        }
        return ret;
    }

    public Block[] getBlocks(){
        PairInt[] coordinates = getCoordinates();
        for(int i = 0;i < coordinates.length; ++i){
            blocks[i].setXY(coordinates[i]);
        }
        return blocks;
    }

}



