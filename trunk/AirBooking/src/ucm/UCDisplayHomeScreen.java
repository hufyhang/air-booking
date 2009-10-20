
package ucm;

import view.UserHomeScreen;

/**
 *
 * @author HANG Feifei
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
