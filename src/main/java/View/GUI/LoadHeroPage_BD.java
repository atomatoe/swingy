package View.GUI;

import Controller.DateBaseController;
import Controller.GameController;
import Model.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoadHeroPage_BD {

    private static LoadHeroPage_BD instance;
    private LoadHeroPage_BD() { }
    private static DateBaseController dateBaseController;

    public static LoadHeroPage_BD getInstance() {
        if(instance == null) {
            instance = new LoadHeroPage_BD();
            dateBaseController = DateBaseController.getInstance();
        }
        return instance;
    }

    public void paint_page() {
        Window frame = Window.getInstance();
        frame.create_background("./src/main/resources/images/pages/loadHeroPage.jpeg");

        JPanel button_panel = new JPanel();
        button_panel.setOpaque(false); // убираем белый цвет на jpanel кнопок
        button_panel.setLayout(new FlowLayout());

        JButton back = frame.create_button("Back", 16);

        back.setActionCommand("Back");

        back.addActionListener(new LoadHeroPage_BD.ButtonClickListener());

        button_panel.add(back);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.weighty = 5;
        c.fill = GridBagConstraints.VERTICAL;
        JLabel text = frame.create_text("Welcome to Swingy!", 22);
        frame.getMainFrame().add(text, c);

        int max_count = 0;
        ResultSet resultSet = dateBaseController.executeQuery("SELECT * FROM heroes");
        try {
            while (resultSet.next())
                max_count++;
            for(int tmp = max_count; max_count != tmp - 10; max_count--) {
                if(max_count == 0)
                    break;
                ResultSet test = dateBaseController.executeQuery("SELECT * from heroes where id=" + max_count);
                while (test.next()) {
                    JButton loadHero = frame.create_button("Hero" + max_count, 16);
                    loadHero.setActionCommand(String.valueOf(max_count));
                    loadHero.addActionListener(new LoadHeroPage_BD.ButtonClickListener());
                    frame.getMainFrame().add(loadHero, c);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL error in LoadHeroPage_BD");
        }
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
            if (command.equals("Back")) {
                Window frame = Window.getInstance();
                frame.clear_window();
                GameController.getInstance().stage_LoadHero();
            } else {
                GameController.getInstance().loadHero_DB(command);
                GameController.getInstance().stage_Game();
            }
        }
    }
}
