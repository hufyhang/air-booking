
package ucm;

import model.AirportModel;
import model.DatabaseModel;
import view.AirportScreen;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author HANG Feifei
 */
public class UCDeleteAirport {

    private AirportModel airport;

    public UCDeleteAirport(AirportScreen air) {
        airport = air.getAirportModel();
    }

    public boolean run() {
        boolean success = false;
        if(validAirport()) {
            if(deleteAirport()) {
                JOptionPane.showMessageDialog(new JFrame(),
                    "Airport information has been removed.",
                    "Message",
                    JOptionPane.INFORMATION_MESSAGE);
                success = true;
            }
            else
            {
                JOptionPane.showMessageDialog(new JFrame(),
                    "Unknow error.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
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
            exp.printStackTrace();
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
            exp.printStackTrace();
        }
        return delete;
    }
}
