/**
 * Класс для одушевленных существ игры
 */
public abstract class Animate extends Entity implements Fighter{
    private int strength;
    private int dexterity;
    private int baseAttack; //базовое значение атаки (влияющее только на атаку,
                        // сила например могла бы влиять и на другие параметры существа
                        // в этой игре можно было бы обойтись и без этого поля
    private Weapon weapon;
    private Armor armor;
    private int level;

    public Animate(String name, int lvl, int hp, int atk, int def, int str,
                   int dex, Weapon weapon, Armor armor, Location loc){
        super(name, hp, def, loc);
        level = lvl;
        strength = str;
        dexterity = dex;
        baseAttack = atk;
        this.weapon = weapon;
        this.armor = armor;
    }

    @Override
    public AttackInfo attack(Entity entity){
        boolean isSuccessful = true;
        boolean isCritical = false;
        int tmpAtk = 0;
        if(probabilityMiss() > Math.random())
            isSuccessful = false;
        else {
            final double spreadAtk = 0.15; //разброс по атаке +-15%
            tmpAtk = (int)(getAttack() * (1 + (2 * Math.random() - 1) * spreadAtk)); //атаку умножаем на 0.85...1.15
            if(probabilityCrtAtk() > Math.random()) {
                isCritical = true;
                tmpAtk *= 3;
            }
            tmpAtk = entity.damage(tmpAtk); //здесь tmpAtk принимает значение нанесенного урона
        }

        return new AttackInfo(isSuccessful, isCritical, tmpAtk, this, entity);
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getAttack() {
        if(weapon == null)
            return baseAttack * strength;
        return baseAttack * strength * weapon.attack;
    }

    public int getBaseAtk(){
        return baseAttack;
    }

    public void setBaseAttack(int attack) {
        this.baseAttack = attack;
    }

    @Override
    public int getDefense(){
        if(armor == null)
            return getBaseDefense();
        return getBaseDefense() * armor.defense;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public int getLevel() {
        return level;
    }

    public void levelUp(){
        level++;
    }

    public Item drop(){
        switch ((int)(Math.random() * 5)){
            case 0:
                return weapon == null ? drop() :  weapon.clone();
            case 1:
                return armor == null ? drop() : armor.clone();
            default:
                return new Potion();
        }
    }

    public abstract int getExp();

    public double probabilityCrtAtk(){   //вероятность критической атаки
        //в зависимости от ловкости меняется от 10% до 90%
        return 0.8 * (1 - 50d / (50d + dexterity)) + 0.1;
    }

    public double probabilityMiss(){ //вероятность промаха
        //может принимать значения от 5% до 25%
        return 0.2 * 50 / (50d + dexterity * level) + 0.05;
    }

}
