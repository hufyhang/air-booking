
package ucm;

import model.UserModel;
import model.DatabaseModel;
import view.AdministratorScreen;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.*;

/**
 * UCDeleteUser
 * @author HANG Feifei, ZHU Hao, WANG Xi
 * This usecase is to delete user from the database
 */
public class UCDeleteUser implements UCController {

    private UserModel user;

    public UCDeleteUser() {
        user = new UserModel("", "", "", "", "");
    }

    public UCDeleteUser(UserModel user) {
        // System retrieves username and password
		this.user = user;
    }

    public UCDeleteUser(AdministratorScreen admin) {
        // System retrieves username and password
		this.user = admin.getUserModel();
    }

    protected boolean validUser() {
        boolean correct = false;
        String username = user.getUsername();

        Connection con = DatabaseModel.getInstance().getConnection();

        try {
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.CONCUR_UPDATABLE);
            ResultSet result = stmt.executeQuery("SELECT * FROM users WHERE " + "username = '" + username + "'");
            correct = result.first() && username.equals(result.getString("username"));
        } catch (Exception exp) {
        }
        return correct;
    }

    protected boolean deleteUser() {
        boolean delete = false;
        String username = user.getUsername();
        Connection con = DatabaseModel.getInstance().getConnection();

        try {
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate("DELETE FROM users WHERE username = '" + username + "'");
            delete = true;
        } catch (Exception exp) {
        }
        return delete;
    }

    public boolean run(javax.swing.JFrame frame) {
        return true;
    }

    // Step 1 - User selects delete account
	public boolean run() {
        boolean success = false;
		// Step 2 - System retrieves username and password 
		// Step 3 - System validates the username and password
        if(validUser()) {
            if(deleteUser()) {
			// Step 4 - System displays successful message
                JOptionPane.showMessageDialog(new JFrame(),
                    "User has been deleted.",
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
            // Step 3a - System displays error message if invalid username
			JOptionPane.showMessageDialog(new JFrame(),
                    "Invalid Username. Try again.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
        }
        return success;
    }
}
