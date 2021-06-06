package game.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.data.Block;

import java.io.File;

public class FileLoadLevel extends LoadLevel{
    private Level level;

    public FileLoadLevel(File file) {
        try {
            level = new ObjectMapper().readValue(file, Level.class);
        }catch (Exception ignored){}
    }

    public FileLoadLevel(String path){
        this(new File(path));
    }

    @Override
    public Block[][] loadBlocks() {
        Block[][] ret = new Block[level.width][level.height];
        for(int i = 0;i < level.width; ++i){
            for(int j = 0;j < level.height; ++j){
                ret[i][j] = BlockExchanger.getBlock(level.field[j][i]);
                ret[i][j].setXY(i,j);
            }
        }
        return ret;
    }

    @Override
    public int getWidth() {
        return level.width;
    }

    @Override
    public int getHeight() {
        return level.height;
    }

    @Override
    public int getLevel() {
        return level.number;
    }

    @Override
    public int getSpeed() {
        return level.speed;
    }
}
