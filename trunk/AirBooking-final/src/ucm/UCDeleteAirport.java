
package ucm;

import model.AirportModel;
import model.DatabaseModel;
import view.AirportScreen;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * UCDeleteAirport.java
 * @author HANG Feifei, ZHU Hao, WANG Xi
 * This usecase is to delete airport
 */
public class UCDeleteAirport {

    private AirportModel airport;

    public UCDeleteAirport(AirportScreen air) {
        // System retrieves some information about airport
		airport = air.getAirportModel();
    }

	//Step 1 - User selects Delete
    public boolean run() {
        boolean success = false;
		//Step 2 - System retrieves airport, tax rate, GST rate
		//Step 3 - System validates the airport
        if(validAirport()) {
            if(deleteAirport()) {
			// Step 4 - System displays successful message
                JOptionPane.showMessageDialog(new JFrame(),
                    "Airport information has been removed.",
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
            // Step 3a - System displays error message if invalid airport
			JOptionPane.showMessageDialog(new JFrame(),
                    "Invalid Airport. Try again.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
        }
        return success;
    }

    public boolean run(JFrame frame) {
        return true;
    }

    protected boolean validAirport() {
        boolean correct = false;
        String airportName = airport.getAirport();

        Connection con = DatabaseModel.getInstance().getConnection();

        try {
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.CONCUR_UPDATABLE);
            ResultSet result = stmt.executeQuery("SELECT * FROM airports WHERE " + "airport = '" + airportName + "'");
            correct = result.first() && airportName.equals(result.getString("airport"));
        } catch (Exception exp) {
        }
        return correct;
    }

    protected boolean deleteAirport() {
        boolean delete = false;
        String airportName = airport.getAirport();
        Connection con = DatabaseModel.getInstance().getConnection();

        try {
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate("DELETE FROM airports WHERE airport = '" + airportName + "'");
            delete = true;
        } catch (Exception exp) {
        }
        return delete;
    }
}
