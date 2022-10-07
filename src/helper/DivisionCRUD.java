package helper;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import model.Divisions;

/**Helper method to create, read, update and delete division data
 *
 */
public class DivisionCRUD {
    public static ObservableList<Divisions> getAllDivisions() {
        ObservableList<Divisions> dList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM FIRST_LEVEL_DIVISIONS;";
            PreparedStatement ps = JDBC.connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");

                Divisions d = new Divisions(divisionId, divisionName, countryId);
                dList.add(d);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dList;
    }
/**Method to get all the first level division data and country data based on division ID selected*/
    public static Divisions getDivisionByCountryId(int countryId) {
        try {
            String SQL = "SELECT D.* FROM first_level_divisions AS D INNER JOIN Countries AS C ON D.Country_ID = C.Country_ID AND D.Division_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(SQL);
            ps.setInt(1,countryId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");

                Divisions d = new Divisions(divisionId, divisionName, countryId);
                return d;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    /**Method to gets all the first level division data based on country ID selected*/
    public static ObservableList<Divisions> getCountryDivisions(int countryId) {
        ObservableList<Divisions> dList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(SQL);
            ps.setInt(1, countryId);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");

                Divisions d = new Divisions(divisionId, divisionName, countryId);
                dList.add(d);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dList;
    }
/**method that gets all the first level division data based on divison ID selected*/
    public static Divisions getDivision(int divisionId) {
        try {
            String SQL = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(SQL);
            ps.setInt(1,divisionId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String divisionName = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");

                Divisions d = new Divisions(divisionId, divisionName, countryId);
                return d;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
