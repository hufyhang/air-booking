
package ucm;

import model.UserModel;
import model.DatabaseModel;
import view.AdministratorScreen;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * UCCreateManager.java
 * @author HANG Feifei, ZHU Hao, WANG Xi
 * This usecase is to create manager
 */
public class UCCreateManager implements UCController {
    private UserModel user;

    public UCCreateManager() {
        user = new UserModel("", "", "", "", "");
    }

    public UCCreateManager(UserModel user) {
        // System retrieves username and password
		this.user = user;
    }

    public UCCreateManager(AdministratorScreen admin) {
        // System retrieves username and password
		this.user = admin.getUserModel();
    }
	// Step 1 - User selects role is manager
    public boolean run() {
        boolean success = false;
		// Step 2 - System retrieves username and password
		// Step 3 - System validates the username and password
        if(!validUser()) {
            if(createManager()) {
			//Step 4 - System displays successful message
                JOptionPane.showMessageDialog(new JFrame(),
                        "Manager account has been created.",
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
		// Step 3a - System displays error message,if user exists already
            JOptionPane.showMessageDialog(new JFrame(),
                    "User exists already. Try again.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
        }
        return success;
    }

    public boolean run(JFrame frame) {
        return true;
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

    protected boolean createManager() {
        boolean correct = false;
        String username = user.getUsername();
        String password = user.getPassword();
        String gender = "N/A";
        String email = user.getEmail();
        String role = "manager";
        
        Connection con = DatabaseModel.getInstance().getConnection();
        try {
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate("INSERT INTO users VALUES ('" + username + "','" + password + "','" + email + "','" + gender + "','" + role + "')");
            correct = true;
        } catch (Exception exp) {
        }
        return correct;
    }
}
