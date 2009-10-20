
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
                    "Login Successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            new ucm.UCDisplayUI().run(new view.UserHomeScreen());
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
                    ResultSet res;

                    res = stmt.executeQuery("SELECT * FROM users WHERE username = '"
                            + username + "'");

                    correct = res.first() && username.equals(res.getString("username"))
                            && password.equals(res.getString("password"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return correct;
    }
}
