package View.Console;

import Controller.GameController;

import java.util.ArrayList;
import java.util.Scanner;

public class LoadHeroPage_noBD_C {
    private static LoadHeroPage_noBD_C instance;
    private LoadHeroPage_noBD_C() { }
    private static int answer;
    private static String current_file;

    public static LoadHeroPage_noBD_C getInstance() {
        if(instance == null) {
            instance = new LoadHeroPage_noBD_C();
        }
        return instance;
    }

    public void paint_page() {
        Scanner scanner = new Scanner(System.in);
        int max_save = 10;
        ArrayList<String> files = null;
        while (true) {
            System.out.println("Welcome to Swingy!\n----------");
            int i = 1;
            files = GameController.getInstance().read_directory();
            if(files == null) {
                System.out.println("Save file not found!");
            } else {
                if(max_save > files.size())
                    max_save = files.size();
                for(; i != max_save + 1; i++) { // показываем последние 10 сохранений
                    System.out.println(i + ") " + files.get(i - 1));
                }
                System.out.println(i + ") Back");
            }
            System.out.println("----------\nby atomatoe!");
            try {
                answer = scanner.nextInt();
            } catch (Exception e) {
                GameController.getInstance().error("Error argument!");
            } if(answer == max_save + 1) {
                break;
            } else if(!files.isEmpty()) {
                if (answer >= 1 && answer < max_save + 1)
                    break;
                else
                    System.out.println("Error argument!");
            } else
                System.out.println("Error argument!");
        }
        if(answer == max_save + 1) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            GameController.getInstance().stage_LoadHero_console();
        } else if(!files.isEmpty()) {
            if (answer >= 1 && answer < max_save + 1) {
                GameController.getInstance().loadHero(files.get(answer - 1));
                System.out.print("\033[H\033[2J");
                System.out.flush();
                GameController.getInstance().stage_Game_console();
            } else
                System.out.println("Error argument!");
        }
    }
}
