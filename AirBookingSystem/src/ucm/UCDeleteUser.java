
package ucm;

import model.UserModel;
import model.DatabaseModel;
import view.AdministratorScreen;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.*;

/**
 *
 * @author HANG Feifei
 */
public class UCDeleteUser implements UCController {

    private UserModel user;

    public UCDeleteUser() {
        user = new UserModel("", "", "", "", "");
    }

    public UCDeleteUser(UserModel user) {
        this.user = user;
    }

    public UCDeleteUser(AdministratorScreen admin) {
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
            exp.printStackTrace();
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
            exp.printStackTrace();
        }
        return delete;
    }

    public boolean run(javax.swing.JFrame frame) {
        return true;
    }

    public boolean run() {
        boolean success = false;
        if(validUser()) {
            if(deleteUser()) {
                JOptionPane.showMessageDialog(new JFrame(),
                    "User has been deleted.",
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
                    "Invalid Username. Try again.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
        }
        return success;
    }
}
