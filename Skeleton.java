public class Skeleton extends Animate {
        static final int xHP = 750;
        static final int xAtk = 3;
        static final int xDef = 8;
        static final int xStr = 3;
        static final int xDex = 2;
        static final int xExp = 70;
    public Skeleton(int lvl, Location loc){
        super("Skeleton", lvl, xHP*lvl,
                xAtk*lvl, xDef*lvl, xStr*lvl, xDex*lvl,
                Weapon.getSword(), null, loc);
    }

    public int getExp(){
       return getLevel()*xExp;
    }

}
