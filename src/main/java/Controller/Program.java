package Controller;

import test.MainWindow;

public class Program {
    public static void main(String[] args){
        GameController game = GameController.getInstance();
        game.stage_main();
    }
}