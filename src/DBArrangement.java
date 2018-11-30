import java.sql.*;
import java.util.ArrayList;

public class DBArrangement extends DBController {

    public ArrayList<Arrangement> getArrangements() {

        ArrayList<Arrangement> arrangementList = new ArrayList<>();
        ResultSet rs = super.resultsetQuery("SELECT * FROM Arrangement");

        try {
            while (rs.next()) {
                arrangementList.add(new Arrangement(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getFloat("TOTAL"),
                        rs.getInt("TIME"),
                        rs.getInt("NUMBEROFEVENTS"),
                        rs.getInt("ATTENDEES")
                ));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        super.closeConnection(true);

        return arrangementList;
    }

    // Metode til at slette arrangementer fra databasen
    public void deleteArrangement(int id){

        super.statementExecute("DELETE FROM Arrangement WHERE ID = " + id);
        super.statementExecute("DELEte FROM EVENT WHERE ARRANGEMENTID = " + id);
        super.closeConnection(false);

    }

    //Metode til at oprette arrangementer i databasen
    public void createArrangement(String name, int participants){

        super.statementExecute("INSERT INTO Arrangement (NAME, TOTAL, TIME, NUMBEROFEVENTS, ATTENDEES)" +
                " VALUES ('" + name + "', '0', '0', '0', '" + participants + "')");
        super.closeConnection(false);

    }

    // METODE TIL AT REDIGERE EKSITERENDE ARRANGEMENTER
    public void editArrangement(String name, int participants, int getId) {

        super.statementExecute("UPDATE Arrangement SET NAME = '" + name + "', ATTENDEES = '" + participants + "' WHERE ID = '" + getId + "'");
        super.closeConnection(false);
    }
}
