
package ucm;

import view.UserHomeScreen;

/**
 * UCDisplayHomeScreen.java
 * @author HANG Feifei, ZHU Hao, WANG Xi
 * Display the home window
 */
public class UCDisplayHomeScreen implements UCController {
    public UCDisplayHomeScreen() {
    }

    public boolean run(javax.swing.JFrame frame) {
        return true;
    }

    public boolean run() {
        new UCDisplayUI().run(new UserHomeScreen());
        return true;
    }
}
