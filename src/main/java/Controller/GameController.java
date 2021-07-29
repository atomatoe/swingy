package Controller;

import Model.Window;
import View.MainPage;

import javax.swing.*;


public class GameController {

    private static GameController instance;
    private GameController() { }

    public static GameController getInstance() {
        if(instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void stage_main() {
        MainPage mainPage = MainPage.getInstance();
        mainPage.paint_page();
    }

    public void stage_CreateHero() {
    }

    public void stage_LoadHero() {
    }
}
