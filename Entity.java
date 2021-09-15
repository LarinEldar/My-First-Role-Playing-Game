/**
 * Класс для любых сущностей, которые могут быть разрушены или убиты
 */
public abstract class Entity {
    public final String name;
    private int hitPoints;
    private Location location;
    private int baseDefense;
    private int maxHP;
    private boolean isDestroyed;

    public Entity(String name, int hp, int def, Location loc){
        this.name = name;
        maxHP = hitPoints = hp;
        baseDefense = def;
        isDestroyed = false;
        location = loc;
        loc.setEntity(this); //при создании локации на ней еще нет существ
                            //при создании сущности помещаем ее в локацию
                            //и указываем локации о появлении в ней существа
    }

    public int damage(int attack){
        //getDefense используется для получения полной защиты, необходимо для подклассов
        int tmp = (int)(attack * 1000d / (getDefense() + 1000d) );
        hitPoints -= tmp;
        if(hitPoints <= 0) {
            isDestroyed = true;
            hitPoints = 0;
        }
        return tmp;
    }

    public int getDefense() {
        //метод нужен для подклассов, для получения значения полной защиты
        //или пришлось бы переопределить метод damage
        return baseDefense;
    }

    public int getBaseDefense(){
        return baseDefense;
    }

    public void setBaseDefense(int defense) {
        this.baseDefense = defense;
    }

    public int getHitpoints() {
        return hitPoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitPoints = hitpoints;
    }

    public int getMaxHP(){
        return maxHP;
    }

    public void setMaxHP(int hp){
        maxHP = hp;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public abstract Item drop();

    public abstract int getExp();

    public void entityResurrection(){
        isDestroyed = false;
        hitPoints = maxHP;
    }
}
