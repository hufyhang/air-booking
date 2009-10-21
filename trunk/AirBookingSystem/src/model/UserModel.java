
package model;

/**
 *
 * @author HANG Feifei
 */
public class UserModel {

    private String username;
    private String password;
    private String email;
    private String gender;
    private String role;

    public UserModel(String username, String password, String email, String gender, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.role = role;
    }

    public UserModel() {
        this("", null, "", "", "");
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getGender() {
        return this.gender;
    }

    public String getEmail() {
        return this.email;
    }

    public String getRole() {
        return this.role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(char[] password) {
        String temp = "";
        for(int index = 0; index != password.length; ++index)
            temp += password[index];
        this.password = temp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
