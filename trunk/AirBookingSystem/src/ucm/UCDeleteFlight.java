
package ucm;

import model.FlightModel;
import model.DatabaseModel;
import view.ManagerScreen;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.*;

/**
 *
 * @author HANG Feifei
 */
public class UCDeleteFlight implements UCController {

    private FlightModel flight;

    public UCDeleteFlight(ManagerScreen manager) {
        flight = manager.getFlightModel();
    }

    public boolean run() {
        boolean success = false;
        if(validFlight()) {
            if(deleteFlight()) {
                JOptionPane.showMessageDialog(new JFrame(),
                    "Flight information has been removed.",
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
            exp.printStackTrace();
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
            exp.printStackTrace();
        }
        return delete;
    }

}
