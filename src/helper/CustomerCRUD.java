package helper;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import model.Customers;

public class CustomerCRUD {
    /*public static ObservableList<Countries> getAllCountries() {
        ObservableList<Countries> cList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM Countries;";
            PreparedStatement ps = JDBC.connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(SQL);

            while (rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");

                Countries c = new Countries(countryId, countryName);
                cList.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cList;
    }*/

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
                String phone = rs.getString("Phone");
                String postalCode = rs.getString("Postal_Code");
                int divisionId = rs.getInt("Division_ID");

                Customers c = new Customers(customerId, customerName, phone, address, postalCode, divisionId);
                cList.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cList;
    }
    public static void deleteCustomer(int customerId) throws SQLException {

        try {
            String sql = "DELETE FROM CUSTOMERS WHERE CUSTOMER_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.executeUpdate();

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int insertCustomer(String customerName, String address, int postalCode,
                                        int phone, int divisionId) throws SQLException {
        String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, address);
        ps.setInt(3, postalCode);
        ps.setInt(4, phone);
        ps.setInt(5, divisionId);
        int rowsAffected = ps.executeUpdate(); //required to initiate in main
        return rowsAffected;
    }
}
