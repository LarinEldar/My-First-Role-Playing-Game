import java.util.ArrayList;
import java.util.List;
//инвентарь персонажа или скорее сумка персонажа
public class Inventory {
    private final List<Item> items;

    public Inventory(){
        items = new ArrayList<>();
    }

    public void put(Item item){
        if(item != null)
            items.add(item);
    }

    public Item take(int i){
        return items.remove(i);
    }

    public List<Item> getItems(){
        return new ArrayList<>(items);
    }
}
