
package ucm;

import model.UserModel;
import model.DatabaseModel;
import view.DeleteUserScreen;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.*;

/**
 * UCDeleteAccount.java
 * @author HANG Feifei, ZHU Hao, WANG Xi
 * This usecase is to delete account from the database
 */
public class UCDeleteAccount implements UCController {
    private UserModel user;
    public UCDeleteAccount() {
        user = new UserModel();
    }

    public UCDeleteAccount(UserModel user) {
        // System retrieves username and password
		this.user = user;
    }

    public UCDeleteAccount(DeleteUserScreen screen) {
        // System retrieves username and password
		this.user = screen.getUserModel();
    }

	// Step 1 - User select Delete account
    public boolean run() {
        boolean success = false;
		// Step 2 - System retrieves username and password
		// Step 3 - System validates the username and password
        if(checkPassword()) {
            if(deleteAccount()) {
                //Step 4 - System displays successful message
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

    public boolean run(JFrame frame) {
        return true;
    }

    protected boolean checkPassword() {
        boolean correct = false;
        Connection con = DatabaseModel.getInstance().getConnection();
        try{
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.CONCUR_UPDATABLE);
            ResultSet result = stmt.executeQuery("SELECT * FROM users WHERE username = '" + user.getUsername() + "'");
            String password = user.getPassword();
            result.first();
            if( password.equals( result.getString("password") ) );
                correct = true;
        } catch(Exception exp) {
        }
        return correct;
    }

    protected boolean deleteAccount() {
        boolean success = false;
        Connection con = DatabaseModel.getInstance().getConnection();
        try{
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate("DELETE FROM users WHERE username = '" + user.getUsername() + "'");
            success = true;
        } catch(Exception exp) {
        }
        return success;
    }

}
