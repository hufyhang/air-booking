
package ucm;

import model.TicketModel;
import model.DatabaseModel;
import view.BookingScreen;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**UCComputeCost.java
 * @author HANG Feifei, ZHU Hao, WANG Xi
 * This usecase is to compute the cost of tickets
 */
public class UCComputeCost implements UCController {
    TicketModel ticketModel;

    public UCComputeCost() {
        ticketModel = new TicketModel();
    }

    public UCComputeCost( TicketModel ticketModel ) {
        //  System retrieve the cost of tickets
		this.ticketModel = ticketModel;
    }

    public UCComputeCost( BookingScreen book ) {
		// System retrieve the cost of tickets
        this.ticketModel = book.getTicketModel();
    }

    public boolean run(){
        return true;
    }

    public boolean run(javax.swing.JFrame frame) {
        return true;
    }

    public String toString() {
        return computeCost();
    }
	// System computes the cost of tickets
    protected String computeCost() {
        String flightID = ticketModel.getFlightID();
        String flightClass = ticketModel.getFlightClass();
        double perCost = 0.00;
        double cost = 0.00;
        int number = ticketModel.getTickets();
        int promotion = ticketModel.getPromotion();
        double discount = ticketModel.getDiscount();
        double tax = ticketModel.getTax();
        double gst = ticketModel.getGst();

        Connection con = DatabaseModel.getInstance().getConnection();
        ResultSet result;
        try{
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.CONCUR_UPDATABLE);
            result = stmt.executeQuery("SELECT * FROM tickets WHERE id='" + flightID + "'");
            result.first();
            perCost = Double.parseDouble(result.getString(flightClass));
        } catch (Exception exp) {
        }

        double fee = perCost*number*tax + perCost*number*gst + perCost*number;
        if(number >= promotion)
            fee *= discount;

        return String.valueOf(fee);
    }
}
