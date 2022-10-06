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
import java.time.*;
import java.util.Locale;
import java.util.ResourceBundle;


/** This controller allows the creation of a new appointment */
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

    /**This method initializes the AddAppointment controller. This sets the combo boxes with the correct information from the DB.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*LocalTime start = LocalTime.of(8,0);
        LocalTime end = LocalTime.of(22,0);

        while(start.isBefore(end.plusSeconds(1))) {
            startTimeComboBox.getItems().add(start);
            endTimeComboBox.getItems().add(start);
            start = start.plusMinutes(10);
        }

        startTimeComboBox.getSelectionModel().select(LocalTime.of(8,0));
        endTimeComboBox.getSelectionModel().select(LocalTime.of(8,0));*/

        startTimeComboBox.setItems(Appointments.getZoneTime());
        endTimeComboBox.setItems(Appointments.getZoneTime());

        contactIDComboBox.setItems(ContactsCRUD.getAllContacts());
        customerIdComboBox.setItems(CustomerCRUD.getAllCustomers());
        userIdComboBox.setItems(UserCRUD.getAllUsers());

        //Appointments ContID = contactIDComboBox.getValue();


    }

    /** LAMBDA is used in the titlebox warning message, this method saves the new appointment data to the appointment table.
     * This method will display if there are missing items or incorrect data in the data fields and display the proper warning message. The LAMDA is used for the titlebox warning message to create an easier way to generate the warning message.
     */
   public void saveButtonClick(ActionEvent actionEvent) throws SQLException, IOException {

        LocalDateTime start = null;
        LocalDateTime end = null;

        try {


            start = LocalDateTime.of(startDateBox.getValue(), startTimeComboBox.getValue());
            end = LocalDateTime.of(startDateBox.getValue(), endTimeComboBox.getValue());

            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zonedStartTime = start.atZone(zoneId);
            ZonedDateTime zonedendTime = end.atZone(zoneId);
            ZonedDateTime eastZoneTime = zonedStartTime.withZoneSameInstant(ZoneId.of("America/New_York"));
            ZonedDateTime eastEndZoneTime = zonedendTime.withZoneSameInstant(ZoneId.of("America/New_York"));
            ZonedDateTime startBusinessHours = ZonedDateTime.of(start.toLocalDate(), LocalTime.of(8,0), ZoneId.of("America/New_York"));
            ZonedDateTime endBusinessHours = startBusinessHours.plusHours(14);

            if (eastZoneTime.isBefore(startBusinessHours) || eastEndZoneTime.isAfter(endBusinessHours)) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Choose an appointment time between normal business hours (8AM-10PM EST)");
                alert.showAndWait();
                return;
            }

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

            if (titleBox.getText().isEmpty()) {
                Warning_Interface notification = () -> {
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



            LocalDateTime mystartDT = LocalDateTime.of(startDateBox.getValue(), startTimeComboBox.getValue());
            LocalDateTime myEndDT = LocalDateTime.of(startDateBox.getValue(), endTimeComboBox.getValue());
            int customerId = customerIdComboBox.getValue().getCustomerId();

            for (Appointments appt : AppointmentsCRUD.getAllAppointments()) {
                if (appt.getCustomerId() != customerId)
                    continue;
                if (myEndDT.isBefore(appt.getStartDateTime().toLocalDateTime()) || myEndDT.isEqual(appt.getStartDateTime().toLocalDateTime()) ||
                        mystartDT.isAfter(appt.getEndDateTime().toLocalDateTime()) || mystartDT.isEqual(appt.getEndDateTime().toLocalDateTime())) {
                    continue;
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Conflict of Appointments");
                    alert.show();
                    return;
                }
            }

            if (startTimeComboBox.getValue().isAfter(endTimeComboBox.getValue()) || startTimeComboBox.getValue().equals(endTimeComboBox.getValue())) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Start time must be before end time.");
                alert.showAndWait();
                return;
            }

            if (startDateBox.getValue().isBefore(LocalDate.now())) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Date cannot be before current date.");
                alert.showAndWait();
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


       Timestamp startTime = Timestamp.valueOf(LocalDateTime.of(startDateBox.getValue(), startTimeComboBox.getValue()));
        Timestamp endTime = Timestamp.valueOf(LocalDateTime.of(startDateBox.getValue(), endTimeComboBox.getValue()));


       AppointmentsCRUD.insertAppointment(titleBox.getText(),
                   descriptionBox.getText(), locationBox.getText(), typeBox.getText(),
                   startTime, endTime, customerIdComboBox.getValue().getCustomerId(), userIdComboBox.getValue().getId(),
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