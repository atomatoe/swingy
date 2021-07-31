package Controller;

import Model.Hero;
import Model.Window;
import View.CreateHeroPage;
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
        Window frame = Window.getInstance();
        frame.clear_window();
        CreateHeroPage createHeroPage = CreateHeroPage.getInstance();
        createHeroPage.paint_page();
    }

    public void stage_LoadHero() {
    }
}
