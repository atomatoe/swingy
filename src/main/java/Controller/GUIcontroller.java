package Controller;

import View.CreateHero;
import View.MainWindow;

import javax.swing.*;

public class GUIcontroller {

    private String stage;
    private static GUIcontroller instance;
    private GUIcontroller() { stage = "Start"; }

    public static GUIcontroller getInstance() {
        if(instance == null) {
            instance = new GUIcontroller();
        }
        return instance;
    }


    public void stage_CreateHero(JFrame mainFrame) {
        CreateHero window = new CreateHero(mainFrame);
        window.showEventDemo();
    }

    public void stage_LoadHero() {

    }
}
