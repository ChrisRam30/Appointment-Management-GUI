package controller;


import helper.AppointmentsCRUD;
import helper.ContactsCRUD;
import helper.CustomerCRUD;
import helper.UserCRUD;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;
import model.Customers;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddAppointment implements Initializable {

    public TextField appointmentIdBox;
    public TextField titleBox;
    public TextField descriptionBox;
    public TextField typeBox;
    public Button saveButtonBox;
    public Button cancelButton;
    public TextField contactIdBox;
    public TextField customerIdBox;
    public TextField locationBox;
    public TextField userIdBox;
    public DatePicker startDateBox;
    public DatePicker endDateBox;
    public ComboBox<LocalTime> startTimeComboBox;
    public ComboBox<LocalTime> endTimeComboBox;
    public ComboBox<Contacts> contactIDComboBox;
    public ComboBox<Customers> customerIdComboBox;
    public ComboBox<User> userIdComboBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalTime start = LocalTime.of(8,0);
        LocalTime end = LocalTime.of(22,0);

        while(start.isBefore(end.plusSeconds(1))) {
            startTimeComboBox.getItems().add(start);
            endTimeComboBox.getItems().add(start);
            start = start.plusMinutes(10);
        }
        startTimeComboBox.getSelectionModel().select(LocalTime.of(8,0));
        endTimeComboBox.getSelectionModel().select(LocalTime.of(8,0));

        contactIDComboBox.setItems(ContactsCRUD.getAllContacts());
        customerIdComboBox.setItems(CustomerCRUD.getAllCustomers());
        userIdComboBox.setItems(UserCRUD.getAllUsers());

        //Appointments ContID = contactIDComboBox.getValue();


    }


   /public void saveButtonClick(ActionEvent actionEvent) throws SQLException, IOException {    //working on this method to at time exception

        Timestamp starttime = Timestamp.valueOf(LocalDateTime.of(startDateBox.getValue(), startTimeComboBox.getValue()));
        Timestamp endtime = Timestamp.valueOf(LocalDateTime.of(startDateBox.getValue(), endTimeComboBox.getValue()));



        LocalDateTime startDT = LocalDateTime.of(AppointmentsCRUD.getAllStartTimes());
        LocalDateTime endDT = LocalDateTime.of(AppointmentsCRUD.getAllEndTimes());
        LocalDateTime mystartDT = LocalDateTime.of(startDateBox.getValue(), startTimeComboBox.getValue());
        LocalDateTime myEndDT = LocalDateTime.of(startDateBox.getValue(), endTimeComboBox.getValue());

        if(mystartDT.isAfter(startDT) && myEndDT.isBefore(endDT))
            System.out.println("Conflicting appointments");
        else if(mystartDT.equals(startDT) || myEndDT.equals(endDT))
            System.out.println("Conflicting appointments");
        else
            AppointmentsCRUD.insertAppointment(titleBox.getText(),
                    descriptionBox.getText(), locationBox.getText(), typeBox.getText(),
                    starttime,endtime, customerIdComboBox.getValue().getCustomerId(), userIdComboBox.getValue().getId(),
                    contactIDComboBox.getValue().getContactId());

        Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentMenu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointment Menu");
        stage.setScene(scene);
        stage.show();

    }

    public void cancelButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentMenu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointment Menu");
        stage.setScene(scene);
        stage.show();
    }

    //private int customerId = 1;

    public void contactIDComboBoxClick(ActionEvent actionEvent) {

    }

    public void customerIdComboBoxClick(ActionEvent actionEvent) {
    }

    public void userIdComboBox(ActionEvent actionEvent) {
    }
}
