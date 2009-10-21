
package ucm;

import model.UserModel;
import model.DatabaseModel;

import javax.swing.*;
import view.WelcomeScreen;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author HANG Feifei
 */
public class UCLoginUser implements UCController {

    private UserModel user;
    private String role;

    public UCLoginUser() {
        user = new UserModel("", "", "", "", "");
    }

    public UCLoginUser(UserModel user) {
        this.user = user;
    }

    public UCLoginUser (WelcomeScreen welcome) {
        this.user = welcome.getUserModel();
    }

    public boolean run(javax.swing.JFrame frame) {
        return true;
    }

    public boolean run() {
        boolean login = false;
        if(validUser()) {
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
                    break;

                case 2:
                    new ucm.UCDisplayUI().run(new view.AdministratorScreen());
                    break;
            }
            
            login = true;

        } else {
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
                    e.printStackTrace();
                }
            }
        }
        return correct;
    }
}
