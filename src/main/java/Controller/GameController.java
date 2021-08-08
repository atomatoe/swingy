package Controller;

import Model.Artifact;
import Model.Hero;
import Model.Window;
import View.Console.*;
import View.GUI.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;


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
        LoadHeroPage_C loadHeroPage = LoadHeroPage_C.getInstance();
        loadHeroPage.paint_page();
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
        Window frame = Window.getInstance();
        frame.clear_window();
        LoadHeroPage loadHeroPage = LoadHeroPage.getInstance();
        loadHeroPage.paint_page();
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

    public void save_Hero() {
        String fileName = "hero";
        int count = 0;
        while(true) {
            count++;
            File file = new File("./src/main/resources/saves/" + fileName + count);
            if(!file.exists())
                break;
        }
        try(FileWriter writer = new FileWriter("./src/main/resources/saves/" + fileName + count, false))
        {
            writer.write(currentHero.getName() + "\n");
            writer.write(currentHero.getHeroClass() + "\n");
            writer.write(currentHero.getAttack() + "\n");
            writer.write(currentHero.getDefence() + "\n");
            writer.write(currentHero.getHitPoints() + "\n");
            writer.write(currentHero.getLvl() + "\n");
            writer.write(currentHero.getExp() + "\n");
            writer.write(currentHero.getCoordinates_x() + "\n");
            writer.write(currentHero.getCoordinates_y() + "\n");
            writer.write(currentHero.getPhoto() + "\n");
            writer.write(currentHero.getPhoto_face() + "\n");
            writer.write(currentHero.getPhoto_left() + "\n");
            writer.write(currentHero.getPhoto_right() + "\n");
            writer.write(currentHero.getPhoto_behind() + "\n");
            if(currentHero.getAxe() != null)
                writer.write(currentHero.getAxe().getAttack() + "\n");
            else
                writer.write(0 + "\n");
            if(currentHero.getArmor() != null)
                writer.write(currentHero.getArmor().getDefence() + "\n");
            else
                writer.write(0 + "\n");
            if(currentHero.getHelm() != null)
                writer.write(currentHero.getHelm().getHitPoints() + "\n");
            else
                writer.write(0 + "\n");
            writer.write(currentHero.getKilledMonsters() + "\n");
            writer.write(currentHero.getAllSteps() + "\n");
            writer.flush();
            writer.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void loadHero(String filename) {
        try {
            FileReader fr = new FileReader("./src/main/resources/saves/" + filename);
            Scanner scan = new Scanner(fr);

            String heroName = scan.nextLine();
            String heroClass = scan.nextLine();
            int heroAttack = Integer.parseInt(scan.nextLine());
            int heroDefense = Integer.parseInt(scan.nextLine());
            int heroHitPoints = Integer.parseInt(scan.nextLine());
            int heroLvl = Integer.parseInt(scan.nextLine());
            if (heroLvl > 15)
                heroLvl = 0;
            int heroExp = Integer.parseInt(scan.nextLine());
            int heroCoordinatesX = Integer.parseInt(scan.nextLine());
            int heroCoordinatesY = Integer.parseInt(scan.nextLine());
            String heroPhoto = scan.nextLine();
            String heroPhotoFace = scan.nextLine();
            String heroPhotoLeft = scan.nextLine();
            String heroPhotoRight = scan.nextLine();
            String heroPhotoBehind = scan.nextLine();
            int artifactAxe = Integer.parseInt(scan.nextLine());
            int artifactArmor = Integer.parseInt(scan.nextLine());
            int artifactHelm = Integer.parseInt(scan.nextLine());
            int killed_monsters = Integer.parseInt(scan.nextLine());
            int all_steps = Integer.parseInt(scan.nextLine());
            create_currentHero(heroName, heroClass, heroAttack, heroDefense, heroHitPoints, heroPhoto, heroLvl,
                    heroExp, heroCoordinatesX, heroCoordinatesY, heroPhotoLeft, heroPhotoRight, heroPhotoBehind);
            currentHero.load_set(heroPhotoFace, artifactAxe, artifactArmor, artifactHelm, killed_monsters, all_steps);
            fr.close();
        } catch (Exception e) {
            System.out.println("Error read file!");
            System.exit(-1);
        }
    }

    public ArrayList<String> read_directory() {
        File[] files = new File("./src/main/resources/saves/").listFiles();
        ArrayList<String> files_names = new ArrayList<String>();
        if(files.length == 0)
            return null;
        for (int i = 0; i != files.length; i++) {
                files_names.add(files[i].getName());
            }
        Collections.sort(files_names);
        Collections.reverse(files_names);
        return files_names;
    }

    public void stage_LoadHero_DB() {
        Window frame = Window.getInstance();
        frame.clear_window();
        LoadHeroPage_BD loadHeroPageBD = LoadHeroPage_BD.getInstance();
        loadHeroPageBD.paint_page();
    }

    public void stage_LoadHero_noDB() {
        Window frame = Window.getInstance();
        frame.clear_window();
        LoadHeroPage_noBD loadHeroPageNoBD = LoadHeroPage_noBD.getInstance();
        loadHeroPageNoBD.paint_page();
    }

    public void stage_LoadHero_DB_C() {
        LoadHeroPage_BD_C loadHeroPageBD_C = LoadHeroPage_BD_C.getInstance();
        loadHeroPageBD_C.paint_page();
    }

    public void stage_LoadHero_noDB_C() {
        LoadHeroPage_noBD_C loadHeroPage = LoadHeroPage_noBD_C.getInstance();
        loadHeroPage.paint_page();
    }
}
