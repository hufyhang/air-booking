
package ucm;

import model.FlightModel;
import model.DatabaseModel;
import view.ManagerScreen;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.*;

/**
 * UCDeleteFlight.java
 * @author HANG Feifei, ZHU Hao, WANG Xi
 * This usecase is to delete flight
 */
public class UCDeleteFlight implements UCController {

    private FlightModel flight;

    public UCDeleteFlight(ManagerScreen manager) {
        //System retrieves some information about flight
		flight = manager.getFlightModel();
    }

    // Step 1 - User selects Delete
	public boolean run() {
        boolean success = false;
		// Step 2 - System retrieves some detail information about flight
		// Step 3 - System validates the flight
        if(validFlight()) {
            if(deleteFlight()) {
			//Step 4 - System displays successful message.
                JOptionPane.showMessageDialog(new JFrame(),
                    "Flight information has been removed.",
                    "Message",
                    JOptionPane.INFORMATION_MESSAGE);
                success = true;
            }
            else
            {
                // Step 2a - System displays error message if unknow error
				JOptionPane.showMessageDialog(new JFrame(),
                    "Unknow error.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            // Step 3a - System displays error message if invalid flight ID
			JOptionPane.showMessageDialog(new JFrame(),
                    "Invalid Flight ID. Try again.",
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

    protected boolean deleteFlight() {
        boolean delete = false;
        String id = flight.getID();
        Connection con = DatabaseModel.getInstance().getConnection();

        try {
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate("DELETE FROM tickets WHERE id = '" + id + "'");
            delete = true;
        } catch (Exception exp) {
        }
        return delete;
    }

}
