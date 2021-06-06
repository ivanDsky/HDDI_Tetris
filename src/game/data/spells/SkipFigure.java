package game.data.spells;

import game.data.Field;
import game.data.Spell;

public class SkipFigure extends Spell {
    public SkipFigure(Field field, int leftSpells) {
        super(field,leftSpells);
    }


    @Override
    public void apply() {
        if (leftSpells == INFINITE || leftSpells > 0) {
            field.skipMove();
            if (leftSpells > 0) leftSpells--;
        }
    }
}
