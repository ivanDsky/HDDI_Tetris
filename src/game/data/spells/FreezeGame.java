package game.data.spells;

import game.data.Field;
import game.data.Spell;
import game.util.Timer;

public class FreezeGame extends Spell {
    public FreezeGame(Field field) {
        super(field);
    }

    @Override
    public void apply() {
        field.gamePause *= 2;
        new Timer(10000){
            private boolean first = true;
            @Override
            public void handle() {
                if(first){first = false;return;}
                field.gamePause /= 2;
                this.stop();
            }
        }.start();
    }
}
