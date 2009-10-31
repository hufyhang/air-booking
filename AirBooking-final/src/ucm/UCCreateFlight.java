
package ucm;

import model.FlightModel;
import model.DatabaseModel;
import view.ManagerScreen;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * UCCreateFlight.java
 * @author HANG Feifei, ZHU Hao, WANG Xi
 * This usecase is to create flight
 */
public class UCCreateFlight implements UCController {
    private FlightModel flight;

    public UCCreateFlight() {
        flight = new FlightModel("", "", "", "", 0, 0, 0, 999, 1);
    }

    public UCCreateFlight(FlightModel flight) {
        // System retrieves flight
		this.flight = flight;
    }

    public UCCreateFlight(ManagerScreen manager) {
		// System retrieves flight
        flight = manager.getFlightModel();
    }
	// Step 1 - User selects Flights information
    public boolean run() {
        boolean success = false;
		// Step 2 - System retrieves some detail information of flight
		// Step 3 - System validates the flight
        if(!validFlight()){
            if(createFlight()) {
			// Step 4 - System displays successful message
                JOptionPane.showMessageDialog(new JFrame(),
                        "New flight information has been uploaded.",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
                    success = true;
            } else {
			// Step 2a - System displays error message
                JOptionPane.showMessageDialog(new JFrame(),
                        "Unknow error.",
                        "Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
		// Step 3a - System displays error message, if flight exists already
            JOptionPane.showMessageDialog(new JFrame(),
                        "Flight exists already. Try again.",
                        "Error Message",
                        JOptionPane.ERROR_MESSAGE);
        }
        return success;
    }

    public boolean run(JFrame frame) {
        return true;
    }

    protected boolean validFlight() {
        boolean correct = false;
        String id = flight.getID();

        Connection con = DatabaseModel.getInstance().getConnection();

        try {
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.CONCUR_UPDATABLE);
            ResultSet result = stmt.executeQuery("SELECT * FROM tickets WHERE " + "id = '" + id + "'");
            correct = result.first() && id.equals(result.getString("id"));
        } catch (Exception exp) {
        }
        return correct;
    }

    protected boolean createFlight() {
        boolean correct = false;
        String id = flight.getID();
        String departure = flight.getDeparture();
        String destination = flight.getDestination();
        String date = flight.getDate();
        double first = flight.getFisrt();
        double biz = flight.getBusiness();
        double econ = flight.getEconomy();
        int promotion = flight.getPromotion();
        double discount = flight.getDiscount();

        Connection con = DatabaseModel.getInstance().getConnection();
        try {
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate("INSERT INTO tickets VALUES ('" + id + "','" + departure + "','" + destination +
                    "','" + date + "','" + first + "','" + biz + "','" + econ + "','"  + promotion + "','" + discount + "')");
            correct = true;
        } catch (Exception exp) {
        }
        return correct;
    }
}
