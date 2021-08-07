package Controller;

import Model.Artifact;
import Model.Hero;
import Model.Window;
import View.Console.Battle_C;
import View.Console.CreateHeroPage_C;
import View.Console.GamePage_C;
import View.Console.MainPage_C;
import View.GUI.Battle;
import View.GUI.CreateHeroPage;
import View.GUI.GamePage;
import View.GUI.MainPage;

import java.util.Random;


public class GameController {

    private static GameController instance;
    private GameController() { }
    private Hero currentHero;

    public static GameController getInstance() {
        if(instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void stage_main_console() {
        MainPage_C mainPage = MainPage_C.getInstance();
        mainPage.paint_page();
    }

    public void stage_CreateHero_console() {
        CreateHeroPage_C createHeroPage = CreateHeroPage_C.getInstance();
        createHeroPage.paint_page();
    }

    public void stage_LoadHero_console() {
//        View.Console.LoadHeroPage loadHeroPage = View.Console.LoadHeroPage.getInstance();
//        loadHeroPage.paint_page();
    }

    public void stage_Battle_console(Hero enemy) {
        Battle_C battlePage = Battle_C.getInstance();
        battlePage.paint_page(enemy, get_winner(enemy));
    }

    public void stage_Game_console() {
        GamePage_C gamePage = GamePage_C.getInstance();
        gamePage.paint_page();
    }

    public void stage_main() {
        MainPage mainPage = MainPage.getInstance();
        mainPage.paint_page();
    }

    public void stage_CreateHero() {
        Window frame = Window.getInstance();
        frame.clear_window();
        CreateHeroPage createHeroPage = CreateHeroPage.getInstance();
        createHeroPage.paint_page();
    }

    public void stage_Game() {
        Window frame = Window.getInstance();
        frame.clear_window();
        GamePage gamePage = GamePage.getInstance();
        gamePage.paint_page();
    }

    public void stage_LoadHero() {

    }

    public void stage_Battle(Hero enemy) {
        Window frame = Window.getInstance();
        frame.clear_window();
        Battle battlePage = Battle.getInstance();
        battlePage.paint_page(enemy, get_winner(enemy));
    }

    private int get_winner(Hero enemy) {
        int attack_bonus = 0;
        int defence_bonus = 0;
        int hp_bonus = 0;
        if(currentHero.getAxe() != null)
            attack_bonus = currentHero.getAxe().getAttack();
        if(currentHero.getArmor() != null)
            defence_bonus = currentHero.getArmor().getDefence();
        if(currentHero.getHelm() != null)
            hp_bonus = currentHero.getHelm().getHitPoints();
        Random rand = new Random();
        int diff = rand.nextInt(1 + 1);
        int hero_hp = currentHero.getHitPoints() + hp_bonus;
        int enemy_hp = enemy.getHitPoints();
        if(diff == 0)
            hero_hp += 5;
        else
            enemy_hp += 5;
        for(; hero_hp > 0 || enemy_hp > 0; enemy_hp -= 3, hero_hp -= 3) {
            if(enemy.getAttack() - (currentHero.getDefence() + defence_bonus) > 0)
                hero_hp -= enemy.getAttack() - currentHero.getDefence();
            if((currentHero.getAttack() + attack_bonus) - enemy.getDefence() > 0)
                enemy_hp -= currentHero.getAttack() - enemy.getDefence();
        }
        if(hero_hp > enemy_hp)
            return(1);
        return(0);
    }

    public void create_currentHero(String name, String heroClass, int attack, int defence, int hp, String photo,
                                   int lvl, int exp, int coordinates_x, int coordinates_y,
                                   String photo_left, String photo_right, String photo_behind) {
        if(currentHero == null)
            currentHero = new Hero(name, heroClass, attack, defence, hp, photo, lvl, exp, coordinates_x, coordinates_y,
                    photo_left, photo_right, photo_behind);
    }

    public Hero getCurrentHero() {
        return currentHero;
    }

    public Artifact generate_artifact(Hero enemy) {
        Random random = new Random();
        Artifact artifact = null;
        int rand = random.nextInt(1 + 1);
        if(rand == 0)
            return null;
        else if(enemy.getLvl() > 1) {
            int artifact_lvl = (enemy.getLvl() / 2) - 1;
            rand = random.nextInt(2 + 1);
            if(rand == 0) {
                if(artifact_lvl == 0) {
                    artifact = new Artifact("./src/main/resources/images/artifacts/Attack/Sword1.png", "Iron Axe", 1, 5, 0, 0);
                } else if(artifact_lvl == 1) {
                    artifact = new Artifact("./src/main/resources/images/artifacts/Attack/Sword2.png", "Axe of power", 1, 10, 0, 0);
                } else if(artifact_lvl >= 2) {
                    artifact = new Artifact("./src/main/resources/images/artifacts/Attack/Sword3.png", "Axe of war", 1, 15, 0, 0);
                }
            } else if(rand == 1) {
                if(artifact_lvl == 0) {
                    artifact = new Artifact("./src/main/resources/images/artifacts/Defence/Armor1.png", "Novice armor", 2, 0, 5, 0);
                } else if(artifact_lvl == 1) {
                    artifact = new Artifact("./src/main/resources/images/artifacts/Defence/Armor2.png", "Protective armor", 2, 0, 10, 0);
                } else if(artifact_lvl >= 2) {
                    artifact = new Artifact("./src/main/resources/images/artifacts/Defence/Armor3.png", "Armor of the gods", 2, 0, 15, 0);
                }
            } else if(rand == 2) {
                if(artifact_lvl == 0) {
                    artifact = new Artifact("./src/main/resources/images/artifacts/HitPoints/Helm1.png", "Iron helm", 3, 0, 0, 5);
                } else if(artifact_lvl == 1) {
                    artifact = new Artifact("./src/main/resources/images/artifacts/HitPoints/Helm2.png", "Dominator", 3, 0, 0, 10);
                } else if(artifact_lvl >= 2) {
                    artifact = new Artifact("./src/main/resources/images/artifacts/HitPoints/Helm3.png", "Helm of legends", 3, 0, 0, 15);
                }
            }
            return artifact;
        }
        return null;
    }

    public void error(String text) {
        System.out.println(text);
        System.exit(-1);
    }
}
