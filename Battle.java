/**
 * Класс для выполнения боя
 */
public class Battle {

    public static Entity fight(Fighter fighter, Entity entity){
        GamePrinter.attack(fighter.attack(entity));
        if(entity.isDestroyed()){
            return entity;
        } else if (entity instanceof Fighter){
            return fight((Fighter) entity, (Entity) fighter);
        }
        return fight(fighter, entity);
    }

    public static ResultBattle startBattle(Fighter fighter, Entity entity){
        Entity loser = fight(fighter, entity);
        return new ResultBattle(loser.drop(), loser.getExp(), loser);
    }

}
