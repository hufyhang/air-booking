package main;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * MenuBar.java
 * @author HANG Feifei, ZHU Hao, WANG Xi
 * This builds a JMenuBar to be used in the MainView
 */
public class MenuBar extends JMenuBar {

    private JFrame frame;

    public MenuBar( JFrame frame ) {
        this.frame = frame;
        addSysMenu();
		addHelpMene();
    }
	
	private void addHelpMene() {
	
		JMenu menuFile = new JMenu("Help");
        JMenuItem menuItemAbout = new JMenuItem("About...");

        menuItemAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(new JFrame(),
                    "Air-Booking System\nby Group 409\nHANG Feifei\nZHU Hao\nWANG Xi",
                    "About...",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
		
        menuFile.add(menuItemAbout);
        add(menuFile);
		
	}

    private void addSysMenu() {
	
        JMenu menuFile = new JMenu("System");
		JMenuItem menuItemLogout = new JMenuItem("Log off");
        JMenuItem menuItemExit = new JMenuItem("Exit");
		
		menuItemLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new ucm.UCDisplayUI().run( new view.WelcomeScreen() );
                frame.dispose();
            }
        });

        menuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
		
		menuFile.add(menuItemLogout);
		menuFile.addSeparator();
        menuFile.add(menuItemExit);
        add(menuFile);
    }
}
