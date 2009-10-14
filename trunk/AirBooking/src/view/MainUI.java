/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.*;
/**
 *
 * @author HANG Feifei
 */
public class MainUI{
   
    public MainUI(javax.swing.JFrame frame) {
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        
        if (frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;

        if (frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;
        
        frame.setLocation((screenSize.width  - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }
}
