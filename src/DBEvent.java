import java.sql.*;
import java.util.ArrayList;

public class DBEvent {

    private static String url = "jdbc:sqlite:resources/EODatabase.db";

    public ArrayList<Event> getEvents(int arrangementID){
        ArrayList<Event> eventList = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Event WHERE ARRAGEMENTID = " + arrangementID);

            while (rs.next())
            {
                eventList.add(new Event(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getFloat("PRICE"),
                        rs.getString("DESCRIPTION"),
                        rs.getString("TYPE"),
                        rs.getString("DATESTART"),
                        rs.getString("DATEEND"),
                        rs.getString("FACILITATOR"),
                        rs.getString("NOTICE"),
                        rs.getInt("ARRANGEMENTID")
                ));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return eventList;
    }

}