package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.User;

public abstract class ListManager {
    public static ObservableList<User> allUsers = FXCollections.observableArrayList();
    public static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

    public static ObservableList<User> getAllUsers() {
        return allUsers;
    }

}