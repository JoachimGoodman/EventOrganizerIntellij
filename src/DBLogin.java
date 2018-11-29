import java.sql.*;

public class DBLogin extends DBController {

    public boolean checkUsername(String username){

        boolean correctUsername = false;
        ResultSet rs = super.resultsetQuery("SELECT USERNAME FROM LogIn WHERE USERNAME = '" + username + "'");

        try {
            if(username.equals(rs.getString("USERNAME"))) {
                correctUsername = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        super.closeConnection(-1);

        return correctUsername;
    }

    public boolean checkPassword(String username, String password){

        boolean correctPassword = false;
        ResultSet rs = super.resultsetQuery("SELECT PASSWORD FROM LogIn WHERE USERNAME = '" + username + "'");

        try {
            if(password.equals(rs.getString("PASSWORD"))) {
                correctPassword = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        super.closeConnection(-1);

        return correctPassword;
    }

    public boolean isPowerUser(String username){

        boolean powerUser = false;
        ResultSet rs = super.resultsetQuery("SELECT POWERUSER FROM LogIn WHERE USERNAME = '" + username + "'");

        try {
            if(rs.getInt("POWERUSER") == 1) {
                powerUser = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        super.closeConnection(-1);

        return powerUser;
    }
}