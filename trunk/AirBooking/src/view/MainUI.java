/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

/**
 *
 * @author HANG Feifei
 */
public class MainUI extends javax.swing.JFrame{
   
    public MainUI()
    {
    }

    public MainUI(javax.swing.JPanel panel) {
        super("Air-Booking System");
        add(panel);
        pack();
        setVisible(true);
        setResizable(false);
    }
}
