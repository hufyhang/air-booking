
package ucm;

import view.BookingScreen;

/**
 * UCDisplayBookingScreen.java
 * @author HANG Feifei, ZHU Hao, WANG Xi
 * Display the the booking window
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
