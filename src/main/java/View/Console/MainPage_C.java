package View.Console;

import Controller.GameController;

import java.util.Scanner;

public class MainPage_C {
    private static MainPage_C instance;
    private MainPage_C() { }
    private static int answer;

    public static MainPage_C getInstance() {
        if(instance == null) {
            instance = new MainPage_C();
        }
        return instance;
    }

    public void paint_page() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to Swingy!\n----------\n1) Create hero\n2) Load hero\n3) Exit\n----------\nby atomatoe!");
            try {
                answer = scanner.nextInt();
            } catch (Exception e) {
                GameController.getInstance().error("Error argument!");
            } if(answer == 1) {
                break;
            } else if(answer == 2) {
                break;
            } else if(answer == 3)
                System.exit(0);
            else
                System.out.println("Error argument!");
        }
        if(answer == 1) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            GameController.getInstance().stage_CreateHero_console();
        }
        if(answer == 2) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            GameController.getInstance().stage_LoadHero_console();
        }
    }
}
