package controller;


import Lambdas.Warning_Interface;
import helper.AppointmentsCRUD;
import helper.ContactsCRUD;
import helper.CustomerCRUD;
import helper.UserCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;
import model.Customers;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddAppointment implements Initializable {

    public TextField appointmentIdBox;
    public TextField titleBox;
    public TextField descriptionBox;
    public TextField typeBox;
    public Button saveButtonBox;
    public Button cancelButton;
    public TextField locationBox;
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


   public void saveButtonClick(ActionEvent actionEvent) throws SQLException, IOException {    //working on this method to at time exception

       boolean isMyStartDateEmpty = startDateBox.getValue() == null;
       if (isMyStartDateEmpty) {
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Warning Dialog");
           alert.setContentText("Please Select a Start Date");
           alert.showAndWait();
       }

       boolean isMyUserComboBoxEmpty = userIdComboBox.getSelectionModel().isEmpty();
       boolean isMyCustomerComboBoxEmpty = customerIdComboBox.getSelectionModel().isEmpty();
       boolean isMyContactComboBoxEmpty = contactIDComboBox.getSelectionModel().isEmpty();
       boolean isMyStartTimeComboBoxEmpty = startTimeComboBox.getSelectionModel().isEmpty();

       //LAMBDA USED HERE TO CREATE AN EASIER WAY TO GENERATE NOTIFICATIONS.
       if (titleBox.getText().isEmpty()) {
           Warning_Interface notification = ()->{
               String sentence = "Please Enter a Title";
               return sentence;
           };
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Warning Dialog");
           alert.setContentText(notification.getMessage());
           alert.show();
           return;
       } else if (descriptionBox.getText().isEmpty()) {
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Warning Dialog");
           alert.setContentText("Please Enter a Description");
           alert.show();
           return;
       } else if (locationBox.getText().isEmpty()) {
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Warning Dialog");
           alert.setContentText("Please Enter a Location");
           alert.show();
           return;
       } else if (typeBox.getText().isEmpty()) {
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Warning Dialog");
           alert.setContentText("Please Enter a Type");
           alert.show();
           return;
       } else if (isMyUserComboBoxEmpty) {
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Warning Dialog");
           alert.setContentText("Please Select a User");
           alert.showAndWait();
       } else if (isMyCustomerComboBoxEmpty) {
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Warning Dialog");
           alert.setContentText("Please Select a Customer");
           alert.show();
           return;
       } else if (isMyContactComboBoxEmpty) {
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Warning Dialog");
           alert.setContentText("Please Select a Contact");
           alert.show();
           return;
       } else if (isMyStartTimeComboBoxEmpty) {
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Warning Dialog");
           alert.setContentText("Please Select a Start Time");
           alert.show();
           return;
       }

       Timestamp starttime = Timestamp.valueOf(LocalDateTime.of(startDateBox.getValue(), startTimeComboBox.getValue()));
       Timestamp endtime = Timestamp.valueOf(LocalDateTime.of(startDateBox.getValue(), endTimeComboBox.getValue()));





        LocalDateTime mystartDT = LocalDateTime.of(startDateBox.getValue(), startTimeComboBox.getValue());
        LocalDateTime myEndDT = LocalDateTime.of(startDateBox.getValue(), endTimeComboBox.getValue());
        int customerId = customerIdComboBox.getValue().getCustomerId();

        for (Appointments appt:AppointmentsCRUD.getAllAppointments()) {
            if (appt.getCustomerId() != customerId)
                continue;
            if(myEndDT.isBefore(appt.getStartDateTime().toLocalDateTime()) || myEndDT.isEqual(appt.getStartDateTime().toLocalDateTime()) ||
            mystartDT.isAfter(appt.getEndDateTime().toLocalDateTime()) || mystartDT.isEqual(appt.getEndDateTime().toLocalDateTime())) {
                continue;
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Conflict of Appointments");
                alert.show();
                return;
            }
        }

       if(startTimeComboBox.getValue().isAfter(endTimeComboBox.getValue())||startTimeComboBox.getValue().equals(endTimeComboBox.getValue())){

           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setContentText("Start time must be before end time.");
           alert.showAndWait();
           return;
       }

       if(startDateBox.getValue().isBefore(LocalDate.now())){

           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setContentText("Date cannot be before current date.");
           alert.showAndWait();
           return;
       }
        AppointmentsCRUD.insertAppointment(titleBox.getText(),
                   descriptionBox.getText(), locationBox.getText(), typeBox.getText(),
                   starttime, endtime, customerIdComboBox.getValue().getCustomerId(), userIdComboBox.getValue().getId(),
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