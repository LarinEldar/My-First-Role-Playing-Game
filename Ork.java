public class Ork extends Animate {
    static final int xHP = 2000;
    static final int xAtk = 3;
    static final int xDef = 10;
    static final int xStr = 4;
    static final int xDex = 1;
    static final int xExp = 150;
    public Ork(int lvl, Location loc){
        super("Ork", lvl, xHP*lvl,
                xAtk*lvl, xDef*lvl, xStr*lvl, xDex*lvl,
                Weapon.getLongSword(), Armor.getHeavy(), loc);
    }

    public int getExp(){
        return getLevel()*xExp;
    }

}
