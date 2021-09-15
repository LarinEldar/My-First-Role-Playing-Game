import java.util.*;

public class Location {
    public final String name;
    private final List<Location> linkedLocations;
    public Entity entity;
    public final boolean hasShop;

    public Location(String name, boolean hasShop){
        this.name = name;
        this.hasShop = hasShop;
        linkedLocations = new ArrayList<>();
    }

    public void addLocation(Location location){
        for(Location l : linkedLocations){
            if(l == location ) return;
        }
        this.linkedLocations.add(location);
        location.linkedLocations.add(this);
    }

    public List<Location> getLinkedLocations(){
        //возвращаем копию, что бы нельзя было добавить элементы в список (только через метод addLocation)
        return new ArrayList<>(linkedLocations);
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public  boolean hasShop(){
        return hasShop;
    }

}
