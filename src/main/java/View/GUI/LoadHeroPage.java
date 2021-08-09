package View.GUI;

import Controller.DateBaseController;
import Controller.GameController;
import Model.Window;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoadHeroPage {

    private static LoadHeroPage instance;
    private LoadHeroPage() { }
    private static String current_file;

    public static LoadHeroPage getInstance() {
        if(instance == null) {
            instance = new LoadHeroPage();
        }
        return instance;
    }

    public void paint_page() {
        Window frame = Window.getInstance();
        frame.create_background("./src/main/resources/images/pages/loadHeroPage.jpeg");

        JButton back = frame.create_button("Back", 16);
        back.setActionCommand("Back");
        back.addActionListener(new LoadHeroPage.ButtonClickListener());

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.weighty = 5;
        c.fill = GridBagConstraints.VERTICAL;
        JLabel text = frame.create_text("Welcome to Swingy!", 22);
        frame.getMainFrame().add(text, c);

        JPanel button_panel_np = new JPanel();
        button_panel_np.setOpaque(false); // убираем белый цвет на jpanel кнопок
        button_panel_np.setLayout(new FlowLayout());

        if(!DateBaseController.getInstance().isConnection_error()) {
            JButton button_database = frame.create_button("From database", 16);
            button_database.setActionCommand("From database");
            button_database.addActionListener(new LoadHeroPage.ButtonClickListener());
            button_panel_np.add(button_database);
        }
        else {
            JButton button_database = frame.create_button("Database connection error", 16);
            button_panel_np.add(button_database);
        }

        JButton button_saves = frame.create_button("From saves", 16);
        button_saves.setActionCommand("From saves");
        button_saves.addActionListener(new LoadHeroPage.ButtonClickListener());
        button_panel_np.add(button_saves);

        c.weighty = 1;
        frame.getMainFrame().add(button_panel_np, c);

        frame.getMainFrame().add(back, c);

        c.weighty = 5;
        text = frame.create_text("by atomatoe!", 22);
        frame.getMainFrame().add(text, c);

        frame.getMainFrame().setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("From database")) {
                if(!DateBaseController.getInstance().isConnection_error())
                    GameController.getInstance().stage_LoadHero_DB();
                else
                    System.out.println("Connection to database error!");
            } else if (command.equals("From saves")) {
                GameController.getInstance().stage_LoadHero_noDB();
            } else if (command.equals("Back")) {
                GameController.getInstance().stage_main();
            } else {
                System.exit(0);
            }
        }
    }
}
