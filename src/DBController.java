import java.sql.*;

public class DBController {

    public DBController(String sql){
        String url = "jdbc:sqlite:resources/EODatabase.db";

        String sq1 = "SELECT * FROM Arrangement";

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

                System.out.println(rs.getInt("ID"));

                stmt.execute(sq1);

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
