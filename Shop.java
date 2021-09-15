import java.util.*;

public class Shop {
    private static final Map<Item, Integer> goods = new LinkedHashMap<>();
    private static final Scanner in = new Scanner(System.in);

    static{
        goods.put(new Potion(), 10);
        goods.put(Weapon.getKnife(), 15);
        goods.put(Weapon.getSword(), 30);
        goods.put(Weapon.getLongSword(), 50);
        goods.put(Armor.getLight(), 15);
        goods.put(Armor.getMedium(), 30);
        goods.put(Armor.getHeavy(), 50);
    }

    public static void intoShop(Hero hero){
        System.out.println("1. Купить\n" +
                           "2. Продать\n" +
                           "Введите любой символ для выхода. ");
        int i = 0;
        try{
            i = in.nextInt();
        } catch (Exception e) {
            in.nextLine();
            intoShop(hero);
        }
            switch (i) {
                case 1:
                    buy(hero);
                    break;
                case 2:
                    sell(hero);
                    break;
                default:
                    return;
            }

    }

    private static void buy(Hero hero){
        Item item = selectGoods();
        if(item == null) {
            intoShop(hero);
            return;
        }
        if(hero.getMoney() >= goods.get(item)){
            hero.setMoney(hero.getMoney() - goods.get(item));
            hero.inventory.put(item.clone());
        } else System.out.println("Недостаточно средств!");
        buy(hero);
    }

    private static Item selectGoods() {
        List<Item> listGoods = new ArrayList<>(goods.keySet());
        GamePrinter.goods(listGoods);
        int i = 0;
        try {
            i = in.nextInt();
        } catch (Exception e) {
            in.nextLine();
            selectGoods();
        }
        if (i >= 1 && i <= listGoods.size()) {
            return listGoods.get(--i);
        } else return null;
    }

    private static void sell(Hero hero){
        Optional<Item> optionalItem = ActionsPlayer.selectItem(hero);
        if(optionalItem == null){
            intoShop(hero);
            return;
        }
        if(optionalItem.isEmpty()){
            sell(hero);
            return;
        }
        hero.setMoney(hero.getMoney() + goods.get(optionalItem.get()));
        sell(hero);
    }

    public static int getCost(Item item){
        if(item != null){
            return goods.get(item);
        } else return -1;
    }
}
