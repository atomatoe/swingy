package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window {

    private static Window instance;
    private static JFrame mainFrame;
    private Window() { }

    public static Window getInstance() {
        if (instance == null) {
            mainFrame = new JFrame("SWINGY");
            mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
            mainFrame.setUndecorated(true);
//            mainFrame.setSize(1200,800);
            mainFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent windowEvent){
                    System.exit(0);
                }
            });
            mainFrame.setResizable(false);
            instance = new Window();
        }
        return instance;
    }

    public void clear_window() {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().repaint();
    }

    public JButton create_button(String text, int size) {
        JButton button = new JButton(text);
        button.setFont(new Font("Papyrus", Font.ITALIC, size));
        button.setForeground(Color.lightGray);
        return(button);
    }

    public JLabel create_text(String str, int size) {
        JLabel text = new JLabel(str, JLabel.CENTER);
        text.setFont(new Font("Papyrus", Font.ITALIC, size));
        text.setForeground(Color.lightGray);
        return(text);
    }

    public void create_background(String file) {
        JLabel content = new JLabel();
        content.setIcon( new ImageIcon(file) );
        content.setLayout( new BorderLayout() );
        mainFrame.setContentPane(content);
        mainFrame.setLayout(new GridBagLayout());
    }

    public void add_empty_text(int gridy, int gridx) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = gridy;
        c.gridx = gridx;
        c.fill = GridBagConstraints.VERTICAL;
        JLabel text = create_text(" ", 16);
        mainFrame.add(text, c);
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
}