package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**Helper method for creating, reading, updating and deleting user data.
 *
 */
public class UserCRUD {
/**Method validates user/password information against the SQL database*/
    public static User validateUser(String userName, String password) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE User_Name=? AND Password =?;";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()) {
            int userId = rs.getInt("User_ID");
            User valUser = new User(userId, userName, password);
            return valUser;
        }
        return null;
    }

    /**method used to query the sql data base to get all user data information
     */
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

    /**Method querys DB for user info
     *
     * @throws SQLException
     */
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

    /**Method opens connection to DB to check user and PW info inputed.
     */
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
/**Method checks to see if username and password match*/
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
