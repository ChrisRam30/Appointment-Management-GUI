package helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCRUD {

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
}
