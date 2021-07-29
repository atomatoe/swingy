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
            mainFrame.setSize(800,800);
            mainFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent windowEvent){
                    System.exit(0);
                }
            });
//            mainFrame.getContentPane().setBackground(Color.DARK_GRAY);
            instance = new Window();
        }
        return instance;
    }

    public void clear_window() {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().repaint();
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
}