package Model;

import Controller.GameController;
import View.GUI.GamePage;

import java.awt.event.KeyEvent;

public class Hero {
    private String photo;
    private String photo_face;
    private String photo_left;
    private String photo_right;
    private String photo_behind;
    private String name;
    private String HeroClass;
    private int lvl;
    private int exp;
    private int attack;
    private int defence;
    private int hitPoints;
    private int coordinates_x;
    private int coordinates_y;
    private Artifact axe;
    private Artifact armor;
    private Artifact helm;
    private int all_steps;
    private int kill_monsters;

    private Hero() { }
    public Hero(String name, String HeroClass, int attack, int defence, int hp, String photo, int lvl,
                int exp, int coordinates_x, int coordinates_y, String photo_left, String photo_right, String photo_behind) {
        this.name = name;
        this.HeroClass = HeroClass;
        this.attack = attack;
        this.defence = defence;
        this.hitPoints = hp;
        this.lvl = lvl;
        this.exp = exp;
        this.photo = photo;
        this.coordinates_x = coordinates_x;
        this.coordinates_y = coordinates_y;
        this.photo_left =  photo_left;
        this.photo_right = photo_right;
        this.photo_behind = photo_behind;
        this.photo_face = photo;
        this.all_steps = 0;
        this.kill_monsters = 0;
    }

    public void equip_artifact(Artifact artifact) {
        if(artifact.getInventory() == 1)
            this.axe = artifact;
        else if(artifact.getInventory() == 2)
            this.armor = artifact;
        else if(artifact.getInventory() == 3)
            this.helm = artifact;
        else
            System.out.println("Error equip artifact!");
    }

    public void unequip_artifact(int id) {
        if(id == 1)
            this.axe = null;
        else if(id == 2)
            this.armor = null;
        else if(id == 3)
            this.helm = null;
        else
            System.out.println("Error unequip artifact!");
    }

    public void moving(int code, int size) {
        if (code == KeyEvent.VK_W) {
            if((coordinates_y - 1) >= 0) {
                if(HeroClass.contains("Enemy") || HeroClass.contains("Neutral")) {
                    if(GamePage.getInstance().getEnemyToPosition(coordinates_x, coordinates_y - 1) != null)
                        return;
                }
                coordinates_y--;
                all_steps++;
            }
            photo = photo_behind;
        }
        if (code == KeyEvent.VK_S) {
            if((coordinates_y + 1) <= (size - 1)) {
                if(HeroClass.contains("Enemy") || HeroClass.contains("Neutral")) {
                    if(GamePage.getInstance().getEnemyToPosition(coordinates_x, coordinates_y + 1) != null)
                        return;
                }
                coordinates_y++;
                all_steps++;
            }
            photo = photo_face;
        }
        if (code == KeyEvent.VK_D) {
            if((coordinates_x + 1) <= (size - 1)) {
                if(HeroClass.contains("Enemy") || HeroClass.contains("Neutral")) {
                    if(GamePage.getInstance().getEnemyToPosition(coordinates_x + 1, coordinates_y) != null) {
                        return;
                    }
                }
                coordinates_x++;
                all_steps++;
            }
            photo = photo_right;
        }
        if (code == KeyEvent.VK_A) {
            if(((coordinates_x - 1) >= 0)) {
                if(HeroClass.contains("Enemy") || HeroClass.contains("Neutral")) {
                    if(GamePage.getInstance().getEnemyToPosition(coordinates_x - 1, coordinates_y) != null)
                        return;
                }
                coordinates_x--;
                all_steps++;
            }
            photo = photo_left;
        }
    }

    public void upgrade(Hero enemy, Artifact artifact) {
        kill_monsters++;
        exp += enemy.getExp();
        int need_exp = lvl * 1000 + (int)Math.pow(lvl - 1, 2) * 450;
        if(exp >= need_exp) {
            lvl++;
            hitPoints += 3;
            attack += 3;
            defence += 3;
        }
        if(artifact != null) {
            if(artifact.getInventory() == 1) {
                if(axe != null) {
                    if (artifact.getAttack() > axe.getAttack()) {
                        unequip_artifact(1);
                        equip_artifact(artifact);
                    }
                } else
                    equip_artifact(artifact);
            } else if(artifact.getInventory() == 2) {
                if(armor != null) {
                    if (artifact.getDefence() > armor.getDefence()) {
                        unequip_artifact(2);
                        equip_artifact(artifact);
                    }
                } else
                    equip_artifact(artifact);
            } else if(artifact.getInventory() == 3) {
                if(helm != null) {
                    if (artifact.getHitPoints() > helm.getHitPoints()) {
                        unequip_artifact(3);
                        equip_artifact(artifact);
                    }
                } else
                    equip_artifact(artifact);
            }
        }
    }

    public boolean check_hero() {
        if(coordinates_x == GameController.getInstance().getCurrentHero().getCoordinates_x() &&
            coordinates_y == GameController.getInstance().getCurrentHero().getCoordinates_y())
            return true;
        return false;
    }

    public String getPhoto() { return photo; }
    public String getName() { return name; }
    public String getHeroClass() { return HeroClass; }
    public int getLvl() { return lvl; }
    public int getExp() { return exp; }
    public int getAttack() { return attack; }
    public int getDefence() { return defence; }
    public int getHitPoints() { return hitPoints; }
    public int getCoordinates_x() { return coordinates_x; }
    public int getCoordinates_y() { return coordinates_y; }
    public Artifact getArmor() {
        return armor;
    }
    public Artifact getAxe() {
        return axe;
    }
    public Artifact getHelm() {
        return helm;
    }
    public String getPhoto_behind() {
        return photo_behind;
    }
    public String getPhoto_left() {
        return photo_left;
    }
    public String getPhoto_right() {
        return photo_right;
    }
    public String getPhoto_face() {
        return photo_face;
    }
    public int getAllSteps() { return all_steps; }
    public int getKilledMonsters() { return kill_monsters; }
}
