package View.Console;

import Controller.GameController;
import Model.Hero;
import Model.Window;
import View.GUI.GamePage;
import View.GUI.MainPage;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GamePage_C {
    private static GamePage_C instance;

    private GamePage_C() {
    }

    private static ArrayList<Hero> enemy_list;
    private static int first_generate;
    private static int size;

    public static GamePage_C getInstance() {
        if (instance == null) {
            first_generate = 0;
            size = 0;
            enemy_list = new ArrayList<Hero>();
            instance = new GamePage_C();
        }
        return instance;
    }

    public void paint_page() {
        size = (GameController.getInstance().getCurrentHero().getLvl() - 1) * 5 + 10;
        if (first_generate == 0) {
            first_generate = 1;
            for (int i = 0; i < GameController.getInstance().getCurrentHero().getLvl() + 2; i++) {
                Hero enemy = generate_enemy(GameController.getInstance().getCurrentHero().getLvl());
                enemy_list.add(enemy);
            }
        }
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (y == GameController.getInstance().getCurrentHero().getCoordinates_y() &&
                        x == GameController.getInstance().getCurrentHero().getCoordinates_x())
                    System.out.print("H");
                else {
                    Hero enemy = getEnemyToPosition(x, y);
                    if (enemy == null)
                        System.out.print("*");
                    else
                        System.out.print(enemy.getLvl());
                }
            }
            System.out.print("\n");
        }
        System.out.println("Name: " + GameController.getInstance().getCurrentHero().getName() + "\n"
                + "Class: " + GameController.getInstance().getCurrentHero().getHeroClass());
        System.out.println("Lvl: " + GameController.getInstance().getCurrentHero().getLvl() + "\n"
                + "Exp: " + GameController.getInstance().getCurrentHero().getExp());
        if(GameController.getInstance().getCurrentHero().getAxe() != null)
            System.out.println("Attack: " + GameController.getInstance().getCurrentHero().getAttack() + "(+"
                    + GameController.getInstance().getCurrentHero().getAxe().getAttack() + ")");
        else
            System.out.println("Attack: " + GameController.getInstance().getCurrentHero().getAttack());
        if(GameController.getInstance().getCurrentHero().getArmor() != null)
            System.out.println("Defense: " + GameController.getInstance().getCurrentHero().getDefence() + "(+"
                    + GameController.getInstance().getCurrentHero().getArmor().getDefence() + ")");
        else
            System.out.println("Defense: " + GameController.getInstance().getCurrentHero().getArmor() );
        if(GameController.getInstance().getCurrentHero().getHelm() != null)
            System.out.println("HitPoints: " + GameController.getInstance().getCurrentHero().getHitPoints() + "(+"
                    + GameController.getInstance().getCurrentHero().getHelm().getHitPoints() + ")");
        else
            System.out.println("HitPoints: " + GameController.getInstance().getCurrentHero().getHitPoints());
        if(GameController.getInstance().getCurrentHero().getAxe() != null)
            System.out.println("Artifact 1: " + GameController.getInstance().getCurrentHero().getAxe().getName());
        if(GameController.getInstance().getCurrentHero().getArmor() != null)
            System.out.println("Artifact 2: " + GameController.getInstance().getCurrentHero().getArmor().getName());
        if(GameController.getInstance().getCurrentHero().getHelm() != null)
            System.out.println("Artifact 3: " + GameController.getInstance().getCurrentHero().getHelm().getName());
        print_menu();
    }

    private Hero generate_enemy(int lvl) {
        Hero enemy = null;
        Random random = new Random();
        int enemy_x = GameController.getInstance().getCurrentHero().getCoordinates_x();
        int enemy_y = GameController.getInstance().getCurrentHero().getCoordinates_y();
        while (enemy_y == GameController.getInstance().getCurrentHero().getCoordinates_y()
                && enemy_x == GameController.getInstance().getCurrentHero().getCoordinates_x()) {
            enemy_x = random.nextInt(size - 1 + 1);
            enemy_y = random.nextInt(size - 1 + 1);
            if (getEnemyToPosition(enemy_x, enemy_y) != null) {
                enemy_x = random.nextInt(size - 1 + 1);
                enemy_y = random.nextInt(size - 1 + 1);
            }
        }
        int id = random.nextInt(lvl + 1);
        int bonus_lvl = random.nextInt(1 + 1);
        id += bonus_lvl;
        int bonus = 0;
        bonus = random.nextInt(8 + 1);
        bonus += GameController.getInstance().getCurrentHero().getLvl();
        if (GameController.getInstance().getCurrentHero().getLvl() == 0) {
            int diff_neutral = random.nextInt(1 + 1);
            if (diff_neutral == 0)
                enemy = new Hero("Cat", "Neutral", 0, 0, 10,
                        "./src/main/resources/images/Neutral/Cat/face.png", 0, 250, enemy_x, enemy_y,
                        "./src/main/resources/images/Neutral/Cat/left.png",
                        "./src/main/resources/images/Neutral/Cat/right.png",
                        "./src/main/resources/images/Neutral/Cat/behind.png");
            else
                enemy = new Hero("Dog", "Neutral", 0, 0, 10,
                        "./src/main/resources/images/Neutral/Dog/face.png", 0, 250, enemy_x, enemy_y,
                        "./src/main/resources/images/Neutral/Dog/left.png",
                        "./src/main/resources/images/Neutral/Dog/right.png",
                        "./src/main/resources/images/Neutral/Dog/behind.png");
        } else if (id <= 1)
            enemy = new Hero("Gumanoid", "Enemy (1lvl)", 5 + bonus, 5 + bonus, 10 + bonus,
                    "./src/main/resources/images/enemy/Enemy1/face.png", 1, 500, enemy_x, enemy_y,
                    "./src/main/resources/images/enemy/Enemy1/left.png",
                    "./src/main/resources/images/enemy/Enemy1/right.png",
                    "./src/main/resources/images/enemy/Enemy1/behind.png");
        else if (id == 2)
            enemy = new Hero("Evil gumanoid", "Enemy (2lvl)", 10 + bonus, 5 + bonus, 10 + bonus,
                    "./src/main/resources/images/enemy/Enemy2/face.png", 2, 750, enemy_x, enemy_y,
                    "./src/main/resources/images/enemy/Enemy2/left.png",
                    "./src/main/resources/images/enemy/Enemy2/right.png",
                    "./src/main/resources/images/enemy/Enemy2/behind.png");
        else if (id == 3)
            enemy = new Hero("Skeleton", "Enemy (3lvl)", 10 + bonus, 10 + bonus, 10 + bonus,
                    "./src/main/resources/images/enemy/Enemy3/face.png", 3, 1000, enemy_x, enemy_y,
                    "./src/main/resources/images/enemy/Enemy3/left.png",
                    "./src/main/resources/images/enemy/Enemy3/right.png",
                    "./src/main/resources/images/enemy/Enemy3/behind.png");
        else if (id == 4)
            enemy = new Hero("Dark skeleton", "Enemy (4lvl)", 10 + bonus, 10 + bonus, 15 + bonus,
                    "./src/main/resources/images/enemy/Enemy4/face.png", 4, 1250, enemy_x, enemy_y,
                    "./src/main/resources/images/enemy/Enemy4/left.png",
                    "./src/main/resources/images/enemy/Enemy4/right.png",
                    "./src/main/resources/images/enemy/Enemy4/behind.png");
        else if (id == 5)
            enemy = new Hero("Ghost", "Enemy (5lvl)", 5 + bonus, 15 + bonus, 20 + bonus,
                    "./src/main/resources/images/enemy/Enemy5/face.png", 5, 1500, enemy_x, enemy_y,
                    "./src/main/resources/images/enemy/Enemy5/left.png",
                    "./src/main/resources/images/enemy/Enemy5/right.png",
                    "./src/main/resources/images/enemy/Enemy5/behind.png");
        else if (id == 6)
            enemy = new Hero("Dark mage", "Enemy (6lvl)", 25 + bonus, 10 + bonus, 15 + bonus,
                    "./src/main/resources/images/enemy/Enemy6/face.png", 6, 1750, enemy_x, enemy_y,
                    "./src/main/resources/images/enemy/Enemy6/left.png",
                    "./src/main/resources/images/enemy/Enemy6/right.png",
                    "./src/main/resources/images/enemy/Enemy6/behind.png");
        else if (id == 7)
            enemy = new Hero("Mister pumpkin", "Enemy (7lvl)", 25 + bonus, 20 + bonus, 25 + bonus,
                    "./src/main/resources/images/enemy/Enemy7/face.png", 7, 2000, enemy_x, enemy_y,
                    "./src/main/resources/images/enemy/Enemy7/left.png",
                    "./src/main/resources/images/enemy/Enemy7/right.png",
                    "./src/main/resources/images/enemy/Enemy7/behind.png");
        else
            enemy = new Hero("Dark assasin", "Enemy (8lvl)", 30 + bonus, 15 + bonus, 40 + bonus,
                    "./src/main/resources/images/enemy/Enemy8/face.png", 8, 2500, enemy_x, enemy_y,
                    "./src/main/resources/images/enemy/Enemy8/left.png",
                    "./src/main/resources/images/enemy/Enemy8/right.png",
                    "./src/main/resources/images/enemy/Enemy8/behind.png");
        return (enemy);
    }

    private Hero getEnemyToPosition(int enemy_x, int enemy_y) {
        for (int i = 0; i != enemy_list.size(); i++) {
            if (enemy_x == enemy_list.get(i).getCoordinates_x() && enemy_y == enemy_list.get(i).getCoordinates_y())
                return (enemy_list.get(i));
        }
        return (null);
    }

    private void print_menu() {
        Scanner scanner = new Scanner(System.in);
        Hero enemy = null;
        int answer = 0;
        while (true) {
            System.out.println("1) Left");
            System.out.println("2) Right");
            System.out.println("3) Up");
            System.out.println("4) Down");
            System.out.println("5) Save");
            System.out.println("6) Go to GUI");
            System.out.println("7) Exit");
            try {
                answer = scanner.nextInt();
            } catch (Exception e) {
                GameController.getInstance().error("Error argument!");
            } if(answer > 0 && answer < 5) {
                int key = 0;
                if(answer == 1)
                    key = KeyEvent.VK_A;
                else if(answer == 2)
                    key = KeyEvent.VK_D;
                else if(answer == 3)
                    key = KeyEvent.VK_W;
                else if(answer == 4)
                    key = KeyEvent.VK_S;
                GameController.getInstance().getCurrentHero().moving(key, size);
                enemy_move();
                enemy = getEnemyToPosition(GameController.getInstance().getCurrentHero().getCoordinates_x(),
                        GameController.getInstance().getCurrentHero().getCoordinates_y());
                break;
            } else if(answer == 5) {
                break;
            } else if(answer == 6) {
                break;
            } else if(answer == 7) {
                System.exit(0);
            } else {
                System.out.println("Error argument!");
            }
        }
        if(answer == 5) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Hero saved!");
            GameController.getInstance().save_Hero();
            paint_page();
        } else if(answer > 0 && answer < 5) {
            if(enemy != null) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                GameController.getInstance().stage_Battle_console(enemy);
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                paint_page();
            }
        } else if(answer == 6) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            GameController.getInstance().stage_main();
            GameController.getInstance().stage_CreateHero();
            GamePage gamePage = GamePage.getInstance();
            gamePage.from_console(enemy_list);
            GameController.getInstance().stage_Game();
        }
    }

    private void enemy_move() {
        Random rand = new Random();
        for(int i = 0; i != enemy_list.size(); i++) {
            int random = rand.nextInt(3 + 1);
            if(!enemy_list.get(i).check_hero()) {
                if (random == 0)
                    enemy_list.get(i).moving(KeyEvent.VK_W, size);
                else if (random == 1)
                    enemy_list.get(i).moving(KeyEvent.VK_S, size);
                else if (random == 2)
                    enemy_list.get(i).moving(KeyEvent.VK_D, size);
                else if (random == 3)
                    enemy_list.get(i).moving(KeyEvent.VK_A, size);
            }
        }
    }
    public void delete_enemy(int x, int y) {
        for(int i = 0; i != enemy_list.size(); i++) {
            if(x == enemy_list.get(i).getCoordinates_x() &&
                    y == enemy_list.get(i).getCoordinates_y()) {
                enemy_list.remove(i);
                if(enemy_list.isEmpty())
                    first_generate = 0;
                return;
            }
        }
    }
}
