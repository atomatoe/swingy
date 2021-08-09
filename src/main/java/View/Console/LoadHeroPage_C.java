package View.Console;

import Controller.DateBaseController;
import Controller.GameController;

import java.util.ArrayList;
import java.util.Scanner;

public class LoadHeroPage_C {
    private static LoadHeroPage_C instance;
    private LoadHeroPage_C() { }
    private static int answer;

    public static LoadHeroPage_C getInstance() {
        if(instance == null) {
            instance = new LoadHeroPage_C();
        }
        return instance;
    }

    public void paint_page() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to Swingy!\n----------");
            System.out.println("1) Back\n2) Load hero from DB\n3) Load hero from saves");
            System.out.println("----------\nby atomatoe!");
            try {
                answer = scanner.nextInt();
            } catch (Exception e) {
                GameController.getInstance().error("Error argument!");
            } if(answer > 0 && answer < 4) {
                break;
            } else
                System.out.println("Error argument!");
        }
        if(answer == 1) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            GameController.getInstance().stage_main_console();
        } else if(answer == 2) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            if(!DateBaseController.getInstance().isConnection_error())
                GameController.getInstance().stage_LoadHero_DB_C();
            else {
                System.out.println("Connection to database error!");
                GameController.getInstance().stage_main_console();
            }
        } else if(answer == 3) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            GameController.getInstance().stage_LoadHero_noDB_C();
        }
    }
}
