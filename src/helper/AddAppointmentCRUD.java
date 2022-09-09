package helper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;


//this method inserts information into the SQL bench tables
public abstract class AddAppointmentCRUD {

    public static int insert(String title, String description, String location,
                             String type, Timestamp startDateTime, Timestamp endDateTime,
                             int customerId, int userId, int contactId) throws SQLException { //indicates what you want inserted
        //String sql = "INSERT INTO APPOINTMENTS (User_Name, Password) VALUES(?, ?)"; //specifies which table
        String sql = "INSERT INTO APPOINTMENTS (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, startDateTime);
        ps.setTimestamp(6, endDateTime);
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);
        int rowsAffected = ps.executeUpdate(); //required to initiate in main
        return rowsAffected;
    }

}
