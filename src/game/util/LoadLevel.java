package game.util;

import game.data.Block;

public abstract class LoadLevel {
    public abstract Block[][] loadBlocks();
    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getLevel();
    public abstract int getSpeed();
}
