package View.Console;

import Controller.DateBaseController;
import Controller.GameController;
import View.GUI.LoadHeroPage_BD;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadHeroPage_BD_C {
    private static LoadHeroPage_BD_C instance;
    private LoadHeroPage_BD_C() { }
    private static int answer;
    private static DateBaseController dateBaseController;

    public static LoadHeroPage_BD_C getInstance() {
        if(instance == null) {
            instance = new LoadHeroPage_BD_C();
            dateBaseController = DateBaseController.getInstance();
        }
        return instance;
    }

    public void paint_page() {
        Scanner scanner = new Scanner(System.in);
        int max_count = 0;
        while (true) {
            max_count = 0;
            System.out.println("Welcome to Swingy!\n----------");
            ResultSet resultSet = dateBaseController.executeQuery("SELECT * FROM heroes");
            try {
                while (resultSet.next())
                    max_count++;
                if(max_count != 0) {
                    for(int tmp = max_count - 10; tmp != max_count + 1; tmp++) {
                        if(tmp <= 0)
                            tmp = 1;
                        System.out.println(tmp + ") hero" + tmp);
                    }
                }
            } catch (SQLException e) {
                System.out.println("SQL error in LoadHeroPage_BD");
            }
            System.out.println((max_count + 1) + ") Back");
            System.out.println("----------\nby atomatoe!");
            try {
                answer = scanner.nextInt();
            } catch (Exception e) {
                GameController.getInstance().error("Error argument!");
            } if(answer == max_count + 1) {
                break;
            } else if(answer > 0 && answer <= max_count && answer >= max_count - 10) {
                break;
            } else
                System.out.println("Error argument!");
        }
        if(answer == (max_count + 1)) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            GameController.getInstance().stage_LoadHero_console();
        } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            GameController.getInstance().loadHero_DB(String.valueOf(answer));
            GameController.getInstance().stage_Game_console();
        }
    }
}
