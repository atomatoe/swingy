package View.Console;

import Controller.GameController;
import Model.Artifact;
import Model.Hero;
import View.GUI.GamePage;

import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Scanner;

public class Battle_C {
    private static Battle_C instance;
    private static int winner;
    private static Hero enemy;

    public static Battle_C getInstance() {
        if (instance == null) {
            winner = 0;
            instance = new Battle_C();
        }
        return instance;
    }

    public void paint_page(Hero enemy, int winner) {
        Scanner scanner = new Scanner(System.in);
        int answer = 0;
        Battle_C.winner = winner;
        Battle_C.enemy = enemy;

        System.out.println("--- Battle ---");
        System.out.println("Name: " + GameController.getInstance().getCurrentHero().getName() + " / " + enemy.getName());
        System.out.println("Lvl: " + GameController.getInstance().getCurrentHero().getLvl() + " / " + enemy.getLvl());
        if(GameController.getInstance().getCurrentHero().getAxe() != null)
            System.out.println("Attack: " + GameController.getInstance().getCurrentHero().getAttack() + "(+"
                    + GameController.getInstance().getCurrentHero().getAxe().getAttack() + ")" + " / " + enemy.getAttack());
        else
            System.out.println("Attack: " + GameController.getInstance().getCurrentHero().getAttack() + " / " + enemy.getAttack());
        if(GameController.getInstance().getCurrentHero().getArmor() != null)
            System.out.println("Defence: " + GameController.getInstance().getCurrentHero().getDefence() + "(+"
                    + GameController.getInstance().getCurrentHero().getArmor().getDefence() + ")" + " / " + enemy.getDefence());
        else
            System.out.println("Defence: " + GameController.getInstance().getCurrentHero().getDefence() + " / " + enemy.getDefence());
        if(GameController.getInstance().getCurrentHero().getHelm() != null)
            System.out.println("HitPoints: " + GameController.getInstance().getCurrentHero().getHitPoints() + "(+"
                    + GameController.getInstance().getCurrentHero().getHelm().getHitPoints() + ")" + " / " + enemy.getHitPoints());
        else
            System.out.println("HitPoints: " + GameController.getInstance().getCurrentHero().getHitPoints() + " / " + enemy.getHitPoints());
        System.out.println("--------------");
        while (true) {
            System.out.println("1) Battle");
            System.out.println("2) Run");
            try {
                answer = scanner.nextInt();
            } catch (Exception e) {
                GameController.getInstance().error("Error argument!");
            } if(answer == 1) {
                if(winner == 1) {
                    print_HeroWin();
                } else {
                    print_HeroLose();
                }
            } else if(answer == 2) {
                hero_run();
            } else
                System.out.println("Error arguments!");
        }
    }

    private void hero_run() {
        Random random = new Random();
        int rand = random.nextInt(1 + 1);
        if(rand == 1) {
            if(winner == 1) {
                print_HeroWin();
            } else {
                print_HeroLose();
            }
        } else {
            if (rand == 0)
                GameController.getInstance().getCurrentHero().moving(KeyEvent.VK_W, GamePage.getSize());
            else if (rand == 1)
                GameController.getInstance().getCurrentHero().moving(KeyEvent.VK_S, GamePage.getSize());
            else if (rand == 2)
                GameController.getInstance().getCurrentHero().moving(KeyEvent.VK_D, GamePage.getSize());
            else if (rand == 3)
                GameController.getInstance().getCurrentHero().moving(KeyEvent.VK_A, GamePage.getSize());
            GamePage_C.getInstance().paint_page();
        }
    }

    public void print_HeroWin() {
        Scanner scanner = new Scanner(System.in);
        int answer = 0;
        System.out.println("Winner!");
        System.out.println("Name: " + GameController.getInstance().getCurrentHero().getName());
        System.out.println("Class: " + GameController.getInstance().getCurrentHero().getHeroClass());
        System.out.println("Attack: " + GameController.getInstance().getCurrentHero().getAttack());
        System.out.println("Defence: " + GameController.getInstance().getCurrentHero().getDefence());
        System.out.println("HitPoints: " + GameController.getInstance().getCurrentHero().getHitPoints());
        System.out.println("Lvl: " + GameController.getInstance().getCurrentHero().getLvl());
        System.out.println("Exp: " + GameController.getInstance().getCurrentHero().getExp() + " (+" + enemy.getExp() + ")");
        Artifact artifact = GameController.getInstance().generate_artifact(enemy);
        if(artifact != null) {
            System.out.println("New artifact: " + artifact.getName());
        } else {
            System.out.println("No award");
        }
        GameController.getInstance().getCurrentHero().upgrade(enemy, artifact);
        while(true) {
            System.out.println("1) Finish");
            try {
                answer = scanner.nextInt();
            } catch (Exception e) {
                GameController.getInstance().error("Error argument!");
            } if(answer == 1) {
                GamePage_C.getInstance().delete_enemy(GameController.getInstance().getCurrentHero().getCoordinates_x(),
                        GameController.getInstance().getCurrentHero().getCoordinates_y());
                GamePage_C.getInstance().paint_page();
            } else
                System.out.println("Error argument!");
        }
    }

    public void print_HeroLose() {

        Scanner scanner = new Scanner(System.in);
        int answer = 0;
        System.out.println("You lose :(");
        System.out.println("Name: " + GameController.getInstance().getCurrentHero().getName());
        System.out.println("Class: " + GameController.getInstance().getCurrentHero().getHeroClass());
        System.out.println("Attack: " + GameController.getInstance().getCurrentHero().getAttack());
        System.out.println("Defence: " + GameController.getInstance().getCurrentHero().getDefence());
        System.out.println("HitPoints: " + GameController.getInstance().getCurrentHero().getHitPoints());
        System.out.println("Lvl: " + GameController.getInstance().getCurrentHero().getLvl());
        System.out.println("Exp: " + GameController.getInstance().getCurrentHero().getExp());
        if(GameController.getInstance().getCurrentHero().getAxe() != null) {
            System.out.println("Attack-artifact: " + GameController.getInstance().getCurrentHero().getAxe().getName());
        } else {
            System.out.println("Attack-artifact: empty");
        }
        if(GameController.getInstance().getCurrentHero().getArmor() != null) {
            System.out.println("Defense-artifact: " + GameController.getInstance().getCurrentHero().getArmor().getName());
        } else {
            System.out.println("Defense-artifact: empty");
        }
        if(GameController.getInstance().getCurrentHero().getHelm() != null) {
            System.out.println("HitPoints-artifact: " + GameController.getInstance().getCurrentHero().getHelm().getName());
        } else {
            System.out.println("HitPoints-artifact: empty");
        }
        System.out.println("Monster killed: " + GameController.getInstance().getCurrentHero().getKilledMonsters());
        System.out.println("Steps: " + GameController.getInstance().getCurrentHero().getAllSteps());

        while(true) {
            System.out.println("1) Exit");
            try {
                answer = scanner.nextInt();
            } catch (Exception e) {
                GameController.getInstance().error("Error argument!");
            } if(answer == 1) {
                System.exit(0);
            } else
                System.out.println("Error argument!");
        }
    }

    private String generate_space(String text) {
        String spaces = "";
        for(int i = 0; i != text.length(); i++) {
            spaces += " ";
        }
        return spaces;
    }
}
