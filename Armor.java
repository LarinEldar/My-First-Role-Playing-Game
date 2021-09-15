public class Armor extends Item{
    public final int defense;

    private Armor(String name, int defense){
        super(name);
        this.defense = defense;
    }

    public static Armor getLight(){
        return new Armor("Light Armor", 25);
    }
    public static Armor getMedium(){
       return new Armor("Medium Armor", 50);
    }
    public static Armor getHeavy(){
        return new Armor("Heavy Armor", 100);
    }

    public Armor clone(){
        return new Armor(name, defense);
    }
}
