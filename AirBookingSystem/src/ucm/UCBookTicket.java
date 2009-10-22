
package ucm;

import model.TicketModel;
import model.DatabaseModel;
import view.BookingScreen;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author HANG Feifei
 */
public class UCBookTicket implements UCController {
    private TicketModel ticket;

    public UCBookTicket() {
        ticket = new TicketModel();
    }

    public UCBookTicket(TicketModel ticket) {
        this.ticket = ticket;
    }

    public UCBookTicket(BookingScreen book) {
        this.ticket = book.getTicketModel();
    }

    public boolean run() {
        boolean success = false;
        if(createBooking()) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Air tickets booked successfully.",
                    "Message",
                    JOptionPane.INFORMATION_MESSAGE);
            success = true;
            } else {
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
            exp.printStackTrace();
        }
        return correct;
    }
}
