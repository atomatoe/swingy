package View.GUI;

import Controller.GameController;
import Model.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoadHeroPage_noBD {

    private static LoadHeroPage_noBD instance;
    private LoadHeroPage_noBD() { }

    public static LoadHeroPage_noBD getInstance() {
        if(instance == null) {
            instance = new LoadHeroPage_noBD();
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

        back.addActionListener(new LoadHeroPage_noBD.ButtonClickListener());

        button_panel.add(back);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.weighty = 5;
        c.fill = GridBagConstraints.VERTICAL;
        JLabel text = frame.create_text("Welcome to Swingy!", 22);
        frame.getMainFrame().add(text, c);

        ArrayList<String> files = GameController.getInstance().read_directory();
        if(files == null) {
            c.weighty = 5;
            text = frame.create_text("Save file not found!", 22);
            frame.getMainFrame().add(text, c);
        } else {
            int max_count = files.size();
            System.out.println("files.size() = " + files.size());
            for(int tmp = max_count; max_count != tmp - 10; max_count--) {
                if (max_count == 0)
                    break;
                JButton loadHero = frame.create_button("hero" + max_count, 16);
                loadHero.setActionCommand(files.get(max_count - 1));
                loadHero.addActionListener(new LoadHeroPage_noBD.ButtonClickListener());
                frame.getMainFrame().add(loadHero, c);
            }
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
                GameController.getInstance().stage_LoadHero();
            } else {
                GameController.getInstance().loadHero(command);
                GameController.getInstance().stage_Game();
            }
        }
    }
}
