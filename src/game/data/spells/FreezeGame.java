package game.data.spells;

import game.data.Field;
import game.data.Spell;
import game.util.Timer;

public class FreezeGame extends Spell {
    public FreezeGame(Field field,int leftSpells) {
        super(field,leftSpells);
    }
    private boolean isActive = false;

    @Override
    public void apply() {
        if(leftSpells == Spell.INFINITE || leftSpells > 0) {
            if(isActive)return;
            leftSpells--;
            isActive = true;
            field.gamePause *= 2;
            new Timer(10000) {
                private boolean first = true;

                @Override
                public void handle() {
                    if (first) {
                        first = false;
                        return;
                    }
                    field.gamePause /= 2;
                    isActive = false;
                    this.stop();
                }
            }.start();
        }
    }
}
