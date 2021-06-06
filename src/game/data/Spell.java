package game.data;

public abstract class Spell implements SpecialAbility{
    public static int INFINITE = -1;
    protected Field field;
    public int leftSpells;
    public Spell(Field field,int leftSpells){
        this.field = field;
        this.leftSpells = leftSpells;
    }
}
