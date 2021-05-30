package game.data;

public abstract class Spell implements SpecialAbility{
    protected Field field;
    public Spell(Field field){
        this.field = field;
    }
}
