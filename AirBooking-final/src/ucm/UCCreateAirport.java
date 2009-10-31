
package ucm;

import model.AirportModel;
import model.DatabaseModel;
import view.AirportScreen;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * UCCreateAirport.java
 * @author HANG Feifei, ZHU Hao, WANG Xi
 * This usecase is to create airport
 */
public class UCCreateAirport implements UCController {
    private AirportModel airport;

    public UCCreateAirport() {
        airport = new AirportModel();
    }

    public UCCreateAirport(AirportModel airport) {
        // System retrieves airport;
		this.airport = airport;
    }

    public UCCreateAirport(AirportScreen air) {
	    // System retrieves airport;
        this.airport = air.getAirportModel();
    }

	// Step 1 - User selects Airports Information
    public boolean run() {
        boolean success = false;
		// Step 2 - System retrieves airport, tax rate,GST rate..
		// Step 3 - System validates the airport
        if(!validAirport()){
            if(createAirport()) {
			// Step 4 - System displays successful message
                JOptionPane.showMessageDialog(new JFrame(),
                        "New airport information has been uploaded.",
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
		// Step 3a - System displays error message,if the airport exists already.
            JOptionPane.showMessageDialog(new JFrame(),
                        "Airport exists already. Try again.",
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

    protected boolean createAirport() {
        boolean correct = false;
        String airportName = airport.getAirport();
        double tax = airport.getTax();
        double gst = airport.getGst();
        Connection con = DatabaseModel.getInstance().getConnection();
        try {
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate("INSERT INTO airports VALUES ('" + airportName + "','" + tax + "','"+ gst + "')");
            correct = true;
        } catch (Exception exp) {
        }
        return correct;
    }

}
