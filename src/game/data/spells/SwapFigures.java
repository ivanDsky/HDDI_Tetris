package game.data.spells;

import game.data.Field;
import game.data.Figure;
import game.data.Spell;
import game.util.PairInt;

public class SwapFigures extends Spell {
    public SwapFigures(Field field,int leftSpells) {
        super(field,leftSpells);
    }

    @Override
    public void apply() {
        if(leftSpells == Spell.INFINITE || leftSpells > 0) {
            if(leftSpells > 0)leftSpells--;
            Figure prevCurrent = field.getCurrentFigure();
            Figure prevNext = field.getNextFigure();
            PairInt coordinate = prevNext.getCenter();
            prevNext.setCenter(prevCurrent.getCenter());

            if (field.isPlacedGood(prevNext)) {
                field.setNextFigure(prevCurrent);
                field.setCurrentFigure(prevNext);
            } else {
                prevNext.setCenter(coordinate);
            }
        }
    }
}
