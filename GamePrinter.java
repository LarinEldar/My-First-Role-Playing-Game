import java.util.List;

class GamePrinter {
    //печатаем результаты боя
    public static void battle(ResultBattle resultBattle){
        System.out.println("!!! " + resultBattle.loser.name + " убит!");
        System.out.println();
        System.out.println("# Вы получаете " + resultBattle.exp + " опыта.");
        System.out.println("# Вы получаете " + resultBattle.loot.name);
        System.out.println();
    }
    //если нет монстра в локации
    public static void noMonsters(){
        System.out.println();
        System.out.println("В данной локации монстра нет.");
        System.out.println();
    }
    //если монстр убит
    public static void isDestroyed(){
        System.out.println();
        System.out.println("Монстр уже убит.");
        System.out.println();
    }
    //результаты атаки
    public static void attack(AttackInfo attackInfo){
        if(attackInfo.isSuccessful){
            if(attackInfo.isCritical)
                System.out.println("+ Критический урон.");
            System.out.println(attackInfo.fighter.name + " наносит " +
                                attackInfo.value + " урона.");
        } else
            System.out.println("- " + attackInfo.fighter.name + " промахнулся.");
        System.out.println("  " + attackInfo.entity.name + " осталось " +
                            attackInfo.entity.getHitpoints() + " хп.");
    }
    //информация о локации
    public static void location(Entity entity){
        Location location = entity.getLocation();
        System.out.println("Текущая локация: " + location.name);
        if(location.getEntity() != null) {
            System.out.println("Монстр локации: ");
            status((Animate) location.getEntity());
        }
        nearbyLoc(entity);
        System.out.println();
    }
    //инвентарь персонажа
    public static void inventory(Hero hero){
        System.out.println("Золото: " + hero.getMoney() + " монет.");
        if(hero.inventory.getItems().size() == 0)
            System.out.println("Инвентарь пуст.");
        System.out.println("0. Снять снаряжение.");
        int i = 1;
        for(Item item : hero.inventory.getItems())
            System.out.println(i++ + ". " + item.name + ".");
        System.out.println("Введите любой символ для выхода.");
    }
    //информация о существе
    public static void status(Animate animate) {
        String firstLine = animate.name + " " + animate.getLevel() + " lvl";
        System.out.println("*".repeat(firstLine.length()));
        System.out.println(firstLine);
        System.out.println("HP " + animate.getHitpoints() + "/" + animate.getMaxHP());
        System.out.print("Опыт: " + animate.getExp());

        if (animate instanceof Hero) {
            System.out.println("/" + animate.getLevel() * animate.getLevel() * 50);
            System.out.println("Золото: " + ((Hero) animate).getMoney());
        } else System.out.println();

        System.out.print("Атака " + animate.getAttack() + "\t");
        System.out.println("Защита " + animate.getDefense());
        System.out.print("Сила  " + animate.getStrength() + "\t");
        System.out.println("Ловкость " + animate.getDexterity());
        System.out.printf("Вероятность крит. удара: %.1f%%\n", animate.probabilityCrtAtk()*100);
        System.out.printf("Вероятность промаха: %.1f%%\n", animate.probabilityMiss()*100);
        equipment(animate);
        System.out.println("*".repeat(firstLine.length()));
    }
    //ближайшие локации к существу
    public static void nearbyLoc(Entity entity){
        System.out.println("Ближайшие локации: ");
        int i = 1;
        for (Location l : entity.getLocation().getLinkedLocations()) {
            System.out.println(" " + i++ + " " + l.name);
        }
    }

    public  static  void gameOver(){
        System.out.println("*********");
        System.out.println("GAME OVER");
        System.out.println("*********");
    }

    public static void exit(){
        System.out.println("Вы действительно хотите завершить игру?\n" +
                "\"Y\" - Да\n" +
                "\"Любой символ\" - Нет");
    }
    //список возможных действий игрока
    public static void actions(){
        int i = 1;
        for (ActionPlayer a : ActionsPlayer.actions) {
            System.out.println(i++ + " " + a.name);
        }
    }
    //список возможных улучшений персонажа
    public static void improve(){
        System.out.println("Выберите улучшение персонажа:");
        int i = 1;
        for (ImproveHero improve : ActionsPlayer.improves){
            System.out.println(" " + i++ + " " +improve.name + " +" + improve.value);
        }
    }

    public static void start(){
        System.out.println("*****************************************");
        System.out.println(" Добро пожаловать в игру!");
        System.out.println(" Введите цифру для выбора действия.");
        System.out.println(" Для выхода из игры введите любой символ");
        System.out.println("*****************************************");
    }
    //список товаров магазина
    public static void goods(List<Item> goods){
        int i = 1;
        for (Item item : goods){
            System.out.printf("%d %-15s Стоимость: %d золота.\n",
                    i++, item.name, Shop.getCost(item));
        }
        System.out.println();
    }
    //снаряжение на существе
    public static void equipment(Animate animate){
        System.out.println("Снаряжение: ");
        if(animate.getWeapon() != null)
            System.out.println(" " + animate.getWeapon().name);
        else System.out.println(" Нет оружия.");

        if(animate.getArmor() != null)
            System.out.println(" " + animate.getArmor().name);
        else System.out.println(" Нет брони.");
    }

    public static void levelUp(){
        System.out.println("************************");
        System.out.println("Достигнут новый уровень!");
        System.out.println("************************");
    }

    public static void hasNotShop(){
        System.out.println("В данной локации нет магазина!");
        System.out.println();
    }
}
