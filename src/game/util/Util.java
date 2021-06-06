package game.util;

import game.data.Figure;
import game.data.Figures;
import game.data.Move;
import game.data.Rotation;
import game.data.figures.*;

import java.util.Random;

public class Util {
    public static Move opposite(Move direction){
        if(direction == Move.DOWN)return Move.UP;
        if(direction == Move.UP)return Move.DOWN;
        if(direction == Move.RIGHT)return Move.LEFT;
        if(direction == Move.LEFT)return Move.RIGHT;
        return null;
    }

    public static Rotation opposite(Rotation rotation){
        if(rotation == Rotation.LEFT)return Rotation.RIGHT;
        if(rotation == Rotation.RIGHT)return Rotation.LEFT;
        if(rotation == Rotation.DOUBLE)return Rotation.DOUBLE;
        return null;
    }

    private static final Random random = new Random();

    public static int getRandomNumber(int l,int r){
        int rand = random.nextInt(r - l + 1);
        return l + rand;
    }

    public static Figure getFigure(Figures code){
        PairInt center = new PairInt(0,0);
        if(code == Figures.I)return new IFigure(center);
        if(code == Figures.J)return new JFigure(center);
        if(code == Figures.L)return new LFigure(center);
        if(code == Figures.O)return new OFigure(center);
        if(code == Figures.S)return new SFigure(center);
        if(code == Figures.T)return new TFigure(center);
        if(code == Figures.Z)return new ZFigure(center);
        if(code == Figures.B)return new BonusFigure(center);
        if(code == Figures.BB)return new BonusBigFigure(center);
        return null;
    }

    public static Figure getRandomFigure(){
        int code = random.nextInt(9);
        return getFigure(Figures.values()[code]);
    }
}
