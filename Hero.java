//герой, персонаж игрока
public class Hero extends Animate {
    private int money;
    public final Inventory inventory;
    private int exp;

    public Hero(String name, Location loc){
        super(name, 1, 800, 3, 8, 3, 3, null, null,
                new Location("", false));
        this.setLocation(loc);
        money = 0;
        inventory = new Inventory();
        exp = 0;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void levelUp(){
        super.levelUp();
        setMaxHP(getMaxHP() + 800);
        setHitpoints(getMaxHP());
        setBaseDefense(getBaseDefense() + 4);
        setBaseAttack(getBaseAtk() + 3);
    }

}
