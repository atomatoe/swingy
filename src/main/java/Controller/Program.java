package Controller;

public class Program {
    public static void main(String[] args){
        GameController game = GameController.getInstance();
        game.stage_main();
    }
}


/*
        frame = new JFrame("TEST");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);

        GridBagConstraints constraints = new GridBagConstraints();
        JPanel contentPane = new JPanel(new GridBagLayout());

        JPanel pinkPanel = new JPanel();
        pinkPanel.setBackground(Color.PINK);

        JPanel blue = new JPanel();
        blue.setBackground(Color.blue);

        JPanel red = new JPanel();
        red.setBackground(Color.red);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.weighty = 0.65;
        constraints.weightx = 0.65;
        contentPane.add(red, constraints);
        constraints.gridx = 2;
        constraints.weighty = 0.35;
        constraints.weightx = 0.35;
        contentPane.add(blue, constraints);

        frame.setContentPane(contentPane);
        frame.setVisible(true);
 */
