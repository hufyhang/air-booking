
package ucm;

import view.SignupScreen;

/**
 *
 * @author HANG Feifei
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
