
package ucm;

import model.UserModel;
import model.DatabaseModel;
import view.AdministratorScreen;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author HANG Feifei
 */
public class UCCreateManager implements UCController {
    private UserModel user;

    public UCCreateManager() {
        user = new UserModel("", "", "", "", "");
    }

    public UCCreateManager(UserModel user) {
        this.user = user;
    }

    public UCCreateManager(AdministratorScreen admin) {
        this.user = admin.getUserModel();
    }

    public boolean run() {
        boolean success = false;
        if(createManager()) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Manager account has been created.",
                    "Message",
                    JOptionPane.INFORMATION_MESSAGE);
                success = true;
        } else {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Unknow error.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
        }
        return success;
    }

    public boolean run(JFrame frame) {
        return true;
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
            exp.printStackTrace();
        }
        return correct;
    }
}
