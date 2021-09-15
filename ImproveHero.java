public abstract class ImproveHero {
    public final String name;
    public final int value;

    public ImproveHero(String name, int value){
        this.name = name;
        this.value = value;
    }

    public abstract void improve(Hero hero);
}
