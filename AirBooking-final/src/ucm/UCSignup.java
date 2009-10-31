
package ucm;

import model.UserModel;
import model.DatabaseModel;
import view.SignupScreen;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * UCSignup.java
 * @author HANG Feifei, ZHU Hao, WANG Xi
 * This usecase allows users to sign up to the system
 */
public class UCSignup implements UCController {
    private UserModel user;

    public UCSignup() {
        user = new UserModel("", null, "", "", "");
    }

    public UCSignup(UserModel user) {
        // System retrieves username and password
		this.user = user;
    }

    public UCSignup(SignupScreen signup) {
        // System retrieves username and password
		this.user = signup.getUserModel();
    }

    public void run(UserModel user) {
        this.user = user;
        run();
    }

    public boolean run(JFrame frame) {
        return true;
    }

    //Step 1 - User selects Sign up
	public boolean run() {
        boolean success = false;
		// Step 2 - System retrieves username and password
		// Step 3 - System checks username and password are valid(5<= length <= 10)
        if( validDetails() ) {
            // Step 4 - System checks with database that username is unique
			if( uniqueUser() ) {
                // Step 5 - System registers username and password with database
				if( signupUser() ) {
                    // Step 6 - System displays successful message
					JOptionPane.showMessageDialog(new JFrame(),
                            "Registered Successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    success = true;
                } else {
					JOptionPane.showMessageDialog(new JFrame(),
                            "Registration failed for unknown reason",
                            "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                //Step 4a - System displays error message if username already exists
				JOptionPane.showMessageDialog(new JFrame(),
                        "Username already exists, please choose another",
                        "Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Step 3a - System displays error message if username or password invalid
			JOptionPane.showMessageDialog(new JFrame(),
                    "Username or password invalid (must be between 5 and 10 characters)",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
        }

        return success;
    }

    private boolean validDetails() {
        return (user.getUsername().length() >= 5) &&
                (user.getUsername().length() <= 10) &&
                (user.getPassword().length() >= 5) &&
                (user.getPassword().length() <= 10);
    }

    private boolean uniqueUser() {
        boolean unique = true;
        String username = user.getUsername();

        Connection con = DatabaseModel.getInstance().getConnection();

        try {
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.CONCUR_UPDATABLE);

            ResultSet result = stmt.executeQuery("SELCET * FROM users WHERE " + "username = '" + username + "'");

            unique = !(result.first() && username.equals(result.getString("username")));
        } catch (Exception exp) {
        }

        return unique;
    }

    private boolean signupUser() {
        boolean signup = false;
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        String gender = user.getGender();
        String role = user.getRole();

        DatabaseModel database = DatabaseModel.getInstance();

        Connection con = database.getConnection();

        try {
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate("INSERT INTO users VALUES('" + username + "', '" + password + "', '" + email + "', '" + gender + "','" + role + "')");
            signup = true;
        } catch (Exception exp) {
        }
        return signup;
    }
}
