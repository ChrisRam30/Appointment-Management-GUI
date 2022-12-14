package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;
import model.Countries;
import model.Divisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**Helper method for contacts to update, read , delete and create
 *
 */
public class ContactsCRUD {

    public static ObservableList<Contacts> getAllContacts() {
        ObservableList<Contacts> cList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM Contacts;";
            PreparedStatement ps = JDBC.connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(SQL);

            while (rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("email");

                Contacts c = new Contacts(contactId, contactName, email);
                cList.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cList;
    }

    /**Helper used to query the SQL DB for all contacts with a specific contact ID
     */
    public static Contacts getContacts(int contactId) {
        try {
            String SQL = "SELECT * FROM Contacts WHERE Contact_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(SQL);
            ps.setInt(1,contactId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                Contacts c = new Contacts(contactId,contactName, email);
                return c;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


}
