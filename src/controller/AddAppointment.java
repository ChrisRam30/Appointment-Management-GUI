package controller;


import helper.JDBC;
import helper.AddAppointmentCRUD;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
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
    public TextField contactIdBox;
    public TextField customerIdBox;
    public TextField locationBox;
    public TextField userIdBox;
    public DatePicker startDateBox;
    public DatePicker endDateBox;
    public ComboBox<LocalTime> startTimeComboBox;
    public ComboBox<LocalTime> endTimeComboBox;

    public void saveButtonClick(ActionEvent actionEvent) throws SQLException {

        Timestamp starttime = Timestamp.valueOf(LocalDateTime.of(startDateBox.getValue(), startTimeComboBox.getValue()));
        Timestamp endtime = Timestamp.valueOf(LocalDateTime.of(endDateBox.getValue(), endTimeComboBox.getValue()));


        int rowsAffected = AddAppointmentCRUD.insert(titleBox.getText(),
                descriptionBox.getText(), locationBox.getText(), typeBox.getText(),
                starttime, endtime, Integer.parseInt(customerIdBox.getText()), Integer.parseInt(userIdBox.getText()),
                Integer.parseInt(contactIdBox.getText()));

    }

    public void cancelButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentMenu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Appointment");
        stage.setScene(scene);
        stage.show();
    }

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

    }

}
