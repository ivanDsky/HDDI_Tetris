package game.data.spells;

import game.data.Field;
import game.data.Spell;

public class SkipFigure extends Spell {
    public SkipFigure(Field field) {
        super(field);
    }

    @Override
    public void apply() {
        field.skipMove();
    }
}
