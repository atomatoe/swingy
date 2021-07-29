package View;

import Controller.GUIcontroller;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CreateHero {
    private JFrame mainFrame;
    private JPanel controlPanel;

    public CreateHero (JFrame mainFrame) {
        this.mainFrame = mainFrame;
        prepareGUI();
    }

    private void prepareGUI() {
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);
    }

    public void showEventDemo() {
        JButton nextButton = new JButton("Next Hero");
        JButton prevButton = new JButton("Prev Hero");
        nextButton.setActionCommand("Next Hero");
        nextButton.addActionListener(new ButtonClickListener());
        prevButton.setActionCommand("Prev Hero");
        prevButton.addActionListener(new ButtonClickListener());
        controlPanel.add(prevButton);
        controlPanel.add(nextButton);
        mainFrame.setVisible(true);

    }

    private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if( command.equals( "Next Hero" ))  {
                System.out.println("Next hero!");
            }
            else if(command.equals("Prev Hero")) {
                System.out.println("Prev hero!");
            }
        }
    }
}