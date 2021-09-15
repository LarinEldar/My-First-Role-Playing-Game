/**
 * В класс записываются результаты боя.
 */
public class ResultBattle {
    public final Item loot; //добыча с монстра
    public final int exp; //опыт за бой
    public final Entity loser; //проигравший

    public ResultBattle(Item loot, int exp, Entity loser){
        this.loot = loot;
        this.loser = loser;
        this.exp = exp;
    }

}
