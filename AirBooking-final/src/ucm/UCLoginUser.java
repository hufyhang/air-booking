
package ucm;

import model.UserModel;
import model.DatabaseModel;

import javax.swing.*;
import view.WelcomeScreen;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * UCLoginUser.java
 * @author HANG Feifei, ZHU Hao, WANG Xi
 * This usecase allows users to log in to the system
 */
public class UCLoginUser implements UCController {

    private UserModel user;
    private String role;

    public UCLoginUser() {
        user = new UserModel("", "", "", "", "");
    }

    public UCLoginUser(UserModel user) {
       //System retrieves username and password
	   this.user = user;
    }

    public UCLoginUser (WelcomeScreen welcome) {
        // System retrieves username and password
		this.user = welcome.getUserModel();
    }

    public boolean run(javax.swing.JFrame frame) {
        return true;
    }

    // Step 1 - User selects Login
	public boolean run() {
        boolean login = false;
		// Step 2 - System retrieves username and password
		// Step 3 - System validates the uername and password
        if(validUser()) {
            // Step 4 - System displays main view
			JOptionPane.showMessageDialog(new JFrame(),
                    "Login Successful! ",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            int roleIndex;
            if(role.equals("user"))
                roleIndex = 0;
            else if(role.equals("manager"))
                roleIndex = 1;
            else
                roleIndex = 2;

            switch(roleIndex) {
                case 0:
                    new ucm.UCDisplayUI().run(new view.UserHomeScreen());
                    break;

                case 1:
                    new ucm.UCDisplayUI().run(new view.ManagerHomeScreen());
                    break;

                case 2:
                    new ucm.UCDisplayUI().run(new view.AdministratorScreen());
                    break;
            }
            
            login = true;

        } else {
            // Step 2a - System displays error message
			JOptionPane.showMessageDialog(new JFrame(),
                    "Invalid Username or Password. Try again.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
        }
        return login;
    }

    protected boolean validUser() {
        boolean correct = false;
        String username = user.getUsername();
        String password = user.getPassword();
        DatabaseModel database = DatabaseModel.getInstance();
        database.setServerName("localhost");

        if(username.equals("") || password.equals("")) {
            correct = false;
        }
        else {
            Connection con = database.getConnection();
            if(con == null) {
                JOptionPane.showMessageDialog(new JFrame(),
                        "Connection to database failed",
                        "Error Message",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE,
                            ResultSet.CONCUR_UPDATABLE);
                    ResultSet res, roleSet;

                    res = stmt.executeQuery("SELECT * FROM users WHERE username = '"
                            + username + "'");

                    correct = res.first() && username.equals(res.getString("username"))
                            && password.equals(res.getString("password"));

                    roleSet = stmt.executeQuery("SELECT role FROM users WHERE username = '"
                            + username + "'");
                    
                    if(roleSet.first())
                        role = roleSet.getString("role");
                    
                } catch (Exception e) {
                }
            }
        }
        return correct;
    }
}
