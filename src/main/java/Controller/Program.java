package Controller;

public class Program {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Error program arguments! Need argument: console/gui");
        } else {
            GameController game = GameController.getInstance();
            if(args[0].equals("gui"))
                game.stage_main();
            else
                game.stage_main_console();
        }
    }
}