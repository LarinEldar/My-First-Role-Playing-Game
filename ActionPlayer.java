/**
 * Класс для различных действий игрока
 * имеет два поля название действия и
 * обьект с единственным методом, выполняющим действие
 */
public class ActionPlayer{
    public final Action action;
    public final String name;

    public ActionPlayer(String name, Action action){
        this.action = action;
        this.name = name;
    }
}
