public class Weapon extends Item{
    public final int attack;

    private Weapon(String name, int attack){
        super(name);
        this.attack = attack;
    }

    public static Weapon getKnife(){
        return new Weapon("Knife", 6);
    }
    public static Weapon getSword(){
        return new Weapon("Sword", 12);
    }
    public static Weapon getLongSword(){
        return new Weapon("LongSword", 24);
    }

    public Weapon clone(){
        return new Weapon(name, attack);
    }
}
