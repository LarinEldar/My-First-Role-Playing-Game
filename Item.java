import java.util.Objects;

public abstract class Item {
    public final String name;

    public Item(String name){
        this.name = name;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null)
            return false;
        if(o instanceof Item) {
            Item item = (Item) o;
            return name.equals(((Item) o).name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public abstract Item clone();
}
