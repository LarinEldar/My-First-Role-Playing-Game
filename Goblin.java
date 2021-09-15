public class Goblin extends Animate {
    static final int xHP = 800; //множители для создания монстра (умножаются на уровень)
    static final int xAtk = 2;
    static final int xDef = 8;
    static final int xStr = 2;
    static final int xDex = 5;
    static final int xExp = 50;

    public Goblin(int lvl, Location loc){
        super("Goblin", lvl, xHP*lvl,
                xAtk*lvl, xDef*lvl, xStr*lvl, xDex*lvl,
                Weapon.getKnife(), null, loc);
    }

    public int getExp(){
        return getLevel()*xExp;
    }

}
