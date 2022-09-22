package helper;

import java.sql.*;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Contacts;
import model.MonthCustomerCount;
import model.MonthTypeCount;

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

    @Override public String toString(){
        return("Contact_ID");
    }



    public static int insertAppointment(String title, String description, String location,
                             String type, Timestamp startDateTime, Timestamp endDateTime,
                             int customerId, int userId, int contactId) throws SQLException { //indicates what you want inserted
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
            ps.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int modifyAppointment(String title, String description, String location,
                                        String type, Timestamp startDateTime, Timestamp endDateTime,
                                        int customerId, int userId, int contactId, int appointmentId) throws SQLException { //indicates what you want inserted
        String sql = "UPDATE APPOINTMENTS SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_Id =?, Contact_ID =? WHERE Appointment_ID = ? ";
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
        ps.setInt(10, appointmentId);
        int rowsAffected = ps.executeUpdate(); //required to initiate in main
        return rowsAffected;
    }

    public static ObservableList<Appointments> getAppointmentsByMonth() {

        ObservableList<Appointments> aList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM APPOINTMENTS WHERE MONTH(start) = MONTH(CURRENT_DATE()) AND YEAR(start) = YEAR(CURRENT_DATE())";
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

    public static ObservableList<Appointments> getAppointmentsByWeek() {

        ObservableList<Appointments> aList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM APPOINTMENTS WHERE WEEK(start) = WEEK(CURRENT_DATE()) AND YEAR(start) = YEAR(CURRENT_DATE())";
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

    public static ObservableList<Appointments> getContactAppointments(int contactId) {
        ObservableList<Appointments> cList = FXCollections.observableArrayList();

        try {
            String SQL = "SELECT * FROM appointments where Contact_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(SQL);
            ps.setInt(1,contactId);
            ResultSet rs = ps.executeQuery();

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

                Appointments c = new Appointments(appointmentId, title, description, location, contact, type, startDateTime,
                        endDateTime, customerId, UserId);
                cList.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cList;
    }

    public static ObservableList<Appointments> getTypeAppointments(String typeAppointments) {
        ObservableList<Appointments> cList = FXCollections.observableArrayList();

        try {
            String SQL = "SELECT * FROM appointments where Type = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(SQL);
            ps.setString(1,typeAppointments);
            ResultSet rs = ps.executeQuery();

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

                Appointments c = new Appointments(appointmentId, title, description, location, contact, type, startDateTime,
                        endDateTime, customerId, UserId);
                cList.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cList;
    }

    public static ObservableList<Timestamp> getAllMonths() { //this allows for the call of DISTINCT months to combo box*****

        ObservableList<Timestamp> tList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT distinct month(start) from appointments";
            PreparedStatement ps = JDBC.connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(SQL);

            while (rs.next()) {
                Timestamp start = rs.getTimestamp("Start");

                tList.add(start);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tList;
    }

    public static ObservableList<MonthTypeCount> getAppointmentTMCount() {

        ObservableList<MonthTypeCount> cList = FXCollections.observableArrayList();

        try {
            String SQL = "SELECT Monthname(start) AS MN, type, COUNT(type) AS CT FROM appointments GROUP BY Monthname(start), type";
            PreparedStatement ps = JDBC.connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String type = rs.getString("Type");
                String monthName = rs.getString("MN");
                int count = rs.getInt("CT");

                MonthTypeCount c = new MonthTypeCount(monthName, type, count);
                cList.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cList;
    }

    public static ObservableList<MonthCustomerCount> getAppointmentMCCount() {

        ObservableList<MonthCustomerCount> cList = FXCollections.observableArrayList();

        try {
            String SQL = "SELECT Monthname(start) AS MN, Customer_ID, COUNT(Customer_ID) AS CT FROM appointments GROUP BY Monthname(start), Customer_ID";
            PreparedStatement ps = JDBC.connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String monthName = rs.getString("MN");
                int count = rs.getInt("CT");

                MonthCustomerCount c = new MonthCustomerCount(monthName, customerId, count);
                cList.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cList;
    }
}
