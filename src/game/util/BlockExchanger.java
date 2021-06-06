package game.util;

import game.data.Block;
import game.data.blocks.*;

public class BlockExchanger {
    public static char getLetter(Block block){
        boolean isBlocked = block instanceof BlockedBlock;
        char ret = ' ';
        if(block instanceof EmptyBlock)return ' ';
        if(isBlocked)block = block.removeBlock(null);
        if(block instanceof DeleteBlock)ret = 'x';
        if(block instanceof DynamiteBlock)ret = 'd';
        if(block instanceof RocketBlock)ret = 'r';
        if(block instanceof BombBlock)ret = 'b';
        if(isBlocked)ret = Character.toUpperCase(ret);
        return ret;
    }

    public static Block getBlock(char letter){
        boolean isBlocked = Character.isUpperCase(letter);
        letter = Character.toLowerCase(letter);
        Block block;
        if(letter == 'x')block = new DeleteBlock(0,0);else
        if(letter == 'd')block = new DynamiteBlock(0,0);else
        if(letter == 'r')block = new RocketBlock(0,0);else
        if(letter == 'b')block = new BombBlock(0,0);else
        return new EmptyBlock(0,0);
        if(isBlocked)block = new BlockedBlock(0,0,block);
        return block;
    }
}

