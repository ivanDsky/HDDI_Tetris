package game.data.spells;

import game.data.Field;
import game.data.Figure;
import game.data.Spell;
import game.util.PairInt;

public class SwapFigures extends Spell {
    public SwapFigures(Field field) {
        super(field);
    }

    @Override
    public void apply() {
        Figure prevCurrent = field.getCurrentFigure();
        Figure prevNext = field.getNextFigure();
        PairInt coordinate = prevNext.getCenter();
        prevNext.setCenter(prevCurrent.getCenter());

        if(field.isPlacedGood(prevNext)){
            field.setNextFigure(prevCurrent);
            field.setCurrentFigure(prevNext);
        }else{
            prevNext.setCenter(coordinate);
        }
    }
}
