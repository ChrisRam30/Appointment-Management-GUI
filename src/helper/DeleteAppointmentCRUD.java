/*package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAppointmentCRUD {

    public static ObservableList<Appointments> getAllAppointments() {

    ObservableList<Appointments> appList = FXCollections.observableArrayList();

    return appList;
    }

    public static void deleteAppointmenta(int appointmentId) throws SQLException { //indicates what you want deleted
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
        ps.setInt(10, contactId);
            ps.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
*/