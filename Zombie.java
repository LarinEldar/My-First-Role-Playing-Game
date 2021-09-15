public class Zombie extends Animate {
    static final int xHP = 1800;
    static final int xAtk = 3;
    static final int xDef = 50;
    static final int xStr = 4;
    static final int xDex = 1;
    static final int xExp = 70;

    public Zombie(int lvl, Location loc){
        super("Zombie", lvl, xHP*lvl,
                xAtk*lvl, xDef*lvl, xStr*lvl, xDex*lvl,
                null , Armor.getMedium(), loc);
    }

    public int getExp(){
        return getLevel()*xExp;
    }

}
