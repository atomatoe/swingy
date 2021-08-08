import Controller.GameController;

public class Program {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Error program arguments! Need argument: console/gui");
        } else {
            GameController game = GameController.getInstance();
            if(args[0].equals("gui"))
                game.stage_main();
            else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                game.stage_main_console();
            }
        }
    }
}