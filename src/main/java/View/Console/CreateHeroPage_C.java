package View.Console;

import Controller.GameController;
import Model.Hero;

import java.util.ArrayList;
import java.util.Scanner;

public class CreateHeroPage_C {
    private static CreateHeroPage_C instance;
    private static ArrayList<Hero> hero_list;
    private static int current_hero;
    private CreateHeroPage_C() { }
    private static int answer;

    public static CreateHeroPage_C getInstance() {
        if(instance == null) {
            instance = new CreateHeroPage_C();
            Hero hero1 = new Hero("Trios", "Warrior", 15, 10, 5,
                    "./src/main/resources/images/heroes/Warrior/face.png", 0, 0, 0, 0,
                    "./src/main/resources/images/heroes/Warrior/left.png",
                    "./src/main/resources/images/heroes/Warrior/right.png",
                    "./src/main/resources/images/heroes/Warrior/behind.png");
            Hero hero2 = new Hero("Maven", "Rogue", 20, 5, 5,
                    "./src/main/resources/images/heroes/Rogue/face.png", 0, 0, 0, 0,
                    "./src/main/resources/images/heroes/Rogue/left.png",
                    "./src/main/resources/images/heroes/Rogue/right.png",
                    "./src/main/resources/images/heroes/Rogue/behind.png");
            Hero hero3 = new Hero("Alex", "Mage", 15, 5, 10,
                    "./src/main/resources/images/heroes/Mage/face.png", 0, 0, 0, 0,
                    "./src/main/resources/images/heroes/Mage/left.png",
                    "./src/main/resources/images/heroes/Mage/right.png",
                    "./src/main/resources/images/heroes/Mage/behind.png");
            Hero hero4 = new Hero("Storm", "Paladin", 5, 15, 10,
                    "./src/main/resources/images/heroes/Paladin/face.png", 0, 0, 0, 0,
                    "./src/main/resources/images/heroes/Paladin/left.png",
                    "./src/main/resources/images/heroes/Paladin/right.png",
                    "./src/main/resources/images/heroes/Paladin/behind.png");
            Hero hero5 = new Hero("Tremor", "Druid", 5, 5, 20,
                    "./src/main/resources/images/heroes/Druid/face.png", 0, 0, 0, 0,
                    "./src/main/resources/images/heroes/Druid/left.png",
                    "./src/main/resources/images/heroes/Druid/right.png",
                    "./src/main/resources/images/heroes/Druid/behind.png");
            hero_list = new ArrayList<Hero>();
            hero_list.add(hero1);
            hero_list.add(hero2);
            hero_list.add(hero3);
            hero_list.add(hero4);
            hero_list.add(hero5);
            current_hero = 0;
        }
        return instance;
    }

    public void paint_page() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("--- Create hero ---");
            System.out.println("Name: " + hero_list.get(current_hero).getName());
            System.out.println("Class: " + hero_list.get(current_hero).getHeroClass());
            System.out.println("Attack: " + hero_list.get(current_hero).getAttack());
            System.out.println("Defense: " + hero_list.get(current_hero).getDefence());
            System.out.println("HitPoints: " + + hero_list.get(current_hero).getDefence());
            System.out.println("-------------------");
            System.out.println("1) Prev hero\n2) Next hero\n3) Create\n4) Back");
            try {
                answer = scanner.nextInt();
            } catch (Exception e) {
                GameController.getInstance().error("Error argument!");
            } if(answer == 1) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                if(current_hero == 0)
                    current_hero = (hero_list.size() - 1);
                else
                    current_hero--;
            } else if(answer == 2) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                if(current_hero == (hero_list.size() - 1))
                    current_hero = 0;
                else
                    current_hero++;
            } else if(answer == 3) {
                int coordinates_start = (0 - 1) * 5 + 10;
                coordinates_start /= 2;
                GameController.getInstance().create_currentHero(hero_list.get(current_hero).getName(),
                            hero_list.get(current_hero).getHeroClass(),hero_list.get(current_hero).getAttack(),
                            hero_list.get(current_hero).getDefence(), hero_list.get(current_hero).getHitPoints(),
                            hero_list.get(current_hero).getPhoto(), hero_list.get(current_hero).getLvl(),
                            hero_list.get(current_hero).getExp(), coordinates_start , coordinates_start,
                            hero_list.get(current_hero).getPhoto_left(), hero_list.get(current_hero).getPhoto_right(),
                            hero_list.get(current_hero).getPhoto_behind());
                break;
            } else if(answer == 4) {
                break;
            } else
                System.out.println("Error argument!");
        }
        if(answer == 3) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            GameController.getInstance().stage_Game_console();
        } else if(answer == 4) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            GameController.getInstance().stage_main_console();
        }
    }
}
