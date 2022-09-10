package helper;

import java.sql.*;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

public class AppointmentsCRUD {
    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> aList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM APPOINTMENTS;";
            PreparedStatement ps = JDBC.connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(SQL);

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contact = rs.getInt("Contact_ID");
                String type = rs.getString("Type");
                Timestamp startDateTime = rs.getTimestamp("Start");
                Timestamp endDateTime = rs.getTimestamp("End");
                int customerId = rs.getInt("Customer_ID");
                int UserId = rs.getInt("User_ID");

                Appointments a = new Appointments(appointmentId, title, description, location, contact, type, startDateTime,
                        endDateTime, customerId, UserId);
                aList.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return aList;
    }

    public static int insertAppointment(String title, String description, String location,
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

    public static void deleteAppointment(int appointmentId) throws SQLException { //indicates what you want deleted
        try {
            String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, appointmentId);
        /*ps.setString(2, title);
        ps.setString(3, description);
        ps.setString(4, location);
        ps.setString(5, type);
        ps.setTimestamp(6, startDateTime);
        ps.setTimestamp(7, endDateTime);
        ps.setInt(8, customerId);
        ps.setInt(9, userId);
        ps.setInt(10, contactId);*/
            ps.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
