package test;

import Controller.GameController;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWindow {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel headerLabelName;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public MainWindow(){
        prepareGUI();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("SWINGY");
        mainFrame.setSize(800,800);
        mainFrame.setLayout(new GridLayout(10, 1));

        headerLabelName = new JLabel("",JLabel.CENTER );
        headerLabel = new JLabel("",JLabel.CENTER );
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350,100);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        ImageIcon mainImg = new ImageIcon("test.png");

        mainFrame.add(headerLabel);
        mainFrame.add(new JLabel(mainImg)); // add image
        mainFrame.add(headerLabelName);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    public void showEventDemo(){
        headerLabel.setText("Welcome to Swingy!");
        headerLabelName.setText("by atomatoe");
        JButton okButton = new JButton("Create hero");
        JButton submitButton = new JButton("Load hero");
        JButton cancelButton = new JButton("Exit");

        okButton.setActionCommand("Create hero");
        submitButton.setActionCommand("Load hero");
        cancelButton.setActionCommand("Exit");

        okButton.addActionListener(new ButtonClickListener());
        submitButton.addActionListener(new ButtonClickListener());
        cancelButton.addActionListener(new ButtonClickListener());

        controlPanel.add(okButton);
        controlPanel.add(submitButton);
        controlPanel.add(cancelButton);

        mainFrame.setVisible(true);
    }
        private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if( command.equals( "Create hero" ))  {
//                statusLabel.setText("Create hero Button clicked.");
                // Очистка окна после нажатия кнопки
                mainFrame.getContentPane().removeAll();
                mainFrame.getContentPane().repaint();
                GameController.getInstance().stage_CreateHero();
            } else if(command.equals( "Load hero" ))  {
//                statusLabel.setText("Load hero Button clicked.");
                // Очистка окна после нажатия кнопки
                mainFrame.getContentPane().removeAll();
                mainFrame.getContentPane().repaint();
                GameController.getInstance().stage_LoadHero();
            } else {
                System.exit(0);
            }
        }
    }
}