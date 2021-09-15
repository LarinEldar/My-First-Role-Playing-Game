import java.util.Scanner;

public class Main {
    public static final Hero hero;
    static final Scanner in = new Scanner(System.in);

    static{
        Location startLoc = new Location("Белый сад", true);
        new Goblin(1, startLoc);

        Location darkForest = new Location("Темный лес", false);
        new Skeleton(2, darkForest);
        startLoc.addLocation(darkForest);

        Location cemetery = new Location("Кладбище", false);
        new Zombie(3, cemetery);
        startLoc.addLocation(cemetery);

        Location gondor = new Location("Гондор", true);
        darkForest.addLocation(gondor);
        cemetery.addLocation(gondor);

        Location mordor = new Location("Мордор", false);
        new Ork(4, mordor);
        gondor.addLocation(mordor);
        darkForest.addLocation(mordor);

        System.out.println("Введите имя персонажа.");
        String name = in.nextLine();
        hero = new Hero(name, startLoc);
        hero.inventory.put(Weapon.getKnife());
        hero.inventory.put(Armor.getLight());
        hero.inventory.put(new Potion());
    }

    public static void main(String[] arg) {
        GamePrinter.start();
        while (true) {
            GamePrinter.actions();
            int i = 0;
            try {
                i = in.nextInt();
            } catch (Exception e) {
                in.nextLine();
                exit();
                continue;
            }

            if(i > 0 && i <= ActionsPlayer.actions.size())
                ActionsPlayer.actions.get(--i).action.action(hero);
            else {
                in.nextLine();
                exit();
            }
        }
    }

    public static void exit(){
        if(hero.isDestroyed()) {
            GamePrinter.gameOver();
            System.exit(0);
        }
        GamePrinter.exit();
        if(in.nextLine().toLowerCase().equals("y"))
            System.exit(0);

    }
}


