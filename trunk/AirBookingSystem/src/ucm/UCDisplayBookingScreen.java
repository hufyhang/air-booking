
package ucm;

import view.BookingScreen;

/**
 *
 * @author HANG Feifei
 */
public class UCDisplayBookingScreen implements UCController {
    public UCDisplayBookingScreen() {
    }

    public boolean run(javax.swing.JFrame frame) {
        return true;
    }

    public boolean run() {
        new UCDisplayUI().run(new BookingScreen());
        return true;
    }
}
