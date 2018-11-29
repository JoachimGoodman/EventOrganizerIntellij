import java.sql.*;
import java.util.ArrayList;

public class DBArrangement {

    private static String url = "jdbc:sqlite:resources/EODatabase.db";

    public ArrayList<Arrangement> getArrangements(){
        ArrayList<Arrangement> arrangementList = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Arrangement");

            while (rs.next())
            {
                arrangementList.add(new Arrangement(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getDouble("TOTAL"),
                        rs.getInt("TIME"),
                        rs.getInt("EVENTS"),
                        rs.getInt("ATTENDEES")
                ));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return arrangementList;
    }

    // Metode til at slette arrangementer fra databasen
    public void deleteArrangement(int id){

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute("DELETE FROM Arrangement WHERE ID ="+id);

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Metode til at oprette arrangementer i databasen
    public void createArrangement(String name, int participants){

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO Arrangement (NAME, TOTAL, TIME, EVENTS, ATTENDEES)" +
                    " VALUES ('" + name + "', '0', '0', '0', '" + participants + "')");

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}