import java.sql.*;

public abstract class DBController {

    private static String url = "jdbc:sqlite:resources/EODatabase.db";
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    //Metode til at kunne udføre en udtagning fra databasen af en til flere kolonner fra en tabel
    public ResultSet resultsetQuery(String sqlCommand) {
        try {
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlCommand);

            return rs;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //Metode til at udføre en kommando i databasen som at slette eller opdatere
    public void statementExecute(String sqlCommand) {
        try {
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            stmt.execute(sqlCommand);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Metode til at lukke alle forbindelserne til databasen
    public void closeConnection(int closeType) {
        if (closeType == -1) {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
