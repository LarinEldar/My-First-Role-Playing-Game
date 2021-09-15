public class Potion extends Item{
    public static final double healing = 0.15; //кол-во восстанавливаемых ХП, 15% от maxHP

    public Potion(){
        super("Potion");
    }

    public void effectPotion(Animate animate){
        int tmpHP = animate.getHitpoints() + (int)(animate.getMaxHP() * healing);
        if(tmpHP < animate.getMaxHP())
            animate.setHitpoints(tmpHP);
        else animate.setHitpoints(animate.getMaxHP());
    }

    public Potion clone(){
        return new Potion();
    }
}
