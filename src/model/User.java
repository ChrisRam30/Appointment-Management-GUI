package model;

public class User {

    private int id;
    private String userName;
    private String password;

    public User(int userId, String userName, String userPassword) {
        this.id = userId;
        this.userName = userName;
        this.password = userPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return(getUserName());
    }
}
