
package ucm;

import view.WelcomeScreen;

/**
 * UCDisplayWelcomeScreen.java
 * @author HANG Feifei, ZHU Hao, WANG Xi
 * Display the welcome window
 */
public class UCDisplayWelcomeScreen implements UCController {
    public UCDisplayWelcomeScreen() {
    }

    public boolean run(javax.swing.JFrame frame) {
        return true;
    }

    public boolean run() {
        new UCDisplayUI().run( new WelcomeScreen() );
        return true;
    }
}
