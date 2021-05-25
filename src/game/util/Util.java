package game.util;

import game.data.Move;
import game.data.Rotation;

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
}
