/**
 * класс содержащий статичные объекты с возможными действиями игрока
 * также содержит возможные улучшения персонажа при достижении нового уровня
 */

import java.util.*;
public class ActionsPlayer {
    private static final Scanner in = new Scanner(System.in);
    public static final List<ActionPlayer> actions = new ArrayList<>();
    public static final List<ImproveHero> improves = new ArrayList<>();

    //действия игрока
    // (реализовано с помощью интерфейса, только для закрепления материала по лямюда-функциям)
    static {
        actions.add(new ActionPlayer("Атаковать монстра", hero->{
            if(hero.getLocation().entity == null) {
                GamePrinter.noMonsters();
                return;
            }

            if(hero.getLocation().entity.isDestroyed()) {
                GamePrinter.isDestroyed();
                return;
            }

            ResultBattle resultBattle = Battle.startBattle(hero, hero.getLocation().entity);
            if (resultBattle.loser != hero) {
                GamePrinter.battle(resultBattle);
                hero.inventory.put(resultBattle.loot);
                hero.setExp(hero.getExp() + resultBattle.exp);
                handlerExp(hero);
                entityResurrection(resultBattle.loser);
            } else Main.exit();
        }));

        actions.add(new ActionPlayer("Переместиться в другую локацию", hero->{
            GamePrinter.nearbyLoc(hero);
            int i = 0;
            try {
                i = in.nextInt();
            } catch (Exception e) {
                return;
            }
            if (i >= 1 && i <= hero.getLocation().getLinkedLocations().size()) {
                hero.setLocation(hero.getLocation().getLinkedLocations().get(--i));
            }
        }));

        actions.add(new ActionPlayer("Статус персонажа", hero-> {
            GamePrinter.status(hero);
        }));

        actions.add(new ActionPlayer("Инвентарь", ActionsPlayer::inventory));

        actions.add(new ActionPlayer("Информация о локации", hero-> GamePrinter.location(hero)));

        actions.add(new ActionPlayer("В магазин", hero-> {
            if(hero.getLocation().hasShop())
                Shop.intoShop(hero);
            else GamePrinter.hasNotShop();
        }));
    }

    //улучшения персонажа
    static {
        improves.add(new ImproveHero("Сила", 3) {
            @Override
            public void improve(Hero hero) {
                hero.setStrength(hero.getStrength() + value);
            }
        });
        improves.add(new ImproveHero("Ловкость", 6) {
            @Override
            public void improve(Hero hero) {
                hero.setDexterity(hero.getDexterity() + value);
            }
        });
        improves.add(new ImproveHero("ХП", 1000) {
            @Override
            public void improve(Hero hero) {
                hero.setMaxHP(hero.getMaxHP() + value);
                hero.setHitpoints(hero.getHitpoints() + value);
            }
        });
    }

    private static void inventory (Hero hero){
        Optional<Item> optionalItem = selectItem(hero);

        if(optionalItem == null) {
            return;
        }

        if(optionalItem.isEmpty()){
            inventory(hero);
            return;
        }

        Item item = optionalItem.get();
        switch (item.getClass().getName()) {
                case "Weapon":
                    if(hero.getWeapon() != null)
                        hero.inventory.put(hero.getWeapon());
                    hero.setWeapon((Weapon) item);
                    break;
                case "Armor":
                    if(hero.getArmor() != null)
                        hero.inventory.put(hero.getArmor());
                    hero.setArmor((Armor) item);
                    break;
                case "Potion":
                    ((Potion) item).effectPotion(hero);
                    break;
            }
        inventory(hero);
        }

    public static Optional<Item> selectItem(Hero hero){
        /*
        метод для выбора предмета в инвентаре
        возможны 3 варианта выбора:
            игрок выбрал предмет - возвращаем его в "оболочке"
            игрок пожелал вернуться назад - возвращаем null
            игрок пожелал снять снаряжение - возвращаем Optional.empty
         */
        GamePrinter.equipment(hero);
        GamePrinter.inventory(hero);
        int i = 0;
        try {
            i = in.nextInt();
        } catch (Exception e) {
            in.nextLine();
            return null;
        }

        if(i == 0){
            if(hero.getWeapon() != null){
                hero.inventory.put(hero.getWeapon());
                hero.setWeapon(null);
            }
            if(hero.getArmor() != null){
                hero.inventory.put(hero.getArmor());
                hero.setArmor(null);
            }
            return Optional.empty();
        }

        if (i >= 1 && i <= hero.inventory.getItems().size()) {
            return Optional.of(hero.inventory.take(--i));
        } else {
            return null;
        }
    }

    private static void selectImprove (){
        //предлагаем игроку выбрать улучшение для персонажа
        GamePrinter.improve();
        int i = 0;
        try {
            i = new Scanner(System.in).nextInt();
            if (i < 1 || i > ActionsPlayer.improves.size())
                throw new Exception();
        } catch (Exception e) {
            selectImprove();
            return;
        }
        improves.get(--i).improve(Main.hero);
    }

    private static void handlerExp(Hero hero){
        // в цикле проверяем достижение нового уровня
        // и выполняем необходимые действия
        while (hero.getLevel() * hero.getLevel() * 50 <= hero.getExp()) {
            hero.levelUp();
            GamePrinter.levelUp();
            selectImprove();
        }
    }

    private static void entityResurrection(Entity entity){
        //воскрешаем монстра через 5с.
        new Thread(()->{
            try {
                Thread.sleep(5000);
                entity.entityResurrection();
            } catch (InterruptedException e) {

            }
        }).start();
    }

}
