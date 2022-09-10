package helper;

import java.sql.*;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Customers;

public class CustomerCRUD {
    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> cList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM CUSTOMERS;";
            PreparedStatement ps = JDBC.connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(SQL);

            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                int phone = rs.getInt("Phone");
                int postalCode = rs.getInt("Postal_Code");
                int divisionId = rs.getInt("Division_ID");

                Customers c = new Customers(customerId, customerName, phone, address, postalCode, divisionId);
                cList.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cList;
    }
}
