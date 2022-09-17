package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCRUD {

    public static ObservableList<User> getAllUsers() {
        ObservableList<User> uList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM Users;";
            PreparedStatement ps = JDBC.connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery(SQL);

            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userPassword = rs.getString("Password");

                User u = new User(userId, userName, userPassword);
                uList.add(u);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return uList;
    }

    public static void select() throws SQLException {
        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int id = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
        }
    }

    public static void select(String User_Name, String Password) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_Name = ? and Password = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, User_Name);
        ps.setString(2, Password);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
        }
    }

    public static User getUser(int userId) {
        try {
            String SQL = "SELECT * FROM USERS WHERE User_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(SQL);
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");

                User u = new User(userId,userName, password);
                return u;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
