/**
 * Класс хранящий в себе информацию об атаке
 */
public class AttackInfo {
    public final boolean isCritical; //true если был критический удар
    public final boolean isSuccessful; //false если промах
    public final int value; //значение атаки
    public final Animate fighter; //атакующий
    public final Entity entity;  //принимающий удар

    public AttackInfo(boolean isSuccessful, boolean isCritical, int value,
                      Animate fighter, Entity entity){
        this.isSuccessful = isSuccessful;
        this.isCritical = isCritical;
        this.value = value;
        this.fighter = fighter;
        this.entity = entity;
    }
}
