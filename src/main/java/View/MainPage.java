package View;

import Controller.GameController;
import Model.Window;
import test.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage {

    private static MainPage instance;
    private MainPage() { }

    public static MainPage getInstance() {
        if(instance == null) {
            instance = new MainPage();
        }
        return instance;
    }

    public void paint_page() {
        Window frame = Window.getInstance();
        frame.getMainFrame().setLayout(new GridBagLayout());

        JPanel button_panel = new JPanel();
        button_panel.setLayout(new FlowLayout());

        JButton createHero = new JButton("Create Hero");
        JButton loadHero = new JButton("Load Hero");
        JButton exit = new JButton("Exit");
        createHero.setActionCommand("Create hero");
        loadHero.setActionCommand("Load hero");

        createHero.addActionListener(new ButtonClickListener());
        loadHero.addActionListener(new ButtonClickListener());
        exit.addActionListener(new ButtonClickListener());

        ImageIcon mainImg = new ImageIcon("test.png");
        JLabel img_label = new JLabel(mainImg);

        button_panel.add(createHero);
        button_panel.add(loadHero);
        button_panel.add(exit);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridheight = 2;
        c.weighty = 1;
        c.weightx = 0;
        c.fill = GridBagConstraints.VERTICAL;
        frame.getMainFrame().add(new JLabel("Welcome to Swingy!", JLabel.CENTER ), c);
//        c.gridx = 1;
//        c.gridheight = 2;
//        c.weighty = 1;
//        c.weightx = 0;
//        c.fill = GridBagConstraints.VERTICAL;
//        frame.getMainFrame().add(img_label, c);
        c.gridx = 1;
        c.gridheight = 2;
        c.weighty = 1;
        c.weightx = 0;
        c.fill = GridBagConstraints.VERTICAL;
        frame.getMainFrame().add(button_panel, c);
        frame.getMainFrame().setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("Create hero")) {
                System.out.println("CREATE HERO");
                GameController.getInstance().stage_CreateHero();
            } else if (command.equals("Load hero")) {
                System.out.println("LOAD HERO");
                GameController.getInstance().stage_LoadHero();
            } else {
                System.exit(0);
            }
        }
    }
}
