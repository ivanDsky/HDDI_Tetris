package game.util;

import game.data.Block;
import game.data.blocks.*;

public class BlockExchanger {
    public static char getLetter(Block block){
        if(block instanceof EmptyBlock)return ' ';
        if(block instanceof BlockedBlock)return '#';
        if(block instanceof DynamiteBlock)return '/';
        if(block instanceof RocketBlock)return '!';
        if(block instanceof BombBlock)return '0';
        else return ' ';
    }

    public static Block getBlock(char letter){
        if(letter == ' ')return new EmptyBlock(0,0);
        if(letter == '#')return new BlockedBlock(0,0,new DeleteBlock(0,0));
        if(letter == '/')return new DynamiteBlock(0,0);
        if(letter == '!')return new RocketBlock(0,0);
        if(letter == '0')return new BombBlock(0,0);
        else return new EmptyBlock(0,0);
    }
}

