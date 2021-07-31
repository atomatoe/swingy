package View;

import Controller.GameController;
import Model.Hero;
import Model.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreateHeroPage {
    private static CreateHeroPage instance;
    private static ArrayList<Hero> hero_list;
    private static int current_hero;
    private CreateHeroPage() { }

    public static CreateHeroPage getInstance() {
        if(instance == null) {
            instance = new CreateHeroPage();
            Hero hero1 = new Hero("Trios", "Warrior", 30, 20, 10,
                    "./src/main/resources/images/Warrior.gif");
            Hero hero2 = new Hero("Maven", "Rogue", 40, 10, 10,
                    "./src/main/resources/images/Rogue.gif");
            Hero hero3 = new Hero("Alex", "Mage", 30, 10, 20,
                    "./src/main/resources/images/Mage.gif");
            Hero hero4 = new Hero("Storm", "Paladin", 10, 30, 20,
                    "./src/main/resources/images/Paladin.gif");
            Hero hero5 = new Hero("Tremor", "Ork", 10, 10, 40,
                    "./src/main/resources/images/Ork.gif");
            hero_list = new ArrayList<Hero>();
            hero_list.add(hero1);
            hero_list.add(hero2);
            hero_list.add(hero3);
            hero_list.add(hero4);
            hero_list.add(hero5);
            current_hero = 0;
        }
        return instance;
    }

    public void paint_page() {
        Window frame = Window.getInstance();
        frame.create_background("./src/main/resources/images/createHeroPage.png");

        JPanel button_panel = new JPanel();
        button_panel.setOpaque(false); // убираем белый цвет на jpanel кнопок
        button_panel.setLayout(new FlowLayout());

        JButton createHero = frame.create_button("Create", 16);
        createHero.setActionCommand("Create hero");
        createHero.addActionListener(new CreateHeroPage.ButtonClickListener());
        button_panel.add(createHero);

        JButton back = frame.create_button("Back", 16);
        back.setActionCommand("Back");
        back.addActionListener(new CreateHeroPage.ButtonClickListener());
        button_panel.add(back);

        JPanel button_panel_np = new JPanel();
        button_panel_np.setOpaque(false); // убираем белый цвет на jpanel кнопок
        button_panel_np.setLayout(new FlowLayout());

        JButton prev = frame.create_button("Prev", 16);
        prev.setActionCommand("Prev");
        prev.addActionListener(new CreateHeroPage.ButtonClickListener());
        button_panel_np.add(prev);

        JButton next = frame.create_button("Next", 16);
        next.setActionCommand("Next");
        next.addActionListener(new CreateHeroPage.ButtonClickListener());
        button_panel_np.add(next);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.VERTICAL;
        JLabel text = frame.create_text("Create hero", 22);
        frame.getMainFrame().add(text, c);

        frame.add_empty_text(2, 0);

        c.gridy = 3;
        ImageIcon imageIcon = new ImageIcon(hero_list.get(current_hero).getPhoto()); // load the image to a imageIcon
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(220, 220,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        frame.getMainFrame().add(new JLabel(imageIcon), c);

        frame.add_empty_text(4, 0);
        c.gridy = 5;
        frame.getMainFrame().add(button_panel_np, c);

        frame.add_empty_text(6, 0);
        c.gridy = 7;
        text = frame.create_text("Name: " + hero_list.get(current_hero).getName(), 16);
        frame.getMainFrame().add(text, c);
        frame.add_empty_text(8, 0);
        c.gridy = 9;
        text = frame.create_text("Class: " + hero_list.get(current_hero).getHeroClass(), 16);
        frame.getMainFrame().add(text, c);
        frame.add_empty_text(10, 0);
        c.gridy = 11;
        text = frame.create_text("Attack: " + hero_list.get(current_hero).getAttack(), 16);
        frame.getMainFrame().add(text, c);
        frame.add_empty_text(12, 0);
        c.gridy = 13;
        text = frame.create_text("Defence: " + hero_list.get(current_hero).getDefence(), 16);
        frame.getMainFrame().add(text, c);
        frame.add_empty_text(14, 0);
        c.gridy = 15;
        text = frame.create_text("HitPoints: " + hero_list.get(current_hero).getHitPoints(), 16);
        frame.getMainFrame().add(text, c);
        frame.add_empty_text(16, 0);
        c.gridy = 17;
        frame.getMainFrame().add(button_panel, c);

        frame.getMainFrame().setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            Window frame = Window.getInstance();
            if (command.equals("Create hero")) {
                System.out.println("CREATE !!!");
            } else if (command.equals("Back")) {
                System.out.println("Back");
                GameController.getInstance().stage_main();
            } else if (command.equals("Next")) {
                System.out.println("Next");
                if(current_hero == (hero_list.size() - 1))
                    current_hero = 0;
                else
                    current_hero++;
                frame.clear_window();
                paint_page();
            } else if (command.equals("Prev")) {
                System.out.println("Prev");
                if(current_hero == 0)
                    current_hero = (hero_list.size() - 1);
                else
                    current_hero--;
                frame.clear_window();
                paint_page();
            } else {
                System.exit(0);
            }
        }
    }
}
