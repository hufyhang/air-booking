
package ucm;

import view.WelcomeScreen;

/**
 *
 * @author HANG Feifei
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
