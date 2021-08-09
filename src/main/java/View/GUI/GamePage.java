package View.GUI;

import Controller.GameController;
import Model.Hero;
import Model.Window;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class GamePage {
    private static GamePage instance;
    private GamePage() { }
    private static ArrayList<Hero> enemy_list;
    private static int first_generate;
    private static int size;
    private static MyKey board_signal;

    public static GamePage getInstance() {
        if(instance == null) {
            first_generate = 0;
            size = 0;
            enemy_list = new ArrayList<Hero>();
            instance = new GamePage();
        }
        return instance;
    }
    public void from_console(ArrayList<Hero> enemy_list) {
        this.first_generate = 1;
        this.enemy_list = enemy_list;
    }
    public void paint_page() {
        Window frame = Window.getInstance();
        frame.getMainFrame().setFocusable(true);
        frame.getMainFrame().setFocusTraversalKeysEnabled(false);
        board_signal = new MyKey();
        frame.getMainFrame().addKeyListener(board_signal);
        frame.getMainFrame().setFocusable(true);
        render();
    }

    public void render() {
        Window frame = Window.getInstance();
        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        JPanel map = paint_map();
        constraints.gridx = 1;
        constraints.weighty = 0.55;
        constraints.weightx = 0.50;
        map.setBackground(new Color(87, 125, 60));
        contentPane.add(map, constraints);

        JPanel game = paint_game();
        constraints.gridx = 2;
        constraints.weighty = 0.45;
        constraints.weightx = 0.50;
        game.setBackground(new Color(32, 124, 55));
        contentPane.add(game, constraints);

        frame.getMainFrame().setContentPane(contentPane);
        frame.getMainFrame().setVisible(true);
    }

    private JPanel paint_map() {
        size = (GameController.getInstance().getCurrentHero().getLvl() - 1) * 5 + 10;
        JPanel map = new JPanel(new GridBagLayout());
        int textures_size = ((Window.getInstance().getMainFrame().getWidth() / 3) * 2) / (size + 1);
        GridBagConstraints c = new GridBagConstraints();
        ImageIcon imageIcon = new ImageIcon("./src/main/resources/images/textures/grass/grass4.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(textures_size, textures_size,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        ImageIcon avatarIcon = null;
        try {
            BufferedImage logo = ImageIO.read(new File("./src/main/resources/images/textures/grass/grass4.png"));
            BufferedImage source = ImageIO.read(new File(GameController.getInstance().getCurrentHero().getPhoto()));

            logo.getGraphics().drawImage(source, 0, 0, null);
            Image newimgAvatar = logo.getScaledInstance(textures_size, textures_size, java.awt.Image.SCALE_SMOOTH);
            avatarIcon = new ImageIcon(newimgAvatar);
        } catch (Exception e) {
            System.out.println("ERROR paint_map");
        }
        if(first_generate == 0) {
            map = paint_enemy_neutrals(map);
            first_generate = 1;
        } else {
            map = paint_saved(map);
        }
        for (int y = 0; y < size; y++) {
            c.gridy = y;
            for(int x = 0; x < size; x++) {
                c.gridx = x;
                if(c.gridy == GameController.getInstance().getCurrentHero().getCoordinates_y() &&
                        c.gridx == GameController.getInstance().getCurrentHero().getCoordinates_x())
                    map.add(new JLabel(avatarIcon), c);
                else
                    map.add(new JLabel(imageIcon), c);
            }
        }
        map.setBackground(new Color(87, 125, 60));
        return (map);
    }

    private JPanel paint_saved(JPanel map) {
        int textures_size = ((Window.getInstance().getMainFrame().getWidth() / 3) * 2) / (size + 1);
        GridBagConstraints c = new GridBagConstraints();
        ImageIcon avatarIcon = null;
        for(int i = 0; i != enemy_list.size(); i++) {
            try {
                BufferedImage logo = ImageIO.read(new File("./src/main/resources/images/textures/grass/grass4.png"));
                BufferedImage source = ImageIO.read(new File(enemy_list.get(i).getPhoto()));

                logo.getGraphics().drawImage(source, 0, 0, null);
                Image newimgAvatar = logo.getScaledInstance(textures_size, textures_size, java.awt.Image.SCALE_SMOOTH);
                avatarIcon = new ImageIcon(newimgAvatar);
                c.gridx = enemy_list.get(i).getCoordinates_x();
                c.gridy = enemy_list.get(i).getCoordinates_y();
                map.add(new JLabel(avatarIcon), c);
            }catch (Exception e) {
                System.out.println("Error paint_saved!");
            }
        }
        return(map);
    }

    private JPanel paint_enemy_neutrals(JPanel map) {
        Hero enemy;
        GridBagConstraints c = new GridBagConstraints();
        ImageIcon avatarIcon = null;
        int textures_size = ((Window.getInstance().getMainFrame().getWidth() / 3) * 2) / (size + 1);
        for(int i = 0; i < GameController.getInstance().getCurrentHero().getLvl() + 2; i++) {
            enemy = generate_enemy(GameController.getInstance().getCurrentHero().getLvl());
            try {
                BufferedImage logo = ImageIO.read(new File("./src/main/resources/images/textures/grass/grass4.png"));
                BufferedImage source = ImageIO.read(new File(enemy.getPhoto()));

                logo.getGraphics().drawImage(source, 0, 0, null);
                Image newimgAvatar = logo.getScaledInstance(textures_size, textures_size, java.awt.Image.SCALE_SMOOTH);
                avatarIcon = new ImageIcon(newimgAvatar);
                c.gridx = enemy.getCoordinates_x();
                c.gridy = enemy.getCoordinates_y();
                map.add(new JLabel(avatarIcon), c);
                enemy_list.add(enemy);
            } catch (Exception e) {
                System.out.println("ERROR paint_enemy_neutrals1");
                System.exit(-1);
            }
        }
        return (map);
    }

    public Hero getEnemyToPosition(int x, int y) {
        for(int i = 0; i != enemy_list.size(); i++) {
            if(x == enemy_list.get(i).getCoordinates_x() && y == enemy_list.get(i).getCoordinates_y())
                return(enemy_list.get(i));
        }
        return(null);
    }

    private Hero generate_enemy(int lvl) {
        Hero enemy = null;
        Random random = new Random();
        int enemy_x = GameController.getInstance().getCurrentHero().getCoordinates_x();
        int enemy_y = GameController.getInstance().getCurrentHero().getCoordinates_y();
        while (enemy_y == GameController.getInstance().getCurrentHero().getCoordinates_y()
                && enemy_x == GameController.getInstance().getCurrentHero().getCoordinates_x()) {
            enemy_x = random.nextInt(size - 1 + 1);
            enemy_y = random.nextInt(size - 1 + 1);
            if(getEnemyToPosition(enemy_x, enemy_y) != null) {
                enemy_x = random.nextInt(size - 1 + 1);
                enemy_y = random.nextInt(size - 1 + 1);
            }
        }
        int id = random.nextInt(lvl + 1);
        int bonus_lvl = random.nextInt(1 + 1);
        id += bonus_lvl;
        int bonus = 0;
        bonus = random.nextInt(8 + 1);
        bonus += GameController.getInstance().getCurrentHero().getLvl();
        if(GameController.getInstance().getCurrentHero().getLvl() == 0) {
            int diff_neutral = random.nextInt(1 + 1);
            if(diff_neutral == 0)
                enemy = new Hero("Cat", "Neutral", 0, 0, 10,
                        "./src/main/resources/images/Neutral/Cat/face.png", 0, 250, enemy_x, enemy_y,
                        "./src/main/resources/images/Neutral/Cat/left.png",
                        "./src/main/resources/images/Neutral/Cat/right.png",
                        "./src/main/resources/images/Neutral/Cat/behind.png");
            else
                enemy = new Hero("Dog", "Neutral", 0, 0, 10,
                        "./src/main/resources/images/Neutral/Dog/face.png", 0, 250, enemy_x, enemy_y,
                        "./src/main/resources/images/Neutral/Dog/left.png",
                        "./src/main/resources/images/Neutral/Dog/right.png",
                        "./src/main/resources/images/Neutral/Dog/behind.png");
        }
        else if(id <= 1)
            enemy = new Hero("Gumanoid", "Enemy (1lvl)", 5 + bonus, 5 + bonus, 10 + bonus,
                    "./src/main/resources/images/enemy/Enemy1/face.png", 1, 500, enemy_x, enemy_y,
                    "./src/main/resources/images/enemy/Enemy1/left.png",
                    "./src/main/resources/images/enemy/Enemy1/right.png",
                    "./src/main/resources/images/enemy/Enemy1/behind.png");
        else if(id == 2)
            enemy = new Hero("Evil gumanoid", "Enemy (2lvl)", 10 + bonus, 5 + bonus, 10 + bonus,
                    "./src/main/resources/images/enemy/Enemy2/face.png", 2, 750, enemy_x, enemy_y,
                    "./src/main/resources/images/enemy/Enemy2/left.png",
                    "./src/main/resources/images/enemy/Enemy2/right.png",
                    "./src/main/resources/images/enemy/Enemy2/behind.png");
        else if(id == 3)
            enemy = new Hero("Skeleton", "Enemy (3lvl)", 10 + bonus, 10 + bonus, 10 + bonus,
                    "./src/main/resources/images/enemy/Enemy3/face.png", 3, 1000, enemy_x, enemy_y,
                    "./src/main/resources/images/enemy/Enemy3/left.png",
                    "./src/main/resources/images/enemy/Enemy3/right.png",
                    "./src/main/resources/images/enemy/Enemy3/behind.png");
        else if(id == 4)
            enemy = new Hero("Dark skeleton", "Enemy (4lvl)", 10 + bonus, 10 + bonus, 15 + bonus,
                    "./src/main/resources/images/enemy/Enemy4/face.png", 4, 1250, enemy_x, enemy_y,
                    "./src/main/resources/images/enemy/Enemy4/left.png",
                    "./src/main/resources/images/enemy/Enemy4/right.png",
                    "./src/main/resources/images/enemy/Enemy4/behind.png");
        else if(id == 5)
            enemy = new Hero("Ghost", "Enemy (5lvl)", 5 + bonus, 15 + bonus, 20 + bonus,
                    "./src/main/resources/images/enemy/Enemy5/face.png", 5, 1500, enemy_x, enemy_y,
                    "./src/main/resources/images/enemy/Enemy5/left.png",
                    "./src/main/resources/images/enemy/Enemy5/right.png",
                    "./src/main/resources/images/enemy/Enemy5/behind.png");
        else if(id == 6)
            enemy = new Hero("Dark mage", "Enemy (6lvl)", 25 + bonus, 10 + bonus, 15 + bonus,
                    "./src/main/resources/images/enemy/Enemy6/face.png", 6, 1750, enemy_x, enemy_y,
                    "./src/main/resources/images/enemy/Enemy6/left.png",
                    "./src/main/resources/images/enemy/Enemy6/right.png",
                    "./src/main/resources/images/enemy/Enemy6/behind.png");
        else if(id == 7)
            enemy = new Hero("Mister pumpkin", "Enemy (7lvl)", 25 + bonus, 20 + bonus, 25 + bonus,
                    "./src/main/resources/images/enemy/Enemy7/face.png", 7, 2000, enemy_x, enemy_y,
                    "./src/main/resources/images/enemy/Enemy7/left.png",
                    "./src/main/resources/images/enemy/Enemy7/right.png",
                    "./src/main/resources/images/enemy/Enemy7/behind.png");
        else
            enemy = new Hero("Dark assasin", "Enemy (8lvl)", 30 + bonus, 15 + bonus, 40 + bonus,
                    "./src/main/resources/images/enemy/Enemy8/face.png", 8, 2500, enemy_x, enemy_y,
                    "./src/main/resources/images/enemy/Enemy8/left.png",
                    "./src/main/resources/images/enemy/Enemy8/right.png",
                    "./src/main/resources/images/enemy/Enemy8/behind.png");
        return(enemy);
    }

    private JPanel paint_game() {
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
        ImageIcon heroIcon = new ImageIcon(GameController.getInstance().getCurrentHero().getPhoto()); // load the image to a imageIcon
        Image heroImage = heroIcon.getImage();
        Image newHeroImg = heroImage.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newHeroImg);
        game.add(new JLabel(imageIcon), cont);

        JPanel button_panel = new JPanel();
        button_panel.setOpaque(false); // убираем белый цвет на jpanel кнопок
        button_panel.setLayout(new FlowLayout());
        JButton save = frame.create_button("Save", 16);
        save.setActionCommand("Save");
        save.addActionListener(new GamePage.ButtonClickListener());
        button_panel.add(save);

        JButton save_db = frame.create_button("SaveDB", 16);
        save_db.setActionCommand("SaveDB");
        save_db.addActionListener(new GamePage.ButtonClickListener());
        button_panel.add(save_db);

        JButton console = frame.create_button("Console", 16);
        console.setActionCommand("Console");
        console.addActionListener(new GamePage.ButtonClickListener());
        button_panel.add(console);

        JButton exit = frame.create_button("Exit", 16);
        exit.setActionCommand("Exit");
        exit.addActionListener(new GamePage.ButtonClickListener());
        button_panel.add(exit);

        cont.gridy = 5;
        game.add(button_panel, cont);

        cont.gridy = 7;
        text = frame.create_text("Name: " + GameController.getInstance().getCurrentHero().getName(), 16);
        game.add(text, cont);
        cont.gridy = 9;
        text = frame.create_text("Class: " + GameController.getInstance().getCurrentHero().getHeroClass(), 16);
        game.add(text, cont);
        cont.gridy = 11;
        if(GameController.getInstance().getCurrentHero().getAxe() != null)
            text = frame.create_text("Attack: " + GameController.getInstance().getCurrentHero().getAttack() + " (+" + GameController.getInstance().getCurrentHero().getAxe().getAttack() + ")", 16);
        else
            text = frame.create_text("Attack: " + GameController.getInstance().getCurrentHero().getAttack(), 16);
        game.add(text, cont);
        cont.gridy = 13;
        if(GameController.getInstance().getCurrentHero().getArmor() != null)
            text = frame.create_text("Defence: " + GameController.getInstance().getCurrentHero().getDefence() + " (+" + GameController.getInstance().getCurrentHero().getArmor().getDefence() + ")", 16);
        else
            text = frame.create_text("Defence: " + GameController.getInstance().getCurrentHero().getDefence(), 16);
        game.add(text, cont);
        cont.gridy = 15;
        if(GameController.getInstance().getCurrentHero().getHelm() != null)
            text = frame.create_text("HitPoints: " + GameController.getInstance().getCurrentHero().getHitPoints() + " (+" + GameController.getInstance().getCurrentHero().getHelm().getHitPoints() + ")", 16);
        else
            text = frame.create_text("HitPoints: " + GameController.getInstance().getCurrentHero().getHitPoints(), 16);
        game.add(text, cont);

        cont.gridy = 17;
        text = frame.create_text("Lvl: " +GameController.getInstance().getCurrentHero().getLvl(), 16);
        game.add(text, cont);

        cont.gridy = 19;
        text = frame.create_text("Exp: " +GameController.getInstance().getCurrentHero().getExp(), 16);
        game.add(text, cont);

        cont.gridy = 21;
        text = frame.create_text("Inventory:", 16);
        game.add(text, cont);

        int img_size = 40;
        JPanel inventory_panel = new JPanel();
        inventory_panel.setOpaque(false); // убираем белый цвет
        inventory_panel.setLayout(new FlowLayout());
        ImageIcon artifact = null;

        if(GameController.getInstance().getCurrentHero().getAxe() != null) {
            artifact = create_img(GameController.getInstance().getCurrentHero().getAxe().getPhoto(), img_size, img_size);
        } else {
            artifact = create_img("./src/main/resources/images/artifacts/empty.png", img_size, img_size);
        }
        inventory_panel.add(new JLabel(artifact));

        if(GameController.getInstance().getCurrentHero().getArmor() != null) {
            artifact = create_img(GameController.getInstance().getCurrentHero().getArmor().getPhoto(), img_size, img_size);
        } else {
            artifact = create_img("./src/main/resources/images/artifacts/empty.png", img_size, img_size);
        }
        inventory_panel.add(new JLabel(artifact));

        if(GameController.getInstance().getCurrentHero().getHelm() != null) {
            artifact = create_img(GameController.getInstance().getCurrentHero().getHelm().getPhoto(), img_size, img_size);
        } else {
            artifact = create_img("./src/main/resources/images/artifacts/empty.png", img_size, img_size);
        }
        inventory_panel.add(new JLabel(artifact));

        cont.gridy = 23;
        game.add(inventory_panel, cont);
        return(game);
    }

    public void finish_battle() {
        Window frame = Window.getInstance();
        frame.getMainFrame().addKeyListener(board_signal);
        render();
    }

    private int get_textures_size(int lvl) {
        int start_size = 50;
        for(int i = 0; i != lvl; i++) {
            if(lvl > 9)
                return(1);
            start_size -= 5;
        }
        return (start_size);
    }

    public ImageIcon create_img(String photo, int size_x, int size_y) {
        ImageIcon artifactIcon = new ImageIcon(photo);
        Image artifactImage = artifactIcon.getImage();
        Image newArtifactImg = artifactImage.getScaledInstance(size_x, size_y, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        ImageIcon imageIcon = new ImageIcon(newArtifactImg);
        return(imageIcon);
    }

    private void enemy_move() {
        Random rand = new Random();
        for(int i = 0; i != enemy_list.size(); i++) {
            int random = rand.nextInt(3 + 1);
            if(!enemy_list.get(i).check_hero()) {
                if (random == 0)
                    enemy_list.get(i).moving(KeyEvent.VK_W, size);
                else if (random == 1)
                    enemy_list.get(i).moving(KeyEvent.VK_S, size);
                else if (random == 2)
                    enemy_list.get(i).moving(KeyEvent.VK_D, size);
                else if (random == 3)
                    enemy_list.get(i).moving(KeyEvent.VK_A, size);
            }
        }
    }

    public void delete_enemy(int x, int y) {
        for(int i = 0; i != enemy_list.size(); i++) {
            if(x == enemy_list.get(i).getCoordinates_x() &&
                y == enemy_list.get(i).getCoordinates_y()) {
                enemy_list.remove(i);
                if(enemy_list.isEmpty())
                    first_generate = 0;
                return;
            }
        }
    }

    public static int getSize() { return size; }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("Save")) {
                GameController.getInstance().save_Hero();
                render();
            } else if (command.equals("SaveDB")) {
                GameController.getInstance().save_Hero_DB();
                render();
            } else if(command.equals("Console")) {
                Window.getInstance().clear_window();
                Window.getInstance().getMainFrame().setVisible(false);
                Window frame = Window.getInstance();
                frame.getMainFrame().removeKeyListener(board_signal);
//                Window.getInstance().getMainFrame().dispose();
                GameController.getInstance().stage_Game_console();
            } else if(command.equals("Exit"))
                System.exit(0);
        }
    }

    public class MyKey extends KeyAdapter implements ActionListener {

        @Override
        public void keyPressed(KeyEvent e) {
            Window frame = Window.getInstance();
            if (e.getKeyCode() == KeyEvent.VK_W) {
                GameController.getInstance().getCurrentHero().moving(e.getKeyCode(), size);
                enemy_move();
                render();
                Hero enemy = getEnemyToPosition(GameController.getInstance().getCurrentHero().getCoordinates_x(),
                        GameController.getInstance().getCurrentHero().getCoordinates_y());
                if(enemy != null) {
                    frame.getMainFrame().removeKeyListener(board_signal);
                    GameController.getInstance().stage_Battle(enemy);
                    return;
                }
            }
            else if (e.getKeyCode() == KeyEvent.VK_S) {
                GameController.getInstance().getCurrentHero().moving(e.getKeyCode(), size);
                enemy_move();
                render();
                Hero enemy = getEnemyToPosition(GameController.getInstance().getCurrentHero().getCoordinates_x(),
                        GameController.getInstance().getCurrentHero().getCoordinates_y());
                if(enemy != null) {
                    frame.getMainFrame().removeKeyListener(board_signal);
                    GameController.getInstance().stage_Battle(enemy);
                    return;
                }
            }
            else if (e.getKeyCode() == KeyEvent.VK_D) {
                GameController.getInstance().getCurrentHero().moving(e.getKeyCode(), size);
                enemy_move();
                render();
                Hero enemy = getEnemyToPosition(GameController.getInstance().getCurrentHero().getCoordinates_x(),
                        GameController.getInstance().getCurrentHero().getCoordinates_y());
                if(enemy != null) {
                    frame.getMainFrame().removeKeyListener(board_signal);
                    GameController.getInstance().stage_Battle(enemy);
                    return;
                }
            }
            else if (e.getKeyCode() == KeyEvent.VK_A) {
                GameController.getInstance().getCurrentHero().moving(e.getKeyCode(), size);
                enemy_move();
                render();
                Hero enemy = getEnemyToPosition(GameController.getInstance().getCurrentHero().getCoordinates_x(),
                        GameController.getInstance().getCurrentHero().getCoordinates_y());
                if(enemy != null) {
                    frame.getMainFrame().removeKeyListener(board_signal);
                    GameController.getInstance().stage_Battle(enemy);
                    return;
                }
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("WTF ???");
        }
    }
}
