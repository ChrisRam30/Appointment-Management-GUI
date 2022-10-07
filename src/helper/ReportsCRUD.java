package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**Helper method for reading, updating, creating and deleting report related data
 *
 */
public class ReportsCRUD {
    public static ObservableList<Appointments> getContactAppointments() {

        ObservableList<Appointments> cList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM APPOINTMENTS WHERE CONTACT_ID = ?;";
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
                cList.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cList;
    }

}
