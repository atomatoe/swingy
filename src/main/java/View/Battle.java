package View;

import Controller.GameController;
import Model.Artifact;
import Model.Hero;
import Model.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Battle {
    private static Battle instance;
    private static int winner;
    private static Hero enemy;

    public static Battle getInstance() {
        if (instance == null) {
            winner = 0;
            instance = new Battle();
        }
        return instance;
    }

    public void paint_page(Hero enemy, int winner) {
        Battle.winner = winner;
        Battle.enemy = enemy;

        Window frame = Window.getInstance();
        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        JPanel hero = print_Hero();
        constraints.gridx = 1;
        constraints.weighty = 0.33;
        constraints.weightx = 0.33;
        hero.setBackground(new Color(87, 55, 23));
        contentPane.add(hero, constraints);

        JPanel vs = print_vs();
        constraints.gridx = 2;
        constraints.weighty = 0.33;
        constraints.weightx = 0.33;
        vs.setBackground(new Color(87, 55, 23));
        contentPane.add(vs, constraints);

        JPanel warrior = print_Enemy(enemy);
        constraints.gridx = 3;
        constraints.weighty = 0.33;
        constraints.weightx = 0.33;
        warrior.setBackground(new Color(87, 55, 23));
        contentPane.add(warrior, constraints);

        frame.getMainFrame().setContentPane(contentPane);
        frame.getMainFrame().setVisible(true);
    }

    public JPanel print_Hero() {
        Window frame = Window.getInstance();

        JPanel game = new JPanel(new GridBagLayout());
        ImageIcon imageIcon = null;
        GridBagConstraints cont = new GridBagConstraints();
        cont.gridx = 0;
        cont.gridy = 1;
        cont.fill = GridBagConstraints.VERTICAL;
        JLabel text = frame.create_text("Hero", 22);
        game.add(text, cont);

        frame.add_empty_text(2, 0);

        cont.gridy = 3;
        ImageIcon heroIcon = new ImageIcon(GameController.getInstance().getCurrentHero().getPhoto_right()); // load the image to a imageIcon
        Image heroImage = heroIcon.getImage();
        Image newHeroImg = heroImage.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newHeroImg);
        game.add(new JLabel(imageIcon), cont);

        cont.gridy = 5;
        text = frame.create_text("Name: " + GameController.getInstance().getCurrentHero().getName(), 16);
        game.add(text, cont);
        cont.gridy = 7;
        text = frame.create_text("Class: " + GameController.getInstance().getCurrentHero().getHeroClass(), 16);
        game.add(text, cont);
        cont.gridy = 9;
        if (GameController.getInstance().getCurrentHero().getAxe() != null)
            text = frame.create_text("Attack: " + GameController.getInstance().getCurrentHero().getAttack() + " (+" + GameController.getInstance().getCurrentHero().getAxe().getAttack() + ")", 16);
        else
            text = frame.create_text("Attack: " + GameController.getInstance().getCurrentHero().getAttack(), 16);
        game.add(text, cont);
        cont.gridy = 11;
        if (GameController.getInstance().getCurrentHero().getArmor() != null)
            text = frame.create_text("Defence: " + GameController.getInstance().getCurrentHero().getDefence() + " (+" + GameController.getInstance().getCurrentHero().getArmor().getDefence() + ")", 16);
        else
            text = frame.create_text("Defence: " + GameController.getInstance().getCurrentHero().getDefence(), 16);
        game.add(text, cont);
        cont.gridy = 13;
        if (GameController.getInstance().getCurrentHero().getHelm() != null)
            text = frame.create_text("HitPoints: " + GameController.getInstance().getCurrentHero().getHitPoints() + " (+" + GameController.getInstance().getCurrentHero().getHelm().getHitPoints() + ")", 16);
        else
            text = frame.create_text("HitPoints: " + GameController.getInstance().getCurrentHero().getHitPoints(), 16);
        game.add(text, cont);

        cont.gridy = 15;
        text = frame.create_text("Lvl: " + GameController.getInstance().getCurrentHero().getLvl(), 16);
        game.add(text, cont);

        cont.gridy = 17;
        text = frame.create_text("Exp: " + GameController.getInstance().getCurrentHero().getExp(), 16);
        game.add(text, cont);

        cont.gridy = 19;
        text = frame.create_text("Inventory:", 16);
        game.add(text, cont);

        int img_size = 40;
        JPanel inventory_panel = new JPanel();
        inventory_panel.setOpaque(false); // убираем белый цвет
        inventory_panel.setLayout(new FlowLayout());
        ImageIcon artifact = null;

        if (GameController.getInstance().getCurrentHero().getAxe() != null) {
            artifact = GamePage.getInstance().create_img(GameController.getInstance().getCurrentHero().getAxe().getPhoto(), img_size, img_size);
        } else {
            artifact = GamePage.getInstance().create_img("./src/main/resources/images/artifacts/empty.png", img_size, img_size);
        }
        inventory_panel.add(new JLabel(artifact));

        if (GameController.getInstance().getCurrentHero().getArmor() != null) {
            artifact = GamePage.getInstance().create_img(GameController.getInstance().getCurrentHero().getArmor().getPhoto(), img_size, img_size);
        } else {
            artifact = GamePage.getInstance().create_img("./src/main/resources/images/artifacts/empty.png", img_size, img_size);
        }
        inventory_panel.add(new JLabel(artifact));

        if (GameController.getInstance().getCurrentHero().getHelm() != null) {
            artifact = GamePage.getInstance().create_img(GameController.getInstance().getCurrentHero().getHelm().getPhoto(), img_size, img_size);
        } else {
            artifact = GamePage.getInstance().create_img("./src/main/resources/images/artifacts/empty.png", img_size, img_size);
        }
        inventory_panel.add(new JLabel(artifact));

        cont.gridy = 21;
        game.add(inventory_panel, cont);
        return (game);
    }

    public JPanel print_Enemy(Hero enemy) {
        Window frame = Window.getInstance();

        JPanel game = new JPanel(new GridBagLayout());
        ImageIcon imageIcon = null;
        GridBagConstraints cont = new GridBagConstraints();
        cont.gridx = 0;
        cont.gridy = 1;
        cont.fill = GridBagConstraints.VERTICAL;
        JLabel text = frame.create_text("Enemy", 22);
        game.add(text, cont);

        frame.add_empty_text(2, 0);

        cont.gridy = 3;
        ImageIcon heroIcon = new ImageIcon(enemy.getPhoto_left()); // load the image to a imageIcon
        Image heroImage = heroIcon.getImage();
        Image newHeroImg = heroImage.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newHeroImg);
        game.add(new JLabel(imageIcon), cont);

        cont.gridy = 5;
        text = frame.create_text("Name: " + enemy.getName(), 16);
        game.add(text, cont);
        cont.gridy = 7;
        text = frame.create_text("Class: " + enemy.getHeroClass(), 16);
        game.add(text, cont);
        cont.gridy = 9;

        text = frame.create_text("Attack: " + enemy.getAttack(), 16);
        game.add(text, cont);
        cont.gridy = 11;

        text = frame.create_text("Defence: " + enemy.getDefence(), 16);
        game.add(text, cont);
        cont.gridy = 13;
        text = frame.create_text("HitPoints: " + enemy.getHitPoints(), 16);
        game.add(text, cont);

        cont.gridy = 15;
        text = frame.create_text("Lvl: " + enemy.getLvl(), 16);
        game.add(text, cont);

        cont.gridy = 17;
        text = frame.create_text("", 16);
        game.add(text, cont);

        cont.gridy = 19;
        text = frame.create_text("", 16);
        game.add(text, cont);

        cont.gridy = 21;
        text = frame.create_text("", 16);
        game.add(text, cont);
        return (game);
    }

    public JPanel print_vs() {
        Window frame = Window.getInstance();
        JPanel game = new JPanel(new GridBagLayout());
        ImageIcon imageIcon = null;
        GridBagConstraints cont = new GridBagConstraints();
        cont.gridx = 0;
        cont.gridy = 1;
        cont.fill = GridBagConstraints.VERTICAL;
        JLabel text = frame.create_text("Battle", 35);
        game.add(text, cont);

        frame.add_empty_text(2, 0);

        cont.gridy = 3;
        ImageIcon heroIcon = new ImageIcon("./src/main/resources/images/other/versus.png"); // load the image to a imageIcon
        Image heroImage = heroIcon.getImage();
        Image newHeroImg = heroImage.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newHeroImg);
        game.add(new JLabel(imageIcon), cont);

        frame.add_empty_text(4, 0);

        JButton battle = frame.create_button("Battle", 16);
        battle.setActionCommand("Battle");
        battle.addActionListener(new Battle.ButtonClickListener());
        cont.gridy = 5;
        game.add(battle, cont);

        frame.add_empty_text(6, 0);

        JButton run = frame.create_button("Run", 16);
        run.setActionCommand("Run");
        run.addActionListener(new Battle.ButtonClickListener());
        cont.gridy = 7;
        game.add(run, cont);
        return(game);
    }

    public void print_HeroWin() {
        Window.getInstance().clear_window();
        Window frame = Window.getInstance();
        frame.create_background("./src/main/resources/images/pages/createHeroPage.png");

        JPanel button_panel = new JPanel();
        button_panel.setOpaque(false); // убираем белый цвет на jpanel кнопок
        button_panel.setLayout(new FlowLayout());

        JButton createHero = frame.create_button("Finish", 16);
        createHero.setActionCommand("Finish");
        createHero.addActionListener(new Battle.ButtonClickListener());
        button_panel.add(createHero);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.VERTICAL;
        JLabel text = frame.create_text("Winner", 22);
        frame.getMainFrame().add(text, c);

        c.gridy = 2;
        ImageIcon imageIcon = new ImageIcon(GameController.getInstance().getCurrentHero().getPhoto_face()); // load the image to a imageIcon
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(220, 220,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        frame.getMainFrame().add(new JLabel(imageIcon), c);

        c.gridy = 3;
        text = frame.create_text("Name: " + GameController.getInstance().getCurrentHero().getName(), 16);
        frame.getMainFrame().add(text, c);
        c.gridy = 4;
        text = frame.create_text("Class: " + GameController.getInstance().getCurrentHero().getHeroClass(), 16);
        frame.getMainFrame().add(text, c);
        c.gridy = 5;
        text = frame.create_text("Attack: " + GameController.getInstance().getCurrentHero().getAttack(), 16);
        frame.getMainFrame().add(text, c);
        c.gridy = 6;
        text = frame.create_text("Defence: " + GameController.getInstance().getCurrentHero().getDefence(), 16);
        frame.getMainFrame().add(text, c);
        c.gridy = 7;
        text = frame.create_text("HitPoints: " + GameController.getInstance().getCurrentHero().getHitPoints(), 16);
        frame.getMainFrame().add(text, c);
        c.gridy = 8;
        text = frame.create_text("Lvl: " + GameController.getInstance().getCurrentHero().getLvl(), 16);
        frame.getMainFrame().add(text, c);
        c.gridy = 9;
        text = frame.create_text("Exp: " + GameController.getInstance().getCurrentHero().getExp() + " (+" + enemy.getExp() + ")", 16);
        frame.getMainFrame().add(text, c);
        Artifact artifact = GameController.getInstance().generate_artifact(enemy);
        if(artifact != null) {
            c.gridy = 10;
            imageIcon = new ImageIcon(artifact.getPhoto()); // load the image to a imageIcon
            image = imageIcon.getImage();
            newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon = new ImageIcon(newimg);
            frame.getMainFrame().add(new JLabel(imageIcon), c);
        } else {
            c.gridy = 10;
            imageIcon = new ImageIcon("./src/main/resources/images/artifacts/empty.png"); // load the image to a imageIcon
            image = imageIcon.getImage();
            newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon = new ImageIcon(newimg);
            frame.getMainFrame().add(new JLabel(imageIcon), c);
        }
        GameController.getInstance().getCurrentHero().upgrade(enemy, artifact);
        c.gridy = 11;
        frame.getMainFrame().add(button_panel, c);

        frame.getMainFrame().setVisible(true);
    }

    public void print_HeroLose() {
        Window.getInstance().clear_window();
        Window frame = Window.getInstance();
        frame.create_background("./src/main/resources/images/pages/mainPage.png");

        JPanel button_panel = new JPanel();
        button_panel.setOpaque(false); // убираем белый цвет на jpanel кнопок
        button_panel.setLayout(new FlowLayout());

        JButton createHero = frame.create_button("Exit", 16);
        createHero.setActionCommand("Exit");
        createHero.addActionListener(new Battle.ButtonClickListener());
        button_panel.add(createHero);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.VERTICAL;
        JLabel text = frame.create_text("You lose", 35);
        frame.getMainFrame().add(text, c);

        c.gridy = 2;
        ImageIcon imageIcon = new ImageIcon(GameController.getInstance().getCurrentHero().getPhoto_face()); // load the image to a imageIcon
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        frame.getMainFrame().add(new JLabel(imageIcon), c);

        c.gridy = 3;
        text = frame.create_text("Name: " + GameController.getInstance().getCurrentHero().getName(), 16);
        frame.getMainFrame().add(text, c);
        c.gridy = 4;
        text = frame.create_text("Class: " + GameController.getInstance().getCurrentHero().getHeroClass(), 16);
        frame.getMainFrame().add(text, c);
        c.gridy = 5;
        text = frame.create_text("Attack: " + GameController.getInstance().getCurrentHero().getAttack(), 16);
        frame.getMainFrame().add(text, c);
        c.gridy = 6;
        text = frame.create_text("Defence: " + GameController.getInstance().getCurrentHero().getDefence(), 16);
        frame.getMainFrame().add(text, c);
        c.gridy = 7;
        text = frame.create_text("HitPoints: " + GameController.getInstance().getCurrentHero().getHitPoints(), 16);
        frame.getMainFrame().add(text, c);
        c.gridy = 8;
        text = frame.create_text("Lvl: " + GameController.getInstance().getCurrentHero().getLvl(), 16);
        frame.getMainFrame().add(text, c);
        c.gridy = 9;
        text = frame.create_text("Exp: " + GameController.getInstance().getCurrentHero().getExp(), 16);
        frame.getMainFrame().add(text, c);
        c.gridy = 10;
        text = frame.create_text("Killed monsters: " + GameController.getInstance().getCurrentHero().getKilledMonsters(), 16);
        frame.getMainFrame().add(text, c);
        c.gridy = 11;
        text = frame.create_text("All steps: " + GameController.getInstance().getCurrentHero().getAllSteps(), 16);
        frame.getMainFrame().add(text, c);

        int img_size = 40;
        JPanel inventory_panel = new JPanel();
        inventory_panel.setOpaque(false); // убираем белый цвет
        inventory_panel.setLayout(new FlowLayout());
        ImageIcon artifact = null;

        if(GameController.getInstance().getCurrentHero().getAxe() != null) {
            artifact = GamePage.getInstance().create_img(GameController.getInstance().getCurrentHero().getAxe().getPhoto(), img_size, img_size);
        } else {
            artifact = GamePage.getInstance().create_img("./src/main/resources/images/artifacts/empty.png", img_size, img_size);
        }
        inventory_panel.add(new JLabel(artifact));

        if(GameController.getInstance().getCurrentHero().getArmor() != null) {
            artifact = GamePage.getInstance().create_img(GameController.getInstance().getCurrentHero().getArmor().getPhoto(), img_size, img_size);
        } else {
            artifact = GamePage.getInstance().create_img("./src/main/resources/images/artifacts/empty.png", img_size, img_size);
        }
        inventory_panel.add(new JLabel(artifact));

        if(GameController.getInstance().getCurrentHero().getHelm() != null) {
            artifact = GamePage.getInstance().create_img(GameController.getInstance().getCurrentHero().getHelm().getPhoto(), img_size, img_size);
        } else {
            artifact = GamePage.getInstance().create_img("./src/main/resources/images/artifacts/empty.png", img_size, img_size);
        }
        inventory_panel.add(new JLabel(artifact));

        c.gridy = 12;
        frame.getMainFrame().add(inventory_panel, c);

        c.gridy = 13;
        frame.getMainFrame().add(button_panel, c);

        frame.getMainFrame().setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("Battle")) {
                System.out.println("Continue battle!");
                if(winner == 1) {
                    print_HeroWin();
                } else {
                    print_HeroLose();
                }
            } else if (command.equals("Run")) {
                Random random = new Random();
                int rand = random.nextInt(1 + 1);
                if(rand == 1) {
                    if(winner == 1) {
                        print_HeroWin();
                    } else {
                        print_HeroLose();
                    }
                } else {
                    if (rand == 0)
                        GameController.getInstance().getCurrentHero().moving(KeyEvent.VK_W, GamePage.getSize());
                    else if (rand == 1)
                        GameController.getInstance().getCurrentHero().moving(KeyEvent.VK_S, GamePage.getSize());
                    else if (rand == 2)
                        GameController.getInstance().getCurrentHero().moving(KeyEvent.VK_D, GamePage.getSize());
                    else if (rand == 3)
                        GameController.getInstance().getCurrentHero().moving(KeyEvent.VK_A, GamePage.getSize());
                    GamePage.getInstance().finish_battle();
                }
            } else if (command.equals("Finish")) {
                System.out.println("Finish!");
                if(winner == 1) {
                    GamePage.getInstance().delete_enemy(GameController.getInstance().getCurrentHero().getCoordinates_x(),
                            GameController.getInstance().getCurrentHero().getCoordinates_y());
                    GamePage.getInstance().finish_battle();
                } else {
                    print_HeroLose();
                }
            } else if (command.equals("Exit")) {
                System.exit(0);
            }
        }
    }
}
