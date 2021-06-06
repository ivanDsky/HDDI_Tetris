package game.util;

import game.data.Block;

public abstract class LoadLevel {
    public abstract Block[][] loadBlocks();
    public abstract Level getLevel();
}
