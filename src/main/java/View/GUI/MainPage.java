package View.GUI;

import Controller.GameController;
import Model.Window;

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
        frame.create_background("./src/main/resources/images/pages/mainPage.png");

        JPanel button_panel = new JPanel();
        button_panel.setOpaque(false); // убираем белый цвет на jpanel кнопок
        button_panel.setLayout(new FlowLayout());

        JButton createHero = frame.create_button("Create Hero", 16);
        JButton loadHero = frame.create_button("Load Hero", 16);
        JButton exit = frame.create_button("Exit", 16);

        createHero.setActionCommand("Create hero");
        loadHero.setActionCommand("Load hero");

        createHero.addActionListener(new ButtonClickListener());
        loadHero.addActionListener(new ButtonClickListener());
        exit.addActionListener(new ButtonClickListener());

        button_panel.add(createHero);
        button_panel.add(loadHero);
        button_panel.add(exit);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.weighty = 5;
        c.fill = GridBagConstraints.VERTICAL;
        JLabel text = frame.create_text("Welcome to Swingy!", 22);
        frame.getMainFrame().add(text, c);

        c.weighty = 1;
        frame.getMainFrame().add(button_panel, c);

        c.weighty = 5;
        text = frame.create_text("by atomatoe!", 22);
        frame.getMainFrame().add(text, c);

        frame.getMainFrame().setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("Create hero")) {
                GameController.getInstance().stage_CreateHero();
            } else if (command.equals("Load hero")) {
                GameController.getInstance().stage_LoadHero();
            } else {
                System.exit(0);
            }
        }
    }
}
