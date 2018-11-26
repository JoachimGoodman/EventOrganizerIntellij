import java.sql.*;
import java.util.ArrayList;

public abstract class DBLogin {

    private static String url = "jdbc:sqlite:resources/EODatabase.db";

    public static String checkPassword(String sql){

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PASSWORD FROM LogIn WHERE USERNAME = '" + sql + "'");

            while (rs.next())
            {
                return rs.getString("PASSWORD");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String checkUsername(String sql){

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT USERNAME FROM LogIn WHERE USERNAME = '" + sql + "'");

            while (rs.next())
            {
                return rs.getString("USERNAME");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static ArrayList<String> getTitle(){

        ArrayList<String> arr = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT NAME FROM Arrangement");

            while (rs.next())
            {
                arr.add(rs.getString("NAME"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
         return arr;
    }
}
