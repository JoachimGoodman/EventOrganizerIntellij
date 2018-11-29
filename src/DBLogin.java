import java.sql.*;

public class DBLogin {

    private static String url = "jdbc:sqlite:resources/EODatabase.db";

    public boolean checkUsername(String username){

        boolean correctUsername = false;

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT USERNAME FROM LogIn WHERE USERNAME = '" + username + "'");

                if(username.equals(rs.getString("USERNAME"))) {
                    correctUsername = true;
                }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return correctUsername;
    }

    public boolean checkPassword(String username, String password){

        boolean correctPassword = false;

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PASSWORD FROM LogIn WHERE USERNAME = '" + username + "'");

            if(password.equals(rs.getString("PASSWORD"))) {
                correctPassword = true;
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return correctPassword;
    }

    public boolean isPowerUser(String username){

        boolean powerUser = false;

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT POWERUSER FROM LogIn WHERE USERNAME = '" + username + "'");

            if(rs.getInt("POWERUSER") == 1) {
                powerUser = true;
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return powerUser;
    }
}