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
            System.out.println("Welcome to Swingy!\n----------\n1) Create hero\n2) Load hero\n----------\nby atomatoe!");
            try {
                answer = scanner.nextInt();
            } catch (Exception e) {
                GameController.getInstance().error("Error argument!");
            } if(answer == 1) {
                GameController.getInstance().stage_CreateHero_console();
            } else if(answer == 2) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                GameController.getInstance().stage_LoadHero_console();
            } else
                System.out.println("Error argument!");
        }
    }
}
