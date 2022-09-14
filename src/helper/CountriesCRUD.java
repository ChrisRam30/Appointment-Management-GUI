package helper;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;

public class CountriesCRUD {
    public static ObservableList<Countries> getAllCountries() {
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
    }

    public static Countries getCountryByDivision(int divisionId) {
        try {
            String SQL = "SELECT C.* FROM Countries AS C INNER JOIN First_Level_Divisions AS D ON C.COUNTRY_ID = D.COUNTRY_ID AND D.DIVISION_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(SQL);
            ps.setInt(1,divisionId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");

                Countries c = new Countries(countryId, countryName);
                return c;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
