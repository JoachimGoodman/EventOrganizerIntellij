import java.sql.*;

public abstract class DBController {

    public static String Controller(String sql){
        String url = "jdbc:sqlite:resources/EODatabase.db";

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

                //System.out.println(rs.getInt("ID"));

                //stmt.execute(sq1);

            while (rs.next())
            {
                return rs.getString("USERNAME");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Failed";
    }
}
