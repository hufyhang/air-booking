
package ucm;

import model.TicketModel;
import model.DatabaseModel;
import view.BookingScreen;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/** UCBookTicket.java
 *  @author HANG Feifei, ZHU Hao, WANG Xi
 *  This usecase is to book the ticket;
 */
public class UCBookTicket implements UCController {
    private TicketModel ticket;

    public UCBookTicket() {
        ticket = new TicketModel();
    }

    public UCBookTicket(TicketModel ticket) {
        this.ticket = ticket;
		// System retrieves information about ticket
    }

    public UCBookTicket(BookingScreen book) {
        this.ticket = book.getTicketModel();
		// System retrieves information about ticket
    }
		// Step 1 - User selects Book ticket
    public boolean run() {
        boolean success = false;
		// Step 2 - System retrieves information about ticket
        if(createBooking()) {
		// Step 2a - System displays successful message
            JOptionPane.showMessageDialog(new JFrame(),
                    "Air tickets booked successfully.",
                    "Message",
                    JOptionPane.INFORMATION_MESSAGE);
            success = true;
            } else {
			// Step 2b - System displays error message
            JOptionPane.showMessageDialog(new JFrame(),
                    "Unknow error.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            }

        return success;
    }

    public boolean run(JFrame frame) {
        return true;
    }

    protected boolean createBooking() {
        boolean correct = false;
        String flightID = ticket.getFlightID();
        String flightClass = ticket.getFlightClass();
        int tickets = ticket.getTickets();
        double cost = ticket.getCost();
        Connection con = DatabaseModel.getInstance().getConnection();
        try {
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate("INSERT INTO sellings VALUES ('" + flightID + "','" + flightClass + "','"+ tickets + "','" + cost + "')");
            correct = true;
        } catch (Exception exp) {
        }
        return correct;
    }
}
