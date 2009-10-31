
package ucm;

import view.SignupScreen;

/**
 * UCDisplaySignupScreen.java
 * @author HANG Feifei, ZHU Hao, WANG Xi
 * Display the sign up window
 */
public class UCDisplaySignupScreen implements UCController {
    public UCDisplaySignupScreen() {
    }

    public boolean run(javax.swing.JFrame frame) {
        return true;
    }

    public boolean run() {
        new UCDisplayUI().run(new SignupScreen());
        return true;
    }
}
