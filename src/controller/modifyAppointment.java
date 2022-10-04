package controller;

import Lambdas.Notification_Interface;
import helper.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class modifyAppointment implements Initializable {
    public TextField modifyAppointmentIdBox;
    public TextField modifyTitleBox;
    public TextField modifyDescriptionBox;
    public TextField modifyTypeBox;
    public DatePicker modifyStartDateBox;
    public DatePicker modifyEndDateBox;
    public Button modifySaveButtonBox;
    public TextField modifyLocationBox;
    public Button modifyCancelButton;
    public ComboBox<LocalTime> modifyStartTimeComboBox;
    public ComboBox<LocalTime> modifyEndTimeComboBox;
    public ComboBox<Contacts> modifyContactIDComboBox;
    public ComboBox<Customers> modifyCustomerIdComboBox;
    public ComboBox<User> modifyUserIdComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       LocalTime start = LocalTime.of(8,0);
       LocalTime end = LocalTime.of(22,0);

        while(start.isBefore(end.plusSeconds(1))) {
            modifyStartTimeComboBox.getItems().add(start);
            modifyEndTimeComboBox.getItems().add(start);
            start = start.plusMinutes(10);
        }

        modifyStartTimeComboBox.getSelectionModel().select(LocalTime.of(8,0));
        modifyEndTimeComboBox.getSelectionModel().select(LocalTime.of(8,0));

        modifyContactIDComboBox.setItems(ContactsCRUD.getAllContacts());
        modifyCustomerIdComboBox.setItems(CustomerCRUD.getAllCustomers());
        modifyUserIdComboBox.setItems(UserCRUD.getAllUsers());


    }

    public void receiveAppointmentData(Appointments data) {


        modifyAppointmentIdBox.setText(String.valueOf(data.getAppointmentId()));
        modifyTitleBox.setText(data.getTitle());
        modifyLocationBox.setText(data.getLocation());
        modifyTypeBox.setText(data.getType());
        modifyDescriptionBox.setText(data.getDescription());
        modifyStartTimeComboBox.setValue((data.getStartDateTime().toLocalDateTime().toLocalTime()));
        modifyStartDateBox.setValue(data.getStartDateTime().toLocalDateTime().toLocalDate());
        modifyEndTimeComboBox.setValue((data.getEndDateTime().toLocalDateTime().toLocalTime()));
        modifyEndDateBox.setValue(data.getStartDateTime().toLocalDateTime().toLocalDate());


        Customers a = CustomerCRUD.getCustomers(data.getCustomerId());
        modifyCustomerIdComboBox.setValue(a);

        User u = UserCRUD.getUser(data.getUserId());
        modifyUserIdComboBox.setValue(u);


        Contacts c = ContactsCRUD.getContacts(data.getContactId());
        modifyContactIDComboBox.setValue(c);
    }

    public void modifySaveButtonClick(ActionEvent actionEvent) throws SQLException, IOException {

        boolean isMyUserComboBoxEmpty = modifyUserIdComboBox.getSelectionModel().isEmpty();
        boolean isMyCustomerComboBoxEmpty = modifyCustomerIdComboBox.getSelectionModel().isEmpty();
        boolean isMyContactComboBoxEmpty = modifyContactIDComboBox.getSelectionModel().isEmpty();
        boolean isMyStartTimeComboBoxEmpty = modifyStartTimeComboBox.getSelectionModel().isEmpty();

        String title = modifyTitleBox.getText();
        String description = modifyDescriptionBox.getText();
        String location = modifyLocationBox.getText();
        String type = modifyTypeBox.getText();
        Timestamp startTime = Timestamp.valueOf(LocalDateTime.of(modifyStartDateBox.getValue(), modifyStartTimeComboBox.getValue()));
        Timestamp endTime = Timestamp.valueOf(LocalDateTime.of(modifyStartDateBox.getValue(), modifyEndTimeComboBox.getValue()));
        int customerId = modifyCustomerIdComboBox.getValue().getCustomerId();
        int userId = modifyUserIdComboBox.getValue().getId();
        int contactId = modifyContactIDComboBox.getValue().getContactId();
        int appointmentId = Integer.parseInt(modifyAppointmentIdBox.getText());

        boolean isMyStartDateEmpty = modifyStartDateBox.getValue() == null;
        if (isMyStartDateEmpty) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please Select a Start Date");
            alert.showAndWait();
        }




        LocalDateTime mystartDT = LocalDateTime.of(modifyStartDateBox.getValue(), modifyStartTimeComboBox.getValue());
        LocalDateTime myEndDT = LocalDateTime.of(modifyStartDateBox.getValue(), modifyEndTimeComboBox.getValue());

        for (Appointments appt:AppointmentsCRUD.getAllAppointments()) {
            if (appt.getCustomerId() == customerId && appt.getAppointmentId() == appointmentId)
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

        if(modifyStartTimeComboBox.getValue().isAfter(modifyEndTimeComboBox.getValue())||modifyStartTimeComboBox.getValue().equals(modifyEndTimeComboBox.getValue())){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Start time must be before end time.");
            alert.showAndWait();
            return;
        }

        if(modifyStartDateBox.getValue().isBefore(LocalDate.now())){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Date cannot be before current date.");
            alert.showAndWait();
            return;
        }
        AppointmentsCRUD.modifyAppointment( title,  description,  location, type,  startTime,  endTime,
                customerId,  userId,  contactId,  appointmentId);

        Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentMenu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointment Menu");
        stage.setScene(scene);
        stage.show();

        //LAMBDA USED HERE TO CREATE AN EASIER WAY TO GENERATE NOTIFICATIONS.
        if (modifyTitleBox.getText().isEmpty()) {
            Notification_Interface notification = ()->{
                String sentence = "Please Enter a Title";
                return sentence;
            };
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText(notification.getMessage());
            alert.show();
            return;
        } else if (modifyDescriptionBox.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please Enter a Description");
            alert.show();
            return;
        } else if (modifyLocationBox.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please Enter a Location");
            alert.show();
            return;
        } else if (modifyTypeBox.getText().isEmpty()) {
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




    }

    public void modifyCancelButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentMenu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointment Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void contactIDComboBoxClick(ActionEvent actionEvent) {
    }

    public void customerIdComboBoxClick(ActionEvent actionEvent) {
    }

    public void userIdComboBox(ActionEvent actionEvent) {
    }
}
