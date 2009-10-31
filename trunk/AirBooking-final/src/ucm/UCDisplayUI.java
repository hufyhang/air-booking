
package ucm;

import java.awt.*;

/**
 * UCDisplayUI.java
 * @author HANG Feifei, ZHU Hao, WANG Xi
 * Display the UI window
 */
public class UCDisplayUI implements UCController {
    public UCDisplayUI() {
    }

    public boolean run() {
        return true;
    }

    public boolean run(javax.swing.JFrame frame) {
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
        return true;
    }
}
