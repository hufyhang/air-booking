/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package airbooking;

import javax.swing.*;
import javax.swing.JFrame;

/**
 *
 * @author HANG Feifei
 */
public class Main extends JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        view.MainUI mainUI = new view.MainUI(new view.Welcome());
        mainUI.pack();
        mainUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainUI.setVisible(true);
    }

}
