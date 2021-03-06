import java.sql.*;
import java.util.ArrayList;

public class DBEvent extends DBController {

    public ArrayList<Event> getEvents(int arrangementID){

        ArrayList<Event> eventList = new ArrayList<>();
        ResultSet rs = super.resultsetQuery("SELECT * FROM Event WHERE ARRANGEMENTID = '" + arrangementID + "'");

        try {
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        super.closeConnection(true);

        return eventList;
    }

    public void deleteEvent(int id){

        super.statementExecute("DELETE FROM EVENT WHERE ID = " + id);
        //super.statementExecute("DELETE FROM EVENT WHERE ARRANGEMENTID = " + id);
        super.closeConnection(false);

    }

}